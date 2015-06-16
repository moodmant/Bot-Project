package org.rebootu.tmoody;

import org.jibble.pircbot.*;
/**
 * Created by taylor on 6/9/15.
 */
public class ViewersCommand implements Command {

    private MyBot bot;

    public ViewersCommand(MyBot bot) {
        this.bot = bot;
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message){
        User[] list = bot.getUsers(channel);
        int viewers = list.length;
        bot.sendMessage(channel, viewers + " are here");
    }
}
