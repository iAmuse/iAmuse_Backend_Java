				<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script type="text/javascript">
      $(document).ready(function() {
    	  $('#effect').delay(9000).fadeOut(400);
            $("#a").addClass("active_menu");
            
            var objdatetime=new Date();
            var timezone1=objdatetime.toTimeString();   // Output like 13:56:48 UTC+0530 , u need to extract UTC+0530 from it
            
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
	}  
  
  function sentTestEmail(){  
		 document.getElementById("gif").style.display = "";
	  var email=document.getElementById("emailId").value;
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
<style>
.right-pannel{height:auto !important;}
</style>
<div id="gif" style="display: none;">
	<img src="http://www.willmaster.com/images/preload.gif">
</div>
<div id="abc">
<!-- Popup Div Starts Here -->
<div id="popupContact">
<!-- Contact Us Form -->
<center><h3 class="heading" style="font-size: 10px;padding:20px;">Confirm that you can receive emails from iAmuse by putting in your email below.</h3></center>
<input id="emailId" name="name" placeholder="Name" value="${boothAdminLogin.emailId}" type="text" style="margin:0px 20px 11px 10px;width: 91%;">
<button onclick="sentTestEmail();" class="btn btn-green" style="float: right;margin-right:6%;margin-bottom: 15px;">send</button>
<button onclick="div_hide();" class="btn btn-green" style="float: right;margin-right:2%;">Cancel</button>
</div>
<!-- Popup Div Ends Here -->
</div>			
	
			<div class="right-pannel" data-step="1" data-intro="These devices appearing here would be the ones synced together and being used in the ongoing event">
					<a href="getDevices"><button style="float: right;" class="btn btn-green">Refresh</button></a>
					<a href="javascript:void(0);" class="btn btn-green" onclick="clearDevicePictures()" style="float: right;margin:0 10px 0 10px" >Clear Device Pictures</a>
					<a href="javascript:void(0);" class="btn btn-green" id="popup" onclick="div_show()" style="float: right;">Test Email</a>
					
					
					<h1 class="heading">Registered Devices</h1>
					<c:if test="${deviceRegistration.size()>=2}"><p class="subtext">These are the 2 Apple devices that are registered together as your Booth (1 Camera device + 1 Guest Touchscreen device)</p></c:if>
					<c:if test="${deviceRegistration.size()!=2}"><p class="subtext">Sign into each device using your iAmuse account, choosing Camera for 1 device and Guest Touchscreen for the other.  Hit Refresh to confirm registration of devices.</p></c:if>
					<c:if test="${deviceRegistration.size() > 0}">
					
					<c:if test="${deviceVO.touchDeviceIP!=null}">
					<center style="color:green;">
					<c:if test="${deviceVO.touchDeviceToken==null || deviceVO.touchDeviceToken==''}">Please On Push Notification Of Your Touch Device</c:if>
					</center>
					</c:if>
					<c:if test="${deviceVO.cameraDeviceIP!=null}">
					<center style="color:green;">
					<c:if test="${deviceVO.cameraDeviceToken==null || deviceVO.cameraDeviceToken==''}">Please On Push Notification Of Your Camera Device</c:if>
					</center>
					</c:if>
					
					<div class="inner-content">
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
								<p><b>Time Zone</b></p>
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
								<input type="hidden" value="${dr.subNetMask}" id="ab${loops.index + 1}" />
								<p style="word-break: break-all;">${dr.deviceUUID}</p>
								<p style="word-break: break-all;">${dr.lastSyncTime}</p>
								<p>
							      <span>${dr.deviceTimestamp}</span>
							</div>
							</c:forEach>
							<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
						</div>
						</c:if>
						<c:if test="${deviceRegistration.size() == 0}">
								<center><div style="padding:50px 0px;"></div></center>
						</c:if>
						
				</div>
				<div class="clearfix"></div>