package com.thinkman.demo.controllers;

import com.google.gson.Gson;
import com.thinkman.demo.RetObject;
import com.thinkman.demo.models.User;
import com.thinkman.demo.workers.SomeWorker;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangx on 2017-03-25.
 */
@RestController
@EnableAutoConfiguration
public class TaskController {

    private Logger logger = Logger.getLogger(TaskController.class);


    @Resource(name = "taskExecutor")
    private TaskExecutor mSomeTask;

    @RequestMapping(value="/test_task.do", method={RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request) {

        mSomeTask.execute(new SomeWorker());

        RetObject ret = new RetObject();
        ret.code = 0;
        ret.message = "success";

        return new Gson().toJson(ret);
    }
}
