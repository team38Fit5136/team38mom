package com.marsmission.team38.missionService;

import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import com.marsmission.team38.missionDAO.MissionDAO;

@Service
public class MissionServcie {

	@Autowired
	private MissionDAO misssionDAO; /*userDao;
	@Value("${mission.mandatory.cargo_id}")
	@Value("${mission.mandatory.coordinator_id}")
	@Value("${mission.mandatory.country_allowed}")
	@Value("${mission.mandatory.country_origin}")
	@Value("${mission.mandatory.duration}")
	@Value("${mission.mandatory.emp_id}")
	@Value("${mission.mandatory.job_id}")
	@Value("${mission.mandatory.launch_date}")
	@Value("${mission.mandatory.location_id}")
	@Value("${mission.mandatory.mission_details}")
	@Value("${mission.mandatory.Mission_name}")
	@Value("${mission.mandatory.shuttle_id}")
	@Value("${mission.mandatory.status_id}")
	`cargo_id`, `coordinator_id`, `country_allowed`, `country_origin`, `duration`, `emp_id`, `job_id`, `launch_date`, `location_id`, `mission_details`, `mission_id`, `Mission_name`, `shuttle_id`, `status_id`
*/
	

	// Method for creating user
	/*
	 * public Map<String, Serializable> addMission(Map<String, ?> props) {
	 * 
	 * Map<String, Serializable> result = new HashMap<>();
	 * System.out.println("+++++++" + props); if (!(props.containsKey("userName")))
	 * {
	 * 
	 * result.put("status", "failed"); result.put("responseMsg",
	 * "error in creating user no user name given"); return result; }
	 * 
	 * String userName = (String) (props.containsKey("userName") ?
	 * props.get("userName") : null); String email = (String)
	 * (props.containsKey("email") ? props.get("email") : null); String passwd =
	 * (String) (props.containsKey("passwd") ? props.get("passwd") : null); String
	 * dob = (String) (props.containsKey("dob") ? props.get("dob") : null); String
	 * address = (String) (props.containsKey("address") ? props.get("address") :
	 * null); String nationality = (String) (props.containsKey("nationality") ?
	 * props.get("nationality") : null); String gender = (String)
	 * (props.containsKey("gender") ? props.get("gender") : null);
	 * 
	 * long userID = 0;
	 * 
	 * // checks for username = first name and last name if (usernameMandatory) { if
	 * (userName == null && !(userName.toString().equalsIgnoreCase(""))) {
	 * result.put("responseMsg",
	 * "userName is mandatory that is first name and last name and cannot be empty"
	 * ); result.put("status", "failed"); return result; } }
	 * 
	 * // checks for email if (emailMandatory) { if (email == null &&
	 * !(email.toString().equalsIgnoreCase(""))) { result.put("responseMsg",
	 * "email is mandatory"); result.put("status", "failed"); return result; }
	 * 
	 * // check for password field if mandatory if (passwdMandatory) { if (passwd ==
	 * null && !(passwd.toString().equalsIgnoreCase(""))) {
	 * result.put("responseMsg", "password is mandatory"); result.put("status",
	 * "failed"); return result; } }
	 * 
	 * // Checks for DOB mandatory if (dobMandatory) { if (dob == null &&
	 * !(dob.toString().equalsIgnoreCase(""))) { result.put("responseMsg",
	 * "Date Of Birth is mandatory"); result.put("status", "failed"); return result;
	 * } } if (addressMandatory) { if (address == null &&
	 * !(address.toString().equalsIgnoreCase(""))) { result.put("responseMsg",
	 * "address is mandatory"); result.put("status", "failed"); return result; } }
	 * if (nationalityMandatory) { if (nationality == null &&
	 * !(nationality.toString().equalsIgnoreCase(""))) { result.put("responseMsg",
	 * "nationality is mandatory"); result.put("status", "failed"); return result; }
	 * }
	 * 
	 * if (genderMandatory) { if (gender == null &&
	 * !(gender.toString().equalsIgnoreCase(""))) { result.put("responseMsg",
	 * "gender is mandatory"); result.put("status", "failed"); return result; } }
	 * 
	 * String encrypted = MissionServcie.encrypt(passwd); try { userID =
	 * (!userName.equalsIgnoreCase("") ? (userDao.addUserDAO(props, encrypted)) :
	 * 0);
	 * 
	 * if (userID != 0) { result.put("userID ", userID); result.put("status",
	 * "success"); result.put("responseMsg", "successfully inserted"); } else {
	 * result.put("status", "failed"); result.put("responseMsg",
	 * "Enter user name is not correct "); } } catch (DuplicateKeyException e) {
	 * result.put("status", "failed"); result.put("responseMsg",
	 * "email is already present"); } catch (UncategorizedSQLException e) {
	 * result.put("status", "failed"); result.put("responseMsg",
	 * "Enter data is not correct or user is already present"); } return result; }
	 * return result;
	 * 
	 * }
	 */

}
