<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<!DOCTYPE HTML>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IAmuse</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/dataTable.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" />

<link href="<%=request.getContextPath()%>/resources/css/bootstrapV2.3.2.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/jqueryv1.7.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrapV2.3.2.js"></script>
  

<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
<SCRIPT type="text/javascript">
  
  function noBack() {
   window.history.forward();
  }
</SCRIPT>
</head>

<body onload="noBack();">
                
               
  <tiles:insertAttribute name="header"/> 
   <div id="wrapbig">
  <div class="container">   
  <tiles:insertAttribute name="menu"/> 
  <tiles:insertAttribute name="body"/>
	</div> 
	
	
	<div id="footer"> 
	<tiles:insertAttribute name="footer"/> 
	
	</div>
  
</div>
        
</body>
</html>
