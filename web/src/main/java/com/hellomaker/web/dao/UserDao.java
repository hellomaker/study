package com.hellomaker.web.dao;

import com.hellomaker.web.model.po.UserPO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Lazy
public interface UserDao {

    //TODO 根据账号获取用户信息
    UserPO getUserByAccount(String account);

    //TODO 插入用户对象，并返回主键id
    Integer insertOrUpdateUsers(List<UserPO> userPOS);
}
