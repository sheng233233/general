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
	<h3>菜单管理</h3>
	<div class="actions">
		<div>
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/api/food/toAdd">添加新品</a>
		</div>
	</div>

	<table class="list">
		<tr>
			<th>序号</th>
			<th>名称</th>
			<th>所属分类</th>
			<th>价格</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="food" begin="${page.pageStart }" end="${page.pageStart+page.pageSize-1 }" varStatus="state">
		<tr>
			<td>${state.index+1 }</td>
			<td>${food.name }</td>
			<td>${food.type }</td>
			<td>${food.price}</td>
			<td>
				<!-- <a class="fa fa-info" title="详情" href="detail.jsp"></a> -->
				<a class="fa fa-info" title="详情" href="${pageContext.request.contextPath }/api/food/details?id=${food.id }"></a>
				&nbsp;&nbsp;
				<a class="fa fa-pencil" title="编辑" href="${pageContext.request.contextPath }/api/food/toEdit?id=${food.id }"></a>
				&nbsp;&nbsp;
				<a class="fa fa-remove" title="删除" href="${pageContext.request.contextPath }/api/food/delete?id=${food.id }" onclick="return confirmDelete(1)"></a>
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
						<a href="${pageContext.request.contextPath}/api/food/listFood?pageNum=${page.pageNum-1 }">上一页</a>
					</c:if>
				</li>
				
				<li class="paginate_button active"><a href="#">${page.pageNum }</a></li>
				
				<li class="paginate_button next disabled">
					<c:if test="${page.pageNum != page.pageMax }">
						<a href="${pageContext.request.contextPath}/api/food/listFood?pageNum=${page.pageNum+1 }">下一页</a>
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
	if (confirm("确定要删除吗？")) {
		return true;
	}
	return false;
}
</script>
</body>
</html>