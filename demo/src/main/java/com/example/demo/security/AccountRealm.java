package com.example.demo.security;

import com.example.demo.model.Permission;
import com.example.demo.model.User;
import com.example.demo.model.UserPermission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.UserPermissionRepository;
import com.example.demo.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AccountRealm extends AuthorizingRealm {
    private final UserRepository userRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final PermissionRepository permissionRepository;

    public AccountRealm(UserRepository userRepository, UserPermissionRepository userPermissionRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UnknownAccountException("用户不存在"));
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userRepository.findByUsername(username).orElse(null);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user == null) return info;
        List<UserPermission> ups = userPermissionRepository.findByUserId(user.getId());
        for (UserPermission up : ups) {
            Permission p = permissionRepository.findById(up.getPermissionId()).orElse(null);
            if (p != null && p.getCode() != null) info.addStringPermission(p.getCode());
        }
        return info;
    }
}
