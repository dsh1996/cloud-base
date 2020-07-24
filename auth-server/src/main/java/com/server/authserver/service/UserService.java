package com.server.authserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.authserver.entity.Role;
import com.server.authserver.entity.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface UserService extends IService<User> {

    ConcurrentMap<String, Object> USER_LIST = new ConcurrentHashMap<>();

    String login(User user);

    boolean register(User user);
}

