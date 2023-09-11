package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * UserMapper
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/9/11 20:26
 */
@Mapper
public interface UserMapper {
    /**
     * 根据openid查询用户
     * @author zyb
     * @date 2023/9/11 20:38
     * @param openId
     * @return User
     */
    @Select("select * from user where openid=#{openId}")
    User getByOpenId(String openId);

    /** 
     * 插入用户
     * @author zyb
     * @date 2023/9/11 20:39
     * @param user
     */
    void insert(User user);
}
