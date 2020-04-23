			<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/uploadImage.js">  </script>
			<link href="<%=request.getContextPath()%>/resources/css/css/bootstrap.min.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/resources/css/css/jcarousel.responsive.css" rel="stylesheet">
			<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/bootstrap.min.js"></script>
		
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/fancyBox/fancy.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/fancyBox/jquery.fancybox.pack.js?v=2.1.5"></script>
			<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/fancyBox/jquery.fancybox.css?v=2.1.5" media="screen" />	
		
<script>
$("document").ready(function(){
		//$("#uploadNow").css('display', 'none');
		$("#waterMarkImageUploadFile,#cameraTVScreenSaverUploadFile,#lookAtTouchScreenUploadFile,#thankyoufilesUploadFile").change(function () {
	    	$("#submitss").css("display", "block");
	    });
	});


    $(document).ready(function() {
        function disableBack() { 
        	window.history.forward() 
        	}
        window.onload = disableBack();
        window.onpageshow = function(evt) {
        	if (evt.persisted) disableBack() ;
        	}
    });
</script>		
		
		
		<script>
function validateUpdateForm(){
  var waterMarkImage=document.getElementById('selectedPreWaterMarkImage').value;
  var cameraTVScreenSaver=document.getElementById('selectedPreCameraTVScreenSaver').value;
  var lookAtTouchScreen=document.getElementById('selectedPreLookAtTouchScreen').value;
  var thankYouScreen=document.getElementById('selectedPreThankYouScreen').value;

  var uploadThankYouFiles=document.getElementById('thankyoufilesUploadFile');

var thankyouSize ='0';

for (var i = 0; i < uploadThankYouFiles.files.length; ++i) {
	thankyouSize = uploadThankYouFiles.files.item(i).size;
   
}
  

var uploadWaterMarkFiles=document.getElementById('waterMarkImageUploadFile');

var waterImageSize ='0';

for (var i = 0; i < uploadWaterMarkFiles.files.length; ++i) {
	waterImageSize = uploadWaterMarkFiles.files.item(i).size;
}
  

var uploadLookAtTouchFiles=document.getElementById('lookAtTouchScreenUploadFile');

var lookAtTouchImageSize ='0';

for (var i = 0; i < uploadLookAtTouchFiles.files.length; ++i) {
	lookAtTouchImageSize = uploadLookAtTouchFiles.files.item(i).size;
   
}

var uploadCameraTVSaverFiles=document.getElementById('cameraTVScreenSaverUploadFile');


var cameraImageSize ='0';

for (var i = 0; i < uploadCameraTVSaverFiles.files.length; ++i) {
	cameraImageSize = uploadCameraTVSaverFiles.files.item(i).size;
   
}


 if( (waterMarkImage.trim() == '0') && (cameraTVScreenSaver.trim() =='0') && (lookAtTouchScreen.trim() =='0') && (thankYouScreen.trim() =='0') && (thankyouSize =='0') && (waterImageSize=='0') && (lookAtTouchImageSize=='0') && (cameraImageSize== '0')){
 alert('Please select Pre Set BackGround Screen');
  return false;
 }
 
 }
 
</script>
		
		
		
		<script>
			 var selectedCheckBox = new Array();
			 function handleClick(id){
				// selectedCheckBox.push(id);
				// $('#selectedPreImage').val(selectedCheckBox);
				
			 }
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
			 
			 
			 function updateThankYouList(){
				 $('#selectedPreThankYouScreen').val('0');
				 $('.ThankYou').prop('checked', false);
			 }
			 
			 function updateWaterMarkImageList(){
				 $('#selectedPreWaterMarkImage').val('0');
				 $('.WaterMarkImgaeClass').prop('checked', false);
			 }
			 
			 function updatelookAtTouchScreenList(){
				 $('#selectedPreLookAtTouchScreen').val('0');
				 $('.LookAtTouch').prop('checked', false);
			 }
			 
			 function updateCameraTvScreenSaverList(){
				 $('#selectedPreCameraTVScreenSaver').val('0');
				 $('.CameraTVScreenSaverClass').prop('checked', false);
			 }
			 
			 </script>
			<script type="text/javascript"> 
function open2() {
	var eventZoomScale =document.getElementById("eventZoomScale").value;
	var currentZoomScale =document.getElementById("currentZoomScale").value;
	if(eventZoomScale==currentZoomScale){
      document.getElementById('light').style.display='block';
     // document.getElementById('fade').style.display='block';
      document.getElementById('selectedPreImage').value='0';
      $('.checkbox1').removeAttr('checked');
	}else{
		alert("Your Current Zoom Scale Not Match to Your Event Zoom Scale");
		return false;
		}
    	  }
    	  
function close2(){	
	var favorite = [];
$.each($("input[name='imgids']:checked"), function(){            
    favorite.push($(this).val());
});
if(favorite==''){
 alert("Please select at least one background");
 return false;
}else{
	jQuery.noConflict();
	jQuery("#myModal").modal('hide');
document.getElementById('light').style.display='none';
document.getElementById('fade').value=favorite;
document.getElementById('uploadBackground').submit();  

/* var index = 1;
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
         }); */
}
}
</script>
<script type="text/javascript">
	function updateList() {
		jQuery("#myModal").modal('hide');
		document.getElementById("gif").style.display = "";
  var input = document.getElementById('images2');
  var output = document.getElementById('fileList');

  output.innerHTML = '<ul>';
  for (var i = 0; i < input.files.length; ++i) {
	  var imageSize = input.files[i].size;
	    if(imageSize > 5000000){
	    	document.getElementById("gif").style.display = "none";
	    alert("Your file size is to large file size is "+ (imageSize / (1024*1024)).toFixed(2)+ "MB")
	    
	    return false;
		}else{
	    	//continue ++;
	    	output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
	    }
  }
  output.innerHTML += '</ul>';
  document.getElementById('uploadBackground').submit();
}
	
	/* function updateList1() {
  var input = document.getElementById('images1');
  var output = document.getElementById('fileList1');

  output.innerHTML = '<ul>';
  for (var i = 0; i < input.files.length; ++i) {
    output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  }
  output.innerHTML += '</ul>';
} */
	</script>
			<style>
			.jcarousel ul li input[type="radio"]{
				position: absolute;
			    top: 5px;
			    left: 8px;
			    width:22px;
			    height:22px;
			}
			/*.right-pannel{    height: auto !important;    min-height: auto !important;}*/
			</style>
			<script type="text/javascript">
			      $(document).ready(function() {
			    	  $('#effect').delay(9000).fadeOut(400);
			            $("#b").addClass("active_menu");
			        });
			      function deletEventPicture(picId,eventId){
			    	  window.location.href ="<%=request.getContextPath()%>/deletEventPicture?picId="+picId+"&eventId="+eventId;
			      }
			      
			      function setPosition(id){
						 $('#position').val(id);
					 }
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

}
</style>
<style>
.black_overlay2 {
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

}
.WHITE_CONTENT_PRE_SELECT_BACKGROUND {
  display: none;
  position: fixed;
 top: 5%;
    left: 10%;
    width: 80%;
    height: 90%;
  padding: 16px;
  border: 16px solid #05a42e;
  background-color: white;
  z-index: 5000;
  overflow: auto;

}
</style>
 <script type="text/javascript"> 
 jQuery.noConflict();
function open1() {
	jQuery("#myModal").modal('show');
    	  }
    	  
function close1(){	  
document.getElementById('light').style.display='none';
document.getElementById('fade').style.display='none';
}
</script>			
	 <script type="text/javascript"> 
				
    function openThankYouScreen() {
    document.getElementById('lightThankYouScreen').style.display='block';
    document.getElementById('fade').style.display='block';
    }
    	  
    /* function closeThankYouScreen(){	  
    document.getElementById('lightThankYouScreen').style.display='none';
    document.getElementById('fade').style.display='none';
    }
 	*/
 
	 function closeThankYouScreen(){	
	     document.getElementById('lightThankYouScreen').style.display='none';
	     var checkedValue = [];
	     $.each($("input[name='imgidsty']:checked"), function(){            
	      checkedValue.push($(this).val());
	     });
	     
	     var output = document.getElementById("reslty");
	     $( "#reslty" ).empty();
	 	    if(checkedValue.length>0){ 
	 	    	document.getElementById("tyPic").style.display='none'; 	
	 		  for(var i = 0; i< checkedValue.length; i++){		   
	 		   var div = document.createElement("div");        
	 		                 div.className = "img-prev-container";
	 	                     div.innerHTML = "<img class='event-img-bg' src='" + checkedValue[i] + "'/>";         
	 		                 
	 		        output.insertBefore(div,null); 
	 		  } 
	 	   }
	 }
	 

	function openwaterMarkImage() {
	document.getElementById('lightwaterMarkImage').style.display='block';
	document.getElementById('fade').style.display='block';
	}
	    	  
	function closewaterMarkImage(){	  
	document.getElementById('lightwaterMarkImage').style.display='none';
	var checkedValue = [];
    $.each($("input[name='imgidswm']:checked"), function(){            
     checkedValue.push($(this).val());
    });
    
    var output = document.getElementById("reslwm");
    $( "#reslwm" ).empty();
	    if(checkedValue.length>0){ 
	    	document.getElementById("wmPic").style.display='none'; 	
		  for(var i = 0; i< checkedValue.length; i++){		   
		   var div = document.createElement("div");        
		                 div.className = "img-prev-container";
	                     div.innerHTML = "<img class='event-img-bg' src='" + checkedValue[i] + "'/>";         
		                 
		        output.insertBefore(div,null); 
		  } 
	   }
	}

	function opencameraTVScreenSaver() {
	document.getElementById('lightcameraTVScreenSaver').style.display='block';
	document.getElementById('fade').style.display='block';
	}
		    	  
	function closecameraTVScreenSaver(){	  
	document.getElementById('lightcameraTVScreenSaver').style.display='none';
	var checkedValue = [];
    $.each($("input[name='imgidsctv']:checked"), function(){            
     checkedValue.push($(this).val());
    });
    
    var output = document.getElementById("reslctv");
    $( "#reslctv" ).empty();
	    if(checkedValue.length>0){ 
	    	document.getElementById("ctvPic").style.display='none'; 	
		  for(var i = 0; i< checkedValue.length; i++){		   
		   var div = document.createElement("div");        
		                 div.className = "img-prev-container";
	                     div.innerHTML = "<img class='event-img-bg' src='" + checkedValue[i] + "'/>";         
		                 
		        output.insertBefore(div,null); 
		  } 
	   }
	}
	
	function openlookAtTouchScreen() {
	document.getElementById('lightlookAtTouchScreen').style.display='block';
	document.getElementById('fade').style.display='block';
	}
			    	  
	function closelookAtTouchScreen(){	 
	
		document.getElementById('lightlookAtTouchScreen').style.display='none';		
		var checkedValue = [];
	    $.each($("input[name='imgidslats']:checked"), function(){            
	     checkedValue.push($(this).val());
	    });
	    var output = document.getElementById("resllats");
	    $( "#resllats" ).empty();
		    if(checkedValue.length>0){ 
		    	document.getElementById("latsPic").style.display='none';
		    
			  for(var i = 0; i< checkedValue.length; i++){		   
			   var div = document.createElement("div"); 
			                 div.className = "img-prev-container";
		                     div.innerHTML = "<img class='event-img-bg' src='" + checkedValue[i] + "'/>";        
			                 
			        output.insertBefore(div,null); 
			  } 
		   }
	}
		function validateId(){
			var idsssss=document.getElementById('imgIdofConfig').value;
			if(idsssss==0){
				alert("No Background(s) Found For This Event");
				return false;
			}
		}
		jQuery(document).ready(function() {  
			  
			jQuery('#slideshowContainer').jcarousel({  
			    scroll: 1,  
			    auto: .01,  
			    wrap: 'last',  
			    easing: 'linear'  
			     });  
			}); 
		function close3(){ 
			document.getElementById('light').style.display='none';
		   	document.getElementById('fade').style.display='none';
		}
		function validateZoom(){
			var eventZoomScale =document.getElementById("eventZoomScale").value;
			var currentZoomScale =document.getElementById("currentZoomScale").value;
			if(eventZoomScale==currentZoomScale){
				return true;
			}else{
			alert("Your Current Zoom Scale Not Match to Your Event Zoom Scale");
			return false;
			}
		}
	</script>		
<div id="gif" style="display: none; z-index: 10000;">
	<img src="https://www.willmaster.com/images/preload.gif">
</div>		
<div class="col-lg-2" style="width:20%;"></div>	
			<div class="right-pannel col-lg-10 col-xs-12">
				 <c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
 					 <a href="javascript:void(0)" class="btn btn-green pull-right"  onclick="open1();">Add Background</a> 				
								
					<a href="publishNow?eid=${eid}"><input type="button" class="btn btn-green pull-right" value="Publish Now" style="width:auto;margin-right: 10px;" data-toggle="tooltip" title=""></a>
					<div class="clearfix"></div>
					<h1 class="heading pull-left">Create Event &nbsp;:&nbsp;<b style="color: green"> ${eventName}</b></h1>
					<div class="clearfix"></div>
					<p class="subtext">This Event Zoom Profile (<c:if test="${eventZoomScale==1.00}">Zoomed In</c:if><c:if test="${eventZoomScale==0.50 || eventZoomScale==0.5}">Zoomed Out</c:if><c:if test="${eventZoomScale==0.75}">Medium Zoom</c:if>)</p>
					<!-- <ul class="nav nav-tabs">
					    <li><a data-toggle="tab" href="#">Step 1<br/>Register Devices</a></li>
					    <li><a data-toggle="tab" href="#">Step 2<br/>Setup Booth</a></li>
					    <li class="active"><a data-toggle="tab" href="#" style="background-color: #05a42e ! important;color: #ffffff  ! important;">Step 3<br/>Setup Events</a></li>
					  </ul> -->
										
						<div id="light" class="WHITE_CONTENT_PRE_SELECT_BACKGROUND">
   <center style="margin: 10px 0px 30px;">
      <b>current zoom scale &nbsp; (<b><c:if test="${fovbyuser.zoomScale==1.00}">Zoomed In</c:if><c:if test="${fovbyuser.zoomScale==0.50 || fovbyuser.zoomScale==0.5}">Zoomed Out</c:if><c:if test="${fovbyuser.zoomScale==0.75}">Medium Zoom</c:if></b>)</b><br/>
    </center>
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
								<center><div><b>No Event Summary</b></div></center>
						</c:if>
  <div class="clearfix"></div>
   <button onclick="return close3();" style="float: right; margin-left: 10px;" class="btn btn-green">Close</button>
<button onclick="close2();" style="float:right;margin-right:10px;" class="btn btn-green">Done</button>
  </div>
  
  
  <div id="lightwaterMarkImage" class="white_content">
  					<c:if test="${waterMarkImage.size() > 0}">
							<c:forEach items="${waterMarkImage}" varStatus="loop" var="igl">
							<c:if test="${not empty igl.waterMarkImage }">
								<div class="gallery-div gallery-div-popup">
									<div class="img-pic" >
										<img src="${igl.waterMarkImage}">
										<input type="radio" class="checkbox1 WaterMarkImgaeClass" id="imgids" name="imgidswm" value="${igl.waterMarkImage }" onclick='handleClickWaterMarkImage(${igl.id});' >
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
										<input type="radio" class="checkbox1 CameraTVScreenSaverClass" id="imgids" name="imgidsctv" value="${igl.cameraTVScreenSaver }" onclick='handleClickCameraTVScreenSaver(${igl.id});' >
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
										<input type="radio" class="checkbox1 LookAtTouch" id="imgids" name="imgidslats" value="${igl.lookAtTouchScreen }" onclick='handleClickLookAtTouchScreen(${igl.id});' >
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
										<input type="radio" class="checkbox1 ThankYou" id="imgidsThankYou" name="imgidsty" value="${igl.thankYouScreen }" onclick='handleClickThankYouScreen(${igl.id});' >
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
					<div id="myModal" class="modal fade">
					    <div class="modal-dialog">
					        <div class="modal-content" style="width:120% !important;">
					            <div class="modal-header">
					                <button type="button" class="close" data-dismiss="modal"  aria-hidden="true">&times;</button>
					                <h4 class="modal-title">Upload Background</h4>
					                <p class="subtext">Current Camera Screen size: <c:forEach items="${deviceRegistration}" var="cr">${cr.deteactedResolution}</c:forEach> (Final email attachment dimensions dictated by Camera device's screen dimensions, regardless of Camera Megapixels.)</p>
					            </div>
					            <div class="modal-body">
					             <input type="hidden" value="${eventZoomScale}" id="eventZoomScale" />
					             <input type="hidden" value="${fovbyuser.zoomScale}" id="currentZoomScale" />
					                <form:form action="editUploadBackgroundImageConfig" modelAttribute="AdminPictureVO" onsubmit="return validateForm2();" enctype="multipart/form-data" method="post"  name="uploadBackground" id="uploadBackground">
										<input type="hidden" value="${eid}" name="eId"  id="eventId"/>
										<input type="hidden" name="selectedPreImage" id="selectedPreImage" value="0"/>
											<div class="form_row" style="margin-top:35px;" >									
											<input type="hidden" value="${eid}" name="eId"/>
												<span class="upload_file" >
													<div class="file_container" data-step="3" data-intro="Upload one or multiple events by keeping the control key pressed" data-toggle="tooltip" title="Upload one or multiple events by keeping the control key pressed">
														<input type="file" name="files" id="images2" multiple accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" onclick="return validateZoom();" />
													<a href="javascript:void(0)"   onclick="open2();" style=" float: right;margin-top:-35px;" class="btn btn-green">Choose Background(s) From Library</a>
													</div>
													<p>&nbsp;&nbsp;*&nbsp;press ctrl to select multiple Images</p>
												</span>
												<div id="fileList"></div>
												<div id="formsubmitbutton">
													<div data-step="4" data-intro="Submit the images after you have selected them"><input type="submit" value="Submit" style="width:auto;margin-left:10px;display: none;" class="btn btn-green" data-toggle="tooltip" title="Submit the images after you have selected them" onclick="ButtonClicked()"></div>
												</div>
												<div id="buttonreplacement" style="margin-left:100px; display:none;">
													<img src="https://www.willmaster.com/images/preload.gif" alt="loading...">
												</div>
												<div class="clearfix"></div>
											</div>
										</form:form>
										
					            </div>
					        </div>
					    </div>
					</div>
					
					
					<div class="clearfix"></div>
					
					<div class="inner-content" style="padding:35px;margin-top: 0px;">
					<h1 class="custom-heading">Backgrounds for the Event</h1>
						<div class="col-row">
						<form:form action="setUpBackgroundImageConfig" modelAttribute="AdminPictureVO" onclick="return validateId();">
							<div class="slide_docs">
								<div class="jcarousel-wrapper" data-step="5" data-intro="Select the desired image to be configured by clicking on the radio button and click on Configure Image">
								<input type="hidden" value="${eid}" name="eId"/>
								<input type="hidden" name="position" id="position" value="">
								<input type="hidden" id="imgIdofConfig" value="${adminPictureVOs2.size()}">
								<c:if test="${notConfiguredImage!=0}"><p class="subtext" style="margin-bottom: 10px;">You have not configured <b>${notConfiguredImage}</b> out of <b>${adminPictureVOs2.size()}</b> backgrounds</p></c:if>
									<!-- <div class="jcarousel" style="padding-left:5px !important" id="slideshowContainer">
										<ul> -->
										<div class="scroll_slider">
										<c:forEach items="${adminPictureVOs2}" var="picBackground" varStatus="loop" >
											<c:if test="${picBackground.imageHeight !=null}">
										            <div class="col-sm-6 col-md-3 ">
										            	<div class="grid_view_img radio_cross"><img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"><c:if test="${boothAdminLogin2.subId!=1}"><span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></c:if></div>
										            	<p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										            </div>
										    </c:if>
										    <c:if test="${picBackground.imageHeight ==null}">
										        <div class="col-sm-6 col-md-3 ">
										            <div class="grid_view_img radio_cross"  style="border: 2px solid red;">
										            	<img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"><c:if test="${boothAdminLogin2.subId!=1}"><span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></c:if>
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										        </div>
										    </c:if>
										    
										    
										    
											<%-- <li>
											
												<!-- <img src="${picBackground.picName }" alt="Image 2" style="max-width:100%; height:auto;-o-object-fit: contain;"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" >-->
												<div class="thumbnail_container" id="">
												<c:if test="${picBackground.imageHeight !=null}">
										            <div class="thumbnail" >
										              <div class="grid_view_img"><img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"><c:if test="${boothAdminLogin2.subId!=1}"><span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></c:if></div>
										          <div class="clearfix"></div>
										           
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										         </c:if>
										         
										         
										         <c:if test="${picBackground.imageHeight ==null}">
										          <div class="thumbnail" style="border: 2px solid red;">
										              <div class="grid_view_img"><img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"><c:if test="${boothAdminLogin2.subId!=1}"><span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></c:if></div>
										            <div class="clearfix"></div>
										            
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										         </c:if>
										         </div>
											</li> --%>
										</c:forEach>
										</div>	
										<%-- <c:forEach items="${adminPictureVOs2}" var="picBackground" varStatus="loop" >
											<li>
											
												<!-- <img src="${picBackground.picName }" alt="Image 2" style="max-width:100%; height:auto;-o-object-fit: contain;"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" >-->
												<div class="thumbnail_container" id="">
												<c:if test="${picBackground.imageHeight !=null}">
										            <div class="thumbnail" >
										              <div class="grid_view_img"><img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"> <span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></div>
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										         </c:if>
										         <c:if test="${picBackground.imageHeight ==null}">
										          <div class="thumbnail" style="border: 2px solid red;">
										              <div class="grid_view_img"><img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"> <span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></div>
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										         </c:if>
										         </div>
											</li>
										</c:forEach>	 --%>										
										<!-- </ul> -->
									<!-- </div> -->
									<!-- <a href="#" class="jcarousel-control-prev">&lsaquo;</a>
									<a href="#" class="jcarousel-control-next">&rsaquo;</a> -->
									<!-- <p class="jcarousel-pagination"></p> -->
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="blank_line"></div>
							<div class="configure_img">
								<input type="submit" class="btn btn-green" id="submit" value="Edit Background" style="width:auto" data-toggle="tooltip" title="Select the desired image to be configured by clicking on the radio button and click on Configure Image">
							</div>
							</form:form>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					
					
					<div class="inner-content" style="padding:35px;">
					<h1 class="custom-heading">Custom Branding</h1>
					 <form:form action="updateWaterMarkLookAtTouchThankYouCameraScreenConfig" modelAttribute="AdminPictureVO" onsubmit="return validateUpdateForm();" enctype="multipart/form-data" method="post">
							<input type="hidden" value="${eid}" name="eId"  id="eventId"/>
							<input type="hidden" name="selectedPreWaterMarkImage" id="selectedPreWaterMarkImage" value="0"/>
							<input type="hidden" name="selectedPreCameraTVScreenSaver" id="selectedPreCameraTVScreenSaver" value="0"/>
							<input type="hidden" name="selectedPreLookAtTouchScreen" id="selectedPreLookAtTouchScreen" value="0"/>
							<input type="hidden" name="selectedPreThankYouScreen" id="selectedPreThankYouScreen" value="0"/>
						
						
						
						<div class="img-prev-box">
						<c:forEach items="${setWaterMarkImageByEventId}" var="setPreFourBackground" varStatus="loop" >
							<div class="img-prev-container" id="wmPic">
								<c:if test="${not empty setPreFourBackground.waterMarkImage}">
								<a class="fancybox" href="${setPreFourBackground.waterMarkImage}" data-fancybox-group="gallery" title="Custom Branding">
										<img src="${setPreFourBackground.waterMarkImage}">
									</a>
								</c:if>
							</div>
							<div id="reslwm"></div>
							<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 10px;" onclick="openwaterMarkImage();">Choose Background Overlay From Library</a> -->
							</c:forEach>
						</div>
						
						<div class="img-prev-box">
						<c:forEach items="${setCameraTVScreenByEventId}" var="setPreFourBackground" varStatus="loop" >
							<div class="img-prev-container" id="ctvPic">
								<c:if test="${not empty setPreFourBackground.cameraTVScreenSaver}">
								<a class="fancybox" href="${setPreFourBackground.cameraTVScreenSaver}" data-fancybox-group="gallery" title="Custom Branding">
										<img src="${setPreFourBackground.cameraTVScreenSaver}">
									</a>
								</c:if>
							</div>
							<div id="reslctv"></div>
						<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 12px;" onclick="opencameraTVScreenSaver();">Choose Screensaver Form Library</a> -->
						</c:forEach>
						</div>
						<div class="img-prev-box">
						<c:forEach items="${setLookAtTouchByEventId}" var="setPreFourBackground" varStatus="loop" >
							<div class="img-prev-container" id="latsPic">
								<c:if test="${not empty setPreFourBackground.lookAtTouchScreen}">
								<a class="fancybox" href="${setPreFourBackground.lookAtTouchScreen}" data-fancybox-group="gallery" title="Custom Branding">
										<img src="${setPreFourBackground.lookAtTouchScreen}">
									</a>
								</c:if>
							</div>
							<div id="resllats"></div>
							<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 10px;" onclick="openlookAtTouchScreen();">Chooses Photos Complete From Library</a> -->
							</c:forEach>
						</div>
						<div class="img-prev-box">
						<c:forEach items="${setThankYouByEventId}" var="setPreFourBackground" varStatus="loop" >
							<div class="img-prev-container" id="tyPic">
								<c:if test="${not empty setPreFourBackground.thankYouScreen}">
								<a class="fancybox" href="${setPreFourBackground.thankYouScreen}" data-fancybox-group="gallery" title="Custom Branding">
										<img src="${setPreFourBackground.thankYouScreen}">
									</a>	
								</c:if>
							</div>
							<div id="reslty"></div>
							<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 12px;"  onclick="openThankYouScreen();">Choose End Session From Library</a> -->
							</c:forEach>
						</div>
						<div class="clearfix"></div>
						
						<div class="upload-imgs">
							<div class="form-row upload-screens">
									
									
									
										<div>
											<span>	End of Session </span><input type="file" name="thankyoufiles" id="thankyoufilesUploadFile"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateThankYouList()" />
											<div class="clearfix"></div>
										</div>
										
										
									
										<div>
											<span>Photos Complete		</span><input type="file" name="lookAtTouchScreen" id="lookAtTouchScreenUploadFile"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updatelookAtTouchScreenList()" />
											<div class="clearfix"></div>
										</div>
										
										<div>
								         	<span>Screensaver</span><input type="file" name="cameraTVScreenSaver" id="cameraTVScreenSaverUploadFile"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateCameraTvScreenSaverList()" />
								        	<div class="clearfix"></div>
								         </div>
								         
								         	<div>
										<span>Background Overlay</span>	<input type="file" name="waterMarkImage" id="waterMarkImageUploadFile"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateWaterMarkImageList()" />
										<div class="clearfix"></div>
										</div>
										<div class="clearfix"></div>
									</div>
									<input type="reset" value="Reset" class="btn btn-green" id="submitsss" style="width:125px;margin-right: 10px;padding:10px 0px 10px 0px;display:none;float: left;" onClick="window.location.reload()"/>
							<input type="submit" value="Save Changes" class="btn btn-green" id="submitss" style="width:auto;display:none;"/>
						
						</div>
						
						</form:form>
					</div>
					
					
					<%-- <a href="boothSetUpByEvent?eventId=${eid}" style="margin-top:10px;" class="pull-right" data-toggle="tooltip" title="These are the camera settings for your ongoing event"><button class="btn btn-green">Next Step >> Booth Setup</button></a> --%>
				</div>
				<div class="clearfix"></div>
			<!-- Added by Abhishek -->	
				