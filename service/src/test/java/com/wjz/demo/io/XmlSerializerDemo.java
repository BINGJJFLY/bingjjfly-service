package com.wjz.demo.io;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.shiro.io.XmlSerializer;

import com.wjz.demo.Person;

public class XmlSerializerDemo {

	public static void main(String[] args) {
		XmlSerializer xmlSerializer = new XmlSerializer();

		Person person = new Person();
		person.setId(1);
		person.setName("iss002");
		person.setAsset(88.88);
		person.setAddTime(new Date());

		byte[] serialized = xmlSerializer.serialize(person);
		person = (Person) xmlSerializer.deserialize(serialized);
		System.out.println(person);
		System.out.println(new String(serialized));

		XMLEncoder xmlEncoder;
		try {
			xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("D:\\jquery-tmpl\\Test.xml")));
			xmlEncoder.writeObject(person);
			xmlEncoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/**
		 * <?xml version="1.0" encoding="UTF-8"?>
		 * <java version="1.8.0_144" class="java.beans.XMLDecoder">
		 * 		<object class="com.wjz.demo.Person">
		 * 			<void property="addTime">
		 * 				<object class="java.util.Date">
		 * 					<long>1538193168812</long>
		 * 				</object>
		 * 			</void>
		 * 			<void property="asset">
		 * 				<double>88.88</double>
		 * 			</void>
		 * 			<void property="id">
		 * 				<int>1</int>
		 * 			</void>
		 * 			<void property="name">
		 * 				<string>iss002</string>
		 * 			</void>
		 * 		</object>
		 * </java>
		 */

		XMLDecoder xmlDecoder;
		try {
			xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("D:\\jquery-tmpl\\Test.xml")));
			Object result = xmlDecoder.readObject();
			System.out.println(result);
			xmlDecoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
