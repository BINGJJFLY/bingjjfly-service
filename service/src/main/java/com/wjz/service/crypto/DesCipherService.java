package com.wjz.service.crypto;

import java.security.Key;

import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;

/**
 * The DES algorithm key size defaults to 56 bits. IV length must be 8 bytes
 * long.
 * 
 * @author iss002
 * @see AesCipherService
 *
 */
public class DesCipherService extends DefaultBlockCipherService {

	private static final String ALGORITHM_NAME = "DES";
	private static final int BLOCK_SIZE = 64;

	public DesCipherService() {
		super(ALGORITHM_NAME);
		setInitializationVectorSize(BLOCK_SIZE);
	}

	@Override
	public Key generateNewKey() {
		return super.generateNewKey(56);
	}

}
