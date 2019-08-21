<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>无人餐厅系统 - 菜单信息管理</title>
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
	<h3>订单管理</h3>
	<div class="container">
		<div class="row">
			<div class="col-md-8"></div>
			<div class="col-md-4">
				<form action="${pageContext.request.contextPath}/api/order/find" method="post">
					<select name="status" class="form-control">
						<option value="-1" selected="selected">选择订单状态</option>
						<option value="0">未受理</option>
						<option value="1">已接受</option>
						<option value="2">已拒绝</option>
					</select>
					<button type="submit" class="btn btn-default">查询</button>
				</form>

			</div>
		</div>
	</div>
	<table class="list">
		<tr>
			<th>序号</th>
			<th>所属桌号</th>
			<th>订单编号</th>
			<th>内容详情</th>
			<th>总价</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="order" begin="${page.pageStart }" end="${page.pageStart+page.pageSize-1 }" varStatus="state">
		<tr>
			<td>${state.index+1 }</td>
			<td>${order.tid }</td>
			<td>${order.id }</td>
			<td>${order.content}</td>
			<td>${order.total}</td>
			<td>
				<c:if test="${order.status == 0}">未受理</c:if>
				<c:if test="${order.status == 1}">已接受</c:if>
				<c:if test="${order.status == 2}">已拒绝</c:if>
			</td>
			<td>
				<!-- <a class="fa fa-info" title="详情" href="detail.jsp"></a> -->
				<a class="fa fa-info" title="详情" href="${pageContext.request.contextPath }/api/order/details?id=${order.id }"></a>
				&nbsp;&nbsp;
				<a class="fa fa-remove" title="删除" href="${pageContext.request.contextPath }/api/order/delete?id=${order.id }" onclick="return confirmDelete(1)"></a>
			</td>
		</tr>
			</c:forEach>
	</table>
	<div class="pager-info">
		<div>共有 ${list.size() } 条记录，第 ${page.pageNum }/${page.pageMax }页&nbsp;&nbsp;&nbsp;&nbsp;<span id="mess">${mess }</span> </div>
		<div>
			<ul class="pagination">
				<li class="paginate_button previous disabled }">
					<c:if test="${page.pageNum != 1 }">
						<a href="${pageContext.request.contextPath}/api/order/listOrder?pageNum=${page.pageNum-1 }">上一页</a>
					</c:if>
				</li>
				
				<li class="paginate_button active"><a href="#">${page.pageNum }</a></li>
				
				<li class="paginate_button next disabled">
					<c:if test="${page.pageNum != page.pageMax }">
						<a href="${pageContext.request.contextPath}/api/order/listOrder?pageNum=${page.pageNum+1 }">下一页</a>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
</div>
<c:remove var="mess" scope="session"/>
<script src="../../lib/jquery/jquery.js"></script>
<script>
function confirmDelete(id){
	if (confirm("确定要删除码？")) {
		return true;
	}
	return false;
}
</script>
</body>
</html>