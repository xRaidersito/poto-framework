package me.raider.poto.channel;

import me.raider.poto.Nameable;
import me.raider.poto.channel.type.ChannelType;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.function.BiConsumer;

import java.util.function.BiPredicate;

public interface ChatChannel extends Nameable {

    List<BiPredicate<AsyncPlayerChatEvent, ChatChannel>> getPredicateList();

    BiConsumer<AsyncPlayerChatEvent, ChatChannel> getConsumer();

    String getPrefix();

    String getPermission();

    ChannelType<?> getChannelType();

}
