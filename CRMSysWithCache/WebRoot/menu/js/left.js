//	function ToggleNav(){
//		if(document.getElementById("divCollapsedNav").style.display == "none")	{
//			alert("dddd");
//			document.getElementById("divCollapsedNav").style.display = "";
//			document.getElementById("main").style.display = "none";
//			parent.frmstOuter.cols = "30,*";
//			parent.document.all ('leftFrame').noResize = true;
//			parent.document.all ('leftFrame').framespacing= 0;
//			
//		} else {
//			document.getElementById("main").style.display = "";
//			document.getElementById("divCollapsedNav").style.display = "none";
//			parent.frmstOuter.cols = "200,*";
//			parent.document.all ('leftFrame').noResize = false;
//		}
//	}


	function ToggleNav(){
		if($("#divCollapsedNav").is(":hidden")){
			 $("#divCollapsedNav").attr("style","display:''");
			 $("#main").attr("style","display:none");
			 window.parent.frmstOuter.cols = "30,*";
		}else{
			  $("#divCollapsedNav").attr("style","display:none");
			  $("#main").attr("style","display:''");
			  window.parent.frmstOuter.cols = "200,*";
		}
	}