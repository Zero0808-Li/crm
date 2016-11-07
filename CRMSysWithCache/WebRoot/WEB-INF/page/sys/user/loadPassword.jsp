<%@ page contentType="text/html; charset=utf-8"  contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/popshow.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>
<script type="text/javascript">
function do_update(){
	var psw = document.getElementById("password").value;  //新密码
	var psw1 = document.getElementById("password1").value;  //重复密码
	if(!psw || psw=="" || !psw1 || psw1==""){
		alert("密码不能为空!");
		return;
	}
	if(psw!=psw1){
		alert("两次输入的密码不一致!");
		return;
	}

	//AJAX
	//组织路径
	var action = "${pageContext.request.contextPath}/sys/sysUserAction_updatePassword.do?";
	var id="${param.id}";
	var paramStr = action+"&id="+id+"&password="+psw;

    $.post(paramStr,function(data,textStatus,xmlHttp){         
    	//document.getElementById("msg").innerHTML = "<font color='red'>"+data+"</font>";
    	$("#msg").empty();   //删除   请修改密码
    	$("#msg").append("<font color='red'>"+data+"</font>");
        $("#password").val("");
        $("#password1").val("");
    	
    });
}

</script>
</head>

<body>
<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
    onClick="do_update();">
    <img src="${pageContext.request.contextPath}/ui/images/button/baocun.png" border='0' 
         align='absmiddle'>&nbsp;保存</button>
    <span id="msg" style="display: null">请修改密码</span>
<table width="100%" border="0" cellspacing="0" class="tabForm">
  <tr>
    <td colspan="4" class="th_head">
		<div id="menuArrow1" style="background:url(${pageContext.request.contextPath}/ui/images/down.gif) no-repeat center;float:left;">&nbsp;</div>
		<div id="menuTitle1" style="font-weight:bold">基本信息</div>
	</td>
  </tr>
  <tr>
  	<td>
		<div id="menu1">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="18%" height="19" class="tabDetailViewDL">用 户 名：</td>
			<td width="29%" class="tabDetailViewDF"><s:property value="#parameters.name[0]"/>&nbsp;</td>
			<td width="17%" class="tabDetailViewDL">中 文 名：</td>
			<td width="36%" class="tabDetailViewDF">
				<s:property value="#parameters.cnname[0]"/>&nbsp;</td>
		  </tr>
		  <tr>
			<td class="tabDetailViewDL">新 密 码：</td>
			<td class="tabDetailViewDF" colspan="3"><s:password id="password" name="password"/></td>
		</tr>
		<tr>
			<td class="tabDetailViewDL">重复新密码：</td>
			<td class="tabDetailViewDF" colspan="3"><s:password id="password1" name="password1"/></td>
		  </tr>
		</table>
		</div>
	</td>
  </tr>
</table>
<br>
</body>
</html>