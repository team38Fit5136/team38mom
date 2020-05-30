package com.marsmission.team38.missionController;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marsmission.team38.missionService.MissionServcie;

@RestController
@RequestMapping("/mom/mission")
public class MissionController {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private MissionServcie missionService;

	@PostMapping("/profile")
	public Map<String, Serializable> addMission(@RequestBody Map<String, ?> props) throws SQLException {
		logger.info("in addMission");
		return null;
//				missionService.addMission(props);
	}

}