package com.marsmission.team38.userservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marsmission.team38.userdao.UserDao;

@Service
public class UserServcie {
	@Autowired
	private UserDao userDao;

	public Map<String, Object> getuserDetails(String userName, String passwd) {

		Map<String, Object> result = userDao.getuserdetails(userName, passwd);
		if (result.get("status").toString().equalsIgnoreCase("0")) {
			result.put("respmsg", "user doesnot exist with given credentials");
		} else {
			result.put("respmsg", result.get("status"));
		}
		return result;
	}

}
