package com.hellomaker.web.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hellomaker.web.common.ViewConst;
import com.hellomaker.web.common.util.AESUtil;
import com.hellomaker.web.dao.UserDao;
import com.hellomaker.web.model.BeanTransfer;
import com.hellomaker.web.model.dto.LoginUser;
import com.hellomaker.web.model.dto.TokenInfoDTO;
import com.hellomaker.web.model.dto.UserDTO;
import com.hellomaker.web.model.po.UserPO;
import com.hellomaker.web.model.vo.UserVO;
import com.hellomaker.web.service.LoginService;
import com.hellomaker.web.service.openfeign.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service("loginService")
@Lazy
public class LoginServiceImpl implements LoginService {

    @Value("${platform.host}")
    private String host;
    @Value("${platform.port}")
    private Integer port;

//    @Resource
    UserDao userDao = new UserDao() {
        @Override
        public UserPO getUserByAccount(String account) {
            return null;
        }

        @Override
        public Integer insertOrUpdateUsers(List<UserPO> userPOS) {
            return null;
        }
    };

    @Resource
    PlatformService platformService;
    @Resource
    @Qualifier("myEncoder")
    PasswordEncoder passwordEncoder;


    @Override
    public LoginUser login(TokenInfoDTO tokenInfoDTO) throws URISyntaxException {
        UserPO userPO = userDao.getUserByAccount(tokenInfoDTO.getAccount());
        //返回认证授权后的loginuser对象
        UserDTO userDTO = null;
        if(Objects.isNull(userPO)){
            //数据库没有这个数据
            URI uri = new URI("http", null, host, port, null, null, null);
            String userResult = platformService.getUserInfoForMe(uri, tokenInfoDTO.getKey());
            userResult = AESUtil.decrypt(userResult);
            JSONObject userJsonObject = JSON.parseObject(userResult);
            userDTO = userJsonObject.getJSONObject(ViewConst.DATA).toJavaObject(UserDTO.class);
            //复制属性，插入用户
            UserPO userPOInsert = BeanTransfer.transfer(userDTO, UserPO.class);
            //新增时需要返回主键id
            if (userDao.insertOrUpdateUsers(Collections.singletonList(userPOInsert)) > 0) {

            }
        } else{
            userDTO = BeanTransfer.transfer(userPO, UserDTO.class);
        }
        LoginUser loginUser = new LoginUser();
        //设置用户对象
        loginUser.setPlatformUser(userDTO);
        //TODO 用户授权
        //示例
        loginUser.setPermissions(Arrays.asList("insert", "delete"));
        loginUser.setRoles(Arrays.asList("user", "manager"));
        loginUser.setIsAdmin(true);

        return loginUser;
    }

    @Override
    public LoginUser login(UserVO userVO) {
        //TODO 子系统登录业务处理
        UserPO userPO = userDao.getUserByAccount(userVO.getAccount());
        if (Objects.isNull(userPO)) {
            if (passwordEncoder.matches(userVO.getPassword(), userPO.getPassword())) {
                LoginUser loginUser = new LoginUser();
                loginUser.setPlatformUser(BeanTransfer.transfer(userPO, UserDTO.class));
                //TODO 授权

                return loginUser;
            }
        }
        return null;
    }
}
