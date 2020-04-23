<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	
	
	
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/font-awesome.min.css">
  <!--  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">-->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cropper/tether.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cropper/cropper.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/cropper/main.css">
  
<script>
$(document).ready(function() {
	  $('#effect').delay(9000).fadeOut(400);
  });
$( document ).ready(function() {
	 var x = document.getElementById("imgNatural").naturalWidth;
   	 var x1 = document.getElementById("imgNatural").naturalHeight;
     document.getElementById("imgNaturalWidth").value = x;
     document.getElementById("imgNaturalHeight").value = x1;
 	});
</script>

<%   
	 String ils = (String)request.getAttribute("imageListSize"); 
	  String ilus = (String)request.getAttribute("imageListUpdatedSize");
%>

<%   
	 String id = (String)request.getAttribute("id"); 
     System.out.println("jcdsahdsjkhdhhghcvdsauy vcdsajhgdsac"+id);
    request.setAttribute("id",id); 
     
%>

<script type="text/javascript">

$(document).ready(function() {
	 $('#effect').delay(5000).fadeOut(400);
    $("#e").addClass("active_menu");
});
var tid = setInterval(mycode, 15000);
var tid1 = setInterval(mycode1, 5000);

function mycode() {
var id=	document.getElementById("eidnumber").value;
      $.ajax({
            url : 'autoRefreshFov',
            data:{"eventId": id },
            success : function(data) {
                $('#result').html(data);
            }
        });
        }
         
    function mycode1() { 
                  
          dwrService.getStatus(1,function(data) { 
    
     if(data==1)
   {
   var r = confirm("Image recieved press ok to refresh");
   if (r == true) {
  
   window.location="<%=request.getContextPath()%>/autoRefreshFov"
   
     }
   }  
  });
}
</script>
<style>
	/*.right-pannel{height:auto !important}*/
</style>

<%-- <script type="text/javascript">
function clearDevicePictures(){
	window.location.href ="<%=request.getContextPath()%>/clearDevicePictures";
}
$(function () {
	window.onload=$("#selectedDiv").hide();
    $("#eventId").change(function () {
        var selectedEventName = $(this).find("option:selected").text();
        var selectedEventId = $(this).val();
        console.log("selectedEventName: " + selectedEventName + " and selectedEventId: " + selectedEventId);
       $("#selectedDiv").show();
       callAjaxMethod(selectedEventName,selectedEventId);
});
});
</script>
<script>
function callAjaxMethod(selectedEventName,selectedEventId){
$.ajax({
    type: "GET",
    url: "getFOVValueBasedOnEvent",
    data: { selectedEventName: selectedEventName, selectedEventId: selectedEventId} ,
    async:false,
   
    success: function(response) {
         $('#fovTop').val(response.fovTop);
         $('#fovLeft').val(response.fovLeft);
         $('#fovBottom').val(response.fovBottom);
         $('#fovRight').val(response.fovRight);
         $('#greenScreenWidth').val(response.greenScreenWidth);
         $('#greenScreenDistance').val(response.greenScreenDistance);
         $('#greenScreenHeight').val(response.greenScreenHeight);
         $('#greenScreenCountdownDelay').val(response.greenScreenCountdownDelay);
         $('#otherIntractionTimout').val(response.otherIntractionTimout);
         $('#otherCountdownDelay').val(response.otherCountdownDelay);
         
}
});
}
</script>
 <script type="text/javascript">
      $(document).ready(function() {
            $("#e").addClass("active_menu");
        });
      
      function sentTestEmail(){
    	  var email=document.getElementById("emailId").value;
    	  window.location.href ="<%=request.getContextPath()%>/sendTestMail?email="+email;
      }
      
      function div_show() {
    	  document.getElementById('abc').style.display = "block";
    	  }
      
      function check_empty() {
    	  if (document.getElementById('name').value == "" || document.getElementById('email').value == "" || document.getElementById('msg').value == "") {
    	  alert("Fill All Fields !");
    	  } else {
    	  document.getElementById('form').submit();
    	  alert("Form Submitted Successfully...");
    	  }
    	  }
    	  //Function To Display Popup
    	  function div_show() {
    	  document.getElementById('abc').style.display = "block";
    	  }
    	  //Function to Hide Popup
    	  function div_hide(){
    	  document.getElementById('abc').style.display = "none";
    	  }
</script> --%>
<style>
/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

#abc {
width:100%;
height:100%;
opacity:.95;
top:0;
left:0;
display:none;
position:fixed;
background-color:#313131;
overflow:auto;
z-index: 1;
}

div#popupContact {
    background: #fff;
    position: absolute;
    left: 50%;
    top: 17%;
    margin-left: -172px;
    font-family: 'Raleway',sans-serif;
    width: 29%;
    height: 30%;
}

</style>
<style>
	/*.right-pannel{height:auto !important}*/
	.left-pannel{min-height: 1100px !important;}
</style>

<div id="abc">
<!-- Popup Div Starts Here -->
<div id="popupContact">
<!-- Contact Us Form -->
<input id="emailId" name="name" placeholder="Name" value="${boothAdminLogin.emailId}" type="text" style="margin:39px 20px 30px 10px;width: 91%;">
<button onclick="sentTestEmail();" class="btn btn-green" style="float: right;margin-right: 10%;">send</button>
<button onclick="div_hide();" class="btn btn-green" style="float: right;margin-right:2%;">Cancel</button>
</div>
<!-- Popup Div Ends Here -->
</div>

<div class="col-lg-2" style="width:20%;"></div>
		<div class="right-pannel col-lg-10 col-xs-12 right-height">
		<!-- <a href="boothSetUpByEventConfig"><button class="btn btn-green pull-right" style="margin-left:10px;">Refresh</button></a>
		<a href="getImagePushAdminCurrentConfig"><button class="btn btn-green pull-right" style="margin-left:10px;">Take Test Picture</button></a> -->
						<!-- <a href="rgbSetup"><button class="btn btn-green pull-right">Refresh</button></a> -->
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left" >Green Screen Cropping</h1>
					<div class="clearfix"></div>
					<p class="subtext" style="margin-bottom:15px;">Once your Camera is mounted in its final position and the Green Screen is in place, Crop the edges so that only your Green Screen is visible.</p>
					
					<!-- <div class="pull-right" data-step="2" data-intro="You can update and send the RGB values as a push notification the synced devices for the ongoing event">
					<a href="rgbSetup"><button class="btn btn-green pull-right"  data-toggle="tooltip" title="You can update and send the RGB values as a push notification the synced devices for the ongoing event">Setup RGB</button></a>
					</div>
					<div class="pull-right" data-step="3" data-intro="You can test mail">
					<a href="#"><button class="btn btn-green pull-right" style="margin-right:10px;" id="popup" onclick="div_show()" data-toggle="tooltip" title="You can test mail">Test Email</button></a>
					</div>
					<div class="pull-right" data-step="4" data-intro="You can clear your device picture">
					<a href="#"><button class="btn btn-green pull-right" style="margin-right:10px;" onclick="clearDevicePictures()"  data-toggle="tooltip" title="You can clear your device picture">Clear Device Pictures</button></a>
					</div> -->
					<div class="clearfix"></div>
					
					<ul class="nav nav-tabs">
					    <li ><a  href="getRegisteredDeviceConfig">Step 1<br/>Register Devices</a></li>
					    <li ><a  href="boothSetUpByEventConfigFirst" >Step 2<br/>Setup Booth</a></li>
					    <li class="active"><a href="#" style="background-color: #05a42e ! important;color: #ffffff  ! important;">Step 3<br/>Setup Events</a></li>
					   <!--  <li><a href="#">Step 4<br/>Booth Zoom Profile</a></li> -->
					  </ul>
					<div class="inner-content" style="padding:35px;margin-top:0px;">
					 <%-- <div class="container">
    <div class="row">
      <div class="col-md-9">
        <div class="img-container">
          <img src="${uploadImage.imageUrl}/${boothAdminLogin2.userId}/${uploadImage.imageName}" alt="Picture" id="imgNatural">
        </div>
      </div>
     
        <div class="docs-data" style="width:300px; ">
            <input type="text" class="form-control" id="dataX" placeholder="x">
            <input type="text" class="form-control" id="dataY" placeholder="y">
            <input type="text" class="form-control" id="dataRight" placeholder="Right">
            <input type="text" class="form-control" id="dataBottom" placeholder="Bottom">
            <input type="text" class="form-control" id="dataWidth" placeholder="width">
            <input type="text" class="form-control" id="dataHeight" placeholder="height">
            <input type="text" class="form-control" id="imgNaturalWidth" placeholder="imgNaturalWidth">
            <input type="text" class="form-control" id="imgNaturalHeight" placeholder="imgNaturalHeight">
        </div>
    </div>				
   </div>	 --%>
						<div class="col-row col-md-9" style="padding:0px;">
							<div class="background-priview" style="width: 100%;border:none;" data-toggle="tooltip" title="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
								<div class="background-image">
									<div class="img-container cropper-bg" id="target" data-step="2" data-intro="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
							         <c:if test="${empty uploadImage.imageUrl}"><img src="<%=request.getContextPath()%>/resources/img/testPicture.png" alt="Picture" id="imgNatural"></c:if>
							         <c:if test="${not empty uploadImage.imageUrl}"><img src="${uploadImage.imageUrl}/${boothAdminLogin2.userId}/${uploadImage.imageName}" alt="Picture" id="imgNatural"></c:if>
							        </div>
    </div>
    </div>
    <div class="clearfix"></div>
     </div>
     
     
        	<div class="docs-data" style="width:300px; display:none;">
            <input type="text" class="form-control" id="dataX" placeholder="x">
            <input type="text" class="form-control" id="dataY" placeholder="y">
            <input type="text" class="form-control" id="dataRight" placeholder="Right">
            <input type="text" class="form-control" id="dataBottom" placeholder="Bottom">
            <input type="text" class="form-control" id="dataWidth" placeholder="width">
            <input type="text" class="form-control" id="dataHeight" placeholder="height">
            <input type="text" class="form-control" id="imgNaturalWidth" placeholder="imgNaturalWidth">
            <input type="text" class="form-control" id="imgNaturalHeight" placeholder="imgNaturalHeight">
             <input type="text" class="form-control" id="imgWidth" placeholder="imgWidth">
            <input type="text" class="form-control" id="imgHeight" placeholder="imgHeight">
        </div>
					


					
						<form:form action="create-event-config.html" modelAttribute="SignInVO1" onsubmit="return formValidation();" >
							
							<input type="hidden"  id="x" name="imageX" value="${fovbyuser.imageX}"/>
							<input type="hidden"  id="y" name="imageY" value="${fovbyuser.imageY}"/>
							<input type="hidden"  id="h2" name="imageHeight" value="${fovbyuser.imageHeight}"/>
							<input type="hidden"  id="w" name="imageWidth" value="${fovbyuser.imageWidth}"/>
							<div class="" style="padding:0px !important;margin-top:25px">
								<!-- <h1 class="heading pull-left">Select Event For Advance Booth Setup</h1> -->								
								<%-- <div style="width:215px;padding:6px;float:left;margin-top:-5px;margin-left:20px;" data-step="1" data-intro="These are the camera settings for your ongoing event">
								<select id="eventId" name="EId" style="width:215px;padding:6px;float:left;margin-top:-5px;margin-left:20px;" required="required">
									<option value="" selected="selected">--Select Event--</option>
									<c:forEach items="${eventList}" var="item" varStatus="loop">
									<option value="${item.EId}" >${item.eventName}</option>
									</c:forEach>
								</select>
								</div> --%>
								<div class="clearfix"></div>
							</div>
							<input type="hidden" value="${eventId}" name="EId"/>
							<input type="hidden" id="imagessssss" value="${uploadImage.imageUrl}" />
						<div id="selectedDiv"> 
						<div class="cropping-section cropping-field-md pull-left" style="margin-top:35px;margin-right:2%;border: 1px solid #000;">
							<h1>Camera Field Of View Cropping</h1>
							<div class="col-6 pull-left">
								<div class="form_row field-view-crop">
									<label>Top</label><br>
									<%-- <input type="text" class="text-medium" name="fovTop" id="fovTop" value="${boothAdminLogin.fovTop}"> <span style="color:red" id="fovTopSpan"></span> --%>
									<input type="text" class="" name="fovTop" id="fovTop" value="${fovbyuser.fovTop}"  readonly="readonly"><b>%</b> <span style="color:red" id="fovTopSpan"></span>
								</div>
								<div class="form_row field-view-crop">
									<label>Left</label><br>
									<input type="text" class="" name="fovLeft" id="fovLeft" value="${fovbyuser.fovLeft}"  readonly="readonly">&nbsp;<b>%</b>  <span style="color:red" id="fovLeftSpan"></span>
									<%-- <input type="text" class="text-medium" name="fovLeft" id="fovLeft" value="${boothAdminLogin.fovLeft}"> <span style="color:red" id="fovLeftSpan"></span> --%>
								</div>
							</div>
							<div class="col-6 pull-right">
								<div class="form_row field-view-crop">
									<label>Bottom</label><br>
									<%-- <input type="text" class="text-medium" name="fovBottom" id="fovBottom" value="${boothAdminLogin.fovBottom}"> <span style="color:red" id="fovBottomSpan"></span> --%>
									<input type="text" class="" name="fovBottom" id="fovBottom" value="${fovbyuser.fovBottom}"  readonly="readonly">&nbsp;<b>%</b>  <span style="color:red" id="fovBottomSpan"></span>
								</div>
								<div class="form_row field-view-crop">
									<label>Right</label><br>
									<input type="text" class="" name="fovRight" id="fovRight" value="${fovbyuser.fovRight}"  readonly="readonly">&nbsp;<b>%</b>  <span style="color:red" id="fovRightSpan"></span>
									<%-- <input type="text" class="text-medium" name="fovRight" id="fovRight" value="${boothAdminLogin.fovRight}"> <span style="color:red" id="fovRightSpan"></span> --%>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						<%-- <div class="cropping-section pull-right" style="margin-top:30px;">
							<h1>Green Screen</h1>
							<div class="col-6 pull-left">
								<div class="form_row">
									<label>Screen Width (m)</label><br>
									<input type="text" class="text-medium" name="greenScreenWidth"  id="greenScreenWidth" value="${fovbyuser.greenScreenWidth}"> <span style="color:red" id="greenScreenWidthSpan"></span>
									<input type="text" class="text-medium" name="greenScreenWidth" id="greenScreenWidth" value="${boothAdminLogin.greenScreenWidth}"> <span style="color:red" id="greenScreenWidthSpan"></span>
								</div>
								<div class="form_row">
									<label>Screen Height (m)</label><br>
									<input type="text" class="text-medium" name="greenScreenDistance"  id="greenScreenDistance" value="${ fovbyuser.greenScreenDistance}"> <span style="color:red" id="greenScreenDistanceSpan"></span>
									<input type="text" class="text-medium" name="greenScreenWidth" id="greenScreenWidth" value="${boothAdminLogin.greenScreenWidth}"> <span style="color:red" id="greenScreenWidthSpan"></span>
								</div>
							</div>
							<div class="col-6 pull-right">
								<div class="form_row">
									<label>Target Distance (m)</label><br>
									<input type="text" class="text-medium" name="greenScreenHeight" id="greenScreenHeight" value="${fovbyuser.greenScreenHeight}" > <span style="color:red" id="greenScreenHeightSpan"></span>
									<input type="text" class="text-medium" name="greenScreenHeight" id="greenScreenHeight" value="${boothAdminLogin.greenScreenHeight}"> <span style="color:red" id="greenScreenHeightSpan"></span>
								</div>
								<div class="form_row">
									<label>Countdown Delay (sec)</label><br>
									<input type="text" class="text-medium" name="greenScreenCountdownDelay"id="greenScreenCountdownDelay" value="${fovbyuser.greenScreenCountDownDelay}"> <span style="color:red" id="greenScreenCountdownDelaySpan"></span>
									<input type="text" class="text-medium" name="greenScreenCountdownDelay" id="greenScreenCountdownDelay" value="${boothAdminLogin.greenScreenCountdownDelay}"> <span style="color:red" id="greenScreenCountdownDelaySpan"></span>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>--%>
						<div class="col-md-5 cropping-section both-section" style="margin-top:35px;min-height:288px;border: 1px solid #000;">
							<h1 style="margin-bottom:15px;">Others</h1>
							<div class="form_row" >
								<div class="form_label"  style="width:100%">Interaction Timeout</div>
								<div class="form_element interfield"  style="/*width:54%;border: 1px solid #c8c8c8;*/"><input type="text" class="" name="otherIntractionTimout" id="otherIntractionTimout" value="${fovbyuser.otherInstructionTimeout}"></div> <span style="color:red" id="otherIntractionTimoutSpan"></span>
								<div class="clearfix"></div>
							</div>
							<div class="form_row">
								<%-- <div class="form_element " ><input type="text" class="text-medium" name="otherIntractionTimout" id="otherIntractionTimout" value="${boothAdminLogin.otherIntractionTimout}"></div> <span style="color:red" id="otherIntractionTimoutSpan"></span> --%>
								<div class="form_label"  style="width:100%">Countdown Delay</div>
								<div class="form_element interfield"  style="*/*width:54%;border: 1px solid #c8c8c8;*/"><input type="text" class="" name="otherCountdownDelay" id="otherCountdownDelay" value="${fovbyuser.othrtCountDelay}"  ></div> <span style="color:red" id="otherCountdownDelaySpan"></span>
								<%-- <div class="form_element"><input type="text" class="text-medium" name="otherCountdownDelay" id="otherCountdownDelay" value="${boothAdminLogin.otherCountdownDelay}"></div> <span style="color:red" id="otherCountdownDelaySpan"></span> --%>
								<div class="clearfix"></div> 
							</div>							
							<div class="clearfix"></div>
						</div> </div>
						<div class="clearfix"></div>
						<input type="submit" value="Next" class="btn btn-green finibtn" data-toggle="tooltip" title="You can update and send the RGB values as a push notification the synced devices for the ongoing event"/>
						
						</form:form>
						<div class="clearfix"></div>
					</div>
					<div class="blank_line"></div>
					
				</div>
				<div class="clearfix"></div>		
	</div>			
  <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
  <!--  <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>-->
  <script src="<%=request.getContextPath()%>/resources/cropper/common.js"></script>
  <script src="<%=request.getContextPath()%>/resources/cropper/tether.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/cropper/cropper.js"></script>
  <script src="<%=request.getContextPath()%>/resources/cropper/main.js"></script>
