
	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<script type="text/javascript">
      $(document).ready(function() {
    	  $('#effect').delay(9000).fadeOut(400);
            $("#b").addClass("active_menu");
            $("#tweet").click(function(){
          	 console.log("tweeted");
            });
            
         /* var date=document.getElementById("tmz1").value; 
            var convertdLocalTime = new Date(date);
            var hourOffset = convertdLocalTime.getTimezoneOffset() / 60;
            convertdLocalTime.setHours( convertdLocalTime.getHours() + hourOffset ); 

            document.getElementById("tmzs1").innerHTML=convertdLocalTime;   
            
            
            getTimeZone()
            function getTimeZone() {
         	    var offset = new Date().getTimezoneOffset(),
         	        o = Math.abs(offset);
         	    return (offset < 0 ? "+" : "-") + ("00" + Math.floor(o / 60)).slice(-2) + ":" + ("00" + (o % 60)).slice(-2);
         	}
            document.getElementById('el').innerHTML = getTimeZone();
             */
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
<!-- <script type="text/javascript">
$(document).ready(function(){
	//document.getElementById("timezone1").value=${events.eventTimezone};
	if(document.getElementById("issub").value=='Yes'){
		$('input:radio[name=isSubscribed][value='+yes+']').attr('checked', true);
	}else if(document.getElementById("issub").value=='No'){
		$('input:radio[name=isSubscribed][value='+no+']').attr('checked', true);
	}
});
</script> -->
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
      /*	.right-pannel{height:auto !important;}*/
      .left-pannel{
  min-height:1026px !important;}
      </style>
      <div class="col-lg-2 col-md-3" style="width:20%;"></div>
				<div class=" col-lg-10 col-sm-9 col-xs-12 col-md-9 right-pannel" id="printableArea">
				<div class="col-lg-7 col-md-7">
					<!--  <a href="getSubscribedEventList"><button type="button" class="btn btn-default btn-sm pull-left" style="margin-right: 10px;"><span class="glyphicon glyphicon-chevron-left"></span>Back</button></a>-->
					<h1 class="heading pull-left" style="padding-top:15px;">Event Summary</h1></div>
					<!-- <a href="#" class="pull-right"><button class="btn btn-green"  onclick="printDiv('printableArea')">Export Contacts</button></a> -->
					<div class="col-lg-5 col-md-5 btnpad">
						<a href="#" class="pull-rightsummary event_gallery_label"><button class="btn btn-green event-gallery1" style="margin-right:10px;" onclick="eventGallery()">See Event Gallery</button></a>
						<a href="getContactEmail?eventId=${eventIdsss}" class="pull-rightevdetails guest-email-label"><button class="btn btn-green guest-email">Guest Email Addresses</button></a>
						<div class="clearfix"></div>
						
					<input type="hidden" value="${fn:length(emailImagesList)}" id="emailCsvLength"/> 
					
				</div>
					<!-- <a href="#" class="pull-right"><button class="btn btn-green" style="margin-right:15px;">Export Contacts</button></a> -->
					<div class="clearfix"></div>
					<div class="inner-content" style="background:none;border:none;">
						<div class="col-lg-12 col-md-12">	
							<div class="col-lg-12 col-md-5 event-stat option_detail_col">
								<div style=" border-bottom: 2px solid #c8c8c8;margin-bottom:0px; ">
									<h1 class="pull-left" style="border:none;margin-bottom:0px;margin-top:0px;">Event Statistics</h1>
									<%-- <a href="eventSummary?eventId=${events.EId}" class="pull-right" target="_blank">Event Summary</a> --%>
									<div class="clearfix"></div>
								</div>
								<div class="col-lg-12" style="padding-top:30px; padding-bottom:30px;">
								<!-- <div class="col-lg-3 stat-block-width">
								<div class="col-lg-12 stat-block" style="text-align:center">
								<img  src="<%=request.getContextPath()%>/resources/images/images/total_guest_session.png" style="width: 30%;">
								   <p style="font-size: 12px;margin-top: 5px;margin-bottom: 5px;">Total Guest sessions</p>
								   <h3 style="margin-top: 0px;margin-bottom: 0px;font-size: 19px;">${optionsReports.totalGuestSessions }</h3></div>
								</div> -->
							 <div class="col-lg-3 stat-block-width">
							 <div class="col-lg-12 stat-block">
							 <img  src="<%=request.getContextPath()%>/resources/images/images/guest_total.png" style="width: 30%;">
								   <p style="font-size: 12px;margin-top: 5px;margin-bottom: 5px;">Total Guests</p>
								   <h3 style="margin-top: 0px;margin-bottom: 0px;font-size: 19px;">${optionsReports.totalGuests }</h3></div>
								</div>
								<div class="col-lg-3 stat-block-width">
								<div class="col-lg-12 stat-block">
								<img  src="<%=request.getContextPath()%>/resources/images/images/repeat_guest.png" style="width: 30%;">
								   <p style="font-size: 12px;margin-top: 5px;margin-bottom: 5px;">Repeat Guests</p>
								   <h3 style="margin-top: 0px;margin-bottom: 0px;font-size: 19px;">${optionsReports.repeatGuests }</h3></div>
								</div>
								<div class="col-lg-3 stat-block-width">
								<div class="col-lg-12 stat-block">
								<img  src="<%=request.getContextPath()%>/resources/images/images/photo_sent.png" style="width: 30%;">
								   <p style="font-size: 12px;margin-top: 5px;margin-bottom: 5px;">Photos sent</p>
								    <h3 style="margin-top: 0px;margin-bottom: 0px;font-size: 19px;">${optionsReports.photosSent}</h3></div>
								</div>
								 <div class="col-lg-3 stat-block-width">
							 <div class="col-lg-12 stat-block">
							 <img  src="<%=request.getContextPath()%>/resources/images/images/email_sent.png" style="width: 30%;">
								  <p style="font-size: 12px;margin-top: 5px;margin-bottom: 5px;">Emails sent</p>
								   <h3 style="margin-top: 0px;margin-bottom: 0px;font-size: 19px;">${optionsReports.emailsSent }</h3></div>
								</div>
								<div class="col-lg-3 stat-block-width">
								<div class="col-lg-12 stat-block">
								<img  src="<%=request.getContextPath()%>/resources/images/images/average_session_time.png" style="width: 30%;">
								  <p style="font-size: 12px;margin-top: 5px;margin-bottom: 5px;"> Average Session Time</p>
								   <h3 style="margin-top: 0px;margin-bottom: 0px;font-size: 19px;">${optionsReports.avgVisitorSession}</h3></div>
								</div>
								</div>
								
								<!-- <table class="table">
								<tbody>
								<tr>
									<td>Total Guest sessions<br>
									${optionsReports.totalGuestSessions }</td>
								
									<td>Total Guests<br>
									${optionsReports.totalGuests}</td>
								
									<td>Repeat Guests<br>
									${optionsReports.repeatGuests}</td>
								
									<td>Photos sent<br>
									${optionsReports.photosSent}</td>
								
									<td>Emails sent<br>
									${optionsReports.emailsSent}</td>
								
									<td>Average Session Time<br>
									${optionsReports.avgVisitorSession}</td>
							    </tr>
								<!-- <div class="event-row">
									<div class="event-label">Sign ups</div>
									<div class="label-value">10</div>
									<div class="clearfix"></div>
								</div> -->
								<!-- <tr>
									<td>Email bounces</td>
									<td>${optionsReports.emailBounces}</td>
								</tr>
								<tr>
									<td>Facebook</td>
									<td>${optionsReports.facebook}</td>
								</tr>
								<tr>
									<td>Twitter</td>
									<td>${optionsReports.twitter}</td>
								</tr></tbody></table> -->
							</div>					
							<div class="col-lg-12 col-md-7 event_detail_col">
								<div style="border-bottom:2px solid #c7c7c7;padding-bottom:8px;margin-bottom:10px;width:100%;">
									<h1 class="pull-left" style="border:none;margin:0px;">Event Details</h1>
									<c:if test="${boothAdminLogin2.userRole=='boothadmin' && empty events.eventType}"><button class="btn btn-green pull-right" style="float:right;margin-top:-10px;" id="hide">edit</button></c:if>
									<div class="clearfix"></div>
								</div>
								<form:form action="saveAdminDetails" modelAttribute="EventVO" onsubmit="return formValidatiionEventDetils();">
								<div class="event-row">
									<div class="event-label">Event Name</div>
									<div class="label-value fb">${events.eventName}</div>
									<div class="label-value fbinput"><input type="text" name="eventName" value="${events.eventName}"></div>
									<div class="clearfix"></div>
								</div>
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
								
								
								<h1 style="margin-top:35px;" title="Please input the text you want to appear in the email sent to each Guest (with their Booth pictures attached).">
									Email Body<br>
								    <p class="subtext"></p>
								</h1>								
								<div class="clearfix"></div>
								<div class="event-row">
									<p>
									<c:if test="${boothAdminLogin2.subId!=1 }">
									 <c:if test="${not empty events.emailBody}">
									  <div id="eb">${events.emailBody}</div>
									 <!-- <div id="eb">Thanks for trying out iAmuse in Free Trial mode!  If you <a href="https://www.iamuse.com/pricing" target="_blank">Upgrade</a> you can unlock customizing this text.</div>-->
									<div id="ebinput"><textarea name="emailBody" rows="4" cols="93" style="resize: vertical; width:100%;"><c:out  value="${events.emailBody}"></c:out> </textarea></div>
									</c:if>
									
									 <c:if test="${empty events.emailBody}">
									 <textarea name="emailBody" rows="4" cols="93" id="ebinput1" readonly="readonly"  style="border: none; resize: none;width: 100%;">Thank you for coming to our Event! We hope you had fun, attached is your picture.</textarea>
									</c:if>
									</c:if>
									<c:if test="${boothAdminLogin2.subId==1 }">
									 <div id="eb">Thanks for trying out iAmuse in Free Trial mode!  If you <a href="https://www.iamuse.com/pricing" target="_blank">Upgrade</a> you can unlock customizing this text.</div>
									
									</c:if>	
								</p>
								<%-- <c:if test="${not empty events.emailBody}">
								<p>Thank you for coming to our ${events.eventName} ! Here is a picture to keep as a memory of the event. We hope you had fun!</p>
								</c:if> --%>
								</div>
								<div class="clearfix"></div><div class="clearfix"></div>
								<div class="col-lg-12 col-md-12 socialop">
								<h1 class="option-class">Options</h1>
								
								<div class="event-row">
									<div class="event-label">Facebook</div>
									<div class="label-value fb"><a href="${events.facebook}" target="_blank">${events.facebook}</a></div>
									<div class="label-value fbinput"><input type="text" name="facebook" id="facebook" value="${events.facebook}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label">Twitter</div>
									<div class="label-value fb"><a href="${events.twitter}" target="_blank" id="tweet">${events.twitter}</a></div>
									<div class="label-value fbinput"><input type="text" name="twitter" id="twitter" value="${events.twitter}">
									
									<input type="hidden" value="${events.EId}" name="EId"></div>
									<div class="clearfix"></div>
								</div>
								<div class="event-row">
									<div class="event-label" title="Toggles whether Guests are asked to give consent to being on the Host's mailing list">Enable Subscription</div>
									<div class="label-value fb">${events.isSubscribed}<input type="hidden" value="${events.isSubscribed}" id="issub"></div>
									<div class="label-value fbinput">
										<span style="margin-right:10px">
										<input type="radio" name="isSubscribed" value="yes" id="yes" checked="checked"> Yes</span>
										<span><input type="radio" name="isSubscribed" id="no" value="no"> No</span>
									</div>
									<div class="clearfix"></div>
								</div>
								
								<div class="event-row">
									<div class="event-label">Enable Name</div>
									<div class="label-value fb">${events.isName}<input type="hidden" value="${events.isName}"></div>
								</div>
								
								<div class="event-row">
									<div class="event-label">Enable Phone Number</div>
									<div class="label-value fb">${events.isPhone}<input type="hidden" value="${events.isPhone}"></div>
								</div>
								
								<div class="event-row fbinput">
									<div class="event-label">&nbsp;</div>
									<div class="label-value">
										<input class="btn btn-green fbinput" type="submit" value="save"  style="width:auto">
									</div>
								</div></div>
								</form:form>
								
							</div>
						
							<div class="clearfix"></div>
							
							
							<div class="col-lg-12 col-md-12 event-grid" style="margin-top:20px;">
								
								<div class="grid-row">
								<h1 class="heading" style="color: #000;border-bottom: 1px solid #c8c8c8;font-size: 17px;    padding-bottom: 10px;margin-bottom: 20px;font-weight: bold;">Backgrounds Used</h1>
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
							<div class="col-lg-3">
								<a href="getSubscribedEventList"><button type="button" class="btn btn-default btn-sm pull-left" style="margin-right: 10px;background:#05a42e;color:#fff;width:70px;border-radius:5px;margin-bottom: 54px;"></span>Back</button></a></div>
							
					
					
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