package bean;

public class Good {
	private int g_id;
	private String g_name;
	private String g_price;
	private String g_pic;
	private int g_amount;
	private String g_type;
	private String u_name;

	public Good() {
	
	}
	
	public Good(String name,String price,String pic,int amount,String type,String uname) {
		g_name = name;
		g_price = price;
		g_pic = pic;
		g_amount =amount;
		g_type = type;
		u_name = uname;
	}
	
	public int getG_id() {
		return g_id;
	}

	public void setG_id(int g_id) {
		this.g_id = g_id;
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
	
}
