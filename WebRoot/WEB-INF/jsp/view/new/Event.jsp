		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>	
<style>
.selected {background: #ddd !important;}
.left-pannel{
          min-height: 750px !important;
      }
</style>
 <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>-->
<script type="text/javascript">
      $(document).ready(function() {
    	  
    		 var pageid=${pageid};
 			 $("#${pageid}").addClass("active"); 
 			
    	  $('#effect').delay(9000).fadeOut(400);
            $("#b").addClass("active_menu");
        });
      $('#example').dataTable({
    	    "oLanguage": {
    	        "sEmptyTable":"My Custom Message On Empty Table"
    	    }
    	});
      
      
      
      
      function validateSubscription() {
      //if((parseInt('${boothAdminLogin2.subId}')==1) && ('${boothAdminLogin1.subEndDate}' > new Date())){
      if(parseInt('${boothAdminLogin2.subId}')==1){
		  //$('#myModal').modal('show');
		  //return false;
		  return true;
	  } 
      }
      
     /*  $('button selector').click(function(){
    	   window.location.href='Subscription.jsp';
    	});    */  
</script>		
		<div class="col-lg-2 col-md-3" style="width:20%;"></div>
		<div class="col-lg-10 col-sm-9 col-xs-12 col-md-9 right-pannel right-height">
		<div class="col-lg-4 col-sm-8 col-xs-6 col-md-6" style="padding-left: 0px;">
		
					
					 <c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<h1 class="headingtitle1 pull-left createEvent-lbl">Your Events</h1>
					<!--  <h3 class="sub-heading pull-left">Your Booth's <a href="zoomPage" style="color:cornflowerblue">Booth Zoom Profile</a> (<c:if test="${fovbyuser==1.00}">Zoomed In</c:if><c:if test="${fovbyuser==0.50 || fovbyuser==0.5}">Zoomed Out</c:if><c:if test="${fovbyuser==0.75}">Medium Zoom</c:if>).</h3>-->
				</div>
				<div class="col-lg-8 col-xs-6">				
					<!-- <p style="subtext">Only Events matching current Booth Zoom Profile are shown.</p> -->
					<div class= "headingbutton1 pull-rightevent1" data-step="1" data-intro="You can begin by clicking on the button and start creating an event">
					 
			 		 <a href="create-event.html" class="pull-rightevent" data-toggle="tooltip" title="Click to create a new Event" onclick="return validateSubscription();"><button class="btn btn-green">Create Event</button></a> 
	 
	</div></div>
		 <!--  <button class="btn btn-green" data-toggle="modal" data-target="#myModal">Create Event</button> -->
		<!--   <a href="create-event.html" class="pull-right" data-toggle="tooltip" title="Click to create a new Event" onclick="return validateSubscription();">
		</a>-->
		 <!-- <button type="button" class="btn btn-green" data-toggle="tooltip" data-toggle="modal" data-target="#myModal1" title="Click to create a new Event">Create Event</button></a> --> 
			 <!-- <button type="button" class="btn btn-green" data-toggle="modal" data-target="#myModal">Open Modal</button></a> -->
  
 
   
	<!--  ----------------------------------------------- -->	
					
					
					
					
					
					<div class="clearfix"></div>									
					<div class="clearfix"></div>
					<div class="inner-content">
						<div class="col-lg-12 table-responsive" style="background:#fff; border: 1px solid; padding-left:0px; padding-right:0px;">
							<table class="table table-hover" id="example">
								<thead style="background-color: #05a42e; color: white;">
									<tr style="padding-top:10px; padding-bottom:10px;">
										<th>Event</th>
										<th>Host Name</th>
										<th>Event Host Email</th>
										<th>Total Guest Sessions</th>
										<!--<th>Booth Zoom Profile</th>  -->
										<th>Date</th>
										<th width="160px">Action</th>
									</tr>
								</thead>
								<tbody style="background-color: #fff; border: 1px solid #c3c3c3;">	
								<c:if test="${empty eventList}">
								<tr  class="admin-row" style="border: 1px solid #ddd;">  <td></td><td><td></td></td><td>Event Data Not Found</td><td></td><td></td><td></td></tr>
								</c:if>
								<c:forEach items="${eventList }" var="el" varStatus="loop">
									<tr  class="admin-row" style="border: 1px solid #ddd;">   
										<td>${el.eventName }</td>
										<td>${el.sponsorName}</td>
										<td>${el.eventHostEmail}</td>
										<td>${el.totalGuestSession}</td>
										<!--<td><c:if test="${el.zoomScale==1.00}">Zoomed In</c:if><c:if test="${el.zoomScale==0.50 || el.zoomScale==0.5}">Zoomed Out</c:if><c:if test="${el.zoomScale==0.75}">Medium Zoom</c:if></td>-->
										<td>${el.eventStart}</td>
										<%-- <c:if test="${el.zoomScale eq  fovbyuser}"> --%>
										<td class="action_td">
											<span class="action_span"><a href="eventReportDetails?eventId=${el.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/eye.png"></a></span>
											<span class="action_span"><a href="getUploadedImages?eventId=${el.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/edit-icon.png"></a></span>
											<c:if test="${el.EId!= 82}"><span class="action_span"><a href="deleteEvent?eventId=${el.EId}"><img src="<%=request.getContextPath()%>/resources/images/images/delete-icon.png"></a></span></c:if>
										</td>
										<%--<c:if test="${el.zoomScale !=  fovbyuser}">
										<td >
										</td>
										</c:if> --%>
									<%-- 	</c:if> --%>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
							
						</div>
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
				<div class="clearfix"></div>
				<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content" style="height: 185px;">
        <div class="modal-header" style="border-bottom:none !important">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <!--<h4 class="modal-title">Subscription</h4>-->
        </div>
        <div class="modal-body">
          <p>Pro feature - Subscribe to unlock</p>
        </div>
        <div class="modal-footer"style="border-top:none !important">
        <a href="getSubscription" class="btn btn-default" data-toggle="tooltip" title="This indicates your current subscription and the option to upgrade, if any is available here. You will be directed to the Payment Gateway to upgrade your plan" style="padding-left:15px;margin-top: -50px;">ok</a>
         <!--  <button type="button" class="btn btn-default" onclick="javascript:window.location.href='Subscription.jsp';" data-dismiss="modal" >Ok</button> -->
        </div>
      </div>
      
    </div>
  </div>