package com.marsmission.team38.userservice;

import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marsmission.team38.userdao.UserDao;

@Service
public class UserServcie {
	@Autowired
	private UserDao userDao;

	Cipher ecipher;
	Cipher dcipher;

	UserServcie(SecretKey key) throws Exception {
		ecipher = Cipher.getInstance("AES");
		dcipher = Cipher.getInstance("AES");
		ecipher.init(Cipher.ENCRYPT_MODE, key);
		dcipher.init(Cipher.DECRYPT_MODE, key);
	}

	public Map<String, Object> getuserDetails(String userName, String passwd) throws Exception {

		String k = "Team38Fit5136";

		// SecretKey key = KeyGenerator.getInstance("AES").generateKey();
		SecretKey key = new SecretKeySpec(k.getBytes(), "AES");
		UserServcie encrypter = new UserServcie(key);

		System.out.println("Original String: " + passwd);

		String encrypted = encrypter.encrypt(passwd);

		System.out.println("Encrypted String: " + encrypted);

//		String decrypted = encrypter.decrypt(encrypted);
//
//		System.out.println("Decrypted String: " + decrypted);

		Map<String, Object> result = userDao.getuserdetails(userName, encrypted);
		if (result.get("status").toString().equalsIgnoreCase("0")) {
			result.put("respmsg", "user doesnot exist with given credentials");
		}
		return result;
	}

	public String encrypt(String str) throws Exception {
		// Encode the string into bytes using utf-8
		byte[] utf8 = str.getBytes("UTF8");

		// Encrypt
		byte[] enc = ecipher.doFinal(utf8);

		// Encode bytes to base64 to get a string
		return Base64.getEncoder().encodeToString(enc);
	}

	public String decrypt(String str) throws Exception {
		// Decode base64 to get bytes
		byte[] dec = Base64.getDecoder().decode(str);

		byte[] utf8 = dcipher.doFinal(dec);

		// Decode using utf-8
		return new String(utf8, "UTF8");
	}

}
