package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.cj.xdevapi.PreparableStatement;

import model.Book;
import model.TacGia;
import model.TheLoai;

public class BookDAO implements DAOInterface<Book> {

	@Override
	public ArrayList<Book> selectAll() {
		ArrayList<Book> book_list = new ArrayList<Book>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM book ORDER BY theLoai_id DESC;";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String book_id = rs.getString("book_id");
				String tenSach = rs.getString("tenSach");
				String tacGia_id = rs.getString("tacGia_id");
				String nhaXuatBan = rs.getString("nhaXuatBan");
				int namXuatBan = rs.getInt("namXuatBan");
				int soLuongCon = rs.getInt("soLuongCon");
				int soLuongMuon = rs.getInt("soLuongMuon");
				String moTa = rs.getString("moTa");
				String theLoai_id = rs.getString("theLoai_id");
				String link_img = rs.getString("link_img");
				
				TacGia author = new TacGia();
				author.setTacGia_id(tacGia_id);
				TacGia tacgia = new TacGiaDAO().selectById(author);
				
				TheLoai loai = new TheLoai();
				loai.setTheLoai_id(theLoai_id);
				TheLoai theloai = new TheLoaiDAO().selectById(loai);
				
				Book sach = new Book(book_id,tenSach, tacgia, nhaXuatBan, namXuatBan, soLuongCon, soLuongMuon, moTa, theloai, link_img);
				book_list.add(sach);
						
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return book_list;
	}

	@Override
	public Book selectById(Book t) {
		Book sach = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM book WHERE book_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getBook_id());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String book_id = rs.getString("book_id");
				String tenSach = rs.getString("tenSach");
				String tacGia_id = rs.getString("tacGia_id");
				String nhaXuatBan = rs.getString("nhaXuatBan");
				int namXuatBan = rs.getInt("namXuatBan");
				int soLuongCon = rs.getInt("soLuongCon");
				int soLuongMuon = rs.getInt("soLuongMuon");
				String moTa = rs.getString("moTa");
				String theLoai_id = rs.getString("theLoai_id");
				String link_img = rs.getString("link_img");
				
				TacGia author = new TacGia();
				author.setTacGia_id(tacGia_id);
				TacGia tacgia = new TacGiaDAO().selectById(author);
				
				TheLoai loai = new TheLoai();
				loai.setTheLoai_id(theLoai_id);
				TheLoai theloai = new TheLoaiDAO().selectById(loai);
				
				sach = new Book(book_id,tenSach, tacgia, nhaXuatBan, namXuatBan, soLuongCon, soLuongMuon, moTa, theloai, link_img);
				JDBCUtil.closeConnection(conn);		
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sach;
	}

	@Override
	public int insert(Book t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO book(book_id, tacGia_id, nhaXuatBan, namXuatBan, soLuongCon, soLuongMuon, moTa, theLoai_id, tenSach) "+
				"VALUES(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getBook_id());
			st.setString(2, t.getTacGia().getTacGia_id());
			st.setString(3, t.getNhaXuatBan());
			st.setInt(4, t.getNamXuatBan());
			st.setInt(5, t.getSoLuongTrenThuVien());
			st.setInt(6, t.getSoLuongDaMuon());
			st.setString(7, t.getMoTa());
			st.setString(8, t.getTheLoai().getTheLoai_id());
			st.setString(9, t.getTenSach());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
	public int insert2(Book t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO book(book_id, tacGia_id, nhaXuatBan, namXuatBan, soLuongCon, soLuongMuon, moTa, theLoai_id, tenSach, link_img) "+
				"VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getBook_id());
			st.setString(2, t.getTacGia().getTacGia_id());
			st.setString(3, t.getNhaXuatBan());
			st.setInt(4, t.getNamXuatBan());
			st.setInt(5, t.getSoLuongTrenThuVien());
			st.setInt(6, t.getSoLuongDaMuon());
			st.setString(7, t.getMoTa());
			st.setString(8, t.getTheLoai().getTheLoai_id());
			st.setString(10, t.getLink_img());
			st.setString(9, t.getTenSach());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<Book> arr) {
		int kq = 0;
		for (Book book : arr) {
			kq += this.insert(book);
		}
		return kq;
	}

	@Override
	public int delete(Book t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM book WHERE book_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getBook_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<Book> arr) {
		int kq = 0;
		for (Book book : arr) {
			kq += this.delete(book);
		}
		return kq;
	}

	@Override
	public int update(Book t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE book SET "+
				"tacGia_id = ?, "+
				"nhaXuatBan = ?, "+
				"namXuatBan = ?, "+
				"soLuongCon = ?, "+
				"soLuongMuon = ?, "+
				"moTa = ?, "+
				"theLoai_id = ?, "+
				"tenSach = ? "+
				"WHERE book_id = ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTacGia().getTacGia_id());
			st.setString(2, t.getNhaXuatBan());
			st.setInt(3, t.getNamXuatBan());
			st.setInt(4, t.getSoLuongTrenThuVien());
			st.setInt(5, t.getSoLuongDaMuon());
			st.setString(6, t.getMoTa());
			st.setString(7, t.getTheLoai().getTheLoai_id());
			st.setString(8, t.getTenSach());
			st.setString(9, t.getBook_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}
	public int update2(Book t) {
		int kq = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE book SET "+
				"tacGia_id = ?, "+
				"nhaXuatBan = ?, "+
				"namXuatBan = ?, "+
				"soLuongCon = ?, "+
				"soLuongMuon = ?, "+
				"moTa = ?, "+
				"theLoai_id = ?, "+
				"tenSach = ?, "+
				"link_img = ? "+
				"WHERE book_id = ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTacGia().getTacGia_id());
			st.setString(2, t.getNhaXuatBan());
			st.setInt(3, t.getNamXuatBan());
			st.setInt(4, t.getSoLuongTrenThuVien());
			st.setInt(5, t.getSoLuongDaMuon());
			st.setString(6, t.getMoTa());
			st.setString(7, t.getTheLoai().getTheLoai_id());
			st.setString(8, t.getTenSach());
			st.setString(9, t.getLink_img());
			st.setString(10, t.getBook_id());
			
			kq = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}
	
	public ArrayList<Book> selectAllTheLoaiID(String theloadID) {
		ArrayList<Book> book_list = new ArrayList<Book>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM book WHERE theLoai_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, theloadID);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String book_id = rs.getString("book_id");
				String tenSach = rs.getString("tenSach");
				String tacGia_id = rs.getString("tacGia_id");
				String nhaXuatBan = rs.getString("nhaXuatBan");
				int namXuatBan = rs.getInt("namXuatBan");
				int soLuongCon = rs.getInt("soLuongCon");
				int soLuongMuon = rs.getInt("soLuongMuon");
				String moTa = rs.getString("moTa");
				String theLoai_id = rs.getString("theLoai_id");
				String link_img = rs.getString("link_img");
				
				TacGia author = new TacGia();
				author.setTacGia_id(tacGia_id);
				TacGia tacgia = new TacGiaDAO().selectById(author);
				
				TheLoai loai = new TheLoai();
				loai.setTheLoai_id(theLoai_id);
				TheLoai theloai = new TheLoaiDAO().selectById(loai);
				
				Book sach = new Book(book_id,tenSach, tacgia, nhaXuatBan, namXuatBan, soLuongCon, soLuongMuon, moTa, theloai, link_img);
				book_list.add(sach);
				
				
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return book_list;
	}
	public ArrayList<Book> selectTopBook() {
		ArrayList<Book> book_list = new ArrayList<Book>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM book "
				+ "ORDER BY book.soLuongMuon DESC "
				+ "LIMIT 15;";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String book_id = rs.getString("book_id");
				String tenSach = rs.getString("tenSach");
				String tacGia_id = rs.getString("tacGia_id");
				String nhaXuatBan = rs.getString("nhaXuatBan");
				int namXuatBan = rs.getInt("namXuatBan");
				int soLuongCon = rs.getInt("soLuongCon");
				int soLuongMuon = rs.getInt("soLuongMuon");
				String moTa = rs.getString("moTa");
				String theLoai_id = rs.getString("theLoai_id");
				String link_img = rs.getString("link_img");
				
				TacGia author = new TacGia();
				author.setTacGia_id(tacGia_id);
				TacGia tacgia = new TacGiaDAO().selectById(author);
				
				TheLoai loai = new TheLoai();
				loai.setTheLoai_id(theLoai_id);
				TheLoai theloai = new TheLoaiDAO().selectById(loai);
				
				Book sach = new Book(book_id,tenSach, tacgia, nhaXuatBan, namXuatBan, soLuongCon, soLuongMuon, moTa, theloai,link_img);
				book_list.add(sach);
				
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return book_list;
	}
	public int CountByTheLoai(String TheLoaiID) {
		int kq = 0;
		String sql = "SELECT COUNT(book_id) FROM book WHERE theLoai_id = ?";
		try {
			Connection conn = JDBCUtil.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, TheLoaiID);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				kq = rs.getInt("COUNT(book_id)");
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
	public ArrayList<Book> selectBookByPage(int numberPage, String maTheLoai) {
		int indexBook = (numberPage - 1) * 5;
		ArrayList<Book> book_list = new ArrayList<Book>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM book WHERE book.theLoai_id = ? LIMIT 10 OFFSET ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, maTheLoai);
			st.setInt(2, indexBook);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String book_id = rs.getString("book_id");
				String tenSach = rs.getString("tenSach");
				String tacGia_id = rs.getString("tacGia_id");
				String nhaXuatBan = rs.getString("nhaXuatBan");
				int namXuatBan = rs.getInt("namXuatBan");
				int soLuongCon = rs.getInt("soLuongCon");
				int soLuongMuon = rs.getInt("soLuongMuon");
				String moTa = rs.getString("moTa");
				String theLoai_id = rs.getString("theLoai_id");
				String link_img = rs.getString("link_img");
				TacGia author = new TacGia();
				author.setTacGia_id(tacGia_id);
				TacGia tacgia = new TacGiaDAO().selectById(author);
				
				TheLoai loai = new TheLoai();
				loai.setTheLoai_id(theLoai_id);
				TheLoai theloai = new TheLoaiDAO().selectById(loai);
				
				Book sach = new Book(book_id,tenSach, tacgia, nhaXuatBan, namXuatBan, soLuongCon, soLuongMuon, moTa, theloai, link_img);
				book_list.add(sach);
				
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book_list;
	}
	
	public ArrayList<Book> SearchBooks(String name) {
		ArrayList<Book> book_list = new ArrayList<Book>();
//		name = "\"%"+ name + "%\"";
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM book WHERE book.tenSach LIKE "+"\"%"+ name + "%\"";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
//			st.setString(1, name);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String book_id = rs.getString("book_id");
				String tenSach = rs.getString("tenSach");
				String tacGia_id = rs.getString("tacGia_id");
				String nhaXuatBan = rs.getString("nhaXuatBan");
				int namXuatBan = rs.getInt("namXuatBan");
				int soLuongCon = rs.getInt("soLuongCon");
				int soLuongMuon = rs.getInt("soLuongMuon");
				String moTa = rs.getString("moTa");
				String theLoai_id = rs.getString("theLoai_id");
				String link_img = rs.getString("link_img");
				TacGia author = new TacGia();
				author.setTacGia_id(tacGia_id);
				TacGia tacgia = new TacGiaDAO().selectById(author);
				
				TheLoai loai = new TheLoai();
				loai.setTheLoai_id(theLoai_id);
				TheLoai theloai = new TheLoaiDAO().selectById(loai);
				
				Book sach = new Book(book_id,tenSach, tacgia, nhaXuatBan, namXuatBan, soLuongCon, soLuongMuon, moTa, theloai, link_img);
				book_list.add(sach);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book_list;
	}
	
}
