package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.GiaoVien;
import model.SinhVien;

public class GiaoVienDAO implements DAOInterface<GiaoVien>{

	@Override
	public ArrayList<GiaoVien> selectAll() {
		ArrayList<GiaoVien> gv_list = new ArrayList<GiaoVien>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM giaovien";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String giaoVien_id = rs.getString("giaoVien_id");
				String hoVaTen = rs.getString("hoVaTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				String gioiTinh = rs.getString("gioiTinh");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				GiaoVien gv = new GiaoVien(giaoVien_id, hoVaTen, ngaySinh, gioiTinh, email, phone);
				gv_list.add(gv);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return gv_list;
	}

	@Override
	public GiaoVien selectById(GiaoVien t) {
		GiaoVien gv = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM giaovien WHERE giaoVien_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getGiaoVien_id());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String giaoVien_id = rs.getString("giaoVien_id");
				String hoVaTen = rs.getString("hoVaTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				String gioiTinh = rs.getString("gioiTinh");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				gv = new GiaoVien(giaoVien_id, hoVaTen, ngaySinh, gioiTinh, email, phone);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return gv;
	}

	@Override
	public int insert(GiaoVien t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO giaovien(giaoVien_id, hoVaTen, ngaySinh, gioiTinh, email, phone) " +
					"VALUES(?,?,?,?,?,?);";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getGiaoVien_id());
			st.setString(2, t.getHoVaTen());
			st.setDate(3, t.getNgaySinh());
			st.setString(4, t.getGioiTinh());
			st.setString(5, t.getEmail());
			st.setString(6, t.getPhone());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<GiaoVien> arr) {
		int kq = 0;
		for (GiaoVien giaoVien : arr) {
			kq += this.insert(giaoVien);
		}
		return kq;
	}

	@Override
	public int delete(GiaoVien t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM giaovien WHERE giaoVien_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getGiaoVien_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<GiaoVien> arr) {
		int kq = 0;
		for (GiaoVien giaoVien : arr) {
			kq += this.delete(giaoVien);
		}
		return kq;
	}

	@Override
	public int update(GiaoVien t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE giaovien SET "+
				"hoVaTen = ?, "+
				"ngaySinh = ?, "+
				"gioiTinh = ?, "+
				"email = ?, "+
				"phone = ? "+
				"WHERE giaoVien_id = ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getHoVaTen());
			st.setDate(2, t.getNgaySinh());
			st.setString(3, t.getGioiTinh());
			st.setString(4, t.getEmail());
			st.setString(5, t.getPhone());
			st.setString(6, t.getGiaoVien_id());
			
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}
	
}
