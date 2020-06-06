package com.marsmission.team38.missionController;

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
import org.springframework.web.multipart.MultipartFile;

import com.marsmission.team38.missionService.MissionServcie;

/**
 * The Rest controller starting from defined port in microservices.properties
 * Default path for request mapping in mission controller
 */
@RestController
@RequestMapping("/mom/mission")
@CrossOrigin(origins = "http://localhost:3000")
public class MissionController {

	//logger variable to print logs
	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private MissionServcie missionService;

	//api call for adding mission
	@PostMapping("")
	public Map<String, Serializable> addMission(@RequestParam("file") MultipartFile[] files,
			@RequestBody Map<String, ?> props) throws SQLException {
		logger.info("in addMission"+files+"            "+props);
		return null;
//		return missionService.addMissionService(props);
	}

	//api call for getting mission
	@GetMapping("")
	public Map<String, Serializable> getMissionDetails(
			@RequestParam(value = "missionID", required = true, defaultValue = "null") String missionID) throws Exception {
		logger.info("in get getMissionDetails" + missionID);
		return missionService.getMissionDetailsService(missionID);
	}

	//api call for updating Mission
	@PutMapping("")
	public Map<String, Serializable> updateMissionDetails(
			@RequestParam(value = "missionID", required = true) String missionID,
			@RequestBody Map<String, ?> props) {
		logger.info("missionID" + missionID);
		return missionService.updateMissionDetailsService(missionID, props);
	}

}