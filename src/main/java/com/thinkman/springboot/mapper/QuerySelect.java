package com.thinkman.springboot.mapper;

import org.apache.ibatis.annotations.Select;


public interface QuerySelect {
	
	@Select("select count(*) from notes")
	public int queryCount();
     
}
