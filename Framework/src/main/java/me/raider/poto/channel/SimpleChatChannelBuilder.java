package me.raider.poto.channel;

import me.raider.poto.channel.type.ChannelType;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class SimpleChatChannelBuilder implements ChatChannelBuilder {

    private final String name;

    private String prefix;
    private String permission;
    private ChannelType type;

    private List<BiPredicate<AsyncPlayerChatEvent, ChatChannel>> predicates = new ArrayList<>();
    private BiConsumer<AsyncPlayerChatEvent, ChatChannel> consumer;


    public SimpleChatChannelBuilder(String name) {
        this.name=name;
    }


    @Override
    public ChatChannelBuilder permission(String permission) {
        this.permission=permission;
        return this;
    }

    @Override
    public ChatChannelBuilder prefix(String prefix) {
        this.prefix=prefix;
        return this;
    }

    @Override
    public ChatChannelBuilder type(ChannelType<?> type) {
        this.type=type;
        return this;
    }

    @Override
    public ChatChannelBuilder filter(BiPredicate<AsyncPlayerChatEvent, ChatChannel> predicate) {
        this.predicates.add(predicate);
        return this;
    }

    @Override
    public ChatChannelBuilder handler(BiConsumer<AsyncPlayerChatEvent, ChatChannel> consumer) {
        this.consumer=consumer;
        return this;
    }

    @Override
    public ChatChannel build() {

        return new SimpleChatChannel(name, prefix, permission, type, predicates, consumer);
    }
}
