package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BookDAO;
import database.PhieuMuonDAO;
import database.SinhVienDAO;
import database.TaiKhoanDAO;
import model.Book;
import model.PhieuMuon;
import model.SinhVien;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
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
		
		String act = request.getParameter("quan-ly");
		String themSoLuong = request.getParameter("soLuongThemVao");
		if(themSoLuong != null) {
			int soLuong = Integer.parseInt(themSoLuong);
			String book_id = request.getParameter("book_id");
			Book sach = new Book();
			sach.setBook_id(book_id);
			sach = new BookDAO().selectById(sach);
			sach.setSoLuongTrenThuVien(soLuong + sach.getSoLuongTrenThuVien());
			new BookDAO().update(sach);
			
			act = "sach";
		}
		if("phieu".equals(act) || act == null) {
			ArrayList<PhieuMuon> phieu_list = null;
			phieu_list = new PhieuMuonDAO().selectAll();
			request.setAttribute("ql_phieu", phieu_list);
			String url = "/admin_home.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
		if("sach".equals(act)) {
			String add = request.getParameter("add");
			String update = request.getParameter("update");
			if(add != null) {
				Book sach = new Book();
				sach.setBook_id(add);
				sach = new BookDAO().selectById(sach);
				request.setAttribute("themSoLuong", true);
				request.setAttribute("BookEdit", sach);
			}
			if(update != null) {
				Book sach = new Book();
				sach.setBook_id(update);
				sach = new BookDAO().selectById(sach);
				request.setAttribute("updateBook", true);
				request.setAttribute("BookEdit", sach);
				
			}
			if(add == null && update == null) {
				ArrayList<Book> book_list = null;
				book_list = new BookDAO().selectAll();
				request.setAttribute("ql_sach", book_list);
			}
			
			String url = "/admin_quanlysach.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
		if("sinhvien".equals(act)) {
			String svID = request.getParameter("svID");
			String update = request.getParameter("update");
			if(svID != null) {
				SinhVien sv = new SinhVien();
				sv.setMsv(svID);
				sv = new SinhVienDAO().selectById(sv);
				
				String username_sv = new TaiKhoanDAO().selectUsernameById(svID);
				request.setAttribute("infoSV", sv);
				request.setAttribute("usernameSV", username_sv);
				String url = "/admin_quanlysinhvien.jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
				rd.forward(request, response);
			}
			if(update != null){
				String id = request.getParameter("msv");
				String hoVaTen = request.getParameter("hoVaTen");
				String dob = request.getParameter("dob");
				String gender = request.getParameter("sex");
				String address = request.getParameter("address");
				String email = request.getParameter("myEmail");
				String phone = request.getParameter("phone");
				String classID = request.getParameter("classID");
				
				SinhVien sv = new SinhVien(id, hoVaTen, Date.valueOf(dob), classID, gender, address, email, phone);
				new SinhVienDAO().update(sv);
				
				update = null;	
				
			}
			if(update == null && svID == null) {
				ArrayList<SinhVien> list_sv = null;
				list_sv = new SinhVienDAO().selectAll();
				request.setAttribute("list_sv", list_sv);
				String url = "/admin_quanlysinhvien.jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
				rd.forward(request, response);
			}
				
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
