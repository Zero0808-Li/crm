<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>客户资料新建</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/ui/js/win.js" type="text/javascript"></script>

<!--处理日期 开始 -->
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/calendar.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.css" type="text/css">
<!--处理日期结束  -->

<script type="text/javascript">
	function name2pinyin(){
       var nameValue=$("#name").val();
       /*
       	Jquery的post请求中的各个数据的含义：
       		1：请求路径
       		2：请求发送的请求参数
       		3：回调函数：
       			1：回调函数中的参数第一个代表请求返回的数据
       			2：状态
       */
       $.post(
   		  "${pageContext.request.contextPath}/crm/companyAction_pinyin.do",
   		  {name:nameValue},
   		  function(data,textStatuts){
            $("#pycode").val(data);  // * val方法，既可以获取值也可以设置值，没有参数的时候就是获取值，有参数的时候就是设置值，JQuery的很多方法都是这样的
      	});
	}

	/* 这里要重点去理解下，编程思路要记住 */
	function showCity(value){
		/* 这里处理的是一个选项改变的事件，当下拉选的取值改变时，就会请求后天回去数据(这就是的就是所谓的二级联动) */
	    $.post("${pageContext.request.contextPath}/crm/companyAction_showCity.do",{name:value} ,function(data,textStatuts){
             //alert(data);
             var dataObj = eval("("+data+")");  // * data为请求处理后的返回值
             //alert(dataObj);
             
             //先之前的省删除城市
             $("select[name='city'] option[value!='']").remove();
             
             // <select name="city" style="width:90%">
                 //<option value="">--------</option>
             //</select>
             for(var i=0; i<dataObj.length; i++){
                   var $option = $("<option></option>");
                   $option.attr("value", dataObj[i].name);
                   $option.text(dataObj[i].name);
                   $("select[name='city']").append($option);
             }
        });
	}
	
</script>
</head>
<body>
<s:form name="companyForm" method="post" action="companyAction_save.do" namespace="/crm">
<div class="mtitle">
	<div class="mtitle-row">&nbsp;</div>
	客户-新建
</div>
<br>
<div class="control">
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';" 
		   onClick="document.forms[0].submit()">
	      <img src="${pageContext.request.contextPath}/ui/images/button/baocun.png" border='0' align='absmiddle'>&nbsp;保存</button>
	<button type='button' class='button' onMouseOver="this.className='button_over';" onMouseOut="this.className='button';"  onClick="">
		  <img src="${pageContext.request.contextPath}/ui/images/button/fanhui.png" border='0' align='absmiddle'>&nbsp;返回</button>
    <span class="remarkText"></span>
</div>
<table width="100%" border="0" cellspacing="0" class="tabForm">
  <tr>
    <th colspan="4" class="th_head">
		<div id="menuArrow1" style="background:url(${pageContext.request.contextPath}/ui/images/down.gif) no-repeat center;float:left;">&nbsp;</div>
		<div id="menuTitle1" style="font-weight:bold">基本信息</div>
	</th>
  </tr>
  <tr>
  	<td>
		<div id="menu1">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="16%" class="red">客户编码：</td>
			<td width="34%"><s:textfield id="code" name="code" value="%{#request.code}"  cssStyle="width:90%"/></td>
			<td width="16%" class="red">客户名称：</td>
			<td width="34%"> 
			  <s:textfield id="name" name="name" value=""  cssStyle="width:90%"  onblur="name2pinyin()"/>
			</td>
		  </tr>
		  <tr>
			<td>拼音码：</td>
			<td> 
			  <s:textfield id="pycode" name="pycode" value="" cssStyle="width:90%" readonly="true" cssClass="disabled"/>
			</td>
			<td>客户等级：</td>
			<td>
			   <s:if test="#request.gradesSelect!=null">
			        <s:select list="#request.gradesSelect" name="grade" id="grade"  
			                listKey="value" listValue="value" 
			                headerKey="" headerValue="--------"
			                cssStyle="width:90%"></s:select>
			   </s:if>
			</td>
		  </tr>
		  <tr>
			<td>区域名称：</td>
			<td>
			     <s:if test="#request.regionNamesSelect!=null">
			        <s:select list="#request.regionNamesSelect" name="regionName" id="regionName"  
			                listKey="value" listValue="value" 
			                headerKey="" headerValue="--------"
			                cssStyle="width:90%"></s:select>
			     </s:if>
            </td>
			<td>客户来源：</td>
			<td>
                <select name="source" style="width:90%">
                        <option value="">--------</option>
			            <option value="招标投标">招标投标</option>
						<option value="展销会议">展销会议</option>
						<option value="报刊杂志">报刊杂志</option>
						<option value="直销业务">直销业务</option>
						<option value="朋友介绍">朋友介绍</option>
						<option value="户外广告">户外广告</option>
						<option value="互联网">互联网</option>
						<option value="代理渠道">代理渠道</option>
						<option value="促销活动">促销活动</option>
						<option value="电话联系">电话联系</option>
                </select>
			 </td>
		  </tr>
		  <tr>
			<td>所属行业：</td>
			<td><select id='trade' name='trade' style='width:90%'>
					<option value=''>------</option>
					<option value='政府机构'>政府机构</option>
					<option value='玩具'>玩具</option>
					<option value='服饰'>服饰</option>
					<option value='化工'>化工</option>
					<option value='机械及工业制品'>机械及工业制品</option>
             </select>
           </td>
			<td>公司规模：</td>
			<td><select id='scale' name='scale' style='width:90%'>
					<option value=''>------</option>
					<option value='300人以上'>300人以上</option>
					<option value='100至300人'>100至300人</option>
					<option value='50至100人'>50至100人</option>
					<option value='少于50人'>少于50人</option>
               </select>
              </td>
		  </tr>
		  <tr>
			<td>省份：</td>
			<td>
			  <s:if test="#request.provincesSelect!=null">
			      <s:select list="#request.provincesSelect" name="province" id="province"  
			                listKey="name" listValue="name" 
			                headerKey="" headerValue="--------"
			                onchange="showCity(this.value)" cssStyle="width:90%"></s:select>
			  </s:if>
			</td>
			<td>城市：</td>
			<td>
			     <s:select list="{}" name="city" id="city"
			                headerKey="" headerValue="--------" cssStyle="width:90%"></s:select>
		    </td>
		  </tr>
		  <tr>
			<td>邮政编码：</td>
			<td>
			  <s:textfield  name="postcode" value="" id="postcode"  cssStyle="width:90%"/>
			</td>
		  </tr>
		  <tr>
			<td>联系地址：</td>
			<td colspan="3">
			   <s:textfield  name="address" value="" id="address"  cssStyle="width:96%"/>
			</td>
			</tr>
		  <tr>
			<td>电子邮件：</td>
			<td>
			  <s:textfield  name="email" id="email"  cssStyle="width:96%"  value=""/>
			</td>
			<td>公司网站：</td>
			<td>
			   <s:textfield  name="web" id="web"  cssStyle="width:90%"  value=""/>
			</td>
		  </tr>
		  <tr>
			<td>电话一：</td>
			<td><s:textfield  name="tel1" id="tel1"  cssStyle="width:90%"  value=""/></td>
			<td>传真：</td>
			<td><s:textfield  name="fax" id="fax"  cssStyle="width:90%"  value=""/></td>
		  </tr>
		  <tr>
		  	<td>手机：</td>
			<td>
			    <s:textfield  name="mobile" id="mobile"  cssStyle="width:90%"  value=""/>
			</td>
			<td>电话二：</td>
			<td>
			    <s:textfield  name="tel2" id="tel2"  cssStyle="width:90%"  value=""/>
			</td>
		  </tr>
		  <tr>
			<td>下次联系时间:</td>
			<td>
			   <s:textfield  name="nextTouchDate" id="nextTouchDate"  cssStyle="width:90%" cssClass="dateClassStyle"  value=""/>
		    </td>
			<td>客户性质：</td>
			<td><select id='quality' name='quality' style='width:90%'>
				<option value=''>------</option>
				<option value='下属子公司'>下属子公司</option>
				<option value='上级主管单位'>上级主管单位</option>
				<option value='竞争对手'>竞争对手</option>
				<option value='合作伙伴'>合作伙伴</option>
				<option value='代理商'>代理商</option>
				<option value='供应商'>供应商</option>
				<option value='客户' selected>客户</option>
               </select>
            </td>
		  </tr>
		  <tr>
			<td valign="top">备注：</td>
			<td colspan="3">
			  <s:textarea name="remark" rows="4" id="remark" cssStyle="width:96%"></s:textarea>
			</td>
		  </tr>
		  </table>
		</div>
	</td>
  </tr>
</table>
<br>
<table width="100%" border="0" cellspacing="0" class="tabForm">
  <tr>
    <th colspan="4" class="th_head">
		<div id="menuArrow2" style="background:url(${pageContext.request.contextPath}/ui/images/down.gif) no-repeat center;float:left;">&nbsp;</div>
		<div id="menuTitle2" style="font-weight:bold">企业信息</div>
	</th>
  </tr>
  <tr>
  	<td>
  <div id="menu2">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="16%">经营范围：</td>
			<td width="34%">
				<select id='dealin' name='dealin' style='width:90%'>
						<option value='' >------</option>
						<option value='证券/金融/投资'>证券/金融/投资</option>
						<option value='电子/电器/半导体/仪器仪表'>电子/电器/半导体/仪器仪表</option>
						<option value='计算机软件'>计算机软件</option>
						<option value='计算机硬件'>计算机硬件</option>
	            </select>
          </td>
			<td width="16%">企业性质：</td>
			<td width="34%">
			   <select id='kind' name='kind' style='width:90%'>
					<option value='' >------</option>
					<option value='合资企业'>合资企业</option>
					<option value='外资企业'>外资企业</option>
					<option value='民营企业'>民营企业</option>
					<option value='国有企业'>国有企业</option>
	          </select>
          </td>
		  </tr>
		  <tr>
			<td>法人代表：</td>
			<td>
			   <s:textfield  name="artificialPerson" id="artificialPerson"  cssStyle="width:90%"  value=""/></td>
			<td>注册资金：</td>
			<td><s:textfield  name="registeMoney" id="registeMoney"  cssStyle="width:90%"  value=""/></td>
		  </tr>
		  <tr>
			<td>开户银行：</td>
			<td><s:textfield  name="bank" id="bank"  cssStyle="width:90%"  value=""/></td>
			<td>银行账户：</td>
			<td><s:textfield  name="account" id="account"  cssStyle="width:90%"  value=""/></td>
		  </tr>
		  <tr>
			<td>公司税号：</td>
			<td><s:textfield  name="taxCode" id="taxCode"  cssStyle="width:90%"  value=""/></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		  </tr>
  		</table>
	</div>
	</td>
	</tr>
</table>
<br>
<table width="100%" border="0" cellspacing="0" class="tabForm">
  <tr>
    <th colspan="4" class="th_head">
		<div id="menuArrow3" style="background:url(${pageContext.request.contextPath}/ui/images/down.gif) no-repeat center;float:left;">&nbsp;</div>
		<div id="menuTitle3" style="font-weight:bold">其他信息</div>
	</th>
  </tr>
  <tr>
  	<td>
  <div id="menu3">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="16%">创建人：</td>
			<td width="34%">
			   <s:textfield  name="creater" id="creater"  cssStyle="width:90%" cssClass="disabled" />
			</td>
			<td width="16%">创建日期：</td>
			<td width="34%">
			  <s:textfield  name="createTime" id="createTime"  cssStyle="width:90%" cssClass="disabled" />
		   </td>
		  </tr>
		  <tr>
			<td>修改人：</td>
			<td>
			   <s:textfield  name="updater" id="updater"  cssStyle="width:90%" cssClass="disabled" />
			</td>
			<td>修改日期：</td>
			<td>
			<s:textfield  name="updateTime" id="updateTime"  cssStyle="width:90%"  cssClass="disabled" />
		   </td>
		  </tr>
		  <tr>
			<td>所属人：</td>
			<td>
   			  <!-- 保存所有人的姓名 -->
   			  <s:textfield  name="dispensePerson" id="dispensePerson"  cssStyle="width:90%" cssClass="disabled"/>
			  <!-- 保存所属人的id -->
			  <s:hidden name="ownerUser"/>
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		  </tr>
  		</table>
	</div>
	</td>
  </tr>
</table>
</s:form>
<script src="${pageContext.request.contextPath}/ui/js/menu.js" type="text/javascript"></script>
</body>
</html>