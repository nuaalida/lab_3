package action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import dao.UserDao;

public class RegisterAction extends ActionSupport  {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private String u_name;
	private String u_pic;
	private String u_pay;
	private String u_pass;
	private String u_repass;
	
	public String register() {
		// validate 
		// addUser
		int result = 1;
		String error = null;
		UserDao userDao = new UserDao();
		User user = userDao.getUserByName(u_name);
		System.out.println(u_name);
		
		if (user != null) {
			result = 0;
			error = "Username is used.";
		}
		else if (u_pass == null) {
			result = 0;
			error = "Password is empty.";
		}
		else if (u_pass.equals(u_repass) == false) {
			result = 0;
			error = "Twice passwords are different.";
		}
		else {
			user = new User(u_name,u_pic,u_pay,u_pass);
			userDao.addUser(user);
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

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_pic() {
		return u_pic;
	}

	public void setU_pic(String u_pic) {
		this.u_pic = u_pic;
	}

	public String getU_pay() {
		return u_pay;
	}

	public void setU_pay(String u_pay) {
		this.u_pay = u_pay;
	}

	public String getU_pass() {
		return u_pass;
	}

	public void setU_pass(String u_pass) {
		this.u_pass = u_pass;
	}

	public String getU_repass() {
		return u_repass;
	}

	public void setU_repass(String u_repass) {
		this.u_repass = u_repass;
	}
}
