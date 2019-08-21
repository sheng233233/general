<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>无人餐厅系统 - 菜品信息更新</title>
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
		<h3>菜品信息更新</h3>
		<span id="mess">${mess }</span>
		<form
			action="${pageContext.request.contextPath }/api/food/edit?id=${food.id }"
			method="post" enctype="multipart/form-data">
			<table class="form-table">
				<tr>
					<td>分类</td>
					<td colspan="3" class="control">
						<select name="type">
							<option value="家常菜" ${food.type=='家常菜'?'selected':'' }>家常菜</option>
							<option  value="精品小炒" ${food.type=='精品小炒'?'selected':'' }>精品小炒</option>
							<option value="主食" ${food.type=='主食'?'selected':'' }>主食</option>
							<option value="甜点" ${food.type=='甜点'?'selected':'' }>甜点</option>
							<option value="硬菜" ${food.type=='硬菜'?'selected':'' }>硬菜</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>名称</td>
					<td colspan="3" class="control"><input type="text"
						name="name" placeholder="菜品名称" value="${food.name}">
					</td>
				</tr>
				<tr>
					<td>价格</td>
					<td colspan="3" class="control"><input type="text"
						name="price" placeholder="价格"
						value="${food.price}"></td>
				</tr>
				<tr>
					<td>描述</td>
					<td colspan="3" class="control"><input type="text"
						name="msg" placeholder="描述" value="${food.msg}">
					</td>
				</tr>

				<tr>
					<td>展示图片</td>
					<td colspan="3" class="control">
						<p>
							<img src="${pageContext.request.contextPath }/${food.img }" width="300px" height="200px">
						</p> <input type="file" name="imgFile">
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