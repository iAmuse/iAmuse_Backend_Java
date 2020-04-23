<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
 	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/font-awesome.min.css"/>
  	<!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/bootstrap.min.css"/> -->
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/cropper.css"/>
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/main.css"/>
  	<script>
	$("document").ready(function(){
		var url=document.getElementById("imgNatural").src;
	     var filename = url.substring(url.lastIndexOf('/')+1);
	     document.getElementById("picDetail").append(filename);
	     //console.log("URL"+filename);
	     
  		//$("#uploadNow").css('display', 'none');
  		/*$("#mi").change(function () {
  	    	$("#uploadNow").css('display', 'block');
  	    });*/
  	});
  
function updateMaskingStatus(){
	var pictureId=document.getElementById("picId1").value;
$.ajax({
    type: "GET",
    url: "updateMaskingImageStatus",
    data: { pictureId: pictureId} ,
    async:false,
    success: function(response) {
    	alert(response);
    	$('.Mask').html('');
}
});
}
function updateWaterMarkStatus(){
	var pictureId=document.getElementById("picId1").value;
	$.ajax({
	    type: "GET",
	    url: "updateWaterMarkStatus",
	    data: { pictureId: pictureId} ,
	    async:false,
	    success: function(response) {
	    	$('.Watermark').html('');
	}
	});
	}
</script>
 <script type="text/javascript">
 function resetFile(){
 $("#mi").val('');
 $("#fileList").html("");
 }
 function resetFile1(){
 $("#wmi").val('');
 $("#fileList1").html("");
 }
      $(document).ready(function() {
    	  $('#effect').delay(9000).fadeOut(400);
            $("#b").addClass("active_menu");
            
            $("#target").click(function() {
                $("#cropperImage").attr("src", "/iamuse/resources/images/images/zoom_profile2.png");
                $('#sec').removeClass("current-img");
                $('#fir').removeClass("current-img");
                $('#thi').removeClass("current-img");
                });
        });
      function fun()
      {
       $('.formal').attr('readonly', false);
       $("#picTitle").focus();
       document.getElementById("picTitle").style.outline = "1px solid #05a42e";
      }
      
</script> 	
<script>
$( document ).ready(function() {
	 var x = document.getElementById("imgNatural").naturalWidth;
    document.getElementById("demo1111").value = x;
 	/* var x1 = document.getElementById("imgNatural").naturalHeight;
    document.getElementById("demo2222").value = x1; */
	});
	
$( document ).ready(function() {
		 //$("#cropperImage").attr("src", "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
	var texte=document.getElementById("picTitle").value;
	if(texte!=''){
		$('.formal').attr('readonly', true);
	}
	
	
	container = document.getElementById ("cropperImage");
    if (container.addEventListener) {
        container.addEventListener ('DOMSubtreeModified', OnSubtreeModified, false);
    }
    
    function OnSubtreeModified () {
        alert ("The subtree that belongs to the container has been modified.");
    }
    
    var imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
    $(".cropperImageDiv").css("background-image", "url(" + imageUrl + ")");

	
});


function cll1()
{
 var name = document.getElementById("call1").src;
 $("#cropperImage").attr("src", name);
 $('#fir').addClass("current-img");
 $('#sec').removeClass("current-img");
 $('#thi').removeClass("current-img");
}
function cll2()
{
 var name = document.getElementById("call2").src;
 $("#cropperImage").attr("src", name);
 $('#sec').addClass("current-img");
 $('#fir').removeClass("current-img");
 $('#thi').removeClass("current-img");
}
function cll3()
{
 var name = document.getElementById("call3").src;
 $("#cropperImage").attr("src", name);
 $('#thi').addClass("current-img");
 $('#sec').removeClass("current-img");
 $('#fir').removeClass("current-img");
}

function setPos(){
	
	var name = document.getElementById("pod").src;
	
	$("#cropperImage").attr("src", name);
}
</script>
<script type="text/javascript">
	function updateList()
 {
  var input = document.getElementById('mi');
  var output = document.getElementById('fileList');

  //output.innerHTML = '<ul>';
  for (var i = 0; i < input.files.length; ++i) {
    output.innerHTML += 'Mask Image&nbsp;' + input.files.item(i).name +'&nbsp;&nbsp;&#10005;';
  }
  //output.innerHTML += '</ul>';
}
	function updateList1() {
		  var input = document.getElementById('wmi');
		  var output = document.getElementById('fileList1');

		  //output.innerHTML = '<ul>';
		  for (var i = 0; i < input.files.length; ++i) {
		    output.innerHTML += 'Watermark Image&nbsp;' + input.files.item(i).name +'&nbsp;&nbsp;&#10005;';
		  }
		  //output.innerHTML += '</ul>';
		}
</script>

<c:if test="${boothAdminLogin2.subId ==1}">
<script>
$( document ).ready(function() {
	
	document.getElementById("Button").disabled = true;
	document.getElementById("Button2").disabled = true;
});
</script>
	</c:if>
 <style>
 	.btn-primary{background:none !important;border:0px;}
 	/*.right-pannel{height:auto !important;}*/
 	
 	.pagination > li > a, .pagination > li > span{
 		bakground:none;
 		border:none;
 		padding:0px;
 	}
 	.pagination > li > a:hover, .pagination > li > span:hover, .pagination > li > a:focus, .pagination > li > span:focus{
 		    background-color: transparent;
 	}
 </style>
 <body onload="myFunction()">
 <div class="col-lg-2 col-md-3" style="width:20%;"></div>
<div class="col-lg-10 col-xs-12 right-pannel">
		<h1 class="heading pull-left">Setup Background</h1>
<form:form action="saveCoordinatesOfImg" modelAttribute="AdminPictureVO" enctype="multipart/form-data">

					 <div >
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
					<h2 class="heading" style="margin: -15px 0px 15px 0px;"> Background ${position} of ${adminPictureVOs2.size()} </h2>
					<div class="event-label">File: <span id="picDetail" style="font-weight:500;"></span></div>
						<div class="col-row">
							<div class="background-priview"  data-toggle="tooltip" title="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
								<div class="background-image">
									<div class="img-container cropper-bg" id="target" data-step="2" data-intro="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
							          <img src="${adminPictureVO1.picName}"  alt="Picture"  id="imgNatural">
							           <%-- <img src="${adminPictureVO1.picName}"  alt="Picture"  id="imgNatural"> --%>
							        </div>
							        
							  <%--  <input type="hidden" name="positionPrev"  value="${position-1}"/>  --%> 
							   <input type="hidden" name="position"  value="${position+1}"/>    
							<input type="hidden" name="cropImgX" id="x" value="${adminPictureVO1.cropImgX}"/>
							<input type="hidden" name="gridShift" id="gridShift" value="${adminPictureVO1.gridShift}"/>
						 	<input type="hidden" name="cropImgY" id="y" value="${adminPictureVO1.cropImgY}"/>
						 	<input type="hidden" name="cropImgWidth" id="w" value="${adminPictureVO1.cropImgWidth}"/>
						 	<input type="hidden" name="cropImgHeight" id="h2" value="${adminPictureVO1.cropImgHeight}"/>
						 	<input type="hidden" id="testX" value=""/>
						 	<input type="hidden" id="testY" value=""/>
						 	
						 	
						 	
						 				        
								</div>
								<div class="title title-textbox"  data-step="1" data-intro="Enter the title for the image"><input type="text" class="formal" id="picTitle" name="picTitle" style="width:94% !important;" placeholder="Enter Title" value="${adminPictureVO1.picTitle}" data-toggle="tooltip" title="Enter the title for the image"/><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png" onclick="fun()" style="cursor: pointer;margin-left:15px;" ></div>
								<div class="img-position-controls">
									<%-- <div class="control" style="margin-top:6px;">
														<label class="primary active">
											            <input type="radio" class="sr-only" id="aspectRatio0" name="aspectRatio"  value="NaN" >
											            <span class="docs-tooltip" data-toggle="tooltip" title="aspectRatio: 16 / 9">
											             <span>Size</span><img src="<%=request.getContextPath()%>/resources/images/images/size.png">
											            </span>
											          </label>
									</div> --%>
									<%-- <div class="control">
										<span>Position</span><img src="<%=request.getContextPath()%>/resources/images/images/position.png" id="pod" onclick="setPos();" >
									</div>  --%>
									<%-- <div class="control">
										<span >Edit Text</span><img src="<%=request.getContextPath()%>/resources/images/images/edit_text.png" onclick="fun()" style="cursor: pointer;" >
									</div> --%>
									<!-- <div class="control zscale"> -->
										<!-- <span>Z Scale</span> --><input type="hidden" placeholder="Z-Scale" name="scaleZOffset" id="scaleZOffset" value="${adminPictureVO1.scaleZOffset}" readonly="readonly">
									<!-- </div> -->
									<div class="clearfix"></div>
								</div>
							</div>
							<c:if test="${boothAdminLogin2.subId ==1}"><center><p class="subtext">You are in Free Trial mode so you cannot edit this Background. <a href="https://www.iamuse.com/pricing" target="_blank"><b>Upgrade</b></a> to unlock all features! </p><div class="clearfix"></div></center></c:if>
							<c:if test="${boothAdminLogin2.subId !=1}">
							<div class="background-position">
								<%-- <h1>Best Zoom Profile</h1>
								<div class="img-crop" >
											<div class="row" id="actions">
											      <div class="col-md-9 docs-buttons">
											       <div class="modal fade docs-cropped" id="getCroppedCanvasModal" role="dialog" aria-hidden="true" aria-labelledby="getCroppedCanvasTitle" tabindex="-1">
											          <div class="modal-dialog">
											            <div class="modal-content">
											              <div class="modal-header">
											                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											                <h4 class="modal-title" id="getCroppedCanvasTitle">Cropped</h4>
											              </div>
											              <div class="modal-body"></div>
											              <div class="modal-footer">
											                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											                <a class="btn btn-primary" id="download" href="javascript:void(0);" download="cropped.jpg">Download</a>
											              </div>
											            </div>
											          </div>
											        </div><!-- /.modal -->
											      </div><!-- /.docs-buttons -->
											
											    <div class="col-md-3 docs-toggles">
											        <!-- <h3 class="page-header">Toggles:</h3> -->
											        <div class="btn-group docs-aspect-ratios" data-toggle="buttons">
											          <div class="croped-img" id="fir">
											          <label class="btn btn-primary">
											            <span class="docs-tooltip" onclick="cll1();" data-toggle="tooltip" title="double click active view">
											             <img  id="call1" src="<%=request.getContextPath()%>/resources/images/images/zoom_profile1.png">
											            </span>
											          </label>
											          </div>
											          <div class="croped-img" id="sec">
											          <label class="btn btn-primary">
											            <span class="docs-tooltip" onclick="cll2();" data-toggle="tooltip" title="double click active view">
											             <img  id="call2" src="<%=request.getContextPath()%>/resources/images/images/zoom_profile2.png">
											            </span>
											          </label>
											          </div>
											          <div class="croped-img" id="thi">
											          <label class="btn btn-primary">
											            <span class="docs-tooltip" onclick="cll3();" data-toggle="tooltip" title="double click active view">
											           	<img  id="call3" src="<%=request.getContextPath()%>/resources/images/images/zoom_profile3.png">
											            </span>
											          </label>
											          </div>
											          <label class="btn btn-primary">
											            <input type="radio" class="sr-only" id="aspectRatio4" name="aspectRatio" value="1.7777777777777777" checked>
											            <span class="docs-tooltip" data-toggle="tooltip" title="aspectRatio: NaN">
											             16:9 
											            </span>
											          </label>
											       </div>
											       <!-- /.dropdown -->
											      </div><!-- /.docs-toggles -->
											    </div>
								</div> --%>
								
								
								
<center><input type="file" name="files" id="mi" class="Masking_image" style="display:none;"  onchange="javascript:updateList()" ><input type="button" onclick="document.getElementById('mi').click();" style="background-color: #05a42e;color: #fff;border: none; border-radius: 3px;margin:19px 0px 0px 0px; padding: 8px;font-size: 14px;" id="Button" value="Choose Background Mask"/></center><p class="subtext"><a href="https://support.iamuse.com/portal/kb/articles/advanced-masking-background-interactivity" target="_blank">A mask is a greyscale JPG image</a> that matches the Background dimensions, allowing you indicate which areas of the Background appear in front of Guests (Black) or behind (White). </p><div class="clearfix"></div>
<div id="fileList" onclick="resetFile();" style="cursor: pointer;"></div>
<c:if test="${not empty adminPictureVO1.imageMask}">
<a href="${adminPictureVO1.imageMask}" class="Mask" ><img src="${adminPictureVO1.imageMask}" height="100" width="125"/></a><span class="Mask" onclick="updateMaskingStatus();" style="cursor: pointer;">&nbsp;&nbsp;&#10005;</span>
</c:if>
<%-- <center><input type="file" name="files1" id="wmi" class="water_mark_file"  style="display:none;" onchange="javascript:updateList1()"><input type="button" onclick="document.getElementById('wmi').click();" style="background-color: #05a42e;color: #fff;border: none; border-radius: 3px;margin: 19px 0px 0px 0px; padding: 8px;font-size: 14px;" id="Button2" value="Upload WaterMark Image" /><div class="clearfix"></div></center>
<div id="fileList1" onclick="resetFile1();" style="cursor: pointer;"></div>	
<c:if test="${not empty adminPictureVO1.imageWaterMark}">
<a href="${adminPictureVO1.imageWaterMark}" class="Watermark">Watermark Image</a><span class="Watermark" style="cursor: pointer;" onclick="updateWaterMarkStatus();">&nbsp;&nbsp;&#10005;</span>
</c:if>		 --%>				
								<!-- <center><div data-step="3" data-intro="You can click done image to configure else You are now ready to run your booth">
								 <input type="submit" class="btn btn-green" style="margin-top:25px;width:auto;display:none;" id="uploadNow" value="Upload Now" name="finish" data-toggle="tooltip" title="You can select the next image to configure else You are now ready to run your booth"/> 
								</div></center> -->
							</div>
							</c:if>
							<div class="clearfix"></div>
						</div>
						<input type="hidden" name="picId" id="picId1" value="${adminPictureVO1.picId}">
						<input type="hidden" name="pictureId" id="picId2" value="${adminPictureVO1.picId}">
						<input type="hidden" value="${eid}" name="eId"/>
								<!-- <div class="docs-data" style="display: none;"> -->
								<div class="docs-data" style="display: none;">
							          <div class="input-group input-group-sm" >
							            <label class="input-group-addon" for="dataX">X</label>
							            <input type="text" class="form-control" id="dataX" placeholder="x" name="scaleXOffset"/>
							            <span class="input-group-addon">px</span>
							          </div>
							          <div class="input-group input-group-sm">
							            <label class="input-group-addon" for="dataY">Y</label>
							            <input type="text" class="form-control" id="dataY" placeholder="y"  name="scaleYOffset" />
							            <span class="input-group-addon">px</span>
							          </div>
							          <div class="input-group input-group-sm">
							            <label class="input-group-addon" for="dataWidth">Width</label>
							            <input type="text" class="form-control" id="dataWidth" placeholder="width" name="scalingWidth" />
							            <span class="input-group-addon">px</span>
							          </div>
							          <div class="input-group input-group-sm">
							            <label class="input-group-addon" for="dataHeight">Height</label>
							            <input type="text" class="form-control" id="dataHeight" placeholder="height" name="scalingHeight">
							            <span class="input-group-addon">px</span>
							          </div>
							          <div class="input-group input-group-sm">
							            <label class="input-group-addon" for="dataRotate">Rotate</label>
							            <input type="text" class="form-control" id="dataRotate" placeholder="rotate">
							            <span class="input-group-addon">deg</span>
							          </div>
							          <div class="input-group input-group-sm">
							            <label class="input-group-addon" for="dataScaleX">ScaleX</label>
							            <input type="text" class="form-control" id="demo1111" placeholder="scaleX" name="imageWidth">
							          </div>
							          <div class="input-group input-group-sm">
							            <label class="input-group-addon" for="dataScaleY">ScaleY</label>
							            <input type="text" class="form-control" id="demo2222" placeholder="scaleY"  name="imageHeight">
							          </div>
							          
							        </div>
							      <c:if test="${boothAdminLogin2.subId !=1}">
								<c:set var="first" value="${first}"/>
      							<c:set var="last" value="${last}"/>
      							<c:set var="current" value="${current}"/>
									<%-- <c:if test="${first != current}"><a href="setUpBackgroundImage?picId=${previous}&eId=${eid}&position=${position-1}" class="btn btn-green" style="padding:10px;background-color:#05a42e ;color:#fff;margin-right: 10px;margin-top:25px">Previous</a></c:if> --%>
									<c:if test="${first != current}"><input type="submit" class="btn btn-green" name="finish" value="Previous" style="width:auto;padding:10px;background-color:#05a42e ;color:#fff;margin-right: 10px;margin-top:25px;margin-bottom: 17px;"></c:if>
									<c:if test="${last != current}"><input type="submit" class="btn btn-green" name="finish" style="width:auto;margin-top:7px;padding: 10px 22px;" value="Next" data-toggle="tooltip" title="You can select the next image to configure else You are now ready to run your booth"/></c:if>
									<input type="submit" class="btn btn-green pull-right" style="width:auto;margin-top:25px" value="Save & Exit" data-toggle="tooltip" name="finish" title="You can select the next image to configure else You are now ready to run your booth"/> 
							 </c:if>
							<div class="clearfix"></div>
					</div>
					
					</div>
					</form:form>	
				</div>
				</body>
				<div class="clearfix"></div>
	<script src="<%=request.getContextPath()%>/resources/js/js/imgconfig/jquery.min.js"></script>
  	<!--  <script src="<%=request.getContextPath()%>/resources/js/js/imgconfig/bootstrap.min.js"></script>-->
  	<script src="<%=request.getContextPath()%>/resources/js/js/imgconfig/cropper.js"></script>
  	<script src="<%=request.getContextPath()%>/resources/js/js/imgconfig/main.js"></script>
  		