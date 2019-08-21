<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>无人餐厅系统 - 登录页面</title>
	<link rel="stylesheet" href="css/index.css">
</head>
<body>
<%
  	session.setAttribute("user", null);
%>

<div class="login">
	<img src="image/logo.png" alt="中工无人餐厅">
	<p>无人餐厅后台系统</p>
	<form action="api/home/login" method="post">
	<div>
		<input type="text" id="username" name="userName" value="admin" placeholder="请输入用户名">
	</div>
	<div>
		<input type="password" id="password" name="pwd" value="admin" placeholder="请输入密码">
	</div>
	<div>
		<p class="error-message">${mess}</p>
		
		<input type="submit" value="登录">
	</div>
	</form>
</div>
<c:remove var="mess" scope="session"/>
<script>
	if (window.top !== window) {
		window.top.location.reload();
	}
</script>
</body>
</html>