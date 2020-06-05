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
			@RequestParam(value = "countryID", required = false, defaultValue = "null") String countryID)
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

	@GetMapping("/status")
	public Map<String, Serializable> getStatusDetails(
			@RequestParam(value = "statusID", required = false, defaultValue = "null") String statusID)
			throws Exception {
		logger.info("in get getCountryDetails" + statusID);
		return commonService.getStatusDetailsService(statusID);
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

	//Employee

	@PostMapping("/employee")
	public Map<String, Serializable> addEmployee(@RequestBody Map<String, ?> props) {
		logger.info("in addEmployee");
		return commonService.addEmployeeService(props);
	}

	@GetMapping("/employee")
	public Map<String, Serializable> getEmployeeDetails(
			@RequestParam(value = "employeeID", required = true, defaultValue = "null") String employeeID){
		logger.info("in get getEmployeeDetails" + employeeID);
		return commonService.getEmployeeDetailsService(employeeID);
	}

	@DeleteMapping("/employee")
	public Map<String, Serializable> deleteEmployeeDetails(
			@RequestParam(value = "employeeID", required = true, defaultValue = "null") String employeeID){
		logger.info("in get deleteEmployeeDetails" + employeeID);
		return commonService.deleteEmployeeDetailsService(employeeID);
	}


	//Job

	@PostMapping("/job")
	public Map<String, Serializable> addJob(@RequestBody Map<String, ?> props){
		logger.info("in addJob");
		return commonService.addJobService(props);
	}

	@GetMapping("/job")
	public Map<String, Serializable> getJobDetails(
			@RequestParam(value = "jobID", required = true, defaultValue = "null") String jobID) {
		logger.info("in get getJobDetails" + jobID);
		return commonService.getJobDetailsService(jobID);
	}

	@DeleteMapping("/job")
	public Map<String, Serializable> deleteJobDetails(
			@RequestParam(value = "jobID", required = true, defaultValue = "null") String jobID){
		logger.info("in get deleteJobDetails" + jobID);
		return commonService.deleteJobDetailsService(jobID);
	}


	//Cargo

	@PostMapping("/cargo")
	public Map<String, Serializable> addCargo(@RequestBody Map<String, ?> props){
		logger.info("in addCargo");
		return commonService.addCargoService(props);
	}


	@GetMapping("/cargo")
	public Map<String, Serializable> getCargoDetails(
			@RequestParam(value = "cargoID", required = true, defaultValue = "null") String cargoID){
		logger.info("in get getCargoDetails" + cargoID);
		return commonService.getCargoDetailsService(cargoID);
	}

	@DeleteMapping("/cargo")
	public Map<String, Serializable> deleteCargoDetails(
			@RequestParam(value = "cargoID", required = true, defaultValue = "null") String cargoID) {
		logger.info("in deleteCargoDetails" + cargoID);
		return commonService.deleteCargoDetailsService(cargoID);
	}

}