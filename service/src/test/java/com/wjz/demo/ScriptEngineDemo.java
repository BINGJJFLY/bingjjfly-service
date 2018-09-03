package com.wjz.demo;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 字符串执行算数运算、数值保留小数位及四舍五入方式
 * 
 * @author iss002
 *
 */
public class ScriptEngineDemo {

	public static void main(String[] args) {
		ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
		String s = "1.5603+3.8934*3.67/5.89";
		try {
			Double d = (Double) jse.eval(s);
			System.out.println(d);
			final BigDecimal bd = new BigDecimal(d);
			System.out.println(bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
