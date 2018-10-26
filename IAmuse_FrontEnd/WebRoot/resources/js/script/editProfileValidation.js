function formValidation(){
	   document.getElementById("usernameSpan").innerHTML="";
	   document.getElementById("contactNumberSpan").innerHTML="";

	//name
	var name=document.getElementById("username").value;
	var filter     = /^([a-zA-Z])+([a-zA-Z0-9\W])+$/;
	if(name.trim() !=null){
		 if(!filter.test(name)){
			   document.getElementById("usernameSpan").innerHTML="*check username";
		       return false;
		      }
	}
	//contact
	var contact=document.getElementById("contactNumber").value;
	if(contact.length<10)
		{
		 document.getElementById("contactNumberSpan").innerHTML="*Invalid phone number";
		 return false;
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
}
