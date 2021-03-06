package com.thinkman.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkman.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkman.springboot.cache.CacheUtil4Hash;
import com.thinkman.springboot.cache.ICacheKey;
import com.thinkman.springboot.service.IredisService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestRedisHsetCache {
   @Autowired
   IredisService iredisService;
   @Autowired
   RedisTemplate redisTemplate;
   
   @SuppressWarnings("unchecked")
   @RequestMapping("/test")
   @ResponseBody
   public String getSubResult(){

	   Map<byte[],byte[]> map=new  HashMap<byte[],byte[]>();
	   
	   map.put("123".getBytes(), "123".getBytes());
	   
	   HashOperations opsForHash = redisTemplate.opsForHash();
	   Object object = opsForHash.get("liucaijin".getBytes(), "123".getBytes());
	   redisTemplate.execute(new RedisCallback<Boolean>() {

		   @Override
		   public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
			   connection.hMSet("liucaijin".getBytes(), map);
			   return true;
		   }

	   });
	   return "ok";
   }
   
   @RequestMapping("/test2")
   @ResponseBody
   public String redisTest(){
	   List<User> users=new ArrayList<>();
	   users.add(new User(1,11));
	   users.add(new User(2,12));
	   users.add(new User(3,13));
	   users.add(new User(4,14));
	   CacheUtil4Hash.cache4Hash(users, "users", new ICacheKey<User>() {

		@Override
		public String getCacheKey(User t) {
			return t.getId()+"";
		}
	});
	   return "ok";
   }
   
   @RequestMapping("/test3")
   @ResponseBody
   public String redisTest2(){
	   List<User> users=new ArrayList<>();
	   List<String> keys=new ArrayList<>();
	   keys.add("1");
	   List<User> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis("users", keys);
	   if(get4HashFromRedis!=null)
	   System.out.println("大小为："+get4HashFromRedis.size());
	   return "ok";
   }
}
