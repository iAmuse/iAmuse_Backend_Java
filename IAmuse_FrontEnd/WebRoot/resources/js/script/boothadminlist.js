$(document).ready(function(){
	$.ajax({
    type:'GET',
    url:'subscriptionList',
 /*    data:{'id':id}, */
  async: false,
dataType: 'json',
    success: function (data) {
          var unitId = $('.subscriptionId');
        for (var i = 0; i < data.length; i++) {
        	if(data[i].subName != 'Test Drive'){
        		unitId.append('<option value=' + data[i].subName + '>' + data[i].subName + '</option>');
        	}
        }  
    }
});
});

function getSubscriptionPlan(userId){
	 var selectedPlan = $("#subscriptionId"+userId+" option:selected").val();
	 var dataString = 'userId='+userId+'&planId='+selectedPlan
	 if (confirm("Are you sure want to upgrad this plan?")) {
		 $.ajax({
	    	    type:'POST',
	    	    url:'updateSubscription',
	    	     data:dataString, 
	    	  async: false,
	    	dataType: 'json',
	    	    success: function (data) {
	    	         if(data){
	    	        	 alert("Plan upgraded successfully.");
	    	        	 window.location.href='getBoothAdminList';
	    	         }else{
	    	        	 alert("something went wrong");
	    	         }
	    	    }
	    	});
		  }
     //alert("You have selected the plan - " + selectedPlan);
    
}