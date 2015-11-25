package action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import dao.UserDao;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private String u_name;
	private String u_pass;
	
	public String login() {
		int result = 1;
		String error = null;
		UserDao userDao = new UserDao();
		
		// validate
		User user = null;
		user = userDao.getUserByName(u_name);
		
		if (user == null) {
			result = 0;
			error = "Username doesn't exist.";
		}
		else if (user.getU_pass().equals(u_pass) == false) {
			result = 0;
			error = "Username doesn't match password.";
		}
		
		// output
		dataMap.clear();
		dataMap.put("result",result);
		dataMap.put("error", error);
		
		return SUCCESS;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_pass() {
		return u_pass;
	}

	public void setU_pass(String u_pass) {
		this.u_pass = u_pass;
	}
	
}
