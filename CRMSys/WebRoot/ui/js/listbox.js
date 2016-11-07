// JavaScript Document
function moveLeft() {
	var left = $("lslt");
	var right = $("rslt");
	var ids = new Array();
	for(var i=0;i<right.options.length;i++) {
		var temp = right.options[i];
		if(temp.selected) {
			ids[ids.length] = temp.index;
			var op = document.createElement("option");
			op.setAttribute("value",temp.value);
			op.appendChild(document.createTextNode(temp.text));
			left.appendChild(op);
		}
	}
	for(var i=0;i<ids.length;i++) {
		var temp = right.options[ids[i]-i];	//if moved,option's length changed
		right.removeChild(temp);
	}
	if(right.options[0]!=null)
		right.options[0].selected = true;
}
function moveRight() {
	var left = $("lslt");
	var right = $("rslt");
	var ids = new Array();
	for(var i=0;i<left.options.length;i++) {
		var temp = left.options[i];
		if(temp.selected) {
			ids[ids.length] = temp.index;
			var op = document.createElement("option");
			op.setAttribute("value",temp.value);
			op.appendChild(document.createTextNode(temp.text));
			right.appendChild(op);
		}
	}
	for(var i=0;i<ids.length;i++) {
		var temp = left.options[ids[i]-i];	//if moved,option's length changed
		left.removeChild(temp);
	}
	if(left.options[0]!=null)
		left.options[0].selected = true;
}
function moveAllLeft() {
	var left = $("lslt");
	var right = $("rslt");
	for(var i=0;i<right.options.length;i++) {
		var temp = right.options[i];
		var op = document.createElement("option");
		op.setAttribute("value",temp.value);
		op.appendChild(document.createTextNode(temp.text));
		left.appendChild(op);
	}
	right.length = 0;
}
function moveAllRight() {
	var left = $("lslt");
	var right = $("rslt");
	for(var i=0;i<left.options.length;i++) {
		var temp = left.options[i];
		var op = document.createElement("option");
		op.setAttribute("value",temp.value);
		op.appendChild(document.createTextNode(temp.text));
		right.appendChild(op);
	}
	left.length = 0;
}
function dumpSelect(flag) {
	var slt = null;
	if(flag==null || flag=="left") {
		slt = $("lslt");
	}else{
		slt = $("rslt");
	}
	var values = "";
	for(var i=0;i<slt.options.length;i++) {
		var temp = slt.options[i];
		if(i==0)
			values = temp.value;
		else
			values = values+","+temp.value;
	}
	return values;
}
function moveSel(moveOne) {   
	var right = $("rslt");
    if(right.selectedIndex < 0)   
  		return;   
    
    if(moveOne < 0) {   
  		if(right.selectedIndex == 0)   
  			return;   
    }   
    else {   
  		if(right.selectedIndex == right.options.length-1)   
  			return;   
    }   
        
    var opt = right.options[right.selectedIndex];   
  	var otheropt = right.options[right.selectedIndex+moveOne];   
    var text = opt.text;   
    var value = opt.value;   
    opt.text = otheropt.text;   
    opt.value = otheropt.value;   
    otheropt.text=text;   
    otheropt.value = value;   
    right.selectedIndex+= moveOne;   
}   