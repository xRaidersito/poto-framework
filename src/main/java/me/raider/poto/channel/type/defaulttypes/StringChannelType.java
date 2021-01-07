package me.raider.poto.channel.type.defaulttypes;

import me.raider.poto.channel.type.ChannelType;

public class StringChannelType implements ChannelType<String> {

    private String type;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type=type;
    }
}
