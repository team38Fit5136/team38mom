package com.marsmission.team38.commonController;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marsmission.team38.commonService.CommonServcie;

@RestController
@RequestMapping("/mom/mission")
@CrossOrigin(origins = "http://localhost:3000")
public class CommonController {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private CommonServcie commonService;

	@PostMapping("/country")
	public Map<String, Serializable> addCountry(@RequestBody Map<String, ?> props){
		logger.info("in addCountry");

		return commonService.addCountryService(props);

	}

	@GetMapping("/country")
	public Map<String, Serializable> getCountryDetails(
			@RequestParam(value = "countryID", required = true, defaultValue = "null") String countryID)
			throws Exception {
		logger.info("in get getCountryDetails" + countryID);
		return commonService.getCountryDetailsService(countryID);
	}

	@DeleteMapping("/country")
	public Map<String, Serializable> deleteCountryDetails(
			@RequestParam(value = "countryID", required = true, defaultValue = "null") String countryID)
			throws Exception {

		logger.info("in get getCountryDetails" + countryID);

		return commonService.deleteCountryDetailsService(countryID);
	}

	@GetMapping("/shuttle")
	public Map<String, Serializable> getShuttleDetails(
			@RequestParam(value = "shuttleID", required = true, defaultValue = "null") String shuttleID)
			throws Exception {
		logger.info("in get getShuttleDetails" + shuttleID);
		return commonService.getShuttleDetailsService(shuttleID);
	}
	
	@PostMapping("/location")
	public Map<String, Serializable> addLocation(@RequestBody Map<String, ?> props){
		logger.info("in addLocation");

		return commonService.addLocationService(props);

	}

	@GetMapping("/location")
	public Map<String, Serializable> getLocationDetails(
			@RequestParam(value = "locationID", required = true, defaultValue = "null") String locationID)
			throws Exception {
		logger.info("in get getLocationDetails" + locationID);
		return commonService.getLocationDetailsService(locationID);
	}

}