package me.raider.poto.commons.cmd;

import me.raider.poto.commons.cmd.tree.CommandTree;

import java.util.List;

public interface CommandManager {

    void register(Command command);

    void registerAll(List<Command> commands);

    void registerAll(Command... commands);

    CommandSupplierManager getSuppliers();

    Executor getExecutor();

    CommandTree getTree();

}
