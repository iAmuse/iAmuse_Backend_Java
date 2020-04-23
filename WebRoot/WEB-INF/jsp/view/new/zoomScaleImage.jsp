<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>

  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/font-awesome.min.css">
  <!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"> -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/config/cropper/cropper.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/config/cropper/main.css">

<script>
$(document).ready(function() {
	$("#e").addClass("active");
	$("#p").addClass("active_menu");
	
	setTimeout(myFunction, 2000)
	function myFunction(){
	var zmlelelcccc=document.getElementById("zScale").value;
	if(zmlelelcccc==0.50 || zmlelelcccc==0.5){
		var name = document.getElementById("call3").src;
		 $("#cropperImage").attr("src", name);
		 $('#thi').addClass("current-img");
		 $('#sec').removeClass("current-img");
		 $('#fir').removeClass("current-img");
	}else if(zmlelelcccc==0.75){
		var name = document.getElementById("call2").src;
		 $("#cropperImage").attr("src", name);
		 $('#sec').addClass("current-img");
		 $('#fir').removeClass("current-img");
		 $('#thi').removeClass("current-img");
	}else if(zmlelelcccc==1.00){
		var name = document.getElementById("call1").src;
		 $("#cropperImage").attr("src", name);
		 $('#fir').addClass("current-img");
		 $('#sec').removeClass("current-img");
		 $('#thi').removeClass("current-img");
	}
	}
	
});
function validateFov(){
	var zcal=document.getElementById("zScale").value;
	var seleczcal=document.getElementById("zoom").value;
	if(zcal==seleczcal){
		return true;
	}else{
		var r=confirm("Existing Events that do not match your Booth Zoom Profile will be unavailable/disabled. Is this OK?");
		 if(r==true){
			 return true;
		 }else{
			 return false;
		 }
	}
}


function cll1()
{
 var name = document.getElementById("call1").src;
 $("#cropperImage").attr("src", name);
 $("#zScale").val("1.00");
 $('#fir').addClass("current-img");
 $('#sec').removeClass("current-img");
 $('#thi').removeClass("current-img");
}
function cll2()
{
 var name = document.getElementById("call2").src;
 $("#zScale").val(0.75);
 $("#cropperImage").attr("src", name);
 $('#sec').addClass("current-img");
 $('#fir').removeClass("current-img");
 $('#thi').removeClass("current-img");
}
function cll3()
{
 var name = document.getElementById("call3").src;
 $("#zScale").val(0.50);
 $("#cropperImage").attr("src", name);
 $('#thi').addClass("current-img");
 $('#sec').removeClass("current-img");
 $('#fir').removeClass("current-img");
}
</script>
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
/*.left-pannel{
          min-height: 716px !important;
      }*/
</style>



		<div class="col-lg-10 col-sm-9 col-xs-7 col-md-9 right-pannel">
		<!-- <a href="boothSetUpByEventConfig"><button class="btn btn-green pull-right" style="margin-left:10px;">Refresh</button></a>
		<a href="getImagePushAdminCurrentConfig"><button class="btn btn-green pull-right" style="margin-left:10px;">Take Test Picture</button></a> -->
					<div class="col-lg-8">
					<h1 class="heading pull-left" style="margin-bottom:0px;">Choose Zoom Profile</h1>
					<div class="clearfix"></div>
					<p class="sub-heading">The Zoom Profile of your Booth refers to how close Guests are to the Camera.  If a Guest is close to Camera, only their upper body is visible vs. if they are farther away from Camera, choose Zoomed Out profile.</p>
					<p class="sub-heading" style="margin-bottom:25px;">The Zoom Profile affects which Backgrounds are available/will look good at your Event, since Backgrounds are setup to only work with a single Zoom Profile.</p>
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>	</div>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;margin-top:0px;">
					 
    <div class="col-lg-12" style="border: 1px solid #000;background:#fff;margin-bottom: 60px;">
					<div class="col-lg-8" >
							<div class="background-priview" style="width: 100%;border:none" data-toggle="tooltip" title="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
								<div class="background-image">
									<div class="img-container cropper-bg" id="target" data-step="2" data-intro="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
							          <c:if test="${empty uploadImage.imageUrl}"><img src="<%=request.getContextPath()%>/resources/img/testPicture.png" alt="Picture" id="imgNatural"></c:if>
							         <c:if test="${not empty uploadImage.imageUrl}"><img src="${uploadImage.imageUrl}/${boothAdminLogin2.userId}/${uploadImage.imageName}" alt="Picture" id="imgNatural"></c:if>
							        </div>
    </div>
    </div>
     </div>
    			 <div class="col-lg-4 docs-toggles" style="text-align:center">
    			 <form:form action="setZoomProfilePage" modelAttribute="SignInVO" onsubmit="return validateFov();">
   <div class="img-crop" >
	<input type="hidden" id="zScale" readonly="readonly" value="${fovbyuser.zoomScale}" name="zoomScale">
	<input type="hidden" id="zoom" readonly="readonly" value="${fovbyuser.zoomScale}" >
	<div class="row" id="actions">
	 
											        <div class="btn-group docs-aspect-ratios" data-toggle="buttons">
											          <div class="croped-img" id="fir">
											          <label class="btn btn-primary">
											            <span class="docs-tooltip" onclick="cll1();" data-toggle="tooltip" >
											             <img  id="call1" src="<%=request.getContextPath()%>/resources/images/images/zoom_profile1.png">
											            </span>
											          </label>
														<span>Zoomed In</span>											          
											          </div>
											          <div class="croped-img" id="sec">
											          <label class="btn btn-primary">
											            <span class="docs-tooltip" onclick="cll2();" data-toggle="tooltip" >
											             <img  id="call2" src="<%=request.getContextPath()%>/resources/images/images/zoom_profile2.png">
											            </span>
											          </label>
											          <span>Medium Zoom</span>
											          </div>
											          <div class="croped-img" id="thi">
											          <label class="btn btn-primary">
											            <span class="docs-tooltip" onclick="cll3();" data-toggle="tooltip">
											           	<img  id="call3" src="<%=request.getContextPath()%>/resources/images/images/zoom_profile3.png">
											            </span>
											          </label>
											          <span>Zoomed Out</span>
											          </div>
											          </div>
											      </div>
											      <div class="clearfix"></div>
											    </div>	
										<div class="clearfix"></div>
										<input type="submit" value="Save" name="save" class="btn btn-green " style="width:40%;font-size:16px;" >	    							
								
								
 							</form:form>
 							</div>
          <!-- <a href="create-event-config.html">
<button style="float: right;margin-top: 10px;" class="btn btn-green">Next</button>
</a> -->
	        
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
            <input type="text"  id="x" name="imageX"  value="${fovbyuser.imageX}"/>
			<input type="text"  id="y" name="imageY" value="${fovbyuser.imageY}"/>
			<input type="text"  id="h2" name="imageHeight" value="${fovbyuser.imageHeight}"/>
			<input type="text"  id="w" name="imageWidth" value="${fovbyuser.imageWidth}"/>
        </div>
    </div>				
  		
   
					</div>
					<div class="clearfix"></div>
				
				<div class="clearfix"></div>
				
  <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
  <!-- <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script> -->
  <script src="<%=request.getContextPath()%>/resources/config/cropper/cropper.js"></script>
  <script src="<%=request.getContextPath()%>/resources/config/cropper/main.js"></script>
  <script src="<%=request.getContextPath()%>/resources/config/BoothSetupByEvent.js"></script>
  
