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

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

;

/**
 * Created by taylor on 6/9/15.
 */

@Controller
public class BotController {

    private MyBot bot;
    public boolean inChat = false;
    public ViewerBo viewerBo;



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

                ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
                ViewerBo viewerBo = (ViewerBo)ctx.getBean("viewerBo");


                User[] list = bot.getUsers(MyBot.CHANNEL);
                for (int i = 0; i < list.length; i++) {
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

        executor.scheduleAtFixedRate(SQLPoints, 0, 5, TimeUnit.MINUTES);
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

    @RequestMapping(value = "/points")
    public String points(Model model) throws Exception{
        ArrayList<Viewer> viewers;
        viewers = viewerBo.findAll();
        model.addAttribute("viewers", viewers);
        return "points";
    }
}
