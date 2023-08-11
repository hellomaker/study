package com.hellomaker.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hellomaker.web.common.ViewConst;
import com.hellomaker.web.common.util.AESUtil;
import com.hellomaker.web.model.dto.DeptDTO;
import com.hellomaker.web.model.dto.GroupDTO;
import com.hellomaker.web.model.dto.UserDTO;
import com.hellomaker.web.service.PlatformDataService;
import com.hellomaker.web.service.openfeign.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Service("platformDataService")
@Slf4j
public class PlatformDataServiceImpl implements PlatformDataService {

    @Value("${platform.host}")
    private String host;
    @Value("${platform.port}")
    private Integer port;

    @Resource
    PlatformService platformService;

    @Override
    @Transactional
    public Integer syncAllPlatformData() throws URISyntaxException {
        URI uri = new URI("http", null, host, port, null, null, null);
        String result = platformService.listAllUserAndDept(uri);
        String resultParsed = AESUtil.decrypt(result);
        JSONObject jsonObject = JSON.parseObject(resultParsed);
        Integer code = jsonObject.getInteger(ViewConst.CODE);
        String message = jsonObject.getString(ViewConst.MESSAGE);
        if (code.equals(ViewConst.OK_STATUS)) {
            JSONObject data = jsonObject.getJSONObject(ViewConst.DATA);
            List<DeptDTO> depts = data.getJSONArray(ViewConst.DEPTS).toJavaList(DeptDTO.class);
            List<UserDTO> users = data.getJSONArray(ViewConst.USERS).toJavaList(UserDTO.class);
            List<GroupDTO> groupS = data.getJSONArray(ViewConst.GROUPS).toJavaList(GroupDTO.class);
            //TODO 保存用户部门组别

            return 0;
        } else {
            log.error("saveUserAndDepts", jsonObject.toJSONString());
            return 0;
        }
    }
}
