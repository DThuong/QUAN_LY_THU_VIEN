package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.PhieuMuonDAO;
import database.SinhVienDAO;
import model.Book;
import model.PhieuMuon;
import model.SinhVien;
import model.TaiKhoan;

/**
 * Servlet implementation class MyOrdersController
 */
@WebServlet("/phieu-muon-cua-toi")
public class MyOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyOrdersController() {
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
		
		HttpSession session = request.getSession(false); 
		TaiKhoan acc = (TaiKhoan) session.getAttribute("user");
		ArrayList<Book> book_cart = (ArrayList<Book>) session.getAttribute("book_cart");
		
		ArrayList<PhieuMuon> MyOders = new ArrayList<PhieuMuon>();
		SinhVien sv = new SinhVien();
		sv.setMsv(acc.getUser_id());
		sv = new SinhVienDAO().selectById(sv);
		
		MyOders = new PhieuMuonDAO().SelectAllPhieuMuonByMSV(sv);
		request.setAttribute("MyOrders", MyOders);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/home");
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
