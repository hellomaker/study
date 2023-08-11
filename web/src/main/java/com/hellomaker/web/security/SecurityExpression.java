package com.hellomaker.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @describe: 自定义权限判断器
 * 可以自定义权限表达式
 * @author  xianzhikun
 * @date    2023/8/11 15:21
 */
@Component("express")
public class SecurityExpression {

    private Collection<? extends GrantedAuthority> getAuthorities() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Collection<? extends GrantedAuthority> authorities1 = authentication.getAuthorities();
        return authorities1;
    }

    private Set<String> getAuthoritySet() {
        Collection<? extends GrantedAuthority> authorities = getAuthorities();
        Set<String> set = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            set.add(authority.getAuthority());
        }
        return set;
    }

    public boolean hasAnyAuthority(String... authorities) {
        Set<String> authoritySet = getAuthoritySet();
        for (String authority : authorities) {
            if (authoritySet.contains(authority)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAuthority(String... authorities) {
        Set<String> authoritySet = getAuthoritySet();
        for (String authority : authorities) {
            if (!authoritySet.contains(authority)) {
                return false;
            }
        }
        return true;
    }
}
