<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/font-awesome.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
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
    $("#h").addClass("active_menu");
    
  
});
function is_valid1(){
	var val1=document.getElementById("otherIntractionTimout").value;
	if(val1==''){
		document.getElementById("otherIntractionTimout").value='';
	}else
	if(val1<1){
		document.getElementById("otherIntractionTimout").value="1";
	}
}
function is_valid2(){
	var val1=document.getElementById("otherCountdownDelay").value;
	if(val1==''){
		document.getElementById("otherCountdownDelay").value='';
	}else
	if(val1<1){
		document.getElementById("otherCountdownDelay").value="1";
	}
}
    
  function validateImage(){
	  var im=document.getElementById("imagessssss").value;
	  if(im ==null || im ==''){
		  alert("Please take test pictue then save and continue");
		  return false;
	  }  }
</script>
<style>
	.right-pannel{height:auto !important}
</style>


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
	.right-pannel{height:auto !important}
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


				<div class="right-pannel">
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left" >Green Screen Cropping</h1>
					<div class="clearfix"></div>
					<p class="subtext" style="margin-bottom:15px;">Once your Camera is mounted in its final position and the Green Screen is in place, Crop the edges so that only your Green Screen is visible.</p>
					
					
						 <form:form action="zoomScalePage" modelAttribute="SignInVO1" onsubmit="return formValidation();" >
					<div class="inner-content" style="padding:35px;margin-top:0px;">
					
   					<input type="hidden" id="imagessssss" value="${uploadImage.imageUrl}" />
						<div class="col-row col-md-9" style="padding:0px;" id="immggdiv">
							<div class="background-priview" style="width: 100%;border:none" data-toggle="tooltip" title="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
								<div class="background-image">
									<div class="img-container cropper-bg" id="target" data-step="2" data-intro="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
							          <c:if test="${empty uploadImage.imageUrl}"><img src="<%=request.getContextPath()%>/resources/img/testPicture.png" alt="Picture" id="imgNatural"></c:if>
							         <c:if test="${not empty uploadImage.imageUrl}"><img src="${uploadImage.imageUrl}/${boothAdminLogin.userId}/${uploadImage.imageName}" alt="Picture" id="imgNatural"></c:if>
							        </div>
    </div>
    </div>
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
							
							<input type="hidden"  id="x" name="imageX" value="${fovbyuser.imageX}"/>
							<input type="hidden"  id="y" name="imageY" value="${fovbyuser.imageY}"/>
							<input type="hidden"  id="h2" name="imageHeight" value="${fovbyuser.imageHeight}"/>
							<input type="hidden"  id="w" name="imageWidth" value="${fovbyuser.imageWidth}"/>
							<div class="" style="padding:0px !important;margin-top:25px">
								<div class="clearfix"></div>
							</div>
							<input type="hidden" value="${eventId}" name="EId"/>
						<div id="selectedDiv"> 
						<div class="cropping-section pull-left" style="margin-top:35px;width:56%;margin-right:2%">
							<h1>Camera Field Of View Cropping</h1>
							<div class="col-6 pull-left">
								<div class="form_row">
									<label>Top</label><br>
									<input type="text" class="text-medium" name="fovTop" id="fovTop" value="${fovbyuser.fovTop}" readonly="readonly">&nbsp;<b>%</b> <span style="color:red" id="fovTopSpan" ></span>
								</div>
								<div class="form_row">
									<label>Left</label><br>
									<input type="text" class="text-medium" name="fovLeft" id="fovLeft" value="${fovbyuser.fovLeft}" readonly="readonly">&nbsp;<b>%</b>  <span style="color:red" id="fovLeftSpan"></span>
								</div>
							</div>
							<div class="col-6 pull-right">
								<div class="form_row">
									<label>Bottom</label><br>
									<input type="text" class="text-medium" name="fovBottom" id="fovBottom" value="${fovbyuser.fovBottom}" readonly="readonly">&nbsp;<b>%</b>  <span style="color:red" id="fovBottomSpan"></span>
								</div>
								<div class="form_row">
									<label>Right</label><br>
									<input type="text" class="text-medium" name="fovRight" id="fovRight" value="${fovbyuser.fovRight}" readonly="readonly">&nbsp;<b>%</b>  <span style="color:red" id="fovRightSpan"></span>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-5 cropping-section both-section" style="margin-top:35px;min-height:228px;">
							<h1>Others</h1>
							<div class="form_row" style="padding-top:15px;">
								<div class="form_label" style="width:45%">Interaction Timeout</div>
								<div class="form_element"  style="width:54%"><input type="text" class="text-medium" name="otherIntractionTimout" id="otherIntractionTimout" value="${fovbyuser.otherInstructionTimeout}"  maxlength="4" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" onkeyup="is_valid1(this);" placeholder="only 0.50 is allow"></div> <span style="color:red" id="otherIntractionTimoutSpan"></span>
								<p class="subtext"  style="font-size: 12px;">interaction timeout delay is the time taken for getting ready to picture</p>
								<div class="clearfix"></div>
							</div>
							<div class="form_row">
								<div class="form_label" style="width:45%">Countdown Delay</div>
								<div class="form_element" style="width:54%"><input type="text" class="text-medium" name="otherCountdownDelay" id="otherCountdownDelay" value="${fovbyuser.othrtCountDelay}" maxlength="4" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" onkeyup="is_valid2(this);" placeholder="only 0.50 is allow"></div> <span style="color:red" id="otherCountdownDelaySpan"></span>
								<p class="subtext" style="font-size: 12px;">countdown delay is the time between countdown timer for taking picture</p>
								<div class="clearfix"></div>
							</div>							
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
						<input type="submit" value="Save" class="btn btn-green" style="width:auto;margin-top:15px;" data-toggle="tooltip" title="You can update and send the RGB values as a push notification the synced devices for the ongoing event" onclick="return validateImage()" />
						</div>
						
						<div class="clearfix"></div>
					</div>
					<div class="blank_line"></div>
					</form:form>
				</div>
				<div class="clearfix"></div>		
	
  <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/cropper/cropper.js"></script>
  <script src="<%=request.getContextPath()%>/resources/cropper/main.js"></script>
