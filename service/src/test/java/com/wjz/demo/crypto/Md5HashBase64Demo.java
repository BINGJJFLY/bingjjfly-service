package com.wjz.demo.crypto;

import java.security.MessageDigest;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5HashBase64Demo {
	
	private static final byte[] base64Alphabet = new byte[255];
	
	static {
        for (int i = 0; i < 255; i++) {
            base64Alphabet[i] = (byte) -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i >= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }
        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }

        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;
    }

	public static void main(String[] args) throws Exception {
		String base64 = new Md5Hash("abc123456").toBase64();
		System.out.println(base64); // BlnHmS4miWI4TrF/r+iDZA==
		
		base64 = Base64.encodeToString("abc123456".getBytes());
		System.out.println(base64); // YWJjMTIzNDU2
		
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] base64b = digest.digest("abc123456".getBytes());
		base64 = Base64.encodeToString(base64b);
		System.out.println(base64); // BlnHmS4miWI4TrF/r+iDZA==
		
		Md5Hash md5 = Md5Hash.fromBase64String(base64);
		base64 = md5.toBase64();
		System.out.println(base64); // BlnHmS4miWI4TrF/r+iDZA==
		
		int i = 'Z';
		System.out.println(i);
		i = '+';
		System.out.println(i);
		i = '0';
		System.out.println(i);
		i = '1';
		System.out.println(i);
		double z = 'z';
		System.out.println(z);
		long A = 'A';
		System.out.println(A);
		float f = '-';
		System.out.println(f);
		byte b = 'b';
		System.out.println(b);
		
		byte[] bs = "=/100*)".getBytes();
		System.out.println(Base64.isBase64(bs));
		
		for(int j = 0; j < 256; j++) {
			System.out.print(base64Alphabet[i]);
		}
	}
}
