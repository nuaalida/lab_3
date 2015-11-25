package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Evaluation;

public class EvaluationDao extends BaseDao {
	public Evaluation getEvaluationByIdAndName(int g_id, String u_name) {
		Evaluation e = null;
		String sql = "select * from evaluation where u_name = " + u_name + " and g_id = " + g_id;
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			e = new Evaluation();
			if (rs.next()) {
	 			e.setG_id(rs.getInt("g_id"));
	 			e.setU_name(rs.getString("u_name"));
	 			e.setE_text(rs.getString("e_text"));
	 			e.setE_time(rs.getDate("e_time"));
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return e;
	}
	
	public List<Evaluation> getEvaluationList(String key,String value) {
		List<Evaluation> list = null;
		String sql = "select * from evaluation where " + key + " = " + value;
		
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while (rs.next()) {
				if (count == 0) {
					list = new ArrayList<Evaluation>();
				}
				Evaluation e = new Evaluation(rs.getInt("g_id"),rs.getString("u_name"),
						rs.getString("e_text"),rs.getDate("e_time"));
				list.add(e);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	public int addEvaluation(Evaluation e) {
		int result = 0;
		String sql = "insert into evaluation(g_id,u_name,e_text,e_time) values(?,?,?,?)";
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e.getG_id());
			pstmt.setString(2, e.getU_name());
			pstmt.setString(3, e.getE_text());
			pstmt.setDate(4, e.getE_time());
			result = pstmt.executeUpdate();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return result;
	}
}
