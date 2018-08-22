package com.wjz.service.vo;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import com.wjz.service.annotation.DO;
import com.wjz.service.exception.UnAssignableException;

/**
 * <b>类型转换工厂</b>
 * <p>
 * 入参DO对象或DO集合对象、转换目标（VO/DTO）Class对象
 * </p>
 * 
 * @author iss002
 *
 */
public class TypeCastFactory {

	/**
	 * 类型转换器
	 */
	// private TypeCaster typeCaster;

	public static <D, T> List<T> create(List<D> dos, Class<T> tClass) throws UnAssignableException {
		if (dos != null) {
			Annotation anno = findAnnotation(tClass, DO.class);
			if (anno == null) {
				throw new UnAssignableException("The DO annotation type is necessary.");
			}
			Class<D> dClass = (Class<D>) ((DO) anno).value();
			return cast(dos, dClass, tClass);
		}
		return null;
	}

	public static <D, T> T create(D d, Class<T> tClass) throws UnAssignableException {
		if (d != null) {
			List<D> dos = new ArrayList<D>(1);
			List<T> ts = create(dos, tClass);
			if (!CollectionUtils.isEmpty(ts)) {
				return ts.get(0);
			}
		}
		return null;
	}

	public static <D, T> List<T> cast(List<D> dos, Class<D> dClass, Class<T> tClass) throws UnAssignableException {
		if (dos != null) {
//			typeCaster.cast(dos, dClass, tClass);
		}
		return null;
	}

	public static Annotation findAnnotation(Class<?> clazz, Class<? extends Annotation> annoClass) {
		return AnnotationUtils.findAnnotation(clazz, annoClass);
	}

}
