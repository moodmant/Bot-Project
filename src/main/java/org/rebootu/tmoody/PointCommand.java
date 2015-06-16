package org.rebootu.tmoody;

import org.rebootu.tmoody.bo.ViewerBo;
import org.rebootu.tmoody.models.Viewer;
import org.rebootu.tmoody.util.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;

/**
 * Created by taylor on 6/16/15.
 */
public class PointCommand implements Command {

    private MyBot bot;

    public PointCommand(MyBot bot) {
        this.bot = bot;
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message){

        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
        ViewerBo viewerBo = (ViewerBo)ctx.getBean("viewerBo");

        Viewer viewer = viewerBo.findByViewerName(sender);
        bot.sendMessage(channel, sender + " you have: " + viewer.getPoints());
    }

}
