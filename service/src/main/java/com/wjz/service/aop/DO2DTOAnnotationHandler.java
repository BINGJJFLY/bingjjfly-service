package com.wjz.service.aop;

import java.lang.annotation.Annotation;

import com.wjz.service.annotation.DO2DTO;

public class DO2DTOAnnotationHandler extends AbstractAnnotationHandler {

	public DO2DTOAnnotationHandler(Class<? extends Annotation> annotationClass) {
		super(DO2DTO.class);
	}

	/**
	 * 核心处理逻辑
	 * <p>
	 * 根据注解中包含的DO.Class和DTO.class进行转化
	 */
	@Override
	void handle(Annotation anno) {
		if (!(anno instanceof DO2DTO)) {
			return;
		}
		DO2DTO do2dtoAnno = (DO2DTO) anno;
		Class<?> doClass = do2dtoAnno.DOType();
		Class<?> dtoClass = do2dtoAnno.DTOType();
		
	}

}
