package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.message.MessageProvider;
import me.raider.plib.commons.cmd.message.Messenger;
import me.raider.plib.commons.cmd.resolved.ResolvedArgument;
import me.raider.plib.commons.cmd.tree.CommandTree;

public class DefaultExecutor implements Executor {

    private final CommandTree commandTree;
    private final MessageProvider messageProvider;
    private final Authorizer<?> authorizer;
    private final Messenger<?> messenger;

    protected DefaultExecutor(CommandTree commandTree, MessageProvider messageProvider, Authorizer<?> authorizer, Messenger<?> messenger) {
        this.commandTree = commandTree;
        this.messageProvider = messageProvider;
        this.authorizer = authorizer;
        this.messenger = messenger;
    }

    @Override
    public void execute(String[] args, Object[] injected, Object[] authorized, Object[] message) {

        WrappedCommandResult commandResult = commandTree.traverseTree(args, injected);

        switch (commandResult.getResult()) {
            case SUCCESSFUL:
                for (Object authorize : authorized) {
                    if (!authorize(authorize, commandResult.getCommand())) {
                        sendMessageToAll(message, "no-permission");
                        return;
                    }
                }
                commandResult
                        .getCommand()
                        .getAction()
                        .start(commandResult.getResolvedArguments().toArray(new ResolvedArgument[0]));
                break;
            case INVALID:
                sendMessageToAll(message, "invalid-argument");
                break;
            case INJECTED_FAILURE:
                sendMessageToAll(message, "invalid-injection");
                break;
        }
    }

    private <T> boolean authorize(T authorized, Command command) {
        if (authorizer==null) {
            return true;
        }
        Authorizer<T> authorizer = (Authorizer<T>) this.authorizer;
        try {
            return authorizer.isAuthorized(authorized, command);
        } catch (ClassCastException ignored) {}
        return false;
    }

    private void sendMessageToAll(Object[] holders, String message) {
        for (Object holder : holders) {
            sendMessage(holder, message);
        }
    }

    private <T> void sendMessage(T holder, String messageKey) {
        if (messenger==null) {
            return;
        }
        Messenger<T> messenger = (Messenger<T>) this.messenger;
        try {
            messenger.sendMessage(messageProvider.getMessage(messageKey), holder);
        } catch (ClassCastException ignored) {}
    }
}
