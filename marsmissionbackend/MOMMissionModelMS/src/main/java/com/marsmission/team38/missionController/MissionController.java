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

		return null;
//				missionService.addMission(props);
	}


}