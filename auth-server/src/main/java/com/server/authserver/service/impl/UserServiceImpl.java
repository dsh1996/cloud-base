package com.server.authserver.service.impl;

import com.server.authserver.entity.User;
import com.server.authserver.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.authserver.dao.UserMapper;
import org.springframework.util.Base64Utils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public String login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(), user.getPassword());
        subject.login(token);
        String authToken = Base64Utils.encodeToString(UUID.randomUUID().toString().getBytes());
        USER_LIST.put(authToken, subject.getPrincipal());
        return subject.isAuthenticated() ? authToken : null;
    }

    @Override
    public boolean register(User user) {
        Md5Hash hash = new Md5Hash(user.getPassword(), user.getName(), 1024);
        user.setPassword(hash.toHex());
        return this.save(user);
    }
}
