	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	<%@page import="com.iamuse.admin.controller.*"%>
	<%
	FBConnection fbConnection = new FBConnection();
%>
 <style>
	.g-signin2{
	margin-left:120px;
	margin-top:25px;
	}
	/*.main-div{
	 background-repeat: no-repeat;
	/* background-size: 1350px 650px;*/
    /*-moz-background-size: cover;
    -webkit-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    background-image: url('/IAmuse/resources/images/images/LoginBg.jpg');
	}*/
	.sign-in-btn:hover{
	   text-decoration:none;
	}
  </style>
  
  <style>
        .signin-button {
            width: 210px;
            height: 20px;
        }
    </style>
    
  <script>var w=window;var p = w.location.protocol;if(p.indexOf("http") < 0){p = "http"+":";}var d = document;var f = d.getElementsByTagName('script')[0],s = d.createElement('script');s.type = 'text/javascript'; s.async = false; if (s.readyState){s.onreadystatechange = function(){if (s.readyState=="loaded"||s.readyState == "complete"){s.onreadystatechange = null;try{loadwaprops("27218d28c96aa859ed2a33e4d6f03c6da","2093010dbeb7340a244b5077c3a9b178f","2fbfcda4ff4449ff3157bc57a98cb1e4e35be57b81e35b5b4","23be8a55c509a48ba0c6d1e0fd7e9193a6a73058460b2a736","0.0");}catch(e){}}};}else {s.onload = function(){try{loadwaprops("27218d28c96aa859ed2a33e4d6f03c6da","2093010dbeb7340a244b5077c3a9b178f","2fbfcda4ff4449ff3157bc57a98cb1e4e35be57b81e35b5b4","23be8a55c509a48ba0c6d1e0fd7e9193a6a73058460b2a736","0.0");}catch(e){}};};s.src =p+"//marketinghub.zoho.com/hub/js/WebsiteAutomation.js";f.parentNode.insertBefore(s, f);</script>
<!-- <script type="text/javascript"
  src="https://appleid.cdn-apple.com/appleauth/static/jsapi/appleid/1/en_US/appleid.auth.js"></script>
 -->

	

	


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


    
function SignWithGoogle(){
	gapi.auth2.getAuthInstance().disconnect();
    location.reload();
}
function handleClientLoad(){	
	gapi.load('client:auth2', initClient);
  }

  function initClient() {
    gapi.client.init({
    	apiKey: 'AIzaSyDFgid0kpXE6298Ll0Ix4K77481oa5Ob-w',
    	//apiKey:'AIzaSyAP-5YFDnrMGmqjVIz40Z37XEazE7-sSEY',
        discoveryDocs: ["https://people.googleapis.com/$discovery/rest?version=v1"],
        clientId: '655850534607-03k4t3r5g8b2eqja34f6ljrfdkv46ns6.apps.googleusercontent.com',
        //clientId:"949527443986-vlbpc7s8bvt7vlgfnlnrkpcs39q729mh.apps.googleusercontent.com",
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
	  gapi.auth2.getAuthInstance().signOut();
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
      'requestMask.includeField': 'person.emailAddresses,person.names'
    }).then(function(response) {
    	email=response.result.emailAddresses[0].value;
    	name=response.result.names[0].displayName;
    	gID=response.result.emailAddresses[0].metadata.source.id;
    	console.log("Email"+email);
    	console.log("name"+name);
    	console.log("gid"+gID);
    	var data = JSON.stringify({
    		"accesstoken":gID,
            "emailId":email,
             "username":name
      	 });
    	
    	
    	$.ajax({
	   url:"<%=request.getContextPath()%>/signInGmail",
	   type : "POST",
	   contentType : "application/json",
	   data : data,
	   dataType : 'json'
	   
	}).done(function(data){
		console.log("****"+data['responseCode']);
		if(data['responseCode']==1)
			{
			 window.location.href="signInGmail?emailId="+email;
			}
		else
			{
			 window.location.href="/iamuse";
			}
		
		});
    	
      	 /* var xhr = new XMLHttpRequest();
    	 xhr.withCredentials = true;
    	 xhr.open("POST", "http://iamuses.eastus.cloudapp.azure.com:8080/iamuseserver_internal/v1/iamuse/signInGmail");
    	 xhr.setRequestHeader("accept", "application/json");
    	 xhr.setRequestHeader("authorization", "DrEgBqmYbTXJqi2/a/H9O9YLYcRNjNTNn89BKpui1Y8");
    	 xhr.setRequestHeader("content-type", "application/json");
     	 xhr.send(data);
     	xhr.onload = function() {debugger;
     		  let responseObj =  JSON.parse(xhr.response);
     		  if(responseObj["responseCode"]=="1"){
     			  window.location.href="signInGmail?emailId="+email;
     		  }
     		  else{
     			 window.location.href="/iamuse";
     		  }
     		}; */
     	// console.log("Response:"+result);
     	//console.log("JSON response:"+(result));
         
    	});
    	//window.location.href='http://stark.eastus.cloudapp.azure.com:8000/iamuseserver_internal/v1/iamuse/signInGmail?emailId='+response.result.emailAddresses[0].value+'&username='+response.result.names[0].displayName+'&googleId='+response.result.emailAddresses[0].metadata.source.id;
    	//
   }

  

</script>	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/signInValidation.js">  </script>
	
    <meta name="google-signin-client_id" content="655850534607-03k4t3r5g8b2eqja34f6ljrfdkv46ns6.apps.googleusercontent.com">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<!--  <script src="sign.js"></script>-->
	<div class="col-lg-12 col-xs-12 col-md-12 main-div">
	<div class="col-lg-4 col-md-2">
	   </div>
	<div class=" col-lg-4 col-md-6 login_form sign_info" style="background-color:#fff;margin-top:90px;height:480px;">
	<div class="signin-form">
	<form:form action="signInAction" modelAttribute="SignInVO" onsubmit="return formValidation();" autocomplete="off">
			<div class="login-logo">
				<img src="<%=request.getContextPath()%>/resources/images/images/iamuse-email-logo.png">
			</div>
					
			<%--
			 autocomplete="off"
				--%>
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<div class="form_row">
						<input type="text" placeholder="Email" name="emailId" id="emailId"  ><span style="color:red" id="emailIdSpan"></span>
					</div>
					<div class="form_row">
						<input type="password" placeholder="Password" name="password" id="password" ><span style="color:red" id="passwordSpan"></span>
					</div>
					 <a href="#" data-toggle="modal" data-target="#myModal"style="margin-left: 55px;">Forgot password?</a>
					<div class="form_row">
						<input type="Submit" Value="SIGN IN" class="btn btn-green" style="margin-top:15px;width:100%;">
					</div>
					<!-- <div class="form_row">
					  <div style="float: left;width: 47%;padding: 10px">
						<fieldset>
							<legend></legend>
						</fieldset>
					</div>
						OR
					 <div style="float: right;width: 47%;padding: 10px">
						<fieldset>
							<legend></legend>
						</fieldset>
					</div>
					</div> -->
					<!-- <div class="form_row">
						<div class="g-signin2" data-onsuccess="onSignIn"></div>
					</div> -->
				</form:form></div>
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
  <!--  <div style="float: left;width: 47%;padding: 10px"><fieldset><legend></legend></fieldset></div>OR<div  style="float: right;width: 47%; padding: 10px"><fieldset><legend></legend></fieldset></div>
  <!-- <a href="<%=fbConnection.getFBAuthUrl()%>">
							<img  src="<%=request.getContextPath()%>/resources/images/images/facebookIcon.jpg" style="width: 25px;" alt="fblogin" />
							</a> -->
							<script async defer src="https://apis.google.com/js/api.js"  onload="this.onload=function(){};handleClientLoad()" onreadystatechange="if (this.readyState === 'complete') this.onload()"></script>
							<button id="signin-button"  class="btn btn-green" style="width:100%;height :40px;background-color: #db4437; " onclick="handleSignInClick()">Login with Gmail</button>
		
	<!--  <div id="appleid-signin" class="signin-button" data-color="black" data-border="false" data-type="sign in"></div>
	 <script src="https://appleid.cdn-apple.com/appleauth/static/jsapi/appleid/1/en_US/appleid.auth.js"></script>

	<script type="text/javascript">
		AppleID.auth.init({
			clientId: 'com.appplesignInlocal',
			scope: 'name email',
			redirectURI: 'http://bit.ly/2sSMkiP',
			state:'state',
			success: onsuccess
			});
		
		var onsuccess = function(data){
			console.log(data)
			
		}
		</script> -->
	
		
		</div> 
			<h2>Not Registered Yet?<span style="font-size:20px;font-weight:600;margin-left:5px;"><a href="signUpPage" class="sign-in-btn" style="color:#22a42f !important;">Sign Up</a></span></h2>
			<div style="text-align: right; margin-top: 40px; font-weight: 500;font-size: 16px;">
			 <a href="https://desk.zoho.com/portal/iamuse/kb" target="_blank">Setup Instructions</a>	</div>
			 
		</div>
		</div>