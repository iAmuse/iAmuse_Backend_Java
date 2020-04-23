<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/tld/customTagLibrary" prefix="cg" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>	
<script >
$(document).ready(function() {
$("#c").addClass("active_menu");
//$('select').on('change', function() {
	});
	
function updateSubscriptionFormAdmin(data)
{

	$.ajax({
	   url:"<%=request.getContextPath()%>/updateSubscriptionFormAdmin",
	   type : "POST",
	   contentType : "application/json",
	   data : data,
	   dataType : 'json'
	   
	}).done(function(){
		window.location="/iamuse/getBoothAdminList";
		});
	
	

	/* var xhr = new XMLHttpRequest();
  	 xhr.withCredentials = true;
  	 xhr.addEventListener("readystatechange", function () {
  	   if (this.readyState === 4) {
  		//alert(this.responseText);
  		 document.location.reload(true);
  	   }
  	 });

  	 xhr.open("POST", "http://iamuses.eastus.cloudapp.azure.com:8080/iamuseserver_internal/v1/iamuse/updateSubscriptionFormAdmin");
  	 xhr.setRequestHeader("accept", "application/json");
  	 xhr.setRequestHeader("authorization", "DrEgBqmYbTXJqi2/a/H9O9YLYcRNjNTNn89BKpui1Y8");
  	 xhr.setRequestHeader("content-type", "application/json");
   	 xhr.send(data); */
} 

function setplanupgrad(userId,subId){

	subId=subId.value;
     //   var selectedValue = item;
        var data = JSON.stringify({
      	  "userid":userId,
      	  "subId":subId
      	 });
        updateSubscriptionFormAdmin(data);
}

</script>
<style>
.left-pannel{
   min-height:795px !important;
}
</style>
<div class="col-lg-2 col-md-3" style="width:20%;"></div>
<div class="right-pannel col-lg-10 col-xs-12">
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="heading pull-left" style="padding-top:15px;">Booth Admin</h1>
					<div class="clearfix"></div>
					<div class="inner-content table-responsive" style="border: 1px solid #000;background:#fff;margin-left: 11px;">
						<div class="col-row">
							<table class="table table-hover">
								<thead style="background-color: #05a42e; color: white;">
									<tr style="padding-top:10px; padding-bottom:10px;">
										<th>Name</th>
										<th>Location</th>
										<th>Subscription</th>
										<th>Phone No</th>
										<th>E-mail Id</th>
										<th>Action</th>
										<th>Manual upgrade</th>
									</tr>
								</thead>
								<tbody style="background-color: #fff; border: 1px solid #c3c3c3; "> 
								<c:forEach items="${boothAdminLoginList}" var="item">
								<tr style="border: 1px solid #ddd;">
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
								<c:if test="${item.subId == 1 || item.createdDate > item.subEndDateFormat}">
								 <%-- <c:if test="${currentDate > item.subUpdatedDateFormat}"> --%>
								 <a href="upgradeSubscriptionMail?emailId=${item.emailId}"><button class="btn btn-green">Sent</button></a> 
								 <%-- </c:if> --%>
								 </c:if>
								 </td>
								 <td>
								 <select class="form-control" id="selectPlan" onchange="setplanupgrad('${item.userId}',this);" name="selectPlan" style="width:80%; float:right">

								    <option value="0" selected="selected"> --Plan-- </option>
								   <c:choose>    
								   <c:when test="${item.subId == 1}">
								  		<option value="2">Single Event</option>
								        <option value="3">professional</option>
								  </c:when>    
								  <c:when test="${item.subId == 2}">
								  		<!-- <option value="1">Test Drive</option> -->
								        <option value="3">professional</option>
								  </c:when> 
								   <c:otherwise>
								      <option value="1">Free Trial</option>
								      <option value="2">Single Event</option>
								   </c:otherwise>
								   </c:choose>
								  </select>
								 </td>
								</tr>
								</c:forEach> 
								</tbody>
							</table>
					</div>
				</div>
							<div class="pageing paginba">
								<c:set var="pC" value="${pageCount}"/>
      								<c:set var="pId" value="${pageid}"/>
								<ul class="pagination">
									<li ><c:if test="${pId > 1}"><a href="getBoothAdminList?pageid=${pageid-1}&total=${total}">&laquo;</a></c:if> </li>
									<c:forEach var="p" begin="1" end="${pageCount}"><li><a href="getBoothAdminList?pageid=${p}&total=${total}">${p}</a></li></c:forEach>
									<li><c:if test="${pId < pC}"><a href="getBoothAdminList?pageid=${pageid+1}&total=${total}">&raquo;</a></c:if> </li> 
								</ul>
							</div>
							
						</div>   
				<div class="clearfix"></div>