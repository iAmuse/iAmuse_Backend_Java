
	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<script type="text/javascript">
      $(document).ready(function() {
    	  $('#effect').delay(9000).fadeOut(400);
            $("#b").addClass("active_menu");
        });
      function eventGallery(){
    	  window.location.href ="<%=request.getContextPath()%>/eventGallery?eventId="+${events.EId};
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

function formValidatiionEventDetils(){
	var eventHostMailerId=document.getElementById("eventHostMailerId").value;
	var filter     = /^([a-zA-Z])+([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(eventHostMailerId.trim() ==null || eventHostMailerId.trim() ==""){
			   alert("Event host email address is required");
		       return false;
	}
	else{
		 if(!filter.test(eventHostMailerId)){
			   alert("Check email address");
		       return false;
		      }
	}
	
	
	var fb=document.getElementById("facebook").value;
	var tw=document.getElementById("twitter").value;
	
	if(fb.trim() !=""){
		var filter1=/^(https?:\/\/)?((w{3}\.)?)facebook.com\/.*/;
		if (!filter1.test(fb)){
			alert("Check Facebook URL");
			return false;
		}
	}
	if(tw.trim() !=""){
		var filter2=/^(https?:\/\/)?((w{3}\.)?)twitter.com\/(#!\/)?[a-zA-Z0-9_]+$/;
		if (!filter2.test(tw)){
			alert("Check Twitter URL");
			return false;
		}
	}
}

</script>
<script type="text/javascript">
$(document).ready(function() {
	$("#timezone1 option[value='${events.eventTimezone}']").attr('selected','selected');
	var isif=document.getElementById("issub").value;
	if(isif=='Yes'){
		$('input:radio[name=isSubscribed][value=yes]').attr('checked', 'checked');
	}else if(isif=='No'){
		$('input:radio[name=isSubscribed][value=no]').attr('checked', 'checked');
	}
});
</script>
<style>
      	.right-pannel{height:auto !important;}
      </style>
				<div class="right-pannel" id="printableArea">
					<a href="getSubscribedEventList"><button type="button" class="btn btn-default btn-sm pull-left" style="margin-right: 10px;"><span class="glyphicon glyphicon-chevron-left"></span>Back</button></a><h1 class="heading pull-left" style="padding-top:3px;">Event Summary</h1>
					
						<a href="getContactEmail?eventId=${eventIdsss}" class="pull-right"><button class="btn btn-green">Guest Email Addresses</button></a>
					<input type="hidden" value="${fn:length(emailImagesList)}" id="emailCsvLength"/>
					
					<a href="#" class="pull-right"><button class="btn btn-green" style="margin-right:10px;" onclick="eventGallery()">See Event Gallery</button></a>
					<div class="clearfix"></div>
					<div class="inner-content" style="background:none;border:none;">
						<div class="col-row">						
							<div class="event_detail_col">
								<div style="border-bottom:1px solid #c7c7c7;padding-bottom:8px;margin-bottom:10px">
									<h1 class="pull-left" style="border:none;margin:0px;">Event Details</h1>
									<c:if test="${boothAdminLogin.userRole=='boothadmin' && empty events.eventType}"><button class="btn btn-green pull-right" style="float:right;margin-top:-10px;" id="hide">edit</button></c:if>
									<div class="clearfix"></div>
								</div>
								<form:form action="saveAdminDetails" modelAttribute="EventVO" onsubmit="return formValidatiionEventDetils();">
								<div class="event-row">
									<div class="event-label">Event Host Name</div>
									<div class="label-value fb">${events.sponsorName}</div>
									<div class="label-value fbinput"><input type="text" name="sponsorName" value="${events.sponsorName}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row"><div class="label-value fbinput">&nbsp;</div></div>
								<div class="event-row">
									<div class="event-label">Start Date</div>
									<div class="label-value fb" >${events.eventStart} &nbsp;${events.eventTimezone}</div>
									<div class="label-value fbinput"><input type="text" name="eventStart" id="sd" value="${events.eventStart}" readonly="readonly"></div>
									<div class="clearfix"></div>
								</div>
								
								<div class="event-row">
									<div class="label-value fbinput">
										<select class="form-control" id="timezone1" name="eventTimezone">
								          <option value="0" >select your timezone</option>
								          <option value="(GMT -12:00)">(GMT -12:00) Eniwetok, Kwajalein</option>
								          <option value="(GMT -11:00)">(GMT -11:00) Midway Island, Samoa</option>
								          <option value="(GMT -10:00)">(GMT -10:00) Hawaii</option>
								          <option value="(GMT -9:30)">(GMT -9:30) Taiohae</option>
								          <option value="(GMT -9:00)">(GMT -9:00) Alaska</option>
								          <option value="(GMT -8:00)">(GMT -8:00) Pacific Time (US & Canada)</option>
								          <option value="(GMT -7:00)">(GMT -7:00) Mountain Time (US & Canada)</option>
								          <option value="(GMT -6:00)">(GMT -6:00) Central Time (US & Canada), Mexico City</option>
								          <option value="(GMT -5:00)">(GMT -5:00) Eastern Time (US & Canada), Bogota, Lima</option>
								          <option value="(GMT -4:30)">(GMT -4:30) Caracas</option>
								          <option value="(GMT -4:00)">(GMT -4:00) Atlantic Time (Canada), Caracas, La Paz</option>
								          <option value="(GMT -3:30)">(GMT -3:30) Newfoundland</option>
								          <option value="(GMT -3:00)">(GMT -3:00) Brazil, Buenos Aires, Georgetown</option>
								          <option value="(GMT -2:00)">(GMT -2:00) Mid-Atlantic</option>
								          <option value="(GMT -1:00)">(GMT -1:00) Azores, Cape Verde Islands</option>
								          <option value="(GMT +0:00)">(GMT +0:00) Western Europe Time, London, Lisbon, Casablanca</option>
								          <option value="(GMT +1:00)">(GMT +1:00) Brussels, Copenhagen, Madrid, Paris</option>
								          <option value="(GMT +2:00)">(GMT +2:00) Kaliningrad, South Africa</option>
								          <option value="(GMT +3:00)">(GMT +3:00) Baghdad, Riyadh, Moscow, St. Petersburg</option>
								          <option value="(GMT +3:30)">(GMT +3:30) Tehran</option>
								          <option value="(GMT +4:00)">(GMT +4:00) Abu Dhabi, Muscat, Baku, Tbilisi</option>
								          <option value="(GMT +4:30)">(GMT +4:30) Kabul</option>
								          <option value="(GMT +5:00)">(GMT +5:00) Ekaterinburg, Islamabad, Karachi, Tashkent</option>
								          <option value="(GMT +5:30)">(GMT +5:30) Bombay, Calcutta, Madras, New Delhi</option>
								          <option value="(GMT +5:45)">(GMT +5:45) Kathmandu, Pokhara</option>
								          <option value="(GMT +6:00)">(GMT +6:00) Almaty, Dhaka, Colombo</option>
								          <option value="(GMT +6:30)">(GMT +6:30) Yangon, Mandalay</option>
								          <option value="(GMT +7:00)">(GMT +7:00) Bangkok, Hanoi, Jakarta</option>
								          <option value="(GMT +8:00)">(GMT +8:00) Beijing, Perth, Singapore, Hong Kong</option>
								          <option value="(GMT +8:45)">(GMT +8:45) Eucla</option>
								          <option value="(GMT +9:00)">(GMT +9:00) Tokyo, Seoul, Osaka, Sapporo, Yakutsk</option>
								          <option value="(GMT +9:30)">(GMT +9:30) Adelaide, Darwin</option>
								          <option value="(GMT +10:00)">(GMT +10:00) Eastern Australia, Guam, Vladivostok</option>
								          <option value="(GMT +10:30)">(GMT +10:30) Lord Howe Island</option>
								          <option value="(GMT +11:00)">(GMT +11:00) Magadan, Solomon Islands, New Caledonia</option>
								          <option value="(GMT +11:30)">(GMT +11:30) Norfolk Island</option>
								          <option value="(GMT +12:00)">(GMT +12:00) Auckland, Wellington, Fiji, Kamchatka</option>
								          <option value="(GMT +12:45)">(GMT +12:45) Chatham Islands</option>
								          <option value="(GMT +13:00)">(GMT +13:00) Apia, Nukualofa</option>
								          <option value="(GMT +14:00)">(GMT +14:00) Line Islands, Tokelau</option>
								        </select>
									</div>
									<div class="clearfix"></div>
								</div>
								<%-- <div class="event-row">
									<div class="event-label">Start Time</div>
									<div class="label-value"><fmt:formatDate type="time" value="${events.createdDate}" /></div>
									<div class="clearfix"></div>
								</div> --%>
								<div class="event-row">
									<div class="event-label">Hosts Email Address</div>
									<div class="label-value fb">${events.eventHostMailerId}</div>
									<div class="label-value fbinput"><input type="text" name="eventHostMailerId" id="eventHostMailerId" value="${events.eventHostMailerId}"></div>
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
									<div id="ebinput"><textarea name="emailBody" rows="4" cols="93" style="resize: vertical;"><c:out  value="${events.emailBody}"></c:out> </textarea></div>
									</c:if>
									
									 <c:if test="${empty events.emailBody}">
									 <textarea name="emailBody" rows="4" cols="93" id="ebinput1" readonly="readonly"  style="border: none; resize: none;">Thank you for coming to our ${events.eventName} ! Here is a picture to keep as a memory of the event. We hope you had fun!</textarea>
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
									<div class="label-value fb"><a href="${events.facebook}">${events.facebook}</a></div>
									<div class="label-value fbinput"><input type="text" name="facebook" id="facebook" value="${events.facebook}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Twitter</div>
									<div class="label-value fb"><a href="${events.twitter}">${events.twitter}</a></div>
									<div class="label-value fbinput"><input type="text" name="twitter" id="twitter" value="${events.twitter}">
									
									<input type="hidden" value="${events.EId}" name="EId"></div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Enable Subscription</div>
									<div class="label-value fb">${events.isSubscribed}<input type="hidden" value="${events.isSubscribed}" id="issub"></div>
									<div class="label-value fbinput">
										<span style="margin-right:10px">
										<input type="radio" name="isSubscribed" value="yes" id="yes" checked="checked"> Yes</span>
										<span><input type="radio" name="isSubscribed" id="no" value="no"> No</span>
									</div>
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