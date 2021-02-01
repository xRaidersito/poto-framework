package me.raider.poto.channel;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Map;
import java.util.function.BiPredicate;

public class ChatChannelListener<T extends ChannelObject> implements Listener {

    private final Map<String, T> channelHolders;

    public ChatChannelListener(Map<String, T> channelHolders) {
        this.channelHolders=channelHolders;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        ChatChannel chatChannel = channelHolders.get(player.getUniqueId().toString()).getChatChannel();

        if (chatChannel==null) {
            return;
        }

        if (!player.hasPermission(chatChannel.getPermission())) {
            return;
        }

        for (BiPredicate<AsyncPlayerChatEvent, ChatChannel> predicate : chatChannel.getPredicateList()) {
            if (!predicate.test(event, chatChannel)) {
                return;
            }
        }

        for (Player recipient : event.getRecipients()) {

            ChatChannel recipientChannel = channelHolders.get(recipient.getUniqueId().toString()).getChatChannel();

            if (recipientChannel==null) {
                continue;
            }

            if (!recipientChannel.equals(chatChannel)) {
                continue;
            }

            chatChannel.getConsumer().accept(event, chatChannel);
        }
    }
}
