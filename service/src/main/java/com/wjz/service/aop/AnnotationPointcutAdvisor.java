package com.wjz.service.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.wjz.service.annotation.DO2DTO;

@SuppressWarnings("serial")
public class AnnotationPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

	private static final Class<? extends Annotation>[] ANNOTATION_CLASSES = new Class[] { DO2DTO.class };

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return false;
	}

}
