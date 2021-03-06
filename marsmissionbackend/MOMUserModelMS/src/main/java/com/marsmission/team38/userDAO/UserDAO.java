package com.marsmission.team38.userDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
 * The User DAO class to interact with Database
 */
@Repository
public class UserDAO {

	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private JdbcTemplate jdbc;

	// Adding the user details into the database in user_info table.
	public long addUserDAO(Map<String, ?> props, String passwd) {
		//sql query to add into user class
		String sql = "insert into user_info(`user_name`,`user_email`,`user_password`,`date_of_birth`,`address`,`nationality`,`gender`,`user_role`)"
				+ " values(?,?,?,?,?,?,?,?)";
		logger.info("in addUserDAO" + sql);
		String userName = (String) (props.containsKey("userName") ? props.get("userName") : null);
		String email = (String) (props.containsKey("email") ? props.get("email") : null);

		// Date of birth format ("%y-%m-%d")

		String dob = (String) (props.containsKey("dob") ? props.get("dob") : null);
		String address = (String) (props.containsKey("address") ? props.get("address") : null);
		String nationality = (String) (props.containsKey("nationality") ? props.get("nationality") : null);
		String gender = (String) (props.containsKey("gender") ? props.get("gender") : null);
		String userRole = (String) (props.containsKey("userRole") ? props.get("userRole") : null);

		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, userName);
					ps.setString(2, email);
					ps.setString(3, passwd);
					ps.setString(4, dob);
					ps.setString(5, address);
					ps.setString(6, nationality);
					ps.setString(7, gender);
					ps.setString(8, userRole);
					return ps;
				}
			};

			// The newly generated key will be saved in this object
			final KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(psc, holder);
			final long userID = holder.getKey().longValue();
			return userID;
		} catch (Exception e) {
			logger.info("in addUserDAO error" + e);
			throw e;

		}
	}

	// getting the user details from the database using user_info table and removing
	// password field.
	public Map<String, Serializable> getUserdetailsDAO(String userName, String passwd, String userRole) {
		logger.info("in getUserdetailsDAO");

		Map<String, Serializable> result = new HashMap<>();
		List<Map<String, Object>> queryResult = new ArrayList<Map<String, Object>>();
		String sql = "SELECT * FROM user_info ";
		try {
			if (!(userName.equalsIgnoreCase("null") || userName.equalsIgnoreCase(""))
					&& !(passwd.equalsIgnoreCase("null") || passwd.equalsIgnoreCase(""))) {
				sql += " where (user_id = '" + userName + "' or user_email= '" + userName + "') and user_password= '"
						+ passwd + "'";
			} else
				sql += " where user_role = '" + userRole + "'";
			logger.info("sql statement" + sql);
			result.put("status", "Success");
			queryResult = jdbc.queryForList(sql);
			for (Map<String, Object> child : queryResult) {
				child.remove("user_password");
				child.put("full_name",child.get("user_name"));
				child.remove("user_name");
			
			}
			result.put("responseMsg", (Serializable) queryResult);
			return result;
		} catch (Exception e) {
			logger.error("in getUserdetailsDAO error" + e);
			result.put("status", "failed");
			return result;
		}
	}

	//Method to update user details
	public Map<String, Serializable> updatedetailsDAO(String userID, Map<String, ?> props) {

		logger.info("in updatedetailsDAO");

		String sql = "update user_info set ";
		String updateQuery = "";
		Map<String, Serializable> result = new HashMap<>();

// cannot update user name and user id and user_role
//		if (props.containsKey("userName")) {
//
//			updateQuery += updateQuery.isEmpty() ? "user_name=" + props.get("userName")
//					: ", user_name=" + props.get("userName");
//		}
//
//		if (props.containsKey("email")) {
//			updateQuery += updateQuery.isEmpty() ? "user_email=" + props.get("email")
//					: ", user_email=" + props.get("email");
//		}

		// Date of birth format ("%y-%m-%d")

		if (props.containsKey("dob")) {
			updateQuery += updateQuery.isEmpty() ? "`date_of_birth`= \" " + props.get("dob") + "\" "
					: ", `date_of_birth`= \"" + props.get("dob") + "\" ";
		}

		if (props.containsKey("address")) {
			updateQuery += updateQuery.isEmpty() ? "`address`= \"" + props.get("address") + "\" "
					: ", `address`= \"" + props.get("address") + "\" ";
		}

		if (props.containsKey("nationality")) {
			updateQuery += updateQuery.isEmpty() ? "`nationality`= \"" + props.get("nationality") + "\" "
					: ", `nationality`= \"" + props.get("nationality") + "\" ";
		}

		if (props.containsKey("gender")) {
			updateQuery += updateQuery.isEmpty() ? "`gender`= \"" + props.get("gender") + "\" "
					: ", `gender`= \"" + props.get("gender") + "\" ";
		}
//		if (props.containsKey("userRole")) {
//			updateQuery += updateQuery.isEmpty() ? "`user_role`= \"" + props.get("userRole") + "\" "
//					: ", `user_role`= \"" + props.get("userRole") + "\" ";
//		}
		sql += updateQuery + " " + "where user_id = " + userID;

		logger.info("in updatedetailsDAO" + sql);

		try {
			if (jdbc.update(sql) == 1) {
				logger.info("in updatedetailsDAO success");
				result.put("status", "success");
			} else {
				result.put("status", "failed");
				logger.info("no update done");
			}

			return result;
		} catch (Exception e) {
			logger.error("in updatedetailsDAO" + e);

			result.put("status", "failed");
			return result;
		}
	}

}
