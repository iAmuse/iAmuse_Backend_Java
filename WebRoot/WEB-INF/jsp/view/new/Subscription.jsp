
<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/dataTable.css" />
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/css/style.css"
	rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {
	    $('#example').DataTable({
	    	 "language": {
	             "lengthMenu": "Show _MENU_ ",
	             "zeroRecords": "No previous payments",
	             "info": "Showing page _PAGE_ of _PAGES_",
	             "infoEmpty": "No records available",
	             "infoFiltered": "(filtered from _MAX_ total records)"
	         }
	    });
	} );
</script>
<script type="text/javascript">
      $(document).ready(function() {
    	  
    	  var subId=document.getElementById("subid").value;
    	  var isAnnual=document.getElementById("isAnnual").value;
    	  $("#current-plan").click(function(){
    		
    		  return false;
    	  })
    	
    	  $( "#orderButton2" ).click(function() {
    	       if(subId>2)
    	        {
    	        alert("you have already upgraded plan");
    	       return false;
    	        }
    	       else
    	    	   {
    	    	   paySub();
    	    	   }
    	             
    	      });
    	  $( "#orderButton2" ).click(function() {
    		  $("#amount").val("25");
    		  $("#item_name").val("Single Event");
    		  $("#item_number").val("2");
   	             
   	      });
    	  $( "#orderButton3" ).click(function() {
    		  $("#amount").val("150");
    		  $("#item_name").val("professional");
    		  $("#item_number").val("3");
   	             
   	      });
    	  $( "#orderButton4" ).click(function() {
    		  $("#amount").val("1200");
    		  $("#item_name").val("professional");
    		  $("#item_number").val("3");
   	             
   	      });
    	      $( "#orderButton3" ).click(function() {
    	       if(subId==3)
    	    {
    	    	   if(isAnnual=="true")
    	    		   {
    	    		   alert("you have already upgraded plan");
    	    	       return false;
    	    		   }
    	        
    	    }
    	       else
	    	   {
	    	   paySub();
	    	   }
    	      });
    	      $( "#orderButton4" ).click(function() {
       	       if(subId>4)
       	    {
       	        alert("you have already upgraded plan");
       	       return false;
       	    }
       	      });
    	  
    	  
            $("#d").addClass("active_menu");
            
            $('#effect').delay(10000).fadeOut(400);
            if(subId==1){
            	$("#abc1").addClass("active-rate-box");
            }else if(subId==2){
            	$("#abc2").addClass("active-rate-box");
            	$("#aa2").hide();
            }else if(subId==3){
            	if(isAnnual=="true")
            		{
            		 $("#abc4").addClass("active-rate-box");
                     $("#aa4").hide();
            		}
            	else{
            	$("#abc3").addClass("active-rate-box");
            	$("#aa3").hide();
            	}
            }else{
            	$("#abc5").addClass("active-rate-box");
            	$("#aa5").hide();
            }
        });
      function upgrade(){
    	   window.location.href ="<%=request.getContextPath()%>/upgradePlan";
      }
      </script>
<style>
/*	.right-pannel{height:auto !important;}*/
</style>


<script>
        console.log("DB EndDate="+'${boothAdminLogin1.subEndDate}');
     	var subId= ${boothAdminLogin1.subId};
     	if(subId!=1){
     		var end =  new Date('${boothAdminLogin1.subEndDate}');
     		console.log("EndDate="+end);
			
   				var _second = 1000;
    			var _minute = _second * 60;
    			var _hour = _minute * 60;
    			var _day = _hour * 24;
    			var timer;

    function showRemaining() {
    	
        var utc = new Date();
        var now = new Date(utc.getTime() + utc.getTimezoneOffset() * 60000);
        console.log("Current Date="+now);
        var distance = end - now;
        console.log(distance);
        if (distance < 0) {

            /* clearInterval(timer);
            document.getElementById('countdown').innerHTML = ' EXPIRED!'; */
         
            
          var data = JSON.stringify({
        	  userid:${boothAdminLogin1.userId},
        	  subId:1,
        	   });
        	       	  
            
        	   $.ajax({
         	   url:"<%=request.getContextPath()%>/updateSubscriptionFormAdmin",
         	   type : "POST",
         	   contentType : "application/json",
         	  data : data,
         	   dataType : 'json'
         	   }).done(function(){
         		  window.location.href="getSubscription";
         	   });
         	  
           return;
        }
        var days = Math.floor(distance / _day);
        var hours = Math.floor((distance % _day) / _hour);
        var minutes = Math.floor((distance % _hour) / _minute);
        var seconds = Math.floor((distance % _minute) / _second);

        document.getElementById('countdown').innerHTML = 'Your Subscription Will Expire After :  ';
        document.getElementById('countdown').innerHTML += days + ' Days  ';
        document.getElementById('countdown').innerHTML += hours + ' Hrs  ';
        document.getElementById('countdown').innerHTML += minutes + ' Mins  ';
        document.getElementById('countdown').innerHTML += seconds + ' Secs ';
    }
    timer = setInterval(showRemaining, 1000);
 }
</script>

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<div class="col-lg-2 col-md-3" style="width: 20%;"></div>
<div
	class="col-lg-10 col-sm-9 col-xs-12 col-md-9 subscription-class right-pannel">
	<%@ page import="com.paypal.constants.*"%>
	<c:set var="context" value="<%=request.getContextPath()%>" />
	<%
			System.out.print("${context}");
			String sellerId=ServerConstants.SELLER_ID;
			String password=ServerConstants.PASSWORD;
			%>
	<script type="text/javascript">
function paySub(){
	document.getElementById("gif").style.display = "";
	
}</script>
	<div id="gif" style="display: none;">
		<img src="https://www.willmaster.com/images/preload.gif">
	</div>
	<c:set var="req" value="${pageContext.request}" />
	<form:form action="saveSubscriptionDetailsPP" method="post"
		modelAttribute="TransactionReceiptVO">
		<input type="hidden" name="cmd" value="_xclick">
		<!-- <input type="hidden" name="charset" value="utf-8"> -->
		<input type="hidden" name="business" value="<%=sellerId %>">
		<%-- <input type="hidden" name="password" value="<%=password %>"/> --%>
		<input type="hidden" name="item_name" id="item_name" value="">
		<input type="hidden" name="item_number" id="item_number" value="">
		<input type="hidden" name="amount" id="amount" value="">
		<!-- <input type="hidden" name="quantity" value="1"> -->
		<input type="hidden" name="first_name"
			value="${boothAdminLogin.username}">
		<input type="hidden" name="night_phone_a"
			value="${boothAdminLogin.contactNumber}">
		<input type="hidden" name="email" value="${boothAdminLogin.emailId}">
		<input type="hidden" name="return"
			value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/saveSubscriptionDetails">
		<input type="hidden" name="notify_url"
			value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/saveSubscriptionDetails">
		<input type="hidden" name="cancel_return"
			value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/NotifyPage">
		<!-- <input type="hidden" name="currency_code" value="USD"> -->
		<input type='hidden' name="rm" value="1">
		<div class="col-lg-8 col-sm-12 col-xs-12 col-md-6 subctitle">
			<input type="hidden" value="${boothAdminLogin1.subId}" id="subid" />
			<input type="hidden" value="${boothAdminLogin1.is_annual}"
				id="isAnnual" />
			<c:if test="${not empty successMessage}">
				<div id="effect" class="">
					<h5 style="color: green;">${successMessage}</h5>
				</div>
			</c:if>

			<c:if test="${not empty errorMessage}">
				<div id="effect" class="">
					<h4 style="color: red;">${errorMessage}</h4>
				</div>
			</c:if>
			<h1 class="heading pull-left">Choose The Plan That Works Best</h1>

			<!-- <h3 class="sub-heading">Your Booth's <a href="zoomPage" style="color:cornflowerblue">Booth Zoom Profile</a> (<c:if test="${fovbyuser==1.00}">Zoomed In</c:if><c:if test="${fovbyuser==0.50 || fovbyuser==0.5}">Zoomed Out</c:if><c:if test="${fovbyuser==0.75}">Medium Zoom</c:if>).</h3>-->
		</div>
		<div class="clearfix"></div>
		<div class="inner-content subctitle1">
			<div class="col-row">
				<b><div id="countdown" style="margin-left: 15px;"></div></b>
				<div class="login_form col-lg-12 col-sm-12 col-md-12"
					style="width: 100%; margin-top: 20px;">
					<!-- <h1 style="color:#717171;font-size:18px;margin-top:25px;">To upgrade, choose subscription plan based on your preferences</h1> -->
					<c:forEach items="${subscriptionMaster}" var="sm" varStatus="loop">
						<c:if test="${sm.subId==3}">
							<div class="plan-rate-box" style="margin: 0px auto;">
								<div class="rate-box" id="abc4" style="">
									<c:if test="${sm.subPrice != 0}">
										<div class="g-pricingtable-ribbon">
											<img
												src="<%=request.getContextPath()%>/resources/img/single_event.png">
										</div>
									</c:if>
									<c:if test="${sm.subPrice != 100 && sm.subPrice!=0}">
										<div class="g-pricingtable-ribbon">
											<img
												src="<%=request.getContextPath()%>/resources/img/unlimited.png">
										</div>
									</c:if>

									<h1>${sm.subName}</h1>
									<p class="features-text">Unlimited access</p>
									<c:set var="string2" value="${sm.deviceDescription}" />

									<c:forEach items="${string2}" var="emps1">

										<p class="device-desc">
											<span style="white-space: pre"><c:out value="${emps1}" /></span>
										</p>
									</c:forEach>
									<div class="clearfix"></div>
									<a href="" id="aa4"><button type="submit" onclick="zcAction('downloaded_ebook_10')"
											class="btn btn-green" id="orderButton4" onclick="">Order
											Now</button></a>
									<c:if test="${boothAdminLogin1.is_annual!=false}">
										<a href="javascript:void(0)"><button
												class="btn btn-green"
												style="border-radius: 0px; padding: 9px; background-color: #e0d800; color: #000"
												id="current-plan">Your Current Plan</button></a>
									</c:if>
									<p class="dollar">$&nbsp;100/month (yearly)</p>
								</div>
							</div>

						</c:if>
						<div class="plan-rate-box" style="margin: 0px auto;">

							<div class="rate-box" id="abc${loop.index + 1 }" style="">
								<c:if test="${sm.subPrice == 0}">
									<div class="g-pricingtable-ribbon">
										<img
											src="<%=request.getContextPath()%>/resources/img/single_event.png">
									</div>
								</c:if>
								<c:if test="${sm.subPrice != 0}">
									<div class="g-pricingtable-ribbon">
										<img
											src="<%=request.getContextPath()%>/resources/img/single_event.png">
									</div>
								</c:if>
								<c:if test="${sm.subPrice != 100 && sm.subPrice!=0}">
									<div class="g-pricingtable-ribbon">
										<img
											src="<%=request.getContextPath()%>/resources/img/unlimited.png">
									</div>
								</c:if>
								<c:if test="${sm.subPrice == 100}">
									<div class="g-pricingtable-ribbon">
										<img
											src="<%=request.getContextPath()%>/resources/img/unlimited.png">
									</div>
								</c:if>
								<div>
									<h1>${sm.subName}</h1>
									<c:if test="${sm.subPrice==0}">
										<p class="features-text">Limited features</p>
										<!-- <p class="dollar">FREE TRIAL</p> -->
									</c:if>
									<c:if test="${sm.subPrice!=0}">
										<c:if test="${sm.subPrice==25}">
											<p class="features-text">One-time use</p>
										</c:if>
										<c:if test="${sm.subPrice==150}">
											<p class="features-text">Unlimited access</p>
										</c:if>
										<!--<p class="dollar">$&nbsp;${sm.subPrice}</p>  -->
									</c:if>
									<!-- <p class="dollar-para">Backgrounds for 24 hrs </p> -->
									<c:set var="string2" value="${sm.deviceDescription}" />

									<c:forEach items="${string2}" var="emps1">

										<p class="device-desc">
											<span style="white-space: pre"><c:out value="${emps1}" /></span>
										</p>
									</c:forEach>
									<div class="clearfix"></div>
								</div>
								<c:if test="${sm.subPrice != 0}">
									<a href="#" id="aa${loop.index+1}"><button type="submit" onclick="zcAction('downloaded_ebook_10')"
											class="btn btn-green" id="orderButton${loop.index+1}">Order
											Now</button></a>
								</c:if>
								<c:if test="${sm.subId == boothAdminLogin1.subId}">
									<c:if test="${boothAdminLogin1.is_annual==false}">
										<a href="#"><button class="btn btn-green"
												style="border-radius: 0px; padding: 13px; background-color: #e0d800; color: #000"
												id="current-plan">Your Current Plan</button></a>
									</c:if>
								</c:if>
								<c:if test="${sm.subPrice!=0}">
									<c:if test="${sm.subPrice==25}">
										<p class="dollar">$&nbsp;${sm.subPrice}/event (24 hours)</p>
									</c:if>
									<c:if test="${sm.subPrice==150}">
										<p class="dollar">$&nbsp;${sm.subPrice}/month</p>
									</c:if>
								</c:if>
								<div class="clearfix"></div>
							</div>

						</div>
					</c:forEach>

					<%-- <div class="plan-rate-box" style="margin:0px auto;">
								 <div class="rate-box" id="abc4" style="">
								 <div class="g-pricingtable-ribbon">
											<img src="<%=request.getContextPath()%>/resources/img/unlimited.png">
										</div> 
									<div>
									<h1>${sm.subName}</h1>
									<p class="features-text">Unlimited access</p>	
									 <p class="device-desc"><span style="white-space:pre"><c:out value="${emps1}"/></span></p> --%>
				</div>
			</div>
		</div>
	</form:form>
	<c:if
		test="${boothAdminLogin1.loginTour==0 && boothAdminLogin1.subId!=1 || boothAdminLogin1.subId==1}">
		<div>
			<a href="getRegisteredDeviceConfig"><button class="btn btn-green"
					style="margin: 2% 3%;">Start configuring your Booth &
					Event</button></a>
		</div>
		<div class="clearfix"></div>
	</c:if>
	<div class="clearfix"></div>


	<div class="clearfix"></div>
	<%-- <c:if test="${transactionHistoryVOs.size}"> --%>
	<div class="table-class table-responsive">
		<h2 class=" table table-hover" style="margin: 25px 0px 20px">Payment
			History</h2>


		<table id="example" class="display table-sm" cellspacing="0"
			width="100%">
			<thead style="background-color: #05a42e; color: white;">
				<tr>
					<th>Transaction Id</th>
					<th>Payment Type</th>
					<th>Payment Date</th>
					<th>Payment Amount</th>
					<th>Subscription Name</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody style="background-color: #fff; border: 1px solid #c3c3c3;">
				<c:forEach items="${transactionHistoryVOs }" varStatus="loop"
					var="th">
					<tr class="admin-row" style="border: 1px solid #ddd;">
						<td>${th.txnId}</td>
						<td>${th.paymentType}</td>
						<td>${th.paymentDate}</td>
						<td>$&nbsp;${th.paymentAmount}</td>
						<td>${th.itemName}</td>
						<td>${th.statusResponse}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%-- </c:if>	 --%>
	<div class="clearfix"></div>
</div>
