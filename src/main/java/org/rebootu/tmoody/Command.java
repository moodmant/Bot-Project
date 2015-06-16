package org.rebootu.tmoody;

/**
 * Created by taylor on 6/8/15.
 */
public interface Command {
    void onMessage(String channel, String sender, String login, String hostname, String message);
}
