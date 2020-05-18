package com.marsmission.team38.usercontroller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marsmission.team38.userservice.UserServcie;

@RestController
@RequestMapping("/mom/user")
public class UserController {

	@Autowired
	private UserServcie userService;

	@PostMapping("/profile")
	public Map<String, Serializable> addUser(@RequestBody Map<String, ?> props) throws SQLException {

		return userService.addUser(props);
	}

	@GetMapping("/profile")
	public Map<String, Serializable> getUserDetails(
			@RequestParam(value = "passwd", required = true, defaultValue = "null") String passwd,
			@RequestParam(value = "userID", required = true, defaultValue = "null") String userID) throws Exception {
		System.out.println("userName" + userID + "    passwd " + passwd);
		return userService.getUserDetails(userID, passwd);
	}

	@PutMapping("/profile")
	public Map<String, Serializable> updateUserDetails(
			@RequestParam(value = "userID", required = true) String userID,
			@RequestBody Map<String, ?> props) {
		System.out.println("userName" + userID);
		return userService.updateUserDetails(userID, props);
	}
}