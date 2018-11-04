package com.wjz.demo.crypto;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class JceDemo {

	private static final String ALGORITHM_NAME = "AES";
	private static final String TRANSFORMATION_STRING_DELIMITER = "/";
	private static final String RANDOM_NUM_GENERATOR_ALGORITHM_NAME = "SHA1PRNG";
	private static final int DEFAULT_KEY_SIZE = 128;
	private static final int BITS_PER_BYTE = 8;
	private static byte[] encryptionCipherKey;
	private static byte[] decryptionCipherKey;
	private static String modeName = "CBC";
	private static String paddingSchemeName = "PKCS5Padding";
	private static String transformationString = ALGORITHM_NAME + TRANSFORMATION_STRING_DELIMITER + modeName
			+ TRANSFORMATION_STRING_DELIMITER + paddingSchemeName;

	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_NAME);
		keyGenerator.init(DEFAULT_KEY_SIZE);
		SecretKey key = keyGenerator.generateKey();
		encryptionCipherKey = key.getEncoded();
		decryptionCipherKey = key.getEncoded();

		int sizeInBytes = DEFAULT_KEY_SIZE / BITS_PER_BYTE;
		byte[] ivBytes = new byte[sizeInBytes];
		SecureRandom random = SecureRandom.getInstance(RANDOM_NUM_GENERATOR_ALGORITHM_NAME);
		random.nextBytes(ivBytes);

		Cipher cipher = Cipher.getInstance(transformationString);
		Key jdkKey = new SecretKeySpec(encryptionCipherKey, ALGORITHM_NAME);
		IvParameterSpec spec = new IvParameterSpec(ivBytes);
		cipher.init(Cipher.ENCRYPT_MODE, jdkKey, spec, random);

		byte[] encrypted = cipher.doFinal("Shiro安全加密".getBytes());

		byte[] output = new byte[ivBytes.length + encrypted.length];
		System.arraycopy(ivBytes, 0, output, 0, ivBytes.length);
		System.arraycopy(encrypted, 0, output, ivBytes.length, encrypted.length);

		byte[] input = de(output);

		System.out.println(new String(input));
	}

	public static byte[] de(byte[] output) throws Exception {
		int sizeInBytes = DEFAULT_KEY_SIZE / BITS_PER_BYTE;
		byte[] ivBytes = new byte[sizeInBytes];
		System.arraycopy(output, 0, ivBytes, 0, sizeInBytes);
		int encryptedSize = output.length - sizeInBytes;
		byte[] encrypted = new byte[encryptedSize];
		System.arraycopy(output, sizeInBytes, encrypted, 0, encryptedSize);

		Cipher cipher = Cipher.getInstance(transformationString);
		Key jdkKey = new SecretKeySpec(decryptionCipherKey, ALGORITHM_NAME);
		IvParameterSpec spec = new IvParameterSpec(ivBytes);
		cipher.init(Cipher.DECRYPT_MODE, jdkKey, spec);

		byte[] decrypted = cipher.doFinal(encrypted);

		return decrypted;
	}

}
