package com.wjz.demo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

import com.wjz.service.annotation.ViewObject;
import com.wjz.service.annotation.ViewProperty;
import com.wjz.service.pojo.Person;

public class SpringAliasForAnnoDemo {
	
	@Test
	public void alias() {
		Person person = new Person();
		ViewObject viewObjectAnno = AnnotationUtils.getAnnotation(person.getClass(), ViewObject.class);
		Assert.assertEquals(Integer.class, viewObjectAnno.value());
		Assert.assertEquals(Integer.class, viewObjectAnno.type());
		
		try {
			ViewProperty viewPropertyAnno = AnnotationUtils.getAnnotation(person.getClass().getDeclaredField("addTime"), ViewProperty.class);
			Assert.assertEquals(String.class, viewPropertyAnno.value());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
