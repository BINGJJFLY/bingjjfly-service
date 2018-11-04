package com.wjz.demo.ini;

import java.util.Map;

import com.wjz.service.utils.IniUtils;
import com.wjz.service.utils.PropertiesUtils;

public class IniDemo {

	public static void main(String[] args) {
		Map<String, String> map = IniUtils.ini2Map(IniUtils.loadIni("classpath:shiro.ini"), "urls");
		System.out.println(map);
		
		map = PropertiesUtils.properties2Map(PropertiesUtils.loadProperties("classpath:shiro.properties"));
		System.out.println(map);
	}
}
