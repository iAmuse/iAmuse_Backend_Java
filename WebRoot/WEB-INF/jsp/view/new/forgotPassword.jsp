	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	<script>
	function confirmPassword(){
		var password=document.getElementById("password").value;
		if(password== ''){
			alert("please enter password");
			return false;
		}
		var confPassword=document.getElementById("confPassword").value;
		if(confPassword==''){
			alert("please enter confirm password");
			return false;
		}
		if(password!=confPassword){
			alert("confirm password mismatch");
			return false;
		}
	}	
	</script>
	<div class="login_form">
			<div class="login"><%-- autocomplete="off" --%>
				<form:form action="resetPassword" modelAttribute="SignInVO" onsubmit="return confirmPassword();" autocomplete="off">
					<div class="form_row">
						<input type="password" placeholder="Password" name="password" id="password"  >
						<input type="hidden" name="token" value="${token}">
					</div>
					<div class="form_row">
						<input type="password" placeholder="Confirm Password" name="cnfpassword" id="confPassword" >
					</div>
					<div class="form_row">
						<input type="Submit" Value="Save" class="btn btn-green" style="margin-top:15px;">
					</div>
				</form:form>
			</div>
		</div>