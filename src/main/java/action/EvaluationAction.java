package action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import bean.Evaluation;
import dao.EvaluationDao;
import pojo.EvaluationPojo;

public class EvaluationAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private List<EvaluationPojo> dataList = new ArrayList<EvaluationPojo>();
	private int g_id;
	private String u_name;
	private String e_text;
	private Date e_time;
	
	public String evaluationList() {
		EvaluationDao eDao = new EvaluationDao();
		setDataList(eDao.getEvaluationList("g_id", Integer.toString(g_id)));
		return SUCCESS;
	}
	
	
	public String addEvaluation() {
		int result = 1;
		String error = null;
		
		EvaluationDao eDao = new EvaluationDao();
		Evaluation e = eDao.getEvaluationByIdAndName(g_id, u_name);
 		
		if (e != null) {
			result = 0;
			error = "There existed a comment.";
		}
		else {
			e = new Evaluation(g_id, u_name, e_text, e_time);
			eDao.addEvaluation(e);
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
	
	public String getE_text() {
		return e_text;
	}
	public void setE_text(String e_text) {
		this.e_text = e_text;
	}
	
	public Date getE_time() {
		return e_time;
	}
	public void setE_time(Date e_time) {
		this.e_time = e_time;
	}


	/**
	 * @return the dataList
	 */
	public List<EvaluationPojo> getDataList() {
		return dataList;
	}


	/**
	 * @param list the dataList to set
	 */
	public void setDataList(List<EvaluationPojo> list) {
		this.dataList = list;
	}
}
