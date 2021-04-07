package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.tree.CommandTree;

import java.util.List;

public interface CommandManager {

    void register(Command command);

    void register(List<Command> commands);

    void register(Command... commands);

    CommandSupplierManager getSuppliers();

    Executor getExecutor();

    CommandTree getTree();

}
