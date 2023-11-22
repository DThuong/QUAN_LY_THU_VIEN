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
    <title>Đăng ký tài khoản</title>
    <link rel="stylesheet" href="<%=url%>/css/register.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    
</head>

<body>
<%
	String err_username = "";
	if (request.getAttribute("err_username") != null) {
		err_username = request.getAttribute("err_username") + "";
	}
	String err_stuID = "";
	if (request.getAttribute("err_stuID") != null) {
		err_stuID = request.getAttribute("err_stuID") + "";
	}
	String err_password = "";
	if (request.getAttribute("err_password") != null) {
		err_password = request.getAttribute("err_password") + "";
	}
	String err_rePassword = "";
	if (request.getAttribute("err_rePassword") != null) {
		err_rePassword = request.getAttribute("err_rePassword") + "";
	}
	String username = "";
	if (request.getAttribute("username") != null) {
		username = request.getAttribute("username") + "";
	}
	String fullname = "";
	if (request.getAttribute("fullname") != null) {
		fullname = request.getAttribute("fullname") + "";
	}
	String student_id = "";
	if (request.getAttribute("student_id") != null) {
		student_id = request.getAttribute("student_id") + "";
	}
	String email = "";
	if (request.getAttribute("email") != null) {
		email = request.getAttribute("email") + "";
	}
	String phone = "";
	if (request.getAttribute("phone") != null) {
		phone = request.getAttribute("phone") + "";
	}
	String ngaySinh = "";
	if (request.getAttribute("ngaySinh") != null) {
		ngaySinh = request.getAttribute("ngaySinh") + "";
	}
	String gender = "";
	if (request.getAttribute("gender") != null) {
		gender = request.getAttribute("gender") + "";
	}
%>
    <div class="form-registration">
        <form action="home" method="post">
        <input type="hidden" name="act" value="register">
            <h1>Registration</h1>
            <div class="input-info">
                <div class="personal-details">
                    <h3>Personal Details</h3>
                    <div class="input-personal-details">
                        <div class="input-box">
                            <label for="fullname">Full Name</label>
                            <input type="text" name="fullname" id="fullname" value="<%=fullname %>" placeholder="Enter your name" required>
                        </div>

                        <div class="input-box">
                            <label for="dob">Date of Birth (yyyy-mm-dd)</label>
                            <input type="text" name="ngaySinh" id="dob" value="<%=ngaySinh %>" placeholder="Enter birth date" required>
                        </div>

                        <div class="input-box">
                            <label for="student_id">Student ID </label>
                            <input type="text" name="student_id" id="student_id" value="<%=student_id %>" placeholder="Enter your studen ID" required>
                       		<span style="color: red; font-size: 10px"><%=err_stuID %></span>
                        </div>

                        <div class="input-box">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" value="<%=email %>" placeholder="Enter your email">
                        </div>

                        <div class="input-box">
                            <label for="phone">Phone number</label>
                            <input type="tel" name="phone" id="phone" value="<%=phone %>" placeholder="Enter phone number">
                        </div>
                        
                        <div class="input-box">
                            <label for="gender">Gender (Male/Female) </label>
                            <input type="text" name="gender" id="gender" value="<%=gender %>" placeholder="Enter your gender">
                        </div>
                        
                    </div>
                </div>
                <div class="account-details">
                    <h3>Account Details</h3>
                   <div class="input-account-details">
                        <div class="input-box">
                            <label for="username">Username</label>
                            <input type="text" name="username" id="username" value="<%=username %>" placeholder="Enter your username" required>
                        	<span style="color: red; font-size: 10px"><%=err_username %></span>
                        </div>
                        <div class="input-box">
                            <label for="re-password">Password</label>
                            <input type="password" name="password" id="password" placeholder="Enter your password" required>
                            <span style="color: red; font-size: 10px"><%=err_password %></span>
                        </div>
                        <div class="input-box">
                            <label for="password">Password</label>
                            <input type="password" name="re_password" id="re-password" placeholder="Enter your password" required>
                            <span style="color: red; font-size: 10px"><%=err_rePassword%></span>
                        </div>
                   </div>
                </div>
                </div>
                <button type="button" class="btn btn-back"><i class='bx bx-chevron-left'></i> <a href="index.jsp">Back</a> </button>
                <button type="submit" class="btn btn-submit">Register <i class='bx bx-chevron-right'></i> </button>
            </div>
            
        </form>
    </div>
</body>

</html>