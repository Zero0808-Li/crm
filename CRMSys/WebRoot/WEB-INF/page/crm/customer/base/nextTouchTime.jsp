<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设定下次联系时间</title>
<link href="${pageContext.request.contextPath}/ui/css/style_cn.css" rel="stylesheet" type="text/css">


<!--处理日期 开始 -->
<script src="${pageContext.request.contextPath}/ui/js/jquery-1.4.2.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/date_input/calendar.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/js/date_input/jquery.datepick.css" type="text/css">
<!--处理日期结束  -->
<script language="javascript">
function Date_of_Today(){ 
    var now=new Date();
    //alert(now);
    var year=now.getYear();
    var mm=now.getMonth()+1;
    var dd=now.getDate();
    if(mm<10) mm="0"+mm;
    if(dd<10) dd="0"+dd;
    //alert(year+"-"+mm+"-"+dd);
    return year+"-"+mm+"-"+dd;
}

function check(){
	var next_touch_date=$("#next_touch_date").val();
    if($.trim(next_touch_date)==""){
       alert("下次联系时间不能为空");
       $("#next_touch_date").focus();
       return false;
    }
    var curDate=Date_of_Today();
	
    //这行代码进不去，打开上面的alert看看输出就明白了，年份好像有问题
    if(next_touch_date<curDate){
       alert("下次联系时间必须大于系统的当前时间");
       $("#next_touch_date").focus();
    }

   return true;
}
</script>
</head>

<body>
<!-- 这里的提交校验好像有问题，参考上面的JS代码 -->
<s:form name="form1" method="post" action="companyAction_updateNextTouchTime.do" namespace="/crm" onsubmit="return check();" >
<s:hidden name="ids" value="%{#parameters.ids[0]}"/>
<div class="mtitle">
	<div class="mtitle-row">&nbsp;</div>
	设定下次联系时间
</div>
<br/>
<div class="control">
	<button type='button' class='button' onMouseOver="this.className='button_over';" 
            onMouseOut="this.className='button';"  onClick="document.forms[0].submit();">
            <img  src="${pageContext.request.contextPath}/ui/images/button/baocun.png" border='0' 
                  align='absmiddlce'>&nbsp;保存</button>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="10" class="tabForm">
	<tr>
				<td width="30%">下次联系时间：</td>
        <td width="70%">
             <input type='text' class="dateClassStyle"  id='next_touch_date' name='next_touch_date' value=''  style='width:50%'>
        </td>
	</tr>
</table>
</s:form>
</body>
</html>
