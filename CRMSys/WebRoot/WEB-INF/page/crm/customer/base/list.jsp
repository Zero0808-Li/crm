<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户拜访</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/win.js" type="text/javascript"></script>

<!--处理日期 开始 -->
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/calendar.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.css" type="text/css">
<!--处理日期结束  -->
</head>
<script type="text/javascript">
 function forward(strURL){
     window.location=strURL;
 }

 //处理客户共享
 function do_share(){
	 var count=0;
	 var ids="";
	 /* 获取所有的文本框，在遍历（each方法）,遍历出来的dom对象不是jQuery对象 */
	 $("input[type='checkbox'][name='ids']").each(function(index, data){
	    if(this.checked){
	       count++;
	       if(count==1){
	           ids=$(this).val();  //只选中了一个
	       }else{
	           ids=ids+","+$(this).val(); //选中了多个，使用拼接
	       }
	    }
	});
	 
	if(count==0){
       alert("必须有一个客户被选中!!!");
       return false;
	}
		
	if(count==1){  //选中一个客户的情况下
	   //alert("${pageContext.request.contextPath}/crm/companyAction_other.do?method=showshareSetOne&id="+ids);
	   OpenWin("${pageContext.request.contextPath}/crm/companyAction_showShareSetOne.do?id="+ids,'',500,400)
	   //OpenWin("${pageContext.request.contextPath}/crm/customer/base/shareSetOne.jsp",'',500,400);
	}else{  //选中多个的情况下（没有处理，需完善）
		 //OpenWin("${pageContext.request.contextPath}/crm/customer/base/shareSetMany.jsp",'',500,400);
	}
 }

 
</script>
<body>
<div class="mtitle">
	<div class="mtitle-row">&nbsp;</div>
	<span id="menu_selected" class="menu_selected">客户拜访</span>
</div>
<div class="link_title">
<br>&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/crm/customer/base/list.jsp">今天需要联系的客户</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/crm/customer/base/list.jsp">已过期未联系的客户</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/crm/customer/base/list.jsp">全部</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</div>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabForm">
  <tr>
    <th class="th_head">
		<div id="menuArrow1" style="background:url(${pageContext.request.contextPath}/ui/images/down.gif) no-repeat center;float:left;">&nbsp;</div>
		<div id="menuTitle1" style="font-weight:bold">客户搜索</div>
	</th>
  </tr>
  <tr>
	  <td colspan="2">
<!-- 基本查询的表单-->
<s:form name="form1" method="post" action="companyAction_list.do" namespace="/crm">
<table width="100%" border="0" cellspacing="0" cellpadding="0" name="base" id="base">
	<tr>
    	<td width="15%" nowrap>客户编码：</td>
   	    <td width="12%" nowrap><input name="code" type="text" id="code" value="" style="width:110px"></td>
  	    <td width="15%" nowrap>客户名称：</td>
		<td width="12%" nowrap><input name="name" type="text" id="name" value="" style="width:110px"></td>
		<td width="15%" nowrap>拼音码：</td>
		<td width="12%" nowrap><input name="pycode" type="text" id="pycode" value="" style="width:110px"></td>
  	    <td width="19%" align="center">
			<div class="control">
				<button type='button' class='button' onMouseOver="this.className='button_over';" 
				        onMouseOut="this.className='button';"  onClick="document.forms[0].submit();">
				        <img src="${pageContext.request.contextPath}/ui/images/button/sousuo.png" 
				             border='0' align='absmiddle'>&nbsp;搜索</button>
				        
				<button type='button' class='button' onMouseOver="this.className='button_over';"
					    onMouseOut="this.className='button';"  
				        onClick="forward('${pageContext.request.contextPath}/crm/customer/base/view.jsp')">
				        <img src="${pageContext.request.contextPath}/ui/images/button/qingkong.png" 
				             border='0' align='absmiddle'>&nbsp;清空</button>
			</div>
		</td>
	</tr>
	<tr>
		<td>电话一：</td>
		<td><input name="tel1" type="text" id="tel1" value="" style="width:110px"></td>
		<td>客户等级：</td>
	<td>
		<s:if test="%{#request.gradesSelect!=null}">
		  <s:select list="%{#request.gradesSelect}" id='grade' name='grade' cssStyle="width:110px"
		            listKey="value" listValue="value" headerKey="" headerValue="-------">
		  </s:select>
		</s:if>
	</td>
	
	<td>客户来源：</td>
	<td>
	 <s:if test="%{#request.sourcesSelect!=null}">
	  <s:select list="%{#request.sourcesSelect}" id='source' name='source' cssStyle="width:110px"
	            listKey="value" listValue="value" headerKey="" headerValue="-------">
	  </s:select>
	</s:if>
   </td>
	</tr>
	<tr>
		<td>客户性质：</td>
		<td>
			<s:if test="%{#request.qualitySelect!=null}">
				  <s:select list="%{#request.qualitySelect}" id='quality' name='quality' cssStyle="width:110px"
				            listKey="value" listValue="value" headerKey="" headerValue="-------">
				  </s:select>
		    </s:if>
	    </td>
	</tr>
</table>
</s:form>
<!-- 基本查询的表单结束 -->
</td>
  </tr>
</table>
<br>
<h3><img src="${pageContext.request.contextPath}/ui/images/menu/khlb.png" border="0">&nbsp;客户列表</h3>
<span id="slt_ids_count1"></span>
<div class="control">
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	     onClick="forward('${pageContext.request.contextPath}/crm/companyAction_add.do')">
	     <img src="${pageContext.request.contextPath}/ui/images/button/xinjian.png" border='0' align='absmiddle'>&nbsp;新建</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';" 
		    onClick="gonextTouchTime()">
	     <img src="${pageContext.request.contextPath}/ui/images/button/xiacilxsj.png" border='0' align='absmiddle'>&nbsp;下次联系时间</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="do_share()">
	     <img src="${pageContext.request.contextPath}/ui/images/button/gongxiang.png" border='0' align='absmiddle'>&nbsp;共享</button>

	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	         onClick="goChangePerson()">
	     <img src="${pageContext.request.contextPath}/ui/images/button/jinshourbg.png" border='0' align='absmiddle'>&nbsp;经手人变更</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  
	        onClick="document.forms[1].submit()" >
	     <img src="${pageContext.request.contextPath}/ui/images/button/shanchu.png" border='0' align='absmiddle'>&nbsp;删除</button>
</div>
<div class="border">
<s:form name="form2" method="post" action="companyAction_delete.do" namespace="/sys">
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="PowerTable" class="PowerTable">
	<tr>
    	<td width="3%" class="listViewThS1">
    	   <s:checkbox name="checkall" id="checkall" value="" cssClass="checkbox" onclick="checkAll()"/>
    	</td>
  	    <td width="30%" class="listViewThS1">客户名称</td>
  	    <td width="10%" class="listViewThS1">客户性质</td>
  	    <td width="10%" class="listViewThS1">客户等级</td>
        <td width="10%" class="listViewThS1">电话一</td>
        <td width="10%" class="listViewThS1">电子邮件</td>
        <td width="12%" class="listViewThS1">下次联系时间</td>
        <td width="7%" class="listViewThS1">联系人</td>
        <td width="8%" class="listViewThS1">联系记录</td>
        </tr>
	<!-- data -->
	<s:if test="#request.companyList!=null">
		<s:iterator value="#request.companyList"  var="company">
			<tr>
		    	<td>
		    	   <s:checkbox name="ids"  fieldValue="%{#company.id}" cssClass="checkbox" onclick="changeCheckCount();"/>
		    	</td>
		  	    <td>
		  	       <a href="${pageContext.request.contextPath}/crm/companyAction_edit.do?id=<s:property value="#company.id"/>"><s:property value="#company.name"/></a>
		  	    </td>
		  	    <td><s:property value="#company.quality"/></td>
		  	    <td><s:property value="#company.grade"/></td>
		        <td><s:property value="#company.tel1"/></td>
		        <td><s:property value="#company.email"/></td>
		        <td><s:property value="#company.nextTouchDate"/></td>
		        <td><a href="">查看</a></td>
		        <td><a href="">查看</a></td>
		        </tr>
		</s:iterator>
	</s:if>
</table>
</s:form>
</div>
<script type="text/javascript">
	function goChangePerson() {
		var count=0;
	    var ids="";
	    $("input[type='checkbox'][name='ids']").each(function(index,data){
	       if(this.checked){
	          count++;
	          if(count==1){
	              ids=$(this).val();
	          }else{
	              ids=ids+","+$(this).val();
	          }
	       }
	   });
	   if(count==0){
	         alert("必须有一个客户被选中!!!");
	         return false;
	   }
	  
	  var url = "${pageContext.request.contextPath}/crm/companyAction_showChangePerson.do?ids="+ids;
	  //var url = "${pageContext.request.contextPath}/crm/customer/base/changePerson.jsp";
	  OpenWin(url,'',480,140);
	}

	
	//处理下次联系时间
    function gonextTouchTime(){
	  var count=0;
	  var ids="";
	   //遍历所有的复选框
	   $("input[type='checkbox'][name='ids']").each(function(index,data){
	     if(this.checked){   //如果复选框处于选中状态
	        count++;         //count加1
	        if(count==1){    
	            ids=$(this).val();   //43,44,45   
	        }else{
	            ids=ids+","+$(this).val();
	        }
	     }
	     //如果没有被选中
	 });

	 //alert("ids  "+ids);
	   		 
	 if(count==0){
	       alert("必须有一个客户被选中!!!");
	       return false;
	 }
	 OpenWin("${pageContext.request.contextPath}/crm/companyAction_showNextTouchTime.do?ids="+ids,'',420,300);
	}

	
   function changeCheckCount(){
       var count=0;
	   $("input[type='checkbox'][name='ids']").each(function(index,data){
            if(this.checked){
            	count++;  
            }
	   });
	   $("#slt_ids_count2").empty();
 	   $("#slt_ids_count2").append(count);

       if(count== $("input[type='checkbox'][name='ids']").length){
    	   $("#checkall").attr("checked","checked");
       }else{
    	   $("#checkall").attr("checked",null);
       }
   }
   
   function  checkAll(){
      if($("#checkall")[0].checked){
    	  $("input[type='checkbox'][name='ids']").attr("checked","checked");
    	  $("#slt_ids_count2").empty();
    	  $("#slt_ids_count2").append($("input[type='checkbox'][name='ids']").length);
      }else{
    	  $("input[type='checkbox'][name='ids']").attr("checked",null);
    	  $("#slt_ids_count2").empty();
    	  $("#slt_ids_count2").append(0);
      }
   }
 </script>
</body>
</html>