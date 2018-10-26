		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>	
<style>
.selected {background: #ddd !important;}
</style>
<script type="text/javascript">
      $(document).ready(function() {
    	  
    		 var pageid=${pageid};
 			 $("#${pageid}").addClass("active"); 
 			
    	  $('#effect').delay(9000).fadeOut(400);
            $("#b").addClass("active_menu");
        });
     /*  $('#example').dataTable({
    	    "oLanguage": {
    	        "sEmptyTable":"My Custom Message On Empty Table"
    	    }
    	}); */
</script>		
		
		<div class="right-pannel">
					
					 <c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left" style="padding-top:15px;">Your Events</h1>				
					<!-- <p style="subtext">Only Events matching current Booth Zoom Profile are shown.</p> -->
					<div class= "heading pull-right" data-step="1" data-intro="You can begin by clicking on the button and start creating an event">
					<a href="create-event.html" class="pull-right" data-toggle="tooltip" title="Click to create a new Event"><button class="btn btn-green">Create Event</button></a>
					<div class="clearfix"></div>					
					</div>					
					<div class="clearfix"></div>
					<p class="subtext">Your Booth's <a href="zoomPage">Booth Zoom Profile</a> (<c:if test="${fovbyuser==1.00}">Zoomed In</c:if><c:if test="${fovbyuser==0.50 || fovbyuser==0.5}">Zoomed Out</c:if><c:if test="${fovbyuser==0.75}">Medium Zoom</c:if>).</p>
					<!-- <p class="subtext">Only those events are shown here, which are compatible with current booth zoom level</p> -->
					<div class="inner-content">
						<div class="col-row">
							<table class="table table-striped" id="example">
								<thead>
									<tr>
										<th>Event</th>
										<th>Host Name</th>
										<th>Event Host Email</th>
										<th>Total Guest Sessions</th>
										<th>Booth Zoom Profile</th>
										<th>Date</th>
										<th width="160px">Action</th>
									</tr>
								</thead>
								<tbody>
								<c:if test="${empty eventList}">
								<tr><td></td><td><td></td></td><td>Event Data Not Found</td><td></td><td></td><td></td></tr>
								</c:if>
								<c:forEach items="${eventList }" var="el" varStatus="loop">
									<tr>
										<td>${el.eventName }</td>
										<td>${el.sponsorName}</td>
										<td>${el.eventHostEmail}</td>
										<td>${el.totalGuestSession}</td>
										<td><c:if test="${el.zoomScale==1.00}">Zoomed In</c:if><c:if test="${el.zoomScale==0.50 || el.zoomScale==0.5}">Zoomed Out</c:if><c:if test="${el.zoomScale==0.75}">Medium Zoom</c:if></td>
										<td>${el.eventStart}</td>
										<%-- <c:if test="${el.zoomScale eq  fovbyuser}"> --%>
										<td class="action_td">
											<span class="action_span"><a href="eventReportDetails?eventId=${el.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></a></span>
											<span class="action_span"><a href="getUploadedImages?eventId=${el.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png"></a></span>
											<c:if test="${boothAdminLogin.planCode!='1'}"><span class="action_span"><a href="deleteEvent?eventId=${el.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/delete-icon.png"></a></span></c:if>
										</td>
										<c:if test="${el.zoomScale !=  fovbyuser}">
										<td >
										</td>
										</c:if>
									<%-- 	</c:if> --%>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
							<div class="pageing">
								<c:set var="pC" value="${pageCount}"/>
      								<c:set var="pId" value="${pageid}"/>
      								<c:set var="startPage" value="${pageid - 5 > 0?pageid - 5:1}"/>
      								<c:if test="${pageCount>5}">
      								<c:set var="endPage" value="${startPage + 5}"/>
      								</c:if>
      								<c:if test="${pageCount<5}"><c:set var="endPage" value="${pageCount}"/></c:if>
								<ul class="pagination">
									<li><c:if test="${pId > 1}"><a href="getSubscribedEventList?pageid=${pageid-1}&total=${total}">&laquo;</a></c:if> </li>
									<c:forEach var="p" begin="${startPage}" end="${endPage}"><li  id="${p}"><a href="getSubscribedEventList?pageid=${p}&total=${total}">${p}</a></li></c:forEach>
									<li><c:if test="${pId < pC}"><a href="getSubscribedEventList?pageid=${pageid+1}&total=${total}">&raquo;</a></c:if> </li> 
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>