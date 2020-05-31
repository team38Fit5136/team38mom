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

}