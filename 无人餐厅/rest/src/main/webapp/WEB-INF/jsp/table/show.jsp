<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>无人餐厅系统 - 台位管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">
    <style>
        .table-able{
            width: 13%;
            height: 100px;
            background-color: rgb(60,141,188);
            margin: 20px;
            display: inline-block;
            text-align: center;
        }
        .table-disable{
            width: 13%;
            height: 100px;
            background-color: rgb(243,156,18);
            margin: 20px;
            display: inline-block;
            text-align: center;
        }
        .fount{
            margin: auto;
        }
        #back{
            width: 1620px;

        }

    </style>
</head>
<style>
    #mess{
        color:red;
    }
</style>
<body>
<div class="box">
    <h3>台位管理</h3>
    <div class="actions">
        <div>
            <button class="btn btn-primary" onclick="edit()">修改桌子数量</button>
        </div>
    </div>

    <div>
        <div class="container">
            <div class="container">
                <c:forEach items="${tables}" var="item">

                    <c:if test="${item.value}">
                        <div class="table-able" id="${item.key}"><h4 class="fount" style="color: white">${item.key}号桌,可用</h4></div>
                    </c:if>
                    <c:if test="${not item.value}">
                        <div class="table-disable" id="${item.key}"><h4 class="fount" style="color: black">${item.key}号桌,不可用</h4></div>
                    </c:if>

                </c:forEach>

        </div>
    </div>


<c:remove var="mess" scope="session"/>
<script src="../../../lib/jquery/jquery.js"></script>
<script>

    function edit() {

        var num = prompt("请输入目标桌子数量");
        if (num<1){
            alert("请输入大于0的整数!!!");
        }
        $.post(
            "${pageContext.request.contextPath}/api/table/edit?num="+num,
            function (data) {
                if(data.success){
                    alert("修改成功");
                    $('html').load('${pageContext.request.contextPath}/api/table/showAll');
                }else {
                    alert("修改失败");
                }
            }
        );
    }


</script>
</body>
</html>