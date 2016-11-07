<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户分类分析</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/report.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/win.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/tabpane/js/tabpane.js"></script>
<link href="${pageContext.request.contextPath}/ui/js/tabpane/css/tab.css" rel="stylesheet" type="text/css">

<!--处理日期 开始 -->
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/calendar.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.css" type="text/css">
<!--处理日期结束  -->
</head>

<body>
<div class="mtitle">
	<div class="mtitle-row">&nbsp;</div>
	客户分类分析
</div>

<div class="tab-pane" id="tabPane1">
	<div class="tab-page" id="tabPage1">
		<h2 class="tab">报表图形</h2>
			<a href="#" target="_blank">
				<img src="${pageContext.request.contextPath}/temp/${filename}" border="0">
			</a>
	</div>
	
	<div class="tab-page" id="tabPage2">
		<h2 class="tab">报表数据</h2>
        <div id="report">
	        <div class="reportTitle">客户分类分析报表</div>
	             <table width="97%" border="1" cellspacing="0" cellpadding="0" class="reportTable">
						<tr>
							<td width="30%" class="thead">分类项目</td>
							<td width="31%" class="thead">客户数量</td>
						</tr>
					   <c:forEach items="${reportBeans}" var="reportBean">
							<tr>
								<td>${reportBean.type}</td>
								<td>${reportBean.count}</td>
							</tr>
						</c:forEach>
						<tr>
							<td>合计:</td>
							<td>${sum}</td>
						</tr>
	           </table>
	       </div>
	    </div>
</div>
</body>
</html>