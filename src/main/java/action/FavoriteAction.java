package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import bean.Favorite;
import bean.Good;
import dao.FavoriteDao;
import dao.GoodDao;

public class FavoriteAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private List<Good> dataList = new ArrayList<Good>();
	private int g_id;
	private String u_name;
	
	public String favoriteList() {
		System.out.println("favoriteList");
		FavoriteDao fDao = new FavoriteDao();
		List<Integer> list = fDao.getFavoriteList("u_name", u_name);
		GoodDao gDao = new GoodDao();
		for (int tmp_id : list) {
			dataList.add(gDao.getGoodById(tmp_id));
		}
		
		return SUCCESS;
	}
	
	public String checkFavorite() {
		String error = null;
		
		FavoriteDao fDao = new FavoriteDao();
		int result = fDao.checkFavorite(g_id, u_name);
		
		dataMap.clear();
		dataMap.put("result",result);
		dataMap.put("error", error);
		return SUCCESS;
	}
	
	public String addFavorite() {
		// validate
		// addUser
		int result = 1;
		String error = null;
		
		FavoriteDao fDao = new FavoriteDao();
		Favorite f = fDao.getFavoriteByName(u_name);
		
		if (f != null) {
			result = 0;
			error = "There a same favorite in db.";
		}
		else {
			f = new Favorite(g_id, u_name);
			fDao.addFavorite(f);
		}
		
		dataMap.clear();
		dataMap.put("result",result);
		dataMap.put("error", error);
		
		return SUCCESS;
	}
	
	public String cancelFavorite() {
		FavoriteDao fDao = new FavoriteDao();
		
		int result = fDao.cancelFavorite(g_id, u_name);
		String error = null;
		
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

	public int getG_id() {
		return g_id;
	}

	public void setG_id(int g_id) {
		this.g_id = g_id;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	/**
	 * @return the dataList
	 */
	public List<Good> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<Good> dataList) {
		this.dataList = dataList;
	}
}
