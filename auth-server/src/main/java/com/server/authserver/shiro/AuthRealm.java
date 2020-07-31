package com.server.authserver.shiro;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.server.authserver.common.Assert;
import com.server.authserver.entity.Permission;
import com.server.authserver.entity.Role;
import com.server.authserver.entity.User;
import com.server.authserver.service.RolePermissionService;
import com.server.authserver.service.UserRoleService;
import com.server.authserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RolePermissionService rolePermissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authz = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        //获取当前用户角色信息 并设置用户角色、权限
        List<Role> roles = userRoleService.getRoles(user.getId());
        Set<String> roleList = roles.stream().map(Role::getName).collect(Collectors.toSet());
        authz.setRoles(roleList);
        log.info("load authorization data, current user has {} role", roleList.size());

        //获取当前用户权限信息
        List<Long> ids = roles.stream().map(Role::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(ids)) {
            List<Permission> permissions = rolePermissionService.getPermissions(ids);
            Set<String> permissionList = permissions.stream().map(Permission::getPermission).collect(Collectors.toSet());
            authz.setStringPermissions(permissionList);
            log.info("load authorization data, current user has {} permission", permissions.size());
        }

        return authz;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, token.getUsername()));
        Assert.notNull(user, "账号不存在");
        ByteSource salt = ByteSource.Util.bytes(user.getName());
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, this.getName());
    }
}
