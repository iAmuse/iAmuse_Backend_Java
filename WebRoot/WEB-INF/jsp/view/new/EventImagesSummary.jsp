			<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script type="text/javascript">
      $(document).ready(function() {
    	  $('#effect').delay(9000).fadeOut(400);
            $("#b").addClass("active_menu");
        });
</script>	
<script type="text/javascript">
$(document).ready(function() {
    $('#selecctall').click(function(event) {  //on click 
        if(this.checked) { // check select status
            $('.checkbox1').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"               
            });
        }else{
            $('.checkbox1').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
            });         
        }
    });
    
});


	function export_image() {
	    //var arrCheckboxes = document.getElementById("deleteFiles").elements["checkbox1"];
	    var arrCheckboxes = document.getElementById("deleteFiles").getElementsByTagName("input");
	    var checkCount = 0;
	    for (var i = 0; i < arrCheckboxes.length; i++) {
	        checkCount += (arrCheckboxes[i].checked) ? 1 : 0;
	    }
	    if (checkCount > 0){
	    	document.getElementById("eventAction").value="export";
	       // return confirm("Are you sure you want to proceed deleting the selected   files?");
	        document.getElementById("form_id").submit();
	        return true;
	    } else {
	        alert("You do not have any selected files to export zip.");
	        return false;
	    }
	}
	function delete_image() {
	    //var arrCheckboxes = document.getElementById("deleteFiles").elements["checkbox1"];
	    var arrCheckboxes = document.getElementById("deleteFiles").getElementsByTagName("input");
	    var checkCount = 0;
	    for (var i = 0; i < arrCheckboxes.length; i++) {
	        checkCount += (arrCheckboxes[i].checked) ? 1 : 0;
	    }
	    if (checkCount > 0){
	    	document.getElementById("eventAction").value="delete";
	       // return confirm("Are you sure you want to proceed deleting the selected   files?");
	      document.getElementById("form_id").submit();
	        return true;
	    } else {
	        alert("You do not have any selected files to delete.");
	        return false;
	    }
	}
	function resend_image() {
	    //var arrCheckboxes = document.getElementById("deleteFiles").elements["checkbox1"];
	     var arrCheckboxes = document.getElementById("deleteFiles").getElementsByTagName("input");
	    var checkCount = 0;
	    for (var i = 0; i < arrCheckboxes.length; i++) {
	        checkCount += (arrCheckboxes[i].checked) ? 1 : 0;
	    }
	    if (checkCount > 0){
	    	document.getElementById("eventAction").value="resend";
	       // return confirm("Are you sure you want to proceed deleting the selected   files?");
	         document.getElementById("form_id").submit();
	        return true;
	    } else {
	        alert("You do not have any selected files to Resend.");
	        return false;
	    } 
	    
		
	}
	function share_image() {
	    //var arrCheckboxes = document.getElementById("deleteFiles").elements["checkbox1"];
	    var arrCheckboxes = document.getElementById("deleteFiles").getElementsByTagName("input");
	    var checkCount = 0;
	    for (var i = 0; i < arrCheckboxes.length; i++) {
	        checkCount += (arrCheckboxes[i].checked) ? 1 : 0;
	    }
	    if (checkCount > 0){
	    	document.getElementById("eventAction").value="share";
	    	var favorite = [];
            $.each($("input[name='imageIds']:checked"), function(){            
                favorite.push($(this).val());
            });
	       // return confirm("Are you sure you want to proceed deleting the selected   files?");
	       //document.getElementById("form_id").submit();
	       //var ids=document.getElementById("imgids")[0].value;
	       
	      // alert(ids);
	      window.location.href="socialShare?userId="+${boothAdminLogin.userId}+"&imageIds="+favorite.join(","); 
	        return true;
	    } else {
	        alert("You do not have any selected files to Share On Social.");
	        return false;
	    }
	}
</script>	

<script type="text/javascript"> 
function sentTestEmail(){
        	  var email=document.getElementById("emailId").value;
        	  var imgid=document.getElementById("imgid").value;
        	  var eventId=document.getElementById("eventId").value;
        	 
        	  window.location.href ="<%=request.getContextPath()%>/sendIndividualMailImage?emailId="+email+"&imgId="+imgid+"&eventId="+eventId;
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
  position: relative;
  top: 25%;
  left: 25%;
  width: 50%;
  height: 50%;
  padding: 16px;
  border: 16px solid #05a42e;
  background-color: white;
  z-index: 1002;
  overflow: auto;
margin-top:-y;
margin-left:-x;  
}

</style>



	<div class="col-lg-2 col-md-3" style="width:20%;"></div>
		<div class="right-pannel col-lg-10 col-xs-12">
					 <c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;" id="msg">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left">Event Summary</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:20px;">

					<form:form action="dbToImagesZip?eventId=${optionsReports.eventId}" modelAttribute="ImageEmailFormVO" id="form_id" >
					
						<div class="col-row">
							<div class="event-gallery-action">
								<%-- <div class="gallery-link">
									<h1>Link to Event Summary</h1>
									<a href="<%="http://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %>" target="_blank"><%="http://"+request.getServerName()+ ":" + request.getServerPort()%><%= request.getAttribute("javax.servlet.forward.request_uri")+"?"+request.getQueryString() %></a>
								</div> --%>
								<input type="hidden"  name="eventAction" id="eventAction">
								<input type="hidden"  name="eventId" id="eventId" value="${optionsReports.eventId }" >
								
								<%-- <div class="gallery-option">
									<h1># Guests Visited- <span style="font-size:20px;font-weight:bold;color:#05a42e">${optionsReports.totalGuestSessions }</span></h1>
									<span class="img-action">
										<a href="#" ><img src="<%=request.getContextPath()%>/resources/images/images/resend_img.png" onclick="resend_image();"></a>
										<p>Resend Img</p>
									</span>
									<span class="img-action">
										<a href="#"><img src="<%=request.getContextPath()%>/resources/images/images/share.png" onclick="share_image();"></a>
										<p>Share</p>
									</span>
									<span class="img-action">
										<a href="#"><img src="<%=request.getContextPath()%>/resources/images/images/export.png" onclick="export_image();"></a>
										<p>Export</p>
									</span>
									<span class="img-action">
										<a href="#"><img src="<%=request.getContextPath()%>/resources/images/images/delete-1.png" onclick="delete_image();"></a>
										<p>Delete</p>
									</span>
									<div class="clearfix"></div>
								</div> --%>
								<div class="clearfix"></div>
							</div>
							<!--   <th class="head"><input type="checkbox" id="selecctall"/>select all/unselect all</th> -->
							<div class="gallery" id="deleteFiles">
							<c:if test="${emailImagesList.size() > 0}">
							<c:forEach items="${emailImagesList}" varStatus="loop" var="igl">
								<div class="gallery-div">
									<div class="img-pic" >
										<img src="${igl.mailImageUrl}">
										<%-- <p class="event-time">${igl.uploadTime}</p> --%>
										<%-- <input type="checkbox" class="checkbox1" id="imgids" name="imageIds" value="${igl.id }" > --%>
									</div>
									
								</div>
							</c:forEach>
							</c:if>
								<c:if test="${emailImagesList.size() == 0}">
								<center><div><b>No Event Summary</b></div></center>
						</c:if>
								<div class="clearfix"></div>
							</div>
							 	<input type="hidden" value="${pageid}" id="a1"/>
       							<input type="hidden" value="${total}" id="a2"/>
							<%-- <div class="pageing">
								<c:set var="pC" value="${pageCount}"/>
      								<c:set var="pId" value="${pageid}"/>
								<ul class="pagination">
									<li ><c:if test="${pId > 1}"><a href="eventGallery?eventId=${optionsReports.eventId }&pageid=${pageid-1}&total=${total}">&laquo;</a></c:if> </li>
									<c:forEach var="p" begin="1" end="${pageCount}"><li><a href="eventGallery?eventId=${optionsReports.eventId }&pageid=${p}&total=${total}">${p}</a></li></c:forEach>
									<li><c:if test="${pId < pC}"><a href="eventGallery?eventId=${optionsReports.eventId }&pageid=${pageid+1}&total=${total}">&raquo;</a></c:if> </li> 
								</ul>
							</div> --%>
							<div class="clearfix"></div>
						</div>
						</form:form>			
									
					</div>
				</div>
				<div class="clearfix"></div>
				<script type="text/javascript"> 
				
function open1(id,emailId) {
   $("#emailId").val(emailId);
   $("#imgid").val(id);
     // document.getElementById('abc').style.display = "block";
      document.getElementById('light').style.display='block';
      document.getElementById('fade').style.display='block';
    	  }
    	  
function close1(){	  
document.getElementById('light').style.display='none';
document.getElementById('fade').style.display='none';
}
</script>

  <div id="light" class="white_content">
  <input id="emailId" name="emailId" value="" type="text" style="margin:39px 20px 30px 10px;width: 91%;">
<input id="imgid" name="imgId" placeholder="Name" value="" type="hidden" style="margin:39px 20px 30px 10px;width: 91%;">
<button onclick="sentTestEmail();" class="btn btn-green" style="float: right;margin-right: 10%;">send</button>
<button onclick="close1();" style="float:right;margin-right:10px;" class="btn btn-green">Close</button>
  </div>
  <div id="fade" class="black_overlay"></div>
				