<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/tld/customTagLibrary" prefix="cg" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>	
<script src="<%=request.getContextPath()%>/resources/js/script/boothadminlist.js"></script>	
<script >
$(document).ready(function() {
$("#c").addClass("active_menu");

$('.subscriptionId').each(function() {
    var planCode = $(this).attr('subscriptionId');
    var subUserId = $(this).attr('id');
 if(planCode=='test5'){
	$("#"+subUserId+" option[value='test5']").each(function() {
	    $(this).remove();
	});
}else if(planCode=='iAmuseCustom'){
	$("#"+subUserId+" option[value='test5']").each(function() {
	    $(this).remove();
	});
	$("#"+subUserId+" option[value='iAmuseCustom']").each(function() {
	    $(this).remove();
	});
}else if(planCode=='iAmuseProfessional'){
	$("#"+subUserId+" option[value='test5']").each(function() {
	    $(this).remove();
	});
	$("#"+subUserId+" option[value='iAmuseCustom']").each(function() {
	    $(this).remove();
	});
	$("#"+subUserId+" option[value='iAmuseProfessional']").each(function() {
	    $(this).remove();
	});
} 
});
});
</script>
<c:set var = "now"  value = "<%=new java.util.Date()%>" />
<fmt:formatDate var="todaydate" pattern='yyyy-MM-dd' value="${now}" />
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
										<th>Subscription End Date</th>
										<th>Subscription</th>
										<th>Phone No</th>
										<th>E-mail Id</th>
										<th>Action</th>
										<th>Upgrade Manual</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${boothAdminLoginList}" var="item">
								<tr>
								<td>${item.username}</td>
								<td>${item.subEndDateFormat}</td>
								<td>${item.planCode}</td>
								<td>${item.contactNumber}</td>
								<td>${item.emailId}</td>
								 <td>
								<c:if test="${todaydate gt item.subEndDateFormat }">
								 <%-- <c:if test="${currentDate > item.subUpdatedDateFormat}"> --%>
								 <a href="upgradeSubscriptionMail?emailId=${item.emailId}"><button class="btn btn-green">Send</button></a> 
								 <%-- </c:if> --%>
								 </c:if>
								 </td>
								 <td>
								 	<select class="subscriptionId" subscriptionId="${item.planCode}" id="subscriptionId${item.userId}" onchange="getSubscriptionPlan('${item.userId}')"><option value="0">--Plans--</option></select>
								 </td>
								</tr>
								</c:forEach> 
								</tbody>
							</table>
							
							<div class="pageing">
								<c:set var="pC" value="${pageCount}"/>
      								<c:set var="pId" value="${pageid}"/>
								<ul class="pagination">
									<li ><c:if test="${pId > 1}"><a href="getBoothAdminList?pageid=${pageid-1}&total=${total}">&laquo;</a></c:if> </li>
									<c:forEach var="p" begin="1" end="${pageCount}"><li><a href="getBoothAdminList?pageid=${p}&total=${total}">${p}</a></li></c:forEach>
									<li><c:if test="${pId < pC}"><a href="getBoothAdminList?pageid=${pageid+1}&total=${total}">&raquo;</a></c:if> </li> 
								</ul>
							</div>
							
						</div>
					</div>
				</div>

				<div class="clearfix"></div>