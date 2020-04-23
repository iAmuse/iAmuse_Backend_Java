$(function () {
      $("#submit").click(function () {
          //Make groups
          var names = []
          $('input:radio[class^=configImg]').each(function () {
              var rname = $(this).attr('name');
              if ($.inArray(rname, names) == -1) names.push(rname);
          });
          var radioError = false;
          $.each(names, function (i, name) {
              if ($('input[name="' + name + '"]:checked').length == 0) {
                  radioError = true;
              }
          });
         
          
          //check for error
          if(radioError) {
              alert('You need to select 1 image');
              return false;
          }
      });
      
      
      $("#thankyoufilesUploadFile").change(function (event){           
	  		var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
	  		var files = event.target.files; //FileList object 
	  		
	  		var output = document.getElementById("reslty");  
	  		//$( "#reslty" ).empty();
	  		document.getElementById("tyPic").style.display='none'; 	
	  		for(var i = 0; i< files.length; i++) {
	  			var file = files[i];    //Only pics
	              if (regex.test(file.name.toLowerCase())) {     
	              	if(!file.type.match('image')) 
	              		continue
	                  var picReader = new FileReader();                  
	                  picReader.addEventListener("load",function(event){                      
	                      var picFile = event.target;
	                      //var div = document.createElement("div");
	                     // div.className = "img-prev-container";
		                    // div.innerHTML = "<img class='event-img-bg' src='" +  picFile.result + "'/>";
	                      //output.insertBefore(div,null);   
	                      $('#thankyouPic').attr("src",picFile.result);
	                      $('#thankyoudiv').css("display",'block');
	                  });
	                   //Read the image
	                  picReader.readAsDataURL(file);
	              }else{
	                alert(file.name + " is not a valid image file.");
	                        result.html("");
	                        return false; 
	              }
	  		}//for end           
          
        }); 
      
      
      $("#waterMarkImageUploadFile").change(function (event){debugger; 
    	  var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
	  		var files = event.target.files; //FileList object 
	  		
	  		var output = document.getElementById("reslwm");  
	  		//$( "#reslwm" ).empty();
	  		document.getElementById("wmPic").style.display='none'; 	
	  		for(var i = 0; i< files.length; i++) {
	  			var file = files[i];    //Only pics
	              if (regex.test(file.name.toLowerCase())) {     
	              	if(!file.type.match('image')) 
	              		continue
	                  var picReader = new FileReader();                  
	                  picReader.addEventListener("load",function(event){                      
	                      var picFile = event.target;
	                      //var div = document.createElement("div");
	                    //  div.className = "img-prev-container";
		                    // div.innerHTML = "<img class='event-img-bg' src='" +  picFile.result + "'/>";
	                      //$('.event-img-b');
	                      $('#waterEvent').attr("src",picFile.result);
	                      $('#waterdiv').css("display",'block');
	                     //output.insertBefore(div,null);   
	                      
	                  });
	                   //Read the image
	                  picReader.readAsDataURL(file);
	              }else{
	                alert(file.name + " is not a valid image file.");
	                        result.html("");
	                        return false; 
	              }
	  		}//for end           
        
      });
      $("#cameraTVScreenSaverUploadFile").change(function (event){ 
    	  var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
	  		var files = event.target.files; //FileList object 
	  		
	  		var output = document.getElementById("reslctv");  
	  		//$( "#reslctv" ).empty();
	  		document.getElementById("ctvPic").style.display='none'; 	
	  		for(var i = 0; i< files.length; i++) {
	  			var file = files[i];    //Only pics
	              if (regex.test(file.name.toLowerCase())) {     
	              	if(!file.type.match('image')) 
	              		continue
	                  var picReader = new FileReader();                  
	                  picReader.addEventListener("load",function(event){                      
	                      var picFile = event.target;
	                     // var div = document.createElement("div");
	                     // div.className = "img-prev-container";
		                    // div.innerHTML = "<img class='event-img-bg' src='" +  picFile.result + "'/>";
	                     // output.insertBefore(div,null);   
	                      $('#cameraEvent').attr("src",picFile.result);
	                      $('#cameradiv').css("display",'block');
	                      
	                  });
	                   //Read the image
	                  picReader.readAsDataURL(file);
	              }else{
	                alert(file.name + " is not a valid image file.");
	                        result.html("");
	                        return false; 
	              }
	  		}//for end           
        
      });
      
      $("#lookAtTouchScreenUploadFile").change(function (event){ 
    	  var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
	  		var files = event.target.files; //FileList object 
	  		
	  		var output = document.getElementById("resllats");  
	  		//$( "#resllats" ).empty();
	  		document.getElementById("latsPic").style.display='none'; 	
	  		for(var i = 0; i< files.length; i++) {
	  			var file = files[i];    //Only pics
	              if (regex.test(file.name.toLowerCase())) {     
	              	if(!file.type.match('image')) 
	              		continue
	                  var picReader = new FileReader();                  
	                  picReader.addEventListener("load",function(event){                      
	                      var picFile = event.target;
	                      //var div = document.createElement("div");
	                      //div.className = "img-prev-container";
		                    // div.innerHTML = "<img class='event-img-bg' src='" +  picFile.result + "'/>";
	                     // output.insertBefore(div,null);   
	                      $('#touchEvent').attr("src",picFile.result);
	                      $('#touchdiv').css("display",'block');
	                  });
	                   //Read the image
	                  picReader.readAsDataURL(file);
	              }else{
	                alert(file.name + " is not a valid image file.");
	                        result.html("");
	                        return false; 
	              }
	  		}//for end           
        
    	}); 

      
});