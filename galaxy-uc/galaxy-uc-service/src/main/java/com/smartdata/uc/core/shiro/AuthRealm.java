package com.smartdata.uc.core.shiro;

import com.smartdata.uc.core.enums.StatusEnum;
import com.smartdata.uc.system.domain.Role;
import com.smartdata.uc.system.domain.User;
import com.smartdata.uc.system.service.RoleService;
import com.smartdata.uc.system.service.UserService;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


/**
 * @author 小懒虫
 * @date 2018/8/14
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户Principal对象
        User user = (User) principal.getPrimaryPrincipal();
        // 获取角色和资源（JPA延迟加载超时，通过用户ID获取角色列表）
        Set<Role> roles = roleService.getUserRoleList(user.getId());

        if(roles != null){
            // 将角色和菜单封装到Subject主体对象
            user.setRoles(roles);

            // 赋予角色和资源授权
            roles.forEach(role -> {
                info.addRole(role.getName());
                role.getMenus().forEach(menu -> {
                    if(menu.getStatus().equals(StatusEnum.OK.getCode()) && !menu.getUrl().equals("#")){
                        info.addStringPermission(menu.getUrl());
                    }
                });
            });
        }
        return info;
    }

    /**
     * 认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 获取数据库中的用户名密码
        User user = userService.getByName(token.getUsername());
        // 对盐进行加密处理
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());
        /** 传入密码自动判断是否正确
         * 参数1：传入对象给Principal
         * 参数2：正确的用户密码
         * 参数3：加盐处理
         * 参数4：固定写法
         */
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        matcher.setHashIterations(ShiroUtil.hashIterations);
        super.setCredentialsMatcher(matcher);
    }
}