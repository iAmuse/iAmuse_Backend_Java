<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/eventBackground.js">  </script>
<script>
$(document).ready(function() {
    function disableBack() { 
    	window.history.forward() 
    	}
    window.onload = disableBack();
    window.onpageshow = function(evt) { 
    	if (evt.persisted) disableBack() ;
    	}
});
	/* var selectedCheckBox = new Array();
	function handleClick(id){
		selectedCheckBox.push(id);
		$('#selectedPreImage').val(selectedCheckBox);
	}	 */		 
	function handleClickWaterMarkImage(id){
		$('#selectedPreWaterMarkImage').val(id);
		$('#waterMarkImageUploadFile').val("");
	}
	function handleClickCameraTVScreenSaver(id){
		$('#selectedPreCameraTVScreenSaver').val(id);
		$('#cameraTVScreenSaverUploadFile').val("");
	}	 
	function handleClickLookAtTouchScreen(id){
		$('#selectedPreLookAtTouchScreen').val(id);
		$('#lookAtTouchScreenUploadFile').val("");
	} 
	function handleClickThankYouScreen(id){
		$('#selectedPreThankYouScreen').val(id);
		$('#thankyoufilesUploadFile').val("");				 
	}	 
			/*  function updateThankYouList(){
				 $('#selectedPreThankYouScreen').val('0');
				 $('.ThankYou').prop('checked', false);
			 } */
			 
			 /* function updateWaterMarkImageList(){
				 $('#selectedPreWaterMarkImage').val('0');
				 $('.WaterMarkImgaeClass').prop('checked', false);
			 } */
			 
			 /* function updatelookAtTouchScreenList(){
				 $('#selectedPreLookAtTouchScreen').val('0');
				 $('.LookAtTouch').prop('checked', false);
			 } */
			 
			 /* function updateCameraTvScreenSaverList(){
				 $('#selectedPreCameraTVScreenSaver').val('0');
				 $('.CameraTVScreenSaverClass').prop('checked', false);
			 } */
			 
</script>  
<script type="text/javascript">
	/* $(document).ready(function(){
		var subId= document.getElementById("subid").value;
		if(subId==1){
			$(".demohide").attr('disabled', 'disabled');
			$('.btn-green').attr('disabled', 'disabled');
		}
	});	 */
	
	function updateList() {
  		var input = document.getElementById('images2');
  		var output = document.getElementById('fileList');
  		output.innerHTML = '<ul>';
  		for (var i = 0; i < input.files.length; ++i) {
    		output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  		}
  		output.innerHTML += '</ul>';
	}
	
	function updateList1() {
  		var input = document.getElementById('images1');
  		var output = document.getElementById('fileList1');
  		output.innerHTML = '<ul>';
  		for (var i = 0; i < input.files.length; ++i) {
    		output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  		}
  		output.innerHTML += '</ul>';
	}
</script>
<script type="text/javascript">

	function ButtonClicked(){
		var subId= document.getElementById("subid").value;
		/* if(subId==1){
			alert("You need to upgrade your subscription to upload custom backgrounds.");
			return false;
		}else{ */
			var existingBackground=$("#selectedPreImage").val(); 	    
		    var uploadBackground = $('#images2').val();
		    if(existingBackground==0 && uploadBackground==""){
		    	alert("select atleast one background"); 
		      	return false;
		    }
	   		document.getElementById("formsubmitbutton").style.display = "none"; // to undisplay
	   		document.getElementById("gif").style.display = ""; // to display
		}
		
	//}
	var FirstLoading = true;
	function RestoreSubmitButton(){
   	if( FirstLoading ) {
      FirstLoading = false;
      return;
   	}
   document.getElementById("formsubmitbutton").style.display = ""; // to display
   document.getElementById("gif").style.display = "none"; // to undisplay
}
// To disable restoring submit button, disable or delete next line.
document.onfocus = RestoreSubmitButton;
</script>

<style>
	.black_overlay {
	  	display: none;
	  	position: fixed;
	  	top: 0%;
	  	left: 0%;
	  	width: 100%;
	  	height: 100%;
	  	background-color: black;
		z-index: 1001;
		-moz-opacity: 0.8;
		opacity: .80;
		filter: alpha(opacity=80);
		margin-top:-y;
		margin-left:-x;
	}
	.white_content {
		display: none;
	  	position: fixed;
	 	top: 5%;
	    left: 10%;
	    width: 80%;
	    height: 90%;
	 	padding: 16px;
	  	border: 16px solid #05a42e;
	  	background-color: white;
	  	z-index: 1002;
	  	overflow: auto;
		margin-top:-y;
		margin-left:-x;  
	}
	/*.right-pannel{height:auto !important;}*/
	.left-pannel{
  min-height:1152px !important;}
</style>
 <script type="text/javascript"> 
				
function open3() {
document.getElementById('light').style.display='block';
document.getElementById('fade').style.display='block';
document.getElementById('selectedPreImage').value='0';
$('.checkbox1').removeAttr('checked');

    	  }
    	  
function close1(){   
	   document.getElementById('light').style.display='none';
	   document.getElementById('fade').style.display='none';
	   var output = document.getElementById("result2");
	   $( "#result2" ).empty();
	   var index = 1;
	    var checkedValue = [];
	    var delValue = [];
	    var i=0;
	             $.each($("input[name='imgidss']"), function(){   
	            	 
	            	 if(this.checked){
	              checkedValue.push($(this).val());
	              delValue[i]=index;
	            if($('#selectedPreImage').val()==0)
	            	{
		              $('#selectedPreImage').val(index);
	            	}
	            else
	            	{
	              $('#selectedPreImage').val($('#selectedPreImage').val()+","+index);
	            	}
	            i++;
	            	 }
	              index++;
	             });
	            // alert("My favourite sports are: " + calert("My fa;
	   for(var i = 0; i< checkedValue.length; i++){
	    var div = document.createElement("div"); 
	        div.className = "grid-img event-bg";
	        div.id="abc"+delValue[i];
	        div.innerHTML = "<img class='img-thumbnail' src='" + checkedValue[i] + "'" +
	                  "title=''/><div style='float: right;cursor: pointer;right:10px;'><a href='javascript:void(0);' id='"+delValue[i]+"' onclick ='rem(this.id);' class='remove_pict' >X</a></div>";
	      
	         output.insertBefore(div,null);   
	        
	   }
	   
	 }
	 
	 function rem(re){
		 var ids=  $('#selectedPreImage').val();
		 $('#selectedPreImage').val("");
		 var splitIds=ids.split(",");
		 if(splitIds.length==1)
		 {
		 $('#selectedPreImage').val("0");
		 }
		 for(var i=0;i<splitIds.length;i++)
			 {
			  if(splitIds[i]!=re)
				{
				  $('#selectedPreImage').val($('#selectedPreImage').val()+","+splitIds[i]);
			    }
			 }
		 
		 var str=$('#selectedPreImage').val();
		
		 while(str.charAt(0) === ',')
			 {
			    str = str.substr(1);
			 }
		 
		 $('#selectedPreImage').val(str);
	      $("#abc"+re).remove();
	    }
</script>

    <script type="text/javascript"> 
				
    function openThankYouScreen() {
    
    document.getElementById('lightThankYouScreen').style.display='block';
    document.getElementById('fade').style.display='block';
    }
    	  
    function closeThankYouScreen(){	  
    document.getElementById('lightThankYouScreen').style.display='none';
    document.getElementById('fade').style.display='none';
    document.getElementById("tyPic").style.display='none'; 
    var output = document.getElementById("reslty");
    $( "#reslty" ).empty();
	   var checkedValue = [];
	            $.each($("input[name='imgidsty']:checked"), function(){            
	             checkedValue.push($(this).val());
	            });
	          /*   alert("My favourite sports are: " + checkedValue); */
	            if(checkedValue==''){
	            	var div = document.createElement("div");        
	                 div.className = "grid-img event-bg-box";
                    div.innerHTML = "<h2>End of Session</h2> <div class='event-img-bg'></div>";
                    div.innerHTML += "<div class='event-img-text'><p>This is the Thank You screen displayed at the conclusion of a successful Booth session.</p> <div class='clearfix'></div></div>";          
	                 
	        output.insertBefore(div,null);   
	       div.children[i].addEventListener("click", function(event){
	           div.parentNode.removeChild(div);
	        });
	            }else{
	  for(var i = 0; i< checkedValue.length; i++){
	   //alert(checkedValue[i]);
	   var div = document.createElement("div");        
	                 div.className = "grid-img event-bg-box";
                     div.innerHTML = "<h2>End of Session</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + checkedValue[i] + "'/> </div>";
                     div.innerHTML += "<div class='event-img-text'><p>This is the Thank You screen displayed at the conclusion of a successful Booth session.</p> <div class='clearfix'></div></div>";          
	                 
	        output.insertBefore(div,null);   
	       div.children[i].addEventListener("click", function(event){
	           div.parentNode.removeChild(div);
	        });
	       
	  }
    }
    }


	function openwaterMarkImage() {	
	document.getElementById('lightwaterMarkImage').style.display='block';
	document.getElementById('fade').style.display='block';
	}
	    	  
	function closewaterMarkImage(){	  		
	document.getElementById('lightwaterMarkImage').style.display='none';
	document.getElementById('fade').style.display='none';
	
	 document.getElementById("wmPic").style.display='none'; 
	    var output = document.getElementById("resl");
	    $( "#resl" ).empty();
		   var checkedValue = [];
		            $.each($("input[name='imgidswm']:checked"), function(){            
		             checkedValue.push($(this).val());
		            });
		           // alert("My favourite sports are: " + checkedValue.join(", "));
		           if(checkedValue==""){
		        	   var div = document.createElement("div");        
                    	div.className = "grid-img event-bg-box";
                       div.innerHTML = "<h2>Background Overlay</h2> <div class='event-img-bg'></div>";
                       div.innerHTML += "<div class='event-img-text'><p>Upload transparent PNG to overlay on top of every Background for this Events (IE logo, picture frame, etc.) Position graphics relative to entire Background (IE logo in bottom right corner on 1920x1080 image.)</p> <div class='clearfix'></div></div>";
	        output.insertBefore(div,null);   
	       div.children[i].addEventListener("click", function(event){
	           div.parentNode.removeChild(div);
	        });
		           }else{
		  for(var i = 0; i< checkedValue.length; i++){
		   //alert(checkedValue[i]);
		   var div = document.createElement("div");        
	                     	div.className = "grid-img event-bg-box";
	                        div.innerHTML = "<h2>Background Overlay</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + checkedValue[i] + "'/> </div>";
	                        div.innerHTML += "<div class='event-img-text'><p>Upload transparent PNG to overlay on top of every Background for this Events (IE logo, picture frame, etc.) Position graphics relative to entire Background (IE logo in bottom right corner on 1920x1080 image.)</p> <div class='clearfix'></div></div>";
		        output.insertBefore(div,null);   
		       div.children[i].addEventListener("click", function(event){
		           div.parentNode.removeChild(div);
		        });
		  	}
		}
	}

	function opencameraTVScreenSaver() {	
	document.getElementById('lightcameraTVScreenSaver').style.display='block';
	document.getElementById('fade').style.display='block';
	}
		    	  
	function closecameraTVScreenSaver(){	  
	document.getElementById('lightcameraTVScreenSaver').style.display='none';
	document.getElementById('fade').style.display='none';
	
	document.getElementById("ctvsPic").style.display='none'; 
    var output = document.getElementById("reslctvs");
    $( "#reslctvs" ).empty();
	   var checkedValue = [];
	            $.each($("input[name='imgidsts']:checked"), function(){            
	             checkedValue.push($(this).val());
	            });
	           // alert("My favourite sports are: " + checkedValue.join(", "));
	           if(checkedValue==""){
	        	   var div = document.createElement("div");        
	        	   div.className = "grid-img event-bg-box";
	               div.innerHTML = "<h2>Photos Complete</h2> <div class='event-img-bg'></div>";
	               div.innerHTML += "<div class='event-img-text'><p>This image is displayed after Photos are taken, prompting Guests to look at the Touchscreen and choose their Photos/enter email.</p> <div class='clearfix'></div></div>";
	        	        output.insertBefore(div,null);   
	        	       div.children[i].addEventListener("click", function(event){
	        	           div.parentNode.removeChild(div);
	        	        });
	           }else{
	  for(var i = 0; i< checkedValue.length; i++){
	   //alert(checkedValue[i]);
	   var div = document.createElement("div");        
	   div.className = "grid-img event-bg-box";
       div.innerHTML = "<h2>Photos Complete</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + checkedValue[i] + "'/> </div>";
       div.innerHTML += "<div class='event-img-text'><p>This image is displayed after Photos are taken, prompting Guests to look at the Touchscreen and choose their Photos/enter email.</p> <div class='clearfix'></div></div>";
	        output.insertBefore(div,null);   
	       div.children[i].addEventListener("click", function(event){
	           div.parentNode.removeChild(div);
	        });
	       
	  			}
	     }
	}
	
	function openlookAtTouchScreen() {	
	document.getElementById('lightlookAtTouchScreen').style.display='block';
	document.getElementById('fade').style.display='block';
	}
			    	  
	function closelookAtTouchScreen(){  
	document.getElementById('lightlookAtTouchScreen').style.display='none';
	document.getElementById('fade').style.display='none';
	
	document.getElementById("atslPic").style.display='none'; 
    var output = document.getElementById("sver");
    $( "#sver" ).empty();
	   var checkedValue = [];
	            $.each($("input[name='imgidslts']:checked"), function(){            
	             checkedValue.push($(this).val());
	            });
	           // alert("My favourite sports are: " + checkedValue.join(", "));
	           if(checkedValue==""){
	        	   var div = document.createElement("div");        
	        	   div.className = "grid-img event-bg-box";
	               div.innerHTML = "<h2>Screensaver</h2> <div class='event-img-bg'></div>";
	               div.innerHTML += "<div class='event-img-text'><p>This image is displayed on the main TV (Camera device) while the Booth is not in an active session.</p> <div class='clearfix'></div></div>";
	        	        output.insertBefore(div,null);   
	        	       div.children[i].addEventListener("click", function(event){
	        	           div.parentNode.removeChild(div);
	        	        });
	           }else{
	  for(var i = 0; i< checkedValue.length; i++){
	   //alert(checkedValue[i]);
	   var div = document.createElement("div");        
	   div.className = "grid-img event-bg-box";
       div.innerHTML = "<h2>Screensaver</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + checkedValue[i]  + "'/> </div>";
       div.innerHTML += "<div class='event-img-text'><p>This image is displayed on the main TV (Camera device) while the Booth is not in an active session.</p> <div class='clearfix'></div></div>";
	        output.insertBefore(div,null);   
	       div.children[i].addEventListener("click", function(event){
	           div.parentNode.removeChild(div);
	        });
	       
	  }
	           }
	
	}
		
	</script>	
<style>
	.right-pannel{height:auto !important}
		#gif {
width:100%;
height:100%;
opacity:.95;
top:0;
left:0;
text-align:center;
position:fixed;
background-color:#313131;
overflow:auto;
z-index: 1000;
padding-top:20%;
}
</style>
<div class="col-lg-2 col-md-3" style="width:20%;"></div>
		<div class="col-lg-10 col-xs-12 right-pannel">
		<%-- <center style="color: #00a616;"><c:if test="${boothAdminLogin2.subId==1}">You need to upgrade your subscription to upload custom backgrounds.</c:if></center> --%>
					<h1 class="headingtitle1 pull-left">Create Event</h1>
					<div class="clearfix"></div>
					<div class="inner-content evtbg">
						<div class="col-row">
							<form action="event-background.html">
								<h1 class="heading" style="margin-bottom:10px;color:#2363c5">Event Details</h1>
								<table class="table table-bordered" style="width:90%">
									<tr>
										<td style="font-weight:600">Event Date</td>
										<td>${event.eventStart } &nbsp; ${event.eventTimezone}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Event</td>
										<td>${event.eventName}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Host</td>
										<td>${event.sponsorName}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Hosts Email Address</td>
										<td>${event.eventHostMailerId}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Facebook</td>
										<td>${event.facebook}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Twitter</td>
										<td>${event.twitter}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Email Body</td>
										<td>${event.emailBody}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Enable Subscription</td>
										<td>${event.isSubscribed}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Enable Name</td>
										<td>${event.isName}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Enable Phone Number</td>
										<td>${event.isPhone}</td>
									</tr>
								</table>
								</form>
								
								<!-- <h1 class="heading" style="margin:10px 0px 0px;color:#2363c5; border-color: black;border-top: 3px solid #05a42e;padding-top: 26px;">Backgrounds for Event</h1>
								<p class="subtext">Choose the Backgrounds that will be available to your Guests at this Event</p> -->
								<div class="grid-row" id="result">
									<!-- <div class="grid-img event-bg">
										
									</div> -->
									<div class="clearfix"></div>
								</div>
								<div class="grid-row" id="result2">
								         <!-- <div class="grid-img event-bg">
								          
								         </div> -->
								         <div class="clearfix"></div>
								</div>
								<div class="clearfix"></div>
								
	<%-- 							<div id="light" class="white_content">
  <center><div><b>Pre Set Background Compatible With Your Current Zoom Scale</b></div></center>
  					<c:if test="${emailImagesList.size() > 0}">
							<c:forEach items="${emailImagesList}" varStatus="loop" var="igl">
								<div class="gallery-div gallery-div-popup">
									<div class="img-pic" >
										<img src="${igl.mailImageUrl}">
										<input type="checkbox" class="checkbox1" id="imgids" name="imgids" value="${igl.id }" onclick='handleClick(this.value);' >
									</div>
								</div>
							</c:forEach>
							</c:if>
								<c:if test="${emailImagesList.size() == 0}">
								<center><div><b>Empty</b></div></center>
						</c:if>
  
  
<button onclick="close1();" style="float:right;margin-right:10px;" class="btn btn-green">Done</button>
  </div> --%>
  
  <div id="light" class="white_content">
    <center>
      <b>current zoom scale &nbsp; ${fovbyuser.zoomScale}</b><br/>
    </center>
    <c:if test="${emailImagesList.size() > 0}">
     <c:forEach items="${emailImagesList}" varStatus="loop" var="igl">
      
      <div class="gallery-div gallery-div-popup">
       <div class="img-pic">
        <img src="${igl.mailImageUrl}"> <input type="checkbox" class="checkbox1" id="imgids" name="imgidss" value="${igl.mailImageUrl}" onclick='handleClick(${igl.id});'>
       </div>
      </div>
     </c:forEach>
    </c:if>
    <c:if test="${emailImagesList.size() == 0}">
     <center>
      <div>
       <b>Empty</b>
      </div>
     </center>
    </c:if>
    <button onclick="close1();" style="float: right; margin-right: 10px;" class="btn btn-green">Done</button>
   </div>
  
  
  <div id="lightwaterMarkImage" class="white_content">
  					<c:if test="${waterMarkImage.size() > 0}">
							<c:forEach items="${waterMarkImage}" varStatus="loop" var="igl">
							<c:if test="${not empty igl.waterMarkImage }">
								<div class="gallery-div gallery-div-popup">
									<div class="img-pic" >
										<img src="${igl.waterMarkImage}">
										<input type="radio" class="checkbox1 WaterMarkImgaeClass" id="imgids" name="imgidswm" value="${igl.waterMarkImage }" onclick='handleClickWaterMarkImage(${igl.id });' >
									</div>
								</div>
								</c:if>
							</c:forEach>
							</c:if>
								<c:if test="${waterMarkImage.size() == 0}">
								<center><div><b>No Water Mark Image</b></div></center>
						</c:if>
<button onclick="closewaterMarkImage();" style="float:right;margin-right:10px;" class="btn btn-green">Done</button>
  </div>
  
   <div id="lightcameraTVScreenSaver" class="white_content">
  					<c:if test="${cameraTvScreenSaver.size() > 0}">
							<c:forEach items="${cameraTvScreenSaver}" varStatus="loop" var="igl">
							<c:if test="${not empty igl.cameraTVScreenSaver }">
								<div class="gallery-div gallery-div-popup">
									<div class="img-pic" >
										<img src="${igl.cameraTVScreenSaver}">
										<input type="radio" class="checkbox1 CameraTVScreenSaverClass" id="imgids" name="imgidsts" value="${igl.cameraTVScreenSaver }" onclick='handleClickCameraTVScreenSaver(${igl.id });' >
									</div>
								</div>
								</c:if>
							</c:forEach>
							</c:if>
								<c:if test="${cameraTvScreenSaver.size() == 0}">
								<center><div><b>No Camera TV Screen Saver</b></div></center>
						</c:if>
<button onclick="closecameraTVScreenSaver();" style="float:right;margin-right:10px;" class="btn btn-green">Done</button>
  </div>
  
   <div id="lightlookAtTouchScreen" class="white_content">
  					<c:if test="${lookAtTouch.size() > 0}">
							<c:forEach items="${lookAtTouch}" varStatus="loop" var="igl">
							<c:if test="${not empty igl.lookAtTouchScreen }">
								<div class="gallery-div gallery-div-popup">
									<div class="img-pic" >
										<img src="${igl.lookAtTouchScreen}">
										<input type="radio" class="checkbox1 LookAtTouch" id="imgids" name="imgidslts" value="${igl.lookAtTouchScreen }" onclick='handleClickLookAtTouchScreen(${igl.id });' >
									</div>
								</div>
								</c:if>
							</c:forEach>
							</c:if>
								<c:if test="${lookAtTouch.size() == 0}">
								<center><div><b>No Look At Touch Screen</b></div></center>
						</c:if>
<button onclick="closelookAtTouchScreen();" style="float:right;margin-right:10px;" class="btn btn-green">Done</button>
  </div>
   <div id="lightThankYouScreen" class="white_content">
  					<c:if test="${thankYou.size() > 0}">
							<c:forEach items="${thankYou}" varStatus="loop" var="igl">
							<c:if test="${not empty igl.thankYouScreen }">
								<div class="gallery-div gallery-div-popup">
									<div class="img-pic" >
										<img src="${igl.thankYouScreen}">
										<input type="radio" class="checkbox1 ThankYou" id="imgids" name="imgidsty" value="${igl.thankYouScreen}" onclick='handleClickThankYouScreen(${igl.id });' >
									</div>
								</div>
								</c:if>
							</c:forEach>
							</c:if>
								<c:if test="${thankYou.size() == 0}">
								<center><div><b>No Thank You Screen</b></div></center>
						</c:if>
<button onclick="closeThankYouScreen();" style="float:right;margin-right:10px;" class="btn btn-green">Done</button>
  </div>
  
  <div id="fade" class="black_overlay"></div>
								<form:form action="uploadBackgroundImage" modelAttribute="AdminPictureVO" onsubmit="return validateUpdateForm();" enctype="multipart/form-data" method="post">
								<input type="hidden" name="eventStart" value="${event.eventStart }"/>
								<input type="hidden" name="eventName" value="${event.eventName}"/>
								<input type="hidden" name="sponsorName" value="${event.sponsorName}"/>
								<input type="hidden" name="isSubscribed" value="${event.isSubscribed}"/>
								<input type="hidden" name="isName" value="${event.isName}"/>
								<input type="hidden" name="isPhone" value="${event.isPhone}"/>
								<input type="hidden" name="eventHostMailerId" value="${event.eventHostMailerId}"/>
								<input type="hidden" name="facebook" value="${event.facebook}"/>
								<input type="hidden" name="twitter" value="${event.twitter}"/>
								<input type="hidden" name="emailBody" value="${event.emailBody}"/>
								<input type="hidden" name="eventTimezone" value="${event.eventTimezone}"/>
								<input type="hidden" name="selectedPreImage" id="selectedPreImage" value="0"/>
								<input type="hidden" name="selectedPreWaterMarkImage" id="selectedPreWaterMarkImage" value="0"/>
								<input type="hidden" name="selectedPreCameraTVScreenSaver" id="selectedPreCameraTVScreenSaver" value="0"/>
								<input type="hidden" name="selectedPreLookAtTouchScreen" id="selectedPreLookAtTouchScreen" value="0"/>
								<input type="hidden" name="selectedPreThankYouScreen" id="selectedPreThankYouScreen" value="0"/>
								
								<div class="form_row evtexp" style="/*margin-top:35px;*/" >									
								
								<input type="hidden" value="${eid}" name="eId"/>
								<input type="hidden" value="${boothAdminLogin2.subId}" id="subid"/>
								<div class="demohide">
								
								<!-- <div class="upload-bg">
									<div class="form_label" style="width:27%">Upload Custom Background(s)</div>
									<div class="form_element">
										<input type="file" name="files" id="images2" multiple accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" class="demohide" style="margin-bottom:10px;margin-left:-12px;padding-bottom:5px;border-bottom:1px solid #e2e2e2;" />
										<p class="subtext" style="margin-top: -12px;">*press ctrl to select multiple Images</p>
										
									</div>
									<div class="form_label" style="margin:-15px 30px;"><div style="width:275px;margin:20px 0px;"><div data-step="4" data-intro="Submit the images after you have selected them"><input type="submit" value="Upload Now" style="width:auto" class="btn btn-green" data-toggle="tooltip" title="Submit the images after you have selected them" onclick="return ButtonClicked()"></div></div></div>
									<div class="clearfix"></div>
									<a href="javascript:void(0)" class="btn btn-green " style="margin:-40px 40px 0px;width:38%;font-size: 14px;float: right;" onclick="open3();">Choose Background(s) From Library</a>
								</div> -->
								
								<h1 class="heading" style="margin:20px 0px 0px;color:#2363c5; border-color: black;border-top: 3px solid #05a42e;padding-top: 26px;">Customized Event Experience</h1>
								<p class="subtext" style="margin-bottom:30px;">Modify screens shown to your guests during their booth session</p>
								
								
	<!--Desktop Blocks --> 	
					
								
								<div class="grid-row">
									<%-- <div class="grid-img event-bg-box">
										<h2>Background Overlay</h2>
										<div class="event-img-bg">
											<img src="<%=request.getContextPath()%>/resources/images/banner-bg.png" class="img-thumbnail">
										</div>
										<div class="event-img-text">
											<p>hfgdsahfgsif bghsuidn said hs</p>
											<div class="clearfix"></div>
										</div>
										<input type="checkbox">
									</div> --%>
									<div class="event-prev-box">
									<div class="grid-img event-bg-box" id='wmPic'>
										        <h2>Background Overlay</h2>
										        <div class="event-img-bg">
										         <img src="<%=request.getContextPath()%>/resources/img/1.png" class="img-thumbnail img-thumbnail1">
										        </div>
										     
										        <div class="event-img-text">
										         <p>Upload transparent PNG to overlay on top of every Background for this Events (IE logo, picture frame, etc.)<br/>
										         Position graphics relative to entire Background (IE logo in bottom right corner on 1920x1080 image.)</p>
										         <div class="clearfix"></div>
										        </div>
										        
										        <!-- <input type="checkbox"> -->
									</div> 
									<div id="resl">
									<div class="grid-img event-bg-box" id="overdiv" style="display:none;">
									<h2>Background Overlay</h2>
									<div class="event-img-bg">
										         <img src="" id="overimg" class="img-thumbnail img-thumbnail1">
										        </div>
										     
										        <div class="event-img-text">
										         <p>Upload transparent PNG to overlay on top of every Background for this Events (IE logo, picture frame, etc.)<br/>
										         Position graphics relative to entire Background (IE logo in bottom right corner on 1920x1080 image.)</p>
										        
										        </div><div class="clearfix"></div>
										        
									</div>
									</div> 
										<div class="uploadEventBrand">
										<input type="file" name="waterMarkImage" id="waterMarkImageUploadFile" class="demohide" accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateWaterMarkImageList()"  />
										
										
								        	<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 10px"   onclick="openwaterMarkImage();"  >Choose Background Overlay From Library</a> -->
									</div>
									</div>  
								  <div class="event-prev-box">
									<div class="grid-img event-bg-box" id="ctvsPic">
										<h2>Screensaver</h2>
										<div class="event-img-bg">
											<img src="<%=request.getContextPath()%>/resources/img/2.png" class="img-thumbnail img-thumbnail1">
										</div>
										
										<div class="event-img-text">
											<p>This image is displayed on the main TV (Camera device) while the Booth is not in an active session.</p>
											<div class="clearfix"></div>
										</div>
										<!-- <input type="checkbox"> -->
									</div>
									<div id="sver">
									<div class="grid-img event-bg-box" id="saverdiv" style="display:none;">
										<h2>Screensaver</h2>
										<div class="event-img-bg">
											<img src="" id="saverimg"  class="img-thumbnail img-thumbnail1">
										</div>
										
										<div class="event-img-text">
											<p>This image is displayed on the main TV (Camera device) while the Booth is not in an active session.</p>
											<div class="clearfix"></div>
										</div>
										<!-- <input type="checkbox"> -->
									</div>
									
									</div>
									 <div class="uploadEventBrand">
								         	<input type="file" name="cameraTVScreenSaver" id="cameraTVScreenSaverUploadFile" class="demohide" accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateCameraTvScreenSaverList()" />
								        	<div class="clearfix"></div>
								        	<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 11px"   onclick="opencameraTVScreenSaver();" >Choose Photos Complete From Library</a> -->
								         </div></div>
								   <div class="event-prev-box">      
									<div class="grid-img event-bg-box" id="atslPic">
									<h2>Photos Complete</h2>
										<div class="event-img-bg">
											<img src="<%=request.getContextPath()%>/resources/img/3.png" class="img-thumbnail img-thumbnail1">
										</div>
										<div class="event-img-text">
											<p>This image is displayed after Photos are taken, prompting Guests to look at the Touchscreen and choose their Photos/enter email.</p>
											<div class="clearfix"></div>
										</div>
										<!-- <input type="checkbox"> -->
									</div>
									<div id="reslctvs">
										<div class="grid-img event-bg-box" id="completediv" style="display:none;">
									<h2>Photos Complete</h2>
										<div class="event-img-bg">
											<img src="" id="completeimg" class="img-thumbnail img-thumbnail1">
										</div>
										<div class="event-img-text">
											<p>This image is displayed after Photos are taken, prompting Guests to look at the Touchscreen and choose their Photos/enter email.</p>
											<div class="clearfix"></div>
										</div>
										<!-- <input type="checkbox"> -->
									</div>
									
									</div>
										<div class="uploadEventBrand">
											<input type="file" name="lookAtTouchScreen" id="lookAtTouchScreenUploadFile" class="demohide" accept=".jpg,.png,.gif,.bmp" onchange="javascript:updatelookAtTouchScreenList()" />
											<div class="clearfix"></div>
								        	<!-- <a href="javascript:void(0)" class="btn btn-green"  style="font-size: 12px"  onclick="openlookAtTouchScreen();">Choose Screensaver From Library</a> -->
								        	<div class="clearfix"></div>
										</div>
										
										</div>
									<div class="event-prev-box">
									<div class="grid-img event-bg-box" id='tyPic'>
									<h2>End of Session</h2>
										<div class="event-img-bg">
											<img src="<%=request.getContextPath()%>/resources/img/4.png" class="img-thumbnail img-thumbnail1">
										</div>
										<div class="event-img-text">
											<p>This is the Thank You screen displayed at the conclusion of a successful Booth session.</p>
											<div class="clearfix"></div>
										</div>
										<!-- <input type="checkbox"> -->
									</div>
									
									<div id="reslty">
									<div class="grid-img event-bg-box" id='tydiv' style="display:none;">
									<h2>End of Session</h2>
										<div class="event-img-bg">
											<img src="" id="tyimg" class="img-thumbnail img-thumbnail1">
										</div>
										<div class="event-img-text">
											<p>This is the Thank You screen displayed at the conclusion of a successful Booth session.</p>
											<div class="clearfix"></div>
										</div>
										<!-- <input type="checkbox"> -->
									</div>
									
									</div>
									<div class="uploadEventBrand">
											<input type="file" name="thankyoufiles" id="thankyoufilesUploadFile" class="demohide"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateThankYouList()"  />
											<div class="clearfix"></div>
											<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 11px" onclick="openThankYouScreen();" >Choose End of Session From Library</a> -->
										</div>
										
									</div>
								</div>
								<div class="clearfix"></div>
									<!-- <div id="fileList"></div> -->
									
									<div class="form-row upload-screens">

										
									
										
										
								
										<div class="clearfix"></div>
									</div>
									<div class="clearfix"></div>		
	<!-- </div>	 -->

			
									
	<!-- Duplicate Code -->
	

	
	<!-- Duplicate Code End -->								
									

									
									</div>
									<div data-step="4" data-intro="Submit the images after you have selected them"><input type="submit" value="Next" style="width:117px;" class="btn btn-green pull-right" data-toggle="tooltip" title="Submit the images after you have selected them" onclick="return ButtonClicked()"><input type="reset" style="width:125px;margin-right: 10px;padding:10px 0px 10px 0px;margin-bottom:20px;" class="btn btn-green pull-right" value="Reset" onClick="window.location.reload()"></div>
									<div id="gif" class="pull-right" style="margin: 5px -220px;display:none;">
										<img src="https://www.willmaster.com/images/preload.gif" alt="loading...">
									</div></div>
									</form:form>
									<div class="clearfix"></div>
								</div>
							
						</div>
					</div>
					
				<div class="clearfix"></div>
