<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/tld/customTagLibrary" prefix="cg" %>

<div class="right-pannel">
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left" style="padding-top:15px;">Booth Admin</h1>
					<div class="clearfix"></div>
					<div class="inner-content">
						<div class="col-row">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Name</th>
										<th>Location</th>
										<th>Subscription</th>
										<th>Phone No</th>
										<th>E-mail Id</th>
										<th>Active/DeActive</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${boothAdminLoginList}" var="item">
								<tr>
								<td>${item.username}</td>
								<td>${item.location}</td>
								<td>
								<c:forEach items="${subList}" var="sl">
								<c:if test="${item.subId == sl.subId }">
								${sl.subName}
								</c:if>
								</c:forEach>
								</td>
								<td>${item.contactNumber}</td>
								<td>${item.emailId}</td>
								<td>
								<c:forEach items="${subList}" var="sl">
								<c:if test="${item.subId == sl.subId }">
								<%--  <c:if test="${cg:getDayCount(item.createdDate,currentDate) ne slM.subValidaityDayPeriod}"> --%>
								 <c:if test="${sl.status =='false'}">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/deactivate.png" width="20px"></span>
											</c:if>
											<%-- </c:if> --%>
											 <%-- <c:if test="${cg:getDayCount(item.createdDate,currentDate) eq slM.subValidaityDayPeriod}"> --%>
											<c:if test="${sl.status =='true'}">
											<span class="action_span"><img src="<%=request.getContextPath()%>/resources/images/images/activate.png" width="20px"></span>
											</c:if> 
											<%-- </c:if> --%>
											</c:if>
											</c:forEach>
									
											</td>
								<td><c:forEach items="${subList}" var="slM">
								<c:if test="${item.subId == slM.subId }">
								 <%-- <c:if test="${slM.status =='false'}"> --%>
								 <c:if test="${cg:getDayCount(item.createdDate,currentDate) ne slM.subValidaityDayPeriod}">
								 <a href="upgradeSubscriptionMail?emailId=${item.emailId}"><button class="btn btn-green">Sent</button></a> 
								 </c:if>
								 </c:if>
								<%--  </c:if> --%>
								 </c:forEach>
								 </td>
								</tr>
								
								</c:forEach> 
								</tbody>
							</table>
							
							<div class="pageing">
								<ul class="pagination">
									<li class="disabled"><a href="#">&laquo;</a></li>
									<li class="active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">&raquo;</a></li>
								</ul>
							</div>
							
						</div>
					</div>
				</div>

				<div class="clearfix"></div>