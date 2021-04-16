package me.raider.plib.bukkit.cmd;

import me.raider.plib.bukkit.cmd.supplier.PlayerSupplier;
import me.raider.plib.commons.cmd.Command;
import me.raider.plib.commons.cmd.SimpleCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class BukkitCommandManager extends SimpleCommandManager {

    private CommandMap commandMap;

    public BukkitCommandManager() {
        super();
        getSuppliers().registerSupplier(Player.class, new PlayerSupplier());
    }

    @Override
    public void register(Command command) {
        if (command!=null) {
            commandMap.register(command.getName(), new ExtendedCommand(command.getName(), this));
            super.getTree().addCommandToTree(command);
        }
    }

    private void startMap() {
        CommandMap commandMap = null;
        try {
            Server server = Bukkit.getServer();
            Method getCommandMap = server.getClass().getDeclaredMethod("getCommandMap");
            getCommandMap.setAccessible(true);
            commandMap = (CommandMap) getCommandMap.invoke(server);
            getCommandMap.setAccessible(getCommandMap.isAccessible());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.commandMap = commandMap;
    }
}