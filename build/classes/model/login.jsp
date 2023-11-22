<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form in HTML and CSS | Codehal</title>
    
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<link rel="stylesheet" href="<%=url%>/css/login.css">
</head>

<body style="background:url(<%=url%>/img/login_background.jpg) no-repeat";>
<%
	String username = "";
	if (request.getAttribute("username") != null) {
		username = request.getAttribute("username") + "";
	}
	String err_username = "";
	if (request.getAttribute("err_username") != null) {
		err_username = request.getAttribute("err_username") + "";
	}
	String err_password = "";
	if (request.getAttribute("err_password") != null) {
		err_password = request.getAttribute("err_password") + "";
	}
%>
    <div class="wraper">
        <form action="home" method="post">
        <input type="hidden" name="act" value="login">
            <h1>Login</h1>
            <div class="input-box">
                <input type="text" placeholder="Username" name="username" value="<%=username %>" required>
                <p><%=err_username %></p>
                <i class='bx bxs-user'></i>
            </div>
            <div class="input-box">
                <input type="password" placeholder="Password" name="password" required>
                <p><%=err_password %></p>
                <i class='bx bxs-lock-alt' ></i>
            </div>

            <div class="remember-forgot">
                <label for="remember-me"><input type="checkbox" name="" id="remember-me">Remember me</label>
                <a href="#">Forgot password?</a>
            </div>

            <button type="submit" class="btn">Login</button>

            <div class="register-link">
                <p>Don't have a account? <a href="register.jsp">Register</a> </p>
            </div>
        </form>
    </div>

</body>
</html>