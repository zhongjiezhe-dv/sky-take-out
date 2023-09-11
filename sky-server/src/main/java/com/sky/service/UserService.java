package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * UserService
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/9/11 20:16
 */
public interface UserService {
    /**
     * 微信登录
     * @author zyb
     * @date 2023/9/11 20:23
     * @param userLoginDTO
     * @return User
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
