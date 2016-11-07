///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var popUpWin=0;
/*
 * onClick="OpenWin('${pageContext.request.contextPath}/sys/dictionary/batchEditDetails.jsp','',500,300)">
 */
function OpenWin(URLStr,name,width,height)
{
  if(popUpWin)
  {
    if(!popUpWin.closed) popUpWin.close();
  }
  if(width=="" || width==null || width==undefined)
	  width = 750;
  if(height=="" || height==null || height==undefined)
	  height = 450;
  var left = (screen.width/2) - width/2;
  var top = (screen.height/2) - height/2;
  popUpWin = window.open(URLStr, name, 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top);
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function OpenMsg(url,name,wid,hei){
	if(wid=="" || wid==null || wid==undefined)
		wid = 600;
	if(hei=="" || hei==null || hei==undefined)
		hei = 400;
	var value = showModalDialog(url,window,'dialogWidth='+wid+'px;dialogHeight='+hei+'px;center=yes;help=no;scroll=auto;status=no');
	return value;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function CloseWin(url) {
	var opener = window.opener;
	if(opener!=null || opener!=undefined) {
		if(url != null)
			window.opener.location = url;
		else
			window.opener.location.reload();
	self.close();
	}else{
            forward(url);
	}
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

