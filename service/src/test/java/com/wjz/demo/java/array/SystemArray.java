package com.wjz.demo.java.array;

import org.junit.Test;

/**
 * 数组复制
 * 
 * @author iss002
 *
 */
public class SystemArray {

	@Test
	public void docOfArrayCopy() {
		Object[] array1 = new Object[] { 1, 2, 3, 4, 5 };
		Object[] array2 = new Object[3];
		/**
		 * Object src 原数组
		 * int srcPos  原数组下标起始位置
		 * Object dest 目标数组
		 * int destPos 目标数组下标起始位置
		 * int length 复制长度（原数组下标终止位置）
		 */
		System.arraycopy(array1, 1, array2, 0, 2);
		for (int i = 0; i < array2.length; i++) {
			System.out.println(array2[i]);
		}
	}
	
	@Test
	public void oneStepMove() {
		// 去掉3后面的值前进一步：1, 2, 3, 4, 5 ==> 1, 2, 4, 5, 5
		Object[] array = new Object[] { 1, 2, 3, 4, 5 };
		int index = 2;
		System.arraycopy(array, index + 1, array, index, array.length - index - 1);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
