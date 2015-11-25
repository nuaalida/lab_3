package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Trade;

public class TradeDao extends BaseDao {
	
	public List<Trade> getTradeList(String key, String value) {
		List<Trade> list = null;
		String sql = "select * from trade where "+ key +" = " + value;
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Trade t = new Trade();
			int count = 0;
			while (rs.next()) {
				if (count == 0) {
					list = new ArrayList<Trade>();
				}
				t.setG_id(rs.getInt("g_id"));
				t.setU_name(rs.getString("u_name"));
				t.setT_color(rs.getString("t_color"));
				t.setT_count(rs.getInt("t_count"));
				t.setT_type(rs.getString("t_type"));
				t.setT_time(rs.getDate("t_date"));
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
