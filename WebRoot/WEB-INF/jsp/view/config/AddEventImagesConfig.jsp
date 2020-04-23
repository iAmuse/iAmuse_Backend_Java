<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/uploadImage.js">  </script>
  <link href="<%=request.getContextPath()%>/resources/css/css/bootstrap.min.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/resources/css/css/jcarousel.responsive.css" rel="stylesheet">
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/jquery.jcarousel.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/jcarousel.responsive.js"></script>
  
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/eventBackground.js">  </script>
<script>
$(document).ready(function(){
    $("#images2").on("change", function() {
    	this.form.submit();
    });
});

function setPosition(id){
	 $('#position').val(id);
}


$(document).ready(function() {
    function disableBack() { 
    	window.history.forward() 
    	}
    window.onload = disableBack();
    window.onpageshow = function(evt) { 
    	}
});

function deletEventPicture(picId,eventId){
	  window.location.href ="<%=request.getContextPath()%>/delEventPicture?picId="+picId+"&eventId="+eventId;
}
</script>  
<script type="text/javascript">
	$(document).ready(function(){
		var subId= document.getElementById("subid").value;
		if(subId==1){
			$(".demohide").attr('disabled', 'disabled');
			$('.btn-green').attr('disabled', 'disabled');
		}
	});	
	
	function updateList() {
  		var input = document.getElementById('images2');
  		var output = document.getElementById('fileList');
  		output.innerHTML = '<ul>';
  		for (var i = 0; i < input.files.length; ++i) {
  			 var imageSize = input.files[i].size;
  		    if(imageSize > 5000000){
  		    	document.getElementById("gif").style.display = "none";
  		    alert("Your file size is to large file size is "+ (imageSize / (1024*1024)).toFixed(2)+ "MB")
  		    
  		    return false;
  			}else{
  		    	//continue ++;
  		    	output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  		    }
  		}
  		output.innerHTML += '</ul>';
	}
	
	/* function updateList1() {
  		var input = document.getElementById('images1');
  		var output = document.getElementById('fileList1');
  		output.innerHTML = '<ul>';
  		for (var i = 0; i < input.files.length; ++i) {
    		output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  		}
  		output.innerHTML += '</ul>';
	} */
</script>
<script type="text/javascript">

	function ButtonClicked(){
		var existingBackground=$("#selectedPreImage").val(); 	    
	    var uploadBackground = $('#images2').val();
	    if(existingBackground==0 && uploadBackground==""){
	    	alert("Please select at least 1 Background"); 
	      	return false;
	    }
   		document.getElementById("formsubmitbutton").style.display = "none"; // to undisplay
   		document.getElementById("buttonreplacement").style.display = ""; // to display
	}
	var FirstLoading = true;
	function RestoreSubmitButton(){
   	if( FirstLoading ) {
      FirstLoading = false;
      return;
   	}
   document.getElementById("formsubmitbutton").style.display = ""; // to display
   document.getElementById("buttonreplacement").style.display = "none"; // to undisplay
}
// To disable restoring submit button, disable or delete next line.
document.onfocus = RestoreSubmitButton;
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
	/*.right-pannel{height:auto !important;}*/
</style>
 <script type="text/javascript"> 
				
function open3() {
document.getElementById('light').style.display='block';
document.getElementById('fade').style.display='block';
document.getElementById('selectedPreImage').value='0';
$('.checkbox1').removeAttr('checked');

    	  }
    	  
function close1(){ 
	   	
	   	var arrCheckboxes = document.getElementById("deleteFilesss").getElementsByTagName("input");
	    var checkCount = 0;
	    for (var i = 0; i < arrCheckboxes.length; i++) {
	        checkCount += (arrCheckboxes[i].checked) ? 1 : 0;
	    }
	    if (checkCount > 0){
	    	document.getElementById('light').style.display='none';
		   	document.getElementById('fade').style.display='none';
		   	document.getElementById("gif").style.display = "";
	    	
	    	var favorite = [];
            $.each($("input[name='imgidss']:checked"), function(){            
                favorite.push($(this).val());
            });
	   	
            document.getElementById("selectedPreImage").value=favorite.join(",")
            setTimeout(myFun, 300);
            return true;
	    } else {
	        alert("Please select at least 1 Background");
	        return false;
	    }
	 
	 }
function close3(){ 
	document.getElementById('light').style.display='none';
   	document.getElementById('fade').style.display='none';
}
	 function rem(re){
		 var ids=  $('#selectedPreImage').val();
		 $('#selectedPreImage').val("");
		 var splitIds=ids.split(",");
		 if(splitIds.length==1)
		 {
		 $('#selectedPreImage').val("0");
		 }
		 for(var i=0;i<splitIds.length;i++)
			 {
			  if(splitIds[i]!=re)
				{
				  $('#selectedPreImage').val($('#selectedPreImage').val()+","+splitIds[i]);
			    }
			 }
		 
		 var str=$('#selectedPreImage').val();
		
		 while(str.charAt(0) === ',')
			 {
			    str = str.substr(1);
			 }
		 
		 $('#selectedPreImage').val(str);
	      $("#abc"+re).remove();
	    }
	 function myFun(){
		 var idsssssss=document.getElementById("selectedPreImage").value;
		  if(idsssssss !=0 && idsssssss !=""){
			  document.querySelectorAll("input[type=submit]")[0].click();
			}
	 }
	 jQuery(document).ready(function() {  
		  
			jQuery('#slideshowContainer').jcarousel({  
			    scroll: 1,  
			    auto: 2,  
			    wrap: 'last',
			    easing: 'linear'  
			     });  
			  
			});  
</script>
<style>
	.right-pannel{position:absolute !important;}
	.event-bg{position:relative;}
	#gif {
width:100%;
height:100%;
opacity:.95;
top:0;
left:0;
text-align:center;
position:fixed;
background-color:#313131;
overflow:auto;
z-index: 1000;
padding-top:20%;
}
</style>
<div id="gif" style="display: none;">
	<img src="https://www.willmaster.com/images/preload.gif">
</div>
<div class="col-lg-2" style="width:20%;"></div>
		<div class="right-pannel col-lg-10 col-xs-12">
		<a href="publishNow?eid=${eid}"><input type="button" class="btn btn-green pull-right" value="Publish Now" style="width:auto;margin-right: 10px;margin-top:10px;" data-toggle="tooltip" title=""></a>
					<div class="clearfix"></div>
					<!-- <ul class="nav nav-tabs">
									    <li><a data-toggle="tab" href="#">Step 1<br/>Register Devices</a></li>
									    <li><a data-toggle="tab" href="#">Step 2<br/>Setup Booth</a></li>
									    <li class="active"><a data-toggle="tab" href="#" style="background-color: #05a42e ! important;color: #ffffff  ! important;">Step 3<br/>Setup Events</a></li>
									 </ul> -->
					<div class="inner-content" style="padding:35px;margin-top:20px;">
						<div class="col-row">
								<h1 class="heading" style="margin:10px 0px 0px;color:#2363c5; border-color: black;">Backgrounds for Event</h1>
								<p class="subtext">Choose the Backgrounds that will be available to your Guests at this Event</p>
								<div class="grid-row" id="result">
									<!-- <div class="grid-img event-bg">
										
									</div> -->
									<div class="clearfix"></div>
								</div>
								<div class="grid-row" id="result2">
								         <!-- <div class="grid-img event-bg">
								          
								         </div> -->
								         <div class="clearfix"></div>
								</div>
								<div class="clearfix"></div>
								
  
  <div id="light" class="white_content">
     <center style="margin: 10px 0px 30px;">
      <b>current zoom scale &nbsp; (<c:if test="${fovbyuser.zoomScale==1.00}">Zoomed In</c:if><c:if test="${fovbyuser.zoomScale==0.50 || fovbyuser.zoomScale==0.5}">Zoomed Out</c:if><c:if test="${fovbyuser.zoomScale==0.75}">Medium Zoom</c:if>)</b><br/>
    </center>
    <div class="gallery" id="deleteFilesss">
    <c:if test="${emailImagesList.size() > 0}">
     <c:forEach items="${emailImagesList}" varStatus="loop" var="igl">
      
      <div class="gallery-div gallery-div-popup">
       <div class="img-pic">
        <img src="${igl.mailImageUrl}"> <input type="checkbox" class="checkbox1" id="imgids" name="imgidss" value="${igl.id}" />
       </div>
      </div>
     </c:forEach>
    </c:if>
    </div>
    <c:if test="${emailImagesList.size() == 0}">
     <center>
      <div>
       <b>Empty</b>
      </div>
     </center>
    </c:if>
    <div class="clearfix"></div>
    <button onclick="return close3();" style="float: right; margin-left: 10px;" class="btn btn-green">Close</button>
    <button onclick="return close1();" style="float: right;" class="btn btn-green">Done</button>
   </div>
 <div id="fade" class="black_overlay"></div>
								<form:form action="addUploadBackgroundImageConfig" modelAttribute="AdminPictureVO" onsubmit="return validateUpdateForm();" enctype="multipart/form-data" method="post" id="fileUploadForm">
								<input type="hidden" value="${boothAdminLogin2.subId}" id="subid"/>
								<input type="hidden" name="selectedPreImage" id="selectedPreImage" value="0" />
								
								
								<div class="form_row" style="margin-top:35px;" >									
								
								<input type="hidden" value="${eid}" name="eId"/>
								<div class="demohide">
								
								<div class="upload-bg">
									<div class="form_label" style="width:27%">Upload Custom Background(s)</div>
									<div class="form_element">
										<input type="file" name="files" id="images2"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" class="demohide" style="margin-bottom:10px;margin-left:-12px;padding-bottom:5px;" />
										<!-- <p class="subtext" style="margin-top: -12px;">*press ctrl to select multiple Images</p> -->
										
									</div>
									<div class="clearfix"></div>
									<a href="javascript:void(0)" class="btn btn-green " style="margin:-40px 40px 0px;width:38%;font-size: 14px;float: right;" onclick="open3();">Choose Background(s) From Library</a>
								</div>
									</div></div>
									<div class="form_label" ><div style="width:275px;margin:20px 0px;"><div data-step="4" data-intro="Submit the images after you have selected them"><input type="submit" value="Publish Now" style="width:auto;display:none" class="btn btn-green" data-toggle="tooltip" title="Submit the images after you have selected them" onclick="return ButtonClicked()"></div></div></div>
									<div class="clearfix"></div>
									<div id="buttonreplacement" style="margin-left:100px; display:none;">
										<img src="https://www.willmaster.com/images/preload.gif" alt="loading...">
									</div>
									</form:form>
									
									
						<div class="clearfix"></div>
					</div>
				</div>
				<c:if test="${adminPictureVOs2.size()>0}">
				<div class="inner-content" style="padding:35px;">
				<h1 class="custom-heading">Backgrounds for the Event</h1>
									<form:form action="setUpBackgroundImageConfig" modelAttribute="AdminPictureVO" onclick="return validateId();">
							<div class="slide_docs">
								<div class="jcarousel-wrapper" data-step="5" data-intro="Select the desired image to be configured by clicking on the radio button and click on Configure Image">
								<input type="hidden" value="${eid}" name="eId"/>
								<input type="hidden" name="position" id="position" value="">
								<input type="hidden" id="imgIdofConfig" value="${adminPictureVOs2.size()}">
								<c:if test="${notConfiguredImage!=0}"><p class="subtext" style="margin-bottom: 10px;color:red;">You have not configured <b>${notConfiguredImage}</b> out of <b>${adminPictureVOs2.size()}</b> backgrounds</p></c:if>
								<!-- 	<div class="jcarousel" style="padding-left:5px !important" id="slideshowContainer">
										<ul> -->
										<div class="scroll_slider">
										<c:forEach items="${adminPictureVOs2}" var="picBackground" varStatus="loop" >
											<c:if test="${picBackground.imageHeight !=null}">
										            <div class="col-sm-6 col-md-3 ">
										            	<div class="grid_view_img radio_cross"><img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"><c:if test="${boothAdminLogin2.subId!=1}"><span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></c:if></div>
										            	<p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										            </div>
										    </c:if>
										    <c:if test="${picBackground.imageHeight ==null}">
										        <div class="col-sm-6 col-md-3 ">
										            <div class="grid_view_img radio_cross"  style="border: 2px solid red;">
										            	<img src="${picBackground.picName}"><input type="radio" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"><c:if test="${boothAdminLogin2.subId!=1}"><span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></c:if>
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										        </div>
										    </c:if>
											<%-- <li>
												<div class="thumbnail_container" id="">
												<c:if test="${picBackground.imageHeight !=null}">
										            <div class="thumbnail" >
										              <div class="grid_view_img"><img src="${picBackground.picName}"><input type="radio" style="left: 10px;cursor: pointer; position: absolute; right: 5px; width: 25px;" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"> <span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></div>
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										         </c:if>
										         <c:if test="${picBackground.imageHeight ==null}">
										          <div class="thumbnail" style="border: 2px solid red;">
										              <div class="grid_view_img"><img src="${picBackground.picName}"><input type="radio" style="left: 10px;cursor: pointer; position: absolute; right: 5px; width: 25px;" name="picId" class="configImg" value="${picBackground.picId}" onclick="setPosition(${loop.index+1})"> <span id="crossId" class="configImg"  style="float: right;cursor: pointer;position: absolute;right: 10px;" onclick="deletEventPicture('${picBackground.picId}','${eid}')"  >&#10005;</span></div>
										            </div>
										            <p class="subtext event_thumbnail_text" style="">${picBackground.picTitle}</p>
										         </c:if>
										         </div>
											</li> --%>
										</c:forEach>											
										<!-- </ul> -->
									</div>
									<!-- <a href="#" class="jcarousel-control-prev">&lsaquo;</a>
									<a href="#" class="jcarousel-control-next">&rsaquo;</a> -->
									<!-- <p class="jcarousel-pagination"></p> -->
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="blank_line"></div>
							<div class="configure_img">
								<input type="submit" class="btn btn-green" id="submit" value="Edittttt Background" style="width:auto" data-toggle="tooltip" title="Select the desired image to be configured by clicking on the radio button and click on Configure Image">
							</div>
							</form:form>
				</div>
				</c:if>
			</div>
		<div class="clearfix"></div>
 