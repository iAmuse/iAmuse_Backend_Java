
 function remove2(re){
	    $('#abc'+re).remove();
	  } 
$(document).ready(function() {
 $("#b").addClass("active_menu");
 $("#images2").change(function (event){
	 document.getElementById("gif").style.display = "";
//var regex = /^(.jpg|.jpeg|.gif|.png|.bmp)$/;
var files = event.target.files; //FileList object            
var output = document.getElementById("result");
$("#result").empty();
/*for(var i = 0; i< files.length; i++){
    var file = files[i]; //Only pics
    if (regex.test(file.name.toLowerCase())) {     
     if(!file.type.match('image')) 
    	 
continue
var picReader = new FileReader();                  
     picReader.addEventListener("load",function(event){                      
    var picFile = event.target;             
    
var div = document.createElement("div");                       
div.className = "grid-img event-bg";
div.id="abc"+i;
div.innerHTML = "<img class='img-thumbnail' src='" + picFile.result + "'" +
"title='" + picFile.name + "'/><a href='javascript:void(0);' id='"+i+++"' onclick ='remove2(this.id);' class='remove_pict' >Delete</a>";
output.insertBefore(div,null);
     });
//Read the image
picReader.readAsDataURL(file);
}else{
alert(file.name + " is not a valid image file.");
result.html("");
return false; 
}
}*///for end  
});  
           
            
            $("#waterMarkImageUploadFile").change(function (event){
              
            	
              var regex = /^([a-zA-Z0-9\s_\\.\-:\,])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
              var files = event.target.files; //FileList object
              if(files.length == 0){
                	document.getElementById("wmPic").style.display = "block";
                }
              var output = document.getElementById("resl"); 
              //$( "#resl" ).empty();
              for(var i = 0; i< files.length; i++)
              {
                  var file = files[i];                  
                  //Only pics
                if (regex.test(file.name.toLowerCase())) {     
                 if(!file.type.match('image')) 
                  continue
                    var picReader = new FileReader();                  
                    picReader.addEventListener("load",function(event){                      
                        var picFile = event.target; 
                        //var div = document.createElement("div"); 
                       // div.className = "grid-img event-bg-box";
                       // div.innerHTML = "<h2>Background Overlay</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + picFile.result + "'/> </div>";
                        //div.innerHTML += "<input type='file' name='waterMarkImage' id='waterMarkImageUploadFile' class='demohide'  accept='.jpg,.png,.gif,.bmp' onchange='javascript:updateWaterMarkImageList()'  />"
                       // div.innerHTML += "<div class='event-img-text'><p>Upload transparent PNG to overlay on top of every Background for this Events (IE logo, picture frame, etc.) Position graphics relative to entire Background (IE logo in bottom right corner on 1920x1080 image.)</p> <div class='clearfix'></div></div>";
                        $('#overimg').attr("src",picFile.result);
	                      $('#overdiv').css("display",'block'); 
                      //  output.insertBefore(div,null);   
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
            
          
            $("#thankyoufilesUploadFile").change(function (event){
                
                var regex = /^([a-zA-Z0-9\s_\\.\-:\,])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
                var files = event.target.files; //FileList object
                if(files.length == 0){
                	document.getElementById("tyPic").style.display = "block";
                }
                var output = document.getElementById("reslty");  
               // $( "#reslty" ).empty();
                
                for(var i = 0; i< files.length; i++)
                {
                    var file = files[i];                  
                    //Only pics
                  if (regex.test(file.name.toLowerCase())) {     
                   if(!file.type.match('image')) 
                    continue
                      var picReader = new FileReader();                  
                      picReader.addEventListener("load",function(event){                      
                          var picFile = event.target;     
                          
                          //var div = document.createElement("div"); 
                         // div.className = "grid-img event-bg-box";
                          //div.innerHTML = "<h2>End of Session</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + picFile.result + "'/> </div>";
                         // div.innerHTML += "<input type='file' name='thankyoufiles' id='thankyoufilesUploadFile' class='demohide'  accept='.jpg,.png,.gif,.bmp' onchange='javascript:updateThankYouList()'  />"
                        //  div.innerHTML += "<div class='event-img-text'><p>This is the Thank You screen displayed at the conclusion of a successful Booth session.</p> <div class='clearfix'></div></div>";
                          $('#tyimg').attr("src",picFile.result);
	                      $('#tydiv').css("display",'block'); 
                         // output.insertBefore(div,null);   
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
                
                var regex = /^([a-zA-Z0-9\s_\\.\-:\,])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
                var files = event.target.files; //FileList object
                if(files.length == 0){
                	//document.getElementById("ctvsPic").style.display = "block";
                	if(document.getElementById("ctvsPic") !=null){
                		document.getElementById("ctvsPic").style.display = "block";
                	}else{
                		document.getElementById("ctvPic").style.display = "block";
                	}
                }
                var output = document.getElementById("sver");  
                //$( "#sver" ).empty();
               
                for(var i = 0; i< files.length; i++)
                {
                    var file = files[i];                  
                    //Only pics
                  if (regex.test(file.name.toLowerCase())) {     
                   if(!file.type.match('image')) 
                    continue
                      var picReader = new FileReader();                  
                      picReader.addEventListener("load",function(event){                      
                          var picFile = event.target;     
                          var div = document.createElement("div"); 
                         // div.className = "grid-img event-bg-box";
                         // div.innerHTML = "<h2>Screensaver</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + picFile.result + "'/> </div>";
                         // div.innerHTML += "<input type='file' name='cameraTVScreenSaver' id='cameraTVScreenSaverUploadFile' class='demohide'  accept='.jpg,.png,.gif,.bmp' onchange='javascript:updateCameraTvScreenSaverList()'  />"
                          //div.innerHTML += "<div class='event-img-text'><p>This image is displayed on the main TV (Camera device) while the Booth is not in an active session.</p> <div class='clearfix'></div></div>";
                          $('#saverimg').attr("src",picFile.result);
	                      $('#saverdiv').css("display",'block');                  
                        
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
            
$("#lookAtTouchScreenUploadFile").change(function (event){
    
    var regex = /^([a-zA-Z0-9\s_\\.\-:\,])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
    var files = event.target.files; //FileList object
    if(files.length == 0){
    	if(document.getElementById("atslPic") !=null){
    		document.getElementById("atslPic").style.display = "block";
    	}else{
    		document.getElementById("latsPic").style.display = "block";
    	}
      //	document.getElementById("atslPic").style.display = "block";
      //	alert("kl");
    	//document.getElementById("latsPic").style.display = "block";
      }
    var output = document.getElementById("reslctvs"); 
   // $( "#reslctvs" ).empty();
    for(var i = 0; i< files.length; i++)
    {
        var file = files[i];                  
        //Only pics
      if (regex.test(file.name.toLowerCase())) {     
       if(!file.type.match('image')) 
        continue
          var picReader = new FileReader();                  
          picReader.addEventListener("load",function(event){                      
              var picFile = event.target;     
              
            //  var div = document.createElement("div"); 
              //div.className = "grid-img event-bg-box";
              //div.innerHTML = "<h2>Photos Complete</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + picFile.result + "'/> </div>";
              //div.innerHTML += "<input type='file' name='lookAtTouchScreen' id='lookAtTouchScreenUploadFile' class='demohide'  accept='.jpg,.png,.gif,.bmp' onchange='javascript:updatelookAtTouchScreenList()'  />"
              //div.innerHTML += "<div class='event-img-text'><p>This image is displayed after Photos are taken, prompting Guests to look at the Touchscreen and choose their Photos/enter email.</p> <div class='clearfix'></div></div>";             
              $('#completeimg').attr("src",picFile.result);
              $('#completediv').css("display",'block');    
             // output.insertBefore(div,null);   
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
       function validateForm2()
      {
        if( $('#images2').val() != "")
        {
            // file selected
            for(var i=0;i<$('#images2')[0].files.length;i++)
             {
              if($('#images2')[0].files[i].size>10000000)
               {
                    alert("Image Size Too Large");
                 return false;
               }
             }
        }
        
      } 
       function validateForm1()
       {
         if( $('#images1').val() != "")
         {
             // file selected
             if($('#images1')[0].files[0].size>3000000)
              {
                    alert("Please Select Image");
                    return false;
              }
         }
        
       }
function updateWaterMarkImageList(){
  $('#selectedPreWaterMarkImage').val('0');
  $('.WaterMarkImgaeClass').prop('checked', false);  
  document.getElementById('wmPic').style.display='none';
 }
function updateThankYouList(){
	 $('#selectedPreThankYouScreen').val('0');
	 $('.ThankYou').prop('checked', false);
	 document.getElementById('tyPic').style.display='none';
}
function updateCameraTvScreenSaverList(){
	 $('#selectedPreCameraTVScreenSaver').val('0');
	 $('.CameraTVScreenSaverClass').prop('checked', false);
	 document.getElementById('ctvsPic').style.display='none';
}
function updatelookAtTouchScreenList(){
	 $('#selectedPreLookAtTouchScreen').val('0');
	 $('.LookAtTouch').prop('checked', false);
	 document.getElementById('atslPic').style.display='none';
}

