package com.wjz.demo.crypto;

import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;

import com.wjz.service.crypto.DesCipherService;

public class AesCipherServiceDemo {

	private static byte[] encryptionCipherKey;
	private static byte[] decryptionCipherKey;

	public static void main(String[] args) {
		AesCipherService cipherService = new AesCipherService();
		byte[] key = cipherService.generateNewKey().getEncoded();
		encryptionCipherKey = key;
		decryptionCipherKey = key;

		ByteSource bs = cipherService.encrypt("Shiro加密机制".getBytes(), encryptionCipherKey);
		bs = cipherService.decrypt(bs.getBytes(), decryptionCipherKey);

		String s = new String(bs.getBytes());
		System.out.println(s);
		
		DesCipherService desCipherService = new DesCipherService();
		byte[] deskey = desCipherService.generateNewKey().getEncoded();
		encryptionCipherKey = deskey;
		decryptionCipherKey = deskey;
		
		ByteSource desbs = desCipherService.encrypt("Shiro加密机制".getBytes(), encryptionCipherKey);
		desbs = desCipherService.decrypt(desbs.getBytes(), decryptionCipherKey);

		s = new String(desbs.getBytes());
		System.out.println(s);
	}

}
