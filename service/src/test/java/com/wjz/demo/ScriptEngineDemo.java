package com.wjz.demo;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 字符串执行算数运算、数值保留小数位及四舍五入方式
 * <p>
 * 但是中运算不能保留具体的小数位，不能四舍五入
 * </p>
 * 
 * @author iss002
 *
 */
public class ScriptEngineDemo {

	public static void main(String[] args) {
		ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
		String s = "((1+1*2)+2/5)*3+3+0.8";
		try {
			Double d = (Double) jse.eval(s);
			System.out.println(d);
			BigDecimal bd = new BigDecimal(19.0896725D);
			System.out.println(bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
