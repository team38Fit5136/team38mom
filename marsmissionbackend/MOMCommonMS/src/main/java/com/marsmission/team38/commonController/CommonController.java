package com.marsmission.team38.commonController;

import java.io.Serializable;
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
import org.springframework.web.multipart.MultipartFile;

import com.marsmission.team38.commonService.CommonServcie;

/**
 * The Rest controller starting from defined port in microservices.properties
 * Default path for request mapping in commons controller
 */
@RestController
@RequestMapping("/mom/mission")
//@CrossOrigin(origins = "http://localhost:3000")
public class CommonController {

	//logger variable to print logs
	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private CommonServcie commonService;

	//api call for adding country
	@PostMapping("/country")
	public Map<String, Serializable> addCountry(@RequestBody Map<String, ?> props){
		logger.info("in addCountry");

		return commonService.addCountryService(props);

	}

	//api call for getting countries
	@GetMapping("/country")
	public Map<String, Serializable> getCountryDetails(
			@RequestParam(value = "countryID", required = false, defaultValue = "null") String countryID)
			throws Exception {
		
		logger.info("in get getCountryDetails" + countryID); //log info for displaying on console as well as in log file, currently in getCountryDetails.
		
		return commonService.getCountryDetailsService(countryID);
	}

	//api call for deleting country
	@DeleteMapping("/country")
	public Map<String, Serializable> deleteCountryDetails(
			@RequestParam(value = "countryID", required = true, defaultValue = "null") String countryID)
			throws Exception {

		logger.info("in get getCountryDetails" + countryID); //log info for displaying on console as well as in log file, currently in deleteCountryDetails

		return commonService.deleteCountryDetailsService(countryID);
	}

	//api call for getting status
	@GetMapping("/status")
	public Map<String, Serializable> getStatusDetails(
			@RequestParam(value = "statusID", required = false, defaultValue = "null") String statusID)
			throws Exception {
		logger.info("in get getCountryDetails" + statusID); //log info for displaying on console as well as in log file, currently in getStatusDetails
		return commonService.getStatusDetailsService(statusID);
	}
	
	//api call for getting shuttle
	@GetMapping("/shuttle")
	public Map<String, Serializable> getShuttleDetails(
			@RequestParam(value = "shuttleID", required = true, defaultValue = "null") String shuttleID)
			throws Exception {
		logger.info("in get getShuttleDetails" + shuttleID); //log info for displaying on console as well as in log file, currently in getShuttleDetails
		return commonService.getShuttleDetailsService(shuttleID);
	}
	
	//api call for adding location
	@PostMapping("/location")
	public Map<String, Serializable> addLocation(@RequestBody Map<String, ?> props){
		logger.info("in addLocation"); //log info for displaying on console as well as in log file, currently in addLocation

		return commonService.addLocationService(props);

	}

	//api call for getting location 
	@GetMapping("/location")
	public Map<String, Serializable> getLocationDetails(
			@RequestParam(value = "locationID", required = false, defaultValue = "null") String locationID)
			throws Exception {
		logger.info("in get getLocationDetails" + locationID); //log info for displaying on console as well as in log file, currently in getLocationDetails
		return commonService.getLocationDetailsService(locationID);
	}


	//api call for getting employee
	@GetMapping("/employee")
	public Map<String, Serializable> getEmployeeDetails(
			@RequestParam(value = "employeeID", required = true, defaultValue = "null") String employeeID){
		logger.info("in get getEmployeeDetails" + employeeID); //log info for displaying on console as well as in log file, currently in getEmployeeDetails
		return commonService.getEmployeeDetailsService(employeeID);
	}

	//api call for deleting employee
	@DeleteMapping("/employee")
	public Map<String, Serializable> deleteEmployeeDetails(
			@RequestParam(value = "employeeID", required = true, defaultValue = "null") String employeeID){
		logger.info("in get deleteEmployeeDetails" + employeeID); //log info for displaying on console as well as in log file, currently in deleteEmployeeDetails
		return commonService.deleteEmployeeDetailsService(employeeID);
	}


	//Job

	//api call for adding Job
	@PostMapping("/job")
	public Map<String, Serializable> addJob(@RequestBody Map<String, ?> props){
		logger.info("in addJob"); //log info for displaying on console as well as in log file, currently in addJob
		return commonService.addJobService(props);
	}

	//api call for getting Job details
	@GetMapping("/job")
	public Map<String, Serializable> getJobDetails(
			@RequestParam(value = "jobID", required = true, defaultValue = "null") String jobID) {
		logger.info("in get getJobDetails" + jobID); //log info for displaying on console as well as in log file, currently in getJobDetails
		return commonService.getJobDetailsService(jobID);
	}

	//api call for deleting Job 
	@DeleteMapping("/job")
	public Map<String, Serializable> deleteJobDetails(
			@RequestParam(value = "jobID", required = true, defaultValue = "null") Integer jobID){
		logger.info("in get deleteJobDetails" + jobID); //log info for displaying on console as well as in log file, currently in deleteJobDetails
		return commonService.deleteJobDetailsService(jobID);
	}


	//Cargo

	//api call for adding cargo
	

    @PostMapping("/upload")
    public String addCargo(@RequestParam("file") MultipartFile file, 
                         @RequestParam(value = "fileType", required = true, defaultValue = "null") String fileType){
		logger.info("in addCargo"); //log info for displaying on console as well as in log file, currently in addCargo
		return commonService.addCargoService(file,fileType);
	}

	
	//api call for getting cargo
	@GetMapping("/cargo")
	public Map<String, Serializable> getCargoDetails(
			@RequestParam(value = "cargoID", required = true, defaultValue = "null") String cargoID){
		logger.info("in get getCargoDetails" + cargoID); //log info for displaying on console as well as in log file, currently in getCargoDetails
		return commonService.getCargoDetailsService(cargoID);
	}

	//api call for deleting cargo
	@DeleteMapping("/cargo")
	public Map<String, Serializable> deleteCargoDetails(
			@RequestParam(value = "cargoID", required = true, defaultValue = "null") String cargoID) {
		logger.info("in deleteCargoDetails" + cargoID); //log info for displaying on console as well as in log file, currently in deleteCargoDetails
		return commonService.deleteCargoDetailsService(cargoID);
	}

}