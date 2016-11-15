package com.miss.foodie.service;

import com.miss.foodie.entity.User;

import java.util.List;

/**
 * @author:MissYou
 * @CreateTime:2016/11/15:9:45
 * @description:
 */
public interface UserService {

    List<User> getUserList(int offset, int limit);
}
