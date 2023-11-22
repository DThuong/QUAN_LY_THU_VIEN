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

import database.ChiTietPhieuMuonDAO;
import database.PhieuMuonDAO;
import model.ChiTietPhieuMuon;
import model.PhieuMuon;
import model.TaiKhoan;

/**
 * Servlet implementation class ChiTietPhieuMuonController
 */
@WebServlet("/chi-tiet-phieu-muon")
public class ChiTietPhieuMuonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChiTietPhieuMuonController() {
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
		
		String phieu_id = request.getParameter("phieu_id");
		ArrayList<ChiTietPhieuMuon> ctpm_list = new ArrayList<ChiTietPhieuMuon>();
		PhieuMuon phieu = new PhieuMuon();
		phieu.setPhieu_id(phieu_id);
		
		phieu = new PhieuMuonDAO().selectById(phieu);
		ctpm_list = new ChiTietPhieuMuonDAO().selectByPhieuMuon(phieu_id);
		request.setAttribute("ctpm", ctpm_list);
		request.setAttribute("phieu", phieu);
		if(acc.isUserType() == true) {
			String url = "/admin_home.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}else {
			String url = "/home";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
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
