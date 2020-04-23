			<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
		<script type="text/javascript">

<%-- $(function () {
	$('.table').hide();
    $("#eventId").change(function () {
        var selectedEventName = $(this).find("option:selected").text();
        var selectedEventId = $(this).val();
        //alert("selectedEventName: " + selectedEventName + " and selectedEventId: " + selectedEventId);
        if(selectedEventId=='all'){
        //alert('hello abhishek');	
        window.location="<%=request.getContextPath()%>/getContactEmail";
        }else{
       $("#selectedDiv").hide();
       $('.pageing').hide();
       callAjaxMethod(selectedEventName,selectedEventId);
        }
});
}); --%>
</script>
<script>
/* function callAjaxMethod(selectedEventName,selectedEventId){
$.ajax({
    type: "GET",
    url: "fetchContactList",
    data: { selectedEventName: selectedEventName, selectedEventId: selectedEventId} ,
    async:true,
    success: function(response) {
    	 var trHTML = '';
             for (var i = 0; i < response.length; i++) {
                 trHTML +=
                     '<tr><td>'
                     + response[i].userName
                     + '</td><td>'
                     + response[i].eventName
                     + '</td><td>'
                     + response[i].emailId
                     + '</td><td>'
                     + response[i].contactNo 
                     + '</td></tr>';
             }
             $('#example_wrapper').hide();
             $('.table').show();
         $('#selectedDivs').html(trHTML);
}
});
} */
</script>
<!--  <script type="text/javascript">
      $(document).ready(function() {
    	  $('#effect').delay(5000).fadeOut(400);
            $("#c").addClass("active_menu");
            
            var emailCsvLength=document.getElementById("emailCsvLength").value;
            var pgid=document.getElementById("a1").value;
            var ttl=document.getElementById("a2").value;
               $("#csv").click(function()
                    {
                if(emailCsvLength == 0 ){
                	alert("No Record Found");
                	return false
                }else{
                    window.location.href="dbToCsv?pageid="+pgid+"&total="+ttl; 
                }
               });
        });
</script> -->
<script>
$(document).ready(function() {
    $('#example').DataTable({
    	"searching": false,
    	"lengthMenu": [ [10,25,50, 100, 250, -1], [10,25,50, 100, 250, "All"] ],
    	 "language": {
             "lengthMenu": "Show _MENU_ ",
             "zeroRecords": "No Previous Contacts",
             "info": "Showing page _PAGE_ of _PAGES_",
             "infoEmpty": "No records available",
             "infoFiltered": "(filtered from _MAX_ total records)"
         }
    });
});
</script>
<script type="text/javascript">
      $(document).ready(function() {
            
            var emailCsvLength=document.getElementById("emailCsvLength").value;
          
               $("#csv").click(function()
                    {
                if(emailCsvLength == 0 ){
                	alert("No Records Found");
                	return false;
                }else{
                    window.location.href="exportsContact?eventId="+${eventId}; 
                }
               });
        });
</script>
<link href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<style>
/*.right-pannel{
height:auto !important;
}*/
/*.left-pannel{
  min-height:750px !important;
}*/
</style>
<div class="col-lg-2 col-md-3" style="width:20%;"></div>
			<div class="col-lg-10 col-xs-12 right-pannel right-height">
			<input type="hidden" value="${fn:length(emailImagesList)}" id="emailCsvLength"/>
					<a href="eventReportDetails?eventId=${eventId}"><button type="button" class="btn btn-default btn-sm pull-left" style="margin-right: 10px;margin-top:12px;"><span class="glyphicon glyphicon-chevron-left"></span>Back</button></a><h1 class="heading pull-left" style="margin-top: 15px;">Contact Emails</h1>
					
				<a href="#" class="pull-right"><button class="btn btn-green" id="csv" style="margin-top:12px">Export to CSV</button></a>
					<div class="col-md-2">
							<%-- <select id="eventId" name="EId" style="width:150px;padding:6px;">
							<option value="all" selected="selected">All</option>
							<c:forEach items="${eventList}" var="item" varStatus="loop">
							<option value="${item.EId}" >${item.eventName}</option>
							</c:forEach>
							</select> --%>
						</div>
					<div class= "heading pull-right" data-step="1" data-intro="These emails have received images from iAmuse for the events listed against the names">
					<!-- <a href="#" class="pull-right"><button class="btn btn-green" id="csv" >Export Contacts to CSV</button></a> -->
					</div>
					<div class="clearfix"></div>
					<div class="inner-content table-responsive" style="padding:25px;margin-bottom:80px; background-color: #fff; margin-top:20px; border: 1px solid #333;">    
						<div class="col-row">
							<table id="example" class="display" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Event Name</th>
										<th>Name</th>
										<th>Event Date</th>
										<th>Email Address</th>
										<th>Phone Number</th>
										<th>Subscription</th>
									</tr>
								</thead>
								<!-- <tbody id="selectedDivs"></tbody> -->
								<tbody id="selectedDiv">
									<c:if test="{fn:length(emailImagesList) lt 0}">
								<p>No Data Found</p>
								</c:if>
								<c:forEach items="${emailImagesList }" var="el" varStatus="">
									<tr>
										<td>${el.eventName }</td>
										<td>${el.userName }</td>
										<td>${el.eventDate}</td>
										<td>${el.emailId }</td>
										<td>${el.contactNo }</td>
										<td>${el.subscribed}<%-- <c:if test="${el.newsletterOptln==1}" >Yes</c:if><c:if test="${el.newsletterOptln==0}" >NO</c:if> --%></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- <table class="table table-striped">
								<thead>
									<tr>
										<th>Name</th>
										<th>Event Name</th>
										<th>Email Id</th>
										<th>Contact Number</th>
									</tr>
								</thead>
								<tbody id="selectedDivs"></tbody>
							</table> -->
						</div>
					</div>
				</div>
				<div class="clearfix"></div>