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
  $(document).ready(function() {
     var date=document.getElementById("tmz1").value; 
     var convertdLocalTime = new Date(date);
     var hourOffset = convertdLocalTime.getTimezoneOffset() / 60;
     convertdLocalTime.setHours( convertdLocalTime.getHours() + hourOffset ); 
     
     document.getElementById("tmzs1").value=convertdLocalTime;
     
     
     var date1=document.getElementById("tmz2").value; 
     var convertdLocalTime1 = new Date(date1);
     var hourOffset1 = convertdLocalTime1.getTimezoneOffset() / 60;
     convertdLocalTime1.setHours( convertdLocalTime1.getHours() + hourOffset1 ); 
     
     document.getElementById("tmzs2").value=convertdLocalTime1;
      
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
         	 setTimeout(function () { alert("Test Mail Sent Successfully"); }, 2000);
         	 
          }else{
         	 document.getElementById("gif").style.display = "none";
         	 document.getElementById('abc').style.display = "none";
         	 setTimeout(function () { alert("Test Mail Sent Failed"); }, 2000);
          }

       }
   });
	  
	  <%-- window.location.href ="<%=request.getContextPath()%>/sendTestMail?email="+email; --%>
  }
  
</script>
<style>
/*.right-pannel{height:auto !important;}*/

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
	<div class="col-lg-2 col-md-3" style="width:20%;"></div>
			<div class="col-lg-10 col-sm-9 col-xs-12 col-md-9 right-pannel" data-step="1" data-intro="These devices appearing here would be the ones synced together and being used in the ongoing event">
			 <div class="col-lg-7 col-md-7 col-xs-12 pagetitle">
			 <h1 class="heading" style="margin-left: 0px !important;">Registered Devices</h1>
			 <c:if test="${deviceRegistration.size()>=2}"><p class="subtext">These are the 2 Apple devices that are registered together as your Booth (1 Camera device + 1 Guest Touchscreen device)</p></c:if>
					<c:if test="${deviceRegistration.size()!=2}"><p class="subtext">Sign into each device using your iAmuse account, choosing Camera for 1 device and Guest Touchscreen for the other.  Hit Refresh to confirm registration of devices.</p></c:if>
					</div>
					<div class="col-lg-5 col-md-5 col-xs-12 blcbtn">
					<a href="getDevices"><button style="float: right;margin-top: 20px;margin-right: 50px;" class="btn btn-green rstbtn">Refresh</button></a>
					<a href="javascript:void(0);" class="btn btn-green tstbtn" id="popup" onclick="div_show()" style="float: right;margin-top:20px;margin-right: 10px;">Test Email</a>
					<a href="javascript:void(0);" class="btn btn-green clear-device" onclick="clearDevicePictures()" >Delete Event Photos</a>
				</div>
					
					
				<div>	
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
					
				</div>	
					<div class="col-lg-12 col-md-12 col-xs-12" style="margin-left: 2px;width: 96%; margin-bottom: 50px; margin-top: 20px;"> 
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
								<p><b>UUID</b></p>
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
							<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
							<!-- <a href="getSubscribedEventList"><button style="float: right;margin-top: 10px;" class="btn btn-green">Next Step&nbsp;>></button></a> -->
						</div></div>
						</c:if>
						<c:if test="${deviceRegistration.size() == 0}">
								<center><div style="padding:50px 0px;"></div></center>
						</c:if>
						
				</div>
				<div class="clearfix"></div>