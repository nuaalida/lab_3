package dao;

import bean.User;

public class UserDao extends BaseDao {
	
	public User getUserByName(String u_name){
		User u = null;
		String sql = "select * from user where u_name = " + u_name;
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			u = new User();
			if (rs.next()) {
	 			u.setU_id(rs.getInt("u_id"));
				u.setU_name(rs.getString("u_name"));
				u.setU_pic(rs.getString("u_pic"));
				u.setU_pay(rs.getString("u_pay"));
				u.setU_pass(rs.getString("u_pass"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return u;
	}
	
	public int addUser(User u) {
		int result = 0;
		String sql = "insert into user(u_name,u_pic,u_pay,u_pass) values(?,?,?,?)";
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getU_name());
			pstmt.setString(2, u.getU_pic());
			pstmt.setString(3, u.getU_pay());
			pstmt.setString(4, u.getU_pass());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}
	
	
}
