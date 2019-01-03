package com.wjz.service.vo.extension;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.wjz.service.annotation.PropertyHandler;
import com.wjz.service.vo.handler.CollectionPropertyHandler;

/**
 * <b>Set类型属性处理器</b>
 * 
 * @author iss002
 *
 */
@PropertyHandler(Set.class)
public class SetPropertyHandler extends CollectionPropertyHandler<Set<Object>> {

	@Override
	protected Collection<Object> initViewCollection(int initialCapacity) {
		return new HashSet<>(initialCapacity);
	}

}
