package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.SinhVien;
import model.TacGia;

public class SinhVienDAO implements DAOInterface<SinhVien>{

	@Override
	public ArrayList<SinhVien> selectAll() {
		ArrayList<SinhVien> sv_list = new ArrayList<SinhVien>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM sinhvien";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String SinhVien_ID = rs.getString("SinhVien_ID");
				String hoVaTen = rs.getString("hoVaTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				String maLop = rs.getString("maLop");
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi = rs.getString("diaChi");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				SinhVien sv = new SinhVien(SinhVien_ID, hoVaTen, ngaySinh, maLop, gioiTinh, diaChi, email, phone);
				sv_list.add(sv);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sv_list;
	}

	@Override
	public SinhVien selectById(SinhVien t) {
		SinhVien sv = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM sinhvien WHERE SinhVien_ID = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getMsv());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String SinhVien_ID = rs.getString("SinhVien_ID");
				String hoVaTen = rs.getString("hoVaTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				String maLop = rs.getString("maLop");
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi = rs.getString("diaChi");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				sv = new SinhVien(SinhVien_ID, hoVaTen, ngaySinh, maLop, gioiTinh, diaChi, email, phone);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sv;
	}

	@Override
	public int insert(SinhVien t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO sinhvien(SinhVien_ID, hoVaTen, ngaySinh, maLop, gioiTinh, diaChi, email, phone) " +
					"VALUES(?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getMsv());
			st.setString(2, t.getHoVaTen());
			st.setDate(3, t.getNgaySinh());
			st.setString(4, t.getMaLop());
			st.setString(5, t.getGioiTinh());
			st.setString(6, t.getDiaChi());
			st.setString(7, t.getEmail());
			st.setString(8, t.getPhone());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<SinhVien> arr) {
		int kq = 0;
		for (SinhVien sinhVien : arr) {
			kq += this.insert(sinhVien);
		}
		return kq;
	}

	@Override
	public int delete(SinhVien t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM sinhvien WHERE SinhVien_ID = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getMsv());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<SinhVien> arr) {
		int kq = 0;
		for (SinhVien sinhVien : arr) {
			kq += this.delete(sinhVien);
		}
		return kq;
	}

	@Override
	public int update(SinhVien t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE sinhvien SET "+
				"hoVaTen = ?, "+
				"ngaySinh = ?, "+
				"maLop = ?, "+
				"gioiTinh = ?, "+
				"diaChi = ?, "+
				"email = ?, "+
				"phone = ? "+
				"WHERE SinhVien_ID = ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getHoVaTen());
			st.setDate(2, t.getNgaySinh());
			st.setString(3, t.getMaLop());
			st.setString(4, t.getGioiTinh());
			st.setString(5, t.getDiaChi());
			st.setString(6, t.getEmail());
			st.setString(7, t.getPhone());
			st.setString(8, t.getMsv());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}
}
