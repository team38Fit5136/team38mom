package com.marsmission.team38.commonDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;

/**
 * The Common DAO class to interact with Database
 */
@Repository
public class CommonDAO {

	@Autowired
	private JdbcTemplate jdbc;
	// logger variable to print logs
	private Log logger = LogFactory.getLog(this.getClass());

	// Method for adding country
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
			// returning countryID
			return countryID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	// Method for getting country details
	public Map<String, Serializable> getCountryDetailsDAO(String countryID) {
		logger.info("in getCountryDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();
		String sql = "SELECT * FROM country";
		logger.info("countryID" + countryID);
		try {
			if (!countryID.equalsIgnoreCase("null")) {
				sql += " where (country_id = '" + countryID + "' or country_name= '" + countryID + "')";
			}
//			logger.info("sql" + sql);
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForList(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getCountryDetailsDAO error" + e);
			result.put("status", "failed");
			return result;
		}

	}

	// Method for deleting Country
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
			} else {
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

	// MEthod for getting shuttle details
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

	// Method for adding location
	public long addLocationDAO(Map<String, ?> props) {
		// TODO Auto-generated method stub
		logger.info("in addLocationDAO");

		String sql = "insert into location(`location_north`, `location_east`)" + " values(?,?)";

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

	// Method for getting location
	public Map<String, Serializable> getLocationDetailsDAO(String locationID) {
		logger.info("in getCountryDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();
		String sql = "SELECT * FROM location ";
		logger.info("locationID" + locationID);
		try {
			if (!locationID.equalsIgnoreCase("null")) {
				sql += " where (location_id = '" + locationID + "'";
			}
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForList(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getLocationDetailsDAO error " + e);
			result.put("status", "failed");
			return result;
		}
	}

	// Employee

	// Method for adding employee
	public long addEmployeeDAO(ArrayList<Map<String, Object>> employment, long jobID) {
		// TODO Auto-generated method stub
		logger.info("in addEmployeeDAO");
		String values = "";
		// buliding insertion query for empyment related to same jobID
		for (Map<String, Object> childElement : employment) {
			values += " ('" + childElement.get("empTitle") + "'," + childElement.get("numberOfEmp") + "," + jobID
					+ "), ";
		}

		String sql = "insert into employee(`emp_title`, `emp_number`,`job_id`)" + " values"
				+ values.substring(0, values.length() - 2);
		logger.info(sql);
		try {
			int rsp = jdbc.update(sql);
			return rsp;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

	}

	// Method for getting employee
	public Map<String, Serializable> getEmployeeDetailsDAO(String employeeID) {
		// TODO Auto-generated method stub
		logger.info("in getEmployeeDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM employee where (emp_id = '" + employeeID + "')";
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getEmployeeDetailsDAO error " + e);
			result.put("status", "failed");
			return result;
		}

	}

	//// Method for deleting employee
	public Map<String, Serializable> deleteEmployeeyDetailsDAO(String employeeID) {
		logger.info("in deleteEmployeeyDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		String sql = "delete  FROM employee where (emp_id = '" + employeeID + "' or emp_title = '" + employeeID + "')";

		try {
			int resultCheck = jdbc.update(sql);
			if (resultCheck == 1) {
				logger.info("in deleteEmployeeyDetailsDAO success");
				result.put("status", "success");
			} else {
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

	// Job
	// Method for adding job
	public int addJobDAO(String jobName, String jobDesc) {
		logger.info("in addJobDAO");

		String sql = "insert into job(`job_title`, `job_desc`)" + " values(?,?)";

		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

					ps.setString(1, jobName);
					ps.setString(2, jobDesc);

					return ps;
				}
			};
			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final int jobID = holder.getKey().intValue();
			return jobID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	// Method for etting job details
	public Map<String, Serializable> getJobDetailsDAO(String jobID) {
		logger.info("in getJobDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM job where (job_id = '" + jobID + "')";
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getJobDetailsDAO error " + e);
			result.put("status", "failed");
			return result;
		}

	}

	// Method for deleting job
	public Map<String, Serializable> deleteJobDetailsDAO(int jobID) {
		logger.info("in deleteJobDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		String sql = "delete  FROM job where job_id = " + jobID;

		try {
			int resultCheck = jdbc.update(sql);
			if (resultCheck == 1) {
				logger.info("in deleteJobDetailsDAO success");
				result.put("status", "success");
			} else {
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

	// Cargo
	// Method for adding cargo
	
	// Method for getting cargo
	public Map<String, Serializable> getCargoDetailsDAO(String cargoID) {
		logger.info("in getCargoDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM cargo where (cargo_id = '" + cargoID + "')";
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getCargoDetailsDAO error " + e);
			result.put("status", "failed");
			return result;
		}

	}

	// Method for deleting cargo
	public Map<String, Serializable> deleteCargoDetailsDAO(String cargoID) {
		logger.info("in deleteCargoDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		String sql = "delete  FROM cargo where (cargo_id = '" + cargoID + "')";

		try {
			int resultCheck = jdbc.update(sql);
			if (resultCheck == 1) {
				logger.info("in deleteCargoDetailsDAO success");
				result.put("status", "success");
			} else {
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

	// Method for getting status
	public Map<String, Serializable> getStatusDetailsDAO(String statusID) {
		logger.info("in getStatusDetailsDAO");

		Map<String, Serializable> result = new HashMap<>();
		String sql = "SELECT * FROM status";
		logger.info("statusID" + statusID);
		try {
			if (!statusID.equalsIgnoreCase("null")) {
				sql += " where (status_id = '" + statusID + "'";
			}
//			logger.info("sql" + sql);
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForList(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getStatusDetailsDAO error" + e);
			result.put("status", "failed");
			return result;
		}

	}

}
