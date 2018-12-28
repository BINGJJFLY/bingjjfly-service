package com.wjz.service.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjz.service.exception.UnAssignableException;
import com.wjz.service.vo.handler.PropertyHandler;
import com.wjz.service.vo.handler.PropertyHandlerRegistry;
import com.wjz.service.vo.handler.Transformer;
import com.wjz.service.vo.manager.DefaultMetaObjectManager;
import com.wjz.service.vo.manager.MetaObjectManager;

public abstract class AbstractDO2VOFactory<D, V> implements DO2VOFactory<D, V> {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	protected final PropertyHandlerRegistry registy = new PropertyHandlerRegistry();

	protected MetaObjectManager<D> metaDomainManager;
	
	protected MetaObjectManager<V> metaViewManager;
	
	public void buildMetaDomainManager(D domain) {
		metaDomainManager = new DefaultMetaObjectManager<D>(domain);
	}
	
	public void buildMetaVeiwManager(V view) {
		metaViewManager = new DefaultMetaObjectManager<V>(view);
	}
	
	protected void doConvert() {
		Field[] fields = metaDomainManager.getDeclaredFields();
		if (!ArrayUtils.isEmpty(fields)) {
			for (Field field : fields) {
				if (!Modifier.isStatic(field.getModifiers())) {
					try {
						PropertyHandler handler = registy.getHandler(field.getType());
						if (handler != null) {
							handler.handle(field, metaDomainManager.getMetaObject(), metaViewManager.getMetaObject(), new Transformer() {
								@Override
								public Object transforming(Object target) throws UnAssignableException {
									return convert(null);
								}
							});
						}
					} catch (UnAssignableException e) {
						log.error("exception occurs when domain object [" + metaDomainManager.getMetaObject()
						+ "] converting to view object.", e);
						throw e;
					}
				}
			}
		}
	}
}
