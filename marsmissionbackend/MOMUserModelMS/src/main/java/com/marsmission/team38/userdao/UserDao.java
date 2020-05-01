package com.marsmission.team38.userdao;

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

@Component
public class UserDao {

	@Autowired
	private JdbcTemplate jdbc;
	private static Log logger = LogFactory.getLog(UserDao.class);

	public Map<String, Serializable> getuserdetails(String userName, String passwd) {
		Map<String, Serializable> result = new HashMap<>();

		try {
			String sql = "SELECT user_name,user_email FROM user_info where (user_id = '" + userName
					+ "' or user_email= '" + userName + "') and user_password= '" + passwd + "'";
			System.out.println(sql);
			result.put("status", "1");
			result.put("respmsg", (Serializable) jdbc.queryForMap(sql));
			System.out.println("*****" + jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			result.put("status", "failed");
			return result;
		}
	}

	public long adduserDAO(String userName, String email, String passwd) {
		String sql = "insert into user_info(`user_name`,`user_email`,`user_password`) values(?,?,?)";
		System.out.println("%%%%%%%%%%%%" + sql);
		try {
			final PreparedStatementCreator psc = new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, userName);
					ps.setString(2, email);
					ps.setString(3, passwd);
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

}
