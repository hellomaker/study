package com.hellomaker.web.model.dto;

import lombok.Data;

import java.util.List;

/**
 *
 * @describe: 平台用户信息
 *
 * @author  xianzhikun
 * @date    2023/8/7 14:24
 */
@Data
public class UserDTO {

    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String account;
    /**
     * 名字
     */
    private String name;
    /**
     * 部门id
     */
    private Integer deptId;
    /**
     * 工号
     */
    private String workNum;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 描述
     */
    private String intro;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 组别id列表
     */
    private List<Integer> groupIdList;

    /**
     * 部门
     */
    private DeptDTO deptDTO;

}
