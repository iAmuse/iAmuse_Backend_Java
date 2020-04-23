<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	<script>
	 $(document).ready(function() {
		 document.getElementById("passValid").innerHTML="";
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
		else{
			return passwordValid(newPassword);
		}
	}
	function passwordValid(password)
	{
		var password= password;
		//filter= /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%&*_.? ])[A-Za-z\d\!@#$%&*_.?]{8,}$/;
		
		/* Validating password with Numbers OR uppercase OR lowercase OR special characters */ 
		filter=^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$;
		if(password.length<8)
		 {    	  
		  document.getElementById("passValid").innerHTML="*password should have atleast 8 character";
		  return false;
		 }
		if(password.trim() !=null){
			
			 if(!filter.test(password)){
				   document.getElementById("passValid").innerHTML="*password should be minimum of 8 characters with at 1 alphabet & 1 special character & 1 digit";
			      return false;
			      }
		}
		
	}
	</script>
	<style>
	.left-pannel{
  min-height:750px !important;
}
	</style>
	<div class="col-lg-2 col-md-3" style="width:20%;"></div>
<div class="col-lg-10 col-xs-12 col-md-9 right-pannel right-height">
					<h1 class="heading pull-left">Change Password</h1>
					<div class="clearfix"></div>
					
					<div class="inner-content" style="padding:35px;">
					<p id="passValid"></p>
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