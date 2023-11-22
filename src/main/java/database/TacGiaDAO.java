package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.TacGia;

public class TacGiaDAO implements DAOInterface<TacGia>{

	@Override
	public ArrayList<TacGia> selectAll() {
		ArrayList<TacGia> authors = new ArrayList<TacGia>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM tacgia";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String tacGia_id = rs.getString("tacGia_id");
				String tenTacGia = rs.getString("tenTacGia");
				int namSinh = rs.getInt("namSinh");
				String ghiChu = rs.getString("ghiChu");
				
				TacGia tg = new TacGia(tacGia_id, tenTacGia, namSinh, ghiChu);
				authors.add(tg);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return authors;
	}

	@Override
	public TacGia selectById(TacGia t) {
		TacGia author = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM tacgia WHERE tacGia_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTacGia_id());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String tacGia_id = rs.getString("tacGia_id");
				String tenTacGia = rs.getString("tenTacGia");
				int namSinh = rs.getInt("namSinh");
				String ghiChu = rs.getString("ghiChu");
				
				author = new TacGia(tacGia_id, tenTacGia, namSinh, ghiChu);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return author;
	}

	@Override
	public int insert(TacGia t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO tacgia(tacGia_id, tenTacGia, namSinh, ghiChu) " +
					"VALUES(?,?,?,?);";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTacGia_id());
			st.setString(2, t.getTen_tacGia());
			st.setInt(3, t.getNamSinh());
			st.setString(4, t.getGhiChu());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<TacGia> arr) {
		int kq = 0;
		for (TacGia tacGia : arr) {
			kq += this.insert(tacGia);
		}
		return kq;
	}

	@Override
	public int delete(TacGia t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM tacgia WHERE tacGia_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTacGia_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<TacGia> arr) {
		int kq = 0;
		for (TacGia tacGia : arr) {
			kq += this.delete(tacGia);
		}
		return kq;
	}

	@Override
	public int update(TacGia t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE tacgia SET "+
				"tenTacGia = ?, "+
				"namSinh = ?, "+
				"ghiChu = ? "+
				"WHERE tacGia_id = ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTen_tacGia());
			st.setInt(2, t.getNamSinh());
			st.setString(3, t.getGhiChu());
			st.setString(4, t.getTacGia_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

}
