package com.wjz.service.aop;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

@SuppressWarnings("serial")
public class AnnotationPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return false;
	}

}
