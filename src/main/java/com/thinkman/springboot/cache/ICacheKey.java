package com.thinkman.springboot.cache;

 

public interface ICacheKey<T>{
	public String getCacheKey(T t);
}
