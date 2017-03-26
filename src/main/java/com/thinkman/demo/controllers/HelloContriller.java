package com.thinkman.demo.controllers;

import com.thinkman.springboot.service.RedisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangx on 2017-03-26.
 */
@RestController
@EnableAutoConfiguration
public class HelloContriller {

    private Logger logger = Logger.getLogger(HelloContriller.class);

    @Autowired
    RedisService mRedisService;

    @RequestMapping(value="/hello.do", method={RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request) {
        mRedisService.insertSet("config_info", "123", "456", "789");
        return "success";
    }
}
