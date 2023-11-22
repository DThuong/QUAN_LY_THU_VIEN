package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.BookDAO;
import database.ChiTietPhieuMuonDAO;
import database.PhieuMuonDAO;
import database.SinhVienDAO;
import model.Book;
import model.ChiTietPhieuMuon;
import model.PhieuMuon;
import model.SinhVien;
import model.TaiKhoan;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/dat-sach")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booking() {
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
		
		SinhVien sv = new SinhVien();
		sv.setMsv(acc.getUser_id());
		sv = new SinhVienDAO().selectById(sv);
		
		// tạo thông tin phiếu mượn
		PhieuMuon phieumuon = new PhieuMuon();
		phieumuon.setSinhVien(sv);
		phieumuon.setNgayMuon(new java.sql.Date(new java.util.Date().getTime()));
		phieumuon.setHanTra(java.sql.Date.valueOf(LocalDate.now().plusMonths(1)));
		phieumuon.setNgayTra(null);
		new PhieuMuonDAO().insert(phieumuon);
		
		phieumuon = new PhieuMuonDAO().getLastPhieuMuonByMSV(sv);
		boolean check = false;
		ArrayList<ChiTietPhieuMuon> ctpm_MyList = new ArrayList<ChiTietPhieuMuon>();
		ArrayList<String> err_soLuong = new ArrayList<String>();
		for (Book book : book_cart) {
			String check_order = request.getParameter(book.getBook_id());
			if("on".equals(check_order)) {
				check = true;
				String slg = request.getParameter("soLuongMuon-"+book.getBook_id());
				int soLuong = Integer.parseInt(slg);
				if(soLuong <= book.getSoLuongTrenThuVien()){
					//tạo thông tin chi tiết từng phiếu mượn
					ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon();
					ctpm.setSach(book);
					ctpm.setSoLuong(soLuong);
					ctpm.setPhieu(phieumuon);
					
					ctpm_MyList.add(ctpm);
				}else {
					check = false;
					String err = "Sách " + book.getTenSach() + " còn số lượng ít, Xin vui lòng chọn số lượng ít hơn hoặc mượn sau!!!\n";
					err_soLuong.add(err);
				}
				
			}
		}
		String url = "/ke-sach?action=xem";
		if(check) {
			request.setAttribute("BookingComplete", "Bạn đã đặt sách thành công, Vui lòng vào danh sách phiếu mượn để xem chi tiết phiếu mượn");
			new ChiTietPhieuMuonDAO().insertAll(ctpm_MyList);
			for(ChiTietPhieuMuon ctpm : ctpm_MyList) {
				ctpm.getSach().setSoLuongDaMuon(ctpm.getSach().getSoLuongDaMuon()+ctpm.getSoLuong());
				ctpm.getSach().setSoLuongTrenThuVien(ctpm.getSach().getSoLuongTrenThuVien()-ctpm.getSoLuong());
				new BookDAO().update(ctpm.getSach());
			}
		}else {
			new PhieuMuonDAO().delete(phieumuon);
			request.setAttribute("err_soLuong", err_soLuong);
		}
		
		
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
