	
function formValidation(){
	
	   document.getElementById("usernameSpan").innerHTML="";
	   document.getElementById("emailIdSpan").innerHTML="";
	   document.getElementById("passwordSpan").innerHTML="";

	//name
	var name=document.getElementById("username").value;
	if(name.trim() ==null || name.trim() ==""){
			   document.getElementById("usernameSpan").innerHTML="*please enter username";
		       return false;
	}
	
	//email
	var email=document.getElementById("emailId").value;
	var filter= /^([a-zA-Z])+([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.trim() !=null){
		 if(!filter.test(email)){
			 document.getElementById("emailIdSpan").innerHTML="*Invalid Email Id";
		       return false;
		      }
	}
	//password
	var password=document.getElementById("password").value;
	//filter= /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%&*_.? ])[A-Za-z\d\!@#$%&*_.?]{8,}$/;
	filter=^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$;
	if(password.length<8)
	 {    	  
	  document.getElementById("passwordSpan").innerHTML="*password should have atleast 8 character";
	  return false;
	 }
	if(password.trim() !=null){
		
		 if(!filter.test(password)){
			   document.getElementById("passwordSpan").innerHTML="*password should be minimum of 8 characters with at 1 alphabet & 1 special character & 1 digit";
		       return false;
		      }
	}
	//contact
	var contact=document.getElementById("contactNumber").value;
	if(contact.length!=0)
		{
	  if(contact.length<10)
		{
		 document.getElementById("contactNumberSpan").innerHTML="*Invalid phone number";
		 return false;
		}
		}
	var ch;
	   for(var i=0;i<contact.length;i++)
	   	  { 
	   	    ch=contact.charAt(i);
	   	   if ( (ch<'0') || (ch > '9') )
	   		   {
	   		document.getElementById("contactNumberSpan").innerHTML="*Invalid phone number";	   		    
	   		return false;
	   		   }
	   	  }
	 //PIN number
	   var PinNumber=document.getElementById("PinNumber").value;
	   if(PinNumber.trim() ==null || PinNumber.trim() ==""){
		   document.getElementById("pinNumberSpan").innerHTML="*please enter the 4-digit PIN number";
	       return false;
}
	   if(pinNumber!="")
		   {
		if(PinNumber.length<4)
			{
			 document.getElementById("pinNumberSpan").innerHTML="*Invalid PIN number";
			 return false;
			}
		var PinVal;
		   for(var i=0;i<PinNumber.length;i++)
		   	  { 
		   	    PinVal=PinNumber.charAt(i);
		   	   if ( (ch<'0') || (ch > '9') )
		   		   {
		   		document.getElementById("pinNumberSpan").innerHTML="*Invalid PIN number";	   		    
		   		return false;
		   		   }
		   	  }
		   }
	   
	   
}
$( document ).ready(function() { 
	  $("#username").on('input',function(){
	        var add=$("#username").val();
	        var regex = /^[a-zA-Z0-9\_ ]*$/;
	         if(!regex.test(add)){
	                           var s = add.substr(0,(add.length -1));
	                           $("#username").val(s);
	                              }
	       });
});