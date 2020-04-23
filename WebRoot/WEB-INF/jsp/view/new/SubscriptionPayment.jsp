<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ page import="java.util.*" %>
<style>
/*.right-pannel{height:auto !important;}*/

#gif {width:100%;height:100%;opacity:.95;top:0;left:0;text-align:center;position:fixed;background-color:#313131;overflow:auto;z-index: 1000;padding-top:20%;}
</style>
<script type="text/javascript">
      $(document).ready(function() {
            $("#d").addClass("active_menu");
            $('#effect').delay(9000).fadeOut(400);
            $("#monthly").click(function(){
          	
              $("#amount").val("150");	

          });
          $("#yearly").click(function(){
          	
              $("#amount").val("1200");	

          });
          $("#custom").click(function(){
        	
            $("#amount").val("25");	

        });
        });
     
</script>
			<div class="col-lg-2" style="width:20%;"></div>
			<div class="right-pannel col-lg-10 col-xs-12 right-height">
			<%@ page import="com.paypal.constants.*" %>
			<c:set var="context" value="<%=request.getContextPath()%>" />
			<%
			System.out.print("${context}");
			String sellerId=ServerConstants.SELLER_ID;
			String password=ServerConstants.PASSWORD;
			%>
<script type="text/javascript">
function paySub(){
	document.getElementById("gif").style.display = "";
	
}
/*$("#monthly").click(function(){debugger;
	alert("monthly");
    $("#amount").value("150");	

});
$("#yearly").click(function(){debugger;
	alert("yearly");
    $("#amount").value("100");	

});*/

//"${subscriptionPlan.subPrice}"
</script>

<div id="gif" style="display: none;">
	<img src="https://www.willmaster.com/images/preload.gif">
</div>
<c:set var="req" value="${pageContext.request}" />

<form:form action="saveSubscriptionDetailsPP" method="post" modelAttribute="TransactionReceiptVO"  >
<input type="hidden" name="cmd" value="_xclick">
<!-- <input type="hidden" name="charset" value="utf-8"> -->
<input type="hidden" name="business" value="<%=sellerId %>">
<%-- <input type="hidden" name="password" value="<%=password %>"/> --%>
<input type="hidden" name="item_name" id="item_name" value="${subscriptionPlan.subName}">
<input type="hidden" name="item_number" id="item_number" value="${subscriptionPlan.subId}">
<input type="hidden" name="amount" id="amount" value="">
<!-- <input type="hidden" name="quantity" value="1"> -->
<input type="hidden" name="first_name" value="${boothAdminLogin.username}">
<input type="hidden" name="night_phone_a" value="${boothAdminLogin.contactNumber}">
<input type="hidden" name="email" value="${boothAdminLogin.emailId}">
<input type="hidden" name="return" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/saveSubscriptionDetails">
<input type="hidden" name="notify_url" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/saveSubscriptionDetails">
<input type="hidden" name="cancel_return" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/NotifyPage">
<!-- <input type="hidden" name="currency_code" value="USD"> -->
<input type='hidden' name="rm" value="1">



			
					<h1 class="heading pull-left">Payment</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<div class="login_form" style="width:100%;margin:0px;">
								<!--  <div class="login-logo">
									<img src="<%=request.getContextPath()%>/resources/images/images/logo_login.png">
								</div>-->
								<c:if test="${subscriptionPlan.subPrice==150}">
								<div class="col-lg-6 profession-plan">
								<div class="plan-rate-box" style="margin:0px auto;">
								<div class="rate-box">
								<h1 style="color:#717171;">${subscriptionPlan.subName }</h1>
								<p class="total-ammount">$&nbsp;${subscriptionPlan.subPrice }</p>
								<center>
								<c:if test="${subscriptionPlan.subPrice==10 || subscriptionPlan.subPrice==25}"><p class="subtext">Expires after 24 Hours</p></c:if>
								<c:if test="${subscriptionPlan.subPrice==150}"><p class="subtext">monthly</p></c:if><br/>
								
								<c:set var="string2" value="${subscriptionPlan.deviceDescription}" />
											    <c:forEach items="${string2}" var="emps1">
											            <p class="device-desc"><c:out value="${emps1}"/></p>
											    </c:forEach>
								
									<!--  <img src="<%=request.getContextPath()%>/resources/images/images/paypal.png" align="center" width="150px" />-->
								</center>
								<div class="payment_row" style="margin-bottom: 0px;float:left;">
								
									<center><button type="submit" id="monthly" value="Pay" style="margin-top:50px;" class="btn btn-green paybtn" onclick="paySub();">Pay</button></center>									
								</div></div>
								<div class="clearfix"></div></div>
								<div class="plan-rate-box" style="margin:0px auto;">
								<div class="rate-box">
								<h1 style="color:#717171;">${subscriptionPlan.subName }</h1>
								<p class="total-ammount">$&nbsp;1200</p>
								<center>
								<!--<c:if test="${subscriptionPlan.subPrice==10 || subscriptionPlan.subPrice==25}"><p class="subtext">Expires after 24 Hours</p></c:if>
								//<c:if test="${subscriptionPlan.subPrice==150}"><p class="subtext">monthly</p></c:if><br/>-->
								<p class="subtext">$100/month(Yearly)</p><br>
								<c:set var="string2" value="${subscriptionPlan.deviceDescription}" />
											    <c:forEach items="${string2}" var="emps1">
											            <p class="device-desc"><c:out value="${emps1}"/></p>
											    </c:forEach>
								
									<!--  <img src="<%=request.getContextPath()%>/resources/images/images/paypal.png" align="center" width="150px" />-->
								</center>
								<div class="payment_row" style="margin-bottom: 0px;float:left;">
								
									<center><button type="submit" id="yearly" value="Pay" style="margin-top:50px;" class="btn btn-green paybtn" onclick="paySub();">Pay</button></center>									
								</div></div>
								<div class="clearfix"></div></div></div>
								</c:if>
								
								
								<!-- dummy text -->
								<c:if test="${subscriptionPlan.subPrice==25}">
								<div class="plan-rate-box" style="margin:0px auto;">
								<div class="rate-box">
								<h1 style="color:#717171;">${subscriptionPlan.subName }</h1>
								<p class="total-ammount">$&nbsp;${subscriptionPlan.subPrice }</p>
								<center>
								<c:if test="${subscriptionPlan.subPrice==10 || subscriptionPlan.subPrice==25}"><p class="subtext">Expires after 24 Hours</p></c:if>
								<!--<c:if test="${subscriptionPlan.subPrice==150}"><p class="subtext">monthly</p></c:if><br/>-->
								
								<c:set var="string2" value="${subscriptionPlan.deviceDescription}" />
											    <c:forEach items="${string2}" var="emps1">
											            <p class="device-desc"><c:out value="${emps1}"/></p>
											    </c:forEach>
								
									<!--  <img src="<%=request.getContextPath()%>/resources/images/images/paypal.png" align="center" width="150px" />-->
								</center>
								<div class="payment_row" style="margin-bottom: 0px;float:left;">
								
									<center><button type="submit" id="custom" value="Pay" style="margin-top:50px;" class="btn btn-green paybtn" onclick="paySub();">Pay</button></center>									
								</div></div>
								<div class="clearfix"></div></div></c:if>
						</div>
						</div>
						</div>
						</form:form>
							</div>
				
				<div class="clearfix"></div>
				