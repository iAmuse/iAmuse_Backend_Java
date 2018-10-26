	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	<script>
	 $(document).ready(function() {
         $("#i").addClass("active_menu");
     });
	function changePassword(){
		var password=document.getElementById("password").value;
		if(password== ''){
			alert("please enter password");
			return false;
		}
		var newPassword=document.getElementById("newPassword").value;
		if(password== ''){
			alert("please enter password");
			return false;
		}
		var confirmPassword=document.getElementById("confirmPassword").value;
		if(confirmPassword!= newPassword){
			alert("confirm password mismatch");
			return false;
		}
		if(password == newPassword){
			alert("new password and old password should not be same");
			return false;
		}
	}
	</script>
<div class="right-pannel">
					<h1 class="heading pull-left">Change Password</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
						<c:if test="${not empty successMessage}">
						<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
						</c:if>
						
						<c:if test="${not empty errorMessage}">
						<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
						</c:if>
						
							<form:form action="changePassword" modelAttribute="SignInVO" onsubmit="return changePassword()">
								<div class="form_row">
									<div class="form_label" style="width:200px">Old Password</div>
									<div class="form_element">
										<input type="password" name="password" id="password" placeholder="********">
										<input type="hidden" name="userId" value="${boothAdminLogin.userId}">
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label" style="width:200px">New Password</div>
									<div class="form_element"><input type="password" name="newPassword" id="newPassword" placeholder="********"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label" style="width:200px">Confirm New Password</div>
									<div class="form_element"><input type="password" id="confirmPassword" placeholder="********"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row" style="margin-top:35px;">
									<div class="form_label" style="width:200px">&nbsp;</div>
									<div class="form_element"><input type="Submit" value="Save" class="btn btn-green"></div>
									<div class="clearfix"></div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>