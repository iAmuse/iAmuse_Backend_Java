			<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/uploadImage.js">  </script>
			<link href="<%=request.getContextPath()%>/resources/css/css/bootstrap.min.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/resources/css/css/jcarousel.responsive.css" rel="stylesheet">
			<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/jquery.jcarousel.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/jcarousel.responsive.js"></script>
			<script type="text/javascript">
	function updateList() {
  var input = document.getElementById('images2');
  var output = document.getElementById('fileList');

  output.innerHTML = '<ul>';
  for (var i = 0; i < input.files.length; ++i) {
    output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  }
  output.innerHTML += '</ul>';
}
	
	function updateList1() {
  var input = document.getElementById('images1');
  var output = document.getElementById('fileList1');

  output.innerHTML = '<ul>';
  for (var i = 0; i < input.files.length; ++i) {
    output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  }
  output.innerHTML += '</ul>';
}
	</script>
			<style>
			.jcarousel ul li input[type="radio"]{
				position: absolute;
			    top: 5px;
			    left: 8px;
			    width:22px;
			    height:22px;
			}
			</style>
			<script type="text/javascript">
			      $(document).ready(function() {
			    	  $('#effect').delay(9000).fadeOut(400);
			            $("#b").addClass("active_menu");
			        });
			      function deletEventPicture(picId,eventId){
			    	  window.location.href ="<%=request.getContextPath()%>/deletEventPicture?picId="+picId+"&eventId="+eventId;
			      }
			</script>
			
<style>
.black_overlay {
  display: none;
  position: fixed;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  background-color: black;
  z-index: 1001;
  -moz-opacity: 0.8;
  opacity: .80;
  filter: alpha(opacity=80);
  margin-top:-y;
margin-left:-x;
}
.white_content {
  display: none;
  position: fixed;
 top: 5%;
    left: 10%;
    width: 80%;
    height: 90%;
  padding: 16px;
  border: 16px solid #05a42e;
  background-color: white;
  z-index: 1002;
  overflow: auto;
margin-top:-y;
margin-left:-x;  
}

</style>
 <script type="text/javascript"> 
				
function open1() {
	$("#myModal").modal('show');
    	  }
    	  
function close1(){	  
document.getElementById('light').style.display='none';
document.getElementById('fade').style.display='none';
}
</script>			
			
			
			<div class="right-pannel">
				 <c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left">Create Events &nbsp;:&nbsp;<b style="color: green"> ${eventName}</b></h1>
					<a href="javascript:void(0)" class="btn btn-green pull-right"  onclick="open1();">Add Background</a>
					<div class="clearfix"></div>					
					
					<div id="myModal" class="modal fade">
					    <div class="modal-dialog">
					        <div class="modal-content">
					            <div class="modal-header">
					                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					                <h4 class="modal-title">Upload Background</h4>
					            </div>
					            <div class="modal-body">
					                <form:form action="editUploadBackgroundImage" modelAttribute="AdminPictureVO" onsubmit="return validateForm2();" enctype="multipart/form-data" method="post">
										<input type="hidden" value="${eid}" name="eId"  id="eventId"/>
											<div class="form_row" style="margin-top:35px;" >									
											<input type="hidden" value="${eid}" name="eId"/>
												<span class="upload_file" >
													<div class="file_container" data-step="3" data-intro="Upload one or multiple events by keeping the control key pressed" data-toggle="tooltip" title="Upload one or multiple events by keeping the control key pressed">
														<input type="file" name="files" id="images2" multiple accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" />
													</div>
													<p>&nbsp;&nbsp;*&nbsp;press ctrl to select multiple Images</p>
												</span>
												<div id="fileList"></div>
												<div id="formsubmitbutton">
													<div data-step="4" data-intro="Submit the images after you have selected them"><input type="submit" value="Submit" style="width:auto;margin-left:10px;" class="btn btn-green" data-toggle="tooltip" title="Submit the images after you have selected them" onclick="ButtonClicked()"></div>
												</div>
												<div id="buttonreplacement" style="margin-left:100px; display:none;">
													<img src="https://www.willmaster.com/images/preload.gif" alt="loading...">
												</div>
												<div class="clearfix"></div>
											</div>
										</form:form>
					            </div>
					        </div>
					    </div>
					</div>
					
					
					<div class="clearfix"></div>
					
					<div class="inner-content" style="padding:35px;">
					
						<div class="col-row">
						<form:form action="setUpBackgroundImage" modelAttribute="AdminPictureVO" >
							<div class="slide_docs">
								<div class="jcarousel-wrapper" data-step="5" data-intro="Select the desired image to be configured by clicking on the radio button and click on Configure Image">
								<input type="hidden" value="${eid}" name="eId"/>
									<div class="jcarousel" style="padding-left:5px !important">
										<ul>
										<c:forEach items="${adminPictureVOs2}" var="picBackground" varStatus="loop" >
											<li>
												<!-- <img src="${picBackground.picName }" alt="Image 2" style="max-width:100%; height:auto;-o-object-fit: contain;"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" >-->
												<div class="thumbnail_container">
										            <div class="thumbnail">
										              <div class="grid_view_img"><img src="${picBackground.picName }"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" > <span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></div>
										            </div>
										         </div>
											</li>
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
								<input type="submit" class="btn btn-green" id="submit" value="Configure Image" style="width:auto" data-toggle="tooltip" title="Select the desired image to be configured by clicking on the radio button and click on Configure Image">
							</div>
							</form:form>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					<%-- <a href="boothSetUpByEvent?eventId=${eid}" style="margin-top:10px;" class="pull-right" data-toggle="tooltip" title="These are the camera settings for your ongoing event"><button class="btn btn-green">Next Step >> Booth Setup</button></a> --%>
				</div>
				<div class="clearfix"></div>