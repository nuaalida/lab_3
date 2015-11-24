package action;

import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import dao.UserDao;

public class RegisterAction extends ActionSupport  {

	private static final long serialVersionUID = 1L;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		UserDao userDao = new UserDao();
		userDao.addUser(user);
		return "success";
	}

	@Override
	public void validate() {

	}
	
	
}
