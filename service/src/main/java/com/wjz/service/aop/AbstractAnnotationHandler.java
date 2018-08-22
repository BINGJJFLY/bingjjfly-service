package com.wjz.service.aop;

import java.lang.annotation.Annotation;

public abstract class AbstractAnnotationHandler extends AnnotationHandler {

	public AbstractAnnotationHandler(Class<? extends Annotation> annotationClass) {
		super(annotationClass);
	}
	
	abstract void handle(Annotation anno);
	
}
