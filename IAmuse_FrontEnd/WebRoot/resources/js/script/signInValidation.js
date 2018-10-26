function formValidation(){
	   document.getElementById("emailIdSpan").innerHTML="";
	   document.getElementById("passwordSpan").innerHTML="";
	//email
	var email=document.getElementById("emailId").value;
	if(email.trim() ==""){
			 document.getElementById("emailIdSpan").innerHTML="*Email Id Required";
		       return false;
	}
	//password
	var password=document.getElementById("password").value;
	if(password.trim() ==""){
			   document.getElementById("passwordSpan").innerHTML="*Password Required";
		       return false;
		      
	}
}
