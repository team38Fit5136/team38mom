package com.marsmission.team38.missionController;

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

import com.marsmission.team38.missionService.MissionServcie;

@RestController
@RequestMapping("/mom/mission")
public class MissionController {

	@Autowired
	private MissionServcie missionService;

	@PostMapping("/profile")
	public Map<String, Serializable> addMission(@RequestBody Map<String, ?> props) throws SQLException {

		return MissionService.addMission(props);
	}

	@GetMapping("/profile")
	public Map<String, Serializable> getMissionDetails(
			@RequestParam(value = "passwd", required = true, defaultValue = "null") String passwd,
			@RequestParam(value = "MissionID", required = true, defaultValue = "null") String MissionID) throws Exception {
		System.out.println("MissionName" + MissionID + "    passwd " + passwd);
		return MissionService.getMissionDetails(userID, passwd);
	}

	@PutMapping("/profile")
	public Map<String, Serializable> updateUserDetails(
			@RequestParam(value = "userID", required = true) String userID,
			@RequestBody Map<String, ?> props) {
		System.out.println("userName" + userID);
		return userService.updateUserDetails(userID, props);
	}
}