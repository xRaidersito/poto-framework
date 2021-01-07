package me.raider.poto.object.defaultobj;

import me.raider.poto.Storable;
import me.raider.poto.channel.ChatChannel;

public interface User extends Storable {

    ChatChannel getChatChannel();

    void setChatChannel(ChatChannel chatChannel);

}
