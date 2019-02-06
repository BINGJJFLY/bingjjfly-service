package com.wjz.service.vo.handler;

import java.util.Collection;
import java.util.Iterator;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.CollectionUtils;

import com.wjz.service.anno.ViewProperty;
import com.wjz.service.vo.magician.DO2VOMagician;
import com.wjz.service.vo.magician.SingleDO2VOMagician;

/**
 * <b>集合属性处理器</b>
 * 
 * @author iss002
 *
 */
@SuppressWarnings("rawtypes")
public abstract class CollectionPropertyHandler<T> extends BasePropertiesHandler<T> {

	@SuppressWarnings("unchecked")
	@Override
	protected void doHandle(Class<T> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter) {
		if (isAssignableFrom(fieldType)) {
			Collection collection = (Collection) getValue(fieldName, domainMetaObject);
			if (!CollectionUtils.isEmpty(collection)) {
				Collection views = initViewCollection(collection.size());
				Iterator iterator = collection.iterator();
				final DO2VOMagician magician = new SingleDO2VOMagician();
				while (iterator.hasNext()) {
					Object view = converter.convert(magician, iterator.next());
					views.add(view);
				}
				setValue(fieldName, views, viewMetaObject);
			}
		}
	}

	protected abstract Collection initViewCollection(int initialCapacity);
}
