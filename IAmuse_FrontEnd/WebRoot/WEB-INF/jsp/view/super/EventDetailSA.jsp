
	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<script type="text/javascript">
      $(document).ready(function() {
    	  $('#effect').delay(9000).fadeOut(400);
            $("#d").addClass("active_menu");
        });
      function eventGallery(){
    	  window.location.href ="<%=request.getContextPath()%>/eventGallerySA?eventId="+${events.EId};
      }
</script>
    <script type="text/javascript">
    function printDiv(divName) {
        var printContents = document.getElementById(divName).innerHTML;
        var originalContents = document.body.innerHTML;

        document.body.innerHTML = printContents;

        window.print();

        document.body.innerHTML = originalContents;
   }
    </script>
     <script type="text/javascript">
      $(document).ready(function() {
            
            var emailCsvLength=document.getElementById("emailCsvLength").value;
          
               $("#csv").click(function()
                    {
                if(emailCsvLength == 0 ){
                	alert("No Records Found");
                	return false;
                }else{
                    window.location.href="exportsContact"; 
                }
               });
        });
</script>
<script>
$(document).ready(function() {
	  $( "#sd" ).datepicker({ 
		  dateFormat: 'yy-mm-dd' ,
		  minDate:0
		  }).val();
       });
       </script>
<style>
      	.right-pannel{height:auto !important;}
      </style>
				<div class="right-pannel" id="printableArea">
					<h1 class="heading pull-left" style="padding-top:15px;">Event Summary</h1>
					<!-- <a href="#" class="pull-right"><button class="btn btn-green"  onclick="printDiv('printableArea')">Export Contacts</button></a> -->
					
						<a href="getContactEmail?eventId=${events.EId}" class="pull-right"><button class="btn btn-green">Guest Email Addresses</button></a>
					<input type="hidden" value="${fn:length(emailImagesList)}" id="emailCsvLength"/>
					
					<a href="#" class="pull-right"><button class="btn btn-green" style="margin-right:10px;" onclick="eventGallery()">See Event Gallery</button></a>
					<!-- <a href="#" class="pull-right"><button class="btn btn-green" style="margin-right:15px;">Export Contacts</button></a> -->
					<div class="clearfix"></div>
					<div class="inner-content" style="background:none;border:none;">
						<div class="col-row">						
							<div class="event_detail_col">
								<div style="border-bottom:1px solid #c7c7c7;padding-bottom:8px;margin-bottom:10px">
									<h1 class="pull-left" style="border:none;margin:0px;">Event Details</h1>
									<c:if test="${boothAdminLogin2.userRole=='superadmin' && events.eventType=='default' || events.eventType=='No'}"><button class="btn btn-green pull-right" style="float:right;margin-top:-10px;" id="hide">edit</button></c:if>
									<div class="clearfix"></div>
								</div>
								<form:form action="saveAdminDetails" modelAttribute="EventVO" >
								<div class="event-row">
									<div class="event-label">Event Host Name</div>
									<div class="label-value fb">${events.sponsorName}</div>
									<div class="label-value fbinput"><input type="text" name="sponsorName" value="${events.sponsorName}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Start Date</div>
									<div class="label-value fb">${events.eventStart}</div>
									<div class="label-value fbinput"><input type="text" name="eventStart" id="sd" value="${events.eventStart}" readonly="readonly"></div>
									<div class="clearfix"></div>
								</div>
								<%-- <div class="event-row">
									<div class="event-label">Start Time</div>
									<div class="label-value"><fmt:formatDate type="time" value="${events.createdDate}" /></div>
									<div class="clearfix"></div>
								</div> --%>
								<div class="event-row">
									<div class="event-label">Event Host's email address</div>
									<div class="label-value fb">${events.eventHostMailerId}</div>
									<div class="label-value fbinput"><input type="text" name="eventHostMailerId" value="${events.eventHostMailerId}"></div>
									<div class="clearfix"></div>
								</div>
								
								
								<h1 style="margin-top:35px;">
									Email Body<br>
								    <p class="subtext">Please input the text you want to appear in the email sent to each Guest (with their Booth pictures attached).</p>
								</h1>								
								<div class="clearfix"></div>
								<div class="event-row">
									<p>
									 <c:if test="${not empty events.emailBody}">
									<div id="eb">${events.emailBody}</div>
									<div id="ebinput"><textarea name="emailBody" rows="4" cols="80"><c:out  value="${events.emailBody}"></c:out> </textarea></div>
									</c:if>
									
									 <c:if test="${empty events.emailBody}">
									 <textarea name="emailBody" rows="4" cols="80" id="ebinput1" readonly="readonly"  style="border: none; resize: none;">Thank you for coming to our ${events.eventName} ! Here is a picture to keep as a memory of the event. We hope you had fun!</textarea>
									</c:if>
										
								</p>
								<%-- <c:if test="${not empty events.emailBody}">
								<p>Thank you for coming to our ${events.eventName} ! Here is a picture to keep as a memory of the event. We hope you had fun!</p>
								</c:if> --%>
								</div>
								<div class="clearfix"></div>
								<h1 style="margin-top:35px;">Options</h1>
								
								<div class="event-row">
									<div class="event-label">Facebook</div>
									<div class="label-value fb">${events.facebook}</div>
									<div class="label-value fbinput"><input type="text" name="facebook" value="${events.facebook}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Twitter</div>
									<div class="label-value fb">${events.twitter}</div>
									<div class="label-value fbinput"><input type="text" name="twitter" value="${events.twitter}">
									
									<input type="hidden" value="${events.EId}" name="EId"></div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row fbinput">
									<div class="event-label">&nbsp;</div>
									<div class="label-value">
										<input class="btn btn-green fbinput" type="submit" value="save"  style="width:auto">
									</div>
								</div>
								</form:form>
								
							</div>
							<div class="option_detail_col">
								<div style=" border-bottom: 1px solid #c8c8c8;margin-bottom:15px; ">
									<h1 class="pull-left" style="border:none;margin-bottom:0px">Event Statistics</h1>
									<%-- <a href="eventSummary?eventId=${events.EId}" class="pull-right" target="_blank">Event Summary</a> --%>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Total Guest sessions</div>
									<div class="label-value">${optionsReports.totalGuestSessions }</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Total Guests</div>
									<div class="label-value">${optionsReports.totalGuests}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Repeat Guests</div>
									<div class="label-value">${optionsReports.repeatGuests}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Photos sent</div>
									<div class="label-value">${optionsReports.photosSent}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Emails sent</div>
									<div class="label-value">${optionsReports.emailsSent}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Average Session Time</div>
									<div class="label-value">${optionsReports.avgVisitorSession}</div>
									<div class="clearfix"></div>
								</div>
								<!-- <div class="event-row">
									<div class="event-label">Sign ups</div>
									<div class="label-value">10</div>
									<div class="clearfix"></div>
								</div> -->
								<div class="event-row">
									<div class="event-label">Email bounces</div>
									<div class="label-value">${optionsReports.emailBounces}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Facebook</div>
									<div class="label-value">${optionsReports.facebook}</div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Twitter</div>
									<div class="label-value">${optionsReports.twitter}</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="clearfix"></div>
							
							
							<div class="event-grid">
								
								<div class="grid-row">
								<h1 class="heading" style="color: #00a616;border-bottom: 1px solid #c8c8c8;font-size: 17px;    padding-bottom: 10px;margin-bottom: 20px;font-weight: bold;">Backgrounds Used</h1>
						<c:if test="${emailImagesLists.size() > 0}">
							<c:forEach items="${emailImagesLists}" varStatus="loop" var="igl">
								<div class="grid-img">
									<img src="${igl.mailImageUrl}">
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${emailImagesLists.size() == 0}">
								<center><div><b>No Event Summary</b></div></center>
						</c:if>
									
									<div class="clearfix"></div>
								</div>
								<div class="clearfix"></div>
							</div>
							
					
					
						</div>
					</div>
					
				</div>
				<div class="clearfix"></div>
				
				
				<script>
$(document).ready(function(){
	 $(".fbinput").hide();
	$("#ebinput").hide();
    $("#hide").click(function(){
        $(".fb").hide();
        $("#eb").hide();
        $(".fbinput").show();
        $("#ebinput").show(); 
        $("#ebinput1").removeAttr("readonly");
        $("#ebinput1").css({"border-color": "#05a42e", 
            "border-width":"1px", 
            "border-style":"solid"});
    });
    $("#show").click(function(){
        $(".fb").show();
        $("#eb").show();
       
      
    });
});
</script>