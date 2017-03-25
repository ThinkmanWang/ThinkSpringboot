package com.thinkman.notes.controllers;

import com.google.gson.Gson;
import com.thinkman.notes.RetObject;
import com.thinkman.notes.mapper.UserMapper;
import com.thinkman.notes.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangx on 2017-02-27.
 */
@RestController
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserMapper mUserMapper;

    private Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value="/login.do", method={RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request) {

        //18621675203 af894449f71fa7689b0bbd66bda09961
        //http://localhost:8081/login.do?username=18621675203&password=af894449f71fa7689b0bbd66bda09961
        String name = request.getParameter("username");
        String pass = request.getParameter("password");

        logger.info(String.format("User Login user_name: %s, password: %s", name, pass));

        org.apache.ibatis.logging.LogFactory.useLog4JLogging();
        User user = mUserMapper.login(name, pass);

        RetObject ret = new RetObject();
        ret.code = 0;
        ret.message = "success";
        if (null != user) {
            ret.data = user;
            logger.info("User not null");
            logger.info(new Gson().toJson(user));
        } else {
            logger.error("User is null");
        }

        return new Gson().toJson(ret);
    }
}
