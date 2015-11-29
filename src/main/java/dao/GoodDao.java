package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Good;

public class GoodDao extends BaseDao {
	
	public void deleteAmount(int g_id, int t_count){
		String sql = "update good set g_amount = g_amount - ? where g_id = " + g_id;
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t_count);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		
	}
	
	public List<Good> getGoodListBySeller(String u_name) {
		List<Good> list = null;
		String sql = "select * from good where u_name = \"" + u_name +"\"";
		
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
	
	public List<Good> recommend(String u_name) {
		List<Good> list = null;
		String[] sql = new String[2];
		sql[0] = "select * from good " + 
				" where u_name in (select u_name from good "
				+ "					where g_id in (select g_id from trade "
				+ "									where u_name = ? ) ) "
				+ " and "
				+ " g_id not in (select g_id from trade where u_name = ? ) ";
		sql[1] = "select * from good " + 
				" where u_name in (select u_name from good "
				+ "					where g_id in (select g_id from favorite "
				+ "									where u_name = ? ) ) "
				+ " and "
				+ " g_id not in (select g_id from favorite where u_name = ? ) ";;
		
		int count = 0;		
		for (String sqlitem : sql) {
			try {
				conn = this.getConection();
				pstmt = conn.prepareStatement(sqlitem);
				pstmt.setString(1, u_name);
				pstmt.setString(2, u_name);
				rs = pstmt.executeQuery();
				
				while (rs.next() && count<10) {
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
		}
		
		if (count<10) {
			String sqlitem = "select * from good order by rand() limit 0, ?";
			try {
				conn = this.getConection();
				pstmt = conn.prepareStatement(sqlitem);
				pstmt.setInt(1, 10-count);
				rs = pstmt.executeQuery();
				
				while (rs.next() && count<10) {
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
			
		}
		
		return list;
	}
	
	public List<Good> search(String key) {
		List<Good> list = null;
		String sql = "select * from good " +
						" where locate(?,g_name) > 0";
		
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
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
	
	public int deleteGood(int g_id){
		int result = 0;
		String sql = "delete from good where g_id=" + g_id;
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return result;
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
