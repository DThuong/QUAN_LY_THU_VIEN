package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterDB {
	public int countAcc() {
		int maxID = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT COUNT(*) AS userID_max FROM taikhoan";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				maxID = rs.getInt("userID_max");
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxID;
	}
}
