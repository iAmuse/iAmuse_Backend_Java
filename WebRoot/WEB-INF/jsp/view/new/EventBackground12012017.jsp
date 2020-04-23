	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
			 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/eventBackground.js">  </script>
			 <script>
			 var selectedCheckBox = new Array();
			 function handleClick(id){
				 selectedCheckBox.push(id);
				 $('#selectedPreImage').val(selectedCheckBox);
			 }
			 </script>
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
	<script type="text/javascript">
/*
   Replacing Submit Button with 'Loading' Image
   Version 2.0
   December 18, 2012

   Will Bontrager Software, LLC
   http://www.willmaster.com/
   Copyright 2012 Will Bontrager Software, LLC

   This software is provided "AS IS," without 
   any warranty of any kind, without even any 
   implied warranty such as merchantability 
   or fitness for a particular purpose.
   Will Bontrager Software, LLC grants 
   you a royalty free license to use or 
   modify this software provided this 
   notice appears on all copies. 
*/
function ButtonClicked()
{
	   
	   var existingBackground=$("#selectedPreImage").val(); 
	    
	    var uploadBackground = $('#images2').val(); 
	    
	    if(existingBackground==0 && uploadBackground=="")
	     {
	      alert("select atleast one background"); 
	      return false;
	     }
   document.getElementById("formsubmitbutton").style.display = "none"; // to undisplay
   document.getElementById("buttonreplacement").style.display = ""; // to display
   return true;
}
var FirstLoading = true;
function RestoreSubmitButton()
{
   if( FirstLoading )
   {
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
.right-pannel{height:auto !important;}
</style>
 <script type="text/javascript"> 
				
function open1() {
 
      document.getElementById('light').style.display='block';
      document.getElementById('fade').style.display='block';
    	  }
    	  
function close1(){	  
document.getElementById('light').style.display='none';
document.getElementById('fade').style.display='none';
}
</script>
	<!-- $('#images2').change(function(){
		alert("dsf");
	    var files = $(this)[0].files;
	    alert(files.length);
	   document.getElementById("msgshow1").innerHTML= files.length+"Image Selected";
	});-->
	
<%-- <c:if test="${boothAdminLogin.subId==2}">
		<div class="right-pannel">
					<h1 class="heading pull-left">Create Events</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<form action="event-background.html">
								<table class="table table-bordered" style="width:30%">
									<tr>
										<td style="font-weight:600">Event Date</td>
										<td>${event.eventStart }</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Event</td>
										<td>${event.eventName}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Sponsor</td>
										<td>${event.sponsorName}</td>
									</tr>
								</table>
								</form>
								<form:form action="uploadBackgroundImage" modelAttribute="AdminPictureVO" onsubmit="return validateForm2();" enctype="multipart/form-data" method="post">
								<input type="hidden" name="eventStart" value="${event.eventStart }"/>
								<input type="hidden" name="eventName" value="${event.eventName}"/>
								<input type="hidden" name="sponsorName" value="${event.sponsorName}"/>
										<div class="form_row" style="margin-top:35px;">									
								<input type="hidden" value="${eid}" name="eId"/>
									<span class="upload_file">
										<div class="file_container">
										onchange='this.form.submit()'
											<input type="file" name="files" id="images2" multiple accept=".jpg,.png,.gif,.bmp" >
										</div>
									</span>
									<input type="submit" value="Submit" style="width:auto" class="btn btn-green">
									<div class="clearfix"></div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
</c:if> --%>
<%-- <c:if test="${boothAdminLogin.subId!=1 }"> --%>
		<div class="right-pannel">
					<h1 class="heading pull-left">Create Events</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<form action="event-background.html">
								<table class="table table-bordered" style="width:50%">
									<tr>
										<td style="font-weight:600">Event Date</td>
										<td>${event.eventStart }</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Event</td>
										<td>${event.eventName}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Host</td>
										<td>${event.sponsorName}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Event Host Email</td>
										<td>${event.eventHostMailerId}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Facebook</td>
										<td>${event.facebook}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Twitter</td>
										<td>${event.twitter}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Email Body</td>
										<td>${event.emailBody}</td>
									</tr>
								</table>
								</form>
								<div id="light" class="white_content">
  
  					<c:if test="${emailImagesList.size() > 0}">
							<c:forEach items="${emailImagesList}" varStatus="loop" var="igl">
								<div class="gallery-div gallery-div-popup">
									<div class="img-pic" >
										<img src="${igl.mailImageUrl}">
										<input type="checkbox" class="checkbox1" id="imgids" name="imgids" value="${igl.id }" onclick='handleClick(this.value);' >
									</div>
								</div>
							</c:forEach>
							</c:if>
								<c:if test="${emailImagesList.size() == 0}">
								<center><div><b>No Event Summary</b></div></center>
						</c:if>
  
  
<button onclick="close1();" style="float:right;margin-right:10px;" class="btn btn-green">Done</button>
  </div>
  <div id="fade" class="black_overlay"></div>
								<form:form action="uploadBackgroundImage" modelAttribute="AdminPictureVO" onsubmit="return validateForm2();" enctype="multipart/form-data" method="post">
								<input type="hidden" name="eventStart" value="${event.eventStart }"/>
								<input type="hidden" name="eventName" value="${event.eventName}"/>
								<input type="hidden" name="sponsorName" value="${event.sponsorName}"/>
								
								<input type="hidden" name="eventHostMailerId" value="${event.eventHostMailerId}"/>
								<input type="hidden" name="facebook" value="${event.facebook}"/>
								<input type="hidden" name="twitter" value="${event.twitter}"/>
								<input type="hidden" name="emailBody" value="${event.emailBody}"/>
								<input type="hidden" name="selectedPreImage" id="selectedPreImage" value="0"/>
								<div class="form_row" style="margin-top:35px;" >									
								
								<input type="hidden" value="${eid}" name="eId"/>
								<div class="file_container file-container-1" data-step="3" data-intro="Upload one or multiple events by keeping the control key pressed" data-toggle="tooltip" title="Upload one or multiple events by keeping the control key pressed">
									<span class="upload_file"  style="position:relative;">
											<input type="file" name="files" id="images2" multiple accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" style="margin-left:-12px;" />
										
										<a href="javascript:void(0)" class="btn btn-green choose_selectedbg" style="width: 55px auto !important;font-size: 10px;" onclick="open1();">Select Pre-Set Background</a>
									</span>
									
									<div class="clearfix"></div>
								</div>
								<p>&nbsp;&nbsp;*&nbsp;press ctrl to select multiple Images</p>
								<div class="clearfix"></div>
									<div id="fileList"></div>
									
									<div class="form-row upload-screens">
										<div><input type="file" name="thankyoufiles" id="images2"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" /></div>
												
								         <div><input type="file" name="cameraTVScreenSaver" id="images2"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" /></div>
										
										<div><span>Upload Look At Touch Screen		</span><input type="file" name="lookAtTouchScreen" id="images2"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" /></div>
										
									<div><span>Upload Water Mark Image	</span>	<input type="file" name="waterMarkImage" id="images2"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" /></div>
										<div class="clearfix"></div>
									</div>
									</div>
									<div id="formsubmitbutton">
									
									
									
									
						
						
						
										<div style="width:275px;margin:20px 0px;"><div data-step="4" data-intro="Submit the images after you have selected them"><input type="submit" value="Submit" style="width:auto" class="btn btn-green" data-toggle="tooltip" title="Submit the images after you have selected them" onclick="ButtonClicked()"></div></div>
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
				<div class="clearfix"></div>
<%-- </c:if> --%>
<%-- <c:if test="${boothAdminLogin.subId==1}">
		<div class="right-pannel">
					<h1 class="heading pull-left">Create Events</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<form action="event-background.html">
								<table class="table table-bordered" style="width:30%">
									<tr>
										<td style="font-weight:600">Event Date</td>
										<td>${event.eventStart }</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Event</td>
										<td>${event.eventName}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Sponsor</td>
										<td>${event.sponsorName}</td>
									</tr>
								</table>
								</form>
								<form:form action="uploadBackgroundImage" onsubmit="return validateForm1();" modelAttribute="AdminPictureVO" enctype="multipart/form-data" method="post">
								<input type="hidden" name="eventStart" value="${event.eventStart }"/>
								<input type="hidden" name="eventName" value="${event.eventName}"/>
								<input type="hidden" name="sponsorName" value="${event.sponsorName}"/>
								<div class="form_row" style="margin-top:35px;">									
								<input type="hidden" value="${eid}" name="eId"/>
									<span class="upload_file">
									<div class="file_container">
										<input type="file" name="files" id="images1" accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList1()" />
									</div>
									<p>&nbsp;&nbsp;*&nbsp;press ctrl to select multiple Images</p>
									</span>
									<div id="fileList1"></div>
									<div id="formsubmitbutton">
									<div style="width:275px;margin:20px 0px;text-align:center;"><input type="submit" value="Submit" style="width:auto" class="btn btn-green" onclick="ButtonClicked()"></div>
									</div>
									<div id="buttonreplacement" style="margin-left:100px; display:none;">
										<img src="http://www.willmaster.com/images/preload.gif" alt="loading...">
									</div>
									<div class="clearfix"></div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
</c:if> --%>