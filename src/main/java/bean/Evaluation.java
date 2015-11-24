package bean;

import java.sql.Date;

public class Evaluation {
	private int g_id;
	private String u_name;
	private String e_text;
	private Date e_time;
	
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
}
