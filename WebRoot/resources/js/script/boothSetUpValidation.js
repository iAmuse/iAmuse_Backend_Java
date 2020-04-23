function formValidation(){
	   document.getElementById("fovTopSpan").innerHTML="";
	   document.getElementById("fovLeftSpan").innerHTML="";
	   document.getElementById("fovBottomSpan").innerHTML="";
	   document.getElementById("fovRightSpan").innerHTML="";
	   document.getElementById("greenScreenWidthSpan").innerHTML="";
	   document.getElementById("greenScreenDistanceSpan").innerHTML="";
	   document.getElementById("greenScreenHeightSpan").innerHTML="";
	   document.getElementById("greenScreenCountdownDelaySpan").innerHTML="";
	   document.getElementById("otherIntractionTimoutSpan").innerHTML="";
	   document.getElementById("otherCountdownDelaySpan").innerHTML="";
	   $(document).ready(function() {
           $("#e").addClass("active_menu");
       });
	//fovTop
	var Top=document.getElementById("fovTop").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(Top.trim() !=null){
		 if(!filter.test(Top)){
			   document.getElementById("fovTopSpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
	//fovLeft
	var Left=document.getElementById("fovLeft").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(Left.trim() !=null){
		 if(!filter.test(Left)){
			   document.getElementById("fovLeftSpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
	//fovBottom
	var Bottom=document.getElementById("fovBottom").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(Bottom.trim() !=null){
		 if(!filter.test(Bottom)){
			   document.getElementById("fovBottomSpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
	//fovRightSpan
	var Right=document.getElementById("fovRight").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(Right.trim() !=null){
		 if(!filter.test(Right)){
			   document.getElementById("fovRightSpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
	//greenScreenWidthSpan
	var Width=document.getElementById("greenScreenWidth").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(Width.trim() !=null){
		 if(!filter.test(Width)){
			   document.getElementById("greenScreenWidthSpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
	//greenScreenDistanceSpan
	var Distance=document.getElementById("greenScreenDistance").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(Distance.trim() !=null){
		 if(!filter.test(Distance)){
			   document.getElementById("greenScreenDistanceSpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
	//greenScreenHeightSpan
	var Height=document.getElementById("greenScreenHeight").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(Height.trim() !=null){
		 if(!filter.test(Height)){
			   document.getElementById("greenScreenHeightSpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
	//greenScreenCountdownDelaySpan
	var CountdownDelay=document.getElementById("greenScreenCountdownDelay").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(CountdownDelay.trim() !=null){
		 if(!filter.test(CountdownDelay)){
			   document.getElementById("greenScreenCountdownDelaySpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
	//otherIntractionTimoutSpan
	var Timout=document.getElementById("otherIntractionTimout").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(Timout.trim() !=null){
		 if(!filter.test(Timout)){
			   document.getElementById("otherIntractionTimoutSpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
	//otherCountdownDelaySpan
	var Delay=document.getElementById("otherCountdownDelay").value;
	var filter     = /^\d{1,4}(\.\d{0,3})?$/;
	if(Delay.trim() !=null){
		 if(!filter.test(Delay)){
			   document.getElementById("otherCountdownDelaySpan").innerHTML="*Invalid Value";
		       return false;
		      }
	}
}
