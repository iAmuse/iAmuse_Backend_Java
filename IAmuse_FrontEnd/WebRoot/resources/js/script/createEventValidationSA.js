  
function formValidationSACreateEvent(){
	
	   document.getElementById("eventStartSpan").innerHTML="";
	   document.getElementById("eventNameSpan").innerHTML="";
	   document.getElementById("sponsorNameSpan").innerHTML="";
	   document.getElementById("createrNameSpan").innerHTML="";

	   $(document).ready(function() {
		   $("#d").addClass("active_menu");
		   });
	   
	//eventStartSpan
	   var date=document.getElementById("eventStart").value;
	 
		if(date.trim() ==""){
				 document.getElementById("eventStartSpan").innerHTML="*Event date is required";
			       return false;
		}
	//eventNameSpan
	var eventName=document.getElementById("eventName").value;
	var filter     = /^([a-zA-Z])+([a-zA-Z0-9\W])+$/;
	if(eventName.trim() !=null){
		 if(!filter.test(eventName)){
			   document.getElementById("eventNameSpan").innerHTML="*check event name";
		       return false;
		      }
	}
	//sponsorNameSpan
	var sponsorName=document.getElementById("sponsorName").value;
	var filter     = /^([a-zA-Z])+([a-zA-Z0-9\W])+$/;
	if(sponsorName.trim() !=null){
		 if(!filter.test(sponsorName)){
			   document.getElementById("sponsorNameSpan").innerHTML="*check sponsor name";
		       return false;
		      }
	}
	//createrNameSpan
	var createrName=document.getElementById("createrName").value;
	var filter     = /^([a-zA-Z])+([a-zA-Z0-9\W])+$/;
	if(createrName.trim() !=null){
		 if(!filter.test(createrName)){
			   document.getElementById("createrNameSpan").innerHTML="*check creater name";
		       return false;
		      }
	}
}
