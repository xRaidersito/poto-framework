package me.raider.poto.channel;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public interface ChatChannelBuilder {

    ChatChannelBuilder permission(String permission);

    ChatChannelBuilder prefix(String prefix);

    ChatChannelBuilder filter(BiPredicate<AsyncPlayerChatEvent, ChatChannel> predicate);

    ChatChannelBuilder handler(BiConsumer<AsyncPlayerChatEvent, ChatChannel> consumer);

    ChatChannel build();

    static ChatChannelBuilder create(String name) {
        return new SimpleChatChannelBuilder(name);
    }

}
