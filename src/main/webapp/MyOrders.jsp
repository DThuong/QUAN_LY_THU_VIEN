<%@page import="database.ChiTietPhieuMuonDAO"%>
<%@page import="model.ChiTietPhieuMuon"%>
<%@page import="model.Book"%>
<%@page import="model.TheLoai"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.TaiKhoan"%>
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
<title>Thư Viện</title>

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
<link rel="stylesheet" href="<%=url%>/css/keSach.css">

<!--[if lte IE 6]>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js" integrity="sha512-qWVvreMuH9i0DrugcOtifxdtZVBBL0X75r9YweXsdCHtXUidlctw7NXg5KVP3ITPtqZ2S575A0wFkvgS2anqSA==" crossorigin="anonymous"></script>
    <![endif]-->

</head>
<body>
	<%
		ArrayList<Book> book_cart = (ArrayList<Book>) session.getAttribute("book_cart");
		Book book_info = null;
		if(request.getAttribute("book_info")!= null){
			book_info = (Book)request.getAttribute("book_info");
		}
		ArrayList<TheLoai> list_theloai = new ArrayList<TheLoai>();
		if(request.getAttribute("list_theloai")!=null){
			list_theloai = (ArrayList<TheLoai>)request.getAttribute("list_theloai");
		}
		ArrayList<Book> book_list = new ArrayList<Book>();
		if(request.getAttribute("book_list")!=null){
			book_list = (ArrayList<Book>)request.getAttribute("book_list");
		}
		int numberPages = 0;
		if(request.getAttribute("numberOfPages")!=null){
			numberPages = (int)request.getAttribute("numberOfPages");
		}
		int nowPage = 0;
		if(request.getAttribute("nowPage")!=null){
			nowPage = (int)request.getAttribute("nowPage");
		}
		String maTheLoai = "";
		if(request.getAttribute("maTheLoai")!=null){
			maTheLoai = (String)request.getAttribute("maTheLoai");
		}
		
		int xemKeSach = 0;
		if(request.getAttribute("xemKeSach") != null){
			xemKeSach = (int) request.getAttribute("xemKeSach");
		}
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
						if(acc == null){
						%>
						<li
							class="header__navbar-item header__navbar-item--strong header__navbar-item--separate">
							<a href="register.jsp">Đăng ký</a>
						</li>
						<li class="header__navbar-item header__navbar-item--strong"><a
							href="login.jsp">Đăng nhập</a></li>
						<%}else
                     		{ %>
			
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
								<li class="header__navbar-user-item"><a href="">Phiếu
										mượn của tôi</a></li>
								<li class="header__navbar-user-item"><a
									href="home?act=logout">Đăng xuất</a></li>
							</ul></li>
						<%}%>
					</ul>
				</nav>
				<!-- Header with search -->
				<div class="header-with-search">
					<div class="header__seacher-and-bar">
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
						<!-- cart layout -->
						<%
							int cart_items = 0;
							if(book_cart != null) cart_items = book_cart.size();
						 %>
						<div class="header__cart">
							<div class="header__cart-warp">
								<i class="header__cart-icon fas fa-cart-plus"></i> <span
									class="header__cart-notice"><%=cart_items%></span>
							
								<!-- Has Cart add "header__cart-list-has-cart" -->
								<div class="header__cart-list header__cart-list--has-cart">
									<h4 class="header__cart-item-heading">Sách đã thêm</h4>
									<ul class="header__cart-list-item">
									<%
										if(book_cart != null){
											for(Book item : book_cart){ 
									%>
										<li class="header__cart-item"><img
											src="https://scontent.fsgn5-7.fna.fbcdn.net/v/t1.0-1/c0.36.320.320a/p320x320/101055847_1642016052629708_6252404825904906240_n.jpg?_nc_cat=105&_nc_sid=7206a8&_nc_ohc=XDjRAENIZGsAX9N8Ohr&_nc_ht=scontent.fsgn5-7.fna&oh=7445eed0da07aca926832984ee00dc31&oe=5F4E7550"
											alt="" class="header__cart-img">
											<div class="header__cart-item-info">
												<div class="header__cart-item-head">
													<h5 class="header__cart-item-name"><%=item.getTenSach()%> [<%=item.getBook_id()%>]</h5>
													<!-- <div class="header__cart-item-price-wrap">
														<span class="header__cart-item-price">5.000</span> <span
															class="header__cart-item-multiply">x</span> <span
															class="header__cart-item-qnt">2</span>
													</div> -->
												</div>
												<div class="header__cart-item-body">
													<span class="header__cart-item-description">Thể loại: <%=item.getTheLoai().getTenTheLoai()%></span> 
													<a href="ke-sach?xoa-sach=<%=item.getBook_id()%>"><span class="header__cart-item-remove">Xóa</span></a>
												</div>
											</div></li>

										<%}}%>	
									</ul>
									<a href="ke-sach?action=xem" class="btn header__cart-view-cart btn--primary">Xem giỏ sách</a>
								</div>
							</div>
						</div>
					</div>
				</div>
		</header>

		<div class="app__container">
			<div class="grid wide">
				<div class="row  sm-gutter app__content">
					<div class="col l-2 m-0 c-0">
						<nav class="category">
							<h3 class="category__heading">
								<i class="category__heading-icon fas fa-list"></i> Danh mục
							</h3>
							<ul class="category-list">
								<%
                            	for(TheLoai theloai : list_theloai){
                            		String tenTL = theloai.getTenTheLoai();
                            		String maTL = theloai.getTheLoai_id();
                            %>
								<li class="category-item category-item--active"><a
									href="the-loai?theloaiID=<%=maTL%>" class="category-item__link"><%=tenTL %></a>
								</li>
								<%		
                            	}
                            %>

							</ul>
						</nav>
					</div>

					<div class="col l-10 m-12 c-12">
						<div class="home-filter hide-on-tablet-and-mobile">
							<span class="home-filter__lable">Sắp xếp theo</span>
							<!-- <button class="home-filter__btn btn">Mới
								nhất</button> 
							<button class="home-filter__btn btn">Phổ biến</button>
							-->
							<button class="home-filter__btn btn">
								<a href="muon-nhieu-nhat"
									style="text-decoration: none; color: black;">Mượn nhiều</a>
							</button>
							<!-- Thanh chuyển phân trang -->
							<%if(numberPages > 0) {
                            	String soTrang = numberPages + "";
                            %>
							<div class="home-filter__page">
								<span class="home-filter__page-num"> <span
									class="home-filter__page-curent"><%=nowPage%></span>/<%=soTrang%>
								</span>

								<div class="home-filter__page-control">
									<%if(nowPage > 1){ %>
									<a href="the-loai?theloai=<%=maTheLoai%>&page=<%=nowPage-1%>"
										class="home-filter__page-btn home-filter__page-btn--disabled">
										<i class=" fas fa-chevron-left"></i>
									</a>
									<%} %>
									<%if(nowPage < numberPages){ %>
									<a href="the-loai?theloai=<%=maTheLoai%>&page=<%=nowPage+1%>"
										class="home-filter__page-btn"> <i
										class=" fas fa-chevron-right"></i>
									</a>
									<%} %>
								</div>
							</div>
							<%} %>
						</div>

						<div class="home-product">
							<!-- Grid-> Row -> column -->
							<div class="row sm-gutter">
							
							<%
								if(book_list.size() > 0){
	                            	for(Book book : book_list){
	                            		String tenSach = book.getTenSach();
	                            		String tacgia = book.getTacGia().getTen_tacGia();
	                            		String nxb = book.getNhaXuatBan();
	                            		String slgCon = book.getSoLuongTrenThuVien()+"";
                            %>
								<div class="col l-2-4 m-4 c-6" >
									<a class="home-product-item" href="thong-tin-san-pham?ten=<%=tenSach%>&id=<%=book.getBook_id()%>">
										<div class="home-product-item__img"
											style="background-image: url(./assets/img/ca-phe-cung-tonny.jpg);"></div>
										<h4 class="home-product-item__name"><%=tenSach%></h4>

										<div class="home-product-item__action">
											<div class="home-product-item__rating">
												<span class="home-product-item__sold"><%=slgCon%> còn
													lại</span>
											</div>
										</div>
										<div class="home-product-item__origin">
											<span class="home-product-item__brand"><%=tacgia%></span> <span
												class="home-product-item__origin-name"><%=nxb %></span>

										</div>
										<div class="home-product-item__favourite">
											<i class="fas fa-check"></i> <span>Yêu thích</span>
										</div>
									</a>
								</div>
								
								<%
								}}
								%>
								<% if(book_info != null)
									{					
								%>
								<div class="product-details">
									<div class="product-image">
										<img src="./assets/img/nhagiakim.jpg" alt="<%=book_info.getTenSach()%>">
									</div>
									<div class="product-info">
										<h1 class="product-title"><%=book_info.getTenSach()%></h1>
										<p class="product-description"><%=book_info.getMoTa()%></p>
										<div class="product-actions">
											<div class="product-buttons">
												<a href="ke-sach?add-book=<%=book_info.getBook_id()%>"><button class="left-button" id="addToShelf">Thêm
													vào kệ sách của tôi</button></a>
												<a href="#"><button type="submit" class="right-button" id="borrowNow" name="" value="">Đăng kí
													mượn ngay</button></a>
											</div>
										</div>
									</div>
								</div>
								<%} %>
								<%
									ArrayList<String> err_soLuong = null;
									if(request.getAttribute("err_soLuong")!=null){
										err_soLuong = (ArrayList<String>)request.getAttribute("err_soLuong");
									}
									
									String BookingComplete = null;
									if(request.getAttribute("BookingComplete")!=null){
										BookingComplete = (String)request.getAttribute("BookingComplete");
									}
								
									if(xemKeSach == 1){
								%>
								<h1>Kệ sách của bạn</h1>
								<form action="dat-sach" method="post">
								
                           		<table>
                                <thead>
                                    <tr>
                                    	<th style="width: 5%;"></th>
                                    	<th style="width: 10%;">Mã sách</th>
                                    	<th style="width: 30%;">Tên sách</th>
                                        <th style="width: 15%;">Tác giả</th>                                      
                                        <th style="width: 20%;">Nhà xuất bản</th>
                                        <th style="width: 10%;">Năm xuất bản</th>
                                        <th style="width: 10%;">Số lượng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		for(Book book : book_cart){
                                	%>
                                		<tr>
                                			<td style="width: 5%;">
                                				<input type="checkbox" id="myCheckbox" name="<%=book.getBook_id()%>">
                                			</td>
                                			<td style="width: 10%"><%=book.getBook_id() %></td>
                                			<td style="width: 30%;">
                                				<a href="#" style="text-decoration: none;" title="Nhấn vào xem thông tin sách">
                                					<%=book.getTenSach()%>
                                				</a>
                                			</td>
                               				<td style="width: 15%;">
                                				<%=book.getTacGia().getTen_tacGia()%>                               					
                               				</td> 			
                               				<td style="width: 25%;"><%=book.getNhaXuatBan() %></td>
                               				<td style="width: 5%;"><%=book.getNamXuatBan()%></td>
                               				<td style="width: 5%;">
                               					<input type="number" name="soLuongMuon-<%=book.getBook_id()%>" id="soLuongMuon" value="1"> 
                               				</td>
                                		</tr>
                                	<%
                                		}
                                	%>
                                </tbody>
                            </table>
                            <button type="submit" class="btn-primary btn-submit">Mượn sách</button>
                            </form>
                            	<%if(BookingComplete != null){%> <h3 style="color: green"><%=BookingComplete %></h3><%} %>
                            	<%
                            		if(err_soLuong != null) {
                            			for(String err : err_soLuong) {                          				
                            	%> 
                            		<h3 style="color: red"><%=err %></h3>
                            	<%		}
                            		}
                            	%>
                            
                            	
							<%} %>
							</div>
						</div>

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
					
				</div>
				<div class="row"></div>
				<p class="footer__text">Bản quyền thuộc về nhóm 5</p>
			</div>
	</div>
	</footer>
	</div>
</body>

</html>