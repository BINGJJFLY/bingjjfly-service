package com.wjz.service.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.core.annotation.AnnotationUtils;

import com.wjz.service.annotation.ViewObject;
import com.wjz.service.exception.UnAssignableException;
import com.wjz.service.vo.handler.PropertyHandler;
import com.wjz.service.vo.handler.Transformer;

public abstract class SingleDO2VOFactory<D, V> extends AbstractDO2VOFactory<D, V> {
	
	@Override
	@SuppressWarnings("unchecked")
	public V convert(D domain) {
		if (domain != null) {
			// ==> 创建Domain-MetaObjectManager
			buildMetaDomainManager(domain);
			// ==> Domain类上是否有注解
			ViewObject viewObjectAnno = AnnotationUtils.getAnnotation(metaDomainManager.getOriginalObjectType(), ViewObject.class);
			if (viewObjectAnno != null) {
				Class<V> viewType = (Class<V>) viewObjectAnno.type();
				if (viewType != null) {
					V view = (V) ReflectUtils.newInstance(viewType);
					// ==> 创建View-MetaObjectManager
					buildMetaVeiwManager(view);
					// ==> 开始转换
					doConvert();
					
					return view;
				}
			}
		}
		return null;
	}

}
