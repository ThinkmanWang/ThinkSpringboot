package com.liucaijin.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

 
public interface QuerySelect {
	
	@Select("select count(*) from notes")
	public int queryCount();
     
}
