package me.raider.poto.channel;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ChatChannelListener<T extends ChannelObject> implements Listener {

    private T channel;

    public ChatChannelListener(T channel) {
        this.channel=channel;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        ChatChannel chatChannel = channel.getChatChannel();
        Player player = event.getPlayer();

        if (!player.hasPermission(chatChannel.getPermission())) {
            return;
        }

        for (BiPredicate<AsyncPlayerChatEvent, ChatChannel> predicate : chatChannel.getPredicateList())
            if (!predicate.test(event, chatChannel)) {
                return;
            }

        chatChannel.getConsumer().accept(event, chatChannel);

    }
}
