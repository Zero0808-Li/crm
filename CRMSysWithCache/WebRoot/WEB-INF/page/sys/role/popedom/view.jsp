<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择权限</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>

<style type="text/css">
<!--
fieldset div {
	float:left;
	width:24%;
	line-height:25px;
	text-align:left;
}
td div {
	float:left;
	width:24%;
	line-height:25px;
	text-align:left;
}
-->
</style>
<script language="javascript">
   function goSelect(id){
      var valueStr=$("#"+id).val();
	  var array=valueStr.split(",");
	  if(array[0] != array[1]){
	     if($("#"+id)[0].checked){
		 	 var supid=array[0]+"_"+array[0];
		 	 $("#"+ supid).attr("checked","checked"); 
		 }else{
		 	 var $jihe=$("input[type='checkbox'][value^="+array[0]+"]:not([value$="+array[0]+"])"); 
			 var flag=false;
			 
			 $jihe.each(function(index,domEle){
				 if(this.checked){
				 	flag=true;
					return;
				 }
			 });
			 
			 if(!flag){
			 	  var supid=array[0]+"_"+array[0];
		 	       $("#"+ supid).attr("checked",null); 
			 }
		 }
	  }
	  
	  if(array[0]==array[1]){
	  	 if($("#"+id)[0].checked){
		     $("input[type='checkbox'][value^="+array[0]+"]").attr("checked","checked");   	
		 }else{
		 	  $("input[type='checkbox'][value^="+array[0]+"]").attr("checked",null); 
		 }
	  }
  }
  
  function SelectAllBox(){
     $("input[type=checkbox][name=popedomModule]").attr("checked","checked");
  }

  function UnSelectAllBox(){
	     $("input[type=checkbox][name=popedomModule]").attr("checked",null);
  }
</script>
</head>

<body>
<s:form name="ActionForm" method="post" action="sysRoleAction_updatePopedom.do" namespace="/sys">
<s:hidden name="roleId" value="%{#request.sysRole.id}" />
<br/>
<div class="control">
	<button type='button' class='button' onMouseOver="this.className='button_over';" 
	onMouseOut="this.className='button';"  onClick="SelectAllBox()"><img src="${pageContext.request.contextPath}/ui/images/button/quanbuxz.png" border='0' align='absmiddle'>&nbsp;全部选中</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" 
	onMouseOut="this.className='button';"  onClick="UnSelectAllBox()"><img src="${pageContext.request.contextPath}/ui/images/button/quanbubxz.png" border='0' align='absmiddle'>&nbsp;全部不选中</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" 
	onMouseOut="this.className='button';"  onClick="document.ActionForm.submit();"><img src="${pageContext.request.contextPath}/ui/images/button/baocun.png" border='0' align='absmiddle'>&nbsp;保存</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" 
	onMouseOut="this.className='button';"  onClick="parent.close();"><img src="${pageContext.request.contextPath}/ui/images/button/guanbi.png" border='0' align='absmiddle'>&nbsp;关闭</button>
</div>

<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#6A82A8">
	<tr>
		<td align="center" height="25"><span style="color:#FFFFFF; font-weight:bold">操作权限组：${sysRole.name}</span></td>
	</tr>
</table>
<div class="border" style="padding:3px">

<%-- <s:if test="#request.sysPopedoms != null}"> 这里使用Struts2标签很麻烦，所以使用jstl标签--%>
<c:if test="${! empty requestScope.sysPopedoms}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<!-- 这里不使用Struts2的标签是因为：数据量较大，循环的速度比标签解析的速度快些 -->
			<c:forEach items="${requestScope.sysPopedoms}" var="sysPopedomSup">
				<!-- 模块名称和操作名称对比 -->
				<c:if test="${sysPopedomSup.id.popedomModule == sysPopedomSup.id.popedomPrivilege}">
					<fieldset style='padding:5px;clear:left;'>
						<!-- 遍历权限组包含的权限 -->
						<!--处理父的复选框的回显  -->
						<c:forEach items="${requestScope.sysPopedomPrivileges }" var="sysPopedomPrivilege">
							<c:if test="${sysPopedomSup.id.popedomModule == sysPopedomPrivilege.id.popedomModule
										&& sysPopedomSup.id.popedomPrivilege == sysPopedomPrivilege.id.popedomPrivilege }">
								
								<!-- set标签var的值和value绑定在一起了 -->
								<c:set value="checked" var="xx" scope="page"/>
								<!-- 上面的set标签等价于pageContext.setAttribute("xx", "checked"); -->
							</c:if>
						</c:forEach>
						
					   	<legend><input type='checkbox' class='checkbox' name='popedomModule'  ${xx} 
							   title="${sysPopedomSup.title}" 
							   value='${sysPopedomSup.id.popedomModule},${sysPopedomSup.id.popedomPrivilege}'
							   id='${sysPopedomSup.id.popedomModule}_${sysPopedomSup.id.popedomPrivilege}' 
							   onClick='goSelect(this.id)'>${sysPopedomSup.popedomName}
						</legend>
						
						<!-- 从page域移除xx的值 -->
						<c:remove var="xx" scope="page" />
						
		   				<c:forEach items="${requestScope.sysPopedoms}" var="sysPopedomSub">
		   					<!-- 父模块名称和子模块名称相同 -->
			   				<c:if test="${sysPopedomSup.id.popedomModule == sysPopedomSub.id.popedomModule
			   							 &&	sysPopedomSub.id.popedomModule != sysPopedomSub.id.popedomPrivilege}">
							    <!--处理子的复选框的回显  -->
								<c:forEach items="${requestScope.sysPopedomPrivileges }" var="sysPopedomPrivilege">
									<c:if test="${sysPopedomSub.id.popedomModule == sysPopedomPrivilege.id.popedomModule
												&& sysPopedomSub.id.popedomPrivilege == sysPopedomPrivilege.id.popedomPrivilege }">
										
										<!-- set标签var的值和value绑定在一起了 -->
										<c:set value="checked" var="xxx" scope="page" />
										<!-- 上面的set标签等价于pageContext.setAttribute("xx", "checked"); -->
									</c:if>
								</c:forEach>
							    <div><input type='checkbox' class='checkbox' name='popedomModule' 
							   	title="${sysPopedomSub.title}" ${xxx }
							    value='${sysPopedomSub.id.popedomModule},${sysPopedomSub.id.popedomPrivilege}'
							    id='${sysPopedomSub.id.popedomModule}_${sysPopedomSub.id.popedomPrivilege}' 
							    onClick='goSelect(this.id)'>${sysPopedomSub.popedomName}</div>
							
								<c:remove var="xxx" scope="page" />
							</c:if>
						</c:forEach>
					 </fieldset>
				 </c:if>
			 </c:forEach>
		</td>
	</tr>
</table>
</c:if>
</div>
</s:form>
</body>
</html>