<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>iAmuse-Admin</title>
    <meta http-equiv='cache-control' content='no-cache'>
	<meta http-equiv='expires' content='0'>
	<meta http-equiv='pragma' content='no-cache'>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/favicon.png">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>    

    <link href="<%=request.getContextPath()%>/resources/css/css/bootstrap.css" rel="stylesheet">
    
	  <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/resources/css/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/introjs.css" rel="stylesheet">	

  </head>
	<body>
		<div class="wrapper">
                
               
		  <tiles:insertAttribute name="header"/> 
			<div class="content">  
		  <tiles:insertAttribute name="menu"/>
		 <tiles:insertAttribute name="body"/>
			
		<%-- <tiles:insertAttribute name="footer"/>  --%>
		
		
		
		</div>	
  
</div>
    <div class="clearfix"></div>    
</body>
</html>
