package me.raider.plib.bukkit.cmd

import me.raider.plib.commons.cmd.CommandManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class ExtendedCommand(cmdName: String, private val commandManager: CommandManager) : Command(cmdName) {

    override fun execute(sender: CommandSender?, s: String?, args: Array<out String>?): Boolean {
        commandManager.executor.execute(args, sender)
        return true
    }
}