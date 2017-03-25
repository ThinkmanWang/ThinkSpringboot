package com.thinkman.test;

import com.thinkman.springboot.MyApplication;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangx on 2017-03-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyApplication.class)
@Transactional
public class DateTimeTest {

    private Logger logger = Logger.getLogger(DateTimeTest.class);

    @Test
    @Rollback
    public void firstTest() {
        logger.info("First Test!!!");
    }
}
