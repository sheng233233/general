<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>无人餐厅系统 - 菜品信息详情查看</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
	<h3>菜品信息详情</h3>
		<table class="form-table">
			<tr>
				<td class="label">名称</td>
				<td class="detail">
					${food.name}
				</td>
			</tr>
			<tr>
				<td class="label">分类</td>
				<td colspan="3" class="detail">
					${food.type}
				</td>
			</tr>
			<tr>
				<td class="label">价格</td>
				<td colspan="3" class="detail">
					${food.price}元每份
				</td>
			</tr>
			<tr>
				<td class="label">描述</td>
				<td colspan="3" class="detail">
					${food.msg}
				</td>
			</tr>
			<tr>
				<td class="label">展示图片</td>
				<td colspan="3" class="detail">
				
					<img src="${pageContext.request.contextPath }/${food.img }" width="300px" height="200px">
				</td>
			</tr>
		</table>
		<div class="buttons">
			<a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
		</div>
</div>
</body>
</html>