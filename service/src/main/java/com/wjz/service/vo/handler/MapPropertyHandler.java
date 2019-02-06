package com.wjz.service.vo.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.CollectionUtils;

import com.wjz.service.anno.ViewProperty;
import com.wjz.service.vo.magician.DO2VOMagician;

/**
 * <b>Map类型属性处理器</b>
 * 
 * @author iss002
 *
 */
public class MapPropertyHandler extends BasePropertiesHandler<Map<Object, Object>> {

	@SuppressWarnings("unchecked")
	@Override
	protected void doHandle(Class<Map<Object, Object>> fieldType, String fieldName, Object fieldValue,
			ViewProperty propertyAnno, MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter) {
		Map<Object, Object> map = (Map<Object, Object>) getValue(fieldName, domainMetaObject);
		if (!CollectionUtils.isEmpty(map)) {
			Map<Object, Object> views = initViewMap(map.size());
			final DO2VOMagician magician = getDO2VOMagician();
			for (Map.Entry<Object, Object> entry : map.entrySet()) {
				Object view = converter.convert(magician, entry.getValue());
				views.put(entry.getKey(), view);
			}
			setValue(fieldName, views, viewMetaObject);
		}
	}

	private Map<Object, Object> initViewMap(int initialCapacity) {
		return new HashMap<Object, Object>(initialCapacity);
	}

}
