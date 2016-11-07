<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>共享查看</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/win.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>
<style type="text/css">
<!--
fieldset div {
	float:left;
	width:24%;
	text-align:left;
	line-height:25px;
}
td div {
	float:left;
	width:24%;
	text-align:left;
	line-height:25px;
}
body {
	padding:5px;
}
-->
</style>
<script type="text/javascript">
function forward(strURL){
    window.location=strURL;
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//设置共享
function goSet(){
	var id=$("#id").val();
	var url = "${pageContext.request.contextPath}/crm/companyAction_showShareSetOne.do?id="+id;
	//var url = "${pageContext.request.contextPath}/crm/customer/base/shareSetOne.jsp";
	forward(url);
}

//取消共享
function goCancel(){
	var id=$("#id").val();
	var url = "${pageContext.request.contextPath}/crm/companyAction_showShareCancelOne.do?id="+id;
	//var url = "${pageContext.request.contextPath}/crm/customer/base/shareCancelOne.jsp?";
	forward(url);
}
</script>
</head>

<body>
<s:form name="ActionForm" method="post" action="#" >
<s:hidden name="id" id="id" value="%{#request.company.id}"/>
<%--EL表达式也行 <input type="hidden" name="id" id="id" value="${company.id}"> --%>
<div class="mtitle">
	<div class="mtitle-row">&nbsp;</div>
		<span onClick="goSet();" class="menu_noselect">共享设置</span>
		<span onClick="goCancel();" class="menu_noselect">取消共享</span>
		<span onClick="" class="menu_selected">共享查看</span>
</div>
<br/>
<div class="control">
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  onClick="self.close();"><img src="${pageContext.request.contextPath}/ui/images/button/guanbi.png" border='0' align='absmiddle'>&nbsp;关闭</button>
	<font color="red"></font>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" height="25"><span class="Tabfont"><%-- ${company.name} --%>
		<s:property value="%{#request.company.name}"/>(客户)共享情况</span></td>
	</tr>
</table>
<div class="border" style="padding:3px">
<fieldset style='padding:5px;clear:left;'>
<legend>
客户资料
</legend>
	<c:if test="${! empty sysUsers }">
		<c:forEach items="${sysUsers }" var="sysUser">
         <div><input type='checkbox' class='checkbox' name='user' value='12' id='7'  disabled checked>${sysUser.cnname}</div>
		</c:forEach>
	</c:if>
&nbsp;</fieldset>
</div>
<br>
</s:form>
</body>
</html>