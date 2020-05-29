package com.marsmission.team38.userDAO;

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
public class UserDAO {

	@Autowired
	private JdbcTemplate jdbc;
	private static Log logger = LogFactory.getLog(UserDAO.class);

	// Adding the user details into the database in user_info table.
	public long addUserDAO(Map<?, ?> props, String passwd) {
		String sql = "insert into user_info(`user_name`,`user_email`,`user_password`,`date_of_birth`,`address`,`nationality`,`gender`,`user_role`)"
				+ " values(?,?,?,?,?,?,?,?)";
		System.out.println("%%%%%%%%%%%%" + sql);
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
			throw e;

		}
	}

	// getting the user details from the database using user_info table and removing
	// password field.
	public Map<String, Serializable> getUserdetails(String userName, String passwd) {
		Map<String, Serializable> result = new HashMap<>();
		Map<String, Object> queryResult = new HashMap<>();

		try {
			String sql = "SELECT * FROM user_info where (user_id = '" + userName + "' or user_email= '" + userName
					+ "') and user_password= '" + passwd + "'";
			System.out.println(sql);
			result.put("status", "Success");
			queryResult = jdbc.queryForMap(sql);
			queryResult.remove("user_password");
			result.put("responseMsg", (Serializable) queryResult);
			System.out.println("*****" + jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			result.put("status", "failed");
			return result;
		}
	}

	public Map<String, Serializable> updatedetails(String userID, Map<String, ?> props) {

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

		try {
			if (jdbc.update(sql) == 1)
				result.put("status", "success");
			return result;
		} catch (Exception e) {
			result.put("status", "failed");
			return result;
		}
	}

}
