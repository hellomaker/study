package com.hellomaker.web.model.dto;

import lombok.Data;

import java.util.List;

/**
 *
 * @describe: 平台的组别信息
 *
 * @author  xianzhikun
 * @date    2023/8/7 14:24
 */
@Data
public class GroupDTO {

    /**
     *主键id
     */
    private Integer id;
    /**
     *名称
     */
    private String name;
    /**
     *状态 1：正常 2：禁用
     */
    private Integer status;
    /**
     *介绍
     */
    private String intro;
    /**
     *创建时间
     */
    private String createTime;
    /**
     *更新时间
     */
    private String updateTime;
    /**
     * 该组别下，用户id列表
     */
    private List<Integer> userIdList;
    /**
     * 该组别下，组长的id 列表
     */
    private List<Integer> managerUserIdList;

}
