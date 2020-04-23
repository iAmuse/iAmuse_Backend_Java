<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<!DOCTYPE HTML>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>iamuse</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
<SCRIPT type="text/javascript">
  
  function noBack() {
   window.history.forward();
  }
</SCRIPT>
</head>

<body onload="noBack();">
                
               
  <tiles:insertAttribute name="header"/> 
   <div id="wrap">
  <div id="content">
  
  
	<tiles:insertAttribute name="body"/>
	
	</div>
	
  
</div>
        
</body>
<div id="footer"> 
	<tiles:insertAttribute name="footer"/> 
	
	</div>
</html>
