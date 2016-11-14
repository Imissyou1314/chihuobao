package com.miss.foodie.dao;

import com.miss.foodie.entity.User;
import com.sun.tools.javac.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author:MissYou
 * @CreateTime:2016/11/14:14:58
 * @description:
 */
public interface UserDao {

    /**
     * 根据手机号码查询用户
     * @param userPhone
     * @return
     */
    User queryByPhone(long userPhone);

    /**
     * 查询所有用户
     * @param offset
     * @param limit
     * @return
     */
    List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 添加用户
     * @param user
     */
    void addUser(@Param("user") User user);

}
