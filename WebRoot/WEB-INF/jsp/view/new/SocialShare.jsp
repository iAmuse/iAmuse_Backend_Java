<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
		 	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.10.2.min.js"></script>
		 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
		<html>
		<head>
			<meta name="twitter:card" content="summary" />
			<meta name="twitter:site" content="@IAmuse_new" />
			<meta name="twitter:title" content="Fun TO Share" />
			<meta name="twitter:description" content="View the album on IAmuse." />
			<c:forEach items="${emailImagesList}" varStatus="loop" var="igl" begin="0" end="0">
					<meta name="twitter:image" content="<%=request.getContextPath()%>${igl.mailImageUrl}" />
  			</c:forEach>
		</head>
		<style>
			.share-page{
				margin:25px auto;
				width:90%;
			}
			.img-pic1{
    position: relative;
    width: 100%;
   	height: 180px;
    text-align: center;
}
		</style>
<script>

/* 
function fbShare()
{
	 var urlContext      = window.location.href;
	  var res = encodeURIComponent(urlContext);
 // var userId=document.getElementById("userId");
 // var imagesId=document.getElementById("imagesId");
 $.ajax({
     type: "GET",
     url: "setFbShareValue",
     data: { userId: $('#userId').val(), imagesId: $('#imagesId').val()} ,
     async:false,
    
     success: function(response) {
        // alert("success");
         var sTop = window.screen.height/2-(218); 
         var sLeft = window.screen.width/2-(313);
         window.open('https://www.facebook.com/sharer/sharer.php?u='+res,'sharer','toolbar=0,status=0,width=626,height=456,top='+sTop+',left='+sLeft);
         return false;
         
 }
 });
} */


$(document).ready(function() {
	
	var tweetUrlBuilder = function(o){
	    return [
	        'https://twitter.com/intent/tweet?tw_p=tweetbutton',
	        '&url=', encodeURI(o.url),
	        '&via=', o.via,
	        '&text=', o.text
	    ].join('');
	};
	$('#twitterShare').on('click', function(){
		 var urlContext      = window.location.href;
		  var res = encodeURIComponent(urlContext);
	    var url = tweetUrlBuilder({
	        url : res,
	        via : 'IAmuse',
	        text: res
	    });
	    var child = window.open(url, 'Tweet', 'height=500,width=700');
	    var timer = setInterval(checkChild, 1000);
        function checkChild() {
  	    if (child.closed) {
  	    	  $.ajax({
  	    	      type: "GET",
  	    	      url: "setTwitterShareValue",
  	    	      data: { userId: $('#userId').val(), imagesId: $('#imagesId').val()} ,
  	    	      async:false,
  	    	     
  	    	      success: function(response) {
  	    	      }
  	    	      }); 
  	        clearInterval(timer);
  	    }
  	}
	});
	
  /* $('#twitterShare').click(function(event) {
	  var urlContext      = window.location.href;
	  var res = encodeURIComponent(urlContext);

          var width  = 575,
          height = 400,
          left   = ($(window).width()  - width)  / 2,
          top    = ($(window).height() - height) / 2;
          var child =window.open('https://twitter.com/share?url='+res,'sharer','toolbar=0,status=0,width=626,height=456,top='+top+',left='+left);
          var timer = setInterval(checkChild, 1000);
          function checkChild() {
    	    if (child.closed) {
    	    	  $.ajax({
    	    	      type: "GET",
    	    	      url: "setTwitterShareValue",
    	    	      data: { userId: $('#userId').val(), imagesId: $('#imagesId').val()} ,
    	    	      async:false,
    	    	     
    	    	      success: function(response) {
    	    	      }
    	    	      }); 
    	    	      alert("inn2");
    	    	      
    	        clearInterval(timer);
    	    }
    	}
 
  });   */
  }); 


</script>		
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '278102269337484',
      xfbml      : true,
      version    : 'v2.10'
    });
    FB.AppEvents.logPageView();
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
<script type="text/javascript">
function fbShare() {
	var urlContext      = window.location.href;
	var res = encodeURIComponent(urlContext);
FB.ui({
	  method: 'share',
	  href: window.location.href,
	}, function(response){
		if(response && response.post_id) {
			 $.ajax({
			     type: "GET",
			     url: "setFbShareValue",
			     data: { userId: $('#userId').val(), imagesId: $('#imagesId').val()} ,
			     async:false,
			     success: function(response) {
			    	 alert(response);
			     }
			 });
	      }
	      else {
	        return false;
	      }
	});
}
</script>		
<body style="background: #ececec !important;">
<div id="fb-root"></div>
<!-- <script>
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.8&appId=1739175283014162";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script> -->
			
			<div class="header">
				<div class="container-1">
					<div class="logo">
						<img src="<%=request.getContextPath()%>/resources/images/images/logo.png">
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			
			<div class="share-page">
					<a href="#" onclick="window.history.back();"><button type="button" class="btn btn-default btn-sm pull-left" style="margin-right: 10px;"><span class="glyphicon glyphicon-chevron-left"></span>Back</button></a><h1 class="heading pull-left">Event Gallery</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:20px;">
					<form:form action="dbToImagesZip" modelAttribute="ImageEmailFormVO" id="form_id" >
						<div class="col-row">
							<div class="event-gallery-action">
								<div class="gallery-link">
									<h1>Link to Event Gallery</h1>
									<%-- <a href="<%="http://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>" target="_blank"><%="http://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %></a> --%>
									
								</div>
								<input type="hidden"  name="eventAction" id="eventAction">
								<input type="hidden"  name="eventId" value="${optionsReports.eventId }" >
								<input type="hidden"  name="userId" id="userId" value=<%=request.getParameter("userId")%> >
        						<input type="hidden"  name="imagesId" id="imagesId" value=<%=request.getParameter("imageIds")%> >
								
								
								
								
							<%-- 	<div class="gallery-option" style="width:186px !important;">
									<span class="img-action" style="margin-right:10px;">
										<div Style="" class="fb-share-button" data-href="<%="http://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>" data-layout="button_count" data-size="large" data-mobile-iframe="true"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=<%="http://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>&amp;src=sdkpreparse">Share</a></div>
									</span>
									<span class="img-action">
										<a href="https://twitter.com/share" class="twitter-share-button" data-size="large" data-url="<%="http://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>" data-show-count="true">Tweet</a><script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
									</span>
									<div class="clearfix"></div>
								</div> --%>
								
								
								<div class="gallery-option" style="width:186px !important;">
								         <span class="img-action" style="margin-right:10px;">
								          <a  onclick="return fbShare();"   target="_blank" href="javascript:void(0)" >
								          <img src="<%=request.getContextPath()%>/resources/images/images/facebookIcon.jpg" style="height:46px;width:46px" alt="fb"/>
								          </a>
								                 
								          
								         </span>
								         <span class="img-action">
								          <a id="twitterShare"  href="javascript:void(0)" data-dnt="true" data-size="large"><img src="<%=request.getContextPath()%>/resources/images/images/twitterIcon.jpg" style="height:46px;width:46px" alt="fb"/></a><script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
								         </span>
								         <div class="clearfix"></div>
								  </div>
								
								
								
								<div class="clearfix"></div>
							</div>
							
							<div class="gallery" id="deleteFiles">
							<c:forEach items="${emailImagesList}" varStatus="loop" var="igl">
								<div class="gallery-div">
									<div class="img-pic1">
										<img src="${igl.mailImageUrl }">
										<p class="event-time">${igl.uploadTime}</p>
									</div>									
								</div>
							</c:forEach>
								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
						</div>
						</form:form>			
									
					</div>
				</div>
				<div class="clearfix"></div>
				</body>
		</html>