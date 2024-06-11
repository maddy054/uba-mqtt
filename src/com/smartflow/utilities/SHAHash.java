package com.smartflow.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class SHAHash {
	public static String getHash(String password) throws  OwnException {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			 StringBuilder hexString = new StringBuilder(2 * hash.length);
			    for (int i = 0; i < hash.length; i++) {
			        String hex = Integer.toHexString(0xff & hash[i]);
			        if(hex.length() == 1) {
			            hexString.append('0');
			        }
			        hexString.append(hex);
			    }
			    return hexString.toString();
		}catch(NoSuchAlgorithmException e) {
			throw new OwnException(e.getMessage());
		}
	}

}
