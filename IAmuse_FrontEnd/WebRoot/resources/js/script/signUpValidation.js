	
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
	var filter= /^([a-zA-Z0-9])+([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.trim() !=null){
		 if(!filter.test(email)){
			 document.getElementById("emailIdSpan").innerHTML="*Invalid Email Id";
		       return false;
		      }
	}
	//password
	var password=document.getElementById("password").value;
	filter= /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%&*_.? ])[A-Za-z\d\!@#$%&*_.?]{8,}$/;
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