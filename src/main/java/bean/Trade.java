package bean;

public class Trade {
	private int g_id;
	private String u_name;
	private String t_color;
	private String t_type;
	private int t_count;
	private String t_time;
	
	public Trade(){}
	
	public Trade(int g_id,String u_name,String t_color,String t_type,int t_count,String t_time) {
		setG_id(g_id);
		setU_name(u_name);
		setT_color(t_color);
		setT_type(t_type);
		setT_count(t_count);
		setT_time(t_time);
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

	public String getT_time() {
		return t_time;
	}

	public void setT_time(String t_time) {
		this.t_time = t_time;
	}

}
