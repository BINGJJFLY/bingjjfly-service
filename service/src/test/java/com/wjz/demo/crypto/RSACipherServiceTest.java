package com.wjz.demo.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

import org.junit.Test;

import com.wjz.service.crypto.RSACipherService;

public class RSACipherServiceTest {
	
	@Test
	public void test() {
		RSACipherService rsaCipherService = new RSACipherService();
		
		Map<String, Object> map = RSACipherService.init();
		String privateKeyStr = rsaCipherService.getPrivateKeyStr(map);
		String publicKeyStr = rsaCipherService.getPublicKeyStr(map);
		PrivateKey privateKey = rsaCipherService.buildPrivateKey(privateKeyStr);
		PublicKey publicKey = rsaCipherService.buildPublicKey(publicKeyStr);
		System.out.println("私钥： \n" + privateKeyStr);
		System.out.println("公钥: \n" + publicKeyStr);

		System.out.println("公钥加密--------私钥解密");
		// 需要加密的信息
		String word = "127.0.0.1:8888/rsa/person/list";
		// 公钥加密
		byte[] encryptWord = rsaCipherService.encryptByKey(word.getBytes(), publicKey);
		System.out.println("公钥加密后的信息: \n" + new String(encryptWord));
		// 公钥进行数字签名
		String sign = rsaCipherService.sign(word.getBytes(), privateKey);
		System.out.println("签名: \n" + sign);
		
		String decryptWord = rsaCipherService.decryptByKey(encryptWord, privateKey);
		System.out.println("加密前: " + word + "\n" + "解密后: " + decryptWord);
		boolean flag = rsaCipherService.verify(decryptWord.getBytes(), publicKey, sign);
		System.out.println("验证签名状态:" + flag);
		
//		System.out.println("私钥加密--------公钥解密");
//		String hello = "大家好";
//		byte[] encryptHello = rsaCipherService.encryptByKey(hello.getBytes(), privateKey);
//		String decryptHello = rsaCipherService.decryptByKey(encryptHello, publicKey);
//		System.out.println("加密前: " + decryptHello + "\n" + "解密后: " + decryptHello);
	}

}
