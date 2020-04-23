$(document).ready(function() {
	 $("#b").addClass("active_menu");
	  $( "#eventStart" ).datepicker({ 
		  dateFormat: 'yy-mm-dd' ,
		  minDate:0
		  }).val();
       });

function formValidation(){
	   document.getElementById("eventStartSpan").innerHTML="";
	   document.getElementById("eventNameSpan").innerHTML="";
	   document.getElementById("sponsorNameSpan").innerHTML="";
	   document.getElementById("eventHostMailerIdSpan").innerHTML="";
	   document.getElementById("facebookSpan").innerHTML="";
	   document.getElementById("twitterSpan").innerHTML="";
	   document.getElementById("emailBodySpan").innerHTML="";
	   
	   ///
	 
	   
	   ///
	//eventStartSpan
	   var date=document.getElementById("eventStart").value;
		if(date.trim() ==""){
				 document.getElementById("eventStartSpan").innerHTML="*event date is required";
			       return false;
		}
		
	//event timezone
		var date=document.getElementById("timezone1").value;
		if(date ==0){
				 document.getElementById("eventStartSpan").innerHTML="*event timezone required";
			       return false;
		}
		
	//eventNameSpan
	var eventName=document.getElementById("eventName").value;
	var filter     = /^([a-zA-Z])+([a-zA-Z0-9\W])+$/;
	if(eventName.trim() ==null || eventName.trim() ==""){
		 document.getElementById("eventNameSpan").innerHTML="*Event name is required";
		 return false;
	}else{
		 if(!filter.test(eventName)){
			   document.getElementById("eventNameSpan").innerHTML="*Check event name";
		       return false;
		      }
	}
	
	//sponsorNameSpan
	var sponsorName=document.getElementById("sponsorName").value;
	var filter     = /^([a-zA-Z])+([a-zA-Z0-9\W])+$/;
	if(sponsorName.trim() ==null || sponsorName.trim() ==""){
			   document.getElementById("sponsorNameSpan").innerHTML="*Sponsor name is required";
		       return false;
		      
	}else{
		 if(!filter.test(sponsorName)){
			   document.getElementById("sponsorNameSpan").innerHTML="*Check sponsor name";
		       return false;
		      }
	}
	//eventBodtySpan
	var eventBody=document.getElementById("emailBody").value;
	if(eventBody.trim() ==null || eventBody.trim() ==""){
		  document.getElementById("emailBodySpan").innerHTML="*Email Body is required";
	       return false;
	}
	//eventHostMailerIdSpan
	var eventHostMailerId=document.getElementById("eventHostMailerId").value;
	var filter     = /^([a-zA-Z])+([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(eventHostMailerId.trim() ==null || eventHostMailerId.trim() ==""){
			   document.getElementById("eventHostMailerIdSpan").innerHTML="*Hosts Email Address is required";
		       return false;
	}
	else{
		 if(!filter.test(eventHostMailerId)){
			   document.getElementById("eventHostMailerIdSpan").innerHTML="*Check email address";
		       return false;
		      }
	}
	
	var fb=document.getElementById("facebook").value;
	var tw=document.getElementById("twitter").value;
	
	if(fb.trim() !=""){
		var filter1=/^(https?:\/\/)?((w{3}\.)?)facebook.com\/.*/;
		if (!filter1.test(fb)){
			document.getElementById("facebookSpan").innerHTML="*Check Facebook URL";
			return false;
		}
	}
	if(tw.trim() !=""){
		var filter2=/^(https?:\/\/)?((w{3}\.)?)twitter.com\/(#!\/)?[a-zA-Z0-9_]+$/;
		if (!filter2.test(tw)){
			document.getElementById("twitterSpan").innerHTML="*Check Twitter URL";
			return false;
		}
	}
	
}
