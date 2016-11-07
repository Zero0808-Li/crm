<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门设置</title>
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
</script>
</head>
<body>
	<div class="mtitle">
		<div class="mtitle-row">&nbsp;</div>
		<span class="menu_selected">部门设置</span>
	</div>
	<br>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="tabForm">
		<tr>
			<th class="th_head">
				<div id="menuArrow1"
					style="background:url(${pageContext.request.contextPath}/ui/images/down.gif) no-repeat center;float:left;">&nbsp;</div>
				<div id="menuTitle1" style="font-weight: bold">部门搜索</div>
			</th>
			<th class="th_head"></th>
		</tr>
		<tr>
			<td colspan="2">
				<!-- 搜索提交的表单 -->
				<s:form name="form1" method="post"
					action="sysUserGroupAction_list.do" namespace="/sys">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						name="base" id="base">
						<tr>
							<td width="38%" nowrap="nowrap">
							部门名称：
								<s:textfield name="name" id="name" cssStyle="width: 140px"/>
							</td>
							<td width="39%" nowrap="nowrap">&nbsp;</td>
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
										onClick="forward('${pageContext.request.contextPath}/sys/sysUserGroupAction_list.do')">
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
			border="0">&nbsp;部门列表
	</h3>
	<div class="control">

		<button type='button' class='button'
			onMouseOver="this.className='button_over';"
			onMouseOut="this.className='button';"
			onClick="forward('${pageContext.request.contextPath}/sys/sysUserGroupAction_add.do')">
			<img
				src="${pageContext.request.contextPath}/ui/images/button/xinjian.png"
				border='0' align='absmiddle'>&nbsp;新建
		</button>


		<button type='button' class='button'
			onMouseOver="this.className='button_over';"
			onMouseOut="this.className='button';" onClick="document.forms[1].submit();">
			<img
				src="${pageContext.request.contextPath}/ui/images/button/shanchu.png"
				border='0' align='absmiddle'>&nbsp;删除
		</button>

	</div>
	
	<!-- list -->
	<div class="border">
		<s:form name="form2" method="post"
			action="sysUserGroupAction_delete.do" namespace="/sys">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				id="PowerTable" class="PowerTable">
				<!-- title -->
				<tr>
					<td width="5%" class="listViewThS1"><input type="checkbox"
						id="checkall" name="checkall" value="" class="checkbox"
						onClick="checkAll()"></td>
					<td width="20%" class="listViewThS1">部门名称</td>
					<td width="15%" class="listViewThS1">部门负责人</td>
					<td width="50%" class="listViewThS1">部门职能</td>
					<td width="10%" class="listViewThS1">人员设置</td>
				</tr>
				<s:if test="%{#request.sysUserGroups != null}">
					<s:iterator value="%{#request.sysUserGroups}" var="sysUserGroup">
						<tr>
							<td>
								<!-- 注意这里的value属性不能使用， 要使用Struts2提供的fieldValue，因为这里是多个id -->
								<s:checkbox name="ids" cssClass="checkbox" fieldValue="%{#sysUserGroup.id}" onClick="changeCheckCount();" />
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/sys/sysUserGroupAction_edit.do?id=<s:property value="%{#sysUserGroup.id}"/>">
									<s:property value="%{#sysUserGroup.name}"/>
								</a>
							</td>
							<td>
								<s:property value="%{#sysUserGroup.principal}"/>
							</td>
							<td>
								<s:property value="%{#sysUserGroup.incumbent}"/>
							</td>
							<td>
								<!-- 新打开一个窗口 -->
								<a href="javascript:OpenWin('${pageContext.request.contextPath}/sys/group/usersInGroup.jsp')">设置</a>
							</td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
		</s:form>
	</div>
</body>
<script type="text/javascript">
	/* 多选 */
	function changeCheckCount() {
		var count = 0;
		$("input[type='checkbox'][name='ids']").each(function(index, data) {
			if (this.checked) {
				count++;
			}
		});
		if (count == $("input[type='checkbox'][name='ids']").length) {
			$("#checkall").attr("checked", "checked");
		} else {
			$("#checkall").attr("checked", null);
		}
	}

	/* 全选  */
	function checkAll() {
		if ($("#checkall")[0].checked) {
			$("input[type='checkbox'][name='ids']").attr("checked", "checked");
		} else {
			$("input[type='checkbox'][name='ids']").attr("checked", null);
		}
	}
</script>
</html>