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
.share-page{margin:25px auto;width:90%;}
.img-pic1{ position: relative; width: 100%;height: 180px;text-align: center;}
</style>
<script>
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
  }); 
</script>		
<script>
   window.fbAsyncInit = function() {
	   FB.init({
		    appId      : '1756329657786142',
		    version    : 'v3.1'
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
 $(document).ready(function(){
 document.getElementById('shareBtn').onclick = function() {
  FB.ui({
    method: 'share',
    display: 'popup',
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
 });
</script>		
<body style="background: #ececec !important;">
<div id="fb-root"></div>
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
								</div>
								<input type="hidden"  name="eventAction" id="eventAction">
								<input type="hidden"  name="eventId" value="${optionsReports.eventId }" >
								<input type="hidden"  name="userId" id="userId" value=<%=request.getParameter("userId")%> >
        						<input type="hidden"  name="imagesId" id="imagesId" value=<%=request.getParameter("imageIds")%> >
								
								<div class="gallery-option" style="width:186px !important;">
								         <span class="img-action" style="margin-right:10px;">
								          <img src="<%=request.getContextPath()%>/resources/images/images/facebookIcon.jpg" id="shareBtn" style="height:46px;width:46px" alt="fb"/>
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