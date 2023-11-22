package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BookDAO;
import database.TacGiaDAO;
import database.TheLoaiDAO;
import model.Book;
import model.TacGia;
import model.TheLoai;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/admin-add-book")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int author_age = 0;
		try {
			author_age = Integer.parseInt((String)request.getParameter("author-age"));
		} catch (Exception e) {
			// TODO: handle exception
			author_age = 0;
		}

		String link_img = request.getParameter("book-image");
		String author_id = request.getParameter("author-id");
		String author_name = request.getParameter("author-name");
		
		String author_notes = request.getParameter("author-notes");
		String book_id = request.getParameter("book-id");
		String book_title = request.getParameter("book-title");
		String book_genre = request.getParameter("book-genre");
		String book_genre_name= request.getParameter("book-genre-name");
		String nhaXuatBan = request.getParameter("nhaXuatBan");
		int namXuatBan = Integer.parseInt((String)request.getParameter("namXuatBan"));
		String book_description = request.getParameter("book-description");
		int soLuong = Integer.parseInt((String)request.getParameter("book-quantity"));
		
		System.out.println((String)request.getParameter("namXuatBan"));
		System.out.println((String)request.getParameter("book-quantity"));
		// xử lý tác giả
		TacGia tacgia = new TacGia();
		tacgia.setTacGia_id(author_id);
		tacgia = new TacGiaDAO().selectById(tacgia);
		if(tacgia == null) {
			tacgia.setTacGia_id(author_id);
			tacgia.setTen_tacGia(author_name);
			tacgia.setGhiChu(author_notes);
			tacgia.setNamSinh(author_age);
			new TacGiaDAO().insert(tacgia);
		}else {
			tacgia = new TacGia(author_id, author_name, author_age, author_notes);
			new TacGiaDAO().update(tacgia);
		}
		// xử lý thể loại
		TheLoai theloai = new TheLoai();
		theloai.setTheLoai_id(book_genre);
		theloai = new TheLoaiDAO().selectById(theloai);
		if(theloai == null) {
			theloai = new TheLoai(book_genre, book_genre_name);
			new TheLoaiDAO().insert(theloai);
		}
		// xử lý sách
		Book sach = new Book();
		sach.setBook_id(book_id);
		sach = new BookDAO().selectById(sach);
		if(sach == null) {
			sach.setBook_id(book_id);
			sach.setLink_img(link_img);
			sach.setTenSach(book_title);
			sach.setNhaXuatBan(nhaXuatBan);
			sach.setNamXuatBan(namXuatBan);
			sach.setMoTa(book_description);
			sach.setTacGia(tacgia);
			sach.setTheLoai(theloai);
			new BookDAO().insert2(sach);
		}
		else {
			sach.setBook_id(book_id);
			sach.setLink_img(link_img);
			sach.setTenSach(book_title);
			sach.setNhaXuatBan(nhaXuatBan);
			sach.setNamXuatBan(namXuatBan);
			sach.setMoTa(book_description);
			sach.setTacGia(tacgia);
			sach.setTheLoai(theloai);
			new BookDAO().update2(sach);
		}
		String url = "/admin?quan-ly=sach";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
