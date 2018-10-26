<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    function boothsetup(){
    	$("#b").removeClass("active_menu");
		$("#d").removeClass("active_menu");
		$("#menu-toggle").addClass("active_menu");
    }
    
     $(document).ready(function(){
    	var userId=document.getElementById("user").value;
    	if(userId!=""){
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
    	}
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
	<img src="http://www.willmaster.com/images/preload.gif">
</div>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<div id="abc">
<!-- Popup Div Starts Here -->
<div id="popupContact">
<!-- Contact Us Form -->
<center><h3 class="heading" style="font-size: 10px;padding:20px;">Confirm that you can receive emails from iAmuse by putting in your email below.</h3></center>
<input id="emailId" name="name" placeholder="Name" value="${boothAdminLogin.emailId}" type="text" style="margin:0px 20px 11px 10px;width: 91%;">
<button onclick="sentTestEmail();" class="btn btn-green" style="float: right;margin-right:6%;margin-bottom: 15px;">send</button>
<button onclick="div_hide();" class="btn btn-green" style="float: right;margin-right:2%;">Cancel</button>
</div>
<!-- Popup Div Ends Here -->
</div>				
		
		<input type="hidden" id="user"	value="${boothAdminLogin.userId}">
				<link href="<%=request.getContextPath()%>/resources/css/css/accordion-menu.css" rel="stylesheet">
				<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/js/script.js"></script>
				<div class="left-pannel">
					<div id='cssmenu'>
							<ul>
							   <li id="d" class='last has-sub' ><a href="getSubscription" data-toggle="tooltip" title="This indicates your current subscription and the option to upgrade, if any is available here. You will be directed to the Payment Gateway to upgrade your plan" style="padding-left:15px">Subscription</a></li>
							   <li id="b" class='has-sub'><a href="getEventList" data-toggle="tooltip" title="You can begin by clicking on the button and start creating an event"><span style="margin-right:5px;" class="glyphicon glyphicon-home"></span>Home/Events</a></li>
							   <li class='has-sub' ><a href='#' id="menu-toggle" onclick="boothsetup();" ><span style="padding-left:15px">Booth Setup </span></a>
							      <ul class='has-sub-1' style="padding-left:15px">
							      	<li id="a" ><a href="javascript:void(0);" onclick="getRegisteredDevice()" data-toggle="tooltip" title="These devices appearing here would be the ones synced together and being used in the ongoing event">Registered Devices</a><c:if test="${deviceRegistration.size()==2}"><span class="glyphicon glyphicon-ok-circle pull-right"></span></c:if></li>
									<li id="q" ><a href="javascript:void(0);" onclick="boothSetUp()" data-toggle="tooltip" title="">Set Transparent Color</a><c:if test="${boothAdminLogin.isDefaultRgb==false}"><span class="glyphicon glyphicon-ok-circle pull-right"></span></c:if></li>
									<li id="h" ><a href="javascript:void(0);" onclick="cropEdges()" data-toggle="tooltip" title="">Green Screen Cropping</a><span class="glyphicon glyphicon-ok-circle pull-right" id="gsc" style="display: none;"></span></li>
									<li id="p" ><a href="javascript:void(0);" onclick="zoomPage()" data-toggle="tooltip" title="">Booth Zoom Profile</a><span class="glyphicon glyphicon-ok-circle pull-right" id="bzp" style="display: none;"></span></li>
							      </ul>
							   </li>
							   <li id="i" class='has-sub' data-toggle="tooltip" ><a href="changeOldPassword">Change Password</a></li>
							</ul>
</div>
				</div>