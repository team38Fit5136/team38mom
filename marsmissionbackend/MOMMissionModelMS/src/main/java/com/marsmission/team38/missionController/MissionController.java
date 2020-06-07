package com.marsmission.team38.missionController;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsmission.team38.missionService.MissionServcie;

/**
 * The Rest controller starting from defined port in microservices.properties
 * Default path for request mapping in mission controller
 */
@RestController
@RequestMapping("/mom/mission")
@CrossOrigin(origins = "http://localhost:3000")
public class MissionController {

	// logger variable to print logs
	private Log logger = LogFactory.getLog(this.getClass());
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MissionServcie missionService;

	// api call for adding mission
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Serializable> addMission(
			@RequestParam(value = "cargoForJourney", required = false, defaultValue = "null") MultipartFile cargoForJourney,
			@RequestParam(value = "cargoForMission", required = false, defaultValue = "null") MultipartFile cargoForMission,
			@RequestParam(value = "cargoForOtherMission", required = false, defaultValue = "null") MultipartFile cargoForOtherMission,
			@RequestParam("props") String props) throws Exception {
		logger.info("in addMission" + cargoForJourney + "            " + props);
		JsonNode actualObj = mapper.readTree(props);
//		
		Map<String, Object> body = mapper.convertValue(actualObj, new TypeReference<Map<String, Object>>() {
		});

		logger.info(body);

		return missionService.addMissionService(body, cargoForJourney, cargoForMission, cargoForOtherMission);
	}

	// api call for getting mission
	@GetMapping("")
	public Map<String, Serializable> getMissionDetails(
			@RequestParam(value = "missionID", required = false, defaultValue = "null") String missionID)
			throws Exception {
		logger.info("in get getMissionDetails" + missionID);
		return missionService.getMissionDetailsService(missionID);
	}

	@GetMapping("/shuttle")
	public Map<String, ?> getMissionForShuttle(
			@RequestParam(value = "missionID", required = true, defaultValue = "null") String missionID)
			throws Exception {
		logger.info("in get getMissionDetails" + missionID);

		return missionService.getMissionDetailsshuttle(missionID);
	}


	// api call for updating Mission

	// api call for adding mission
	/*
	 * @RequestMapping(value = "", method = RequestMethod.PUT)
	 * 
	 * @RequestParam(value = "cargoForJourney", required = false, defaultValue =
	 * "null") MultipartFile cargoForJourney, public Map<String, Serializable>
	 * updateMission(
	 * 
	 * @RequestParam(value = "cargoForMission", required = false, defaultValue =
	 * "null") MultipartFile cargoForMission,
	 * 
	 * @RequestParam(value = "cargoForOtherMission", required = false, defaultValue
	 * = "null") MultipartFile cargoForOtherMission,
	 * 
	 * @RequestParam("props") String props) throws Exception {
	 * logger.info("in addMission" + cargoForJourney + "            " + props);
	 * JsonNode actualObj = mapper.readTree(props); // Map<String, Object> body =
	 * mapper.convertValue(actualObj, new TypeReference<Map<String, Object>>() { });
	 * 
	 * logger.info(body);
	 * 
	 * // return missionService.updateMissionService(body, cargoForJourney,
	 * cargoForMission, cargoForOtherMission); return null; }
	 */
//	public Map<String, Serializable> updateMissionDetails(
//			@RequestParam(value = "missionID", required = true) String missionID, @RequestBody Map<String, ?> props) {
//		logger.info("missionID" + missionID);
//		return missionService.updateMissionDetailsService(missionID, props);
//	}

}