package org.rebootu.tmoody.controller;

import org.jibble.pircbot.User;
import org.rebootu.tmoody.MyBot;
import org.rebootu.tmoody.bo.ViewerBo;
import org.rebootu.tmoody.models.Viewer;
import org.rebootu.tmoody.util.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

;import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by taylor on 6/9/15.
 */

@Controller
public class BotController {

    private MyBot bot;
    public boolean inChat = false;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "standby";
    }

    @RequestMapping(value = "/joined")
    public String joined(Model model) throws Exception {

        if (inChat == false) {
            this.bot = new MyBot();
            inChat = true;
            bot.start();
            this.startCycle();

            return "joined_form";}
        else {
            System.out.println("check for already joined.");
            return "joined_form";
        }
    }

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private void startCycle() {

        //@Entity
        //@Table(name = "points")
        Runnable SQLPoints = new Runnable() {
            public void run() {
                int i;

                ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();

                ViewerBo viewerBo = (ViewerBo)ctx.getBean("viewerBo");

                User[] list = bot.getUsers(MyBot.CHANNEL);
                for (i = 0; i < list.length; i++) {
                    User user = list[i];
                    Viewer viewer = viewerBo.findByViewerName(user.getNick());
                    if (viewer == null) {
                        viewer = new Viewer();
                        viewer.setViewerName(user.getNick());
                    }
                    viewer.addPoint();

                    viewerBo.save(viewer);
                    System.out.println(list[i]);
                }

            }
        };

        executor.scheduleAtFixedRate(SQLPoints, 0, 30, TimeUnit.SECONDS);
    }

    @RequestMapping(value = "/leave")
    public String leave(Model model) throws Exception {

        if (inChat == true){
            this.bot.leave();
            this.executor.shutdownNow();
            inChat = false;
            return "standby";}
        else { return "standby";}
    }
}
