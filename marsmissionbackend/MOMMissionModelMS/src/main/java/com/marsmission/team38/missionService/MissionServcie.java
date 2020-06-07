package com.marsmission.team38.missionService;

import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsmission.team38.missionDAO.MissionDAO;

@Service
public class MissionServcie {

	// logger variable to print logs
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private MissionDAO missionDAO;

	@Autowired
	private HttpServletRequest request;

	ObjectMapper mapper = new ObjectMapper();

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
		Integer coordinatorID = (body.containsKey("coordinatorID")
				? body.get("coordinatorID").toString().equalsIgnoreCase("") ? 1
						: Integer.parseInt((String) body.get("coordinatorID"))
				: 1);

		Integer locationID = (body.containsKey("locationID")
				? body.get("locationID").toString().equalsIgnoreCase("") ? 1
						: Integer.parseInt((String) body.get("locationID"))
				: 1);
		Integer statusID = (body.containsKey("statusID") ? body.get("statusID").toString().equalsIgnoreCase("") ? 1
				: Integer.parseInt((String) body.get("statusID")) : 1);

		Integer countryOrigin = (body.containsKey("countryOrigin")
				? body.get("countryOrigin").toString().equalsIgnoreCase("") ? 1
						: Integer.parseInt((String) body.get("countryOrigin"))
				: 1);

		Integer duration = (body.containsKey("duration") ? body.get("duration").toString().equalsIgnoreCase("") ? 0
				: Integer.parseInt((String) body.get("duration")) : 0);

		List<?> countryAllowed = (List<?>) (body.containsKey("countryAllowed") ? body.get("countryAllowed") : null);// array
		List<?> jobID = (List<?>) (body.containsKey("jobID") ? body.get("jobID") : null);// array

		String launchDate = (String) (body.containsKey("launchDate") ? body.get("launchDate") : null);
		String missionDetails = (String) (body.containsKey("missionDetails") ? body.get("missionDetails") : null);
		String missionName = (String) (body.containsKey("missionName") ? body.get("missionName") : null);

		if (coordinatorIDMandatory) {
			if (coordinatorID == 1) {
				logger.warn("coordinatorID is mandatory for the mission");
				result.put("responseMsg", "coordinatorID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (statusIDMandatory) {
			if (statusID == 1) {
				logger.warn("Status ID is mandatory for the mission");
				result.put("responseMsg", "Status ID is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}
		if (countryOriginMandatory) {
			if (countryOrigin == 1) {
				logger.warn("Country of Origin is mandatory for the mission");
				result.put("responseMsg", "Country of Origin is mandatory for the mission");
				result.put("status", "failed");
				return result;
			}
		}

		if (durationMandatory) {
			if (duration == 0) {
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
		FileReader cargoForJourneyFileReader = new FileReader(
				cargoFilesPath + missionID + "_" + cargoForJourneyFileName);
		FileReader cargoForMissionFileReader = new FileReader(
				cargoFilesPath + missionID + "_" + cargoForMissionFileName);
		FileReader cargoForOtherMissionReader = new FileReader(
				cargoFilesPath + missionID + "_" + cargoForOtherMissionFileName);

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

	// Method for getting mission details without using missionID or missionName
	public Map<String, Serializable> getMissionDetailsService(String missionID) {
		logger.info("in getMissionDetailsService");

		Map<String, Serializable> result = (Map<String, Serializable>) missionDAO.getMissiondetailsDAOAll(missionID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" mission does not exist with given credentials");
			result.put("responseMsg", "mission doesnot exist with given credentials");
		}
		return result;
	}

// for mission to shuttle getting mission details with using missionID or missionName
	public Map<String, ?> getMissionDetailsshuttle(String missionID) {
		logger.info("in getMissionDetailsService");

		Map<String, Serializable> result = missionDAO.getMissiondetailsDAO(missionID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" mission does not have any cargo for this please provide cargo information");
			result.put("responseMsg", "mission does not exist with given credentials");
			return result;
		}
//		eachCargoQuantiy(result);
//		logger.info(eachCargoQuantiy(result));
		Map<String, Object> resp = new HashMap<String, Object>();
		String jsonInString = eachCargoQuantiy(result).toString().replaceAll("\\\\", "");
		try {
			resp = mapper.readValue(jsonInString, new TypeReference<Map<String, Object>>() {
			});
			mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.put("responseMsg", (Serializable) resp);
		logger.info("--------------" + result);
		return result;
	}

	public JSONObject eachCargoQuantiy(Map<String, Serializable> result) {
		JSONObject obj = null;
		try {
			obj = new JSONObject(result.get("responseMsg").toString());

			JSONObject obj1 = obj.getJSONObject("cargo");

			JSONArray cargoForJourneyQuant = obj1.getJSONArray("cargoForJourney");
			int cargoForJourneyQuantity = cargoQuantiy(cargoForJourneyQuant);
			obj.put("cargoForJourneyQuantityt", cargoForJourneyQuantity);

			JSONArray cargoForMissionQuant = obj1.getJSONArray("cargoForMission");
			int cargoForMissionQuantity = cargoQuantiy(cargoForMissionQuant);
			obj.put("cargoForMissionQuantity", cargoForMissionQuantity);

			JSONArray cargoForOtherMissionQuant = obj1.getJSONArray("cargoForOtherMission");
			int cargoForOtherMissionQuantity = cargoQuantiy(cargoForOtherMissionQuant);
			obj.put("cargoForOtherMissionQuantity", cargoForOtherMissionQuantity);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	public int cargoQuantiy(JSONArray cargoForJourneyQuant) {
		int total = 0;
		try {
			for (int i = 0; i < cargoForJourneyQuant.length(); i++) {
				JSONObject objects;
				objects = cargoForJourneyQuant.getJSONObject(i);
				Iterator key = objects.keys();
				while (key.hasNext()) {
					String k = key.next().toString();
					total += Integer.parseInt(objects.getString(k));
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;

	}

	// Method for updating mission details using missionID or missionName
	public Map<String, Serializable> updateMissionDetailsService(String missionID, Map<String, ?> props) {
		logger.info("in updateMissionDetailsService ");
		// updating mission details
		Map<String, Serializable> result = missionDAO.updatedetailsDAO(missionID, props);

		return result;
	}
}
