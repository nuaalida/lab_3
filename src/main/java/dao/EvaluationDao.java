package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Evaluation;
import pojo.EvaluationPojo;

public class EvaluationDao extends BaseDao {
	public Evaluation getEvaluationByIdAndName(int g_id, String u_name) {
		Evaluation e = null;
		String sql = "select * from evaluation where u_name = \"" + u_name + 
						"\" and g_id = " + g_id;
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				e = new Evaluation();
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
	
	public List<EvaluationPojo> getEvaluationList(String key,String value) {
		List<EvaluationPojo> list = null;
		String sql = "select e.g_id, e.u_name, e.e_text, e.e_time, u.u_pic " +
					"from evaluation e, user u " +
					"where e." + key + " = " + value + " and " +
					"e.u_name = u.u_name";
		
		try {
			conn = this.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while (rs.next()) {
				if (count == 0) {
					list = new ArrayList<EvaluationPojo>();
				}
				EvaluationPojo e = new EvaluationPojo(rs.getInt("g_id"),
						rs.getString("u_name"),rs.getString("e_text"),
						rs.getDate("e_time"),rs.getString("u_pic"));
				list.add(e);
				count++;
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
