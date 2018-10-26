	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/dataTable.css" />
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
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
		var plancode=$("#plancode").val();
		 $('#plancode'+plancode).slideToggle('load', function(){
			 alert(plancode.text());
			 plancode.html(plancode.text() == 'Your Current Plan' ? 'Order Now' : 'Your Current Plan');
		    });
		
		
			var response=${plans};
			 console.log(response);
	    		  console.log(response.plans);
	    		  var plansDetailed = [];
	    			
	    		  response.plans.sort(function(a, b){
  				    return a.plan_code-b.plan_code
  				})
	    			for (i = 0; i < response.plans.length; i++)
	    			{
	    				var plan = {};
	    				plan.name = response.plans[i].name;
	    				plan.billing_cycles = response.plans[i].billing_cycles;
	    				plan.created_time = response.plans[i].created_time;
	    				plan.created_time_formatted = response.plans[i].created_time_formatted;
	    				plan.interval = response.plans[i].interval;
	    				plan.interval_unit = response.plans[i].interval_unit;
	    				plan.plan_code = response.plans[i].plan_code;
	    				plan.product_id = response.plans[i].product_id;
	    				plan.product_type = response.plans[i].product_type;
	    				plan.status = response.plans[i].status;
	    				plan.recurring_price = response.plans[i].recurring_price;
	    				plansDetailed.push(plan);
	    				
	    				
	    				
	    			 	if(i==0){
	    					$('.login_form').append("<div class='plan-rate-box' style='margin:0px auto;'> <div class='rate-box' id='abc1'> <div class='g-pricingtable-ribbon'> <img src='<%=request.getContextPath()%>/resources/img/single_event.png'> </div><div style='padding:15px;'> <h1>Test Drive</h1><p class='dollar'>$&nbsp; Free</p> <p class='dollar-text'>limited features </p> <p class='dollar-para'>Play around with the technology and see if it's right for you.</p> <!-- <p class='dollar-para'>Backgrounds for 24 hrs </p> --> <div class='border-line'></div>  <p class='device-desc'>1 Apple '\Camera\' device <br/>1  Apple '\Guest Interface\' device <br/>1 Watermarked iAmuse Background</p> <div class='clearfix'></div> </div> <a href='detailSubscriptionPlan?id="+i+"' id='plancode1'><button class='btn btn-green' id='orderButton1'>Order Now</button></a><a href='javascript:void(0)' ><button class='btn btn-green' style='border-radius: 0px;padding: 13px;' id='planButton1'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button></a><div class='clearfix'></div></div></div>");
	    				}
	    			 	if(response.plans[i].status=='active')
	    				$('.login_form').append("<div class='plan-rate-box' style='margin:0px auto;'> <div class='rate-box' id='abc"+response.plans[i].plan_code+"'> <div class='g-pricingtable-ribbon'> <img src='<%=request.getContextPath()%>/resources/img/single_event.png'> </div><div class='g-pricingtable-ribbon'> <img src='<%=request.getContextPath()%>/resources/img/unlimited.png'> </div><div style='padding:15px;'> <h1>"+response.plans[i].name+"</h1><p class='dollar'>$&nbsp;"+response.plans[i].recurring_price+"</p> <p class='dollar-text'></p><div class='border-line'></div>  <p class='device-desc'>"+response.plans[i].description.split(",").join("<br/>")+"</p> <div class='clearfix'></div> </div> <a href='createCustomer?planCode="+response.plans[i].plan_code+"' id='plancode"+response.plans[i].plan_code+"'><button class='btn btn-green' id='planButton"+response.plans[i].plan_code+"'>Order Now</button></a><div class='clearfix'></div></div></div>");
	    			 }
	} );

	 $("#d").addClass("active_menu");
     $('#effect').delay(10000).fadeOut(400);

$(document).ready(function(){
	var subId=document.getElementById("subid").value;
	if(subId=='1'){
    	$("#abc1").addClass("active-rate-box");
    	$("#planButton1").html('Your Current Plan');
    }else{
    	$("#abc"+subId).addClass("active-rate-box");
    	$("#planButton"+subId).html('Your Current Plan');
    }
	
	 $( "#plancode"+subId ).click(function() {
 	        alert("you have already taken this plan");
 	   		return false;
 	  });
})


function upgrade(){
    	   window.location.href ="<%=request.getContextPath()%>/upgradePlan";
}

var end =  new Date('${boothAdminLogin.subEndDate}');
if(end=='Invalid Date'){
}else{

    var _second = 1000;
    var _minute = _second * 60;
    var _hour = _minute * 60;
    var _day = _hour * 24;
    var timer;

    function showRemaining() {
        var now = new Date();
        var distance = end - now;
        if (distance < 0) {

            clearInterval(timer);
            document.getElementById('countdown').innerHTML = ' EXPIRED!';

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
	}
    timer = setInterval(showRemaining, 1000);
    
    $('#example').DataTable();
</script>
	
      <input type="hidden" value="${boothAdminLogin.planCode}" id="subid"/>
<div class="right-pannel" >
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left">Choose The Plan That Works Best</h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:15px 20px 20px;">
						<div class="col-row">
							<b><div id="countdown"></div></b>
							<div class="login_form" style="width:100%;margin:0px;">
								 <c:if test="${boothAdminLogin.planCode=='1'}">
									<center><a href="getRegisteredDeviceConfig"><button class="btn btn-green" style="margin:20px auto;">Start configuring your Booth & Event</button></a></center>
									<div class="clearfix"></div>
								</c:if>
								 <div class="clearfix"></div>
							</div>
						</div>
					</div>
	<div class="clearfix"></div>
	<h2 class="heading" style="margin:25px 0px -20px">Payment History</h2>

<div style="background:#fff;padding:20px;border-radius:5px;border:1px solid #e2e2e2;margin:30px 0px">	
	<table id="example" class="display" cellspacing="0" width="100%">
	<thead>
  <tr>
    <th>Transaction Id</th>
    <th>Payment Type</th>
    <th>Payment Date</th>
    <th>Payment Amount</th>
    <th>Subscription Name</th>
     <th>Status</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${transactionHistoryVOs }" varStatus="loop" var="th">
  <tr>
    <td>${th.txnId}</td>
    <td>${th.paymentType}</td>
    <td>${th.paymentDate}</td>
    <td>$&nbsp;${th.paymentAmount}</td>
    <td>${th.itemName}</td>
    <td>${th.paymentStatus}</td>
  </tr>
  </c:forEach>
  </tbody>
</table>
</div>
<%-- </c:if>	 --%>
<div class="clearfix"></div>
</div>
	