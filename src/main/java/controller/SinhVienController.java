package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import database.GiaoVienDAO;
import database.KeSachDAO;
import database.RegisterDB;
import database.SinhVienDAO;
import database.TaiKhoanDAO;
import database.TheLoaiDAO;
import model.Book;
import model.GiaoVien;
import model.SinhVien;
import model.TaiKhoan;
import model.TheLoai;

/**
 * Servlet implementation class TaiKhoanController
 */
@WebServlet("/home")
public class SinhVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinhVienController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		TheLoaiDAO theloaiDAO = new TheLoaiDAO();
		
		HttpSession session = request.getSession(false);
		TaiKhoan acc = (TaiKhoan) session.getAttribute("user");
		String hanhDong = request.getParameter("act");
		ArrayList<TheLoai> list_theloai = theloaiDAO.selectAll();
		session.setAttribute("list_theloai", list_theloai);
		if(hanhDong == null) {
			String url = "";
			if(acc == null || !acc.isUserType()) {
				url = "/index.jsp";
			}else {
				url = "/admin";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
		if("login".equals(hanhDong)) { 
			Login(request, response);
		}
		if("logout".equals(hanhDong)) {
			Logout(request, response);
		}
		if("register".equals(hanhDong)){
			Register(request, response);
		}
		if("infoUser".equals(hanhDong)) {
			InfoUser(request, response);
		}
		if("changPassword".equals(hanhDong)) {
			ChangPassword(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void Login(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			TaiKhoanDAO taikhoanDAO = new TaiKhoanDAO();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			boolean check_err = false;
			
			String err_username = "";
			String err_password = "";
			TaiKhoan acc = taikhoanDAO.selectByUsername(username);
			if(acc == null) {
				err_username = "Tên đăng nhập không tồn tại!";
				check_err = true;
			}else {
				String salt = acc.getSalt();
				if(!BCrypt.checkpw(password, acc.getPassword())) {
					err_password = "Mật khẩu không chính xác!";
					check_err = true;
				}
			}
			
			String url = "/muon-nhieu-nhat";
			String url_admin = "/admin";
			boolean isAdmin = acc.isUserType();
			if(check_err) {
				request.setAttribute("username", username);
				request.setAttribute("err_username", err_username);
				request.setAttribute("err_password", err_password);
			}else {
				System.out.println("Tài khoản admin:" + acc.isUserType());
				if(!isAdmin) {
					SinhVien sv = new SinhVien();
					sv.setMsv(acc.getUser_id());
					SinhVien me = new SinhVienDAO().selectById(sv);
					ArrayList<Book> book_cart = new ArrayList<Book>();
					book_cart = new KeSachDAO().selectCartByUsername(acc);
					for (Book book : book_cart) {
						System.out.println();
					}
					HttpSession session = request.getSession();
					session.setAttribute("user", acc);
					session.setAttribute("myInfo", me);
					session.setAttribute("book_cart", book_cart);
				}
				if(isAdmin){
					GiaoVien gv = new GiaoVien();
					gv.setGiaoVien_id(acc.getUser_id());
					GiaoVien admin = new GiaoVienDAO().selectById(gv);
					
					HttpSession session = request.getSession();
					session.setAttribute("admin_info", admin);
					session.setAttribute("user", acc);
				}
				
			}
			if(check_err) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}else {
				if(!isAdmin) {
					RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher(url_admin);
					rd.forward(request, response);
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void Register(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			TaiKhoanDAO taikhoanDAO = new TaiKhoanDAO();
			SinhVienDAO sinhvienDAO = new SinhVienDAO();
			SinhVien sv = new SinhVien();
			TaiKhoan acc = new TaiKhoan();
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
			String student_id = request.getParameter("student_id");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String date_of_birth = request.getParameter("ngaySinh");
			String gender = request.getParameter("gender");
			String re_password = request.getParameter("re_password");
			
			request.setAttribute("username", username);
			request.setAttribute("fullname", fullname);
			request.setAttribute("student_id", student_id);
			request.setAttribute("email", email);
			request.setAttribute("phone", phone);
			request.setAttribute("ngaySinh", date_of_birth);
			request.setAttribute("gender", gender);
			
			boolean check_err = false;
			String err_username = "";
			String err_stuID = "";
			String err_password = "";
			String err_ngaysinh = "";
			String err_rePassword = "";
			//in hoa toàn bộ mã sinh viên 
			student_id.toUpperCase();
			// check username
			if(taikhoanDAO.selectByUsername(username)!= null) {
				err_username = "Tên đăng nhập đã tồn tại!";
				check_err = true;
			}
			//check password > 8 charater
			if(password.length() < 8) {
				err_password = "Mật khẩu phải có 8 ký tự trở lên";
				check_err = true;
			}
			//check re-password
			if(!password.equals(re_password)) {
				err_rePassword = "Nhập lại mật khẩu";
				check_err = true;
			}
			// check ngày sinh
			Date dob = Date.valueOf(date_of_birth);
			//check mã sinh viên
			sv.setMsv(student_id);
			if(sinhvienDAO.selectById(sv)!= null) {
				err_stuID = "Mã sinh viên này đã tồn tại tài khoản!";
				check_err = true;
			}
			if(check_err) {
				request.setAttribute("err_username", err_username);
				request.setAttribute("err_stuID", err_stuID);
				request.setAttribute("err_password", err_password);
				request.setAttribute("err_ngaysinh", err_ngaysinh);
				request.setAttribute("err_rePassword", err_rePassword);
				String url = "/register.jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
				rd.forward(request, response);
			}else
			{	
				String salt = BCrypt.gensalt();
				System.out.println(salt);
				String hashPassword = BCrypt.hashpw(password, salt);
				acc = new TaiKhoan(username, hashPassword, student_id, false, salt);
				taikhoanDAO.insert(acc);
				
				SinhVien sv_o = new SinhVien(student_id, fullname, dob, "", gender, "", email, phone);
				sinhvienDAO.insert(sv_o);
				
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void InfoUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			String classID = request.getParameter("classID");
			String gender = request.getParameter("sex");
			String address = request.getParameter("address");
			String myEmail = request.getParameter("myEmail");
			String phone = request.getParameter("phone");
			
			HttpSession session = request.getSession();
			SinhVien myInfo = (SinhVien)session.getAttribute("myInfo");
			myInfo.setGioiTinh(gender);
			myInfo.setEmail(myEmail);
			myInfo.setPhone(phone);
			myInfo.setDiaChi(address);
			myInfo.setMaLop(classID);
			
			new SinhVienDAO().update(myInfo);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void ChangPassword(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String reNewPassword = request.getParameter("re_newPassword");
			
			boolean check_err= false;
			TaiKhoan acc = null;
			String msg_err1 = "";
			String msg_err2 = "";
			String msg_err3 = "";
			HttpSession session = request.getSession();
			String salt = BCrypt.gensalt();
			String password = BCrypt.hashpw(newPassword, salt);
			if(session != null) {
				acc = (TaiKhoan) session.getAttribute("user");
			}
			if(!BCrypt.checkpw(oldPassword, acc.getPassword())) {
				check_err = true;
				msg_err1 = "Mật khẩu không chính xác";
				request.setAttribute("msg_err1", msg_err1);
			}
			if(!newPassword.equals(reNewPassword)) {
				check_err = true;
				msg_err2 = "Xin vui lòng nhập lại mật khẩu mới";
				request.setAttribute("msg_err2", msg_err2);
			}
			if(newPassword.equals(password)) {
				msg_err3 = "Mật khẩu mới phải khác mật khẩu hiện tại";
				request.setAttribute("msg_err3", msg_err3);
			}
			if(newPassword.length() < 8) {
				check_err = true;
				msg_err3 = "Mật khẩu mới tối thiểu 8 ký tự";
				request.setAttribute("msg_err3", msg_err3);
			}
			String url = "/ChangePassword.jsp";
			if(check_err) {
				
				url = "/ChangePassword.jsp";
			}else {
				request.setAttribute("notify", "Bạn đã đổi mật khẩu thành công");
				
				acc.setPassword(password);
				acc.setSalt(salt);
				new TaiKhoanDAO().update(acc);
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
