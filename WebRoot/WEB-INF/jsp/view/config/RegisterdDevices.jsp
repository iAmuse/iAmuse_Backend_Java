				<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script type="text/javascript">
      $(document).ready(function() {
    	  $('#effect').delay(9000).fadeOut(400);
            $("#a").addClass("active_menu");
            
            var sub1=document.getElementById("abcd1").value;
            var sub2=document.getElementById("abcd2").value;
            if(sub1.trim() !=null && sub1.trim() !=null && sub1 != sub2){
            	//sub1.style.color = '#00FF00';
            	//sub2.style.color = '#00FF00';
            	$(".wire").css("color", "Red");
            	alert("Please Check Your Device Configuration Wireless Network")
            } 
        });
      
      
      function clearDevicePictures(){
   		var r = confirm('Clear device will clear all the images which belongs to iAmuse application from your camera device gallery. Do you really want to do this?');
   		  if(r==true){
   		$.ajax({
   		  type: "GET",
   		  url: "clearDevicePictures",
   		  cache: false,
   		  success: function(data){
   		     alert("Clear Device Request Send Successfully");
   		     return false;
   		  }
   		});
   		  }
   		/* $("#a").removeClass("active_menu");
  		$("#q").removeClass("active_menu");
  		$("#h").removeClass("active_menu");
  		$("#d").removeClass("active_menu");
  		$("#p").removeClass("active_menu");
  		$("#i").removeClass("active_menu");
  		$("#g").addClass("active_menu"); */
  		
  	}  
      function sentTestEmail(){  
 		 document.getElementById("gif").style.display = "";
 	  var email=document.getElementById("emailId").value;
 		/* $("#a").removeClass("active_menu");
 		$("#d").removeClass("active_menu");
 		$("#q").removeClass("active_menu");
 		$("#h").removeClass("active_menu");
 		$("#p").removeClass("active_menu");
 		$("#g").removeClass("active_menu");
 		$("#i").addClass("active_menu"); */
 	//var id=	document.getElementById("eidnumber").value;
  $.ajax({
        url : 'sendTestMail',
        data:{"email": email },
        success : function(data) {
           if(data){
          	 document.getElementById("gif").style.display = "none";
          	 document.getElementById('abc').style.display = "none";
          	 setTimeout(function () { alert("Test Mail Send Successfully"); }, 2000);
          	 
           }else{
          	 document.getElementById("gif").style.display = "none";
          	 document.getElementById('abc').style.display = "none";
          	 setTimeout(function () { alert("Test Mail Send Failed"); }, 2000);
           }

        }
    });
 	  
 	  <%-- window.location.href ="<%=request.getContextPath()%>/sendTestMail?email="+email; --%>
   } 
</script>
<style>/*.right-pannel{height:auto !important;}*/
.left-pannel{
min-height: 817px !important;
}
</style>
<div id="gif" style="display: none;">
	<img src="https://www.willmaster.com/images/preload.gif">
</div>
<div id="abc">
<!-- Popup Div Starts Here -->
<div id="popupContact">
<!-- Contact Us Form -->
<center><h3 class="heading" style="font-size: 10px;padding:20px;">Confirm that you can receive emails from iAmuse by putting in your email below.</h3></center>
<input id="emailId" name="name" placeholder="Name" value="${boothAdminLogin2.emailId}" type="text" style="margin:0px 20px 11px 10px;width: 91%;">
<button onclick="sentTestEmail();" class="btn btn-green" style="float: right;margin-right:6%;margin-bottom: 15px;">send</button>
<button onclick="div_hide();" class="btn btn-green" style="float: right;margin-right:2%;">Cancel</button>
</div>
<!-- Popup Div Ends Here -->
</div>			
<div class="col-lg-2" style="width:20%;"></div>
					
					<div class="col-lg-10 col-xs-12 right-pannel right-height" data-step="1" data-intro="These devices appearing here would be the ones synced together and being used in the ongoing event">
					<div class="regdev">
					<div class="regtit">
					<h1 class="heading">Registered Devices</h1>
					<c:if test="${deviceRegistration.size()>=2}"><p class="subtext">These are the 2 Apple devices that are registered together as your Booth (1 Camera device + 1 Guest Touchscreen device)</p></c:if>
					<c:if test="${deviceRegistration.size()!=2}"><p class="subtext">Sign into each device using your iAmuse account, choosing Camera for 1 device and Guest Touchscreen for the other.  Hit Refresh to confirm registration of devices.</p></c:if>
					</div>
					<div class="regbtn">
					<a href="getRegisteredDeviceConfig"><button style="margin-top:8px;" class="btn btn-green refrbtn">Refresh</button></a>
					<a href="javascript:void(0);" class="btn btn-green clrdebtn" onclick="clearDevicePictures()" >Delete Event Photos</a>
					<a href="javascript:void(0);" class="btn btn-green tstembtn" id="popup" onclick="div_show()">Test Email</a>
					</div>
					</div>
					
					
					
					<c:if test="${deviceVO.touchDeviceIP!=null}">
					<center style="color:green;">
					<c:if test="${deviceVO.touchDeviceToken==null || deviceVO.touchDeviceToken==''}">Please On Push Notification Of Your Touch Device</c:if><br/>
					</center>
					</c:if>
					<c:if test="${deviceVO.cameraDeviceIP!=null}">
					<center style="color:green;">
					<c:if test="${deviceVO.cameraDeviceToken==null || deviceVO.cameraDeviceToken==''}">Please On Push Notification Of Your Camera Device</c:if>
					</center>
					</c:if>
					
					<ul class="nav nav-tabs">
					    <li class="active" ><a data-toggle="tab" href="#" style="background-color: #05a42e ! important;color: #ffffff  ! important;">Step 1<br/>Register Devices</a></li>
					    <li><a data-toggle="tab" href="#">Step 2<br/>Setup Booth</a></li>
					    <li><a data-toggle="tab" href="#">Step 3<br/>Setup Events</a></li>
					  </ul>
					<c:if test="${deviceRegistration.size() > 0}">
					<div class="col-lg-12 col-md-12 col-sm-12" style="margin-left: 2px;width: 96%; margin-bottom: 30px;">
					<div class="inner-content" style="border: 1px solid #000;background:#fff">
						<div class="col-row">
							<div class="col-3" style="width:28%">
								<h2>&nbsp;</h2>
								<p><b>Device</b></p>
								<p><b>IP address</b></p>
								<!-- <p><b>iOS</b></p> -->
								<p><b>Operating System Version</b></p>
								<p><b>Detected Screen Resolution</b></p>
								<p><b>Guided Access enabled</b></p>
								<p><b>Device Storage</b></p>
								<p><b>Wireless network</b></p>
								<p><b>UDID</b></p>
								<p><b>Last Sync</b></p>
								<a>&nbsp;</a>
							</div>
							<c:forEach items="${deviceRegistration}" var="dr" varStatus="loops">
							<div class="col-3" style="width:36%">
								<h2>${dr.deviceType}</h2>
								<p>${dr.deviceName}</p>
								<p><b>${dr.ipAddress}</b></p>
								<%-- <p>${dr.deviceType}</p> --%>
								<p>${dr.operationgSystemVersion}</p>
								<p>${dr.deteactedResolution}</p>
								<p>${dr.guidedAccessEnabled}</p>
								<p>${dr.deviceStorage}</p>
								<p class="wire">${dr.wirelessNetwork}</p>
								<input type="hidden" id="abcd${loops.index + 1}" value="${dr.wirelessNetwork}">
								<input type="hidden" value="${dr.subNetMask}" id="${loops.index +1}" />
								<p style="    word-break: break-all;">${dr.deviceUUID}</p>
								<p>${dr.deviceTimestamp}<%-- <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${dr.lastSyncTime}" /> --%></p>
								<%-- <a href="syncDevice?deviceId=${dr.deviceId}">Sync Now</a> --%>
								&nbsp;
								&nbsp;
								&nbsp;
							</div>
							<!-- <div class="col-3">
								<h2>Interface</h2>
								<p>Apple iPhone 5S</p>
								<p>V7.04</p>
								<p>1080p (1920*1080)</p>
								<p>No</p>
								<p>3.6GB used 12GB available</p>
								<p>MyWireless</p>
								<p>192.168.1.100</p>
								<p>Oct 19,2013 8:37Am</p>
								<a href="#">Sync Now</a>
							</div> -->
							</c:forEach>
							</div>
							<div class="clearfix"></div></div>
							<a href="boothSetUpByEventConfigFirst"><button class="btn btn-green nxtbtn">Next</button></a>
						</div>
						</c:if>
						<c:if test="${deviceRegistration.size() == 0}">
								<center><div>Sign into app with both devices</div></center>
						</c:if>
						
				</div>
				<div class="clearfix"></div>