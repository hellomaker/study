package com.hellomaker.web.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @describe: 记录本次认证授权的用户信息
 *
 * @author  xianzhikun
 * @date    2023/8/11 14:40
 */
@Data
public class LoginUser implements UserDetails {

    /**
     * 平台用户对象
     */
    UserDTO platformUser;

    /**
     * 权限列表
     */
    List<String> permissions;
    /**
     * 角色列表
     */
    List<String> roles;
    /**
     * 是否是超管 0否1是
     */
    Boolean isAdmin;

    //存储SpringSecurity所需要的权限信息的集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    /**
     *
     * @describe: 返回所有该用户的权限
     *
     * @param
     * @return  {{@link Collection<? extends  GrantedAuthority>}}
     * @author  xianzhikun
     * @date    2023/8/11 15:23
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null){
            return authorities;
        }
        Stream<String> stream = permissions.stream();
        if (isAdmin != null && isAdmin.equals(true)) {
            //超级管理员
            stream = Stream.concat(stream,
                    Arrays.asList("admin").stream()
            );
        }
        if (roles != null && roles.size() > 0) {
            stream = Stream.concat(stream,
                    roles.stream());
        }

        //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
        authorities = stream
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
        ;
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        //获取用户账号
        return Objects.isNull(platformUser) ? null : platformUser.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}