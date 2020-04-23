	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script type="text/javascript">
      $(document).ready(function() {
    	  var subId=document.getElementById("subid").value;
    	  $("#d").addClass("active_menu");
            $('#effect').delay(5000).fadeOut(400);
            if(subId==1){
            	$("#abc1").addClass("active-rate-box");
            }else if(subId==2){
            	$("#abc2").addClass("active-rate-box");
            	$("#aa2").hide();
            }else if(subId==3){
            	$("#abc3").addClass("active-rate-box");
            	$("#aa3").hide();
            }else{
            	$("#abc4").addClass("active-rate-box");
            	$("#aa4").hide();
            }
        });
</script>		
	
      <input type="hidden" value="${boothAdminLogin2.subId}" id="subid"/>		
     <div class="col-lg-2 col-md-3" style="width:20%;"></div>
					<div class="right-pannel col-lg-10 col-xs-12">
					<h1 class="heading pull-left">Upgrade Subscription Plan</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<div class="login_form" style="width:100%;margin:0px;">
								<h1 style="color:#717171;font-size:18px;margin-top:25px;">To upgrade, choose subscription plan based on your preferences</h1>
								<c:forEach items="${subscriptionMaster}" var="sm" varStatus="loop">
								<div class="plan-rate-box">
								
									<div class="rate-box" id="abc${loop.index + 1 }">
									<c:if test="${sm.subPrice != 0 && sm.subPrice != 100}">
										<div class="g-pricingtable-ribbon">
											<img src="<%=request.getContextPath()%>/resources/img/single_event.png">
										</div> 
									</c:if>
									<c:if test="${sm.subPrice == 100}">
										<div class="g-pricingtable-ribbon">
											<img src="<%=request.getContextPath()%>/resources/img/unlimited.png">
										</div> 
									</c:if>
										<div style="height:auto;padding:15px;">
											<h1>${sm.subName}</h1>
											<c:if test="${sm.subPrice==0}">
											<p class="dollar">FREE</p>
											</c:if>
											<c:if test="${sm.subPrice!=0}">
											<p class="dollar">$&nbsp;${sm.subPrice}</p>
											</c:if>
											<p class="dollar-text"><c:if test="${sm.subValidaityDayPeriod==1}" >24 Hrs</c:if><c:if test="${sm.subValidaityDayPeriod!=1}">${sm.subValidaityDayPeriod}&nbsp;Days</c:if></p>
											<p class="dollar-para">${sm.description}</p>
											<!-- <p class="dollar-para">Backgrounds for 24 hrs </p> -->
											<div class="border-line"></div>
											<p class="device-desc">${sm.deviceDescription}
											</p>
											<div class="clearfix"></div>
										</div>
										<c:if test="${sm.subPrice != 0}">
										<a href="detailSubscriptionPlan?id=${sm.subId}" id="aa${loop.index+1}"><button  class="btn btn-green">Subscribe</button></a>
										</c:if>
										<div class="clearfix"></div>
									</div>
									
								</div>
								 </c:forEach>
								 <div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>