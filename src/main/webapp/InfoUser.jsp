<%@page import="database.SinhVienDAO"%>
<%@page import="model.SinhVien"%>
<%@page import="model.TaiKhoan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form ChangePassword</title>
    <link rel="stylesheet" href="<%=url%>/css/infoUser.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    
</head>

<body>
    <%
	    Object obj1 = session.getAttribute("user");
		TaiKhoan acc = null;
		if(obj1 != null && obj1 instanceof TaiKhoan) {
			acc = (TaiKhoan) obj1;
		}
    
		Object obj2 = session.getAttribute("myInfo");
		SinhVien myInfo = null;
		if(obj2 != null && obj2 instanceof SinhVien) {
			myInfo = (SinhVien) obj2;
		}
		
    	
    %>
    <div class="infomation">
        <form action="home" method="post">
        <input type="hidden" name="act" value="infoUser">
            <h1>Thông tin cá nhân</h1>
            <div class="content-form">
                <div class="info-name">
                    <label for="username">Tên đăng nhập<span>(*)</span>: </label>
                    <label for="fullname">Họ và tên<span>(*)</span>: </label>
                    <label for="msv">Mã sinh viên<span>(*)</span>: </label>
                    <label for="msv">Ngày sinh<span>(*) (yyyy-mm-dd)</span>: </label>
                    <label for="classID">Mã lớp<span>(*)</span>: </label>
                    <label for="sex">Giới tính: </label>
                    <label for="address">Địa chỉ: </label>
                    <label for="email">Email: </label>
                    <label for="phone">Số điện thoại: </label>
                </div>
                <div class="info-value">
                    <input type="text" name="username" id="username" value="<%=acc.getUsername()%>" readonly="readonly">
                    <input type="text" name="hoVaTen" id="fullname" value="<%=myInfo.getHoVaTen()%>" readonly="readonly">
                    <input type="text" name="msv" id="msv" value="<%=myInfo.getMsv() %>" readonly="readonly">
                    <input type="text" name="dob" id="dob" value="<%=myInfo.getNgaySinh().toString() %>" readonly="readonly">
                    <input type="text" name="classID" id="classID" value="<%=myInfo.getMaLop()%>" >
                    <input type="text" name="sex" id="sex" value="<%=myInfo.getGioiTinh() %>" >
                    <input type="text" name="address" id="address" value="<%=myInfo.getDiaChi()%>">
                    <input type="email" name="myEmail" id="myEmail" value="<%=myInfo.getEmail() %>" >
                    <input type="tel" name="phone" id="phone" value="<%=myInfo.getPhone() %>" >
                </div> 
            </div>
            
            <button type="submit" class="btn">Lưu thông tin</button>
        </form>
    </div>
    
</body>
</html>