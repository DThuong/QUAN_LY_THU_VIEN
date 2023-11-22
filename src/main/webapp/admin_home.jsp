<%@page import="model.ChiTietPhieuMuon"%>
<%@page import="model.PhieuMuon"%>
<%@page import="model.Book"%>
<%@page import="model.TheLoai"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.TaiKhoan"%>
<%@page import="model.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ADMIN HOME</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/modern-normalize/0.7.0/modern-normalize.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=url%>/css/fontawesome-free-5.13.1-web/css/all.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link rel="stylesheet" href="<%=url%>/css/grid.css">
<link rel="stylesheet" href="<%=url%>/css/base.css">
<link rel="stylesheet" href="<%=url%>/css/main.css">
<link rel="stylesheet" href="<%=url%>/css/responsive.css">
<!-- <link rel="stylesheet" href="<%=url%>/css/productDetail.css"> -->
<link rel="stylesheet" href="<%=url%>/css/Info.css">
<link rel="stylesheet" href="<%=url%>/css/adm_manage.css">
<!--[if lte IE 6]>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js" integrity="sha512-qWVvreMuH9i0DrugcOtifxdtZVBBL0X75r9YweXsdCHtXUidlctw7NXg5KVP3ITPtqZ2S575A0wFkvgS2anqSA==" crossorigin="anonymous"></script>
    <![endif]-->

</head>
<body>
	<%
		ArrayList<PhieuMuon> list_phieu = new ArrayList<PhieuMuon>();
		if(request.getAttribute("ql_phieu")!=null){
			list_phieu = (ArrayList<PhieuMuon>)request.getAttribute("ql_phieu");
		}
		
		ArrayList<ChiTietPhieuMuon> ctpm = new ArrayList<ChiTietPhieuMuon>();
		if(request.getAttribute("ctpm")!=null){
			ctpm = (ArrayList<ChiTietPhieuMuon>)request.getAttribute("ctpm");
		}
		
		PhieuMuon phieu = null;
		if(request.getAttribute("phieu")!=null){
			phieu = (PhieuMuon)request.getAttribute("phieu");
		}
		/*
		int nowPage = 0;
		if(request.getAttribute("nowPage")!=null){
			nowPage = (int)request.getAttribute("nowPage");
		}
		String maTheLoai = "";
		if(request.getAttribute("maTheLoai")!=null){
			maTheLoai = (String)request.getAttribute("maTheLoai");
		}
		*/
	%>
	<!-- Block Element Modifier -->
	<div class="app">
		<header class="header">
			<div class="grid wide">
				<nav class="header__navbar hide-on-tablet-and-mobile">
					<ul class="header__navbar-list">
						<li
							class="header__navbar-item header__navbar-item--has-qr header__navbar-item--separate">
							Thư viện nhóm 5 <!-- Header QR code -->
							<div class="header__qr">
								<img src="./assets/img/qr_code.png" alt="QR code"
									class="header__qr-img">
								<div class="header__qr-apps">
									<a href="" class="header__qr-link"> <img
										src="./assets/img/google_play.png" alt=""
										class="header__qr-download-img">
									</a> <a href="" class="header__qr-link"> <img
										src="./assets/img/app_store.png" alt=""
										class="header__qr-download-img">
									</a>
								</div>
							</div>
						</li>
						<li class="header__navbar-item header__navbar-title--no-pointer ">
							<span class="">Kết nối</span> <a
							href="https://www.facebook.com/profile.php?id=100023585464185"
							class="header_navbar-icon-link"> <i
								class="header__navbar-icon fab fa-facebook"></i>
						</a> <a href="" class="header_navbar-icon-link"> <i
								class="header__navbar-icon fab fa-instagram"></i>
						</a>
						</li>
					</ul>
					<ul class="header__navbar-list">
						<%	
							Object obj = session.getAttribute("user");
							TaiKhoan acc = null;
							if(obj != null && obj instanceof TaiKhoan) {
								 acc = (TaiKhoan) obj;
							}
							if(acc != null){
						%>
						<li class="header__navbar-item header__navbar-user"><img
							src="./assets/img/logo.png" alt=""
							class="header__navbar-user-img"> <span
							class="header__navbar-user-name"><%=acc.getUsername() %></span>

							<ul class="header__navbar-user-menu">
								<li class="header__navbar-user-item"><a href="InfoUser.jsp">Thông
										tin các nhân</a></li>
								<li class="header__navbar-user-item"><a
									href="ChangePassword.jsp" id="changePassword">Đổi mật khẩu</a>
								</li>
								<li class="header__navbar-user-item">
								<a href="home?act=logout">Đăng xuất</a></li>
							</ul></li>
						<%}%>
					</ul>
				</nav>
				<!-- Header with search -->
				<div class="header-with-search">
					<div class="header__seacher-and-bar">
 -->
						<form action="tim-kiem" method="get" style="flex: 1;">
							<div class="header__search hide-on-mobile">
								<div class="header__search-input-wrap">
									<input type="text" class="header__search-input"
										id="searchButton" name="search"
										placeholder="Nhập để tìm kiếm sách">
								</div>
								<button type="submit" class="header__search-btn"
									id="searchButton">
									<i class="header__search-btn-icon fas fa-search"></i>
								</button>
							</div>
						</form>
					</div>
				</div>
		</header>

		<div class="app__container">
             <div class="grid wide">
                    <div class="row  sm-gutter app__content">
                        <div class="col l-2 m-0 c-0">
                              <nav class="category">
                                  <h3 class="category__heading">
                                      <i class="category__heading-icon fas fa-list"></i>
                                      Chức năng
                                  </h3>
                                  <ul class="category-list">
                                      <li class="category-item category-item--active">
                                          <a href="admin?quan-ly=phieu" class="category-item__link">Danh sách phiếu mượn</a>
                                      </li>
                                      <li class="category-item">
                                          <a href="admin?quan-ly=sach" class="category-item__link">Quản lý sách</a>
                                      </li>
                                      <li class="category-item">
                                          <a href="admin?quan-ly=sinhvien" class="category-item__link">Quản lý sinh viên</a>
                                      </li>
                                  </ul>
                            </nav>
                            
                        </div>
                        <div class="main-content">
                        <%
                        	if(phieu == null){                                       	
                       	%>                     
                           	<h1>Danh sách phiếu mượn</h1>                       		 
                            <table>
                                <thead>
                                    <tr>
                                    	<th style="width: 10%;">Mã phiếu mượn</th>
                                    	<th style="width: 15%;">Mã sinh viên</th>
                                        <th style="width: 30%;">Họ tên </th>                                      
                                        <th style="width: 15%;">Ngày mượn</th>
                                        <th style="width: 15%;">Ngày đến hạn</th>
                                        <th style="width: 15%;">Ngày trả</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		for(PhieuMuon phieu_item : list_phieu){
                                			String ngay_muon = Util.convertSqlDateToString(phieu_item.getNgayMuon());
                                			String han_tra = Util.convertSqlDateToString(phieu_item.getHanTra());
                                			String ngay_tra = Util.convertSqlDateToString(phieu_item.getNgayTra());
                                			
                                			String trangThai = "";
                                			if(phieu_item.getNgayTra()==null){
                                				 trangThai = "red";
                                			}
                                			else{
                                				int comparison = phieu_item.getNgayTra().compareTo(phieu_item.getHanTra());
                                				if(comparison < 0)
                                    			{                                			
                                    				trangThai = "green";
                                    			}
                                    			if(comparison > 0){
                                    				trangThai = "orange";
                                    			}
                                			}
                                			
                                	%>
                                		<tr style="color: <%=trangThai%>;">
                                			<td style="width: 10%;">
                                				<a href="chi-tiet-phieu-muon?phieu_id=<%=phieu_item.getPhieu_id()%>" style="color: <%=trangThai%>; text-decoration: none;" title="Nhấn vào xem chi tiết phiếu mượn">
                                					<%=phieu_item.getPhieu_id() %>
                                				</a>
                                			</td>
                                			<td style="width: 15%;">
                                				<a href="#" style="color: <%=trangThai%>; text-decoration: none;" title="Nhấn vào xem thông tin sinh viên">
                                					<%=phieu_item.getSinhVien().getMsv() %>
                                				</a>
                                			</td>
                               				<td style="width: 20%;">
                               					<a href="#" style="color: <%=trangThai%>; text-decoration: none;" title="Nhấn vào xem thông tin sinh viên">
                                					<%=phieu_item.getSinhVien().getHoVaTen() %>
                                				</a>
                               					
                               				</td> 			
                               				<td style="width: 15%;"><%=ngay_muon %></td>
                               				<td style="width: 15%;"><%=han_tra %></td>
                               				<td style="width: 15%;"><%=ngay_tra %></td>
                                		</tr>
                                	<%
                                		}
                                	%>
                                </tbody>
                            </table>
						<%}else{
						%>
							<h1>Thông tin chi tiết phiếu mượn</h1>
							<h3>Mã phiếu mượn   : <%=phieu.getPhieu_id()%> </h3>
							<h3>Họ tên sinh viên: <%=phieu.getSinhVien().getHoVaTen()%></h3>
							<h3>Mã sinh viên    : <%=phieu.getSinhVien().getMsv()%></h3>
							<h3>Ngày mượn       : <%=Util.convertSqlDateToString(phieu.getNgayMuon())%></h3>     
							<h3>Hạn trả	        : <%=Util.convertSqlDateToString(phieu.getHanTra())%></h3>
		 					<h3>Ngày trả		: <%=Util.convertSqlDateToString(phieu.getNgayTra()) %></h3>
                            <table>
                                <thead>
                                    <tr>
                                    	<th style="width: 15%;">Mã sách</th>
                                    	<th style="width: 45%;">Tên sách</th>
                                        <th style="width: 30%;">Tên tác giả</th>                                                                              
                                        <th style="width: 10%;">Số lượng mượn</th>                                                                              
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		for(ChiTietPhieuMuon CTphieu:ctpm){
                                	%>
                                		<tr>
                                			<td style="width: 15%;">
                                				<%=CTphieu.getSach().getBook_id()%>
                                			</td>
                                			<td style="width: 45%;">
                                				<%=CTphieu.getSach().getTenSach() %>
                                			</td>
                               				<td style="width: 30%;">                               					
                               					<%=CTphieu.getSach().getTacGia().getTen_tacGia() %>
                               				</td> 			
                               				<td style="width: 10%;">
                               					<%=CTphieu.getSoLuong() %>
                               				</td>
                               				
                                		</tr>
									<%}%>
								</tbody>
                          </table>
                          
                          <a href="tra-sach?phieu=<%=phieu.getPhieu_id()%>"><button>Xác nhận trả sách</button></a>
                    <%} %>
                    </div>
                    </div>
                    
            </div>    
        </div>

		<footer class="footer">
			<div class="grid wide">
				<div class="row">
					<div class="col l-2-4 m-4 c-12 ">
						<h3 class="footer__heading">Chăm sóc độc giả</h3>
						<ul class="footer-list">
							<li class="footer-item"><a href="" class="footer-item__link">Trung
									tâm trợ giúp</a></li>
							<li class="footer-item"><a href="" class="footer-item__link">Nhóm
									5</a></li>
							<li class="footer-item"><a href="" class="footer-item__link">Hướng
									dẫn mượn sách</a></li>
						</ul>
					</div>
					<div class="col l-2-4 m-4 c-12">
						<h3 class="footer__heading">Giới thiệu</h3>
						<ul class="footer-list">
							<li class="footer-item"><a href="" class="footer-item__link">Giới
									thiệu</a></li>
							<li class="footer-item"><a href="" class="footer-item__link">Tuyển
									dụng</a></li>
							<li class="footer-item"><a href="" class="footer-item__link">Điều
									khoản</a></li>
						</ul>
					</div>
					<div class="col l-2-4 m-4 c-12">
						<h3 class="footer__heading">Danh mục</h3>
						<ul class="footer-list">
							<li class="footer-item"><a href="" class="footer-item__link">Giáo
									trình</a></li>
							<li class="footer-item"><a href="" class="footer-item__link">Tiểu
									thuyết</a></li>
							<li class="footer-item"><a href="" class="footer-item__link">Tài
									liệu</a></li>
						</ul>
					</div>
					<div class="col l-2-4 m-4 c-12">
						<h3 class="footer__heading">Theo dõi</h3>
						<ul class="footer-list">
							<li class="footer-item"><a href="" class="footer-item__link">
									<i class="footer-item__icon fab fa-facebook"></i> Facebook
							</a></li>
							<li class="footer-item"><a href="" class="footer-item__link">
									<i class="footer-item__icon fab fa-instagram"></i> Instagram
							</a></li>
							<li class="footer-item"><a href="" class="footer-item__link">
									<i class=" footer-item__icon fab fa-linkedin"></i> Linkedin
							</a></li>
						</ul>
					</div>
					<div class="col l-2-4 m-4 c-12">
						<div class="footer__qr">
							<div class="footer__qr-apps">
								<a href="" class="footer__qr-link"> <img
									src="./assets/img/google_play.png" alt=""
									class="footer__qr-download-img">
								</a> <a href="" class="footer__qr-link"> <img
									src="./assets/img/app_store.png" alt=""
									class="footer__qr-download-img">
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="row"></div>
				<p class="footer__text">Bản quyền thuộc về nhóm 5</p>
			</div>
	</div>
	</footer>
	</div>
</body>

</html>