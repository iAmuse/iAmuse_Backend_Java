	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js" type="text/javascript"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/signUpValidation.js">  </script>
	<div class="login_form">
			<div class="login-logo">
				<img src="<%=request.getContextPath()%>/resources/images/images/logo_login.png">
			</div>
			<h1>The Green Screen Photo Booth Mobile App</h1>
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
			<div class="login">
				<form:form action="createBootAdmin" modelAttribute="SignInVO" onsubmit="return formValidation();" autocomplete="off">
					<div class="form_row">
						<input type="text" placeholder="Name" name="username" id="username"><span style="color:red" id="usernameSpan"></span>
					</div>
					<div class="form_row">
						<input type="text" placeholder="Email" name="emailId" id="emailId"><span style="color:red" id="emailIdSpan"></span>
					</div>
					<div class="form_row">
						<input type="password" placeholder="Password" name="password" id="password" minlength="8" maxlength="20" ><span style="color:red" id="passwordSpan"></span>
						<!-- <input type="password" placeholder="Password" name="password" id="password"  maxlength="8" ><span style="color:red" id="passwordSpan"></span> -->
					</div>
					<div class="form_row">
					<select id="" name="userType">
					<option value="Personal">Personal</option>
					<option value="Professional">Professional</option>
					</select>
					</div>
					<div class="form_row">
						<input type="Submit" Value="SIGN UP" class="btn btn-green" style="margin-top:15px;">
					</div>
				</form:form>
			</div>
			<h2>You Already Registered? <span><a href="./">Sign In</a></span></h2>
		</div>
