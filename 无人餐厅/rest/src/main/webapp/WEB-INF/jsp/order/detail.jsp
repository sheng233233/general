<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>无人餐厅系统 - 订单详情查看</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
	<h3>订单详情</h3>
		<table class="form-table">
			<tr>
				<td class="label">订单编号</td>
				<td class="detail">
					${order.id}
				</td>
			</tr>
			<tr>
				<td class="label">所属桌号</td>
				<td class="detail">
					${order.tid}
				</td>
			</tr>
			<tr>
				<td class="label">订单状态</td>
				<td class="detail">
					<c:if test="${order.status == 0}">未受理</c:if>
					<c:if test="${order.status == 1}">已接受</c:if>
					<c:if test="${order.status == 2}">已拒绝</c:if>
				</td>
			</tr>
			<tr>
				<td class="label">订单时间</td>
				<td class="detail">
					${order.createTime}
				</td>
			</tr>
			<tr>
				<td class="label">订单内容</td>
				<td colspan="3" class="detail">
					${order.content}
				</td>
			</tr>
		</table>
		<div class="buttons">
			<a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
		</div>
</div>
</body>
</html>