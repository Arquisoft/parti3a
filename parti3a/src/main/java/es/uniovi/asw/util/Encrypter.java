package es.uniovi.asw.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {
 
	private static Encrypter instance;

	public static Encrypter getInstance() {
		if (instance == null)
			instance = new Encrypter();

		return instance;
	}

	public String makeSHA1Hash(String input) {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA1");		

			md.reset(); 
			byte[] buffer = input.getBytes();
			md.update(buffer); 

			byte[] digest = md.digest();
			String hexStr = ""; 

			for (int i = 0; i < digest.length; i++) { 
				hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1); 
			} 

			return hexStr; 
		}
		catch (NoSuchAlgorithmException e) {	
			
			e.printStackTrace();
			return input;
		} 
	} 
}
