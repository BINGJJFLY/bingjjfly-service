package com.wjz.demo.crypto;

import java.security.MessageDigest;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5HashHexDemo {

	public static void main(String[] args) throws Exception {
		String hashed = new Md5Hash("abc123456").toHex();
		System.out.println(hashed); // 0659c7992e268962384eb17fafe88364
		
		hashed = new Md5Hash("abc123456").toString();
		System.out.println(hashed); // 0659c7992e268962384eb17fafe88364
		
		hashed = Hex.encodeToString("abc123456".getBytes());
		System.out.println(hashed); // 616263313233343536
		
		MessageDigest digest = MessageDigest.getInstance("MD5");
		// 第一次加密
		byte[] hash = digest.digest("abc123456".getBytes());
		hashed = Hex.encodeToString(hash);
		System.out.println(hashed); // 0659c7992e268962384eb17fafe88364
		
		Md5Hash md5 = Md5Hash.fromHexString(hashed);
		hashed = md5.toHex(); // 0659c7992e268962384eb17fafe88364
		System.out.println(hashed);
		
		// 两次加密之后再hash
		hashed = new Md5Hash("abc123456", null, 2).toHex();
		System.out.println(hashed); // 147ff12b4fe96c7e15fdd6001769967d
		
		// 第二次加密
		hash = digest.digest(hash);
		hashed = Hex.encodeToString(hash);
		System.out.println(hashed); // 147ff12b4fe96c7e15fdd6001769967d
		
		// 加点佐料
		hashed = new Md5Hash("abc123456", "wjz").toHex();
		System.out.println(hashed); // e6e53df81744bb60df6e793070149241
		
		MessageDigest digest2 = MessageDigest.getInstance("MD5");
		digest2.reset();
		digest2.update("wjz".getBytes());
		hash = digest2.digest("abc123456".getBytes());
		hashed = Hex.encodeToString(hash);
		System.out.println(hashed); // e6e53df81744bb60df6e793070149241
		
		// 加点佐料且加密两次
		hashed = new Md5Hash("abc123456", "wjz", 2).toHex();
		System.out.println(hashed); // 4f2395d9301ebad0203412c3f6852056
		
		hash = digest2.digest(hash);
		hashed = Hex.encodeToString(hash);
		System.out.println(hashed); // 4f2395d9301ebad0203412c3f6852056
		
		// hash和反hash
		char[] cs = Hex.encode("abc123456".getBytes());
		System.out.println(new String(Hex.decode(cs)));
	}
}
