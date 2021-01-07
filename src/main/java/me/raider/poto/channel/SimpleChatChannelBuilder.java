package me.raider.poto.channel;

import me.raider.poto.channel.type.ChannelType;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class SimpleChatChannelBuilder implements ChatChannelBuilder {

    private final String name;

    private String prefix;
    private String permission;
    private ChannelType type;

    private boolean permissionBased;
    private boolean typeBased;

    private List<Predicate<AsyncPlayerChatEvent>> predicates = new ArrayList<>();
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
    public ChatChannelBuilder mode(String mode) {
        switch (mode.toLowerCase()) {
            case "permission":
                this.permissionBased=true;
                this.typeBased=false;
                break;
            case "type":
                this.permissionBased=false;
                this.typeBased=true;
                break;
            case "mixed":
                this.typeBased=true;
                this.permissionBased=true;
                break;
            default:
                break;
        }
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
    public ChatChannelBuilder filter(Predicate<AsyncPlayerChatEvent> predicate) {
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

        return new SimpleChatChannel(name, prefix, permission, type, permissionBased, typeBased, predicates, consumer);
    }
}
