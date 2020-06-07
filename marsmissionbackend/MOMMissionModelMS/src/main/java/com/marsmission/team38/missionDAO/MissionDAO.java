package com.marsmission.team38.missionDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * The Mission DAO class to interact with Database
 */
@Repository
public class MissionDAO {

	@Autowired
	private JdbcTemplate jdbc;
	// logger variable to print logs
	private static Log logger = LogFactory.getLog(MissionDAO.class);

	// adding Mission details in DB
	public int addMissionDAO(Integer coordinatorID, Integer statusID, Integer countryOrigin, Integer duration,
			String launchDate, String missionDetails, String missionName) throws ParseException {

		logger.info("in addMissionDAO");
		String sql = "insert into mission_details( `coordinator_id`, `country_origin`, `duration`, `launch_date`, `mission_details`, `mission_name`, `status_id`)"
				+ " values(?,?,?,?,?,?,?)";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
		if (launchDate == null || launchDate.toString().equalsIgnoreCase("")) {
			launchDate = "1999-12-12";
		}
		java.util.Date dateStr = formatter.parse(launchDate);
		java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());

		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, coordinatorID);
					ps.setInt(2, countryOrigin);
					ps.setInt(3, duration);
					ps.setDate(4, dateDB);
					ps.setString(5, missionDetails);
					ps.setString(6, missionName);
					ps.setInt(7, statusID);
					return ps;
				}
			};
			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final int missionID = holder.getKey().intValue();

			return missionID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	// getting mission details from DB
	public Map<String, Serializable> getMissiondetailsDAO(String missionID) {
		logger.info("in getMissiondetailsDAO");

		Map<String, Serializable> result = new HashMap<>();
		String sql = "SELECT * FROM mission_details  md join cargo ca on ca.mission_id=md.mission_id ";
		logger.info("missionID" + missionID);

		try {
			// creating sql query for get mission details

			sql += " where (md.mission_id = '" + missionID + "' or md.mission_name= '" + missionID + "')";
			logger.info(sql);
			if (jdbc.queryForList(sql).size() <= 0 || jdbc.queryForList(sql).size() > 1) {
				result.put("status", "failed");
				return result;
			}
			result.put("status", "Success");
			result.put("responseMsg", (Serializable) jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			logger.error("in getMissiondetailsDAO error" + e);
			result.put("status", "failed");
			return result;
		}
	}

	// updating mission details in DB
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

		// final sql query
		sql += updateQuery + " " + "where `mission_id` = " + missionID;
		logger.info("final sql------------" + sql);

		try {
			if (jdbc.update(sql) == 1) {
				result.put("status", "success");
				result.put("responseMsg", "successfully updated mission");

			} else {
				result.put("status", "failed");
				result.put("responseMsg", "failed to updated mission, No mission present with given missionID");
			}
			// returning the status and responseMsg
			return result;

		} catch (Exception e) {
			result.put("status", "failed");
			result.put("responseMsg", "failed to updated mission because of some exception");
			return result;
		}

	}

	public int addAllowedCountry(int missionID, List<?> countryAllowed) {

		logger.info("in addAllowedCountry");
		String values = "";
		for (int i = 0; i < countryAllowed.size(); i++) {
			logger.info(countryAllowed.get(i));
			values += " (" + missionID + "," + countryAllowed.get(i) + "), ";
		}
		String sql = "insert into country_allowed(`mission_id`,`country_id`) values "
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

	public void deleteMissionDAO(int missionID) {

		String sql = "delete from mission_details where mission_id = " + missionID;
		logger.info(sql);
		try {
			jdbc.update(sql);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public int addMissionRelatedLocation(int missionID, Integer locationID) {
		logger.info("in addMissionRelatedLocation");
		String sql = "insert into mission_location(`mission_id`,`location_id`) values (" + missionID + "," + locationID
				+ ")";
		logger.info(sql);
		try {
			int rsp = jdbc.update(sql);
			return rsp;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public void deleteAllowedCountry(int missionID) {
		String sql = "delete from country_allowed where mission_id = " + missionID;
		logger.info(sql);
		try {
			jdbc.update(sql);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public void deleteMissionRelatedLocation(int missionID) {
		String sql = "delete from mission_location where mission_id = " + missionID;
		logger.info(sql);
		try {
			jdbc.update(sql);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public int updateMissiontoJob(int missionID, List<?> jobIDs) {
		logger.info("in addAllowedCountry");
		String values = " (";
		for (int i = 0; i < jobIDs.size(); i++) {
			logger.info(jobIDs.get(i));
			values += jobIDs.get(i) + ",";
		}
		String sql = "update job set `mission_id`=" + missionID + " where job_id in"
				+ values.substring(0, values.length() - 1) + ")";
		logger.info(sql);
		try {
			int rsp = jdbc.update(sql);
			return rsp;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public int addCargoDAO(String jsonObject, int missionID) {
		logger.info("in addCargoDAO");

		String sql = "insert into cargo(`cargo`, `mission_id`)" + " values(?,?)";
		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, jsonObject);
					ps.setInt(2, missionID);
					return ps;
				}
			};
			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final int cargoID = holder.getKey().intValue();

			return cargoID;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

}
