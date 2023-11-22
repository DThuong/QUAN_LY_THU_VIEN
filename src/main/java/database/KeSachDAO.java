package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Book;
import model.TaiKhoan;

public class KeSachDAO {
	public ArrayList<Book> selectCartByUsername(TaiKhoan acc) {
		ArrayList<Book> books_cart = new ArrayList<Book>();
		ArrayList<String> bookid_cart = new ArrayList<String>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT book_id FROM kesach WHERE username = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, acc.getUsername());
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				String book_id = rs.getString("book_id");
				
				bookid_cart.add(book_id);
			}
			JDBCUtil.closeConnection(conn);
		
		for (String book_id : bookid_cart) {
			Book sach = new Book();
			sach.setBook_id(book_id);
			sach = new BookDAO().selectById(sach);
			
			books_cart.add(sach);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return books_cart;
	}
	public int addBookToCart(TaiKhoan acc, Book sach) {
		int res = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO kesach(username, book_id) VALUES (?,?)";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, acc.getUsername());
			st.setString(2, sach.getBook_id());
			
			res = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int deleteBookToCart(TaiKhoan acc, Book sach) {
		int res = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM kesach WHERE username = ? AND book_id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, acc.getUsername());
			st.setString(2, sach.getBook_id());
			
			res = st.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
//	public static void main(String[] args) {
//		TaiKhoan acc = new TaiKhoan();
//		acc.setUsername("thachnn");
//		 ArrayList<Book> cart = new KeSachDAO().selectCartByUsername(acc);
//		 for (Book book : cart) {
//			System.out.println(book.toString());
//		}
//	}
}
