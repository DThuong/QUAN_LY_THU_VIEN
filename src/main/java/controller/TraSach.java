package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.PhieuMuonDAO;
import model.PhieuMuon;

/**
 * Servlet implementation class TraSach
 */
@WebServlet("/tra-sach")
public class TraSach extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraSach() {
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
		
		String phieu_id = request.getParameter("phieu");
		PhieuMuon phieu = new PhieuMuon();
		phieu.setPhieu_id(phieu_id);
		phieu = new PhieuMuonDAO().selectById(phieu);
		LocalDate today = LocalDate.now();
	    // Chuyển đổi thành java.sql.Date
	    Date sqlDate_today = Date.valueOf(today);
	    phieu.setNgayTra(sqlDate_today);
	    new PhieuMuonDAO().update(phieu);
	    String url = "/admin";
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
