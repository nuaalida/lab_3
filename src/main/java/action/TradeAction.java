package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import bean.Trade;
import dao.GoodDao;
import dao.TradeDao;
import pojo.TradePojo;

public class TradeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private List<TradePojo> dataList = new ArrayList<TradePojo>();
	private int g_id;
	private String u_name;
	private String t_color;
	private String t_type;
	private int t_count;
	private String t_time;
	
	public String tradeList() {
		TradeDao tDao = new TradeDao();
		setDataList(tDao.getTradeList("u_name", u_name));
		return SUCCESS;
	}
	
	public String checkTrade() {
		System.out.println("checkTrade");
		System.out.println(g_id + u_name);
		
		int result = 1;
		String error = null;
		
		TradeDao tDao = new TradeDao();
		
		result = tDao.checkTrade(g_id, u_name);
		
		dataMap.clear();
		dataMap.put("result",result);
		dataMap.put("error", error);
		
		return SUCCESS;
	}
	
	public String addTrade() {
		
		int result = 1;
		String error = null;
		TradeDao tDao = new TradeDao();
		GoodDao gDao = new GoodDao();
		Trade t = null;
		
		if (gDao.getGoodById(g_id).getG_amount() < t_count) {
			result = 0;
			error = "Inventory shortage.";
		}
		else {
			try {
				t = new Trade(g_id, u_name, t_color, t_type, t_count, t_time);
				tDao.addTrade(t);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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

	public String getT_color() {
		return t_color;
	}

	public void setT_color(String t_color) {
		this.t_color = t_color;
	}

	public String getT_type() {
		return t_type;
	}

	public void setT_type(String t_type) {
		this.t_type = t_type;
	}

	public int getT_count() {
		return t_count;
	}

	public void setT_count(int t_count) {
		this.t_count = t_count;
	}


	/**
	 * @return the dataList
	 */
	public List<TradePojo> getDataList() {
		return dataList;
	}

	/**
	 * @param list the dataList to set
	 */
	public void setDataList(List<TradePojo> list) {
		this.dataList = list;
	}

	public String getT_time() {
		return t_time;
	}

	public void setT_time(String t_time) {
		this.t_time = t_time;
	}
	
}
