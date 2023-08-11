package com.hellomaker.web.service.impl;

import com.hellomaker.web.model.dto.LoginUser;
import com.hellomaker.web.service.SecurityContextService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author xzk
 * @Date 2023/4/14 9:02
 **/
@Service("securityContextService")
public class SecurityContextServiceImpl implements SecurityContextService {


    private Collection<? extends GrantedAuthority> getAuthorities() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Collection<? extends GrantedAuthority> authorities1 = authentication.getAuthorities();
        return authorities1;
    }

    @Override
    public LoginUser nowLoginUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        try {
            LoginUser loginUser = (LoginUser)authentication.getPrincipal();
            return loginUser;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Set<String> getAuthoritySet() {
        Collection<? extends GrantedAuthority> authorities = getAuthorities();
        Set<String> set = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            set.add(authority.getAuthority());
        }
        return set;
    }


}
