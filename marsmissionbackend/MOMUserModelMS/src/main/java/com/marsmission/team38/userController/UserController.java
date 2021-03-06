package com.marsmission.team38.userController;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marsmission.team38.userService.UserServcie;

/**
 * The Rest controller starting from defined port in microservices.properties
 * Default path for request mapping in user controller
 */
@RestController
@RequestMapping("/mom/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	private  Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private UserServcie userService;

	@PostMapping("/profile")
	public Map<String, Serializable> addUser(@RequestBody Map<String, ?> props) throws SQLException {
		logger.info("in post addUser" + props);
		return userService.addUserService(props);
	}

	@GetMapping("/profile")
	public Map<String, Serializable> getUserDetails(
			@RequestParam(value = "passwd", required = false, defaultValue = "null") String passwd,
			@RequestParam(value = "userRole", required = false, defaultValue = "null") String userRole,
			@RequestParam(value = "userID", required = false, defaultValue = "null") String userID) throws Exception {

		logger.info("in get getUserDetails" + userID + " password " + passwd+"userRole"+userRole);

		return userService.getUserDetailsService(userID, passwd,userRole);
	}

	@PutMapping("/profile")
	public Map<String, Serializable> updateUserDetails(@RequestParam(value = "userID", required = true) String userID,
			@RequestBody Map<String, ?> props) {
		logger.info("in upadte updateUserDetails" + userID);
		return userService.updateUserDetailsService(userID, props);
	}
}