package org.rebootu.tmoody;

/**
 * Created by taylor on 6/4/15.
 */

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MyBot extends PircBot {

    public static final String CHANNEL = "#moodmant";

    public MyBot() {
        this.setName("CognoscoBot");
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.getCommand(message, this);
        if (command != null)
        command.onMessage(channel, sender, login, hostname, message);
    }

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public void start() throws Exception {
        this.setVerbose(true);

        // Connect to the IRC server.
        try {
            this.connect("irc.twitch.tv", 6667, "oauth:0jiz9xupoiw0ialyr82vh3ptbc3t2c");
        } catch (NickAlreadyInUseException e) {
            System.err.println("Nickname is currently in use");
        } catch (IrcException e) {
            System.err.println("Server did not accept connection");
            e.printStackTrace();
        }
        // Join the #pircbot channel.
        this.joinChannel(CHANNEL);
        this.sendRawLine("CAP REQ :twitch.tv/membership");
        this.sendMessage(CHANNEL, "hello again");
    }


    public void leave()throws Exception{

        this.sendMessage(CHANNEL, "I'll be back.");
        this.partChannel(CHANNEL);
        this.quitServer("irc.twitch.tv");
    }
}