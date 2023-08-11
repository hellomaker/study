package com.hellomaker.web.model.dto;


import lombok.Data;

/**
 *
 * @describe: 平台的部门信息
 *
 * @author  xianzhikun
 * @date    2023/8/7 14:25
 */
@Data
public class DeptDTO {

    /**
     *主键id
     */
    private Integer id;
    /**
     *名称
     */
    private String name;
    /**
     *简介
     */
    private String intro;
    /**
     *父部门id
     */
    private Integer pid;
    /**
     *状态 1：正常 2：禁用
     */
    private Integer status;
    /**
     *创建时间
     */
    private String createTime;
    /**
     *更新时间
     */
    private String updateTime;


}
