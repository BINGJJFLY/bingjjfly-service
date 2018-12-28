package com.wjz.service.vo;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

public class MetaObjectManager<D> {
	
	private MetaObject metaObject;
	
	public MetaObjectManager(D domain) {
		this.metaObject = SystemMetaObject.forObject(domain);
	}

	public MetaObject getMetaObject() {
		return metaObject;
	}

	public void setMetaObject(MetaObject metaObject) {
		this.metaObject = metaObject;
	}

	
	
}
