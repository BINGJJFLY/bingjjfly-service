package com.wjz.service.vo.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <b>List类型属性处理器</b>
 * 
 * @author iss002
 *
 */
public class ListPropertyHandler extends CollectionPropertyHandler<List<Object>> {

	@Override
	protected Collection<Object> initViewCollection(int initialCapacity) {
		return new ArrayList<Object>(initialCapacity);
	}

}
