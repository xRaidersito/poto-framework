package me.raider.plib.bukkit.cmd.suppliers

import me.raider.plib.commons.cmd.ArgumentSupplier
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PlayerSupplier : ArgumentSupplier<Player> {

    override fun supply(`object`: Any?): Player? {
        if (`object` is CommandSender) {
            if (`object` is Player) {
                return `object`
            }
            return null
        }
        if (`object` == null) {
            return null
        }

        return Bukkit.getPlayer(`object` as String)
    }
}