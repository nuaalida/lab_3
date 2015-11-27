package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Trade;
import pojo.TradePojo;

public class TradeDao extends BaseDao {
	
	public List<TradePojo> getTradeList(String key, String value) {
		List<TradePojo> list = null;
		String sql = "select t.g_id, t.t_color, t.t_count, t.t_type, t.t_time," +
							"g.g_name, g.g_price, g.g_pic, g.g_amount " +
					 "from trade t, good g" +
					 "where t." +key+ "= \"" + value +"\" and " +
					 "t.g_id = g.g_id";
				
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			TradePojo t = new TradePojo();
			int count = 0;
			while (rs.next()) {
				if (count == 0) {
					list = new ArrayList<TradePojo>();
				}
				t.setG_id(rs.getInt("g_id"));
				t.setT_color(rs.getString("t_color"));
				t.setT_count(rs.getInt("t_count"));
				t.setT_type(rs.getString("t_type"));
				t.setT_time(rs.getDate("t_time"));
				
				t.setG_amount(rs.getInt("g_amount"));
				t.setG_name(rs.getString("g_name"));
				t.setG_pic(rs.getString("g_pic"));
				t.setG_price(rs.getString("g_price"));
				list.add(t);
				count ++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public int addTrade(Trade t) {
		int result = 0;
		String sql = "insert into trade(g_id,u_name,t_color,t_count,t_type,t_date) values(?,?,?,?,?,?)";
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,t.getG_id());
			pstmt.setString(2, t.getU_name());
			pstmt.setString(3, t.getT_color());
			pstmt.setInt(4, t.getT_count());
			pstmt.setString(5, t.getT_type());
			pstmt.setDate(6, t.getT_time());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}
}
