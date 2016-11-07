<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>取消共享</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
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

	//设置共享
	function goSet(){
		var id=$("#id").val();
		var url = "${pageContext.request.contextPath}/crm/companyAction_showShareSetOne.do?id="+id;
		//var url = "${pageContext.request.contextPath}/crm/customer/base/shareSetOne.jsp";
		forward(url);
	}
	
	//查看共享
	function goView(){
		var id=$("#id").val();
		var url = "${pageContext.request.contextPath}/crm/companyAction_showShareViewOne.do?id="+id;
		//var url = "${pageContext.request.contextPath}/crm/customer/base/shareViewOne.jsp";
		//var url = "${pageContext.request.contextPath}/crm/companyAction_other.do?method=showShareViewOne&id="+id;
		forward(url);
	}
	
	function selectAllBox(){
	   $("input[type='checkbox'][name='s_module']").attr("checked","checked");
	}
	
	function unSelectAllBox(){
	   $("input[type='checkbox'][name='s_module']").attr("checked",null);
	}
</script>
</head>
<body>
<s:form name="ActionForm" method="post" action="companyAction_updateshareCancelOne.do" namespace="/crm">
<s:hidden name="id" id="id" value="%{#request.company.id}" />
<%-- <input type="hidden" name="id" id="id" value="${param.id}"> --%>
<div class="mtitle">
	<div class="mtitle-row">&nbsp;</div>
		<span onClick="goSet();" class="menu_noselect">共享设置</span>
		<span onClick="" class="menu_selected">取消共享</span>
		<span onClick="goView();" class="menu_noselect">共享查看</span>
</div>
<br/>
<div class="control">
	<button type='button' class='button'  onMouseOver="this.className='button_over';"  onMouseOut="this.className='button';"  
	        onClick="selectAllBox()"><img src="${pageContext.request.contextPath}/ui/images/button/quanbuxz.png" border='0' 
	        align='absmiddle'>&nbsp;全部选中</button>
	        
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onclick="unSelectAllBox()">
	       <img src="${pageContext.request.contextPath}/ui/images/button/quanbubxz.png" border='0' align='absmiddle'>&nbsp;全部不选中
	       </button>
	       
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="document.ActionForm.submit();">
	        <img src="${pageContext.request.contextPath}/ui/images/button/baocun.png" border='0' align='absmiddle'>&nbsp;保存</button>
	
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="self.close();">
	<img src="${pageContext.request.contextPath}/ui/images/button/guanbi.png" border='0' align='absmiddle'>&nbsp;关闭</button>
	<font color="red"></font>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" height="25"><span class="Tabfont">${company.name}(客户)待共享的用户<%-- 或者写成：<s:property value=%{#request.company.name}> --%></span></td>
	</tr>
</table>
<div id="border" class="border" style="padding:3px">
<fieldset style='padding:5px;clear:left;'>
<legend>
选择模块：
</legend>
  <div><input type='checkbox' class='checkbox' name='s_module' value='c_company' id='c_company'>客户资料</div>
  &nbsp;</fieldset>
</div>
<br>
</s:form>
</body>
</html>