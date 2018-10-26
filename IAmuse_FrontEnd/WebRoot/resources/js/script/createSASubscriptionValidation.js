
function formValidationSASubscription(){
	
	   document.getElementById("subNameSpan").innerHTML="";
	   document.getElementById("subPriceSpan").innerHTML="";
	   document.getElementById("subValidaityDayPeriodSpan").innerHTML="";
	   ///
	  
	      $(document).ready(function() {
	            $("#b").addClass("active_menu");
	        });
		
	 
	   ///
	//subNameSpan
	   var subNameValue=document.getElementById("subName").value;
	   var filter     = /^([a-zA-Z])+([a-zA-Z0-9\W])+$/;
		if(subNameValue.trim() !=null){
			if(!filter.test(subNameValue)){
				 document.getElementById("subNameSpan").innerHTML="*Subscription Name is required";
			       return false;
		}
		}
	//subPriceSpan
	var subPriceValue=document.getElementById("subPrice").value;
	var filter     = /^[1-9]\d*(?:\.\d{0,2})?$/;
	if(subPriceValue.trim() !=null){
		 if(!filter.test(subPriceValue)){
			   document.getElementById("subPriceSpan").innerHTML="*check subscription price";
		       return false;
		      }
	}
	//subValidaityDayPeriodSpan
	var subtimePeriodValue=document.getElementById("subValidaityDayPeriod").value;
	var filter     = /^[1-9]\d*(?:\.\d{0,2})?$/;
	if(subtimePeriodValue.trim() !=null){
		 if(!filter.test(subtimePeriodValue)){
			   document.getElementById("subValidaityDayPeriodSpan").innerHTML="*check subscriptions time period";
		       return false;
		      }
	}
}
