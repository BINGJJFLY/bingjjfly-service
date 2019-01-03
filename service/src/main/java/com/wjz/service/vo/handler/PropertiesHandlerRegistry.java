package com.wjz.service.vo.handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import com.wjz.service.annotation.PropertyHandler;
import com.wjz.service.annotation.ViewObject;
import com.wjz.service.cache.Cache;

/**
 * <b>属性处理器注册机</b>
 * 
 * @author iss002
 *
 */
public final class PropertiesHandlerRegistry {

	private final Cache<Type, PropertiesHandler> TYPE_HANDLER_MAP = new Cache<Type, PropertiesHandler>();

	/**
	 * 是否可以通过VFS方式，获得所有的handler(注解方式)，注解中说明处理类型
	 * 
	 * @see org.apache.ibatis.binding.MapperRegistry MapperRegistry
	 * @see org.apache.ibatis.type.TypeHandlerRegistry TypeHandlerRegistry
	 */
	public PropertiesHandlerRegistry() {
		registy(Long.class, new LongPropertyHandler());
		registy(Double.class, new DoublePropertyHandler());
		registy(Integer.class, new IntegerPropertyHandler());
		registy(String.class, new StringPropertyHandler());
		registy(Date.class, new DatePropertyHandler());
		registy(List.class, new ListPropertyHandler());
		registy(void.class, new UnexpectedPropertyHandler());
		registy(ViewObject.class, new BeanPropertyHandler());
		registy(Boolean.class, new BooleanPropertyHandler());
		registy(Map.class, new MapPropertyHandler());
		registy(BigDecimal.class, new BigDecimalPropertyHandler());
	}
	
	public void setPackageName(String packageName) {
		register(packageName);
	}

	public void registy(Type type, PropertiesHandler handler) {
		TYPE_HANDLER_MAP.put(type, handler);
	}

	public PropertiesHandler getHandler(Type type) {
		PropertiesHandler handler = TYPE_HANDLER_MAP.get(type);
		if (handler == null) {
			handler = tryAgain((Class<?>) type);
		}
		if (handler == null) {
			handler = unexpected();
		}
		return handler;
	}

	private PropertiesHandler tryAgain(Class<?> type) {
		ViewObject viewObject = AnnotationUtils.getAnnotation(type, ViewObject.class);
		if (viewObject != null) {
			return TYPE_HANDLER_MAP.get(ViewObject.class);
		}
		return null;
	}

	public PropertiesHandler unexpected() {
		return TYPE_HANDLER_MAP.get(void.class);
	}

	public void register(String packageName) {
		final ResolverUtil<PropertiesHandler> resolverUtil = new ResolverUtil<>();
		resolverUtil.findAnnotated(PropertyHandler.class, packageName);
		Set<Class<? extends PropertiesHandler>> handlerSet = resolverUtil.getClasses();
		if (!CollectionUtils.isEmpty(handlerSet)) {
			for (Class<? extends PropertiesHandler> handler : handlerSet) {
				if (!handler.isAnonymousClass() && !handler.isInterface()
						&& !Modifier.isAbstract(handler.getModifiers())) {
					register(handler);
				}
			}
		}
	}

	private void register(Class<? extends PropertiesHandler> handler) {
		PropertyHandler propertyHandler = AnnotationUtils.getAnnotation(handler, PropertyHandler.class);
		Class<?> propertyType = propertyHandler.value();
		if (!existing(propertyType)) {
			registy((Type) propertyType, (PropertiesHandler) newInstance(handler));
		}
	}

	private PropertiesHandler newInstance(Class<? extends PropertiesHandler> handler) {
		try {
			Constructor<? extends PropertiesHandler> constructor = handler.getConstructor();
			return constructor.newInstance();
		} catch (Exception e) {
			throw new RegisterException("an exception occurred during initialization", e);
		}
	}

	private boolean existing(Class<?> propertyType) {
		return TYPE_HANDLER_MAP.containsKey(propertyType);
	}

	public static class RegisterException extends RuntimeException {

		private static final long serialVersionUID = -6172007631235576280L;

		public RegisterException(String message, Throwable cause) {
			super(message, cause);
		}

	}

}
