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


       
		<h3 align="left" >DeviceIP List</h3>
	<br/>
     	
        <c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					
					 <c:if test="${not empty deviceIPList}">
                   
                        <form:form id="deleteFiles" action="deleteSelected" method="post" onsubmit="return confirm_update();">
                            <input type="hidden" name="page" value="crashlogsFiles" />       
                        <table id="example" class="table table-bordered table-striped dashboardtable" cellspacing="0" >
                         <thead class="thead">
                          <tr>
                          <th class="head" style=""><span>S.No.</span></th>
                          	<th class="head"  ><span>Device Type</span></th>
                          	<th class="head"><span>DeviceIP</span></th>
                          
                            <th class="head"  ><span>Time Span</span></th>
                            
                           
                            
                           
                          </tr>
                          </thead> 
                          	<tbody>
                          	
                       	<c:forEach items="${deviceIPList}" var="al">
                       	<tr>
                      		<td >${al.serialNumber}</td>
							
							<td >${al.deviceType}</td>
							<td >${al.deviceIP}</td>
								<td >${al.uploadTime}</td>
							</tr>
						</c:forEach>
							</tbody>
                        </table>
                        </form:form>
                        </c:if>
				<c:if test="${empty deviceIPList}">
				<div style="text-align: center; margin-top: 20px;">No Record Found</div>
				</c:if>
				
				<div class="clearfix"></div>
    
				   
  