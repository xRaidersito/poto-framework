package me.raider.poto.channel;

import me.raider.poto.channel.type.ChannelType;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.function.BiConsumer;

import java.util.function.Predicate;

public interface ChatChannel {

    List<Predicate<AsyncPlayerChatEvent>> getPredicateList();

    BiConsumer<AsyncPlayerChatEvent, ChatChannel> getConsumer();

    String getPrefix();

    String getPermission();

    ChannelType getChannelType();

    boolean isPermissionBased();

    boolean isTypeBased();

}
