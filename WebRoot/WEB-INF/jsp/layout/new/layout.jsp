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
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>    
	<!--[if lt IE 9]>
        <script src="https://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/css/bootstrap.css" rel="stylesheet">
    
	  <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/resources/css/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/introjs.css" rel="stylesheet">	

<!-- <script async src="https://www.googletagmanager.com/gtag/js?id=G-NK1LDT7CR4"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'G-NK1LDT7CR4');
</script> -->

<!-- Google Tag Manager admin.iamuse.com -->
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-PWTLCNN');
</script>
<!-- End Google Tag Manager -->

<!-- This is for PayPal interaction -->
<!-- Google Tag Manager
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-PW4VC63');</script>
<!-- End Google Tag Manager -->

<script>var w=window;var p = w.location.protocol;if(p.indexOf("http") < 0){p = "http"+":";}var d = document;var f = d.getElementsByTagName('script')[0],s = d.createElement('script');s.type = 'text/javascript'; s.async = false; if (s.readyState){s.onreadystatechange = function(){if (s.readyState=="loaded"||s.readyState == "complete"){s.onreadystatechange = null;try{loadwaprops("27218d28c96aa859ed2a33e4d6f03c6da","2c4d6911fdc174e687e194c9c6b4ca86e","2fbfcda4ff4449ff3157bc57a98cb1e4e35be57b81e35b5b4","23be8a55c509a48ba0c6d1e0fd7e9193a6a73058460b2a736","0.0");}catch(e){}}};}else {s.onload = function(){try{loadwaprops("27218d28c96aa859ed2a33e4d6f03c6da","2c4d6911fdc174e687e194c9c6b4ca86e","2fbfcda4ff4449ff3157bc57a98cb1e4e35be57b81e35b5b4","23be8a55c509a48ba0c6d1e0fd7e9193a6a73058460b2a736","0.0");}catch(e){}};};s.src =p+"//marketinghub.zoho.com/hub/js/WebsiteAutomation.js";f.parentNode.insertBefore(s, f);</script>

  </head>
	<body>
	
	<!-- Google Tag Manager (noscript) admin.iamuse.com -->
<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-PWTLCNN"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<!-- End Google Tag Manager (noscript) -->
		
<!-- This is for PayPal interaction -->
<!-- Google Tag Manager (noscript) 
<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-PW4VC63"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<!-- End Google Tag Manager (noscript) -->


		<div class="wrapper">
           <tiles:insertAttribute name="header"/> 
			<div class="content">  
		  <tiles:insertAttribute name="menu"/>
		 <tiles:insertAttribute name="body"/>
			
		
		</div>	
  <tiles:insertAttribute name="footer"/> 
</div>  
</body>

</html>
