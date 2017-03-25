package com.thinkman.demo.scheduled;

import com.thinkman.demo.controllers.UserController;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wangx on 2017-03-25.
 */
@Component
public class SomeSchedule {

    private Logger logger = Logger.getLogger(SomeSchedule.class);

    @Scheduled(fixedRate = 5000)
    public void doSomethong() {
        logger.info("OOXX");
    }

    //do at 00:00
    @Scheduled(cron = "0 0 0 * * ?")
    public void doSomething0() {
        logger.info("OOXX");

    }

    //do at 01:00
    @Scheduled(cron = "0 0 1 * * ?")
    public void doSomething1() {
        logger.info("OOXX");

    }
}
