		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
		<html>
		<head>
			<meta name="twitter:card" content="photo" />
  			<meta name="twitter:site" content="@rapidsoft" />
  			<meta name="twitter:title" content="iAmuse-Admin" />
  			<meta name="twitter:image" content="<%="https://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>" />
  			<meta name="twitter:url" content="<%="https://"+request.getServerName()+ ":" + request.getServerPort()%>" />
		</head>
		<body>
		
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.8&appId=1739175283014162";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
			<div class="">
					<h1 class="heading pull-left">Event Gallery</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:20px;">
					<form:form action="dbToImagesZipSA" modelAttribute="ImageEmailFormVO" id="form_id" >
						<div class="col-row">
							<div class="event-gallery-action">
								<div class="gallery-link">
									<h1>Link to Event Gallery</h1>
									<a href="<%="https://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>" target="_blank"><%="https://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %></a>
									
								</div>
								<input type="hidden"  name="eventAction" id="eventAction">
								<input type="hidden"  name="eventId" value="${optionsReports.eventId }" >
								
								<div class="gallery-option" style="width: 12% !important;">
									<span class="img-action">
										<div class="fb-share-button" data-href="<%="https://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>" data-layout="button_count" data-size="large" data-mobile-iframe="true"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=<%="https://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>&amp;src=sdkpreparse">Share</a></div>
									</span>
									&nbsp;&nbsp;&nbsp;
									<span class="img-action">
										<a href="https://twitter.com/share" class="twitter-share-button" data-size="large" data-url="<%="https://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>" data-show-count="false">Tweet</a><script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script></html>
									</span>
									<div class="clearfix"></div>
								</div>
								<div class="clearfix"></div>
							</div>
							
							<div class="gallery" id="deleteFiles">
							<c:forEach items="${emailImagesList}" varStatus="loop" var="igl">
								<div class="gallery-div">
									<div class="img-pic">
										<img src="${igl.mailImageUrl }">
										<p class="event-time">${igl.uploadTime}</p>
									</div>
									<p class="email-id">${igl.emailId} </p>
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