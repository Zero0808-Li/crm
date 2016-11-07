<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>操作权限组</title>
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css"
	rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/win.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function forward(strURL) {
		window.location = strURL;
	}

	function deleteIds() {
		document.forms[1].submit();
	}
</script>
<script type="text/javascript">
	function setPopedom(id) {
		OpenWin(
				'${pageContext.request.contextPath}/sys/sysRoleAction_listPopedom.do?roleId='
						+ id, '', 800, 600); //800 600的大小显示
	}
	function setMenu(id) {
		OpenWin(
				'${pageContext.request.contextPath}/sys/sysRoleAction_listMenu.do?roleId='
						+ id, '', 800, 600);
	}
</script>
</head>

<body>
	<div class="mtitle">
		<div class="mtitle-row">&nbsp;</div>
		<span class="menu_selected">操作权限组</span>
	</div>
	<br>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="tabForm">
		<tr>
			<th class="th_head">
				<div id="menuArrow1"
					style="background:url(${pageContext.request.contextPath}/ui/images/down.gif) no-repeat center;float:left;">&nbsp;</div>
				<div id="menuTitle1" style="font-weight: bold">操作权限组搜索</div>
			</th>
			<th class="th_head"></th>
		</tr>
		<tr>
			<td colspan="2">
				<s:form name="searchForm" method="post"
					action="sysRoleAction_list.do" namespace="/sys">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						name="base" id="base">
						<tr>
							<td width="38%" nowrap>名称：
								<s:textfield name="name" id="name" cssStyle="width: 140px"/>
							</td>
							<td width="39%" nowrap>&nbsp;</td>
							<td width="23%" align="center">
								<div class="control">
									<button type='button' class='button'
										onMouseOver="this.className='button_over';"
										onMouseOut="this.className='button';"
										onClick="document.forms[0].submit()">
										<img
											src="${pageContext.request.contextPath}/ui/images/button/sousuo.png"
											border='0' align='absmiddle'>&nbsp;搜索
									</button>
									<button type='button' class='button'
										onMouseOver="this.className='button_over';"
										onMouseOut="this.className='button';"
										onClick="forward('${pageContext.request.contextPath}/sys/sysRoleAction_list.do')">
										<img
											src="${pageContext.request.contextPath}/ui/images/button/qingkong.png"
											border='0' align='absmiddle'>&nbsp;清空
									</button>
								</div>
							</td>
						</tr>
					</table>
				</s:form>
			</td>
		</tr>
	</table>
	<br>
	<h3>
		<img src="${pageContext.request.contextPath}/ui/images/menu/khlb.png"
			border="0">&nbsp;操作权限组列表
	</h3>
	<div class="control">
		<button type='button' class='button'
			onMouseOver="this.className='button_over';"
			onMouseOut="this.className='button';"
			onClick="forward('${pageContext.request.contextPath}/sys/sysRoleAction_add.do')">
			<img
				src="${pageContext.request.contextPath}/ui/images/button/xinjian.png"
				border='0' align='absmiddle'>&nbsp;新建
		</button>
		<button type='button' class='button'
			onMouseOver="this.className='button_over';"
			onMouseOut="this.className='button';" onClick="deleteIds()">
			<img
				src="${pageContext.request.contextPath}/ui/images/button/shanchu.png"
				border='0' align='absmiddle'>&nbsp;删除
		</button>
	</div>
	<!-- list -->
	<div class="border">
		<s:form name="ActionForm" method="post"
			action="sysRoleAction_delete.do" namespace="/sys">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				id="PowerTable" class="PowerTable">
				<tr>
					<td width="6%" class="listViewThS1">
					<input type="checkbox"
						id="checkall" name="checkall" value="" class="checkbox"
						onClick="checkAll()">
					</td>
					<td width="17%" class="listViewThS1">名称</td>
					<td width="27%" class="listViewThS1">可访问菜单</td>
					<td width="28%" class="listViewThS1">可进行的操作</td>
				</tr>
				<s:if test="%{#request.sysRoles != null}">
					<s:iterator value="%{#request.sysRoles}" var="sysRole">
						<tr>
							<td>
								<s:checkbox fieldValue="%{#sysRole.id}" name="ids" cssClass="checkbox" onClick="changeCheckCount();"/>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/sys/sysRoleAction_edit.do?id=<s:property value="%{#sysRole.id}" />">
									<s:property value="%{#sysRole.name}" />
								</a>
							</td>
							<td><a href="#" onClick="setMenu('<s:property value="%{#sysRole.id}" />')">设置</a></td>
							<td><a href="#" onClick="setPopedom('<s:property value="%{#sysRole.id}" />')">设置</a></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
		</s:form>
	</div>
</body>
<script type="text/javascript">
	function changeCheckCount() {
		var count = 0;
		$("input[type='checkbox'][name='ids']").each(function(index, data) {
			if (this.checked) {
				count++;
			}
		});
		$("#slt_ids_count2").empty();
		$("#slt_ids_count2").append(count);

		if (count == $("input[type='checkbox'][name='ids']").length) {
			$("#checkall").attr("checked", "checked");
		} else {
			$("#checkall").attr("checked", null);
		}

	}

	function checkAll() {
		if ($("#checkall")[0].checked) {
			$("input[type='checkbox'][name='ids']").attr("checked", "checked");
			$("#slt_ids_count2").empty();
			$("#slt_ids_count2").append(
					$("input[type='checkbox'][name='ids']").length);
		} else {
			$("input[type='checkbox'][name='ids']").attr("checked", null);
			$("#slt_ids_count2").empty();
			$("#slt_ids_count2").append(0);
		}
	}
</script>
</html>