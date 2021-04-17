package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.message.MessageProvider;
import me.raider.plib.commons.cmd.message.Messenger;
import me.raider.plib.commons.cmd.tree.CommandTree;

import java.util.List;

public interface CommandManager {

    void register(Command command);

    void register(List<Command> commands);

    void register(Command... commands);

    CommandSupplierManager getSuppliers();

    MessageProvider getMessageProvider();

    Authorizer<?> getAuthorizer();

    Messenger<?> getMessenger();

    Executor getExecutor();

    CommandTree getTree();

}
