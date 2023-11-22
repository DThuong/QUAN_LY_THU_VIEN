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

import database.BookDAO;
import database.KeSachDAO;
import model.Book;
import model.TaiKhoan;

/**
 * Servlet implementation class GioSach
 */
@WebServlet("/ke-sach")
public class GioSach extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GioSach() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(false); 
		TaiKhoan acc = (TaiKhoan) session.getAttribute("user");
		ArrayList<Book> book_cart = (ArrayList<Book>) session.getAttribute("book_cart");
		
		String add_book_id = request.getParameter("add-book");
		String remove_book_id = request.getParameter("xoa-sach");
		String action = request.getParameter("action");
		if(acc == null) {
			String pre_url = request.getRequestURI();
			request.setAttribute("pre_url", pre_url);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}else {
			if(add_book_id != null) {
				Book sach = new Book();
				sach.setBook_id(add_book_id);
				sach = new BookDAO().selectById(sach);
				if(!book_cart.contains(sach)) {
					book_cart.add(sach);
					new KeSachDAO().addBookToCart(acc, sach);
				}
			}
			if(remove_book_id != null) {
				Book sach = new Book();
				sach.setBook_id(remove_book_id);
				sach = new BookDAO().selectById(sach);
				book_cart.remove(sach);
				new KeSachDAO().deleteBookToCart(acc, sach);
			}
			int check = 0;
			if("xem".equals(action)) {
				check = 1;
			}
			request.setAttribute("xemKeSach", check);
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
