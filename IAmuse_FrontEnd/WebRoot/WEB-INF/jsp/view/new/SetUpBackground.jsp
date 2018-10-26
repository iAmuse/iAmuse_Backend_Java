<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
 	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/font-awesome.min.css"/>
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/bootstrap.min.css"/>
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/cropper.css"/>
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/css/imgconfig/main.css"/>
  	<script>
  	setTimeout(function () {
  		var zoom =$('#zoomedScaledVersion').val();
  		 if(zoom=='1.00'){
  			 $(".cropper-view-box").attr("src", "/IAmuse/resources/images/images/zoom_profile1.png"); 
  		}else if(zoom=='0.50'){
  			 $(".cropper-view-box").attr("src", "/IAmuse/resources/images/images/zoom_profile3.png"); 
  		}else if(zoom=='0.75'){
  			 $(".cropper-view-box").attr("src", "/IAmuse/resources/images/images/zoom_profile2.png");
  		} 
		}, 1000);
	$("document").ready(function(){
  		//$("#uploadNow").css('display', 'none');
  		$("#mi").change(function () {
  	    	$("#uploadNow").css('display', 'block');
  	    });
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
 	var x1 = document.getElementById("imgNatural").naturalHeight;
    document.getElementById("demo2222").value = x1;
	});
	
$( document ).ready(function() {
	var texte=document.getElementById("picTitle").value;
	if(texte!=''){
		$('.formal').attr('readonly', true);
	}
});
</script>
<script type="text/javascript">
	function updateList() {
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

<c:if test="${boothAdminLogin.planCode =='1'}">
<script>
$( document ).ready(function() {
	document.getElementById("Button").disabled = true;
	document.getElementById("Button2").disabled = true;
});
</script>
	</c:if>
 <style>
 	.btn-primary{background:none !important;border:0px;}
 	.right-pannel{height:auto !important;}
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
<div class="right-pannel">
		<h1 class="heading pull-left">Setup Custom Background</h1>
<form:form action="saveCoordinatesOfImg" modelAttribute="AdminPictureVO" enctype="multipart/form-data">

					 <div >
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
					<h2 class="heading" style="margin: -15px 0px 15px 0px;"> Background ${position} of ${adminPictureVOs2.size()} </h2>
						<div class="col-row">
							<div class="background-priview"  data-toggle="tooltip" title="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
								<div class="background-image">
									<div class="img-container cropper-bg" id="target" data-step="2" data-intro="You can drag, resize the window to the location where you want the camera image to appear. You can also use the zoom profile. You can also upload Masking image and Watermark image. Click on Done after you have made all configurations">
							          <img src="${adminPictureVO1.picName}" alt="Picture" id="imgNatural"/>
							        </div>
							        
							  <%--  <input type="hidden" name="positionPrev"  value="${position-1}"/>  --%> 
							<input type="hidden" name="position"  value="${position+1}"/>    
							<input type="hidden" name="cropImgX" id="x" value="${adminPictureVO1.cropImgX}"/>
						 	<input type="hidden" name="cropImgY" id="y" value="${adminPictureVO1.cropImgY}"/>
						 	<input type="hidden" name="cropImgWidth" id="w" value="${adminPictureVO1.cropImgWidth}"/>
						 	<input type="hidden" name="cropImgHeight" id="h2" value="${adminPictureVO1.cropImgHeight}"/>	
						 				        
								</div>
								<div class="title title-textbox"  data-step="1" data-intro="Enter the title for the image"><input type="text" class="formal" id="picTitle" name="picTitle" style="width:94% !important;" placeholder="Enter Title" value="${adminPictureVO1.picTitle}" data-toggle="tooltip" title="Enter the title for the image"/><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png" onclick="fun()" style="cursor: pointer;margin-left:15px;" ></div>
								<div class="img-position-controls">
								<input type="hidden" placeholder="Z-Scale" name="scaleZOffset" id="scaleZOffset" value="${adminPictureVO1.scaleZOffset}" readonly="readonly">
									<!-- </div> -->
									<div class="clearfix"></div>
								</div>
							</div>
							<c:if test="${boothAdminLogin.planCode =='1'}"><center><p class="subtext">This Background Is Default Background You Can't Configure According To Your Choice .</p><div class="clearfix"></div></center></c:if>
							<c:if test="${boothAdminLogin.planCode !='1'}">
							<div class="background-position">
								
								
<center><input type="file" name="files" id="mi" class="Masking_image" style="display:none;"  onchange="javascript:updateList()" ><input type="button" onclick="document.getElementById('mi').click();" style="background-color: #05a42e;color: #fff;border: none; border-radius: 3px;margin:19px 0px 0px 0px; padding: 8px;font-size: 14px;" id="Button" value="Choose Background Mask"/><p class="subtext">A mask is a greyscale JPG image that matches the Background dimensions, allowing you indicate which areas of the Background appear in front of Guests (Black) or behind (White). </p><div class="clearfix"></div></center>
<div id="fileList" onclick="resetFile();" style="cursor: pointer;"></div>
<c:if test="${not empty adminPictureVO1.imageMask}">
<a href="${adminPictureVO1.imageMask}" class="Mask" ><img src="${adminPictureVO1.imageMask}" height="100" width="200"/></a><span class="Mask" onclick="updateMaskingStatus();" style="cursor: pointer;">&nbsp;&nbsp;&#10005;</span>
</c:if>
								<center><div data-step="3" data-intro="You can click done image to configure else You are now ready to run your booth">
								 <input type="submit" class="btn btn-green" style="margin-top:25px;width:auto;display:none;" id="uploadNow" value="Upload Now" name="finish" data-toggle="tooltip" title="You can select the next image to configure else You are now ready to run your booth"/> 
								</div></center>
							</div>
							</c:if>
							<div class="clearfix"></div>
						</div>
						<input type="hidden" name="picId" id="picId1" value="${adminPictureVO1.picId}">
						<input type="hidden" name="pictureId" id="picId2" value="${adminPictureVO1.picId}">
						<input type="hidden" value="${eid}" name="eId"/>
						<input type="hidden" value="${adminPictureVO1.zoomScale}" id="zoomedScaledVersion"/>
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
							        <c:if test="${boothAdminLogin.planCode !='1'}">
								<c:set var="first" value="${first}"/>
      							<c:set var="last" value="${last}"/>
      							<c:set var="current" value="${current}"/>
									<%-- <c:if test="${first != current}"><a href="setUpBackgroundImage?picId=${previous}&eId=${eid}&position=${position-1}" class="btn btn-green" style="padding:10px;background-color:#05a42e ;color:#fff;margin-right: 10px;margin-top:25px">Previous</a></c:if> --%>
									<c:if test="${first != current}"><input type="submit" class="btn btn-green" name="finish" value="Previous" style="width:auto;padding:10px;background-color:#05a42e ;color:#fff;margin-right: 10px;margin-top:25px"></c:if>
									<c:if test="${last != current}"><input type="submit" class="btn btn-green" name="finish" style="width:auto;margin-top:25px;padding: 10px 22px;" value="Next" data-toggle="tooltip" title="You can select the next image to configure else You are now ready to run your booth"/></c:if>
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
  	<script src="<%=request.getContextPath()%>/resources/js/js/imgconfig/bootstrap.min.js"></script>
  	<script src="<%=request.getContextPath()%>/resources/js/js/imgconfig/cropper.js"></script>
  	<script src="<%=request.getContextPath()%>/resources/js/js/imgconfig/main.js"></script>
  		