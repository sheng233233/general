<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/8/19
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销量统计</title>

    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>

</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px; height: 400px;"></div>

    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        alert(100);
        $.post(
            "${pageContext.request.contextPath}/api/food/statistic",
            function (data) {
                alert(1111);
                // 2.获取数据
                salesVolume = data.salesVolume;//销量
                bussinessVolume = data.bussinessVolume;//营业额
                months = data.months;//月份

                // 3.更新图表myChart的数据
                var option = {
                    title : {
                        text : 'ECharts 入门示例'
                    },
                    tooltip : {},
                    legend : {
                        data : [ '销量' ],
                        data : [ '营业额' ]
                    },
                    xAxis : {
                        data : months
                    },
                    yAxis : {},
                    series : [ {
                        name : '销量',
                        type : 'bar',
                        data : salesVolume
                    }, {
                        name : '营业额',
                        type : 'line',
                        data : bussinessVolume
                    } ],
                    toolbox : {
                        show : true,
                        feature : {
                            mark : {
                                show : true
                            },
                            dataView : {
                                show : true,
                                readOnly : false
                            },
                            magicType : {
                                show : true,
                                type : [ 'line', 'bar' ]
                            },
                            restore : {
                                show : true
                            },
                            saveAsImage : {
                                show : true
                            }
                        }
                    }
                }
                alert(111);
                myChart.setOption(option);
                alert(222);
            }
        )
    </script>



</body>
</html>
