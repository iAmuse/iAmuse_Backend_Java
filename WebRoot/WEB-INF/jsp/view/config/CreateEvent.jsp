		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/createEventValidation.js">  </script>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
 <script>
 $(document).ready(function() {
	  $('#effect').delay(9000).fadeOut(400);
       $("#b").addClass("active_menu");
   });
 </script>
<style>
/*.right-pannel{height:auto !important;}*/
/*.left-pannel{
  min-height:750px !important;}*/
.form_element{width:30%}
</style>
<div class="col-lg-2 col-md-3" style="width:20%;"></div>
		<div class="right-pannel col-lg-10 col-xs-12">
					<h1 class="headingtitle1 pull-left createEvent-lbl">Create Event</h1>
					<div class="clearfix"></div>
					<div class="inner-content col-xs-11" style="/*padding:35px;*/" data-step="2" data-intro="Add basic details of the event to start configuring the event">
						<div class="col-row event-field-row">
							<form:form action="save-create-event-Config.html" modelAttribute="EventVO" onsubmit="return formValidation();" >
								<div class="form_row event-field-cls">
									<div class="form_label">Event Date</div> 
									<div class="form_element">
									<input type="text" placeholder="Date"  name="eventStart" id="eventStart" readonly="readonly" style="padding: 6px;width:48%;float:left;border:1px solid #c8c8c8"> 
										<select class="form-control" id="timezone1" name="eventTimezone" style="width:48%;float:right">
								          <option value="0" selected="selected">select timezone</option>
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
									</div> <span style="color:red" id="eventStartSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row event-field-cls">
									<div class="form_label">Name Of Event</div>
									<div class="form_element"><input type="text" placeholder="Event Name" name="eventName" id="eventName"></div> <span style="color:red" id="eventNameSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row event-field-cls">
									<div class="form_label">Name Of Host</div>
									<div class="form_element"><input type="text" placeholder="Name"  name="sponsorName" id="sponsorName"></div> <span style="color:red" id="sponsorNameSpan"></span>
									<div class="clearfix"></div>
								</div>
								
								<div class="form_row event-field-cls">
									<div class="form_label">Hosts Email Address</div>
									<div class="form_element"><input type="text" placeholder="Hosts Email Address"  name="eventHostMailerId" id="eventHostMailerId"></div> <span style="color:red" id="eventHostMailerIdSpan"></span>
									<div class="clearfix"></div>
								</div>
								
								<div class="form_row event-field-cls">
									<div class="form_label">Facebook</div>
									<div class="form_element"><input type="text" placeholder="Facebook"  name="facebook" id="facebook" value="https://www.facebook.com/iamusebooth/"></div> <span style="color:red" id="facebookSpan"></span>
									<div class="clearfix"></div>
								</div>
								
								<div class="form_row event-field-cls">
									<div class="form_label">Twitter</div>
									<div class="form_element"><input type="text" placeholder="Twitter" value="https://twitter.com/iamusebooth" name="twitter" id="twitter"></div> <span style="color:red" id="twitterSpan"></span>
									<div class="clearfix"></div>
								</div>
								
								<div class="form_row event-field-cls">
									<div class="form_label">Email Body</div>
									<div class="form_element"><textarea placeholder="Email Body"  name="emailBody" id="emailBody"  rows="4" cols="80" maxlength="800"></textarea></div> <span style="color:red" id="emailBodySpan"></span>
									<div class="clearfix"></div>
									<!-- <p class="subtext" style="font-size:9px;float:right;margin-right: 380px;">Thank you for coming to our event ! Here is a picture to keep as a memory of the event. We hope you had fun!</p> -->
									<div class="clearfix"></div>
								</div>
								<div class="form_row event-field-cls">
									<div class="form_label" style="line-height:15px;">Enable Subscription</div>
									<div class="form_element">
										<span style="margin-right:10px"><input type="radio" name="isSubscribed" value="yes"  checked="checked"> Yes</span>
										<span><input type="radio" name="isSubscribed" value="no"> No</span>
									</div> 
										<span style="color:red" id="emailBodySpan"></span>
									<div class="clearfix"></div>
									<!-- <p class="subtext" style="font-size:9px;float:right;margin-right: 380px;">Thank you for coming to our event ! Here is a picture to keep as a memory of the event. We hope you had fun!</p> -->
									<div class="clearfix"></div>
								</div>
								<div class="form_row event-field-cls">
									<div class="form_label" style="line-height:15px;">Enable Name</div>
									<div class="form_element">
										<span style="margin-right:10px"><input type="radio" name="isName" value="yes"  checked="checked"> Yes</span>
										<span><input type="radio" name="isName" value="no"> No</span>
									</div> 
										<span style="color:red" id="emailBodySpan"></span>
									<div class="clearfix"></div>
									<!-- <p class="subtext" style="font-size:9px;float:right;margin-right: 380px;">Thank you for coming to our event ! Here is a picture to keep as a memory of the event. We hope you had fun!</p> -->
									<div class="clearfix"></div>
								</div>
								<div class="form_row event-field-cls">
									<div class="form_label" style="line-height:15px;">Enable PhoneNumber</div>
									<div class="form_element">
										<span style="margin-right:10px"><input type="radio" name="isPhone" value="yes"  checked="checked"> Yes</span>
										<span><input type="radio" name="isPhone" value="no"> No</span>
									</div> 
										<span style="color:red" id="emailBodySpan"></span>
									<div class="clearfix"></div>
									<!-- <p class="subtext" style="font-size:9px;float:right;margin-right: 380px;">Thank you for coming to our event ! Here is a picture to keep as a memory of the event. We hope you had fun!</p> -->
									<div class="clearfix"></div>
								</div>
								<div class="form_row event-field-cls" style="margin-top:35px;">
									<div class="form_label">&nbsp;</div>
									<div class="form_element"><input type="Submit" value="Next" class="btn btn-green" data-toggle="tooltip" title="Add basic details of the event to start configuring the event"></div>
									<div class="clearfix"></div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>