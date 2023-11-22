package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Book;
import model.ChiTietPhieuMuon;
import model.PhieuMuon;
import model.SinhVien;

public class ChiTietPhieuMuonDAO implements DAOInterface<ChiTietPhieuMuon>{

	@Override
	public ArrayList<ChiTietPhieuMuon> selectAll() {
		ArrayList<ChiTietPhieuMuon> ctpm_list = new ArrayList<ChiTietPhieuMuon>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM chitietphieumuon";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String ctpm_id = rs.getString("ctpm_id");
				String phieu_id = rs.getString("phieu_id");
				String book_id = rs.getString("book_id");
				int soLuong = rs.getInt("soLuong");
				
				Book sach = new Book();
				sach.setBook_id(book_id);
				Book book = new BookDAO().selectById(sach);
				
				PhieuMuon phieu_defaul = new PhieuMuon();
				phieu_defaul.setPhieu_id(phieu_id);
				PhieuMuon phieu = new PhieuMuonDAO().selectById(phieu_defaul);
				
				ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon(ctpm_id, phieu, book, soLuong);
				ctpm_list.add(ctpm);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ctpm_list;
	}

	@Override
	public ChiTietPhieuMuon selectById(ChiTietPhieuMuon t) {
		ChiTietPhieuMuon ctpm = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM chitietphieumuon WHERE ctpm_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getCtpm_id());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String ctpm_id = rs.getString("ctpm_id");
				String phieu_id = rs.getString("phieu_id");
				String book_id = rs.getString("book_id");
				int soLuong = rs.getInt("soLuong");
				
				Book sach = new Book();
				sach.setBook_id(book_id);
				Book book = new BookDAO().selectById(sach);
				
				PhieuMuon phieu_defaul = new PhieuMuon();
				phieu_defaul.setPhieu_id(phieu_id);
				PhieuMuon phieu = new PhieuMuonDAO().selectById(phieu_defaul);
				
				ctpm = new ChiTietPhieuMuon(ctpm_id, phieu, book, soLuong);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ctpm;
	}

	@Override
	public int insert(ChiTietPhieuMuon t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO chitietphieumuon(phieu_id, book_id, soLuong) " +
					"VALUES(?,?,?);";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getPhieu().getPhieu_id());
			st.setString(2, t.getSach().getBook_id());
			st.setInt(3, t.getSoLuong());
			kq = st.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(conn);
		return kq;
	}

	@Override
	public int insertAll(ArrayList<ChiTietPhieuMuon> arr) {
		int kq = 0;
		for (ChiTietPhieuMuon ctpm : arr) {
			kq += this.insert(ctpm);
		}
		return kq;
	}

	@Override
	public int delete(ChiTietPhieuMuon t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM chitietphieumuon WHERE ctpm_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getCtpm_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<ChiTietPhieuMuon> arr) {
		int kq = 0;
		for (ChiTietPhieuMuon ctpm : arr) {
			kq += this.delete(ctpm);
		}
		return kq;
	}

	@Override
	public int update(ChiTietPhieuMuon t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE chitietphieumuon SET "+
				"phieu_id = ?, "+
				"book_id = ?, "+
				"soLuong = ? "+
				"WHERE ctpm_id = ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getPhieu().getPhieu_id());
			st.setString(2, t.getSach().getBook_id());
			st.setInt(3, t.getSoLuong());
			st.setString(5, t.getCtpm_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}
	public ArrayList<ChiTietPhieuMuon> selectByPhieuMuon(String phieuMuon_id) {
		ArrayList<ChiTietPhieuMuon> ctpm_list = new ArrayList<ChiTietPhieuMuon>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM chitietphieumuon WHERE phieu_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, phieuMuon_id);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String ctpm_id = rs.getString("ctpm_id");
				String phieu_id = rs.getString("phieu_id");
				String book_id = rs.getString("book_id");
				int soLuong = rs.getInt("soLuong");
				
				Book sach = new Book();
				sach.setBook_id(book_id);
				Book book = new BookDAO().selectById(sach);
				
				PhieuMuon phieu_defaul = new PhieuMuon();
				phieu_defaul.setPhieu_id(phieu_id);
				PhieuMuon phieu = new PhieuMuonDAO().selectById(phieu_defaul);
				
				ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon(ctpm_id, phieu, book, soLuong);
				ctpm_list.add(ctpm);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ctpm_list;
	}
}
