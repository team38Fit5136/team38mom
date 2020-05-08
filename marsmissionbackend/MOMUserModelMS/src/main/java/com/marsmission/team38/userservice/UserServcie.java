package com.marsmission.team38.userservice;

import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import com.marsmission.team38.userdao.UserDao;

@Service
public class UserServcie {
	@Autowired
	private UserDao userDao;

	@Value("${user.mandatory.username}")
	boolean usernameMandatory;

	@Value("${user.mandatory.email}")
	boolean emailMandatory;

	@Value("${user.mandatory.password}")
	boolean passwdMandatory;

	// Method for creating user
	public Map<String, Serializable> addUser(Map<String, ?> props) {

		Map<String, Serializable> result = new HashMap<>();
		System.out.println("+++++++" + props);
		if (!(props.containsKey("userName"))) {

			result.put("status", "failed");
			result.put("responseMsg", "error in creating user no user name given");
			return result;
		}

		String userName = (String) (props.containsKey("userName") ? props.get("userName") : null);
		String email = (String) (props.containsKey("email") ? props.get("email") : null);
		String passwd = (String) (props.containsKey("passwd") ? props.get("passwd") : null);
		long userID = 0;
		// checks for username = first name and last name
		if (usernameMandatory) {
			if (userName == null && !(userName.toString().equalsIgnoreCase(""))) {
				result.put("respmsg", "userName is mandatory that is first name and last name and cannot be empty");
				result.put("status", "failed");
				return result;
			}
		}

		// checks for email
		if (emailMandatory) {
			if (email == null && !(email.toString().equalsIgnoreCase(""))) {
				result.put("respmsg", "email is mandatory");
				result.put("status", "failed");
				return result;
			}

			if (passwdMandatory) {
				if (passwd == null && !(passwd.toString().equalsIgnoreCase(""))) {
					result.put("respmsg", "password is mandatory");
					result.put("status", "failed");
					return result;
				}
			}
			String encrypted = UserServcie.encrypt(passwd);
			try {
				userID = (!userName.equalsIgnoreCase("") ? (userDao.addUserDAO(userName, email, encrypted)) : 0);

				if (userID != 0) {
					result.put("userID ", userID);
					result.put("status", "success");
					result.put("responseMsg", "successfully inserted");
				} else {
					result.put("status", "failed");
					result.put("responseMsg", "Enter user name is not correct ");
				}
			} catch (DuplicateKeyException e) {
				result.put("status", "failed");
				result.put("responseMsg", "email is already present");
			} catch (UncategorizedSQLException e) {
				result.put("status", "failed");
				result.put("responseMsg", "Enter data is not correct or user is already present");
			}
			return result;
		}
		return result;

	}

	// Method for getting user details using email as user-name and password
	public Map<String, Serializable> getUserDetails(String userID, String passwd) {
		String encrypted = UserServcie.encrypt(passwd);
		Map<String, Serializable> result = userDao.getUserdetails(userID, encrypted);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			result.put("respmsg", "user doesnot exist with given credentials");
		}
		return result;
	}

	// Method for encrypting base64
	public static String encrypt(String str) {
		String BasicBase64format = Base64.getEncoder().encodeToString(str.getBytes());
		return BasicBase64format;
	}

	// Method for decrypting base64
	public static String decrypt(String str) {
		byte[] actualByte = Base64.getDecoder().decode(str);
		String actualString = new String(actualByte);
		return actualString;
	}
	
	public Map<String, Serializable> updateUserDetails(String userID, String passwd) {
		String encrypted = UserServcie.encrypt(passwd);
		Map<String, Serializable> result = userDao.getUserdetails(userID, encrypted);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			result.put("respmsg", "user doesnot exist with given credentials");
			return result;
		}
		
		else {
			Map<String, Serializable> result1 = userDao.getUserdetails(userID, encrypted);
		}
		return result;
		
	}
}
