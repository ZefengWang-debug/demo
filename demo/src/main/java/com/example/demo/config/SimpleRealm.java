package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.model.RolePermission;
import com.example.demo.repository.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.*;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SimpleRealm extends AuthorizingRealm {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public SimpleRealm(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PermissionRepository permissionRepository,
                       UserRoleRepository userRoleRepository,
                       RolePermissionRepository rolePermissionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRoleRepository = userRoleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userRepository.findByUsername(username).orElse(null);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user == null) return info;

        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        for (UserRole ur : userRoles) {
            roleRepository.findById(ur.getRoleId()).ifPresent(role -> info.addRole(role.getRoleCode()));
            List<RolePermission> rps = rolePermissionRepository.findByRoleId(ur.getRoleId());
            for (RolePermission rp : rps) {
                permissionRepository.findById(rp.getPermissionId())
                        .ifPresent(p -> info.addStringPermission(p.getPermissionCode()));
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UnknownAccountException("user not found"));
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}
