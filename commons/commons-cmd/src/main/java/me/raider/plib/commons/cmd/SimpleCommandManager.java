package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.message.MessageProvider;
import me.raider.plib.commons.cmd.message.Messenger;
import me.raider.plib.commons.cmd.tree.CommandTree;

import java.util.List;

public class SimpleCommandManager implements CommandManager {

    private final CommandTree tree;
    private final CommandExecutor executor;
    private final CommandSupplierManager supplierManager;
    private final MessageProvider messageProvider;
    private final ArgumentRegistry argumentRegistry;
    private final Authorizer<?> authorizer;
    private final Messenger<?> messenger;

    public SimpleCommandManager(MessageProvider messageProvider, Authorizer<?> authorizer, Messenger<?> messenger,
                                ArgumentRegistry argumentRegistry) {
        this.messageProvider = messageProvider;
        this.authorizer = authorizer;
        this.messenger = messenger;
        this.argumentRegistry = argumentRegistry;
        this.tree = new CommandTree(argumentRegistry);
        this.executor = new DefaultCommandExecutor(tree, messageProvider, authorizer, messenger);
        this.supplierManager = new SimpleCommandSupplierManager();
    }

    @Override
    public void register(Command command) {
        tree.addCommandToTree(command);
    }

    @Override
    public void register(List<Command> commands) {
        for (Command command : commands) {
            register(command);
        }
    }

    @Override
    public void register(Command... commands) {
        for (Command command : commands) {
            register(command);
        }
    }

    @Override
    public CommandSupplierManager getSuppliers() {
        return supplierManager;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }

    @Override
    public Authorizer<?> getAuthorizer() {
        return authorizer;
    }

    @Override
    public Messenger<?> getMessenger() {
        return messenger;
    }

    @Override
    public CommandExecutor getExecutor() {
        return executor;
    }

    @Override
    public CommandTree getTree() {
        return tree;
    }

    @Override
    public ArgumentRegistry getArgumentRegistry() {
        return argumentRegistry;
    }
}
