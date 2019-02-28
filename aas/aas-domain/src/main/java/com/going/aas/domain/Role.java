package com.going.aas.domain;


import com.going.framework.persistence.domain.AbstractEntity;

import lombok.Data;

/**
 * 角色
 */
@Data
public class Role extends AbstractEntity  {

	/** 角色名称 */
	private String name;

	/**
	 * 描述
	 */
	private String remark; 
	
	/**
	 * 序列号
	 */
	private int sequence;


}
