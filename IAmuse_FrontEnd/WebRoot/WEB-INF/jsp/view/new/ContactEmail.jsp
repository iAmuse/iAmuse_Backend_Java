			<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<script>
$(document).ready(function() {
    $('#example').DataTable({
    	"searching": false,
    	"lengthMenu": [ [50, 100, 250, -1], [50, 100, 250, "All"] ],
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
.right-pannel{
height:auto !important;
}
</style>

			<div class="right-pannel">
			<input type="hidden" value="${fn:length(emailImagesList)}" id="emailCsvLength"/>
					<a href="eventReportDetails?eventId=${eventId}"><button type="button" class="btn btn-default btn-sm pull-left" style="margin-right: 10px;"><span class="glyphicon glyphicon-chevron-left"></span>Back</button></a><h1 class="heading pull-left">Contact Emails</h1>
					
				<a href="#" class="pull-right"><button class="btn btn-green" id="csv" >Export to CSV</button></a>
					<div class="col-md-2">
						</div>
					<div class= "heading pull-right" data-step="1" data-intro="These emails have received images from iAmuse for the events listed against the names">
					<!-- <a href="#" class="pull-right"><button class="btn btn-green" id="csv" >Export Contacts to CSV</button></a> -->
					</div>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:25px;">
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
										<td>${el.subscribed}</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>