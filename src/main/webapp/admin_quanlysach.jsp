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
<style>
	.main-content a{
		text-decoration: none;
		color: #333;
	}
</style>
</head>
<body>
	<%
		ArrayList<Book> book_list = new ArrayList<Book>();
		if(request.getAttribute("ql_sach")!=null){
			book_list = (ArrayList<Book>)request.getAttribute("ql_sach");
		}
		
		
		boolean themSoLuong = false;
		if(request.getAttribute("themSoLuong")!=null){
			themSoLuong = (boolean)request.getAttribute("themSoLuong");
		}
		
		boolean updateBook = false;
		if(request.getAttribute("updateBook")!=null){
			updateBook = (boolean)request.getAttribute("updateBook");
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
                                      <li class="category-item ">
                                          <a href="admin?quan-ly=phieu" class="category-item__link">Danh sách phiếu mượn</a>
                                      </li>
                                      <li class="category-item category-item--active">
                                          <a href="admin?quan-ly=sach" class="category-item__link">Quản lý sách</a>
                                      </li>
                                      
                                      <li class="category-item">
                                          <a href="admin?quan-ly=sinhvien" class="category-item__link">Quản lý sinh viên</a>
                                      </li>
                                     
                                  </ul>
                            </nav>
                            
                        </div>
                        <div class="main-content">
                        <%if(themSoLuong == false && updateBook == false){ %>
                        <h1>Quản lý sách</h1>
                        <a href="admin_Themsach.jsp">
                        	<button type="button" style="padding: 8px 16px; margin-bottom: 12px"> Thêm sách mới </button>
                        </a>                       		 
                            <table>
                                <thead>
                                    <tr>
                                    	<th style="width: 10%;">Mã sách</th>
                                    	<th style="width: 30%;">Tên sách</th>
                                        <th style="width: 10%;">Tác giả</th>                                      
                                        <th style="width: 20%;">Nhà xuất bản</th>
                                        <th style="width: 15%;">Số lượng</th>
                                        <th style="width: 15%;">Sửa thông tin</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		for(Book sach : book_list){
                                			
                                	%>
                                		<tr>
                                			<td style="width: 10%;">
                                				<%=sach.getBook_id() %>
                                			</td>
                                			<td style="width: 30%;">
                                				<%=sach.getTenSach() %>
                                			</td>
                               				<td style="width: 10%;">
                               					<%=sach.getTacGia().getTen_tacGia() %>
                               				</td> 			
                               				<td style="width: 20%;"><%=sach.getNhaXuatBan()%></td>
                               				<td style="width: 15%;">
                               					<%=sach.getSoLuongDaMuon()+sach.getSoLuongTrenThuVien()%>
                               					<a style="color: blue; text-decoration: underline;" href="admin?quan-ly=sach&add=<%=sach.getBook_id()%>"> 
                               						(Thêm)
                               					</a>
                               				</td>
                               				<td style="width: 15%;">
                               					<a style="color: blue; text-decoration: underline;" href="admin?quan-ly=sach&update=<%=sach.getBook_id()%>">
                               						Sửa
                               					</a>
                               				</td>
                                		</tr>
                                	<%
                                		}
                                	%>
                                </tbody>
                            </table>
                            <% } %>
                            <%if(themSoLuong == true){
                            	Book bookEdit = null;
                        		if(request.getAttribute("BookEdit")!=null){
                        			bookEdit = (Book)request.getAttribute("BookEdit");
                        		}
                            %>
                            	<h1>Thông tin sách:</h1>
                            	<h3>Mã sách: <%=bookEdit.getBook_id()%></h3>
                            	<h3>Tên sách: <%=bookEdit.getTenSach() %></h3>
                            	<h3>Thể loại: <%=bookEdit.getTheLoai().getTenTheLoai() %></h3>
                            	<h3>Tác giả: <%=bookEdit.getTacGia().getTen_tacGia() %></h3>
									<form action="admin" method="get">
										<div class="form-group">
		                                    <label for="soLuongThemVao">Số lượng thêm vào thư viện</label>
		                                    <input type="number" id="soLuongThemVao" name="soLuongThemVao" >
	                                	</div>
	                                	<input type="hidden" name="book_id" value="<%=bookEdit.getBook_id()%>" >
	                                	<button type="submit">Thêm vào</button>
									</form>
                            <%} %>
                            <%if(updateBook == true){ 
                            	Book bookEdit = null;
                        		if(request.getAttribute("BookEdit")!=null){
                        			bookEdit = (Book)request.getAttribute("BookEdit");
                        		}
                        	%>
                        <h1>Chỉnh sửa sách</h1>
                        <form action="admin-add-book">
                        <div class="main-content">
                        <div class="form-container">
                            <div class="form-part form-part1">
                                <h2> Thêm ảnh sách</h2>
                                <div class="form-group">
                                    <label for="book-image">Link sách:</label>
                                    <input type="text" id="book-image" name="book-image" value="<%=bookEdit.getLink_img()%>">
                                </div>
                            </div>
                            <div class="form-part form-part2">
                                <h2> Thông tin tác giả</h2>
                                <div class="form-group">
                                    <label for="author-id">Mã tác giả:</label>
                                    <input type="text" id="author-id" name="author-id" value="<%=bookEdit.getTacGia().getTacGia_id() %>" required>
                                </div>
                                <div class="form-group">
                                    <label for="author-name">Tên tác giả:</label>
                                    <input type="text" id="author-name" name="author-name" value="<%=bookEdit.getTacGia().getTen_tacGia() %>" required>
                                </div>
                                <div class="form-group">
                                    <label for="author-age">Năm sinh:</label>
                                    <input type="number" id="author-age" name="author-age" value="<%=bookEdit.getTacGia().getNamSinh() %>" >
                                </div>
                                <div class="form-group">
                                    <label for="author-notes">Ghi chú về tác giả:</label>
                                    <textarea id="author-notes" name="author-notes" value="<%=bookEdit.getTacGia().getGhiChu() %>" rows="4"></textarea>
                                </div>
                            </div>
                            <div class="form-part form-part3">
                                <h2>Thông tin sách</h2>
                                 <div class="form-group">
                                    <label for="book-id">Mã sách:</label>
                                    <input type="text" id="book-id" name="book-id" value="<%=bookEdit.getBook_id() %>" readonly="readonly">
                                </div>
                                <div class="form-group">
                                    <label for="book-title">Tên sách:</label>
                                    <input type="text" id="book-title" name="book-title" value="<%=bookEdit.getTenSach()%>" required>
                                </div>
                                
                                <div class="form-group">
                                    <label for="book-genre">Thể loại sách:</label>
                                    <input type="text" id="book-genre" name="book-genre" value="<%=bookEdit.getTheLoai().getTheLoai_id() %>" required>
                                </div>
                                 <div class="form-group">
                                    <label for="book-genre-name">Tên thể loại sách (Nếu là thể loại mới):</label>
                                    <input type="text" id="book-genre-name" name="book-genre-name" value="<%=bookEdit.getTheLoai().getTenTheLoai()%>" >
                                </div>
                                <div class="form-group">
                                    <label for="nhaXuatBan">Nhà xuất bản:</label>
                                    <input type="text" id="nhaXuatBan" name="nhaXuatBan" value="<%=bookEdit.getNhaXuatBan()%>">
                                </div>
                                <div class="form-group">
                                    <label for="namXuatBan">Năm xuất bản:</label>
                                    <input type="number" id="namXuatBan" name="namXuatBan" value="<%=bookEdit.getNamXuatBan()%>" >
                                </div>
                                <div class="form-group">
                                    <label for="book-description">Mô tả sách:</label>
                                    <textarea id="book-description" name="book-description" rows="6" value="<%=bookEdit.getMoTa()%>"></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="book-quantity">Số lượng sách trên thư viện:</label>
                                    <input type="number" id="book-quantity" name="book-quantity" value="<%=bookEdit.getSoLuongTrenThuVien()%>" readonly="readonly">
                                </div>
                                <button type="submit">Chỉnh sửa thông tin sách</button>
                            </div>                            
                        </div>
                    </div>
                    </form>
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