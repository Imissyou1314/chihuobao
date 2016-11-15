package com.miss.foodie.service.impl;

import com.miss.foodie.dao.UserDao;
import com.miss.foodie.entity.User;
import com.miss.foodie.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:MissYou
 * @CreateTime:2016/11/15:9:46
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserList(int offset, int limit) {
        return userDao.queryAll(offset, limit);
    }
}
