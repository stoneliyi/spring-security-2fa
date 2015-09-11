package com.jasper.users.auth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base32;

public class TOTPCodeGenerator {
	public static void main(String[] args) {
		TOTPAuthenticator totpAuthenticator = new TOTPAuthenticator();
		String secret = "ORXXI4DTMVRXEZLU";
		long timeIndex = System.currentTimeMillis() / 1000 / 30;
	    byte[] secretBytes = new Base32().decode(secret);
	    try {
	    		System.out.println("using key = " + new String(secretBytes) + " to generate TOTP code");
			long totpCode = totpAuthenticator.getCode(secretBytes, timeIndex);
			System.out.println("generated TOTP code to use: [" + totpCode + "]");
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
