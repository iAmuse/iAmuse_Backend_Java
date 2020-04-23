<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	 <!--<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>-->
   
	<script type="text/javascript">
	
	
	 function sentTestEmail(){  
		 document.getElementById("gif").style.display = "";
   	  var email=document.getElementById("emailId").value;
   		$("#a").removeClass("active_menu");
   		$("#d").removeClass("active_menu");
		$("#q").removeClass("active_menu");
		$("#h").removeClass("active_menu");
		$("#p").removeClass("active_menu");
		$("#g").removeClass("active_menu");
		$("#i").addClass("active_menu");
   	//var id=	document.getElementById("eidnumber").value;
    $.ajax({
          url : 'sendTestMail',
          data:{"email": email },
          success : function(data) {
             if(data){
            	 document.getElementById("gif").style.display = "none";
            	 document.getElementById('abc').style.display = "none";
            	 setTimeout(function () { alert("Test Mail Send Successfully"); }, 2000);
            	 
             }else{
            	 document.getElementById("gif").style.display = "none";
            	 document.getElementById('abc').style.display = "none";
            	 setTimeout(function () { alert("Test Mail Send Failed"); }, 2000);
             }

          }
      });
   	  
   	  <%-- window.location.href ="<%=request.getContextPath()%>/sendTestMail?email="+email; --%>
     }
     
     function div_show() {
   	  document.getElementById('abc').style.display = "block";
   	  }
     
     function check_empty() {
   	  if (document.getElementById('name').value == "" || document.getElementById('email').value == "" || document.getElementById('msg').value == "") {
   	  alert("Fill All Fields !");
   	  } else {
   	  document.getElementById('form').submit();
   	  alert("Form Submitted Successfully...");
   	  }
   	  }
   	  //Function To Display Popup
   	  function div_show() {
   	  document.getElementById('abc').style.display = "block";
   	  }
   	  //Function to Hide Popup
   	  function div_hide(){
   	  document.getElementById('abc').style.display = "none";
   	  }
   	  function clearDevicePictures(){
   		var r = confirm('Clear device will clear all the images which belongs to iAmuse application from your camera device gallery. Do you really want to do this?');
   		  if(r==true){
   		$.ajax({
   		  type: "GET",
   		  url: "clearDevicePictures",
   		  cache: false,
   		  success: function(data){
   		     alert("Clear Device Request Send Successfully");
   		     return false;
   		  }
   		});
   		  }
   		$("#a").removeClass("active_menu");
		$("#q").removeClass("active_menu");
		$("#h").removeClass("active_menu");
		$("#d").removeClass("active_menu");
		$("#p").removeClass("active_menu");
		$("#i").removeClass("active_menu");
		$("#g").addClass("active_menu");
  		
  	}
    function resetDefault() {
  	     
  	     var r = confirm("Are you Sure Want to Reset Default?");
  	    if (r == true) {
  	     
  	        window.location="<%=request.getContextPath()%>/resetSystemDefaultRGBValue"
  	        
  	    } 
  	 }
    
    function getRegisteredDevice() {
 	        window.location="<%=request.getContextPath()%>/getDevices"
 	 } 
    function boothSetUp() {
	        window.location="<%=request.getContextPath()%>/boothSetUp"
	 } 
    function cropEdges() {
	        window.location="<%=request.getContextPath()%>/cropEdges"
	 } 
    function zoomPage() {
	        window.location="<%=request.getContextPath()%>/zoomPage"
	 } 
    function driveupload() {
        window.location="<%=request.getContextPath()%>/driveupload"
 } 
    function boothsetup(){
    	$("#b").removeClass("active_menu");
		$("#d").removeClass("active_menu");
		$("#menu-toggle").addClass("active_menu");
    }
    
    /* $("#menu-toggle").click( function(){
    	alert("fd");
    	  $(this).find('i').toggleClass('glyphicon glyphicon-triangle-right').toggleClass('glyphicon glyphicon-triangle-bottom');
    }); */
    
    $(document).ready(function(){
    	var userId=document.getElementById("user").value;			
    	$.ajax({
            url : 'validateTakeTestPicture',
            data:{"userId": userId },
            success : function(data) {
               if(data){
              	 	document.getElementById("gsc").style.display = "block";
              		document.getElementById("bzp").style.display = "block";
               }
            }
        });
    });
    $(document).ready(function(){
        $("#menu-toggle").on('click',function(){
          if($("#collapse-menu").hasClass('active')){
            $("#collapse-menu").addClass('menu-active');
          }
          else{
            $("#collapse-menu").removeClass('menu-active');
          }
        });
       });
</script>		
<style>
/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

#abc {
width:100%;
height:100%;
opacity:.95;
top:0;
left:0;
display:none;
position:fixed;
background-color:#313131;
overflow:auto;
z-index: 50;
}

div#popupContact {
    background: #fff;
    position: absolute;
    left: 50%;
    top: 17%;
    margin-left: -172px;
    font-family: 'Raleway',sans-serif;
    width: 29%;
    /* height: 30%; */
}
/**/

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

<div id="gif" style="display: none;">
	<img src="https://www.willmaster.com/images/preload.gif">
</div>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<div id="abc">
<!-- Popup Div Starts Here -->
<div id="popupContact">
<!-- Contact Us Form -->
<center><h3 class="heading" style="font-size: 10px;padding:20px;">Confirm that you can receive emails from iAmuse by putting in your email below.</h3></center>
<input id="emailId" name="name" placeholder="Name" value="${boothAdminLogin2.emailId}" type="text" style="margin:0px 20px 11px 10px;width: 91%;">
<button onclick="sentTestEmail();" class="btn btn-green" style="float: right;margin-right:6%;margin-bottom: 15px;">send</button>
<button onclick="div_hide();" class="btn btn-green" style="float: right;margin-right:2%;">Cancel</button>
</div>
<!-- Popup Div Ends Here -->
</div>				
		
		<input type="hidden" id="loginTour"	value="${boothAdminLogin2.loginTour}">
		<input type="hidden" id="user"	value="${boothAdminLogin2.userId}">
				<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/accordion-menu.js"></script>
				<link href="<%=request.getContextPath()%>/resources/css/css/accordion-menu.css" rel="stylesheet"> --%>
				<!--<link href="<%=request.getContextPath()%>/resources/css/css/accordion-menu.css" rel="stylesheet">-->
				<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/script.js"></script>
				 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/script-menu.js"></script>
				<link href="<%=request.getContextPath()%>/resources/css/css/style.css" rel="stylesheet">
				<div class="left-pannel col-lg-2 col-md-3 col-xs-5">
					<%-- <div id="accordion">
						<ul class="menu-list">
						<c:if test="${boothAdminLogin2.loginTour==1}"><li id="b"><a href="getEventList" data-toggle="tooltip" title="You can begin by clicking on the button and start creating an event"><span style="margin-right:5px;" class="glyphicon glyphicon-home"></span>Home/Events</a></li></c:if>
							<li>
								<div>Booth Setup </div>
								<ul class="acc-menu-list">
									<li id="a"><a href="javascript:void(0);" onclick="getRegisteredDevice()" data-toggle="tooltip" title="These devices appearing here would be the ones synced together and being used in the ongoing event">Registered Devices</a></li>
									<li id="q"><a href="javascript:void(0);" onclick="boothSetUp()" data-toggle="tooltip" title="">Set Transparent Color</a></li>
									<li id="h"><a href="javascript:void(0);" onclick="cropEdges()" data-toggle="tooltip" title="">Green Screen Cropping</a></li>
									<li id="p"><a href="javascript:void(0);" onclick="zoomPage()" data-toggle="tooltip" title="">Booth Zoom Profile</a></li>
									<li id="g"><a href="javascript:void(0);" data-toggle="tooltip" onclick="clearDevicePictures()" title=""  style="background-color: #fff !important;">Clear Device Pictures</a></li>
									<li id="i"><a href="javascript:void(0);" data-toggle="tooltip" title="" id="popup" onclick="div_show()">Test Email<br/><i style="font-size: 9px">Confirm that you can receive emails from iAmuse by putting in your email below.</i></a></li>
									<!-- <li id="c"><a href="getContactEmail" data-toggle="tooltip" title="These emails have received images from iAmuse for the events listed against the names">Contact Emails</a></li> -->
								</ul>
							</li>
							<li id="d"><a href="getSubscription" data-toggle="tooltip" title="This indicates your current subscription and the option to upgrade, if any is available here. You will be directed to the Payment Gateway to upgrade your plan"><span style="margin-right:5px; font-size:14px;" class="glyphicon glyphicon-repeat"></span>Subscription</a></li>
							
						</ul>
					</div> --%>
					<div id='cssmenu'>
							<ul>
							   <!-- <li class='active'><a href='#'><span>Home</span></a></li> -->
							   <!-- <li class='has-sub'><a href='#'><span>Products</span></a> -->
							   
							   <li id="d" class='last has-sub' ><a href="getSubscription" data-toggle="tooltip" title="This indicates your current subscription and the option to upgrade, if any is available here. You will be directed to the Payment Gateway to upgrade your plan" style="padding-left:15px">Subscription</a></li>
 <%-- <c:if test="${boothAdminLogin2.loginTour==1}"> --%><li  id="b" class='has-sub'><a href="getEventList" data-toggle="tooltip" title="You can begin by clicking on the button and start creating an event" style="padding-left:15px">Home/Events</a></li><%-- </c:if>   --%>

 <li id="collapse-menu" class='has-sub menu-drop active'><a href='#' id="menu-toggle" ><span style="padding-left:15px;">
         Booth Setup</span></a>
 
 <ul class='col-lg-12 has-sub-1 sub-item'>
							      	<li id="a" ><a href="javascript:void(0);" onclick="getRegisteredDevice()" data-toggle="tooltip" title="These devices appearing here would be the ones synced together and being used in the ongoing event">Registered Devices</a><c:if test="${deviceRegistration.size()==2}"></c:if></li>
									<li id="q" ><a href="javascript:void(0);" onclick="boothSetUp()" data-toggle="tooltip" title="">Set Transparent Color</a><c:if test="${boothAdminLogin2.isDefaultRgb==false}"></c:if></li>
									<!-- <li id="h" ><a href="javascript:void(0);" onclick="cropEdges()" data-toggle="tooltip" title="">Green Screen Cropping</a></li> -->
									<!-- <li id="p" ><a href="javascript:void(0);" onclick="zoomPage()" data-toggle="tooltip" title="">Booth Zoom Profile</a></li> -->
 </ul></li>
							      <!-- <li>
							    <button id="login"> Upload Files to Drive</button>
							   </li> -->
							   <li class='last has-sub'>
							    <!-- <a href="javascript:void(0);" onclick="driveupload()" data-toggle="tooltip" title="" style="padding-left: 15px;"> Upload Files to Drive</a> </li> -->
							   </li> 
							</ul>
				</div></div>
				
<div class="mobile-container">

<!-- Top Navigation Menu -->
<div class="topnav">
  <!--  <a href="#home" class="active">Logo</a>-->
  <div id="myLinks">
    <!--<a href="#news">News</a>
    <a href="#contact">Contact</a>
    <a href="#about">About</a>-->
	<ul>
<li><a href="getSubscription" class="menu-item" onclick="test()">Subscription</a></li>
<li><a href="getEventList" class="menu-item">Home/Events</a></li>
 <li class="has-sub"><a id="boothId" class="menu-item accordion-heading" data-toggle="collapse" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
         Booth setup</a>
<ul id="collapseOne"class="has-sub1 accordion-body collapse"  aria-labelledby="headingOne">
<li><a href="javascript:void(0);" onclick="getRegisteredDevice()" class="menu-item sub-menu accordion-toggle">Registered Devices</a></li>
<li><a href="javascript:void(0);" onclick="boothSetUp()" class="menu-item sub-menu accordion-toggle">Set Transparent Color</a></li>
<!-- <li><a href="javascript:void(0);" onclick="cropEdges()" class="menu-item sub-menu accordion-toggle">GreenScreen Croping</a></li> -->

 </ul></li>
 <!-- <li><a href="#" class="menu-item">Upload to Storage</a></li> -->
  </div>
  <a href="javascript:void(0);" class="icon" onclick="myFunction(this)">
   <div class="bar1"></div>
  <div class="bar2"></div>
  <div class="bar3"></div>
  </a>
</div>



<!-- End smartphone / tablet look -->
</div>
<script>
function myFunction(y) {
	  var x = document.getElementById("myLinks");
	   y.classList.toggle("change");
	  if (x.style.display === "block") {
	    x.style.display = "none";
	  } else {
	    x.style.display = "block";
	  }
	}
	$(document).ready(function(){
	$(".sub-menu").on('click',function(){
	 $(".has-sub1").addClass("collapse-in");
	 $('[data-toggle=collapse]').prop('disabled',true);
	    
	});
	/*$("#boothId").on('click',function(){
	  $(".has-sub1").removeClass("collapse-in");
	});*/
	 $("#boothId").on('click',function(){
	    	      $(".has-sub").toggleClass('closed');
				  if($('.has-sub1').hasClass("collapse-in"))
				  {
				    $(".has-sub1").removeClass("collapse-in");
				    $('[data-toggle=collapse]').prop('disabled',false);
				  }
	    	  });});
	function test(){
	    $('#content-area').load('iamuse.html');
	}

</script>
				<!-- <script type="text/javascript">

				var login = document.getElementById('login');

				login.onclick = function(){

				    var script = document.createElement("script");
				    script.type = "text/javascript";
				    script.src = "WebRoot/resources/js/main.js."; 
				    document.getElementById("login").href = "http://www.cnn.com/";
				    return false;

				}


				</script> -->