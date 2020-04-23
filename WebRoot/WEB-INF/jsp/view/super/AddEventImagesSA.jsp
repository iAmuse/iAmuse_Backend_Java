<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
  			<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/uploadImage.js">  </script>
  <!--  <link href="<%=request.getContextPath()%>/resources/css/css/bootstrap.min.css" rel="stylesheet">-->
			<link href="<%=request.getContextPath()%>/resources/css/css/jcarousel.responsive.css" rel="stylesheet">
			<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<!--  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/bootstrap.min.js"></script>-->
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/jquery.jcarousel.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/jcarousel.responsive.js"></script>
  
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/eventBackground.js">  </script>
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
 $(document).ready(function() {
	  $('#effect').delay(9000).fadeOut(400);
	  $("#b").removeClass("active_menu");
       $("#d").addClass("active_menu");
   });
 </script>	

<script>
$(document).ready(function(){
    $("#images2").on("change", function() {
    	this.form.submit();
    });
});

function setPosition(id){
	 $('#position').val(id);
}

$("document").ready(function(){
	//$("#uploadNow").css('display', 'none');
	$("#waterMarkImageUploadFile,#cameraTVScreenSaverUploadFile,#lookAtTouchScreenUploadFile,#thankyoufilesUploadFile").change(function () {
		$("#submitss").css("display", "block");
		$("#submitsss").css("display", "block");
    });
});


   
$(document).ready(function() {
    function disableBack() { 
    	window.history.forward() 
    	}
    window.onload = disableBack();
    window.onpageshow = function(evt) { 
    	}
});

function deletEventPicture(picId,eventId){
	var r= confirm("Are you sure want to delete ?");
	   if(r){
		   window.location.href ="<%=request.getContextPath()%>/delEventPicture?picId="+picId+"&eventId="+eventId;
	   }else{
		   
	   }
}
</script>  
<script type="text/javascript">
	
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
		var existingBackground=$("#selectedPreImage").val(); 	    
	    var uploadBackground = $('#images2').val();
	    if(existingBackground==0 && uploadBackground==""){
	    	alert("Please select at least 1 Background"); 
	      return false;
	    }
   		document.getElementById("formsubmitbutton").style.display = "none"; // to undisplay
   		document.getElementById("buttonreplacement").style.display = ""; // to display
	}
	var FirstLoading = true;
	function RestoreSubmitButton(){
   	if( FirstLoading ) {
      FirstLoading = false;
      return;
   	}
   document.getElementById("formsubmitbutton").style.display = ""; // to display
   document.getElementById("buttonreplacement").style.display = "none"; // to undisplay
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
	.right-pannel{height:auto !important;}
</style>
 <script type="text/javascript"> 
				
function open3() {
document.getElementById('light').style.display='block';
document.getElementById('fade').style.display='block';
document.getElementById('selectedPreImage').value='0';
$('.checkbox1').removeAttr('checked');

    	  }
    	  
function close1(){ 
	    var arrCheckboxes = document.getElementById("deleteFilesss").getElementsByTagName("input");
	    var checkCount = 0;
	    for (var i = 0; i < arrCheckboxes.length; i++) {
	        checkCount += (arrCheckboxes[i].checked) ? 1 : 0;
	    }
	    if (checkCount > 0){
	    	document.getElementById('light').style.display='none';
		   	document.getElementById('fade').style.display='none';
		   	document.getElementById("gif").style.display = "";
	    	
	    	var favorite = [];
            $.each($("input[name='imgidss']:checked"), function(){            
                favorite.push($(this).val());
            });
	   	
            document.getElementById("selectedPreImage").value=favorite.join(",")
            setTimeout(myFun, 200);
            return true;
	    }else {
	        alert("Please select at least 1 Background");
	        return false;
	    }
	  	
	 
	 }
function close3(){ 
	document.getElementById('light').style.display='none';
   	document.getElementById('fade').style.display='none';
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
	 function myFun(){
		  var idsssssss=document.getElementById("selectedPreImage").value;
		  if(idsssssss !=0 && idsssssss !=""){
			  document.querySelectorAll("input[type=submit]")[0].click();
			} 
	 }
	 jQuery(document).ready(function() {  
		  
			jQuery('#slideshowContainer').jcarousel({  
			    scroll: 1,  
			    auto: 2,  
			    wrap: 'last',
			    easing: 'linear'  
			     });  
			  
			});  
</script>
<style>
	.right-pannel{height:auto !important}
	.event-bg{position:relative;}
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

<div id="gif" style="display: none;">
	<img src="https://www.willmaster.com/images/preload.gif">
</div>
<div class="col-lg-2" style="width:20%;"></div>
		<div class="right-pannel col-lg-10 col-xs-12">
		
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:15px;"> 
						<div class="col-row">
								<div> 
								<div class="bgeventbtn1" ><h1 class="heading" style="margin:10px 0px 0px;color:#2363c5; border-color: black;">Backgrounds for Event</h1>
								<p class="subtext">Choose the Backgrounds that will be available to your Guests at this Event</p></div>
								<div class="bgeventbtn">
								<a href="publishNow?eid=${eid}"><input type="button" class="btn btn-green pull-right" value="Publish Now" style="margin-right: 10px;margin-top:10px" data-toggle="tooltip" title=""></a>
								</div>
								</div>
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
								
  
  <div id="light" class="white_content">
    <center style="margin: 10px 0px 30px;">
      <b>current zoom scale &nbsp; (<c:if test="${fovbyuser.zoomScale==1.00}">Zoomed In</c:if><c:if test="${fovbyuser.zoomScale==0.50 || fovbyuser.zoomScale==0.5}">Zoomed Out</c:if><c:if test="${fovbyuser.zoomScale==0.75}">Medium Zoom</c:if>)</b><br/>
    </center>
     <div class="gallery" id="deleteFilesss">
    <c:if test="${emailImagesList.size() > 0}">
     <c:forEach items="${emailImagesList}" varStatus="loop" var="igl">
      
      <div class="gallery-div gallery-div-popup">
      
       <div class="img-pic">
        <img src="${igl.mailImageUrl}"> <input type="checkbox" class="checkbox1" id="imgids" name="imgidss" value="${igl.id}" >
       </div>
      </div>
     </c:forEach>
    </c:if>
     </div>
    <c:if test="${emailImagesList.size() == 0}">
     <center>
      <div>
       <b>Empty</b>
      </div>
     </center>
    </c:if>
    <button onclick="return close3();" style="float: right; margin-left: 10px;" class="btn btn-green">Close</button>
    <button onclick="return close1();" style="float: right;" class="btn btn-green">Done</button>
    
   </div>
 <div id="fade" class="black_overlay"></div>
								<form:form action="addUploadBackgroundImageSA" modelAttribute="AdminPictureVO" onsubmit="return validateUpdateForm();" enctype="multipart/form-data" method="post" id="fileUploadForm">
								<input type="hidden" value="${boothAdminLogin1.subId}" id="subid"/>
								<input type="hidden" name="selectedPreImage" id="selectedPreImage" value="0" />
								
								
								<div class="form_row uploadcbg">									
								
								<input type="hidden" value="${eid}" name="eId"/>
								<div class="demohide">
								
								<div class="upload-bg">
									<div class="form_label" style="width:100%">Upload Custom Background(s)</div> 
									<div class="form_element">
										<input type="file" name="files" id="images2"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" class="demohide"  />
										<!-- <p class="subtext" style="margin-top: -12px;">*press ctrl to select multiple Images</p> -->
									</div>
									<div class="clearfix"></div>
									<!-- <a href="javascript:void(0)" class="btn btn-green " style="margin:-40px 40px 0px;width:38%;font-size: 14px;float: right;" onclick="open3();">Choose Background(s) From Library</a> -->
								</div>
									</div></div>
									<div class="form_label" ><div style="width:275px;margin:20px 0px;"><div data-step="4" data-intro="Submit the images after you have selected them"><input type="submit" value="Publish Now" style="width:auto;display:none" class="btn btn-green" data-toggle="tooltip" title="Submit the images after you have selected them" onclick="return ButtonClicked()"></div></div></div>
									<div class="clearfix"></div>
									<div id="buttonreplacement" style="margin-left:100px; display:none;">
										<img src="https://www.willmaster.com/images/preload.gif" alt="loading...">
									</div>
									</form:form>
									
									
						<div class="clearfix"></div>
					</div>
				</div>
				<c:if test="${adminPictureVOs2.size()>0}">
				<div class="inner-content" style="padding:35px;">
									<form:form action="setUpBackgroundImageSA" modelAttribute="AdminPictureVO" onclick="return validateId();">
							<div class="slide_docs">
								<div class="jcarousel-wrapper" data-step="5" data-intro="Select the desired image to be configured by clicking on the radio button and click on Configure Image">
								<input type="hidden" value="${eid}" name="eId"/>
								<input type="hidden" name="position" id="position" value="">
								<input type="hidden" id="imgIdofConfig" value="${adminPictureVOs2.size()}">
								<c:if test="${notConfiguredImage!=0}"><p class="subtext" style="margin-bottom: 10px;color: red;">You have not configured <b>${notConfiguredImage}</b> out of <b>${adminPictureVOs2.size()}</b> backgrounds</p></c:if>
									<div class="scroll_slider">
									<c:forEach items="${adminPictureVOs2}" var="picBackground" varStatus="loop" >
									<c:if test="${picBackground.updatedBy !=null}">
										            <div class="col-sm-6 col-md-3 ">
										            	<div class="grid_view_img radio_cross"><img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})">
										            	<%--<c:if test="${boothAdminLogin2.subId!=1}">--%>
										            	<span class="configImg" id="temp1"  onclick="deletEventPicture('${picBackground.picId}','${eid}')" <%--  onclick="deletEventPicture('${picBackground.picId}','${eid}')"  --%> >&#10005;</span>
									</div>
									  <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										           </div>
										    </c:if>
										    <c:if test="${picBackground.updatedBy ==null}">
										        <div class="col-sm-6 col-md-3 ">
										            <div class="grid_view_img radio_cross" style="border: 2px solid red;">
										            	<img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})">
										            	<%--<c:if test="${boothAdminLogin2.subId!=1}">--%>
										            	<span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span>
										            	<%--</c:if>--%>
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										        </div>
										    </c:if>
										    	</c:forEach>
										</div>
									<%-- <div class="jcarousel" style="padding-left:5px !important" id="slideshowContainer">
										<ul>
										<c:forEach items="${adminPictureVOs2}" var="picBackground" varStatus="loop" >
											<li>
												<div class="thumbnail_container" id="">
												<c:if test="${picBackground.imageHeight !=null}">
										            <div class="thumbnail" >
										              <div class="grid_view_img"><img src="${picBackground.picName}"><input type="radio" style="left: 10px;cursor: pointer; position: absolute; right: 5px; width: 25px;" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"> <span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></div>
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										         </c:if>
										         <c:if test="${picBackground.imageHeight ==null}">
										          <div class="thumbnail" style="border: 2px solid red;">
										              <div class="grid_view_img"><img src="${picBackground.picName}"><input type="radio" name="picId" style="left: 10px;cursor: pointer; position: absolute; right: 5px; width: 25px;" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"> <span id="crossId" class="configImg"  style="float:right;cursor:pointer;position:absolute;right:10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></div>
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										         </c:if>
										         </div>
											</li>
										</c:forEach>											
										</ul>
									</div>
									<a href="#" class="jcarousel-control-prev">&lsaquo;</a>
									<a href="#" class="jcarousel-control-next">&rsaquo;</a> --%>
									<!-- <p class="jcarousel-pagination"></p> -->
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="configure_img editconimg">
								<input type="submit" class="btn btn-green" id="submit" value="Edit Background"  data-toggle="tooltip" title="Select the desired image to be configured by clicking on the radio button and click on Configure Image">
							</div>
							</form:form>
				</div>
				</c:if>
				
				<div class="inner-content" style="padding:35px;">
					 <form:form action="updateWaterMarkLookAtTouchThankYouCameraScreen" modelAttribute="AdminPictureVO" onsubmit="return validateUpdateForm();" enctype="multipart/form-data" method="post">
							<input type="hidden" value="${eid}" name="eId"  id="eventId"/>
							<input type="hidden" name="selectedPreWaterMarkImage" id="selectedPreWaterMarkImage" value="0"/>
							<input type="hidden" name="selectedPreCameraTVScreenSaver" id="selectedPreCameraTVScreenSaver" value="0"/>
							<input type="hidden" name="selectedPreLookAtTouchScreen" id="selectedPreLookAtTouchScreen" value="0"/>
							<input type="hidden" name="selectedPreThankYouScreen" id="selectedPreThankYouScreen" value="0"/>
						
						
						
						<div class="img-prev-box">
						<c:forEach items="${setWaterMarkImageByEventId}" var="setPreFourBackground" varStatus="loop" >
							<div class="img-prev-container" id="wmPic">
								<c:if test="${not empty setPreFourBackground.waterMarkImage}">
								<img src="${setPreFourBackground.waterMarkImage}">
								</c:if>
							</div>
							<div id="reslwm">
							<div class="img-prev-container" id="waterdiv" style="display:none">
							<img class='event-img-bg' id="waterEvent" src=''/>
							</div></div>
							<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 10px;" onclick="openwaterMarkImage();">Choose Background Overlay From Library</a> -->
							</c:forEach>
								<div class="upload-imgs">
										<span style="font-weight: 600;">Background Overlay</span>	<input type="file" name="waterMarkImage" id="waterMarkImageUploadFile"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateWaterMarkImageList()" />
										<div class="clearfix"></div>
										</div>
						</div>
						
						<div class="img-prev-box">
						<c:forEach items="${setCameraTVScreenByEventId}" var="setPreFourBackground" varStatus="loop" >
							<div class="img-prev-container" id="ctvPic">
								<c:if test="${not empty setPreFourBackground.cameraTVScreenSaver}">
								<img src="${setPreFourBackground.cameraTVScreenSaver}">
								</c:if>
							</div>
							<div id="reslctv">
							<div class="img-prev-container" id="cameradiv" style="display:none">
							<img class='event-img-bg' id="cameraEvent" src=''/>
							</div>
							</div>
						<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 12px;" onclick="opencameraTVScreenSaver();">Choose Screensaver Form Library</a> -->
						</c:forEach>
						<div class="upload-imgs">
								         	<span style="font-weight: 600;">Screensaver</span><input type="file" name="cameraTVScreenSaver" id="cameraTVScreenSaverUploadFile"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateCameraTvScreenSaverList()" />
								        	<div class="clearfix"></div>
								         </div>
						</div>
						<div class="img-prev-box">
						<c:forEach items="${setLookAtTouchByEventId}" var="setPreFourBackground" varStatus="loop" >
							<div class="img-prev-container" id="latsPic">
								<c:if test="${not empty setPreFourBackground.lookAtTouchScreen}">
								<img src="${setPreFourBackground.lookAtTouchScreen}">
								</c:if>
							</div>
							<div id="resllats">
							<div class="img-prev-container" id="touchdiv" style="display:none">
							<img class='event-img-bg' id="touchEvent" src=''/>
							</div></div>
							<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 10px;" onclick="openlookAtTouchScreen();">Chooses Photos Complete From Library</a> -->
							</c:forEach>
							<div class="upload-imgs">
											<span style="font-weight: 600;">Photos Complete		</span><input type="file" name="lookAtTouchScreen" id="lookAtTouchScreenUploadFile"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updatelookAtTouchScreenList()" />
											<div class="clearfix"></div>
										</div>
						</div>
						<div class="img-prev-box">
						<c:forEach items="${setThankYouByEventId}" var="setPreFourBackground" varStatus="loop" >
							<div class="img-prev-container" id="tyPic">
								<c:if test="${not empty setPreFourBackground.thankYouScreen}">
								<img src="${setPreFourBackground.thankYouScreen}">
								</c:if>
							</div>
							<div id="reslty">
							<div class="img-prev-container" id="thankyoudiv" style="display:none">
							<img class='event-img-bg' id="thankyouPic" src=''/>
							</div></div>
							<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 12px;"  onclick="openThankYouScreen();">Choose End Session From Library</a> -->
							</c:forEach>
							<div class="upload-imgs">
											<span style="font-weight: 600;">	End of Session </span><input type="file" name="thankyoufiles" id="thankyoufilesUploadFile"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateThankYouList()" />
											<div class="clearfix"></div>
										</div>
						</div>
						<div class="clearfix"></div>
						
						<div class="upload-imgs">
							<div class="form-row upload-screens">
									
									
									
										
										
										
									
										
										
										
								         
								         
										<div class="clearfix"></div>
									</div>
									<input type="reset" value="Reset" class="btn btn-green" id="submitsss" style="width:125px;margin-right: 10px;padding:10px 0px 10px 0px;display:none;float: left;" onClick="window.location.reload()"/>
							<input type="submit" value="Save Changes" class="btn btn-green" id="submitss" style="width:auto;display:none;"/>
						
						</div>
						
						</form:form>
					</div>
			</div>
		<div class="clearfix"></div>
		<style>
		.grid-img{
		display:none;
		}
		</style>
		
 