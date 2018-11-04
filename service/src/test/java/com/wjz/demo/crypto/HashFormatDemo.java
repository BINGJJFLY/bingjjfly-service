package com.wjz.demo.crypto;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.format.HexFormat;

public class HashFormatDemo {

	public static void main(String[] args) {
		String hashed = new Md5Hash("abc123456").toHex();
		System.out.println(hashed);
		HexFormat format = new HexFormat();
		hashed = format.format(new Md5Hash("abc123456"));
		System.out.println(hashed);
	}
}
