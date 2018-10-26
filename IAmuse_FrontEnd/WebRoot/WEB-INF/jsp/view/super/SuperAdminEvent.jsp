<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>	
<script >
$(document).ready(function() {
$("#d").addClass("active_menu");
});
</script>
<style>
	.admin-row td{background:#05a42e !important}
</style>
<div class="right-pannel">
<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left" style="padding-top:15px;">Events</h1>
					<a href="#" class="pull-right" title="Events By Me"><%-- <img src="<%=request.getContextPath()%>/resources/images/images/dots.png" style="margin-left:10px;"> --%></a>
					<!-- <a href="create-event.html" class="pull-right"><button class="btn btn-green">Create Event</button></a> -->
					<a href="superAdminCreateEvent" class="pull-right"><button class="btn btn-green">Create Event</button></a>					
					<div class="clearfix"></div>
					<div class="inner-content">
						<div class="col-row">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Date</th>
										<th>Event</th>
										<th>Sponsor Name</th>
										<th>Created By</th>
										<th width="160px">Action</th>
									</tr>
								</thead>
								<tbody>
					<c:forEach items="${eventList}" var="item">
					<c:if test="${item.eventType=='default'}">
						<tr class="admin-row">
					<input type="hidden" name="EId" value="${item.EId}"/>
							<td>${item.eventStart}</td>
							<td>${item.eventName}</td>
							<td>${item.sponsorName}</td>
							<td>${item.createrName}</td>
							<td class="action_td"><span class="action_span">
							<%-- <a href="viewSuperAdminEvent?id=${item.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></a></span> --%>
							<a href="eventReportDetailsSA?eventId=${item.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></a></span>
							
							<c:if test="${item.createdBy == userId}" >
							<span class="action_span"><a href="getUploadedImagesSA?eventId=${item.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png"></a></span>
							<span class="action_span"><a href="deleteSuperAdminEvent?id=${item.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/delete-icon.png"></a></span>
							</c:if>
							
							</td>
							</tr>
							</c:if>
							<c:if test="${item.eventType!='default'}">
						<tr >
					<input type="hidden" name="EId" value="${item.EId}"/>
							<td>${item.eventStart}</td>
							<td>${item.eventName}</td>
							<td>${item.sponsorName}</td>
							<td>${item.createrName}</td>
							<td class="action_td"><span class="action_span">
							<%-- <a href="viewSuperAdminEvent?id=${item.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></a></span> --%>
							<a href="eventReportDetailsSA?eventId=${item.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></a></span>
							
							<c:if test="${item.createdBy == userId}" >
							<span class="action_span"><a href="getUploadedImagesSA?eventId=${item.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png"></a></span>
							<span class="action_span"><a href="deleteSuperAdminEvent?id=${item.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/delete-icon.png"></a></span>
							</c:if>
							
							</td>
							</tr>
							</c:if>
					</c:forEach>
					
					<%-- <tr>
						<td>3rd Oct, 2016</td>
						<td>Brithday superadmin</td>
						<td>Rajat Thakur</td>
						<td>Anil Rawat Thakur</td>
						<td class="action_td"><span class="action_span"><img
								src="<%=request.getContextPath()%>/resources/images/images/eye.png"></span>
						</td>
					</tr>
					<tr>
										<td>3rd Oct, 2016</td>
										<td>Brithday</td>
										<td>Rajat Thakur</td>										
										<td>Anil Rawat Thakur</td>
										<td class="action_td">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></span>
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png"></span>
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/delete-icon.png"></span>
										</td>
									</tr>
									<tr>
										<td>3rd Oct, 2016</td>
										<td>Brithday</td>
										<td>Rajat Thakur</td>										
										<td>Anil Rawat Thakur</td>
										<td class="action_td">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></span>
										</td>
									</tr>
									<tr>
										<td>3rd Oct, 2016</td>
										<td>Brithday</td>
										<td>Rajat Thakur</td>										
										<td>Anil Rawat Thakur</td>
										<td class="action_td">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></span>
										</td>
									</tr>
									<tr>
										<td>3rd Oct, 2016</td>
										<td>Brithday</td>
										<td>Rajat Thakur</td>										
										<td>Me</td>
										<td class="action_td">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></span>
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png"></span>
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/delete-icon.png"></span>
										</td>
									</tr>
									<tr>
										<td>3rd Oct, 2016</td>
										<td>Brithday</td>
										<td>Rajat Thakur</td>										
										<td>Anil Rawat Thakur</td>
										<td class="action_td">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></span>
										</td>
									</tr>
									<tr>
										<td>3rd Oct, 2016</td>
										<td>Brithday</td>
										<td>Rajat Thakur</td>										
										<td>Anil Rawat Thakur</td>
										<td class="action_td">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></span>
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png"></span>
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/delete-icon.png"></span>
										</td>
									</tr>
									<tr>
										<td>3rd Oct, 2016</td>
										<td>Brithday</td>
										<td>Rajat Thakur</td>										
										<td>Me</td>
										<td class="action_td">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></span>
										</td>
									</tr>
									<tr>
										<td>3rd Oct, 2016</td>
										<td>Brithday</td>
										<td>Rajat Thakur</td>										
										<td>Anil Rawat Thakur</td>
										<td class="action_td">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></span>
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png"></span>
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/delete-icon.png"></span>
										</td>
									</tr> --%>
								</tbody>
							</table>
							
							<div class="pageing">
								<c:set var="pC" value="${pageCount}"/>
      								<c:set var="pId" value="${pageid}"/>
								<ul class="pagination">
									<li ><c:if test="${pId > 1}"><a href="getSubscribedEventList?pageid=${pageid-1}&total=${total}">&laquo;</a></c:if> </li>
									<c:forEach var="p" begin="1" end="${pageCount}"><li><a href="getSubscribedEventList?pageid=${p}&total=${total}">${p}</a></li></c:forEach>
									<li><c:if test="${pId < pC}"><a href="getSubscribedEventList?pageid=${pageid+1}&total=${total}">&raquo;</a></c:if> </li> 
								</ul>
							</div>
							
						</div>
					</div>
				</div>
				<div class="clearfix"></div>