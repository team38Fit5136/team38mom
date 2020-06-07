package com.marsmission.team38.missionService;

import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marsmission.team38.missionDAO.MissionDAO;

@Service
public class MissionServcie {

	// logger variable to print logs
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private MissionDAO missionDAO;

	@Autowired
	private HttpServletRequest request;

	// variables values form property files

	@Value("${cargo.file.path}")
	String cargoFilesPath;

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

	public Map<String, Serializable> addMissionService(Map<String, Object> body, MultipartFile cargoForJourney,
			MultipartFile cargoForMission, MultipartFile cargoForOtherMission) throws Exception {

		Map<String, Serializable> result = new HashMap<>();

		//
		int missionID = 0;
		Integer coordinatorID = (int) (body.containsKey("coordinatorID") ? body.get("coordinatorID") : 1);
		Integer locationID = (int) (body.containsKey("locationID") ? body.get("locationID") : 1);
		Integer statusID = (int) (body.containsKey("statusID") ? body.get("statusID") : 1);
		Integer countryOrigin = (int) (body.containsKey("countryOrigin") ? body.get("countryOrigin") : 1);

		List<?> countryAllowed = (List<?>) (body.containsKey("countryAllowed") ? body.get("countryAllowed") : null);// array
		List<?> jobID = (List<?>) (body.containsKey("jobID") ? body.get("jobID") : null);// array

		Integer duration = (Integer) (body.containsKey("duration") ? body.get("duration") : 0);
		String launchDate = (String) (body.containsKey("launchDate") ? body.get("launchDate") : null);
		String missionDetails = (String) (body.containsKey("missionDetails") ? body.get("missionDetails") : null);
		String missionName = (String) (body.containsKey("missionName") ? body.get("missionName") : null);

		if (coordinatorIDMandatory) {
			if (coordinatorID == 0) {
				logger.warn("coordinatorID is mandatory for the mission");
				result.put("responseMsg", "coordinatorID is mandatory for the mission");
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

		if (durationMandatory) {
			if (duration == null || (duration.toString().equalsIgnoreCase(""))) {
				logger.warn("Duration is mandatory for the mission");
				result.put("responseMsg", "Duration is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (launchDateMandatory) {
			if (launchDate == null || (launchDate.toString().equalsIgnoreCase(""))) {
				logger.warn("Launch Date is mandatory for the mission");
				result.put("responseMsg", "Launch Date is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (missionDetailsMandatory) {
			if (missionDetails == null || (missionDetails.toString().equalsIgnoreCase(""))) {
				logger.warn("Mission Details is mandatory for the mission");
				result.put("responseMsg", "Mission Details is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}

		}
		if (missionNameMandatory) {
			if (missionName == null || (missionName.toString().equalsIgnoreCase(""))) {
				logger.warn("Mission Name is mandatory for the mission");
				result.put("responseMsg", "Mission Name is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		try {
			// adding mission and returning mission id
			missionID = missionDAO.addMissionDAO(coordinatorID, statusID, countryOrigin, duration, launchDate,
					missionDetails, missionName);
			if (missionID != 0) {
				logger.info("added successfully");
				if (countryAllowed != null && !(countryAllowed.isEmpty())) {
					if (missionDAO.addAllowedCountry(missionID, countryAllowed) == 0) {
						missionDAO.deleteMissionDAO(missionID);
						logger.error("Enter Detials is not correct  allowed country ");
						result.put("status", "failed");
						result.put("responseMsg", "Enter Detials is not correct  allowed country ");
						return result;
					}
				}
				if (locationID != null && (locationID != 0)) {
					if (missionDAO.addMissionRelatedLocation(missionID, locationID) == 0) {
						missionDAO.deleteAllowedCountry(missionID);
						missionDAO.deleteMissionDAO(missionID);
						logger.error("Enter Detials is not correct  location");
						result.put("status", "failed");
						result.put("responseMsg", "Enter Detials is not correct location");
						return result;
					}
				}
				logger.info("*****" + jobID);

				if (jobID != null && !(jobID.isEmpty())) {
					logger.info("*****" + jobID);
					if (missionDAO.updateMissiontoJob(missionID, jobID) == 0) {
						missionDAO.deleteMissionRelatedLocation(missionID);
						missionDAO.deleteAllowedCountry(missionID);
						missionDAO.deleteMissionDAO(missionID);
						logger.error("Enter Detials is not correct  Job");
						result.put("status", "failed");
						result.put("responseMsg", "Enter Detials is not correct Job");
						return result;
					}
				}

				String jsonObject = addCargoFile(cargoForJourney, cargoForMission, cargoForOtherMission, missionID);

				if (missionDAO.addCargoDAO(jsonObject, missionID) == 0) {
					missionDAO.updateMissiontoJob(1, jobID);
					missionDAO.deleteMissionRelatedLocation(missionID);
					missionDAO.deleteAllowedCountry(missionID);
					missionDAO.deleteMissionDAO(missionID);
					logger.error("Enter Detials is not correct ");
					result.put("status", "failed");
					result.put("responseMsg", "Enter Detials is not correct Job");
					return result;
				}

				result.put("missionID ", missionID);
				result.put("status", "success");
				result.put("responseMsg", "successfully inserted");
			} else {
				logger.error("Enter Detials is not correct ");
				result.put("status", "failed");
				result.put("responseMsg", "Enter Details is not correct ");
			}
		} catch (

		DuplicateKeyException e) {
			logger.error("Duplciate key error" + e);
			result.put("status", "failed");
			result.put("responseMsg", "Duplicate entry already present");
		} catch (UncategorizedSQLException e) {
			missionDAO.updateMissiontoJob(1, jobID);
			missionDAO.deleteMissionRelatedLocation(missionID);
			missionDAO.deleteAllowedCountry(missionID);
			missionDAO.deleteMissionDAO(missionID);

			logger.error("Uncategorized  error" + e);
			result.put("status", "failed");
			result.put("responseMsg", "Enter data is not correct");
		}
		return result;
	}

	public String addCargoFile(MultipartFile cargoForJourney, MultipartFile cargoForMission,
			MultipartFile cargoForOtherMission, int missionID) throws Exception {

		// Taking the from tmp storage

		// Getting file original Name
		File cargoForJourneyFileName = new File(cargoForJourney.getOriginalFilename());
		// Saving the file to destination
		String filePath = cargoFilesPath + missionID + "_" + cargoForJourneyFileName;
		File dest = new File(filePath);

		logger.info("******" + filePath);

		cargoForJourney.transferTo(dest);

		// Getting file original Name
		File cargoForMissionFileName = new File(cargoForMission.getOriginalFilename());
		// Saving the file to destination
		filePath = cargoFilesPath + missionID + "_" + cargoForMissionFileName;
		dest = new File(filePath);
		cargoForMission.transferTo(dest);

		// Getting file original Name
		File cargoForOtherMissionFileName = new File(cargoForOtherMission.getOriginalFilename());
		// Saving the file to destination
		filePath = cargoFilesPath + missionID + "_" + cargoForOtherMissionFileName;
		dest = new File(filePath);
		cargoForOtherMission.transferTo(dest);

		// Making file readable
		FileReader cargoForJourneyFileReader = new FileReader(cargoFilesPath + cargoForJourneyFileName);
		FileReader cargoForMissionFileReader = new FileReader(cargoFilesPath + cargoForMissionFileName);
		FileReader cargoForOtherMissionReader = new FileReader(cargoFilesPath + cargoForOtherMissionFileName);

		// Getting as json object of each file
		String completeObject = "{";
		completeObject += fileJsonReturner(cargoForJourneyFileReader, "cargoForJourney") + ",";
		completeObject += fileJsonReturner(cargoForMissionFileReader, "cargoForMission") + ",";
		completeObject += fileJsonReturner(cargoForOtherMissionReader, "cargoForOtherMission") + "}";
		logger.info(completeObject);
		return completeObject;

	}

//	converting csv to json 
	public String fileJsonReturner(FileReader cargoFileReader, String string) throws Exception {

		CSVParser cargoForJourneyCSV = new CSVParser(cargoFileReader,
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
//		{"cargoForJourney":[{"item":"20"},{"item":"20"}]}
		String jsoncargoObject = "\"" + string + "\":[";
		String buildcargoObject = "";
		for (CSVRecord csvRecord : cargoForJourneyCSV) {
			logger.info("inside loop");

			String item = csvRecord.get(0);
			String quantity = csvRecord.get(1);
			buildcargoObject += "{\"" + item + "\":" + "\"" + quantity + "\"},";

		}
		if (!buildcargoObject.toString().equalsIgnoreCase("")) {
			jsoncargoObject += buildcargoObject.substring(0, buildcargoObject.length() - 1) + "]";

		} else {
			jsoncargoObject += "]";
		}
		logger.info(jsoncargoObject);

		return jsoncargoObject;

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
