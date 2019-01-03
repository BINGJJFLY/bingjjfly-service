package com.wjz.service.vo.handler;

import org.apache.ibatis.type.TypeReference;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;

/**
 * <b>属性对称加密处理器</b>
 * 
 * @author iss002
 *
 * @param <T>
 */
public abstract class CryptoPropertiesHandler<T> extends TypeReference<T> implements PropertiesHandler {
	
	private static final AesCipherService cipherService = new AesCipherService();
	
	protected boolean crypto;
	
	protected String encrypt(byte[] plaintext) {
		ByteSource byteSource = cipherService.encrypt(plaintext, CIPHER_KEY);
		return byteSource.toBase64();
	}
	
	public static String decrypt(String ciphertext) {
		byte[] bytes = cipherService.decrypt(Base64.decode(ciphertext), PropertiesHandler.CIPHER_KEY).getBytes();
		return new String(bytes);
	}

	public void setCrypto(boolean crypto) {
		this.crypto = crypto;
	}
	
}
