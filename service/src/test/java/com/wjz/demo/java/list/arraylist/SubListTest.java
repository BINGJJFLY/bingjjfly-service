package com.wjz.demo.java.list.arraylist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * ArrayList的子集合
 * <p>
 * 通过定制下标达到子集合的效果
 * </p>
 * <p>
 * 新增删除操作会影响原数组
 * </p>
 * 
 * @author iss002
 *
 */
public class SubListTest {
	
	@Test
	public void subList() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
//		new SubList(this, 0, fromIndex, toIndex);
//		SubList(AbstractList<E> parent,
//                int offset, int fromIndex, int toIndex) {
//            this.parent = parent;
//            this.parentOffset = fromIndex;
//            this.offset = offset + fromIndex;
//            this.size = toIndex - fromIndex;
//            this.modCount = ArrayList.this.modCount;
//        }
		List<Integer> sublist = list.subList(1, 3);
		sublist.forEach(val->System.out.println(val));
		// ArrayList.this.elementData(offset + index);
		assertEquals((Integer) 2, sublist.get(0));
		
		sublist.add(100);
		
		list.forEach(val->System.out.println(val));
		
	}

}
