package com.wjz.service.crypto;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 非对称加密算法
 * 
 * @author iss002
 *
 */
public class RSACipherService {

	private static final Logger logger = LoggerFactory.getLogger(RSACipherService.class);
	/**
	 * 定义加密方式
	 */
	private final static String KEY_RSA = "RSA";
	/**
	 * 定义签名算法
	 */
	private final static String KEY_RSA_SIGNATURE = "MD5withRSA";
	/**
	 * 定义公钥算法
	 */
	private final static String KEY_RSA_PUBLICKEY = "RSAPublicKey";
	/**
	 * 定义私钥算法
	 */
	private final static String KEY_RSA_PRIVATEKEY = "RSAPrivateKey";

	private static Encoder encoder = null;
	private static Decoder decoder = null;
	
	public static Map<String, Object> init() {
		encoder = Base64.getEncoder();
		decoder = Base64.getDecoder();
		Map<String, Object> map = null;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_RSA);
			keyPairGenerator.initialize(1024);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			map = new HashMap<>();
			map.put(KEY_RSA_PRIVATEKEY, privateKey);
			map.put(KEY_RSA_PUBLICKEY, publicKey);
		} catch (Exception e) {
			logger.error("初始化私、公钥时出现异常");
		}
		return map;
	}

	public KeyFactory createKeyFactory() throws NoSuchAlgorithmException {
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
		return keyFactory;
	}

	/**
	 * 创建签名对象
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Signature createSignature() throws NoSuchAlgorithmException {
		Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
		return signature;
	}

	/**
	 * 组建公钥对象
	 * 
	 * @param publicKeyStr
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public PublicKey buildPublicKey(String publicKeyStr) {
		PublicKey publicKey = null;
		try {
			byte[] publicKeyBytes = BASE64decode(publicKeyStr);
			// 创建编码说明对象
			EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
			publicKey = createKeyFactory().generatePublic(encodedKeySpec);
		} catch (Exception e) {
			logger.error("创建公钥失败");
		}
		return publicKey;
	}

	/**
	 * 组建私钥对象
	 * 
	 * @param privateKeyStr
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public PrivateKey buildPrivateKey(String privateKeyStr) {
		PrivateKey privateKey = null;
		try {
			byte[] privateKeyBytes = BASE64decode(privateKeyStr);
			EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			privateKey = createKeyFactory().generatePrivate(encodedKeySpec);
		} catch (Exception e) {
			logger.error("创建私钥失败");
		}
		return privateKey;
	}

	/**
	 * RSA使用私钥对象加密数据生成数字签名
	 * 
	 * @param encryptData
	 *            要加密的数据
	 * @param privateKey
	 */
	public String sign(byte[] encryptData, PrivateKey privateKey) {
		String signData = null;
		try {
			Signature signature = createSignature();
			signature.initSign(privateKey);
			signature.update(encryptData);
			signData = BASE64ecode(signature.sign());
		} catch (Exception e) {
			logger.error("使用私钥对象加密数据生成数字签名时出现异常");
		}
		return signData;
	}

	/**
	 * RSA使用公钥对象对返回数据和数字签名进行校验
	 * 
	 * @param retunData
	 *            返回的数据
	 * @param publicKey
	 * @param signData
	 *            数字签名
	 * @return
	 */
	public boolean verify(byte[] retunData, PublicKey publicKey, String signData) {
		boolean flag = false;
		try {
			Signature signature = createSignature();
			signature.initVerify(publicKey);
			signature.update(retunData);
			// 返回数据和数字签名进行校验
			flag = signature.verify(BASE64decode(signData));
		} catch (Exception e) {
			logger.error("使用公钥对象对返回数据和数字签名进行校验时出现问题");
		}
		return flag;
	}

	/**
	 * 根据私、公钥加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @param privateKey
	 * @return
	 */
	public byte[] encryptByKey(byte[] data, Key key) {
		byte[] result = null;
		try {
			KeyFactory keyFactory = createKeyFactory();
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, key);
			result = cipher.doFinal(data);
			result = BASE64ecode(result).getBytes();
		} catch (Exception e) {
			logger.error("根据私、公钥加密数据时出现异常");
		}
		return result;
	}

	/**
	 * 根据私、公钥解密数据
	 * 
	 * @param data
	 *            加密数据
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public String decryptByKey(byte[] data, Key key) {
		String result = null;
		try {
			data = BASE64decode(new String(data));
			KeyFactory keyFactory = createKeyFactory();
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = new String(cipher.doFinal(data));
		} catch (Exception e) {
			logger.error("根据私、公钥解密数据时出现异常", e);
		}
		return result;
	}

	/**
	 * 获得私钥串
	 * 
	 * @param map
	 * @return
	 */
	public String getPrivateKeyStr(Map<String, Object> map) {
		Key privateKey = (Key) map.get(KEY_RSA_PRIVATEKEY);
		return BASE64ecode(privateKey.getEncoded());
	}

	/**
	 * 获得公钥串
	 * 
	 * @param map
	 * @return
	 */
	public String getPublicKeyStr(Map<String, Object> map) {
		Key publicKey = (Key) map.get(KEY_RSA_PUBLICKEY);
		return BASE64ecode(publicKey.getEncoded());
	}

	/**
	 * base64加密
	 * 
	 * @param sign
	 * @return
	 */
	private String BASE64ecode(byte[] sign) {
		return encoder.encodeToString(sign);
	}

	/**
	 * base64解码
	 * 
	 * @param publicKeyStr
	 * @return
	 * @throws IOException
	 */
	private byte[] BASE64decode(String data) throws IOException {
		return decoder.decode(data);
	}

}