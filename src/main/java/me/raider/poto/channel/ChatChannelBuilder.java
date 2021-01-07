package me.raider.poto.channel;

import me.raider.poto.channel.type.ChannelType;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public interface ChatChannelBuilder {

    ChatChannelBuilder permission(String permission);

    ChatChannelBuilder mode(String mode);

    ChatChannelBuilder prefix(String prefix);

    ChatChannelBuilder type(ChannelType<?> type);

    ChatChannelBuilder filter(Predicate<AsyncPlayerChatEvent> predicate);

    ChatChannelBuilder handler(BiConsumer<AsyncPlayerChatEvent, ChatChannel> consumer);

    ChatChannel build();

    static ChatChannelBuilder create(String name) {
        return new SimpleChatChannelBuilder(name);
    }

}
