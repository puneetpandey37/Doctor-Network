package com.docnet.common.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Puneet Pandey
 *
 */
public class DocNetPasswordEncoder {

	public String getEncryptedPassword(String password) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encryptedPass = bCryptPasswordEncoder.encode(password);
		return encryptedPass;
	}
}
