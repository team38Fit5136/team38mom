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
		String sql = "insert into mission_details(`cargo_id`, `coordinator_id`, `country_allowed`, `country_origin`, `duration`,  `launch_date`, `location_id`, `mission_details`, `mission_name`, `shuttle_id`, `status_id`)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?)";

		int cargoID = (int) (props.containsKey("cargoID") ? props.get("cargoID") : 0);
		int coordinatorID = (int) (props.containsKey("coordinatorID") ? props.get("coordinatorID") : 0);
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
					ps.setString(6, launchDate);
					ps.setInt(7, locationID);
					ps.setString(8, missionDetails);
					ps.setString(9, missionName);
					ps.setInt(10, shuttleID);
					ps.setInt(11, statusID);
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

	public Map<String, Serializable> updatedetailsDAO(String missionID, Map<String, ?> props) {
		// TODO Auto-generated method stub
		logger.info("updatedetailsDAO");
		String sql = "update mission_details set ";
		String updateQuery = "";
		String whereclause = "";
		
		logger.info("missionID" + missionID + "props");

		Map<String, Serializable> result = new HashMap<>();

		if (props.containsKey("coordinatorID")) {
			updateQuery += updateQuery.isEmpty() ? "`coordinator_id`= \"" + props.get("coordinatorID") + "\" "
					: ", `coordinator_id`= \"" + props.get("coordinatorID") + "\" ";
		}

		
		if (props.containsKey("locationID")) {
			updateQuery += updateQuery.isEmpty() ? "`location_id`= \"" + props.get("locationID") + "\" "
					: ", `location_id`= \"" + props.get("locationID") + "\" ";
		}

		if (props.containsKey("shuttleID")) {
			updateQuery += updateQuery.isEmpty() ? "`shuttle_id`= \"" + props.get("shuttleID") + "\" "
					: ", `shuttle_id`= \"" + props.get("shuttleID") + "\" ";
		}

		if (props.containsKey("statusID")) {
			updateQuery += updateQuery.isEmpty() ? "`status_id`= \"" + props.get("statusID") + "\" "
					: ", `status_id`= \"" + props.get("statusID") + "\" ";
		}

		if (props.containsKey("countryAllowed")) {
			updateQuery += updateQuery.isEmpty() ? "`country_allowed`= \"" + props.get("countryAllowed") + "\" "
					: ", `country_allowed`= \"" + props.get("countryAllowed") + "\" ";
		}
		if (props.containsKey("countryOrigin")) {
			updateQuery += updateQuery.isEmpty() ? "`country_origin`= \"" + props.get("countryOrigin") + "\" "
					: ", `country_origin`= \"" + props.get("countryOrigin") + "\" ";
		}

		if (props.containsKey("duration")) {
			updateQuery += updateQuery.isEmpty() ? "`duration`= \"" + props.get("duration") + "\" "
					: ", `duration`= \"" + props.get("duration") + "\" ";
		}

		if (props.containsKey("launchDate")) {
			updateQuery += updateQuery.isEmpty() ? "`launch_date`= \"" + props.get("launchDate") + "\" "
					: ", `launch_date`= \"" + props.get("launchDate") + "\" ";
		}

		if (props.containsKey("missionDetails")) {
			updateQuery += updateQuery.isEmpty() ? "`mission_details`= \"" + props.get("missionDetails") + "\" "
					: ", `mission_details`= \"" + props.get("missionDetails") + "\" ";
		}
		
		
		
		sql += updateQuery + " " + "where `mission_id` = " + missionID;
		logger.info("final sql------------" + sql);

		try {
			if (jdbc.update(sql) == 1)
			{
				result.put("status", "success");
				result.put("responseMsg", "successfully updated mission");
				
			}
			else {
				result.put("status", "failed");
				result.put("responseMsg", "failed to updated mission, No mission present with given missionID");
			}
			return result;
			
		} catch (Exception e) {
			result.put("status", "failed");
			result.put("responseMsg", "failed to updated mission because of some exception");
			return result;
		}
		
	}
}
