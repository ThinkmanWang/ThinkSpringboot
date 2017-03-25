package com.thinkman.test;

import com.thinkman.springboot.MyApplication;
import com.thinkman.springboot.utils.TimeUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

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

    @Test
    @Rollback
    public void dateTest() {
        logger.info(String.format("timestamp: %d", TimeUtils.getTimeStamp()));
        logger.info(String.format("now date %s", TimeUtils.getDateStr()));
        logger.info(String.format("now date time %s", TimeUtils.getDateTimeStr()));

        logger.info(String.format("yesterday: %s", TimeUtils.yesterday(new Date())));
        logger.info(String.format("diff day %d ==> %s", 0, TimeUtils.getDiffDay(new Date(), 0)));
        logger.info(String.format("diff day %d ==> %s", -5, TimeUtils.getDiffDay(new Date(), -5)));
    }
}
