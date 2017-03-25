package com.thinkman.test;

import com.thinkman.springboot.MyApplication;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by wangx on 2017-03-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyApplication.class)
@Transactional
public class RedisTest {

    private Logger logger = Logger.getLogger(DateTimeTest.class);

    @Autowired
    private JedisPool jedisPool;

    @Test
    @Rollback
    public void redisTest() {
        logger.info("Redis Test!!!");

    }
}
