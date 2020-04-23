		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
		
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>	
<script >
$(document).ready(function() {
$("#b").addClass("active_menu");
});
</script>
<div class="col-lg-2" style="width:20%;"></div>
		<div class="right-pannel col-lg-10 col-xs-12">
					<h1 class="heading pull-left">Edit Subscriptions</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<form:form action="updateSubscriptions.html" modelAttribute="subscriptionMaster" onsubmit="return formValidation();">
							<input type="hidden" name="subId" value="${masterValue.subId}"/>
							<input type="hidden" name="createdDate" value="${masterValue.createdDate}"/>
							<input type="hidden" name="createdUserId" value="${masterValue.createdUserId}"/>
							<input type="hidden" name="status" value="${masterValue.status}"/>
							<input type="hidden" name="isDeleted" value="${masterValue.isDeleted}"/>
								<div class="form_row">
									<div class="form_label">Subscriptions Name</div>
									<div class="form_element"><input type="text" placeholder="Pay Per Use"  name="subName" id="subName" value="${masterValue.subName}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Subscriptions Price</div>
									<div class="form_element"><input type="text" placeholder="Subscriptions Price" name="subPrice" id="subPrice" value="${masterValue.subPrice}"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Subscriptions Time Period</div>
									<div class="form_element"><input type="text" placeholder="Subscriptions Time Period"  name="subValidaityDayPeriod" id="subValidaityDayPeriod" value="${masterValue.subValidaityDayPeriod}"></div>
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