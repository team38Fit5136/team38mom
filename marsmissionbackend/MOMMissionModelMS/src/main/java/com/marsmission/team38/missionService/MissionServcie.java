package com.marsmission.team38.missionService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import com.marsmission.team38.missionDAO.MissionDAO;

@Service
public class MissionServcie {

	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private MissionDAO missionDAO;

	@Value("${mission.mandatory.cargoID}")
	boolean cargoIDMandatory;

	@Value("${mission.mandatory.coordinatorID}")
	boolean coordinatorIDMandatory;

	@Value("${mission.mandatory.countryAllowed}")
	boolean countryAllowedMandatory;

	@Value("${mission.mandatory.countryOrigin}")
	boolean countryOriginMandatory;

	@Value("${mission.mandatory.duration}")
	boolean durationMandatory;

	@Value("${mission.mandatory.empID}")
	boolean empIDMandatory;

	@Value("${mission.mandatory.jobID}")
	boolean jobIDMandatory;

	@Value("${mission.mandatory.launchDate}")
	boolean launchDateMandatory;

	@Value("${mission.mandatory.locationID}")
	boolean locationIDMandatory;

	@Value("${mission.mandatory.missionDetails}")
	boolean missionDetailsMandatory;

	@Value("${mission.mandatory.missionName}")
	boolean missionNameMandatory;

	@Value("${mission.mandatory.shuttleID}")
	boolean shuttleIDMandatory;

	@Value("${mission.mandatory.statusID}")
	boolean statusIDMandatory;

	// Method for creating mission

	public Map<String, Serializable> addMissionService(Map<String, ?> props) {

		Map<String, Serializable> result = new HashMap<>();
		logger.info("Mission" + props);

		long missionID = 0;
		Integer cargoID = (int) (props.containsKey("cargoID") ? props.get("cargoID") : 0);
		Integer coordinatorID = (int) (props.containsKey("coordinatorID") ? props.get("coordinatorID") : 0);
		Integer empID = (int) (props.containsKey("empID") ? props.get("empID") : 0);
		Integer jobID = (int) (props.containsKey("jobID") ? (int) props.get("jobID") : 0);
		Integer locationID = (int) (props.containsKey("locationID") ? props.get("locationID") : 0);
		Integer shuttleID = (int) (props.containsKey("shuttleID") ? props.get("shuttleID") : 0);
		Integer statusID = (int) (props.containsKey("statusID") ? props.get("statusID") : 0);
		Integer countryOrigin = (int) (props.containsKey("countryOrigin") ? props.get("countryOrigin") : 0);

		String countryAllowed = (String) (props.containsKey("countryAllowed") ? props.get("countryAllowed") : null);// Json
		String duration = (String) (props.containsKey("duration") ? props.get("duration") : null);
		String launchDate = (String) (props.containsKey("launchDate") ? props.get("launchDate") : null);
		String missionDetails = (String) (props.containsKey("missionDetails") ? props.get("missionDetails") : null);
		String missionName = (String) (props.containsKey("missionName") ? props.get("missionName") : null);

		if (cargoIDMandatory) {
			if (cargoID == 0) {
				logger.warn("cargoID is mandatory for the mission");
				result.put("responseMsg", "cargoID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}

		if (coordinatorIDMandatory) {
			if (coordinatorID == 0) {
				logger.warn("coordinatorID is mandatory for the mission");
				result.put("responseMsg", "coordinatorID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (empIDMandatory) {
			if (empID == 0) {
				logger.warn("Employee ID is mandatory for the mission");
				result.put("responseMsg", "Employee ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (jobIDMandatory) {
			if (jobID == 0) {
				logger.warn("Job ID is mandatory for the mission");
				result.put("responseMsg", "Job ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (locationIDMandatory) {
			if (locationID == 0) {
				logger.warn("Location ID is mandatory for the mission");
				result.put("responseMsg", "Location ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (shuttleIDMandatory) {
			if (shuttleID == 0) {
				logger.warn("Shuttle ID is mandatory for the mission");
				result.put("responseMsg", "Shuttle ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (statusIDMandatory) {
			if (statusID == 0) {
				logger.warn("Status ID is mandatory for the mission");
				result.put("responseMsg", "Status ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (countryOriginMandatory) {
			if (countryOrigin == 0) {
				logger.warn("Country of Origin is mandatory for the mission");
				result.put("responseMsg", "Country of Origin is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}

		if (countryAllowedMandatory) {
			if (countryAllowed == null && !(countryAllowed.toString().equalsIgnoreCase(""))) {
				logger.warn("Country Allowed is mandatory for the mission");
				result.put("responseMsg", "Country Allowed is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (durationMandatory) {
			if (duration == null && !(duration.toString().equalsIgnoreCase(""))) {
				logger.warn("Duration is mandatory for the mission");
				result.put("responseMsg", "Duration is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (launchDateMandatory) {
			if (launchDate == null && !(launchDate.toString().equalsIgnoreCase(""))) {
				logger.warn("Launch Date is mandatory for the mission");
				result.put("responseMsg", "Launch Date is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (missionDetailsMandatory) {
			if (missionDetails == null && !(missionDetails.toString().equalsIgnoreCase(""))) {
				logger.warn("Mission Details is mandatory for the mission");
				result.put("responseMsg", "Mission Details is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}

		}
		if (missionNameMandatory) {
			if (missionName == null && !(missionName.toString().equalsIgnoreCase(""))) {
				logger.warn("Mission Name is mandatory for the mission");
				result.put("responseMsg", "Mission Name is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		try {
			missionID = missionDAO.addMissionDAO(props);
			if (missionID != 0) {
				logger.info("added successfully");
				result.put("missionID ", missionID);
				result.put("status", "success");
				result.put("responseMsg", "successfully inserted");
			} else {
				logger.error("Enter Detials is not correct ");
				result.put("status", "failed");
				result.put("responseMsg", "Enter Detials is not correct ");
			}
		} catch (DuplicateKeyException e) {
			logger.error("Duplciate key error" + e);
			result.put("status", "failed");
			result.put("responseMsg", "Duplicate entry already present");
		} catch (UncategorizedSQLException e) {
			logger.error("Uncategorized  error" + e);
			result.put("status", "failed");
			result.put("responseMsg", "Enter data is not correct");
		}
		return result;
	}

	// Method for getting mission details using missionID or missionName
	public Map<String, Serializable> getMissionDetailsService(String missionID) {
		logger.info("in getMissionDetailsService");

		Map<String, Serializable> result = missionDAO.getMissiondetailsDAO(missionID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" mission does not exist with given credentials");
			result.put("responseMsg", "mission doesnot exist with given credentials");
		}
		return result;
	}

	public Map<String, Serializable> updateMissionDetailsService(String missionID, Map<String, ?> props) {
		logger.info("in updateMissionDetailsService ");
		Map<String, Serializable> result = missionDAO.updatedetailsDAO(missionID, props);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			result.put("responseMsg", "failed to updated mission");
		} else {
			result.put("responseMsg", "successfully updated mission");
		}
		return result;
	}
}
