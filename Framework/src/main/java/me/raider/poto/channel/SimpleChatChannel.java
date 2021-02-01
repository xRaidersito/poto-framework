package me.raider.poto.channel;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class SimpleChatChannel implements ChatChannel {

    private final String name;

    private final String prefix;
    private final String permission;

    private final List<BiPredicate<AsyncPlayerChatEvent, ChatChannel>> predicates;
    private final BiConsumer<AsyncPlayerChatEvent, ChatChannel> consumer;

    public SimpleChatChannel(String name, String prefix, String permission,
                             List<BiPredicate<AsyncPlayerChatEvent, ChatChannel>> predicates,
                             BiConsumer<AsyncPlayerChatEvent, ChatChannel> consumer) {
        this.name = name;
        this.prefix = prefix;
        this.permission = permission;
        this.predicates = predicates;
        this.consumer = consumer;
    }


    @Override
    public List<BiPredicate<AsyncPlayerChatEvent, ChatChannel>> getPredicateList() {
        return predicates;
    }

    @Override
    public BiConsumer<AsyncPlayerChatEvent, ChatChannel> getConsumer() {
        return consumer;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public String getName() {
        return name;
    }
}
