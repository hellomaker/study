package com.hellomaker.web.service.openfeign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

/**
 *
 * @describe: 平台远程服务
 *
 * @author  xianzhikun
 * @date    2023/8/1 16:06
 * @version 1.0
 */
@FeignClient(name = "platformService", url = "10.30.44.36:8081")
public interface PlatformService {

    /**
     *
     * @describe: 验证平台的key信息
     *
     * @param uri 平台url前缀
     * @param token http 头的token信息
     * @return  {{@link String}}
     * @author  xianzhikun
     * @date    2023/8/4 17:52
     */
    @GetMapping("platform/getTokenInfoForMe")
    String check(URI uri, @RequestHeader("I-Token") String token);

    /**
     *
     * @describe: 获取当前key 的用户信息
     *
     * @param uri 平台url前缀
     * @param token http 头的token信息
     * @return  {{@link String}}
     * @author  xianzhikun
     * @date    2023/8/4 17:54
     */
    @GetMapping("platform/getUserInfoForMe")
    String getUserInfoForMe(URI uri, @RequestHeader("I-Token") String token);

    /**
     *
     * @describe: 获取平台的用户和部门信息
     *
     * @param uri 平台url前缀
     * @return  {{@link String}}
     * @author  xianzhikun
     * @date    2023/8/4 17:53
     */
    @GetMapping("platform/listAllBasicInformation")
    String listAllUserAndDept(URI uri);

    /**
     *
     * @describe: 子系统调用平台退出登录接口
     *
     * @param uri 平台url前缀
     * @param token http 头的token信息
     * @return  {{@link String}}
     * @author  xianzhikun
     * @date    2023/8/4 17:54
     */
    @PostMapping("platform/exit")
    String userLogout(URI uri, @RequestHeader("I-Token") String token);

}
