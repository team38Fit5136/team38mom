package com.marsmission.team38.userService;

import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import com.marsmission.team38.userDAO.UserDAO;

@Service
public class UserServcie {

	private  Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserDAO userDAO;

	@Value("${user.mandatory.userName}")
	boolean userNameMandatory;

	@Value("${user.mandatory.email}")
	boolean emailMandatory;

	@Value("${user.mandatory.password}")
	boolean passwordMandatory;

	@Value("${user.mandatory.DOB}")
	boolean dobMandatory;

	@Value("${user.mandatory.address}")
	boolean addressMandatory;

	@Value("${user.mandatory.nationality}")
	boolean nationalityMandatory;

	@Value("${user.mandatory.gender}")
	boolean genderMandatory;

	@Value("${user.mandatory.userRole}")
	boolean userRoleMandatory;

	// Method for creating user
	public Map<String, Serializable> addUserService(Map<String, ?> props) {

		Map<String, Serializable> result = new HashMap<>();
		logger.info("properties to addUserService" + props);

		String userName = (String) (props.containsKey("userName") ? props.get("userName") : null);
		String email = (String) (props.containsKey("email") ? props.get("email") : null);
		String passwd = (String) (props.containsKey("passwd") ? props.get("passwd") : null);
		String dob = (String) (props.containsKey("dob") ? props.get("dob") : null);
		String address = (String) (props.containsKey("address") ? props.get("address") : null);
		String nationality = (String) (props.containsKey("nationality") ? props.get("nationality") : null);
		String gender = (String) (props.containsKey("gender") ? props.get("gender") : null);
		String userRole = (String) (props.containsKey("userRole") ? props.get("userRole") : null);
		long userID = 0;

		// checks for username = first name and last name
		if (userNameMandatory) {
			if (userName == null && !(userName.toString().equalsIgnoreCase(""))) {
				logger.warn("userName is mandatory that is first name and last name and cannot be empty");
				result.put("responseMsg", "userName is mandatory that is first name and last name and cannot be empty");
				result.put("status", "failed");
				return result;
			}
		}

		// checks for email
		if (emailMandatory) {
			if (email == null && !(email.toString().equalsIgnoreCase(""))) {
				logger.warn("email is mandatory");
				result.put("responseMsg", "email is mandatory");
				result.put("status", "failed");
				return result;
			}

			// check for password field if mandatory
			if (passwordMandatory) {
				if (passwd == null && !(passwd.toString().equalsIgnoreCase(""))) {
					logger.warn("password is mandatory");
					result.put("responseMsg", "password is mandatory");
					result.put("status", "failed");
					return result;
				}
			}

			// Checks for DOB mandatory
			if (dobMandatory) {
				if (dob == null && !(dob.toString().equalsIgnoreCase(""))) {
					logger.warn("Date Of Birth is mandatory");
					result.put("responseMsg", "Date Of Birth is mandatory");
					result.put("status", "failed");
					return result;
				}
			}
			if (addressMandatory) {
				if (address == null && !(address.toString().equalsIgnoreCase(""))) {
					logger.warn("address is mandatory");
					result.put("responseMsg", "address is mandatory");
					result.put("status", "failed");
					return result;
				}
			}
			if (nationalityMandatory) {
				if (nationality == null && !(nationality.toString().equalsIgnoreCase(""))) {
					logger.warn("nationality is mandatory");
					result.put("responseMsg", "nationality is mandatory");
					result.put("status", "failed");
					return result;
				}
			}

			if (genderMandatory) {
				if (gender == null && !(gender.toString().equalsIgnoreCase(""))) {
					logger.warn("userName is mandatory that is first name and last name and cannot be empty");
					result.put("responseMsg", "gender is mandatory");
					result.put("status", "failed");
					return result;
				}
			}
			if (userRoleMandatory) {
				if (userRole == null && !(userRole.toString().equalsIgnoreCase(""))) {
					logger.warn("userRole is mandatory that is admin,coordinator or candidate and cannot be empty");
					result.put("responseMsg",
							"userRole is mandatory that is admin,coordinator or candidate and cannot be empty");
					result.put("status", "failed");
					return result;
				}
			}

			String encrypted = UserServcie.encrypt(passwd);
			try {
				userID = userDAO.addUserDAO(props, encrypted);

				if (userID != 0) {
					logger.info("added the user" + userID);
					result.put("userID ", userID);
					result.put("status", "success");
					result.put("responseMsg", "successfully inserted");
				} else {
					logger.error("Enter user name is not correct");
					result.put("status", "failed");
					result.put("responseMsg", "Enter user name is not correct ");
				}
			} catch (DuplicateKeyException e) {
				logger.error("email is already present");
				result.put("status", "failed");
				result.put("responseMsg", "email is already present");
			} catch (UncategorizedSQLException e) {
				logger.error("Enter data is not correct or user is already present");
				result.put("status", "failed");
				result.put("responseMsg", "Enter data is not correct or user is already present");
			}
			return result;
		}
		return result;

	}

	// Method for getting user details using email as user-name and password
	public Map<String, Serializable> getUserDetailsService(String userID, String passwd) {
		logger.info("in getUserDetailsService");
		String encrypted = UserServcie.encrypt(passwd);
		Map<String, Serializable> result = userDAO.getUserdetailsDAO(userID, encrypted);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("in user doesnot exist with given credentials");
			result.put("responseMsg", "user doesnot exist with given credentials");
		}
		return result;
	}

	public Map<String, Serializable> updateUserDetailsService(String userID, Map<String, ?> props) {
		logger.info("in updateUserDetailsService ");
		Map<String, Serializable> result = userDAO.updatedetailsDAO(userID, props);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("failed to update with given credentials");
			result.put("responseMsg", "failed to update with given credentials");
		} else {
			logger.info("successfully update with given credentials");
			result.put("responseMsg", "successfully update with given credentials");
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

}
