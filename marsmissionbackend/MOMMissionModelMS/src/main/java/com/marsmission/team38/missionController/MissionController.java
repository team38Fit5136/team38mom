package com.marsmission.team38.missionController;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private MissionServcie missionService;

	@PostMapping("")
	public Map<String, Serializable> addMission(@RequestBody Map<String, ?> props) throws SQLException {
		logger.info("in addMission");
		
		return missionService.addMissionService(props);
		
	}

	@GetMapping("")
	public Map<String, Serializable> getMissionDetails(
			@RequestParam(value = "missionID", required = true, defaultValue = "null") String missionID) throws Exception {

		logger.info("in get getMissionDetails" + missionID);

		return missionService.getMissionDetailsService(missionID);
	}

	@PutMapping("")
	public Map<String, Serializable> updateUserDetails(
			@RequestParam(value = "missionID", required = true) String missionID,
			@RequestBody Map<String, ?> props) {
		logger.info("missionID" + missionID);
		return missionService.updateMissionDetailsService(missionID, props);
	}

}