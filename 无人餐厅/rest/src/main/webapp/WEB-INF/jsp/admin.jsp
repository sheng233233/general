<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>无人餐厅系统 - 后台管理</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="header">
	<h1><a href="#">无人餐厅后台管理系统</a></h1>
	<div class="user">
		<a href="#">${name }</a>
		<a href="../../login.jsp">退出</a>
	</div>
</div>
<div class="main">
	<ul class="left-side">
		<li class="menu-title active">
			<a href="#">
				<i class=" fa fa-users"></i>&nbsp;&nbsp;厨房管理
			</a>
		</li>
		<li class="sub-menu">
			<ul>
				<li class="active">
					<a href="${pageContext.request.contextPath}/api/food/listFood"  target="pageBox">
						<i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;菜单管理
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/api/ingre/listIngre" target="pageBox">
						<i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;食材管理
					</a>
				</li>
			</ul>
		</li>
		<li class="menu-title">
			<a href="#">
				<i class=" fa fa-file-text"></i>&nbsp;&nbsp;经营管理
			</a>
		</li>
		<li class="sub-menu">
			<ul>
				<li>
					<a target="pageBox" href="${pageContext.request.contextPath}/api/order/listOrder">
						<i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;订单管理
					</a>
				</li>
				<li>
					<a target="pageBox" href="${pageContext.request.contextPath}/api/table/showAll">
					<i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;台位管理
					</a>
				</li>
			</ul>
		</li>
	</ul>
	<div class="right-side">
		<!-- <iframe name="pageBox" src="view/flats/list.jsp"></iframe> -->
		<iframe name="pageBox" src="${pageContext.request.contextPath}/api/food/listFood"></iframe>
		

	</div>
</div>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
</body>
</html>