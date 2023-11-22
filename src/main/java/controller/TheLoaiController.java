package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BookDAO;
import database.TheLoaiDAO;
import model.Book;
import model.TheLoai;

/**
 * Servlet implementation class TheLoaiController
 */
@WebServlet("/the-loai")
public class TheLoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheLoaiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int nop = 0;
		if(request.getParameter("page")==null) {
			String theloaiID = request.getParameter("theloaiID");
			ArrayList<Book> book_list = new ArrayList<Book>();
			book_list = new BookDAO().selectBookByPage(1, theloaiID);
			nop = new BookDAO().CountByTheLoai(theloaiID);
			request.setAttribute("book_list", book_list);
			request.setAttribute("nowPage", (int)1);
			request.setAttribute("maTheLoai", theloaiID);
			
			
		}
		if(request.getParameter("page")!=null) {
			String theloaiID = request.getParameter("theloai");
			String numberPage = request.getParameter("page");
			int page = Integer.parseInt(numberPage);
			ArrayList<Book> book_list = new ArrayList<Book>();
			book_list = new BookDAO().selectBookByPage(page, theloaiID);
			nop = new BookDAO().CountByTheLoai(theloaiID);
			request.setAttribute("book_list", book_list);
			request.setAttribute("nowPage", page);
			request.setAttribute("maTheLoai", theloaiID);
		}
		if(nop % 10 == 0) nop = nop/10;
		else nop = nop/10 +1;
		request.setAttribute("numberOfPages", nop);
		
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
