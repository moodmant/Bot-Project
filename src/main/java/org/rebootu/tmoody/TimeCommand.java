package org.rebootu.tmoody;

import java.util.Date;

/**
 * Created by taylor on 6/8/15.
 */
public class TimeCommand implements Command {

    private MyBot bot;

    public TimeCommand(MyBot bot){
        this.bot = bot;
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        String time = new Date().toString();
        bot.sendMessage(channel, sender + ": The time is now " + time);
    }
}

