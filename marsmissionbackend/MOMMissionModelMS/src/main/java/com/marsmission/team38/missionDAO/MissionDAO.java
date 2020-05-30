package com.marsmission.team38.missionDAO;

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
public class MissionDAO {

	@Autowired
	private JdbcTemplate jdbc;
	private static Log logger = LogFactory.getLog(MissionDAO.class);

	public long addMissionDAO(Map<String, ?> props) {
		logger.info("in addMissionDAO");
		String sql = "insert into mission_details(`cargo_id`, `coordinator_id`, `country_allowed`, `country_origin`, `duration`, `emp_id`, `job_id`, `launch_date`, `location_id`, `mission_details`, `mission_name`, `shuttle_id`, `status_id`)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		int cargoID = (int) (props.containsKey("cargoID") ? props.get("cargoID") : 0);
		int coordinatorID = (int) (props.containsKey("coordinatorID") ? props.get("coordinatorID") : 0);
		int empID = (int) (props.containsKey("empID") ? props.get("empID") : 0);
		int jobID = (int) (props.containsKey("jobID") ? (int) props.get("jobID") : 0);
		int locationID = (int) (props.containsKey("locationID") ? props.get("locationID") : 0);
		int shuttleID = (int) (props.containsKey("shuttleID") ? props.get("shuttleID") : 0);
		int statusID = (int) (props.containsKey("statusID") ? props.get("statusID") : 1);
		int countryOrigin = (int) (props.containsKey("countryOrigin") ? props.get("countryOrigin") : 0);
		
		String countryAllowed = (String) (props.containsKey("countryAllowed") ? props.get("countryAllowed") : null);// Json
		String duration = (String) (props.containsKey("duration") ? props.get("duration") : null);
		String launchDate = (String) (props.containsKey("launchDate") ? props.get("launchDate") : null);
		String missionDetails = (String) (props.containsKey("missionDetails") ? props.get("missionDetails") : null);
		String missionName = (String) (props.containsKey("missionName") ? props.get("missionName") : null);
		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, cargoID);
					ps.setInt(2, coordinatorID);
					ps.setString(3, countryAllowed);
					ps.setInt(4, countryOrigin);
					ps.setString(5, duration);
					ps.setInt(6, empID);
					ps.setInt(7, jobID);
					ps.setString(8, launchDate);
					ps.setInt(9, locationID);
					ps.setString(10, missionDetails);
					ps.setString(11, missionName);
					ps.setInt(12, shuttleID);
					ps.setInt(13, statusID);
					return ps;
				}
			};
			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final long missionID = holder.getKey().longValue();
			return missionID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public Map<String, Serializable> getMissiondetailsDAO(String missionID) {
		logger.info("in getMissiondetailsDAO");

		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT * FROM mission_details where (mission_id = '" + missionID + "' or mission_name= '"
					+ missionID + "')";

			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getMissiondetailsDAO error" + e);
			result.put("status", "failed");
			return result;
		}
	}
}
