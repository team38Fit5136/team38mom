package com.marsmission.team38.usercontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marsmission.team38.userservice.UserServcie;

@RestController
@RequestMapping("/mom/user")
public class UserController {

	@Autowired
	private UserServcie userService;

	@GetMapping("/profile")
	public Map<String, Object> getuserDetails(
			@RequestParam(value = "passwd", required = false, defaultValue = "null") String passwd,
			@RequestParam(value = "userName", required = false, defaultValue = "null") String userName) {

		return userService.getuserDetails(userName, passwd);
	}

}