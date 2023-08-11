package com.hellomaker.web.model.po;

import lombok.Data;

@Data
public class UserPO {

    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否管理员
     */
    private Integer isAdmin;

}
