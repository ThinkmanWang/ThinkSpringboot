package com.thinkman.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by wangx on 2017-03-26.
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void delete (String key) {
        redisTemplate.delete(key);
    }

    public <V> V query (String key) {
        return (V)redisTemplate.opsForValue().get(key);
    }

    public <T> Set<T> getKeys (String pattern) {
        return redisTemplate.keys(pattern);
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void insertSet(String szKey, String szVal) {
        redisTemplate.opsForSet().add(szKey, szVal);
    }

    public void insertSet(String szKey, String... szVals) {
        SetOperations<String, String> operations = redisTemplate.opsForSet();
        operations.add(szKey, szVals);
    }
}
