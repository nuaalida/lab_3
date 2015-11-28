package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import bean.Good;
import dao.GoodDao;

public class GoodAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private List<Good> dataList = new ArrayList<Good>();
	private int g_id;
	private String g_name;
	private String g_price;
	private String g_pic;
	private int g_amount;
	private String g_type;
	private String u_name;
	private String search;
	
	public String goodListOfType() {
		System.out.println("goodlistoftype");
		GoodDao gDao = new GoodDao();
		dataList = gDao.getGoodListByType("g_type", g_type);
		System.out.println(g_type);
		return SUCCESS;
	}
	
	public String recommend() {
		GoodDao gDao = new GoodDao();
		dataList = gDao.recommend(u_name);
		
		return SUCCESS;
	}
	
	public String search() {
		GoodDao gDao = new GoodDao();
		dataList = gDao.search(search);
		
		return SUCCESS;
	}
	
	public String addGood() {
		int result = 1;
		String error = null;
		GoodDao goodDao = new GoodDao();
		Good good = goodDao.getGoodByName(g_name);
	
		if (good != null) {
			result = 0;
			error = "There's a same good in DB.";
		}
		else {
			good = new Good(g_name,g_price,g_pic,g_amount,g_type,u_name);
			goodDao.addGood(good);
		}
		
		dataMap.clear();
		dataMap.put("result",result);
		dataMap.put("error", error);
		
		return SUCCESS;
	}
	
	public String changeGood() {
		int result = 1;
		String error = null;
	
		dataMap.clear();
		dataMap.put("result",result);
		dataMap.put("error", error);
		
		return SUCCESS;
	}
	
	public String deleteGood() {
		String error = null;
		
		GoodDao goodDao = new GoodDao();
		int result = goodDao.deleteGood(g_id);
		
		dataMap.clear();
		dataMap.put("result",result);
		dataMap.put("error", error);
		
		return SUCCESS;
	}
	
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_price() {
		return g_price;
	}
	public void setG_price(String g_price) {
		this.g_price = g_price;
	}
	public String getG_pic() {
		return g_pic;
	}
	public void setG_pic(String g_pic) {
		this.g_pic = g_pic;
	}
	public int getG_amount() {
		return g_amount;
	}
	public void setG_amount(int g_amount) {
		this.g_amount = g_amount;
	}
	public String getG_type() {
		return g_type;
	}
	public void setG_type(String g_type) {
		this.g_type = g_type;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
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

	public List<Good> getDataList() {
		return dataList;
	}

	public void setDataList(List<Good> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}
	
}
