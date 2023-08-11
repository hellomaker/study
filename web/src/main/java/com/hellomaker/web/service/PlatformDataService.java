package com.hellomaker.web.service;

import java.net.URISyntaxException;

/**
 *
 * @describe: 平台数据服务
 *
 * @author  xianzhikun
 * @date    2023/8/11 17:30
 */
public interface PlatformDataService {

    /**
     *
     * @describe: 同步平台数据
     *
     * @param
     * @return  {{@link Integer}}
     * @author  xianzhikun
     * @date    2023/8/11 17:31
     */
    Integer syncAllPlatformData() throws URISyntaxException;

}
