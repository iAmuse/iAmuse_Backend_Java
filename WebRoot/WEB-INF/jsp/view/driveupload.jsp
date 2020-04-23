 <%@include file="/WEB-INF/jsp/include/taglibs.jsp"%> 
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/upload.css">
<script src="<%=request.getContextPath()%>/resources/js/upload.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>

<%-- <script src="<%=request.getContextPath()%>/resources/js/oneDriveToken.js"></script> --%>
<script src="https://apis.google.com/js/platform.js" async defer></script>
   <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
 <script type='text/javascript' src='http://code.jquery.com/jquery.min.js'></script>	
 <style>



.message {
	padding-top: 10;
	font-size: 25px;
	font-style: bold;
}

.firstbloack {
	background-color: white;
	background-size: 300px;
	font-size: 25px;
	height: 250px;
	margin-left: 12px;
	margin-top: 29px;
	border:1px solid #000;
}
/* img {
    max-height: 100px;
    max-width: 150px;
    border: none;
} */


.secondblock {
	padding-top: 10;
	background-size: 300px;
margin-top: 5px;
background-color: white;
	height: 250px;
}

.drive-top{
	margin-top:20px;
}
.drive-left{
	margin-left:15px;
}

.image1 {
    margin-top: 87px;
    margin-left : 34px;
}
.p{
    padding: 50px;
    font-size: 14px;
}
.left-pannel{
min-height: 750px !important;
}
.photo-class{
 margin-top:30px;
}

.loggedphoto-class{
margin-top:29px;
}
</style>

<div class="col-lg-2 col-md-3" style="width:20%;"></div>
<div class="right-pannel col-lg-10 col-xs-12 right-height">
		<div class="message heading">You are currently not syncing with a cloud storage.Choose from the below
		available storage accounts.</div>
<div class="firstbloack">

<!-- <div id="message"> You have currently not linked your cloud storage accounts</div> -->
	
		<%-- <button id="login" class="btn btn-green"><img src="<%=request.getContextPath()%>/resources/images/images/googlephoto.png"></button> --%>
		<%-- <img src="<%=request.getContextPath()%>/resources/images/images/googlephoto.png"> --%>
		<p style="font-size:12px" class="sub-heading pull-left booth-desc">Need help?  Check our support articles on <a href="https://support.iamuse.com/portal/kb/articles/sync-iamuse-photo-booth-event-pictures-to-google-photos" target="_blank"/>Syncing iAmuse Event Photos to Google Photos</a>
		    and/or <a href="https://support.iamuse.com/portal/kb/articles/sync-photo-booth-event-pictures-with-microsoft-onedrive" target="_blank"/>Syncing iAmuse Event Photos with Microsoft OneDrive</a>.</p>
		<div  align="center">
		<div class="row justify-content-start">
		<div id="main-storepage">
		<div class="col-lg-12">
		<div class="col-lg-3"></div>
			<div class="col-lg-3 photo-class" >
			<div id="div_google_photos">
				<%-- <a href="<%=request.getContextPath()%>/resources/images/images/googlephoto.jpg" id="login"></a> --%>
				 <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/resources/images/images/GooglephotosButton.png" class="redirectToGooglePhotos"  id="login" onClick="signIn();" /></a> 
				  </div>
				  </div>
				  <div class="col-lg-3 photo-class">
				  <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/resources/images/images/OneDriveButton.png" class="redirectToOneDrive" id="Ologin" onClick="OsignIn();" /></a>
				</div>
			<div class="col-lg-3"></div>
			</div></div>
			
			<div id="redirect-div" hidden="true">
			<div class="col-lg-12"  style="text-align:left;">
			<div class="col-lg-12">
			<div class="col-lg-12 loggedphoto-class">
			<div id="div_google_photos">
				<%-- <a href="<%=request.getContextPath()%>/resources/images/images/googlephoto.jpg" id="login"></a> --%>
				<label id="txt_googlephoto" style=" text-align: center" ></label>
				 <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/resources/images/images/GooglephotosButton.png"  class="redirectToGooglePhotos"  onClick="signIn();" /></a> 
				  </div>
				  </div><div class="col-lg-3"></div></div>
				  <div class="col-lg-12">
				  <div class="col-lg-9 loggedphoto-class">
				  <label id="txt_onedrive" style="text-align: center" ></label>
				  <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/resources/images/images/OneDriveButton.png" class="redirectToOneDrive" onClick="OsignIn();" /></a>
				</div>
			<div class="col-lg-3"></div></div>
			</div>
			</div>
			
			<%--  <div class="col-md-12">	
				<!-- <button id="login" class="btn btn-green">link</button> -->
				<button id="unLinkGoogle" class="btn btn-danger" onClick="deleteAccessToken(${driveupload.userId})">Unlink</button>
			</div> --%>
			</div>
		</div>
	<%-- 	<div class="image1">
		<!-- <div class="col-lg-9"> -->
			<div class="row drive-top" >
				<div class="col-md-12" style="margin-top: -66px;" id="div_ondrive_photo">
					
				</div>
				<div class="col-md-12">
					<button id="Ologin" class="btn btn-green">link</button> 	
					<button id="unlinkOlogin" class="btn btn-danger"  onClick="deleteAccessToken(${driveupload.userId})">Unlink</button>
				</div>	
				
			</div>			
	<!-- 	</div> -->
		</div> --%>
		<div class="col-md-12">
					<input type="hidden" value="${driveupload.userId}" id="userId"/>
		</div>
		</div>
		</div>
		<div> 
			<!-- <div class="message" ></div> -->
			 <!-- <div id="txt_message" style="margin-top: 86px; text-align: center" >You have currently not linked your cloud storage accounts</div> --> 
		</div>
		
		<%-- <div id="div1">Img1:
    <img id="img1" src="<%=request.getContextPath()%>/resources/images/images/onedrive.jpg" onclick="yourFunction();" />
</div>
<div id="div2">txt1:
    <input type="text" id="txt1" />
</div>
<input type="button" value="Disable It" onclick="disableit();" />
<input type="button" value="Enable It" onclick="enableit();" /> --%>

<script>

$(document).ready(function() {
	
		if("${accessToken}"==="0")
		{
			$('#main-storepage').hide();
			$('#redirect-div').show();
			$('.firstbloack').css('height','330');
			$(".redirectToGooglePhotos").click(true).css('opacity',0.6);
			$(".redirectToOneDrive").click(false);
			$("#txt_googlephoto").text("You are syncing your photos with Google Photos ");
			$("#txt_googlephoto").append("<a href='#' onClick='deleteAccessToken(${driveupload.userId})'>&nbsp;Click here to desync</a>");
				return false;
		}
		else if("${accessToken}"==="1")
		{
			$('#main-storepage').hide();
			$('#redirect-div').show();
			$('.firstbloack').css('height','330');
			$(".redirectToOneDrive").click(true).css('opacity',0.3);
			$(".redirectToGooglePhotos").click(false);			
			$("#txt_onedrive").text("You are syncing your photos with oneDrive");
			$("#txt_onedrive").append("<a href='#' onClick='deleteAccessToken(${driveupload.userId})'>&nbsp;Click here to desync</a>");
			return false;		
		}
		 else
		{
			 $('#redirect-div').hide();
			 $('#main-storepage').show();
			 
			
		}  
	}); 		



function deleteAccessToken(userId)
{
	var data = JSON.stringify({
  	  "userId":document.getElementById('userId').value
  	 });

  	 var xhr = new XMLHttpRequest();
  	 xhr.withCredentials = true;
  	 xhr.addEventListener("readystatechange", function () {
  	   if (this.readyState === 4) {
  		 window.location="driveupload";
  	   }
  	 });

  	 //xhr.open("POST", "http://localhost:8080/iamuse/removeaccesstoken");
  	 xhr.open("POST", "https://admin.iamuse.com/iamuse/removeaccesstoken");
  	 xhr.setRequestHeader("accept", "application/json");
  	 xhr.setRequestHeader("authorization", "DrEgBqmYbTXJqi2/a/H9O9YLYcRNjNTNn89BKpui1Y8");
  	 xhr.setRequestHeader("content-type", "application/json");
   	 xhr.send(data);
} 

</script> 
 


<%-- /* function deleteAccessToken(userId)
{
	debugger;
	var data = JSON.stringify({
  	  "userId":document.getElementById('userId').value
  	 });

  	 var xhr = new XMLHttpRequest();
  	 xhr.withCredentials = true;
	
  	 xhr.addEventListener("readystatechange", function () {
  	   if (this.readyState === 4) {
  		 window.location="driveupload";
  	   }
  	 });

  	 xhr.open("POST", "http://localhost:8080/iamuseserver_internal/v1/iamuse/removeaccesstoken");
  	 xhr.setRequestHeader("accept", "application/json");
  	 xhr.setRequestHeader("authorization", "DrEgBqmYbTXJqi2/a/H9O9YLYcRNjNTNn89BKpui1Y8");
  	 xhr.setRequestHeader("content-type", "application/json");
   	 xhr.send(data);
}
	$(document).ready(function() {
		if("${accessToken.flag}"==="0")
		{
			alert(message);
		}
		else if("${accessToken.flag}"==="1")
		{
			 $("#login").attr("disabled", true);
			 $("#unLinkGoogle").hide();
			 $("#Ologin").hide();
			 $("#unlinkOlogin").show();
		}
		else
			{
				$("#login").show();
				 $("#unLinkGoogle").hide();
				 $("#Ologin").show();
				 $("#unlinkOlogin").hide();
			}
	}); 

 */
 --%>


  <%-- 
  <%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/upload.css">
 <script src="<%=request.getContextPath()%>/resources/js/upload.js"></script>
 <script src="https://apis.google.com/js/platform.js" async defer></script>




<style>
	.right-pannel{height:auto !important}
	
abc {
  background-color: linen;
}

</style>
<div class="right-pannel">
<abc>
  <div>
       <input id="files" type="file" name="files[]" multiple/>
     <button id="upload">Upload</button>
     <div id="progress-wrp">
        <div class="progress-bar"></div>
        <div class="status">0%</div>
     </div>
  </div> 
 </abc>
   <div id="result"></div>
</div> 

 --%>

 