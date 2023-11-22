package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.TacGia;
import model.TaiKhoan;

public class TaiKhoanDAO implements DAOInterface<TaiKhoan>{

	@Override
	public ArrayList<TaiKhoan> selectAll() {
		ArrayList<TaiKhoan> users = new ArrayList<TaiKhoan>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM taikhoan";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String user_id = rs.getString("user_id");
				boolean userType = rs.getBoolean("userType");
				String salt = rs.getString("salt");
				TaiKhoan nguoiDung = new TaiKhoan(username, password, user_id, userType,salt);
				users.add(nguoiDung);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public TaiKhoan selectById(TaiKhoan t) {
		TaiKhoan user = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM taikhoan WHERE user_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getUser_id());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String user_id = rs.getString("user_id");
				boolean userType = rs.getBoolean("userType");
				String salt = rs.getString("salt");
				user = new TaiKhoan(username, password, user_id, userType, salt);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int insert(TaiKhoan t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO taikhoan(username, password, user_id, userType, salt) " +
					"VALUES(?,?,?,?,?);";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getUsername());
			st.setString(2, t.getPassword());
			st.setString(3, t.getUser_id());
			st.setBoolean(4, t.isUserType());
			st.setString(5, t.getSalt());
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<TaiKhoan> arr) {
		int kq = 0;
		for (TaiKhoan taiKhoan : arr) {
			kq += this.insert(taiKhoan);
		}
		return kq;
	}

	@Override
	public int delete(TaiKhoan t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM taikhoan WHERE user_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getUser_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<TaiKhoan> arr) {
		int kq = 0;
		for (TaiKhoan taiKhoan : arr) {
			kq += this.delete(taiKhoan);
		}
		return kq;
	}

	// update password
	@Override
	public int update(TaiKhoan t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE taikhoan SET "+
				"password = ?, "+
				"salt = ? "+
				"WHERE user_id = ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getPassword());
			st.setString(2, t.getSalt());
			st.setString(3, t.getUser_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}
	
	// find account by username
	public TaiKhoan selectByUsername(String tendangnhap) {
		TaiKhoan user = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM taikhoan WHERE username = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, tendangnhap);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String user_id = rs.getString("user_id");
				boolean userType = rs.getBoolean("userType");
				String salt = rs.getString("salt");
				user = new TaiKhoan(username, password, user_id, userType, salt);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}
	public String selectUsernameById(String id) {
		String username = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT username FROM taikhoan WHERE user_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				username = rs.getString("username");
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return username;
	}
}
