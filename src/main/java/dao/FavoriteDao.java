package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Favorite;

public class FavoriteDao extends BaseDao {
	
	public List<Integer> getFavoriteList(String key,String value) {
		List<Integer> list = null;
		String sql = "select * from favorite where " + key + " = \"" + value + "\"";
		
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while (rs.next()) {
				if (count == 0) {
					list = new ArrayList<Integer>();
				}
				list.add(rs.getInt("g_id"));
				count++;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	public Favorite getFavoriteByName(String name) {
		Favorite f = null;
		String sql = "select * from favorite where u_name = \"" + name +"\"";
		conn = this.getConection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				f = new Favorite();
				f.setG_id(rs.getInt("g_id"));
				f.setU_name(rs.getString("u_name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return f;
	}
	
	public int addFavorite(Favorite f) {
		int result = 0;
		String sql = "insert into favorite(g_id,u_name) values(?,?)";
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, f.getG_id());
			pstmt.setString(2, f.getU_name());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}
	
}
