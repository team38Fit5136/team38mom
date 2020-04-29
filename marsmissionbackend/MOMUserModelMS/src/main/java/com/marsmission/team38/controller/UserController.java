package com.marsmission.team38.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marsmission.team38.service.UserServcie;

@RestController
@RequestMapping("/mom/user")
public class UserController {

	@Autowired
	private UserServcie userService;

	@GetMapping("/profile")
	public Map<String, Object> getuserDetailss() {

		return null;
	}

}
