package com.hellomaker.web.service;

import com.hellomaker.web.model.dto.LoginUser;
import com.hellomaker.web.model.dto.TokenInfoDTO;
import com.hellomaker.web.model.vo.UserVO;
import java.net.URISyntaxException;

/**
 * @Description
 * @Author xzk
 * @Date 2023/8/11 16:09
 **/
public interface LoginService {

    /**
     *
     * @describe: 平台token登录，授权
     *
     * @param tokenInfoDTO
     * @return  {{@link LoginUser}}
     * @author  xianzhikun
     * @date    2023/8/11 16:11
     */
    LoginUser login(TokenInfoDTO tokenInfoDTO) throws URISyntaxException;

    /**
     *
     * @describe: 本地登录，授权
     *
     * @param userVO
     * @return  {{@link LoginUser}}
     * @author  xianzhikun
     * @date    2023/8/11 16:11
     */
    LoginUser login(UserVO userVO);

}
