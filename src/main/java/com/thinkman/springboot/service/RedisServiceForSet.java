package com.thinkman.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by wangx on 2017-03-26.
 */
@Service
public class RedisServiceForSet {
    @Autowired
    private RedisTemplate redisTemplate;

    public void delete (String key) {
        redisTemplate.delete(key);
    }

    public boolean hasKey(String szKey) {
        return redisTemplate.hasKey(szKey);
    }

    public void insertSet(String szKey, String szVal) {
        redisTemplate.opsForSet().add(szKey, szVal);
    }

    public void insertSet(String szKey, String... szVals) {
        redisTemplate.opsForSet().add(szKey, szVals);
    }

    public String popOne(String szKey) {
        return (String) redisTemplate.opsForSet().pop(szKey);
    }

    public void deleteItem(String szKey, String szValue) {
        redisTemplate.opsForSet().remove(szKey, szValue);
    }

    public void deleteItem(String szKey, String... szValues) {
        redisTemplate.opsForSet().remove(szKey, szValues);
    }

    public long size(String szKey) {
        return redisTemplate.opsForSet().size(szKey);
    }
}
