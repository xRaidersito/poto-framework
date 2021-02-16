package me.raider.poto.arena.game.phase.defaultphases;

import me.raider.poto.arena.game.Game;
import me.raider.poto.arena.game.phase.AbstractPhase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.Plugin;

public abstract class GamePhase extends AbstractPhase {

    private final boolean canBreak;
    private final boolean canPlace;
    private final boolean canSpawn;
    private final boolean canReceiveDamage;
    private final boolean canSpecChat;
    private final boolean canDrop;
    private final boolean canHungerLoss;
    private final boolean canPickup;

    public GamePhase(Plugin plugin, Game game, boolean canBreak, boolean canPlace,
                     boolean canSpawn, boolean canSpecChat, boolean canReceiveDamage,
                     boolean canHungerLoss, boolean canDrop, boolean canPickup) {

        super(plugin, game);

        this.canBreak=canBreak;
        this.canPlace=canPlace;
        this.canSpecChat=canSpecChat;
        this.canSpawn=canSpawn;
        this.canReceiveDamage=canReceiveDamage;
        this.canHungerLoss=canHungerLoss;
        this.canDrop=canDrop;
        this.canPickup=canPickup;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (event.getBlock().getWorld().equals(getGame().getArena().getWorld().getWorld()) && !canBreak) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        if (event.getBlock().getWorld().equals(getGame().getArena().getWorld().getWorld()) && !canPlace) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {

        if (event.getLocation().getWorld().equals(getGame().getArena().getWorld().getWorld()) && !canSpawn) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpecChat(AsyncPlayerChatEvent event) {

        if (event.getPlayer().getWorld().equals(getGame().getArena().getWorld().getWorld()) && !canSpecChat) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        if (event.getEntity().getWorld().equals(getGame().getArena().getWorld().getWorld()) && !canReceiveDamage) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityHungerLoss(FoodLevelChangeEvent event) {

        if (event.getEntity().getWorld().equals(getGame().getArena().getWorld().getWorld()) && !canHungerLoss) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {

        if (event.getPlayer().getWorld().equals(getGame().getArena().getWorld().getWorld()) && !canDrop) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPickUp(PlayerPickupItemEvent event) {

        if (event.getPlayer().getWorld().equals(getGame().getArena().getWorld().getWorld()) && !canPickup) {
            event.setCancelled(true);
        }
    }




}
