package com.marsmission.team38.missionService;

import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import com.marsmission.team38.missionDAO.MissionDAO;

@Service
public class MissionServcie {

	@Autowired
	private MissionDAO misssionDAO;

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

	// Method for creating user

	public Map<String, Serializable> addMission(Map<String, ?> props) {

		Map<String, Serializable> result = new HashMap<>();
		System.out.println("+++++++ Mission" + props);
		long missionID = 0;
		int cargoID = (int) (props.containsKey("cargoID") ? props.get("cargoID") : 0);
		int coordinatorID = (int) (props.containsKey("coordinatorID") ? props.get("coordinatorID") : 0);
		int empID = (int) (props.containsKey("empID") ? props.get("empID") : 0);
		int jobID = (int) (props.containsKey("jobID") ? (int) props.get("jobID") : 0);
		int locationID = (int) (props.containsKey("locationID") ? props.get("locationID") : 0);
		int shuttleID = (int) (props.containsKey("shuttleID") ? props.get("shuttleID") : 0);
		int statusID = (int) (props.containsKey("statusID") ? props.get("statusID") : 0);

		String countryAllowed = (String) (props.containsKey("countryAllowed") ? props.get("countryAllowed") : null);// Json
		String countryOrigin = (String) (props.containsKey("countryOrigin") ? props.get("countryOrigin") : null);
		String duration = (String) (props.containsKey("duration") ? props.get("duration") : null);
		String launchDate = (String) (props.containsKey("launchDate") ? props.get("launchDate") : null);
		String missionDetails = (String) (props.containsKey("missionDetails") ? props.get("missionDetails") : null);
		String missionName = (String) (props.containsKey("missionName") ? props.get("missionName") : null);

		// checks for username = first name and last name
		if (cargoIDMandatory) {
			if (cargoID == 0) {
				result.put("responseMsg", "cargoID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}

		if (coordinatorIDMandatory) {
			if (coordinatorID == 0) {
				result.put("responseMsg", "coordinatorID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (empIDMandatory) {
			if (empID == 0) {
				result.put("responseMsg", "Employee ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (jobIDMandatory) {
			if (jobID == 0) {
				result.put("responseMsg", "Job ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (locationIDMandatory) {
			if (locationID == 0) {
				result.put("responseMsg", "Location ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (shuttleIDMandatory) {
			if (shuttleID == 0) {
				result.put("responseMsg", "Shuttle ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (shuttleIDMandatory) {
			if (shuttleID == 0) {
				result.put("responseMsg", "Shuttle ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (statusIDMandatory) {
			if (statusID == 0) {
				result.put("responseMsg", "Status ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}

		if (countryAllowedMandatory) {
			if (countryAllowed == null && !(countryAllowed.toString().equalsIgnoreCase(""))) {
				result.put("responseMsg", "Country Allowed is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (countryOriginMandatory) {
			if (countryOrigin == null && !(countryOrigin.toString().equalsIgnoreCase(""))) {
				result.put("responseMsg", "Country of Origin is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (durationMandatory) {
			if (duration == null && !(duration.toString().equalsIgnoreCase(""))) {
				result.put("responseMsg", "Duration is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (launchDateMandatory) {
			if (launchDate == null && !(launchDate.toString().equalsIgnoreCase(""))) {
				result.put("responseMsg", "Launch Date is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (missionDetailsMandatory) {
			if (missionDetails == null && !(missionDetails.toString().equalsIgnoreCase(""))) {
				result.put("responseMsg", "Mission Details is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (missionNameMandatory) {
			if (missionName == null && !(missionName.toString().equalsIgnoreCase(""))) {
				result.put("responseMsg", "Mission Name is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		try {
			missionID = misssionDAO.addMissionDAO(props);
			if (missionID != 0) {
				result.put("userID ", missionID);
				result.put("status", "success");
				result.put("responseMsg", "successfully inserted");
			} else {
				result.put("status", "failed");
				result.put("responseMsg", "Enter Detials is not correct ");
			}
		} catch (DuplicateKeyException e) {
			result.put("status", "failed");
			result.put("responseMsg", "Duplicate entry already present");
		} catch (UncategorizedSQLException e) {
			result.put("status", "failed");
			result.put("responseMsg", "Enter data is not correct");
		}
		return result;
	}
}
