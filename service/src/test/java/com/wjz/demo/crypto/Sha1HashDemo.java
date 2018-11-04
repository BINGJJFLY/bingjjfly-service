package com.wjz.demo.crypto;

import org.apache.shiro.crypto.hash.Sha1Hash;

public class Sha1HashDemo {

	public static void main(String[] args) {
		String sha1Hashed = new Sha1Hash("abc123456").toHex();
		System.out.println(sha1Hashed);
	}
}
