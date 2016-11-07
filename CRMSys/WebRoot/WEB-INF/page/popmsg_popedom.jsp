<%@ page contentType="text/html; charset=utf-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>出错了</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<script language="javascript">
function closedWin() {
	window.close();
}
</script>
</head>
<body>
<table width="100%" height="300" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center"><table width="380" border="0" align="center" cellpadding="0" cellspacing="0" class="error">
			<tr>
				<th align="left">出错提示</th>
			</tr>
			<tr>
				<td height="60"><font color="#FF0000">
			您无权进行此项操作！</font></td>
			</tr>
			<tr>
				<td style="text-align:center">
					<button type='button' class='button' onMouseOver="this.className='button_over';" 
					        onMouseOut="this.className='button';"  onClick="javascript:history.go(-1)">
					        <img src="${pageContext.request.contextPath}/ui/images/button/fanhui.png" border='0' align='absmiddle'>
					             &nbsp;返回</button>
				</td>
			</tr>
		</table></td>
	</tr>
</table>
</body>
</html>