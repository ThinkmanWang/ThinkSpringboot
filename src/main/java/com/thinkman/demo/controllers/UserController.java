package com.thinkman.demo.controllers;

import com.google.gson.Gson;
import com.thinkman.demo.RetObject;
import com.thinkman.demo.mapper.UserMapper;
import com.thinkman.demo.models.User;
import com.thinkman.springboot.core.redisconfig.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisUtil mRedisUtils;

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

        mRedisUtils.set("fxxk_fxxxxxxk", "123");

        return new Gson().toJson(ret);
    }
}
