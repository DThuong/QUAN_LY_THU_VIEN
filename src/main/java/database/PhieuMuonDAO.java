package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import model.PhieuMuon;
import model.SinhVien;
import model.TacGia;

public class PhieuMuonDAO implements DAOInterface<PhieuMuon>{

	@Override
	public ArrayList<PhieuMuon> selectAll() {
		ArrayList<PhieuMuon> phieu_list = new ArrayList<PhieuMuon>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM phieumuon";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String phieu_id = rs.getString("phieu_id");
				String sinhVien_id = rs.getString("sinhVien_id");
				Date ngayMuon = rs.getDate("ngayMuon");
				Date hanTra = rs.getDate("hanTra");
				Date ngayTra = rs.getDate("ngayTra");
				
				SinhVien sv = new SinhVien();
				sv.setMsv(sinhVien_id);
				SinhVien sinhVien = new SinhVienDAO().selectById(sv);
				
				PhieuMuon phieu = new PhieuMuon(phieu_id, sinhVien, ngayMuon, hanTra, ngayTra);
				
				phieu_list.add(phieu);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return phieu_list;
	}

	@Override
	public PhieuMuon selectById(PhieuMuon t) {
		PhieuMuon phieu = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM phieumuon WHERE phieu_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getPhieu_id());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String phieu_id = rs.getString("phieu_id");
				String sinhVien_id = rs.getString("sinhVien_id");
				Date ngayMuon = rs.getDate("ngayMuon");
				Date hanTra = rs.getDate("hanTra");
				Date ngayTra = rs.getDate("ngayTra");
				
				SinhVien sv = new SinhVien();
				sv.setMsv(sinhVien_id);
				SinhVien sinhVien = new SinhVienDAO().selectById(sv);
				
				phieu = new PhieuMuon(phieu_id, sinhVien, ngayMuon, hanTra, ngayTra);
				
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return phieu;
	}

	@Override
	public int insert(PhieuMuon t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO phieumuon(sinhVien_id, ngayMuon, hanTra, ngayTra) " +
					"VALUES(?,?,?,?);";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getSinhVien().getMsv());
			st.setDate(2, t.getNgayMuon());
			st.setDate(3, t.getHanTra());
			st.setDate(4, t.getNgayTra());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<PhieuMuon> arr) {
		int kq = 0;
		for (PhieuMuon phieuMuon : arr) {
			kq += this.insert(phieuMuon);
		}
		return kq;
	}

	@Override
	public int delete(PhieuMuon t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM phieumuon WHERE phieu_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getPhieu_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<PhieuMuon> arr) {
		int kq = 0;
		for (PhieuMuon phieuMuon : arr) {
			kq += this.delete(phieuMuon);
		}
		return kq;
	}

	@Override
	public int update(PhieuMuon t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE phieumuon SET "+
				"sinhVien_id = ?, "+
				"ngayMuon = ?, "+
				"hanTra = ?, "+
				"ngayTra = ? "+
				"WHERE phieu_id = ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getSinhVien().getMsv());
			st.setDate(2, t.getNgayMuon());
			st.setDate(3, t.getHanTra());
			st.setDate(4, t.getNgayTra());
			st.setString(5, t.getPhieu_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}
	
	public PhieuMuon getLastPhieuMuonByMSV(SinhVien sv) {
		PhieuMuon phieumuon = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM phieumuon "
				+ "WHERE sinhVien_id = ? "
				+ "ORDER BY phieu_id DESC "
				+ "LIMIT 1;";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, sv.getMsv());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String phieu_id = rs.getString("phieu_id");
				String sinhVien_id = rs.getString("sinhVien_id");
				Date ngayMuon = rs.getDate("ngayMuon");
				Date hanTra = rs.getDate("hanTra");
				Date ngayTra = rs.getDate("ngayTra");
				
				phieumuon = new PhieuMuon(phieu_id, sv, ngayMuon, hanTra, ngayTra);
				
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return phieumuon;
	}
	public ArrayList<PhieuMuon> SelectAllPhieuMuonByMSV(SinhVien sv) {
		ArrayList<PhieuMuon> my_phieumuon = new ArrayList<PhieuMuon>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM phieumuon "
				+ "WHERE sinhVien_id = ? "
				+ "ORDER BY phieu_id DESC ";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, sv.getMsv());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String phieu_id = rs.getString("phieu_id");
				String sinhVien_id = rs.getString("sinhVien_id");
				Date ngayMuon = rs.getDate("ngayMuon");
				Date hanTra = rs.getDate("hanTra");
				Date ngayTra = rs.getDate("ngayTra");
				
				PhieuMuon phieumuon = new PhieuMuon(phieu_id, sv, ngayMuon, hanTra, ngayTra);
				my_phieumuon.add(phieumuon);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return my_phieumuon;
	}
}
