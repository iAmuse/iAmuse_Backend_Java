<!--  Start of Desktop Menu   -->	
				<div class="left-pannel col-lg-2">
					<ul class="menu-list">
						<li id="a"><a href="getRegisteredDevice">Registerd Devices</a></li>
						<!-- <li id="b"><a href="getSuperAdminSubscription">Subscription </a></li> -->
						<li id="c"><a href="getBoothAdminList">Booth Admin List</a></li>
						<li id="d"><a href="getEventList">Events</a></li>
						<!-- <li id="e"><a href="getReports">Reports</a></li> -->
					</ul>
				</div>
				
<!--  End of Desktop Menu   -->


<!--  Start of Mobile Menu -->					
				
<div class="mobile-container">

<div class="topnav">
	<div id="myLinks">
	
			<ul>
				<li id="a"><a href="getRegisteredDevice">Registerd Devices</a></li>
				<li id="c"><a href="getBoothAdminList">Booth Admin List</a></li>
				<li id="d"><a href="getEventList">Events</a></li>
 			</ul>
	</div>
	<a href="javascript:void(0);" class="icon" onclick="myFunction(this)">
   		<div class="bar1"></div>
  		<div class="bar2"></div>
  		<div class="bar3"></div>
  	</a>
</div>
</div>
<!--  End of Mobile Menu   -->


<!--  Script  -->
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
         $("#mylinks1").css("display","none");
		}); 
		
</script> 