package com.hellomaker.web.model.vo;

import lombok.Data;

@Data
public class UserVO {

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

}
