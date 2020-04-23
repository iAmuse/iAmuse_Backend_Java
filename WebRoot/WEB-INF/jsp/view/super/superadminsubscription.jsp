<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>	
	<script >
      $(document).ready(function() {
            $("#b").addClass("active_menu");
        });
	</script>
<style>
	.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td{line-height:25px !important;}
</style>
<div class="right-pannel col-lg-10 col-xs-12">
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left" style="padding-top:15px;">Subscription</h1>
					<a href="create-superadminSubscription.html" class="pull-right"><button class="btn btn-green">Add Subscription</button></a>
					<div class="clearfix"></div>
					<div class="inner-content">
						<div class="col-row">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Subscription Plan</th>
										<th>Price</th>
										<th width="180px">Action</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${subscriptionPlan}" var="item">
								<tr>
								<input type="hidden" name="subId" value="${item.subId}"/>
								<td>${item.subName}</td>
								<td>$ ${item.subPrice}</td>
								<td class="action_td">
											<span class="action_span"><a href="viewSubscription?id=${item.subId}"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></a></span>
											<span class="action_span"><a href="editSubscription?id=${item.subId}"><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png"></a></span>
											<span class="action_span"><a href="deleteSubscription?id=${item.subId}"><img src="<%=request.getContextPath()%>/resources/images/images/delete-icon.png"></a></span>
											<c:if test="${item.status =='false'}">
											<span class="action_span"><a href="activeSubscription?id=${item.subId}"><img src="<%=request.getContextPath()%>/resources/images/images/deactivate.png" width="20px"></a></span>
											</c:if>
											<c:if test="${item.status =='true'}">
											<span class="action_span"><a href="deactiveSubscription?id=${item.subId}"><img src="<%=request.getContextPath()%>/resources/images/images/activate.png" width="20px"></a></span>
											</c:if>
								</td>
								
								</tr>
								</c:forEach>
								</tbody>
							</table>
							
							<div class="pageing">
								<c:set var="pC" value="${pageCount}"/>
      								<c:set var="pId" value="${pageid}"/>
								<ul class="pagination">
									<li ><c:if test="${pId > 1}"><a href="getSuperAdminSubscription?pageid=${pageid-1}&total=${total}">&laquo;</a></c:if> </li>
									<c:forEach var="p" begin="1" end="${pageCount}"><li><a href="getSuperAdminSubscription?pageid=${p}&total=${total}">${p}</a></li></c:forEach>
									<li><c:if test="${pId < pC}"><a href="getSuperAdminSubscription?pageid=${pageid+1}&total=${total}">&raquo;</a></c:if> </li> 
								</ul>
							</div>
							
						</div>
					</div>
				</div>
<div class="clearfix"></div>