
function openpopshow()
{	
	var sx=window.document.body.scrollLeft;
	var sy=window.document.body.scrollTop;
	var x= window.event.x+sx;
	var y=window.event.y+sy;
	
	var	xx=x;
	var	yy=y;
	var	w=document.body.offsetWidth;
	var	h=document.body.offsetHeight+document.body.scrollTop;
	var	width;
	var	height;
	var	left;
	var	top;


	if(w<=480){
		width=w;
		left=0;
	}else{
		width=580;
		if((w-xx)<=300)	left=w-600;
		else	if(xx<=270)	left=20;
				else		left=xx-250;
	}

	if(h<=250){
		height=h;
		top=0;
	}else{
		height=250;
		if((h-yy)<=250)	top=h-260;
		else			top=yy;
	}

	popshow.style.left=left;
	popshow.style.top=top;
	popshow.style.width=width;
	popshow.style.height=height;
	popshow.style.visibility="visible";
}

function close_window(){
	document.all.popshow.style.visibility="hidden";
	//document.all.gedit.innerHTML='';
}

var dragswitch=0;
var nsx;
var nsy;
var nstemp;
var whichIt = null;
var dragapproved=false;

function drag_dropns(name){
	temp=eval(name);
	temp.captureEvents(Event.MOUSEDOWN | Event.MOUSEUP);
	temp.onmousedown=gons;
	temp.onmousemove=dragns;
	temp.onmouseup=stopns;
}

function gons(e){
	temp.captureEvents(Event.MOUSEMOVE);
	nsx=e.x;
	nsy=e.y;
}
function dragns(e){
	if (dragswitch==1){
		temp.moveBy(e.x-nsx,e.y-nsy);
		return false;
	}
}

function stopns(){
	temp.releaseEvents(Event.MOUSEMOVE);
}

function drag_dropie(){
	if (dragapproved==true){
		temp.style.pixelLeft=tempx+event.clientX-iex;
		temp.style.pixelTop=tempy+event.clientY-iey;
		return false;
	}
}

function initializedragie(name){
	whichIt=name;
	temp=whichIt;
	iex=event.clientX;
	iey=event.clientY;
	tempx=temp.style.pixelLeft;
	tempy=temp.style.pixelTop;
	dragapproved=true;
	document.onmousemove=drag_dropie;
}

if (document.all){
	document.onmouseup=new Function("dragapproved=false");
}

//iframe
function PopDivHeight() {
	var iframe = document.getElementById("DetailFrame");
	if(iframe.readyState == "complete")	{
		var height = DetailFrame.document.body.scrollHeight;
		if(height<250)
			height = 250;
		iframe.style.height=height+"px";
	}
}

function OpenDiv(url) {
	openpopshow();
	document.all.gedit.innerHTML="<iframe id='DetailFrame' name='DetailFrame' src='"+url
		+"' width='100%' scrolling='no' frameborder='0'  onreadystatechange='PopDivHeight();'></iframe>";
}

function OpenDivText(html) {
	openpopshow();
	document.all.gedit.innerHTML=html;
}

function OpenDivShow() {
	openpopshow();
}