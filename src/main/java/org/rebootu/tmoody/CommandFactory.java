package org.rebootu.tmoody;

/**
 * Created by taylor on 6/8/15.
 */
public class CommandFactory {

    public Command getCommand(String input, MyBot bot){
        if (input.equalsIgnoreCase("!time")){
            return new TimeCommand(bot);
        }
        else if (input.equalsIgnoreCase("!viewers")){
            return new ViewersCommand(bot);
        }
        return null;
    }
}
