      $(document).ready(function() {
            $("#d").addClass("active_menu");
        });
       function validateForm2()
      {
    	  
    	   if( $('#images2').val() != "")
    	   {
    	       // file selected
    	       for(var i=0;i<$('#images2')[0].files.length;i++)
    	    	   {
    	    	    if($('#images2')[0].files[i].size>3000000)
    	    	    	{
    	     	         alert("Image Size Too Large");
    	    	     	 return false;
    	    	    	}
    	    	   }
    	   }
    	   else{
    	       // no file selected
    	       alert("Please Select Image");
    	       return false;
    	   }
      } 
      /* function validateForm1()
       {
    	   alert("inn");
    	   alert($('#images2').val());
     	   if( $('#images1').val() != "")
     	   {
     		  alert($('#images2').val());
     	       // file selected
     	       if($('#images1')[0].files[0].size>3000000)
     	    	   {
       	            alert("Please Select Image");
                    return false;
     	    	   }
     	   }
     	   else{
     	       // no file selected
     	       alert("Please Select Image");
     	       return false;
     	   }
       } */
