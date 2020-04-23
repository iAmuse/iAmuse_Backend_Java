 
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=request.getContextPath()%>/resources/js/applicantDashboard.js" type="text/javascript"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.3.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/pop-up-style.css" />
<%   
	 String id = (String)request.getAttribute("id"); 
     System.out.println("jcdsahdsjkhdhhghcvdsauy vcdsajhgdsac"+id);
    request.setAttribute("id",id); 
%>
<%   
	 String ils = (String)request.getAttribute("imageListSize"); 
	  String ilus = (String)request.getAttribute("imageListUpdatedSize");
%>
<style>
	.rgb-textbox .form-row input[type="text"] {width:310px;}
	.rgb-textbox .form-row .label-text{
		  float: left;
    line-height: 35px;
    margin-right: 10px;
    width: 30px;
    text-align: right;
	}
	#preview{height:100px;}
</style>
<script type="text/javascript">
      $(document).ready(function() {
    	  $('#effect').delay(9000).fadeOut(400);
            $("#e").addClass("active_menu");
        });
</script>
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
<!-- 
<script type="text/javascript">

$(document).ready(function() {

    $('#example').dataTable( {
         "scrollY":        "470px",
        "scrollCollapse": true,
        "paging":         false
    } );
} );

  
        </script>	 -->
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


       

<script src="<%=request.getContextPath()%>/resources/js/pop-up-script.js"></script>
<script>
function resetDefault() {
     
     var r = confirm("Are you Sure Want to Reset Default?");
    if (r == true) {
     
        window.location="<%=request.getContextPath()%>/resetSystemDefaultRGBValue"
        
    } 
 }
 function updateDefault() {
     
     var r = confirm("Are you Sure Want to Update");
    if (r == true) {
     
        window.location="<%=request.getContextPath()%>/updateDefaultRGB"
        
    } 
 }
function validnum(a) { 
    if(a < 0 || a > 255) 
        return false;
     
    else 
        return true;
} 
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	// Added to allow decimal, period, or delete
	if (charCode == 110 || charCode == 190 || charCode == 46) 
		return false;
	
	if (charCode > 31 && (charCode < 48 || charCode > 57)) 
		return false;
	
	return true;
} 
function finish() {
    
    var r = confirm("Your event setup is complete. This event is now available to download on your devices.");
   if (r == true) {
    
       window.location="<%=request.getContextPath()%>/finishConfiguration"
       
   } 
}
</script>
<div class="right-pannel col-xs-12 col-lg-10">
					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>


						<a href="getImagePushAdmin"><button class="btn btn-green pull-right" style="margin-left:10px;">Take Test Picture</button></a>
						<a href="rgbSetup"><button class="btn btn-green pull-right">Refresh</button></a>
<div class="img-container">
<h1 class="heading pull-left">Update Transparent Color</h1><br/><br/>
<div class="col-md-3">
	<div>Preview:</div>
	<div id="preview"></div>
</div>
<div class="col-md-8 rgb-textbox"> 
<form:form action="updateDefaultRGB" modelAttribute="imageFormVO"> 
     <c:forEach items="${imageDetails}" var="dl">
     
    <div id="hex" class="form-row ">
    	<span class="label-text">HEX:</span> 
    	<input type="text" name="hexValue" id="hexValue" class="input-sm pull-left" value="${dl.hexValue}" readonly="readonly"/>
		<div class="clearfix"></div>
	</div>	
	<div id="rgb" class="form-row"> 
		<input type="hidden" name="rgbValue" id="rgbValue" class="input-sm"  value="${dl.rgbValue}" readonly="readonly"/>
		<div class="clearfix"></div>
	</div>
	<div class="form-row"> 
		<input type="hidden" id="rgbaVal" name="rgbaValue" class="input-sm" style="margin-top:15px;" value="${dl.rgbaValue}" readonly="readonly" />
		<div class="clearfix"></div>
	</div>	
	<div class="form-row">
		<span class="label-text">R:</span>
		<input type="text" name="r" id="rValue" class="input-sm pull-left"  value="${dl.r}" onkeypress="return isNumberKey(event)" onkeyup='if(!validnum(this.value)) this.value="";' />
		<div class="clearfix"></div>
	</div>
	<div class="form-row">
		<span class="label-text">G:</span> 
		<input type="text" name="g" id="gValue" class="input-sm pull-left"  value="${dl.g}" onkeypress="return isNumberKey(event)" onkeyup='if(!validnum(this.value)) this.value="";'/>
		<div class="clearfix"></div>
	</div>
	
	<div class="form-row">
		<span class="label-text">B:</span> 
		<input type="text" name="b" id="bValue" class="input-sm pull-left"  value="${dl.b}" onkeypress="return isNumberKey(event)" onkeyup='if(!validnum(this.value)) this.value="";' />
		<div class="clearfix"></div>
	</div>
<script>
	var pixelcolor1="rgba("+"${dl.rgbaValue}"+")";
    $('#preview').css('backgroundColor',pixelcolor1);
	</script>
	</c:forEach>
    <input type="submit" class="btn-success btn pull-left" style="margin-left:43px; margin-top:20px; text-decoration:none;width:155px;" value="Update">
	<a onclick="resetDefault()" class="btn-success btn "  style="margin-left:10px; margin-top:20px; text-decoration:none;width:145px;color:#fff;padding:10px;">Reset Default</a>	
   
      </form:form>
</div>
<div class="clearfix"></div>
</div>
<!-- <button class="btn btn-green pull-right" style="margin-left:10px;background-color: #ff5722 !important;" onclick="finish();">Finish</button> -->
<div class="clearfix"></div>
<div class="image-list">

						<c:if test="${not empty imagesList}">
                   
                        
                          <form:form id="deleteFiles" action="deleteSelected" method="post"  onsubmit="return confirm_update();">
                          
                          <!--   <input type="submit"  class="btn-success btn" value="Delete Selected" style="width:auto;float:right;margin-bottom:10px;"/>   -->
                            <input type="hidden" name="page" value="dashboardImage" />       
                       
                         <table id="example" class="table table-bordered table-striped" cellspacing="0">
                         <thead class="thead">
                          <tr>
                         <!--   <th class="head"><input type="checkbox" id="selecctall"/>Select All</th> -->
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
							<%--  <td style="width:90px;position:relative;"><input type="checkbox" class="checkbox1" id="checkbox" name="checkbox1" value="${al.imageId}"/></td> --%>
							<td>${al.serialNumber}</td>
							<td ><a href="openimagepage?id=${al.imageId}">${al.imagename}</a></td>
								<td>${al.uploadTime}</td>
								<td>${al.updateTime}</td>
								<td><b>MinRGB:&nbsp;&nbsp;</b>${al.rgbValue}<%-- <br/><br/><b>MaxRGB:&nbsp;&nbsp;</b>${al.rgbValueMax} --%></td>
								<td> <img onclick="javascript:exportToForm('${al.imageId}')" src="<%=request.getContextPath()%>/resources/images/delete.png" />
</td>
							</tr>
						</c:forEach>
							</tbody>
                        </table>
                        </form:form>
                        </c:if>
				<c:if test="${empty imagesList}">
				<div style="text-align: center; margin-top: 20px;">No Previous Records</div>
				</c:if>
							<div class="clearfix"></div>
					</div>
</div>
    
