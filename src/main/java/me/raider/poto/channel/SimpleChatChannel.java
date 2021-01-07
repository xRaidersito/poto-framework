package me.raider.poto.channel;

import me.raider.poto.channel.type.ChannelType;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class SimpleChatChannel implements ChatChannel {

    private final String name;

    private final String prefix;
    private final String permission;
    private final ChannelType type;

    private final boolean permissionBased;
    private final boolean typeBased;

    private final List<Predicate<AsyncPlayerChatEvent>> predicates;
    private final BiConsumer<AsyncPlayerChatEvent, ChatChannel> consumer;

    public SimpleChatChannel(String name, String prefix, String permission, ChannelType type,
                             boolean permissionBased, boolean typeBased, List<Predicate<AsyncPlayerChatEvent>> predicates,
                             BiConsumer<AsyncPlayerChatEvent, ChatChannel> consumer) {
        this.name = name;
        this.prefix = prefix;
        this.permission = permission;
        this.type = type;
        this.permissionBased = permissionBased;
        this.typeBased = typeBased;
        this.predicates = predicates;
        this.consumer = consumer;
    }


    @Override
    public List<Predicate<AsyncPlayerChatEvent>> getPredicateList() {
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
    public ChannelType getChannelType() {
        return type;
    }

    @Override
    public boolean isPermissionBased() {
        return permissionBased;
    }

    @Override
    public boolean isTypeBased() {
        return typeBased;
    }
}
