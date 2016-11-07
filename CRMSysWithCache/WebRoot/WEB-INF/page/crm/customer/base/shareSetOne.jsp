<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>共享设置one</title>
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

<script language="javascript">
function forward(strURL){
    window.location=strURL;
}
////////////////////////////////////////////////////////////////////////////////////////////////////
//共享查看
function goView(){
	var id=$("#id").val();
	var url = "${pageContext.request.contextPath}/crm/companyAction_showShareViewOne.do?id="+id;
	//var url = "${pageContext.request.contextPath}/crm/customer/base/shareViewOne.jsp";
	forward(url);
}

//取消共享
function goCancel(){	
	var id=$("#id").val();
	//var url = "${pageContext.request.contextPath}/crm/customer/base/shareCancelOne.jsp";
	var url = "${pageContext.request.contextPath}/crm/companyAction_showShareCancelOne.do?id="+id;
	forward(url);
}

//模块全选
function selectAllBox(){
	   $("input[type='checkbox'][name='s_module']").attr("checked","checked");
}
	
//模块全不选
function unSelectAllBox(){
   $("input[type='checkbox'][name='s_module']").attr("checked",null);
}

//用户全选
function selectAllUser(){
	$("input[type='checkbox'][name='uid']").attr("checked","checked");
	$("#c_company").attr("checked","checked");
}
	
//用户全不选
function unSelectAllUser(){
   $("input[type='checkbox'][name='uid']").attr("checked",null);
   $("#c_company").attr("checked",null);
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
function do_select(id){
	   if(id=='c_company'){
	       if(!$("#c_company")[0].checked){
	          $("input[type=checkbox][name='uid']").attr("checked",null);
	       }
	   }else{
		   if($("#"+id)[0].checked){
				   $("#c_company").attr("checked","checked");
		   }
	   }
}

</script>
</head>

<body>
<form name="ActionForm" method="post" action="${pageContext.request.contextPath}/crm/companyAction_updateShareSetOne.do" >
<!-- 隐藏域 -->
<input type="hidden" name="id" id="id" value="${company.id}">
<div class="mtitle">
	<div class="mtitle-row">&nbsp;</div>
		<span onClick="" class="menu_selected" style="cursor:default">共享设置</span>
		<span onClick="goCancel();" class="menu_noselect" style="cursor:default">取消共享</span>
		<span onClick="goView();" class="menu_noselect" style="cursor:default">共享查看</span>
</div>
<br/>
<div class="control">
		<select name='sharetype' id='sharetype'>
		<option value='add'>增加共享</option>
		<option value='minus'>减少共享</option>
</select>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="selectAllBox()"><img src="${pageContext.request.contextPath}/ui/images/button/mokuaiqx.png" border='0' 
	        align='absmiddle'>&nbsp;模块全选</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="unSelectAllBox()"><img src="${pageContext.request.contextPath}/ui/images/button/mokuaiqbx.png" border='0' 
	        align='absmiddle'>&nbsp;模块全不选</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="document.forms[0].submit();"><img src="${pageContext.request.contextPath}/ui/images/button/baocun.png" border='0' 
	        align='absmiddle'>&nbsp;保存</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="self.close();"><img src="${pageContext.request.contextPath}/ui/images/button/guanbi.png" border='0' 
	        align='absmiddle'>&nbsp;关闭</button>
	<font color="red"></font>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#6A82A8">
	<tr>
		<td align="center" height="25"><span class="Tabfont">${company.name}(客户)待共享的模块</span></td>
	</tr>
</table>
<div id="border" class="border" style="padding:3px">
<fieldset style='padding:5px;clear:left;'>
<legend>
选择模块：
</legend>
<div><input type='checkbox' class='checkbox' name='s_module' value='c_company' id='c_company'  
            onclick='do_select(this.id)' >客户资料</div>
&nbsp;</fieldset>

</div>
<br>
<div class="control">
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="selectAllUser()">
	        <img src="${pageContext.request.contextPath}/ui/images/button/yonghuqx.png" border='0' align='absmiddle'>&nbsp;用户全选</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="unSelectAllUser()">
	        <img src="${pageContext.request.contextPath}/ui/images/button/yonghuqbx.png" border='0' align='absmiddle'>&nbsp;用户全不选</button>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" height="25"><span class="Tabfont">
		<%-- ${company.name} --%><s:property value="%{#request.company.name}"/> (客户)待共享的用户</span>
		</td>
	</tr>
</table>

<div class="border" style="padding:3px">
	<c:if test="${! empty requestScope.sysUserGroups}">	
		<c:forEach items="${requestScope.sysUserGroups}" var="sysUserGroup">
			<fieldset style='padding:5px;clear:left;'>
			 <legend>${sysUserGroup.name}</legend>
                   <c:if test="${! empty requestScope.sysUsers}">
                       <c:forEach items="${requestScope.sysUsers}" var="sysUser">
                         <c:if test="${sysUser.sysUserGroup.id == sysUserGroup.id }">
                          <div>
						   <input type='checkbox' class='checkbox' name='uid' value='${sysUser.id }' id='uid${sysUser.id }'  onclick='do_select(this.id)' >${sysUser.cnname }
						  </div>&nbsp;
					  </c:if>
					</c:forEach>
				</c:if>
			</fieldset>
		</c:forEach>	
	</c:if>		
</div>
</form>
</body>
</html>