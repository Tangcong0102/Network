package com.diao.course.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.diao.course.bean.User;

import java.util.List;

/**
 * 和用户相关的DAO操作
 *
 */
@Repository
public interface UserDao {
    @Select("SELECT id, userName, nickName, userType,password " +
            "FROM person " +
            "WHERE userName = #{username} AND password = #{password}")
    List<User> login(@Param("username") String username, @Param("password") String password);
}