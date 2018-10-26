<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	 $('#effect').delay(9000).fadeOut(400);
})
</script>
		<div class="right-pannel">
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left" style="padding-top:15px;">My Profile</h1>
					<div class="clearfix"></div>
					<div class="inner-content profile-content">
						<div class="col-row">
							<c:if test="${not empty boothAdminLogin.imageFileName}">
							<div class="profile-img">
							<img src="<%=request.getContextPath()%>/imageDisplay?id=${boothAdminLogin.userId}">
							<%-- <img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png"> --%>
							</div>
							</c:if>
							
							<c:if test="${empty boothAdminLogin.imageFileName}">
							<div class="profile-img">
							<img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png">
							</div>
							</c:if>
							<div class="profile-detail">
								<div class="event-row">
									<div class="event-label">Name</div>
									<div class="label-value">${boothAdminLogin.username}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Email Id</div>
									<div class="label-value">${boothAdminLogin.emailId}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Phone Number</div>
									<div class="label-value">${boothAdminLogin.contactNumber}</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="edit-btn"><a href="editProfileDetails" class="btn btn-green">Edit</a></div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>