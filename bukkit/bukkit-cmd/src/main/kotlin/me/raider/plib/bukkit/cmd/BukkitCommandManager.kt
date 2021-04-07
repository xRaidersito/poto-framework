package me.raider.plib.bukkit.cmd

import me.raider.plib.bukkit.cmd.suppliers.PlayerSupplier
import me.raider.plib.commons.cmd.Command
import me.raider.plib.commons.cmd.CommandSupplierManager
import me.raider.plib.commons.cmd.SimpleCommandManager
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.entity.Player

class BukkitCommandManager() : SimpleCommandManager() {

    private var commandMap : CommandMap? = null

    init {
        startMap()
        val supplierManager: CommandSupplierManager = super.getSuppliers()
        supplierManager.registerSupplier(Player::class.java, PlayerSupplier())
    }

    override fun register(command: Command?) {
        if (command != null) {
            commandMap?.register(command.name, ExtendedCommand(command.name, this))
            super.getTree().addCommandToTree(command)
        }
    }

    private fun startMap() {
        var commandMap: CommandMap? = null

        try {
            val server = Bukkit.getServer()
            val getCommandMap = server.javaClass.getDeclaredMethod("getCommandMap")
            getCommandMap.isAccessible = true
            commandMap = getCommandMap.invoke(server) as CommandMap
            getCommandMap.isAccessible = getCommandMap.isAccessible
        } catch (e: Exception) {
            e.printStackTrace()
        }

        this.commandMap = commandMap
    }

}