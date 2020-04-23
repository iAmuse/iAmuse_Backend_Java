<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	 $('#effect').delay(9000).fadeOut(400);
})
</script>
<div class="col-lg-2 col-md-3" style="width:20%;"></div>
		<div class="col-lg-10 col-xs-12 col-md-9 right-pannel right-height">
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left" style="padding-top:15px;">My Profile</h1>
					<div class="clearfix"></div>
					<div class="inner-content profile-content">
						<div class="col-row prorow">
							<c:if test="${not empty boothAdminLogin2.imageFileName}">
							<div class="profile-img">
							<img src="<%=request.getContextPath()%>/imageDisplay?id=${boothAdminLogin2.userId}">
							<%-- <img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png"> --%>
							</div>
							</c:if>
							
							<c:if test="${empty boothAdminLogin2.imageFileName}">
							<div class="profile-img">
							<img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png">
							</div>
							</c:if>
							<div class="profile-detail">
							<%-- 	<div class="event-row">
									<div class="event-label">First Name</div>
									<div class="label-value">${boothAdminLogin2.username}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Last Name</div>
									<div class="label-value">${boothAdminLogin2.lastname}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Email Id</div>
									<div class="label-value">${boothAdminLogin2.emailId}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Phone Number</div>
									<div class="label-value">${boothAdminLogin2.contactNumber}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label" title="Edit the 4-digit PIN used to enter setup mode for the booth and make administrative changes (accessed with 2-finger swipe up)">Booth Security PIN</div>
									<div class="label-value">${boothAdminLogin2.pin}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">User Type</div>
									<div class="label-value">${boothAdminLogin2.userType}</div>
									<div class="clearfix"></div>
								</div>
							<div class="event-row">
									<div class="event-label">Subscription Type</div>
									<c:forEach items="${subscriptionMaster}" var="sm" varStatus="loop">
							         <c:if test="${sm.subId == boothAdminLogin2.subId}">
									<div class="label-value">${sm.subName}</div>
									</c:if>
									</c:forEach>
									<c:if test="${boothAdminLogin2.subId==4}">
									<div class="label-value">Professional</div>
									</c:if>
									<div class="clearfix"></div>
								</div> --%>
								<table cellspacing="20">
								<tr class="event-row">
								<td class="event-label">First Name</td>
								<td>${boothAdminLogin2.username}</td>
								</tr>
								<tr class="event-row">
								<td class="event-label">Last Name</td>
								<td>${boothAdminLogin2.lastname}</td>
								</tr>
								<tr class="event-row">
								<td class="event-label">Email ID</td>
								<td>${boothAdminLogin2.emailId}</td>
								</tr>
								<tr class="event-row">
								<td class="event-label">Phone Number</td>
								<td>${boothAdminLogin2.contactNumber}</td>
								</tr>
								<tr class="event-row">
								<td class="event-label" title="Edit the 4-digit PIN used to enter setup mode for the booth and make administrative changes (accessed with 2-finger swipe up)">Booth Security PIN</td>
								<td>${boothAdminLogin2.pin}</td>
								</tr>
								<tr class="event-row">
								<td class="event-label">User Type</td>
								<td>${boothAdminLogin2.userType}</td>
								</tr>
								<tr class="event-row">
								<td class="event-label">Subscription Type</td>
								<c:forEach items="${subscriptionMaster}" var="sm" varStatus="loop">
								<c:if test="${sm.subId == boothAdminLogin2.subId}">
								<td>${sm.subName}</td>
								</c:if>
								</c:forEach>
								</tr>
								</table>
							<div class="profile-config probtn">
								<a href="editProfileDetails" class="btn btn-green editbtn">Edit</a>  
								<a class="btn btn-green zscale" onclick="deleteUserProfile();">Delete Account</a>
							</div>
							</div>
							
							<div class="clearfix"></div><br>
							<b><div id="profileCountdown"></div></b>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
<script>
	var end = new Date('${boothAdminLogin2.subEndDate}');
	if (end == 'Invalid Date') {
	} else {
		var profile = {}
		profile.second = 1000;
		profile.minute = profile.second * 60;
		profile.hour = profile.minute * 60;
		profile.day = profile.hour * 24;
		var timer;

		function showRemaining() {
			var now = new Date();
			var distance = end - now;
			if (distance < 0) {
				clearInterval(timer);
				document.getElementById('profileCountdown').innerHTML = ' EXPIRED!';
				return;
			}
			var days = Math.floor(distance / profile.day);
			var hours = Math.floor((distance % profile.day) / profile.hour);
			var minutes = Math.floor((distance % profile.hour) / profile.minute);
			var seconds = Math.floor((distance % profile.minute) / profile.second);

			document.getElementById('profileCountdown').innerHTML = 'Your Subscription Will Expire After :  ';
			document.getElementById('profileCountdown').innerHTML += days + ' Days  ';
			document.getElementById('profileCountdown').innerHTML += hours + ' Hrs  ';
			document.getElementById('profileCountdown').innerHTML += minutes + ' Mins  ';
			document.getElementById('profileCountdown').innerHTML += seconds + ' Secs ';
		}
	}
	timer = setInterval(showRemaining, 1000);
	function deleteUserProfile(){
		var r = confirm("Are you sure you want to delete this profile?");
		if (r == true) {
			$.ajax({url: "<%=request.getContextPath()%>/deleteProfileDetail",
				success: function(result){
			    	alert("Successfully deleted");
			    	window.location="/iamuse"; 			  }}
			);
		}
	}
</script>