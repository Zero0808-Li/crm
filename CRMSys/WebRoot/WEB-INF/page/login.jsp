<%@ page import="java.net.URLDecoder"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CRM客户关系管理系统</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css"
	rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js"
	type="text/javascript"></script>
<!-- 下面的JS可能要和jQuery冲突，将其注释掉 -->	
<%-- <script src="${pageContext.request.contextPath}/ui/js/opform.js"
	type="text/javascript"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/ui/js/global.js"
	type="text/javascript"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/ui/js/win.js"
	type="text/javascript"></script> --%>
<style>
.font {
	color: #000000;
	font-size: 9pt;
	font-family: "宋体"
}

.td {
	font-size: 9pt;
	font-family: "宋体";
	height: 25px;
}

input {
	background-color: #FFFFFF;
}

body {
	margin: 0px;
}
</style>
<script type="text/javascript">
	//验证码
	function changeCheckNum() {
		var checkNumImage_ = document.getElementById("checkNumImage");
		checkNumImage_.src = "${pageContext.request.contextPath}/image.jsp?timeStamp="
				+ new Date().getTime(); //加时间戳，防止缓存
	}

	//登录
	function checkSubmit() {
		if ($("#name").val().length <= 0) {
			alert("登陆的用户名不能为空");
			return false;
		}
		if ($("#password").val().length <= 0) {
			alert("登陆的密码不能为空");
			return false;
		}
		if ($("#checkNum").val().length <= 0) {
			alert("验证码不能为空");
			return false;
		}
		document.forms[0].submit();
	}
</script>
</head>

<body topmargin="0" leftmargin="0"
	onLoad="if (window.location != window.top.location) window.top.location.href=window.location.href; document.all.userName.focus();setUserName();">
<%
	/* 获取服务器端发送过来的Cookie，用于记住用户名和密码 */
	String name = "";
	String psw = "";
	String checked = "";
	Cookie[] cookies = request.getCookies();
	if (cookies != null && cookies.length > 0) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			//Cookie的获取key值是getName()方法
			if ("name".equals(cookie.getName())) {
				/* 服务端进行了编码（目的是防止中文乱码），客户端进行解码 */
				name = URLDecoder.decode(cookie.getValue(), "UTF-8");
				/* 记住我的勾选 */
				checked = "checked";
			}
			if ("psw".equals(cookie.getName())) {
				psw = cookie.getValue();
			}
		}
	}
%>
	<form name="form1" method="post"
		action="${pageContext.request.contextPath}/sys/sysUserAction_isLogin.do">
		<table width="100%" cellpadding="0" cellspacing="0" id="header">
			<tr>
				<td>
					<table height="80" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="225"><img
								src="${pageContext.request.contextPath}/ui/images/ban_1.gif"></td>
							<td
								background="${pageContext.request.contextPath}/ui/images/ban_2.gif">&nbsp;</td>
							<td width="181"
								background="${pageContext.request.contextPath}/ui/images/ban_2.gif"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="119"
					background="${pageContext.request.contextPath}/ui/images/bg.gif">&nbsp;</td>
			</tr>
			<tr>
				<td height="300">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top" align="center"><img
								src="${pageContext.request.contextPath}/ui/images/logo.jpg">
							</td>
							<td width="44" height="200"
								background="${pageContext.request.contextPath}/ui/images/line.png">&nbsp;</td>
							<td width="540" valign="top">
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td height="140" valign="top"><font class="font">请输入您的用户名及密码:</font>
											<br> <font color="#FF0000"></font><br>
											<table cellpadding="0" cellspacing="0">
												<tr>
													<td class="td">用户帐号：</td>
													<td class="td">
														<input name="name" type="text" value="<%=name%>" id="name" />
														<font color="red"><s:fielderror fieldName="name" /></font>	
													</td>
												</tr>
												<tr>
													<td class="td">登录密码：</td>
													<td class="td"><input name="password" type="password"
														value="<%=psw%>" id="password"></td>
												</tr>
												<tr>
													<td class="td">验&nbsp;证&nbsp;码：</td>
													<td class="td"><input name="checkNum" type="text"
														value="" id="checkNum" style="width: 80"> <img
														id="checkNumImage"
														src="${pageContext.request.contextPath}/image.jsp"
														height="19" align="absmiddle" onClick="changeCheckNum()"
														title="点击换一张" style="cursor: hand">
														<!-- 打成错误提示信息fieldName是Action中的错误消息的key值 -->
														<font color="red"><s:fielderror fieldName="checkNum" /></font>
													</td>
												</tr>
												<tr>
													<td class="td">记&nbsp;住&nbsp;我：</td>
													<td class="td"><input name="rememberMe"
														type="checkbox" id="rememberMe" value="yes"
														class="checkbox" <%=checked %>></td>
												</tr>
											</table></td>
									</tr>
									<tr>
										<td><img
											src="${pageContext.request.contextPath}/ui/images/login.png"
											id="login" onClick="checkSubmit();" style="cursor: hand">
											&nbsp;&nbsp;<img
											src="${pageContext.request.contextPath}/ui/images/reset.png"
											id="reset" onClick="" style="cursor: hand"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center"><br> <a href="https://github.com/CUITLLB/crm"
					target="_blank">©copyright:https://github.com/CUITLLB/crm</a></td>
			</tr>
			<tr>
				<td height="5">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>