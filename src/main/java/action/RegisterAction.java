package action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import dao.UserDao;

public class RegisterAction extends ActionSupport  {

	private static final long serialVersionUID = 1L;
	private User user;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	public String register() {
		
		UserDao userDao = new UserDao();
		/*
		userDao.addUser(user);
		*/
		dataMap.clear();
		dataMap.put("result",1);
		dataMap.put("error", null);
		
		return SUCCESS;
	}

	@Override
	public void validate() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

}
