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
}
