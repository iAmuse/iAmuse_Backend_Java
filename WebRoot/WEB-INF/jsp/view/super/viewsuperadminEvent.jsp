		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
		
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>	
<script >
$(document).ready(function() {
$("#d").addClass("active_menu");
});
</script>
<div class="col-lg-2" style="width:20%;"></div>
		<div class="right-pannel col-lg-10">
					<h1 class="heading pull-left">View Events</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<form:form action="getEventList"  onsubmit="return formValidation();">
								<div class="form_row">
									<div class="form_label">Event Date</div>
									<div class="form_element"><input type="text" placeholder="3rd  Oct. 2016"  name="eventStart" id="eventStart" value="${eventList.eventStart}" readonly="readonly"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Name Of Event</div>
									<div class="form_element"><input type="text" placeholder="Birthday" name="eventName" id="eventName" value="${eventList.eventName}" readonly="readonly"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Name Of Sponsor</div>
									<div class="form_element"><input type="text" placeholder="Rajat Thakur"  name="sponsorName" id="sponsorName" value="${eventList.sponsorName}" readonly="readonly"></div>
									<div class="clearfix"></div>
								</div>
								
								<div class="form_row">
									<div class="form_label">Creater Name</div>
									<div class="form_element"><input type="text" placeholder="Rajat Thakur"  name="createrName" id="createrName" value="${eventList.createrName}" readonly="readonly"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row" style="margin-top:35px;">
									<div class="form_label">&nbsp;</div>
									<div class="form_element"><input type="Submit" value="Back to Continue" class="btn btn-green"></div>
									<div class="clearfix"></div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>