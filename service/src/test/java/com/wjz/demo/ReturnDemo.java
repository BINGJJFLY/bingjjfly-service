package com.wjz.demo;

public class ReturnDemo {

	public static void main(String[] args) {
		String s = hello();
		System.out.println(s);
		
		String test1 = "hi";
		String test2 = "中国";
		System.out.println(test1.length());
		System.out.println(test2.length());
		System.out.println(test1.getBytes().length);
		System.out.println(test2.getBytes().length);
		
	}
	
	private static String hello() {
		// check方法什么都没做，但是该方法还是返回"world"
		check();
		return "world";
	}
	
	// 仅仅是不执行return 后的内容
	@SuppressWarnings("unused")
	public static void check() {
		if (true) {
			return;
		}
		System.out.println();
	}
}
