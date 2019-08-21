<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>无人餐厅系统 - 食材信息更新</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">
</head>
<style>
	#mess{
	display:inline;
	color:red;
	}
</style>
<body>
	<div class="box">
		<h3>食材信息更新</h3>
		<span id="mess">${mess }</span>
		<form
			action="${pageContext.request.contextPath }/api/ingre/edit?id=${ingre.id }"
			method="post" enctype="multipart/form-data">
			<table class="form-table">

				<tr>
					<td>名称</td>
					<td colspan="3" class="control"><input type="text" class="form-control"
						name="name" placeholder="食材名称" value="${ingre.name}" readonly>
					</td>
				</tr>
				<tr>
					<td>剩余量</td>
					<td colspan="3" class="control"><input type="text"
						name="surplus" placeholder="剩余量"
						value="${ingre.surplus}"></td>
				</tr>
				<
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