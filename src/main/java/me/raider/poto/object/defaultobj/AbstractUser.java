package me.raider.poto.object.defaultobj;

import me.raider.poto.channel.ChatChannel;

public abstract class AbstractUser implements User {

    private String name;
    private ChatChannel chatChannel;

    public AbstractUser(String name) {
        this.name=name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ChatChannel getChatChannel() {
        return chatChannel;
    }

    @Override
    public void setChatChannel(ChatChannel chatChannel) {
        this.chatChannel = chatChannel;
    }

    @Override
    public abstract void save();

}
