package com.marsmission.team38.userdao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

	@Autowired
	private JdbcTemplate jdbc;

	public Map<String, Object> getuserdetails(String userName, String passwd) {
		Map<String, Object> result = new HashMap<>();

		try {
			String sql = "SELECT user_name,user_email FROM user_info where user_email= '" + userName
					+ "' and user_password= '" + passwd + "'";
			System.out.println(sql);
			result.put("status", "1");
			result.put("respmsg", jdbc.queryForMap(sql));
			System.out.println("*****" + jdbc.queryForMap(sql));
			return result;
		} catch (Exception e) {
			result.put("status", "0");
			return result;
		}
	}

}
