					<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script type="text/javascript">
      $(document).ready(function() {
            $("#a").addClass("active_menu");
        });
</script>		
<style>
.left-pannel{
      min-height:750px !important;
}
</style>	
<div class="col-lg-2" style="width:20%;"></div>
			<div class="right-pannel col-lg-10 col-xs-12 right-height">
					<h1 class="heading">Your Device</h1>
					<c:if test="${deviceRegistration.size() > 0}">
					<div class="col-lg-12" style="margin-left: 2px;width: 96%; margin-bottom: 30px;">
					<div class="inner-content" style="border: 1px solid #000;background:#fff">
						<div class="col-row">
							<div class="col-3">
								<h2>&nbsp;</h2>
								<p>Device</p>
								<p>iOS</p>
								<p>Detected resolution</p>
								<p>Guided Access enabled</p>
								<p>Device Storage</p>
								<p>Wireless network</p>
								<p>IP address</p>
								<p>last Sync</p>
								<a>&nbsp;</a>
							</div>
							<c:forEach items="${deviceRegistration}" var="dr">
							<div class="col-3">
								<h2>${dr.deviceType}</h2>
								<p>${dr.deviceName}</p>
								<p>${dr.deviceType}</p>
								<%-- <p>${deviceRegistration.operationgSystemVersion}</p> --%>
								<p>${dr.deteactedResolution}</p>
								<p>${dr.guidedAccessEnabled}</p>
								<p>${dr.deviceStorage}</p>
								<p>${dr.wirelessNetwork}</p>
								<p>${dr.ipAddress}</p>
								<p>${dr.lastSyncTime}</p>
								<a href="syncDevice?deviceId=${dr.deviceId}">Sync Now</a>
							</div>
							
							</c:forEach>
							</div>
							<div class="clearfix"></div>
						</div></div>
						</c:if>
						<c:if test="${deviceRegistration.size() == 0}">
						<p style="margin-left:12px">No Device Synchronized</p>
						</c:if>
						
				</div>
				<div class="clearfix"></div>