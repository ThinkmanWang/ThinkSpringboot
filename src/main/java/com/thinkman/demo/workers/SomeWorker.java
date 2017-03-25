package com.thinkman.demo.workers;

import com.thinkman.demo.controllers.UserController;
import org.apache.log4j.Logger;

/**
 * Created by wangx on 2017-03-25.
 */
public class SomeWorker implements Runnable {

    private Logger logger = Logger.getLogger(SomeWorker.class);

    public void run() {
        logger.info("Do some task");
    }
}
