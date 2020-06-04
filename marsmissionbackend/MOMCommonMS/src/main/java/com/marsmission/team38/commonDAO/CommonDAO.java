package com.marsmission.team38.commonDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO {

	@Autowired
	private JdbcTemplate jdbc;
	private Log logger = LogFactory.getLog(this.getClass());

	public long addCountryDAO(Map<String, ?> props) {
		logger.info("in addCountryDAO");
		String sql = "insert into country(`country_name`) values(?)";

		String countryName = (String) (props.containsKey("countryName") ? props.get("countryName") : null);
		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, countryName);
					return ps;
				}
			};
			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final long countryID = holder.getKey().longValue();
			return countryID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public Map<String, Serializable> getCountryDetailsDAO(String countryID) {
		logger.info("in getCountryDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM country where (country_id = '" + countryID + "' or country_name= '" + countryID
					+ "')";

			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getCountryDetailsDAO error" + e);
			result.put("status", "failed");
			return result;
		}

	}

	public Map<String, Serializable> deleteCountryDetailsDAO(String countryID) {
		logger.info("in deleteCountryDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		String sql = "delete  FROM country where (country_id = '" + countryID + "' or country_name= '" + countryID
				+ "')";

		try {
			int resultCheck = jdbc.update(sql);
			if (resultCheck == 1) {
				logger.info("in deleteCountryDetailsDAO success");
				result.put("status", "success");
			}
			else {
				result.put("status", "failed");
				logger.info("no Country present with given id");
			}
			return result;
		} catch (Exception e) {
			
			result.put("status", "failed");
			logger.error("in deleteCountryDetailsDAO" + e);

			return result;
		}

	}

	public Map<String, Serializable> getShuttleDetailsDAO(String shuttleID) {
		// TODO Auto-generated method stub
		logger.info("in getShuttleDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM shuttle where (shuttle_id = '" + shuttleID + "' or shuttle_name= '" + shuttleID
					+ "')";

			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getShuttleDetailsDAO error" + e);
			result.put("status", "failed");
			return result;
		}
	}

	public long addLocationDAO(Map<String, ?> props) {
		// TODO Auto-generated method stub
		logger.info("in addLocationDAO");
		
		String sql = "insert into location(`location_north`, `location_east`)"
				+ " values(?,?)";

		
		String locationNorth = (String) (props.containsKey("locationNorth") ? props.get("locationNorth") : null);
		String locationEast = (String) (props.containsKey("locationEast") ? props.get("locationEast") : null);
		
		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					ps.setString(1, locationNorth);
					ps.setString(2, locationEast);
					
					return ps;
				}
			};
			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final long locationID = holder.getKey().longValue();
			return locationID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public Map<String, Serializable> getLocationDetailsDAO(String locationID) {
		// TODO Auto-generated method stub
		logger.info("in getLocationDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM location where (location_id = '" + locationID+"')";

			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getLocationDetailsDAO error " + e);
			result.put("status", "failed");
			return result;
		}
	}


	//Employee

	public long addEmployeeDAO(Map<String, ?> props) {
		// TODO Auto-generated method stub
		logger.info("in addEmployeeDAO");

		String sql = "insert into employee(`emp_title`, `emp_number`)"
				+ " values(?,?)";

		String employeeTitle = (String) (props.containsKey("employeeTitle") ? props.get("employeeTitle") : null);
		String employeeNumber = (String) (props.containsKey("employeeNumber") ? props.get("employeeNumber") : null);

		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

					ps.setString(1, employeeTitle);
					ps.setString(2, employeeNumber);

					return ps;
				}
			};
			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final long employeeID = holder.getKey().longValue();
			return employeeID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public Map<String, Serializable> getEmployeeDetailsDAO(String employeeID) {
		// TODO Auto-generated method stub
		logger.info("in getEmployeeDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM employee where (emp_id = '" + employeeID+"')";
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getEmployeeDetailsDAO error " + e);
			result.put("status", "failed");
			return result;
		}

	}

	public Map<String, Serializable> deleteEmployeeyDetailsDAO(String employeeID) {
		logger.info("in deleteEmployeeyDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		String sql = "delete  FROM employee where (emp_id = '" + employeeID + "' or emp_title = '" + employeeID
				+ "')";

		try {
			int resultCheck = jdbc.update(sql);
			if (resultCheck == 1) {
				logger.info("in deleteEmployeeyDetailsDAO success");
				result.put("status", "success");
			}
			else {
				result.put("status", "failed");
				logger.info("no Employee present with given id");
			}
			return result;
		} catch (Exception e) {

			result.put("status", "failed");
			logger.error("in deleteEmployeeyDetailsDAO" + e);

			return result;
		}

	}


	//Job

	public long addJobDAO(Map<String, ?> props) {
		logger.info("in addJobDAO");

		String sql = "insert into job(`job_title`, `job_no`)"
				+ " values(?,?)";

		String jobTitle = (String) (props.containsKey("jobTitle") ? props.get("jobTitle") : null);
		String jobNumber = (String) (props.containsKey("jobNumber") ? props.get("jobNumber") : null);

		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

					ps.setString(1, jobTitle);
					ps.setString(2, jobNumber);

					return ps;
				}
			};
			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final long jobID = holder.getKey().longValue();
			return jobID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}


	public Map<String, Serializable> getJobDetailsDAO(String jobID) {
		logger.info("in getJobDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM job where (job_id = '" + jobID +"')";
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getJobDetailsDAO error " + e);
			result.put("status", "failed");
			return result;
		}

	}

	public Map<String, Serializable> deleteJobDetailsDAO(String jobID) {
		logger.info("in deleteJobDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		String sql = "delete  FROM job where (job_id = '" + jobID + "' or job_title = '" + jobID
				+ "')";

		try {
			int resultCheck = jdbc.update(sql);
			if (resultCheck == 1) {
				logger.info("in deleteJobDetailsDAO success");
				result.put("status", "success");
			}
			else {
				result.put("status", "failed");
				logger.info("no Job present with given id");
			}
			return result;
		} catch (Exception e) {

			result.put("status", "failed");
			logger.error("in deleteJobDetailsDAO" + e);

			return result;
		}

	}

	//Cargo

	public long addCargoDAO(Map<String, ?> props) {
		logger.info("in addCargoDAO");

		String sql = "insert into cargo(`cargo_journey`, `cargo_mission`,`cargo_other_mission`)"
				+ " values(?,?,?)";

		String cargoJourney = (String) (props.containsKey("cargoJourney") ? props.get("cargoJourney") : null);
		String cargoMission = (String) (props.containsKey("cargoMission") ? props.get("cargoMission") : null);
		String cargoOtherMission = (String) (props.containsKey("cargoOtherMission") ? props.get("cargoOtherMission") : null);

		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

					ps.setString(1, cargoJourney);
					ps.setString(2, cargoMission);
					ps.setString(3, cargoOtherMission);

					return ps;
				}
			};
			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final long cargoID = holder.getKey().longValue();
			return cargoID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public Map<String, Serializable> getCargoDetailsDAO(String cargoID) {
		logger.info("in getCargoDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM cargo where (cargo_id = '" + cargoID +"')";
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getCargoDetailsDAO error " + e);
			result.put("status", "failed");
			return result;
		}

	}

	public Map<String, Serializable> deleteCargoDetailsDAO(String cargoID) {
		logger.info("in deleteCargoDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		String sql = "delete  FROM cargo where (cargo_id = '" + cargoID + "')";

		try {
			int resultCheck = jdbc.update(sql);
			if (resultCheck == 1) {
				logger.info("in deleteCargoDetailsDAO success");
				result.put("status", "success");
			}
			else {
				result.put("status", "failed");
				logger.info("no cargo present with given id");
			}
			return result;
		} catch (Exception e) {

			result.put("status", "failed");
			logger.error("in deleteCargoDetailsDAO" + e);

			return result;
		}

	}



}
