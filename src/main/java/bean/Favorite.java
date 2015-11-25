package bean;

public class Favorite {
	private int g_id;
	private String u_name;
	
	public Favorite() {}
	
	public Favorite(int id, String name) {
		setG_id(id);
		setU_name(name);
	}

	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	
	public int getG_id() {
		return g_id;
	}

	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	
}
