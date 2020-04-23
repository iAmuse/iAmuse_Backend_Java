		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
		
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#eventStart" ).datepicker({ dateFormat: 'yy-mm-dd' }).val();;
  } );
  </script>
  <div class="col-lg-2" style="width:20%;"></div>
		<div class="right-pannel col-lg-10 col-xs-12">
					<h1 class="heading pull-left">Update Events</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<form:form action="updatesuperadminEvent" modelAttribute="EventVO" onsubmit="return formValidation();">
							<input type="hidden" name="EId" value="${eventList.EId}"/>
								<div class="form_row">
									<div class="form_label">Event Date</div>
									<div class="form_element"><input type="text" placeholder="3rd  Oct. 2016"  name="eventStart" id="eventStart" value="${eventList.eventStart}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Name Of Event</div>
									<div class="form_element"><input type="text" placeholder="Birthday" name="eventName" id="eventName" value="${eventList.eventName}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Name Of Sponsor</div>
									<div class="form_element"><input type="text" placeholder="Rajat Thakur"  name="sponsorName" id="sponsorName" value="${eventList.sponsorName}"></div>
									<div class="clearfix"></div>
								</div>
								
								<div class="form_row">
									<div class="form_label">Creater Name</div>
									<div class="form_element"><input type="text" placeholder="Rajat Thakur"  name="createrName" id="createrName" value="${eventList.createrName}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row" style="margin-top:35px;">
									<div class="form_label">&nbsp;</div>
									<div class="form_element"><input type="Submit" value="Update to Continue" class="btn btn-green"></div>
									<div class="clearfix"></div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>