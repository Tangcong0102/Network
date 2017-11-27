package com.diao.course.service.impl;

import com.diao.course.bean.User;
import com.diao.course.dao.UserDao;
import com.diao.course.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 鐢ㄦ埛鐩稿叧鏈嶅姟鐨勫疄鐜�
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 鐧诲綍鎺ュ彛
     * @param username 鐢ㄦ埛鍚�
     * @param password 瀵嗙爜
     * @return 瀵瑰簲鐨勭敤鎴峰璞�
     */
    @Override
    public User login(String username, String password) {
        List<User> users = userDao.login(username, password);
        if (users == null && users.size() != 1) {
            return null;
        }
        return users.get(0);
    }
}
