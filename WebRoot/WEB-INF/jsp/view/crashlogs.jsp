    <%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/pop-up-style.css" />
<script src="<%=request.getContextPath()%>/resources/js/applicantDashboard.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {

    $('#example').dataTable( {
        "scrollY":        "470px",
        "scrollCollapse": true,
        "paging":         false
       
    } );
    
} );
  
        </script>	
<script>
	$(document).ready(function() {
        $('.downloadLink').click(function() {
                
                $(this).parent().siblings('td').css('background-color', '#EBA521');
                $(this).parent().css('background-color', '#EBA521');
                return true;
        });
});


$(document).ready(function() {
    $('#selecctall').click(function(event) {  //on click 
        if(this.checked) { // check select status
            $('.checkbox1').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"               
            });
        }else{
            $('.checkbox1').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
            });         
        }
    });
    
});

function confirm_update() {
    var arrCheckboxes = document.getElementById('deleteFiles').elements["checkbox1"];
    var checkCount = 0;
    for (var i = 0; i < arrCheckboxes.length; i++) {
        checkCount += (arrCheckboxes[i].checked) ? 1 : 0;
    }

    if (checkCount > 0){
        return confirm("Are you sure you want to proceed deleting the selected   files?");
    } else {
        alert("You do not have any selected files to delete.");
        return false;
    }
}

</script>

        <div class="">
		<h3 align="left" >CrashLogs List</h3>
	<br/>
     	
        <c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					
					 <c:if test="${not empty crashLogsList}">
                   
                        <form:form id="deleteFiles" action="deleteSelected" method="post" onsubmit="return confirm_update();">
                            <input type="submit"  class="btn-success btn" value="Delete Selected"/>  
                            <input type="hidden" name="page" value="crashlogsFiles" />       
                        <table id="example" class="table table-bordered table-striped dashboardtable" cellspacing="0" >
                         <thead class="thead">
                          <tr>
                          <th class="head"><input type="checkbox" id="selecctall"/>Select</th>
                          <th class="head" style=""><span>S.No.</span></th>
                          	<th class="head"  ><span>Logs List</span></th>
                          	<th class="head"><span>Download</span></th>
                          
                            <th class="head"  ><span>Uploaded Time Span</span></th>
                            
                           
                            
                           
                          </tr>
                          </thead> 
                          	<tbody>
                       	<c:forEach items="${crashLogsList}" var="al">
                       	
                      <c:if test="${al.readStatus==true}" >
							<tr id="${al.logId}" >
							 <td style="width:3%;background-color:#EBA521;"><input type="checkbox" class="checkbox1" id="checkbox" name="checkbox1" value="${al.logId}"/></td>
							
							<td style="background-color:#EBA521" >${al.serialNumber}</td>
							<td  style="background-color:#EBA521"><c:set var="dateParts" value="${fn:split(al.fileName, ',')}" />
							<c:forEach items="${dateParts}" var="link" >CrashLog${link}&nbsp;<br/></c:forEach></td>
							
							<td style="background-color:#EBA521"><a  class="downloadLink" href="<%=request.getContextPath()%>/crashLogsDownloads/${al.logId}" >Download</a></td>
								<td style="background-color:#EBA521">${al.uploadTime}</td>
							</tr>
					</c:if>
							 <c:if test="${al.readStatus==false}" >
							
							<tr id="${al.logId}" >
							 <td style="width:7%"><input type="checkbox" class="checkbox1" id="checkbox" name="checkbox1" value="${al.logId}"/></td>
							
							<td style="width:10%" >${al.serialNumber}</td>
							<td ><c:set var="dateParts" value="${fn:split(al.fileName, ',')}" />
							<c:forEach items="${dateParts}" var="link" >CrashLog${link}&nbsp;<br/></c:forEach></td>
							
							<td ><a class="downloadLink" href="<%=request.getContextPath()%>/crashLogsDownloads/${al.logId}" >Download</a></td>
								<td >${al.uploadTime}</td>
							</tr>
					</c:if>
						</c:forEach>
							</tbody>
                        </table>
                        </form:form>
                        </c:if>
				<c:if test="${empty crashLogsList}">
				<div style="text-align: center; margin-top: 20px;">No Record Found</div>
				</c:if>
				
				<div class="clearfix"></div>
      </div>
				   
  