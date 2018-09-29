package com.wjz.demo;

import java.util.Date;

import org.apache.shiro.io.DefaultSerializer;

/**
 * <b>{@link javax.persistence.Transient @Transient}注解 和transient关键字的区别：</b>
 * </p>
 * 注解的作用是属性或字段不是持久性的，如Mapper插入对象数据时注解修饰的属性数据库中不存在对应的字段
 * 拼装SQL时也不会将该属性考虑在内。序列化时注解修饰的属性在对象被序列化时会被序列化，反序列化时属性值也会存在。
 * <p/>
 * 关键字的作用是类实现了{@code Serilizable}接口，transient修饰的属性在对象被序列化时会被序列化，但是反序列化时属性值不会存在
 * 
 * @author iss002
 *
 */
public class TransientDemo {

	/**
	 * Person对象的Assert属性被transient修饰了序列化时会被序列化，反序列化后的Person对象中该属性的信息为Null
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DefaultSerializer<Person> defaultSerializer = new DefaultSerializer<>();

		Person person = new Person();
		person.setId(1);
		person.setName("iss002");
		person.setAsset(88.88);
		person.setAddTime(new Date());

		byte[] serialized = defaultSerializer.serialize(person);
		person = defaultSerializer.deserialize(serialized);
		System.out.println(person);
		System.out.println(person.getAsset());
		System.out.println(person.getName());
	}
}
