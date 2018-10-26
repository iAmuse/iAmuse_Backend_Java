<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>

<style>
   .form-horizontal .control-label{text-transform: capitalize; text-align:left; float:left !important;}
   .form-left{ border-bottom: 1px solid grey; margin-bottom:20px;margin-top:20px;padding-bottom:20px; }
   input[type="text"],input[type="email"],input[type="number"],input[type="tel"],input[type="month"]{ max-width: 100%;}
	.form-left input[type="text"]{ max-width: 80%;}
	.form-content{margin-top: 20px;}
    </style>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/dataTable.css" />
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
<script>
$(document).ready(function(){
	 var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	}; 
	var tech = getUrlParameter('planCode');
	 $("#d").addClass("active_menu");
     $('#effect').delay(10000).fadeOut(400);

	var response=${plan};
  		  console.log(response.plan);
  		  
  		  	document.getElementById("name").innerHTML =response.plan.name;
  		  	document.getElementById("recurring_price").innerHTML ="$ "+response.plan.recurring_price;
  			document.getElementById("description").innerHTML =response.plan.description;
  			var a = document.getElementById("payment"); //or grab it by tagname etc
  			a.href = response.plan.url;
		  	
  		
});
</script>
<style>
	.right-pannel{height:auto !important}
		#gif {
width:100%;
height:100%;
opacity:.95;
top:0;
left:0;
text-align:center;
position:fixed;
background-color:#313131;
overflow:auto;
z-index: 1000;
padding-top:20%;
}
</style>
<div id="gif" class="pull-right" style="margin: 5px -220px;display:none;">
										<img src="http://www.willmaster.com/images/preload.gif" alt="loading...">
									</div>
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
							<div class="login_form" style="width:100%;margin:5% 0px 0 39%;">
								<!-- <h1 style="color:#717171;font-size:18px;margin-top:25px;">To upgrade, choose subscription plan based on your preferences</h1> -->
								<div class="plan-rate-box" style="margin:0px auto;">
								
									<div class="rate-box" id="" style="">
										<div style="/* height:auto; */padding:15px;">
											<h1 id="name"></h1>
											
											<p class="dollar" id="recurring_price"></p>
											
											<p class="dollar-text" ></p>
											<p class="device-desc" id="description"></p>
											<!-- <p class="dollar-para">Backgrounds for 24 hrs </p> -->
											<div class="border-line"></div>
											
											<div class="clearfix"></div>
										</div>
										<a href="" id="payment"><button  class="btn btn-green" id="orderButton">Pay</button></a>
										<div class="clearfix"></div>
									</div>
									
								</div>
									<div class="clearfix"></div>
								 <div class="clearfix"></div>
							</div>
						</div>
					</div>
	<div class="clearfix"></div>
<div class="clearfix"></div>
</div> 
   