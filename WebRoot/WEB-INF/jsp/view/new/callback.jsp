<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript" src="https://appleid.cdn-apple.com/appleauth/static/jsapi/appleid/1/en_US/appleid.auth.js"></script>
        <div id="appleid-signin" data-color="black" data-border="true" data-type="sign in"></div>
        <script type="text/javascript">
        AppleID.auth.init({
			clientId: 'com.appplesignInstark',
			scope: 'name email',
			redirectURI: 'http://star-k.eastus.cloudapp.azure.com:8000/iamuse/',
			state:'state'
			}).done(function(response){
				
				console.log("***"+response)
			});
            
        </script>
</body>
</html>