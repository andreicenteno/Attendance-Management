$(document).ready(function(){
	cookieValue = getCookie('notification_message');
	var minutes_display = 8000;
	
/*notification div tab*/	
	if(cookieValue == "div_success"){
		$('#div_success').show();
		   setTimeout(function() { 
		       $('#div_success').fadeOut(); 
		   }, minutes_display);
		setCookie('notification_message', '', -1);
	}
	else if(cookieValue == "div_error"){
		$('#div_error').show();
		   setTimeout(function() { 
		       $('#div_error').fadeOut(); 
		   }, minutes_display);
		setCookie('notification_message', '', -1);
	}
	
	else if(cookieValue == "div_success_fixed"){
		$('#div_success_fixed').show();
		   /*setTimeout(function() { 
		       $('#div_success_fixed').fadeOut(); 
		   });*/
		setCookie('notification_message', '', -1);
	}
	else if(cookieValue == "div_error_fixed"){
		$('#div_error_fixed').show();
		  /* setTimeout(function() { 
		       $('#div_error_fixed').fadeOut(); 
		   });*/
		setCookie('notification_message', '', -1);
	}
	
	else if(cookieValue == "div_success_import"){
		$('#div_success_import').show();
		   /*setTimeout(function() { 
		       $('#div_success_fixed').fadeOut(); 
		   });*/
		setCookie('notification_message', '', -1);
	}
	
	
	//-- set and erase the cookie;
	function setCookie(name, value, expires, path, domain, secure){
		cookieStr = name + "=" + escape(value) + "; ";
		
		if(expires){
			expires = setExpiration(expires);
			cookieStr += "expires=" + expires + "; ";
		}
		if(path){
			cookieStr += "path=" + path + "; ";
		}
		if(domain){
			cookieStr += "domain=" + domain + "; ";
		}
		if(secure){
			cookieStr += "secure; ";
		}
		
		document.cookie = cookieStr;
	}
	function setExpiration(cookieLife){
	    var today = new Date();
	    var expr = new Date(today.getTime() + cookieLife * 24 * 60 * 60 * 1000);
	    return  expr.toGMTString();
	}
	
	//setCookie('expires', '', -1);
	function getCookie(w){
		cName = "";
		pCOOKIES = new Array();
		pCOOKIES = document.cookie.split('; ');
		for(bb = 0; bb < pCOOKIES.length; bb++){
			NmeVal  = new Array();
			NmeVal  = pCOOKIES[bb].split('=');
			if(NmeVal[0] == w){
				cName = unescape(NmeVal[1]);
			}
		}
		return cName;
	}
	
	
});