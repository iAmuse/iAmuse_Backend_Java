			<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/uploadImage.js">  </script>
			<link href="<%=request.getContextPath()%>/resources/css/css/bootstrap.min.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/resources/css/css/jcarousel.responsive.css" rel="stylesheet">
			<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/jquery.jcarousel.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/jcarousel.responsive.js"></script>
			<style>
			.jcarousel ul li input[type="radio"]{
				position: absolute;
			    top: 5px;
			    left: 8px;
			    width:22px;
			    height:22px;
			}
			</style>
			<div class="col-lg-2 col-md-3" style="width:20%;"></div>
			<div class="right-pannel col-lg-10 col-xs-12">
					<h1 class="heading pull-left">Create Events</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
						<form:form action="setUpBackgroundImageSA" modelAttribute="AdminPictureVO" >
							<div class="slide_docs">
								<div class="jcarousel-wrapper">
								<input type="hidden" value="${eid}" name="eId"/>
									<div class="jcarousel" style="padding-left:5px !important">
										<ul>
										<c:forEach items="${adminPictureVOs2}" var="picBackground" varStatus="loop" >
											<li><img src="${picBackground.picName }" alt="Image 2"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" ></li>
										</c:forEach>											
										</ul>
									</div>
									<a href="#" class="jcarousel-control-prev">&lsaquo;</a>
									<a href="#" class="jcarousel-control-next">&rsaquo;</a>
									<p class="jcarousel-pagination"></p>
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="blank_line"></div>
							<div class="configure_img">
								<input type="submit" class="btn btn-green" value="Configure Image" style="width:auto" id="submit">
							</div>
							</form:form>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>	
<script >
$(document).ready(function() {
$("#d").addClass("active_menu");
});
</script>