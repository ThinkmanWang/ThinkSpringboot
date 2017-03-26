package com.thinkman.demo.redis;

import com.thinkman.demo.controllers.RedisContriller;
import com.thinkman.springboot.service.RedisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by wangx on 2017-03-26.
 */
@Repository
public class BaseRedisCache {
    private Logger logger = Logger.getLogger(RedisContriller.class);

    @Autowired
    protected RedisService mRedisService;

    /**
     * 单位是秒, 也就是默认保存7天
     */
    protected static final int EXPIRE_TIME_DEFAULT = 7 * 24 * 60 * 60;

    /**
     * 单位是秒, 过期时间为1天
     */
    protected static final int EXPIRE_TIME_DAY = 1 * 24 * 60 * 60;

    /**
     * 半小时过期
     */
    protected static final int EXPIRE_TIME_DAY_30_MIN = 30 * 60;

    public String makeKey(String szKey) {
        return szKey;
    }

    public String get(String key) {
        return (String) mRedisService.query(makeKey(key));
    }

    public void set(String key, String value) {
        set(key, value, EXPIRE_TIME_DEFAULT);
    }

    public boolean contain(String szKey) {
        return mRedisService.hasKey(makeKey(szKey));
    }

    public void set(String key, String value, int expireTime) {
        mRedisService.save(makeKey(key), value, expireTime);
    }

    public void delete(String szKey) {
        mRedisService.delete(makeKey(szKey));
    }

}
