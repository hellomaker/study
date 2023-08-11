package com.hellomaker.web.service;

import com.hellomaker.web.model.dto.LoginUser;
import java.util.Set;

/**
 *
 * @describe: springsecurity 上下文服务
 *
 * @author  xianzhikun
 * @date    2023/8/11 16:27
 */
public interface SecurityContextService {

    Set<String> getAuthoritySet();

    LoginUser nowLoginUser();

}
