package com.hellomaker.web.model.dto;


import lombok.Data;

/**
 * 平台校验的token，返回的用户信息
 * 
 * @author xianzhikun
 * @email 
 * @date 2023-07-04 18:20:17
 */
@Data
public class TokenInfoDTO {

	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * key
	 */
	private String key;
	/**
	 * 平台的用户id
	 */
	private Integer userId;
	/**
	 * 平台的用户账号
	 */
	private String account;
	/**
	 * 刷新时间
	 */
	private String refreshTime;
	/**
	 * 有效时间
	 */
	private String effectiveTime;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;

}
