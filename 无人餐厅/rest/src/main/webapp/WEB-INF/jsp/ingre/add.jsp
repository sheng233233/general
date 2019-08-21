<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>无人餐厅系统 - 食材信息添加</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">
</head>
<style>
	#mess{
	color:red;
	}
</style>
<body>
<div class="box">
	<h3>添加新种食材</h3>
	<form action="${pageContext.request.contextPath}/api/ingre/add" method="post" enctype="multipart/form-data">
	<span id="mess">${mess }</span>

	<table class="form-table">
		<tr>
			<td>食材名称</td>
			<td colspan="3" class="control">
				<input type="text" name="name" placeholder="食材名称">
			</td>
		</tr>
		<tr>
			<td>进货数量</td>
			<td colspan="3" class="control">
				<input type="text" name="surplus" placeholder="进货数量">
			</td>
		</tr>
	</table>
	<div class="buttons">
		<input class="btn btn-primary va-bottom" type="submit" value="保存">&nbsp;&nbsp;
		<a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
	</div>
	</form>
		<c:remove var="mess" scope="session"/>
</div>
</body>
</html>