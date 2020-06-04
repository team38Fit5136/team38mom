package com.marsmission.team38.commonService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import com.marsmission.team38.commonDAO.CommonDAO;

@Service
public class CommonServcie {

	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private CommonDAO commonDAO;

	// Method for creating country

	public Map<String, Serializable> addCountryService(Map<String, ?> props) {

		Map<String, Serializable> result = new HashMap<>();
		logger.info("country" + props);

		long countryID = 0;

		String countryName = (String) (props.containsKey("countryName") ? props.get("countryName") : null);

		if (countryName == null || !(countryName.toString().equalsIgnoreCase(""))) {
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

	public Map<String, Serializable> deleteCountryDetailsService(String countryID) {
		logger.info("in updateUserDetailsService ");
		Map<String, Serializable> result = commonDAO.deleteCountryDetailsDAO(countryID);
		logger.info("---------"+result);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("failed to delete with given credentials");
			result.put("responseMsg", "failed to delete with given credentials");
		} else {
			logger.info("successfully delete with given credentials");
			result.put("responseMsg", "successfully deleted with given credentials");
		}
		return result;

	}

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

	public Map<String, Serializable> addLocationService(Map<String, ?> props) {
		// TODO Auto-generated method stub
		Map<String, Serializable> result = new HashMap<>();
		logger.info("location" + props);

		long locationID = 0;

		String locationNorth = (String) (props.containsKey("locationNorth") ? props.get("locationNorth") : null);
		String locationEast = (String) (props.containsKey("locationEast") ? props.get("locationEast") : null);

		if ((locationNorth == null || (locationNorth.toString().equalsIgnoreCase(""))) 
				||( locationEast == null || (locationEast.toString().equalsIgnoreCase("")))) {
			logger.warn("location North and East are mandatory to add the location");
			result.put("responseMsg", "Location North and East are mandatory to add the location");
			result.put("status", "failed");
			return result;
		}
		try
		{
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

	//Employee

	public Map<String, Serializable> addEmployeeService(Map<String, ?> props) {
		// TODO Auto-generated method stub
		Map<String, Serializable> result = new HashMap<>();
		logger.info("Employee" + props);

		long employeeID = 0;

		String employeeTitle = (String) (props.containsKey("employeeTitle") ? props.get("employeeTitle") : null);
		String employeeNumber = (String) (props.containsKey("employeeNumber") ? props.get("employeeNumber") : null);

		if ((employeeTitle == null || (employeeTitle.toString().equalsIgnoreCase("")))
				||( employeeNumber == null || (employeeNumber.toString().equalsIgnoreCase("")))) {
			logger.warn("Employee Title and Employee Number are mandatory to add the employee");
			result.put("responseMsg", "Employee Title and Employee Number are mandatory to add the employee");
			result.put("status", "failed");
			return result;
		}
		try
		{
			employeeID = commonDAO.addEmployeeDAO(props);
			if (employeeID != 0) {
				logger.info("added successfully");
				result.put("employeeID ", employeeID);
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


	public Map<String, Serializable> getEmployeeDetailsService(String employeeID) {
		logger.info("in getEmployeeDetailsService");

		Map<String, Serializable> result = commonDAO.getEmployeeDetailsDAO(employeeID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" Employee does not exist with given credentials");
			result.put("responseMsg", "Employee does not exist with given credentials");
		}
		return result;
	}

	public Map<String, Serializable> deleteEmployeeDetailsService(String employeeID) {
		logger.info("in deleteEmployeeDetailsService ");
		Map<String, Serializable> result = commonDAO.deleteEmployeeyDetailsDAO(employeeID);
		logger.info("---------"+result);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("failed to delete with given credentials");
			result.put("responseMsg", "failed to delete with given credentials");
		} else {
			logger.info("successfully delete with given credentials");
			result.put("responseMsg", "successfully deleted with given credentials");
		}
		return result;

	}

	//Job

	public Map<String, Serializable> addJobService(Map<String, ?> props) {
		// TODO Auto-generated method stub
		Map<String, Serializable> result = new HashMap<>();
		logger.info("Job" + props);

		long jobID = 0;

		String jobTitle = (String) (props.containsKey("jobTitle") ? props.get("jobTitle") : null);
		String jobNumber = (String) (props.containsKey("jobNumber") ? props.get("jobNumber") : null);

		if ((jobTitle == null || (jobTitle.toString().equalsIgnoreCase("")))
				||( jobNumber == null || (jobNumber.toString().equalsIgnoreCase("")))) {
			logger.warn("Job Title and Job Number are mandatory to add the job");
			result.put("responseMsg", "Job Title and Job Number are mandatory to add the job");
			result.put("status", "failed");
			return result;
		}
		try
		{
			jobID = commonDAO.addJobDAO(props);
			if (jobID != 0) {
				logger.info("added successfully");
				result.put("jobID ", jobID);
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

	public Map<String, Serializable> getJobDetailsService(String jobID) {
		logger.info("in getJobDetailsService");

		Map<String, Serializable> result = commonDAO.getJobDetailsDAO(jobID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" Job does not exist with given credentials");
			result.put("responseMsg", "Job does not exist with given credentials");
		}
		return result;
	}

	public Map<String, Serializable> deleteJobDetailsService(String jobID) {
		logger.info("in deleteJobDetailsService ");
		Map<String, Serializable> result = commonDAO.deleteJobDetailsDAO(jobID);
		logger.info("---------"+result);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("failed to delete with given credentials");
			result.put("responseMsg", "failed to delete with given credentials");
		} else {
			logger.info("successfully delete with given credentials");
			result.put("responseMsg", "successfully deleted with given credentials");
		}
		return result;

	}

	//Cargo

	public Map<String, Serializable> addCargoService(Map<String, ?> props) {
		Map<String, Serializable> result = new HashMap<>();
		logger.info("Cargo" + props);

		long cargoId = 0;

		String cargoJourney = (String) (props.containsKey("cargoJourney") ? props.get("cargoJourney") : null);
		String cargoMission = (String) (props.containsKey("cargoMission") ? props.get("cargoMission") : null);
		String cargoOtherMission = (String) (props.containsKey("cargoOtherMission") ? props.get("cargoOtherMission") : null);


		if ((cargoJourney == null || (cargoJourney.toString().equalsIgnoreCase("")))
				||( cargoMission == null || (cargoMission.toString().equalsIgnoreCase("")))
				||( cargoOtherMission == null || (cargoOtherMission.toString().equalsIgnoreCase("")))) {
			logger.warn("cargo Journey, cargoMission and cargoOtherMission are mandatory to add the cargo");
			result.put("responseMsg", "cargo Journey, cargoMission and cargoOtherMission are mandatory to add the cargo");
			result.put("status", "failed");
			return result;
		}
		try
		{
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


	public Map<String, Serializable> getCargoDetailsService(String cargoID) {
		logger.info("in getCargoDetailsService");

		Map<String, Serializable> result = commonDAO.getCargoDetailsDAO(cargoID);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error(" Cargo does not exist with given credentials");
			result.put("responseMsg", "Cargo does not exist with given credentials");
		}
		return result;
	}

	public Map<String, Serializable> deleteCargoDetailsService(String cargoID) {
		logger.info("in deleteCargoDetailsService ");
		Map<String, Serializable> result = commonDAO.deleteCargoDetailsDAO(cargoID);
		logger.info("---------"+result);
		if (result.get("status").toString().equalsIgnoreCase("failed")) {
			logger.error("failed to delete with given credentials");
			result.put("responseMsg", "failed to delete with given credentials");
		} else {
			logger.info("successfully delete with given credentials");
			result.put("responseMsg", "successfully deleted with given credentials");
		}
		return result;

	}

}