package me.raider.plib.commons.cmd;

import me.raider.plib.commons.cmd.tree.CommandTree;

import java.util.List;

public class SimpleCommandManager implements CommandManager {

    private final CommandTree tree;
    private final Executor executor;
    private final CommandSupplierManager supplierManager;

    public SimpleCommandManager() {
        this.tree = new CommandTree();
        this.executor = new DefaultExecutor(tree);
        this.supplierManager = new SimpleCommandSupplierManager();
    }

    @Override
    public void register(Command command) {
        tree.addCommandToTree(command);
    }

    @Override
    public void registerAll(List<Command> commands) {
        for (Command command : commands) {
            register(command);
        }
    }

    @Override
    public void registerAll(Command... commands) {
        for (Command command : commands) {
            register(command);
        }
    }

    @Override
    public CommandSupplierManager getSuppliers() {
        return supplierManager;
    }

    @Override
    public Executor getExecutor() {
        return executor;
    }

    @Override
    public CommandTree getTree() {
        return tree;
    }
}
