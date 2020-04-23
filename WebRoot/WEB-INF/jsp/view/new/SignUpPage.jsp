	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js" type="text/javascript"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/signUpValidation.js">  </script>
	<script>var w=window;var p = w.location.protocol;if(p.indexOf("http") < 0){p = "http"+":";}var d = document;var f = d.getElementsByTagName('script')[0],s = d.createElement('script');s.type = 'text/javascript'; s.async = false; if (s.readyState){s.onreadystatechange = function(){if (s.readyState=="loaded"||s.readyState == "complete"){s.onreadystatechange = null;try{loadwaprops("27218d28c96aa859ed2a33e4d6f03c6da","2093010dbeb7340a244b5077c3a9b178f","2fbfcda4ff4449ff3157bc57a98cb1e4e35be57b81e35b5b4","23be8a55c509a48ba0c6d1e0fd7e9193a6a73058460b2a736","0.0");}catch(e){}}};}else {s.onload = function(){try{loadwaprops("27218d28c96aa859ed2a33e4d6f03c6da","2093010dbeb7340a244b5077c3a9b178f","2fbfcda4ff4449ff3157bc57a98cb1e4e35be57b81e35b5b4","23be8a55c509a48ba0c6d1e0fd7e9193a6a73058460b2a736","0.0");}catch(e){}};};s.src =p+"//marketinghub.zoho.com/hub/js/WebsiteAutomation.js";f.parentNode.insertBefore(s, f);</script>
	<div class="login_form signup-div">
			<div class="login-logo">
				<img src="<%=request.getContextPath()%>/resources/images/images/iamuse-email-logo.png">
			</div>
			<h1 style="color:#fff">The Green Screen Photo Booth Mobile App</h1>
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
			<div class="login">
			<div class="signup-form">
				<form:form action="createBootAdmin" modelAttribute="SignInVO" onsubmit="return formValidation();" autocomplete="off">
					<div class="form_row">
						<input type="text" placeholder="First Name" name="username" id="username"><span style="color:red" id="usernameSpan"></span>
					</div>
					<div class="form_row">
						<input type="text" placeholder="Last Name" name="lastname" id="lastname"><span style="color:red" id="lastnameSpan"></span>
					</div>
					<div class="form_row">
						<input type="text" placeholder="Email" name="emailId" id="emailId"><span style="color:red" id="emailIdSpan"></span>
					</div>
					<div class="form_row">
						<input type="text" placeholder="Phone Number" name="contactNumber" id="contactNumber" maxlength="10" onkeypress="return onlyNos(event,this);"><span style="color:red" id="contactNumberSpan"></span>
					</div>
					<div class="form_row">
						<input type="password" placeholder="Password" name="password" id="password" minlength="8" maxlength="20" ><span style="color:red" id="passwordSpan"></span>
						<!-- <input type="password" placeholder="Password" name="password" id="password"  maxlength="8" ><span style="color:red" id="passwordSpan"></span> -->
					</div>
					<div class="form_row">
					<select id="" name="userType">
					<option value="Unknown">Unknown</option>
					<option value="Do-It-Yourselfer">Do-It-Yourselfer</option>
					<option value="Event Professional">Event Professional</option>
					<option value="Tradeshow">Tradeshow</option>
					<option value="Tourism">Tourism</option>
					<option value="Venue">Venue</option>
					</select>
					</div>
					<div class="form_row">
						<input type="text" placeholder="4-digit PIN for booth security" name="pin" id="PinNumber" maxlength="4" onkeypress="return onlyNos(event,this);"><span style="color:red" id="pinNumberSpan"></span>
					</div>
					<div class="form_row">
					  <p style="color:#fff">By clicking Sign Up, you agree to our <a href="https://www.iamuse.com/terms-conditions" target="_blank" style="color:red" >Terms</a> and <a href="https://www.iamuse.com/privacy-policy" target="_blank" style="color:red">Privacy Policy</a>.</p>
					</div>
					<div class="form_row">
						<input type="Submit" Value="SIGN UP" class="btn btn-green" style="margin-top:15px;width:100%">
					</div>
				</form:form>
				</div>
			</div>
			<h2 style="color:#fff">You Already Registered? <span><a style="color:#fff" href="./">Sign In</a></span></h2>
		</div>
<script>

function onlyNos(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
       if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
    catch (err) {
        alert(err.Description);
    }
}
</script>