package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.TacGia;
import model.TheLoai;

public class TheLoaiDAO implements DAOInterface<TheLoai>{

	@Override
	public ArrayList<TheLoai> selectAll() {
		ArrayList<TheLoai> kq = new ArrayList<TheLoai>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM theloai";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String theLoai_id = rs.getString("theLoai_id");
				String tenTheLoai = rs.getString("tenTheLoai");
				
				TheLoai theloai = new TheLoai(theLoai_id, tenTheLoai);
				kq.add(theloai);
				
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public TheLoai selectById(TheLoai t) {
		TheLoai kq = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM theloai WHERE theLoai_id =?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTheLoai_id());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String theLoai_id = rs.getString("theLoai_id");
				String tenTheLoai = rs.getString("tenTheLoai");
				
				kq = new TheLoai(theLoai_id, tenTheLoai);
				
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insert(TheLoai t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO theloai(theLoai_id, tenTheLoai) " +
					"VALUES(?,?);";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTheLoai_id());
			st.setString(2, t.getTenTheLoai());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<TheLoai> arr) {
		int kq = 0;
		for (TheLoai theLoai : arr) {
			kq += this.insert(theLoai);
		}
		return kq;
	}

	@Override
	public int delete(TheLoai t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM theloai WHERE theLoai_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTheLoai_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<TheLoai> arr) {
		int kq = 0;
		for (TheLoai theLoai : arr) {
			kq += this.delete(theLoai);
		}
		return kq;
	}

	@Override
	public int update(TheLoai t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE theloai SET "+
				"tenTheLoai = ? "+
				"WHERE theLoai_id = ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTenTheLoai());
			st.setString(2, t.getTheLoai_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}
	

}
