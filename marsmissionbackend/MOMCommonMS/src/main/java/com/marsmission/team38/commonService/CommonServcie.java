package com.marsmission.team38.commonService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marsmission.team38.commonDAO.CommonDAO;

@Service
public class CommonServcie {

	// logger variable to print logs
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private CommonDAO commonDAO;

	// Method for creating country
	public Map<String, Serializable> addCountryService(Map<String, ?> props) {

		Map<String, Serializable> result = new HashMap<>();
		logger.info("country" + props);

		long countryID = 0;

		String countryName = (String) (props.containsKey("countryName") ? props.get("countryName") : null);

		if (countryName == null || (countryName.toString().equalsIgnoreCase(""))) {
			logger.warn("Country Name is mandatory to add the country");
			result.put("responseMsg", "Country Name is mandatory to add the country");
			result.put("status", "failed");
			return result;
		}

		try

		{
			countryID = commonDAO.addCountryDAO(props);
			if (countryID != 0) {
				logger.info("added successfully");
				result.put("countryID ", countryID);
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

	// Method for getting country details using countryID or countryName
	public Map<String, Serializable> getCountryDetailsService(String countryID) {
		logger.info("in getCountryDetailsService");

		Map<String, Serializable> result = commonDAO.getCountryDetailsDAO(countryID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" Country does not exist with given credentials");
			result.put("responseMsg", "Country does not exist with given credentials");
		}
		return result;
	}

	// Method for deleting country
	public Map<String, Serializable> deleteCountryDetailsService(String countryID) {
		logger.info("in updateUserDetailsService ");
		Map<String, Serializable> result = commonDAO.deleteCountryDetailsDAO(countryID);
		logger.info("---------" + result);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("failed to delete with given credentials");
			result.put("responseMsg", "failed to delete with given credentials");
		} else {
			logger.info("successfully delete with given credentials");
			result.put("responseMsg", "successfully deleted with given credentials");
		}
		return result;

	}

	// Method for getting shuttle details
	public Map<String, Serializable> getShuttleDetailsService(String shuttleID) {
		// TODO Auto-generated method stub
		logger.info("in getShuttleDetailsService");

		Map<String, Serializable> result = commonDAO.getShuttleDetailsDAO(shuttleID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" Shuttle does not exist with given credentials");
			result.put("responseMsg", "Shuttle does not exist with given credentials");
		}
		return result;

	}

	// Method for adding location
	public Map<String, Serializable> addLocationService(Map<String, ?> props) {
		// TODO Auto-generated method stub
		Map<String, Serializable> result = new HashMap<>();
		logger.info("location" + props);

		long locationID = 0;

		String locationNorth = (String) (props.containsKey("locationNorth") ? props.get("locationNorth") : null);
		String locationEast = (String) (props.containsKey("locationEast") ? props.get("locationEast") : null);

		if ((locationNorth == null || (locationNorth.toString().equalsIgnoreCase("")))
				|| (locationEast == null || (locationEast.toString().equalsIgnoreCase("")))) {
			logger.warn("location North and East are mandatory to add the location");
			result.put("responseMsg", "Location North and East are mandatory to add the location");
			result.put("status", "failed");
			return result;
		}
		try {
			locationID = commonDAO.addLocationDAO(props);
			if (locationID != 0) {
				logger.info("added successfully");
				result.put("locationID ", locationID);
				result.put("status", "success");
				result.put("responseMsg", "successfully inserted");
			} else {
				logger.error("Entered Details are not correct ");
				result.put("status", "failed");
				result.put("responseMsg", "Enter Details are not correct ");
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

	// Method for getting location
	public Map<String, Serializable> getLocationDetailsService(String locationID) {
		// TODO Auto-generated method stub
		logger.info("in getLocationDetailsService");

		Map<String, Serializable> result = commonDAO.getLocationDetailsDAO(locationID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" location does not exist with given credentials");
			result.put("responseMsg", "Location does not exist with given credentials");
		}
		return result;
	}

	// Method for getting employee
	public Map<String, Serializable> getEmployeeDetailsService(String employeeID) {
		logger.info("in getEmployeeDetailsService");

		Map<String, Serializable> result = commonDAO.getEmployeeDetailsDAO(employeeID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" Employee does not exist with given credentials");
			result.put("responseMsg", "Employee does not exist with given credentials");
		}
		return result;
	}

	// Method for deleting employee
	public Map<String, Serializable> deleteEmployeeDetailsService(String employeeID) {
		logger.info("in deleteEmployeeDetailsService ");
		Map<String, Serializable> result = commonDAO.deleteEmployeeyDetailsDAO(employeeID);
		logger.info("---------" + result);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("failed to delete with given credentials");
			result.put("responseMsg", "failed to delete with given credentials");
		} else {
			logger.info("successfully delete with given credentials");
			result.put("responseMsg", "successfully deleted with given credentials");
		}
		return result;

	}

	// Job
	// Method for adding job
	public Map<String, Serializable> addJobService(Map<String, ?> props) {
		// TODO Auto-generated method stub
		Map<String, Serializable> result = new HashMap<>();
		logger.info("Job" + props);

		int jobID = 0;

		String jobName = (String) (props.containsKey("jobName") ? props.get("jobName") : null);
		String jobDesc = (String) (props.containsKey("jobDesc") ? props.get("jobDesc") : null);
		ArrayList<Map<String, Object>> employment = (ArrayList<Map<String, Object>>) (props.containsKey("employment")
				? props.get("employment")
				: null);
		logger.info("jobName" + jobName + "jobDesc" + jobDesc + "employment" + employment);
		try {
			jobID = commonDAO.addJobDAO(jobName, jobDesc);
			if (jobID != 0) {
				logger.info("added successfully");
				if (employment != null && !(employment.isEmpty())) {
					Long resp = commonDAO.addEmployeeDAO(employment, jobID);
					if (resp != 0) {
						result.put("jobID ", jobID);
						result.put("status", "success");
						result.put("responseMsg", "successfully inserted");
					} else {
						commonDAO.deleteJobDetailsDAO(jobID);

					}
				} else {
					if (jobID != 0) {
						result.put("jobID ", jobID);
						result.put("status", "success");
						result.put("responseMsg", "successfully inserted");
					}
				}
			} else {
				logger.error("Entered Details are not correct ");
				result.put("status", "failed");
				result.put("responseMsg", "Enter Details are not correct ");
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

	// Method for getting job
	public Map<String, Serializable> getJobDetailsService(String jobID) {
		logger.info("in getJobDetailsService");

		Map<String, Serializable> result = commonDAO.getJobDetailsDAO(jobID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" Job does not exist with given credentials");
			result.put("responseMsg", "Job does not exist with given credentials");
		}
		return result;
	}

	// Method for deleting job
	public Map<String, Serializable> deleteJobDetailsService(int jobID) {
		logger.info("in deleteJobDetailsService ");
		Map<String, Serializable> result = commonDAO.deleteJobDetailsDAO(jobID);
		logger.info("---------" + result);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("failed to delete with given credentials");
			result.put("responseMsg", "failed to delete with given credentials");
		} else {
			logger.info("successfully delete with given credentials");
			result.put("responseMsg", "successfully deleted with given credentials");
		}
		return result;

	}

	// Cargo
	// Method for adding cargo
	public String addCargoService(MultipartFile file, String fileType) {
		logger.info("addCargoService " + fileType);

		return null;
	}

	public Map<String, Serializable> addCargoService(Map<String, ?> props) {
		Map<String, Serializable> result = new HashMap<>();

		long cargoId = 0;

		String cargoJourney = (String) (props.containsKey("cargoJourney") ? props.get("cargoJourney") : null);
		String cargoMission = (String) (props.containsKey("cargoMission") ? props.get("cargoMission") : null);
		String cargoOtherMission = (String) (props.containsKey("cargoOtherMission") ? props.get("cargoOtherMission")
				: null);

		if ((cargoJourney == null || (cargoJourney.toString().equalsIgnoreCase("")))
				|| (cargoMission == null || (cargoMission.toString().equalsIgnoreCase("")))
				|| (cargoOtherMission == null || (cargoOtherMission.toString().equalsIgnoreCase("")))) {
			logger.warn("cargo Journey, cargoMission and cargoOtherMission are mandatory to add the cargo");
			result.put("responseMsg",
					"cargo Journey, cargoMission and cargoOtherMission are mandatory to add the cargo");
			result.put("status", "failed");
			return result;
		}
		try {
			cargoId = commonDAO.addCargoDAO(props);
			if (cargoId != 0) {
				logger.info("added successfully");
				result.put("cargoId ", cargoId);
				result.put("status", "success");
				result.put("responseMsg", "successfully inserted");
			} else {
				logger.error("Entered Details are not correct ");
				result.put("status", "failed");
				result.put("responseMsg", "Enter Details are not correct ");
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

	// Method for getting cargo
	public Map<String, Serializable> getCargoDetailsService(String cargoID) {
		logger.info("in getCargoDetailsService");

		Map<String, Serializable> result = commonDAO.getCargoDetailsDAO(cargoID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" Cargo does not exist with given credentials");
			result.put("responseMsg", "Cargo does not exist with given credentials");
		}
		return result;
	}

	// Method for delete cargo
	public Map<String, Serializable> deleteCargoDetailsService(String cargoID) {
		logger.info("in deleteCargoDetailsService ");
		Map<String, Serializable> result = commonDAO.deleteCargoDetailsDAO(cargoID);
		logger.info("---------" + result);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("failed to delete with given credentials");
			result.put("responseMsg", "failed to delete with given credentials");
		} else {
			logger.info("successfully delete with given credentials");
			result.put("responseMsg", "successfully deleted with given credentials");
		}
		return result;

	}

	// Method for getting country details using countryID or countryName

	public Map<String, Serializable> getStatusDetailsService(String statusID) {
		logger.info("in getStatusDetailsService");

		Map<String, Serializable> result = commonDAO.getStatusDetailsDAO(statusID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" Country does not exist with given credentials");
			result.put("responseMsg", "Country does not exist with given credentials");
		}
		return result;

	}

}