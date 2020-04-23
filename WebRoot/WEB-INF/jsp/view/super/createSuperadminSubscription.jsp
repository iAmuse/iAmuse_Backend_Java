		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/createSASubscriptionValidation.js">  </script>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>	
	
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<div class="col-lg-2" style="width:20%;"></div>
		<div class="right-pannel col-lg-10">
					<h1 class="heading pull-left">Add Subscriptions</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<form:form action="saveNewSubscriptions.html" modelAttribute="subscriptionMaster" onsubmit="return formValidationSASubscription();">
								<div class="form_row">
									<div class="form_label">Subscriptions Name</div>
									<div class="form_element"><input type="text" placeholder="Pay Per Use"  name="subName" id="subName"></div><span style="color:red" id="subNameSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Subscriptions Price</div>
									<div class="form_element"><input type="text" placeholder="Subscriptions Price" name="subPrice" id="subPrice"></div><span style="color:red" id="subPriceSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Subscriptions Time Period</div>
									<div class="form_element"><input type="text" placeholder="Subscriptions Time Period"  name="subValidaityDayPeriod" id="subValidaityDayPeriod"></div><span style="color:red" id="subValidaityDayPeriodSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row" style="margin-top:35px;">
									<div class="form_label">&nbsp;</div>
									<div class="form_element"><input type="Submit" value="Save to Continue" class="btn btn-green"></div>
									<div class="clearfix"></div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>