package com.marsmission.team38.userService;

import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import com.marsmission.team38.userDAO.UserDAO;

@Service
public class UserServcie {

	@Autowired
	private UserDAO userDao;

	@Value("${user.mandatory.username}")
	boolean usernameMandatory;

	@Value("${user.mandatory.email}")
	boolean emailMandatory;

	@Value("${user.mandatory.password}")
	boolean passwdMandatory;

	@Value("${user.mandatory.DOB}")
	boolean dobMandatory;

	@Value("${user.mandatory.address}")
	boolean addressMandatory;

	@Value("${user.mandatory.nationality}")
	boolean nationalityMandatory;

	@Value("${user.mandatory.gender}")
	boolean genderMandatory;

	// Method for creating user
	public Map<String, Serializable> addUser(Map<String, ?> props) {

		Map<String, Serializable> result = new HashMap<>();
		System.out.println("+++++++" + props);

		String userName = (String) (props.containsKey("userName") ? props.get("userName") : null);
		String email = (String) (props.containsKey("email") ? props.get("email") : null);
		String passwd = (String) (props.containsKey("passwd") ? props.get("passwd") : null);
		String dob = (String) (props.containsKey("dob") ? props.get("dob") : null);
		String address = (String) (props.containsKey("address") ? props.get("address") : null);
		String nationality = (String) (props.containsKey("nationality") ? props.get("nationality") : null);
		String gender = (String) (props.containsKey("gender") ? props.get("gender") : null);

		long userID = 0;

		// checks for username = first name and last name
		if (usernameMandatory) {
			if (userName == null && !(userName.toString().equalsIgnoreCase(""))) {
				result.put("responseMsg", "userName is mandatory that is first name and last name and cannot be empty");
				result.put("status", "failed");
				return result;
			}
		}

		// checks for email
		if (emailMandatory) {
			if (email == null && !(email.toString().equalsIgnoreCase(""))) {
				result.put("responseMsg", "email is mandatory");
				result.put("status", "failed");
				return result;
			}

			// check for password field if mandatory
			if (passwdMandatory) {
				if (passwd == null && !(passwd.toString().equalsIgnoreCase(""))) {
					result.put("responseMsg", "password is mandatory");
					result.put("status", "failed");
					return result;
				}
			}

			// Checks for DOB mandatory
			if (dobMandatory) {
				if (dob == null && !(dob.toString().equalsIgnoreCase(""))) {
					result.put("responseMsg", "Date Of Birth is mandatory");
					result.put("status", "failed");
					return result;
				}
			}
			if (addressMandatory) {
				if (address == null && !(address.toString().equalsIgnoreCase(""))) {
					result.put("responseMsg", "address is mandatory");
					result.put("status", "failed");
					return result;
				}
			}
			if (nationalityMandatory) {
				if (nationality == null && !(nationality.toString().equalsIgnoreCase(""))) {
					result.put("responseMsg", "nationality is mandatory");
					result.put("status", "failed");
					return result;
				}
			}

			if (genderMandatory) {
				if (gender == null && !(gender.toString().equalsIgnoreCase(""))) {
					result.put("responseMsg", "gender is mandatory");
					result.put("status", "failed");
					return result;
				}
			}

			String encrypted = UserServcie.encrypt(passwd);
			try {
				userID = userDao.addUserDAO(props, encrypted);

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
			result.put("responseMsg", "user doesnot exist with given credentials");
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

	public Map<String, Serializable> updateUserDetails(String userID, Map props) {

		Map<String, Serializable> result = userDao.updatedetails(userID, props);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			result.put("responseMsg", "failed to update with given credentials");
		} else {
			result.put("responseMsg", "successfully update with given credentials");
		}
		return result;

	}
}