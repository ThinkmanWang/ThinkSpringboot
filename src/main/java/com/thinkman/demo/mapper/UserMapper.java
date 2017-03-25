package com.thinkman.demo.mapper;

import com.thinkman.demo.models.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wangx on 2017-02-27.
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM view_user WHERE user_name=#{user_name} and password=#{password} order by create_time desc limit 1")
    User login(@Param("user_name") String user_name, @Param("password") String password);
}
