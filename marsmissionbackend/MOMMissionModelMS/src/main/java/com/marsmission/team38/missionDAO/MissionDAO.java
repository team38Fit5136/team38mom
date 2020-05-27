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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MissionDAO {

	@Autowired
	private JdbcTemplate jdbc;
	private static Log logger = LogFactory.getLog(MissionDAO.class);

	public long addMissionDAO(Map<String, ?> props) {
		String sql = "insert into user_info(`cargo_id`, `coordinator_id`, `country_allowed`, `country_origin`, `duration`, `emp_id`, `job_id`, `launch_date`, `location_id`, `mission_details`, `Mission_name`, `shuttle_id`, `status_id`)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println("%%%%%%%%%%%%" + sql);
		int cargoID = (int) (props.containsKey("cargoID") ? props.get("cargoID") : null);
		int coordinatorID = (int) (props.containsKey("coordinatorID") ? props.get("coordinatorID") : null);
		int empID = (int) (props.containsKey("empID") ? props.get("empID") : null);
		int jobID = (int) (props.containsKey("jobID") ? (int) props.get("jobID") : null);
		int locationID = (int) (props.containsKey("locationID") ? props.get("locationID") : null);
		int shuttleID = (int) (props.containsKey("shuttleID") ? props.get("shuttleID") : null);
		int statusID = (int) (props.containsKey("statusID") ? props.get("statusID") : null);

		String countryAllowed = (String) (props.containsKey("countryAllowed") ? props.get("countryAllowed") : null);// Json
		String countryOrigin = (String) (props.containsKey("countryOrigin") ? props.get("countryOrigin") : null);
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
					ps.setString(4, countryOrigin);
					ps.setString(5, duration);
					ps.setInt(6, empID);
					ps.setInt(7, jobID);
					ps.setString(5, launchDate);
					ps.setInt(6, locationID);
					ps.setString(7, missionDetails);
					ps.setString(6, missionName);
					ps.setInt(7, shuttleID);
					ps.setInt(6, statusID);

					return ps;
				}
			};

			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final long missionID = holder.getKey().longValue();
			return missionID;
		} catch (Exception e) {
			throw e;

		}
	}
}
