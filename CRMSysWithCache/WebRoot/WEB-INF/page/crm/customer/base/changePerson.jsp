<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>经手人变更</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="/ui/js/win.js" type="text/javascript"></script>
<script language="javascript">
function check() {
	if($("#new_owner").val()==''){
		alert("所属人不能为空!");
		return false;
    }

    if(window.confirm("您确定进行客户经手人变更操作吗?")){
         document.forms[0].submit();
    }
	return false;
}
</script>
<style type="text/css">
body {
	padding: 10px;
}
</style>
</head>

<body>
<div id="loadDiv" style="display:none; background-color:#62B6F2;border:'1' 'slid' '#000000';height:40;width:200;left:200px; top:19px; z-index:2 ;position:absolute;" align="center"><span style="font-size:9pt;color:#FFFFFF;"><br>
操作中，请稍等...</span></div>
<s:form name="form1" method="post" action="companyAction_changeHandler.do"  namespace="/crm">
<!-- 保存的是客户的id -->
<s:hidden name="ids" value="%{#parameters.ids[0]}"/>
<div class="mtitle">
	<div class="mtitle-row">&nbsp;</div>
	客户经手人变更
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabForm">
	<tr>
		<td><strong>选择经手人变更的目标：</strong></td>
	</tr>
	<tr>
		<td>
		所属人:
           <s:if test="#request.sysUserSelect!=null">
			        <s:select list="#request.sysUserSelect" id='new_owner'  name='new_owner'
			                listKey="id" listValue="cnname" 
			                headerKey="" headerValue="--------"
			                cssStyle="width:140px"></s:select>
		   </s:if>
		</td>
	</tr>
	<tr>
		<td colspan="4" height="10"><hr style="height:1px; color:#D8D8D8"></td>
	</tr>
	<tr>
		<td align="right">
		<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
		        onClick="check()">
		        <img src="${pageContext.request.contextPath}/ui/images/button/biangeng.png" border='0' 
		             align='absmiddle'>&nbsp;变更</button>
		<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';" 
			    onClick="self.close();">
			 <img src="${pageContext.request.contextPath}/ui/images/button/quxiao.png"   border='0' align='absmiddle'>&nbsp;取消</button>
		&nbsp;&nbsp;
		</td>
	</tr>
</table>
</s:form>
</body>
</html>