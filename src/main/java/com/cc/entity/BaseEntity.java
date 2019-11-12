package com.cc.entity;

import javax.persistence.Id;
/*
 * 应用tk.mybatis组件必须使用一个主键自增的ID
 */
public class BaseEntity {
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
