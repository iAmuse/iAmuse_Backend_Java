<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%   
	 String ils = (String)request.getAttribute("imageListSize"); 
	  String ilus = (String)request.getAttribute("imageListUpdatedSize");
     
   
     
%>

<script src="<%=request.getContextPath()%>/resources/js/applicantDashboard.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">

var tid = setInterval(mycode, 15000);
var tid1 = setInterval(mycode1, 5000);

function mycode() {
      $.ajax({
            url : 'autoRefresh',
            success : function(data) {
                $('#result').html(data);
            }
        });
        }
      
         
    function mycode1() { 
                  
          dwrService.getStatus(1,function(data) { 
    
     if(data==1)
   {
   var r = confirm("Image recieved press ok to refresh");
   if (r == true) {
  
   window.location="<%=request.getContextPath()%>/afterRefresh"
   
     }
   }  
  });
        
 
    
}

</script>

<script type="text/javascript">

$(document).ready(function() {

    $('#example').dataTable( {
         "scrollY":        "470px",
        "scrollCollapse": true,
        "paging":         false
    } );
} );

  
        </script>	
<script type="text/javascript">
function exportToForm(id) {
     
     var r = confirm("Press Ok to delete!");
    if (r == true) {
     
        window.location="<%=request.getContextPath()%>/removeImage?id="+id
        
    } 
 }
 
 
 
 
 
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
<script type="text/javascript">
var alertWindow = Ext.Msg.alert('Alert', 'An alert!');

(function() {
   alertWindow.hide();
}).defer(10000);
</script>

        <div class="col-md-6" id="refresh">
		<h3 align="center" >Images List</h3>
		
     	
        <c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
					<div class="form-row">
                        <a style="margin-top:15px;" href="getImagePush">Get Image</a>
     					<a href="iamusedashboard" style="float:right;"><img src="<%=request.getContextPath()%>/resources/images/icon_refresh.png">Refresh</a>
                        <div class="clearfix"></div> 
                        </div>
					 <c:if test="${not empty imagesList}">
                   
                        
                          <form:form id="deleteFiles" action="deleteSelected" method="post" onsubmit="return confirm_update();">
                            <input type="submit" style="" class="btn-success btn" value="Delete Selected"/>  
                            <input type="hidden" name="page" value="dashboardImage" />       
                       
                         <table id="example" class="table table-bordered table-striped" cellspacing="0">
                         <thead class="thead">
                          <tr>
                           <th class="head"><input type="checkbox" id="selecctall"/>Select All</th>
                          <th class="head"  ><span>Serial Number</span></th>
                          	<th class="head"  ><span>Images List</span></th>
                            <th class="head"  ><span>Upload Time Span</span></th>
                            <th class="head"  ><span>Update Time Span</span></th>
                            <th class="head"  ><span>RGB Value</span></th>
                            <th></th>
                          </tr>
                          </thead> 
                          	<tbody>
                       	<c:forEach items="${imagesList}" var="al">
                       	
                      
							<tr>
							 <td style="width:3%"><input type="checkbox" class="checkbox1" id="checkbox" name="checkbox1" value="${al.imageId}"/></td>
							<td>${al.serialNumber}</td>
							<td ><a href="openimagepage?id=${al.imageId}">${al.imagename}</a></td>
								<td>${al.uploadTime}</td>
								<td>${al.updateTime}</td>
								<td><b>MinRGB:&nbsp;&nbsp;</b>${al.rgbValue}<br/><br/><b>MaxRGB:&nbsp;&nbsp;</b>${al.rgbValueMax}</td>
								<td> <img onclick="javascript:exportToForm('${al.imageId}')" src="<%=request.getContextPath()%>/resources/images/delete.png" />
</td>
							</tr>
					
							
						</c:forEach>
							</tbody>
                        </table>
                        </form:form>
                        </c:if>
				<c:if test="${empty imagesList}">
				<div style="text-align: center; margin-top: 20px;">No Record Found</div>
				</c:if>
      
      <div class="clearfix"></div>
      </div>
      
    
    
    