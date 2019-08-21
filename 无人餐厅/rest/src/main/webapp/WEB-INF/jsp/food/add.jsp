<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>无人餐厅系统 - 新品信息添加</title>
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
	<h3>添加新品菜样</h3>
	<form action="${pageContext.request.contextPath}/api/food/add" method="post" enctype="multipart/form-data">
	<span id="mess">${mess }</span>

	<table class="form-table">
		<tr>
			<td>分类</td>
			<td colspan="3" class="control">
				<select name="type">
					<option value="家常菜">家常菜</option>
					<option  value="精品小炒" selected>精品小炒</option>
					<option value="主食">主食</option>
					<option value="甜点">甜点</option>
					<option value="硬菜">硬菜</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>新品名称</td>
			<td colspan="3" class="control">
				<input type="text" name="name" placeholder="新品名称">
			</td>
		</tr>
		<tr>
			<td>详细介绍</td>
			<td colspan="3" class="control">
				<input type="text" name="msg" placeholder="详细介绍">
			</td>
		</tr>
		<tr>
			<td>单价</td>
			<td colspan="3" class="control">
				<input type="text" name="price" placeholder="价格">
			</td>
		</tr>
		<tr>
			<td>展示图片</td>
			<td colspan="3" class="control">
				<input type="file" name="imgFile" placeholder="展示图片">
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