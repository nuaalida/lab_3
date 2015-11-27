package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Good;

public class GoodDao extends BaseDao {
	
	public List<Good> getGoodListByType(String key, String value) {
		List<Good> list = null;
		String sql = "select * from good where " + key + " = \"" + value +"\"";
		
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while (rs.next()) {
				if (count == 0) {
					list = new ArrayList<Good>();
				}
				Good g = new Good(rs.getString("g_name"),
						rs.getString("g_price"),rs.getString("g_pic"),
						rs.getInt("g_amount"),rs.getString("g_type"),
						rs.getString("u_name"));
				g.setG_id(rs.getInt("g_id"));
				list.add(g);
				count++;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public Good getGoodById(int g_id){
		Good g = null;
		String sql = "select * from good where g_id = \"" + g_id +"\"";
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				g = new Good();
	 			g.setG_id(rs.getInt("g_id"));
	 			g.setG_name(rs.getString("g_name"));
	 			g.setG_pic(rs.getString("g_pic"));
	 			g.setG_price(rs.getString("g_price"));
	 			g.setG_type(rs.getString("g_type"));
	 			g.setG_amount(rs.getInt("g_amount"));
	 			g.setU_name(rs.getString("u_name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return g;
	}
	
	public Good getGoodByName(String g_name){
		Good g = null;
		String sql = "select * from good where g_name = \"" + g_name +"\"";
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				g = new Good();
	 			g.setG_id(rs.getInt("g_id"));
	 			g.setG_name(rs.getString("g_name"));
	 			g.setG_pic(rs.getString("g_pic"));
	 			g.setG_price(rs.getString("g_price"));
	 			g.setG_type(rs.getString("g_type"));
	 			g.setG_amount(rs.getInt("g_amount"));
	 			g.setU_name(rs.getString("u_name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return g;
	}
	
	public int addGood(Good g) {
		int result = 0;
		String sql = "insert into good(g_name,g_price,g_pic,g_amount,g_type,u_name) values(?,?,?,?,?,?)";
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());
			pstmt.setString(2, g.getG_price());
			pstmt.setString(3, g.getG_pic());
			pstmt.setInt(4, g.getG_amount());
			pstmt.setString(5, g.getG_type());
			pstmt.setString(6, g.getU_name());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}
	
}
