package com.marsmission.team38.missionService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.csvreader.CsvReader;
import com.marsmission.team38.missionDAO.MissionDAO;

@Service
public class MissionServcie {

	// logger variable to print logs
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private MissionDAO missionDAO;

	// variables checks form property files

	@Value("${mission.mandatory.coordinatorID}")
	boolean coordinatorIDMandatory;

	@Value("${mission.mandatory.countryOrigin}")
	boolean countryOriginMandatory;

	@Value("${mission.mandatory.duration}")
	boolean durationMandatory;

	@Value("${mission.mandatory.launchDate}")
	boolean launchDateMandatory;

	@Value("${mission.mandatory.missionDetails}")
	boolean missionDetailsMandatory;

	@Value("${mission.mandatory.missionName}")
	boolean missionNameMandatory;

	@Value("${mission.mandatory.statusID}")
	boolean statusIDMandatory;

	// Method for creating mission

	public Map<String, Serializable> addMissionService(Map<String, Object> body, FileReader cargoForJourneyFileReader,
			FileReader cargoForMissionFileReader, FileReader cargoForOtherMissionFileReader) throws IOException {
	
		Map<String, Serializable> result = new HashMap<>();
		List<String> headers = new ArrayList<String>();

		logger.info("Mission" + body);
		CsvReader cargoForJourney = new CsvReader(cargoForJourneyFileReader);

		while (cargoForJourney.readRecord()) {
			logger.info("inside loop");
			Map<String, Serializable> cargoForJourneyMap = new HashMap<String, Serializable>();

			int columnCount = cargoForJourney.getColumnCount();

			for (int i = 0; i < columnCount; i++) {
				logger.info("products.get(i):" + cargoForJourney.get(i));
				headers.add(cargoForJourney.get(i));
			}
		}
//
//		int missionID = 0;
//		int resp = 0;
//		Integer coordinatorID = (int) (body.containsKey("coordinatorID") ? body.get("coordinatorID") : 0);
//		Integer locationID = (int) (body.containsKey("locationID") ? body.get("locationID") : 0);
//		Integer statusID = (int) (body.containsKey("statusID") ? body.get("statusID") : 0);
//		Integer countryOrigin = (int) (body.containsKey("countryOrigin") ? body.get("countryOrigin") : 0);
//
//		List<String> countryAllowed = (List<String>) (body.containsKey("countryAllowed") ? body.get("countryAllowed")
//				: null);// array
//		List<String> jobIDs = (List<String>) (body.containsKey("jobID") ? body.get("jobID") : null);// array
//
//		Integer duration = (Integer) (body.containsKey("duration") ? body.get("duration") : null);
//		String launchDate = (String) (body.containsKey("launchDate") ? body.get("launchDate") : null);
//		String missionDetails = (String) (body.containsKey("missionDetails") ? body.get("missionDetails") : null);
//		String missionName = (String) (body.containsKey("missionName") ? body.get("missionName") : null);
//
//		if (coordinatorIDMandatory) {
//			if (coordinatorID == 0) {
//				logger.warn("coordinatorID is mandatory for the mission");
//				result.put("responseMsg", "coordinatorID is mandatory for the mission");
//				result.put("status", "failed");
//				return result;
//			}
//		}
//		if (statusIDMandatory) {
//			if (statusID == 0) {
//				logger.warn("Status ID is mandatory for the mission");
//				result.put("responseMsg", "Status ID is mandatory for the mission");
//				result.put("status", "failed");
//				return result;
//			}
//		}
//		if (countryOriginMandatory) {
//			if (countryOrigin == 0) {
//				logger.warn("Country of Origin is mandatory for the mission");
//				result.put("responseMsg", "Country of Origin is mandatory for the mission");
//				result.put("status", "failed");
//				return result;
//			}
//		}
//
//		if (durationMandatory) {
//			if (duration == null || (duration.toString().equalsIgnoreCase(""))) {
//				logger.warn("Duration is mandatory for the mission");
//				result.put("responseMsg", "Duration is mandatory for the mission");
//				result.put("status", "failed");
//				return result;
//			}
//		}
//		if (launchDateMandatory) {
//			if (launchDate == null || (launchDate.toString().equalsIgnoreCase(""))) {
//				logger.warn("Launch Date is mandatory for the mission");
//				result.put("responseMsg", "Launch Date is mandatory for the mission");
//				result.put("status", "failed");
//				return result;
//			}
//		}
//		if (missionDetailsMandatory) {
//			if (missionDetails == null || (missionDetails.toString().equalsIgnoreCase(""))) {
//				logger.warn("Mission Details is mandatory for the mission");
//				result.put("responseMsg", "Mission Details is mandatory for the mission");
//				result.put("status", "failed");
//				return result;
//			}
//
//		}
//		if (missionNameMandatory) {
//			if (missionName == null || (missionName.toString().equalsIgnoreCase(""))) {
//				logger.warn("Mission Name is mandatory for the mission");
//				result.put("responseMsg", "Mission Name is mandatory for the mission");
//				result.put("status", "failed");
//				return result;
//			}
//		}
//		try {
//			// adding mission and returning mission id
//			missionID = missionDAO.addMissionDAO(coordinatorID, statusID, countryOrigin, duration, launchDate,
//					missionDetails, missionName);
//			if (missionID != 0) {
//				logger.info("added successfully");
//				if (countryAllowed != null && !(countryAllowed.isEmpty())) {
//					resp = missionDAO.addAllowedCountry(missionID, countryAllowed);
//					resp = 0;
//				} else if (resp == 0) {
//					missionDAO.deleteMissionDAO(missionID);
//					logger.error("Enter Detials is not correct  allowed country ");
//					result.put("status", "failed");
//					result.put("responseMsg", "Enter Detials is not correct  allowed country ");
//					return result;
//				}
//				if (locationID != null && !(locationID != 0)) {
//					resp = missionDAO.addMissionRelatedLocation(missionID, locationID);
//					resp = 0;
//				} else if (resp == 0) {
//					missionDAO.deleteAllowedCountry(missionID);
//					missionDAO.deleteMissionDAO(missionID);
//					logger.error("Enter Detials is not correct  location");
//					result.put("status", "failed");
//					result.put("responseMsg", "Enter Detials is not correct location");
//					return result;
//				}
//				if (jobIDs != null && !(jobIDs.isEmpty())) {
//					resp = missionDAO.updateMissiontoJob(missionID, jobIDs);
//				} else if (resp == 0) {
//					missionDAO.deleteMissionRelatedLocation(missionID);
//					missionDAO.deleteAllowedCountry(missionID);
//					missionDAO.deleteMissionDAO(missionID);
//					logger.error("Enter Detials is not correct  Job");
//					result.put("status", "failed");
//					result.put("responseMsg", "Enter Detials is not correct Job");
//					return result;
//				}
//				
//
//				result.put("missionID ", missionID);
//				result.put("status", "success");
//				result.put("responseMsg", "successfully inserted");
//			} else {
//				logger.error("Enter Detials is not correct ");
//				result.put("status", "failed");
//				result.put("responseMsg", "Enter Details is not correct ");
//			}
//		} catch (
//
//		DuplicateKeyException e) {
//			logger.error("Duplciate key error" + e);
//			result.put("status", "failed");
//			result.put("responseMsg", "Duplicate entry already present");
//		} catch (UncategorizedSQLException e) {
//			logger.error("Uncategorized  error" + e);
//			result.put("status", "failed");
//			result.put("responseMsg", "Enter data is not correct");
//		}
//					return result;
		return null;
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

	// Method for updating mission details using missionID or missionName
	public Map<String, Serializable> updateMissionDetailsService(String missionID, Map<String, ?> props) {
		logger.info("in updateMissionDetailsService ");
		// updating mission details
		Map<String, Serializable> result = missionDAO.updatedetailsDAO(missionID, props);

		return result;
	}

}
