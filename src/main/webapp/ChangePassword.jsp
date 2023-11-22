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
    <link rel="stylesheet" href="<%=url%>/css/changePw.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    
</head>
<script>
    function dongHopChangePassword(){
        var buttonClose = document.getElementById("close-box");
        var changePassword = document.getElementById("form-changePW");
       if(changePassword.style.display !== "none"){
        changePassword.style.display = "none";
       }
    }
</script>
<body>
<%
	String msg_err1 = "";
	if (request.getAttribute("msg_err1") != null) {
		msg_err1 = request.getAttribute("msg_err1") + "";
	}
	String msg_err2 = "";
	if (request.getAttribute("msg_err2") != null) {
		msg_err2 = request.getAttribute("msg_err2") + "";
	}
	String msg_err3 = "";
	if (request.getAttribute("msg_err3") != null) {
		msg_err3 = request.getAttribute("msg_err3") + "";
	}
%>
    <div class="form-changePW" id="form-changePW">
        <div class="change-password" id="change-password">
            <form action="home" method="post">
            <input type="hidden" name="act" value="changPassword">
                <button type="button" class="close-box" id="close-box" > 
                	<a href="home">
                	<i class='bx bxs-x-circle'></i>
                	</a>
                </button>
                <h1>Thay đổi mật khẩu</h1>
                
                <div class="input-box">
                    <input type="password" name="oldPassword" id="oldPassword" placeholder="Mật khẩu cũ" required>
                    <p style="color: red; font-size: 10px; margin-left: 16px; margin-top: 4px;"><%=msg_err1 %></p>
                </div>
                <div class="input-box">
                    <input type="password" name="newPassword" id="newPassword" placeholder="Mật khẩu mới" required>
                	 <p style="color: red; font-size: 10px;margin-left: 16px;margin-top: 4px;"><%=msg_err3 %></p>
                </div>
                <div class="input-box">
                    <input type="password" name="re_newPassword" id="re-newPassword" placeholder="Nhập lại mật khẩu mới" required>
                	 <p style="color: red; font-size: 10px;margin-left: 16px;margin-top: 4px;"><%=msg_err2 %></p>
                </div>
                <%
	                String notify = null;
	            	if (request.getAttribute("notify") != null) {
	            		notify = request.getAttribute("notify") + "";
	            	}
	            	if(notify != null){
                %>
                <p style="color: yellow;">Đổi mật khẩu thành công !!!</p>
                <%} %>
                <button type="submit" class="btn">Đổi mật khẩu</button>
            </form>
        </div>
    </div>
    
</body>
</html>