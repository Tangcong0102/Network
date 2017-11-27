package com.diao.course.service;

import com.diao.course.bean.User;

/**
 * 鐢ㄦ埛鐩稿叧鏈嶅姟鐨勫畾涔�
 *
 */
public interface UserService {
    /**
     * 鐧诲綍鎺ュ彛
     *
     * @param username 鐢ㄦ埛鍚�
     * @param password 瀵嗙爜
     * @return 瀵瑰簲鐨勭敤鎴峰璞�
     */
    User login(String username, String password);
}
