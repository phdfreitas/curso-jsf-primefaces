package br.com.bookstore.utils;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordEncrypt {

	public static String encrypt(String userPassword) {
		String finalPassword = "";
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] encryptedPass = messageDigest.digest(userPassword.getBytes());
			
			finalPassword = Base64.getEncoder().encodeToString(encryptedPass);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalPassword;
	}
	
}
