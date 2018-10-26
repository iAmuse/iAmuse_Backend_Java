	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	<%@page import="com.iamuse.admin.controller.*"%>
	<%
	FBConnection fbConnection = new FBConnection();
%>
<script>

(function () {
    var cookies = document.cookie.split("; ");
    for (var c = 0; c < cookies.length; c++) {
        var d = window.location.hostname.split(".");
        while (d.length > 0) {
            var cookieBase = encodeURIComponent(cookies[c].split(";")[0].split("=")[0]) + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT; domain=' + d.join('.') + ' ;path=';
            var p = location.pathname.split('/');
            document.cookie = cookieBase + '/';
            while (p.length > 0) {
                document.cookie = cookieBase + p.join('/');
                p.pop();
            };
            d.shift();
        }
    }
})();
function forgotPassworValidate(){
	var email=document.getElementById("email").value;
	var filter     = /^([a-zA-Z])+([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(email==''){
		alert("Please Enter Email Id");
		return false;
	}else{
		if(!filter.test(email)){
			   alert("Check email address");
		       return false;
		      }
	}
}
</script>
<script type="text/javascript">
      function handleClientLoad() {
        gapi.load('client:auth2', initClient);
      }

      function initClient() {
        gapi.client.init({
        	apiKey: 'AIzaSyDFgid0kpXE6298Ll0Ix4K77481oa5Ob-w',
            discoveryDocs: ["https://people.googleapis.com/$discovery/rest?version=v1"],
            clientId: '655850534607-03k4t3r5g8b2eqja34f6ljrfdkv46ns6.apps.googleusercontent.com',
            //apiKey: 'AIzaSyAa4EelXu7576FWb4XF-KO9fQf0dGQ7qmg',
            //discoveryDocs: ["https://people.googleapis.com/$discovery/rest?version=v1"],
            //clientId: '41725756077-9bpfdps2kb87767nvbr1ff52tgr2n9ih.apps.googleusercontent.com',
            scope: 'profile'
        }).then(function () {
          gapi.auth2.getAuthInstance().isSignedIn.listen(updateSigninStatus);
          updateSigninStatus(gapi.auth2.getAuthInstance().isSignedIn.get());
        });
      }

      function updateSigninStatus(isSignedIn) {
        if (isSignedIn) {
          makeApiCall();
        }
      }

      function handleSignInClick(event) {
        gapi.auth2.getAuthInstance().signIn();
      }

      function handleSignOutClick(event) {
        gapi.auth2.getAuthInstance().signOut();
      }

      function makeApiCall() {
        gapi.client.people.people.get({
          'resourceName': 'people/me',
          'requestMask.includeField': 'person.emailAddresses,person.names,person.photos'
        }).then(function(response) {
        	window.location.href='googleAction?emailId='+response.result.emailAddresses[0].value+'&username='+response.result.names[0].displayName+'&googleId='+response.result.emailAddresses[0].metadata.source.id+'&imageUrl='+response.result.photos[0].url;
        	console.log(JSON.stringify(response));
        }, function(reason) {
          console.log('Error: ' + reason.result.error.message);
        });
      }
      
    </script>


	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/signInValidation.js">  </script>
	<div class="login_form">
			<div class="login-logo">
				<img src="<%=request.getContextPath()%>/resources/images/images/logo_login.png">
			</div>
			<h1>The Green Screen Photo Booth Mobile App</h1>
					
			<div class="login"><%--
			 autocomplete="off"
				--%>
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
				<form:form action="signInAction" modelAttribute="SignInVO" onsubmit="return formValidation();" autocomplete="off">
					<div class="form_row">
						<input type="text" placeholder="Email" name="emailId" id="emailId"  ><span style="color:red" id="emailIdSpan"></span>
					</div>
					<div class="form_row">
						<input type="password" placeholder="Password" name="password" id="password" ><span style="color:red" id="passwordSpan"></span>
					</div>
					 <a href="#" data-toggle="modal" data-target="#myModal">Forgot password?</a>
					<div class="form_row">
						<input type="Submit" Value="SIGN IN" class="btn btn-green" style="margin-top:15px;">
					</div>
				</form:form>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Forgot Password</h4>
        </div>
        <div class="modal-body">
        <form:form action="forgotPassword" modelAttribute="loginVO" onsubmit="return forgotPassworValidate()">
        <div class="form_row">
          Enter Email:<input type="text" name="username" id="email" >
        </div>
        <div class="form_row">
          <input type="submit" value="Send">
        </div>  
          </form:form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <div class="form_row">
  <div style="float: left;width: 47%;padding: 10px"><fieldset><legend></legend></fieldset></div>OR<div  style="float: right;width: 47%; padding: 10px"><fieldset><legend></legend></fieldset></div>
<%--  <a href="<%=fbConnection.getFBAuthUrl()%>">
							<img  src="<%=request.getContextPath()%>/resources/images/images/facebookIcon.jpg" style="width: 25px;" alt="fblogin" />
							</a>  --%>
							<script async defer src="https://apis.google.com/js/api.js"  onload="this.onload=function(){};handleClientLoad()" onreadystatechange="if (this.readyState === 'complete') this.onload()"></script>
    		<button id="signin-button"  class="btn btn-green" style="width:100%;height :40px;background-color: #db4437; " onclick="handleSignInClick()">LogIn With Google</button>
			</div>
			</div>
			<h2>Not Registered Yet? <a href="https://desk.zoho.com/portal/iamuse/kb/iamuse-setup/setting-up-iamuse-app-1/first-time-registration-device-setup">Setup Instructions</a> <span><a href="signUpPage">Sign Up</a></span></h2>
		</div>