
<script>
function myFunction() {
	$('.logout-section').css('display','none');
}
</script>
<script type="text/javascript">
function handleClientLoad() {
  gapi.load('client:auth2', initClient);
}

function initClient() {
  gapi.client.init({
	  apiKey: 'AIzaSyDFgid0kpXE6298Ll0Ix4K77481oa5Ob-w',
      discoveryDocs: ["https://people.googleapis.com/$discovery/rest?version=v1"],
      clientId: '655850534607-03k4t3r5g8b2eqja34f6ljrfdkv46ns6.apps.googleusercontent.com',
      //apiKey: 'AIzaSyAa4EelXu7576FWb4XF-KO9fQf0dGQ7qmg',
      //discoveryDocs: ["https://people.googleapis.com/$discovery/rest?version=v1"],
      //clientId: '41725756077-9bpfdps2kb87767nvbr1ff52tgr2n9ih.apps.googleusercontent.com',
      scope: 'profile'
  })
}

function handleSignOutClick(event) {
  gapi.auth2.getAuthInstance().signOut();
}


</script>

<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>


		<div class="header">
				<div class="container-1">
					<div class="logo">
						<img src="<%=request.getContextPath()%>/resources/images/images/logo.png">
					</div>
					<div class="profile_tab">
							<c:if test="${not empty boothAdminLogin.image}">
							<div class="profile-pic">
							<img  src="<%=request.getContextPath()%>/imageDisplay?id=${boothAdminLogin.userId}">
							<%-- <img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png"> --%>
							</div>
							</c:if>
							
							<c:if test="${empty boothAdminLogin.image}">
							<div class="profile-pic">
								<img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png">
							</div>
							</c:if>
						<div class="profile-name">
							<p>hello,&nbsp; ${boothAdminLogin.username}<p>
							<p>Welcome Back<p>
							<img src="<%=request.getContextPath()%>/resources/images/images/drop-down.png" class="drop-down">
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			
						<div class="logout-section" onclick="myFunction()">
							<div class="logout-box">
								<ul>
									<li><a href="getProfileDetails">View Profile</a></li>
									<li style="border-bottom:0px"><a href="#" onclick="signOut();">Logout</a></li>
								</ul>
							</div>
						</div>
						<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
						<script async defer src="https://apis.google.com/js/api.js"  onload="this.onload=function(){};handleClientLoad();" onreadystatechange="if (this.readyState === 'complete') this.onload()"></script>
<script>
function signOut(){
	handleSignOutClick();
	window.location.href='SignOut';
}

$(document).ready(function(){

		$(window).resize(function() {
			var header_height=$('.header').height();
			//alert(header_height);
			$('.right-pannel').height($(window).height() - (header_height+50));
			$('.logout-section').height($(window).height() - (header_height));
		});
		$(window).trigger('resize');
		$('.drop-down').click(function(){
			$('.logout-section').toggle();			
		});
		$('.logout-box ul li a').click(function(){
			$('.logout-section').hide();
		});
})
</script>