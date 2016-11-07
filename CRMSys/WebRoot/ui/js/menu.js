$(document).ready(function(){
	var $menuTitlediv=$("div[id^='menuTitle']");
	
	$menuTitlediv.each(function(index,data){
		var indexI=index+1;
		//alert("indexI   "+indexI);
		$(this).click(function(){
			if ($("#menu"+indexI).is(":hidden")) {
				$("#menuArrow" + indexI).attr("style", "background:url(../../../ui/images/down.gif) no-repeat center;float:left;");
			}else{
			    $("#menuArrow" + indexI).attr("style", "background:url(../../../ui/images/up.gif) no-repeat center;float:left;");
			}
			$("#menu"+indexI).slideToggle(200);
		})
	});
});
