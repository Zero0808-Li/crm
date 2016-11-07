/*
 * 是否显示高级查询
 *  显示基本查询的表格定义
 *  <table width="100%" border="0" cellspacing="0" cellpadding="0" name="base" id="base">
 *  显示高级查询的表格定义
 *  <table width="100%" style="display:none" border="0" cellspacing="0" cellpadding="0" name="adv" 
 *         id="adv">
 *         
 *  是否显示高级查询连接定义
 *  <div align="right" name="base1" id="base1">
 *           <a class="tabFormAdvLink" href="javascript:showadv(0);">
 *           <img src="..." />&nbsp;高级查询</a>
 *   </div>
 *   <div style="display:none" align="right" name="adv1" id="adv1">
 *          <a class="tabFormAdvLink" href="javascript:showadv(1);">
 *           <img src="..." />&nbsp;基本查询</a>
 *   </div>
 */
//function showadv(checks){
//	if(checks==0){  //==0
//		$("#adv").css("display","");
//		$("#base").css("display","none");
//		//是否显示高级查询 基本查询
//		//用一个表达式来检查当前选择的元素集合，如果其中至少有一个元素符合这个给定的表达式就返回true。
//	    if($("#adv1").is(":hidden")){
//		 	$("#adv1").css("display","");
//		    $("#base1").css("display","none");
//		}
//	}else{  //==1
//		$("#adv").css("display","none");
//		$("#base").css("display","");
//		if($("#adv1").is(":visible")){
//		 	$("#adv1").css("display","none");
//		    $("#base1").css("display","");
//		}
//		
//	}
//}


//function delForm(url) {
//	var f = document.ActionForm;
//	var ids = document.all.ids;
//	var select = false;
//	if(ids == undefined) {
//		alert("请选择一条记录！");
//		return;
//	}
//	if(ids.length==undefined && ids.checked)
//		select = true;
//	for(var i=0;i<ids.length;i++) {
//		var temp = ids[i];
//		if(temp.checked) {
//			select = true;
//			break;
//		}
//	}
//	if(select) {
//		if(confirm("您确定删除吗?(删除后是不可恢复的)")) {
//			f.action = url;
//			f.submit();
//		}
//	}else{
//		alert("请选择一条记录！");
//	}
//}
//
//function changeStatus(url) {
//	var f = document.ActionForm;
//	var ids = document.all.ids;
//	var select = false;
//	if(ids == undefined) {
//		alert("请选择一条记录！");
//		return;
//	}
//	if(ids.length==undefined && ids.checked)
//		select = true;
//	for(var i=0;i<ids.length;i++) {
//		var temp = ids[i];
//		if(temp.checked) {
//			select = true;
//			break;
//		}
//	}
//	if(select) {
//		if(confirm("您确定继续吗?(启用或停用后将改变记录状态)")) {
//			f.action = url;
//			f.submit();
//		}
//	}else{
//		alert("请选择一条记录！");
//	}
//}
//
//function recordMove(url) {
//	var f = document.ActionForm;
//	var id = "";
//	var select = false;
//	for(var i=0;i<f.elements.length;i++) {
//		var temp = f.elements[i];
//		if(temp.name=="ids" && temp.checked) {
//			select = true;
//			id += temp.value+",";
//		}
//	}
//	id = id.substring(0,id.length-1);
//	if(select) {
//		if(id.indexOf(",")!=-1) {
//			alert("只能同时对一条记录进行移动!");
//		}
//		else if(confirm("您确定移动您选择的记录吗?")) {
//			url = url + id;
//			window.location = url;
//		}
//	}else{
//		alert("请选择一条记录！");
//	}
//}
//
////submit ActionForm
//function doSubmit(url) {
//	var f = document.ActionForm;
//	if(url!=null)
//		f.action = url;
//	f.submit();
//}
//
////check all checkbox
//function checkAll(checked) {
//	var f = document.all.ids;
//	if(f != undefined) {
//		for(var i=0;i<f.length;i++) {
//			f[i].checked = checked;
//		}
//	}
//}
//
////submit SearchForm
//function doSearch() {
//	var f = document.SearchForm;
//	f.submit();
//}
//
//function getIds() {
//	var f = document.ActionForm;
//	var ids = document.all.ids;
//	var s = "";
//	if(ids == null)
//		s = "";
//	else if(ids.length==undefined && ids.checked)
//		s = ids.value;
//	else
//
//	for(var i=0;i<ids.length;i++) {
//		var temp = ids[i];
//		if(temp.checked) {
//			if(s == "")
//				s = temp.value;
//			else
//				s += ","+temp.value;
//		}
//	}
//
//	return s;
//}
//
