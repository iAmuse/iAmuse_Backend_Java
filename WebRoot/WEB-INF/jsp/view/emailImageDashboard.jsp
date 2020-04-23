    <%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/pop-up-style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" />
<script src="<%=request.getContextPath()%>/resources/js/applicantDashboard.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/calander.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/calander.css" />
<% String emailId= (String)request.getSession().getAttribute("email"); %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.fancybox-1.3.4.css" />
<style type="text/css">
    a.fancybox img {
        border: none;
        box-shadow: 0 1px 7px rgba(0,0,0,0.6);
        -o-transform: scale(1,1); -ms-transform: scale(1,1); -moz-transform: scale(1,1); -webkit-transform: scale(1,1); transform: scale(1,1); -o-transition: all 0.2s ease-in-out; -ms-transition: all 0.2s ease-in-out; -moz-transition: all 0.2s ease-in-out; -webkit-transition: all 0.2s ease-in-out; transition: all 0.2s ease-in-out;
    } 
    a.fancybox:hover img {
        position: relative; z-index: 999; -o-transform: scale(1.03,1.03); -ms-transform: scale(1.03,1.03); -moz-transform: scale(1.03,1.03); -webkit-transform: scale(1.03,1.03); transform: scale(1.03,1.03);
    }
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.fancybox-1.3.4.pack.min.js"></script>
<script type="text/javascript">
    $(function($){
        var addToAll = false;
        var gallery = false;
        var titlePosition = 'inside';
        $(addToAll ? 'img' : 'img.fancybox').each(function(){
            var $this = $(this);
            var title = $this.attr('title');
            var src = $this.attr('data-big') || $this.attr('src');
            var a = $('<a href="#" class="fancybox"></a>').attr('href', src).attr('title', title);
            $this.wrap(a);
        });
        if (gallery)
            $('a.fancybox').attr('rel', 'fancyboxgallery');
        $('a.fancybox').fancybox({
            titlePosition: titlePosition
        });
    });
    $.noConflict();
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
     
     var r = confirm("Are You Sure Want to delete!");
    if (r == true) {
     
        window.location="<%=request.getContextPath()%>/removeEmailImage?id="+id
        
    } 
 }




function showpopup(id)
{           
	         var str = id;
	        
	         var arr = str.split(',');
	        
	         
	        $("#mailpoptext1").val(arr[0]);
	        $("#mailpoptext2").val(arr[1]);
	     
	        if(arr.length===3)
	        {
	        var imageName=arr[2];
	        }
	      if(arr.length===4)
	        {
	        var imageName=arr[2]+','+arr[3];
	        }
	        if(arr.length===5){
	       
	        var imageName=arr[2]+','+arr[3]+','+arr[4];
	       }
	        $("#mailpoptext3").val(imageName);
	        $('#currentmailId').html(arr[1]);
		    $("#mailPopup").fadeIn(0500); // fadein popup div
			$("#backgroundPopup").css("opacity", "0.7"); // css opacity, supports IE7, IE8
			$("#backgroundPopup").fadeIn(0001); 
				popupStatus = 1;
}
function showpopup2()
{           
	        
		    $("#csvPopup").fadeIn(0500); // fadein popup div
			$("#backgroundPopup").css("opacity", "0.7"); // css opacity, supports IE7, IE8
			$("#backgroundPopup").fadeIn(0001); 
				popupStatus = 1;
}
function showpopup1(id,emailId)
{           
	         var str = id;
	         var arr = str.split(',');
	        $("#poptext1").val(arr[0]);
	        $("#poptext2").val(arr[1]);
		    $("#toPopup").fadeIn(0500); // fadein popup div
			$("#backgroundPopup").css("opacity", "0.7"); // css opacity, supports IE7, IE8
			$("#backgroundPopup").fadeIn(0001); 
				popupStatus = 1;
}
function closePopup()
{
			$("#toPopup").fadeOut("normal");  
			$("#backgroundPopup").fadeOut("normal");  
}
function closePopup1()
{
			$("#mailPopup").fadeOut("normal");  
			$("#backgroundPopup").fadeOut("normal");  
}
function closePopup2()
{
			$("#csvPopup").fadeOut("normal");  
			$("#backgroundPopup").fadeOut("normal");  
}

$(document).ready(function(){
	$(this).keyup(function(event) {
		if (event.which == 27) { // 27 is 'Ecs' in the keyboard
			disablePopup();  // function close pop up
		}  	
	});
});

function edit()
{           

			$("#poptext2").attr("disabled",false);  
			
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

$(function() {
    $('.date-picker').datepicker( {
        changeMonth: true,
        changeYear: true,
        changeDate: true,
        showButtonPanel: true,
        dateFormat: 'dd-mm-yy'
    });
});
function validateDate()
{

	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;

	if(startDate.trim()=='')
		{
		alert('Please Select Start Date');
		return false;
		}
	if(endDate.trim()=='')
		{
		alert('Please Select End Date');
		return false;
		}
	
var dt1  = parseInt(startDate.substring(0,2),10); 
var mon1 = parseInt(startDate.substring(3,5),10); 
var yr1  = parseInt(startDate.substring(6,10),10); 
var dt2  = parseInt(endDate.substring(0,2),10); 
var mon2 = parseInt(endDate.substring(3,5),10); 
var yr2  = parseInt(endDate.substring(6,10),10); 
var date1 = new Date(yr1, mon1, dt1); 
var date2 = new Date(yr2, mon2, dt2); 

if(date2 < date1) 
{ 
    alert("To date cannot be greater than From date"); 
    return false; 
} 

	closePopup2();
	return true;
	
}
function clearList()
{
 var r = confirm("Are You Sure Want to Clear Downloaded List!");
    if (r == true) {
     
        window.location="<%=request.getContextPath()%>/clearList"
        
    } 
}


function clearDevicePictures()
{
 var r = confirm("Are You Sure Want to Clear Device Pictures!");
    if (r == true) {
     
        window.location="<%=request.getContextPath()%>/clearDevicePictures"
        
    } 
}
</script>
<script>
  $(document).ready(function(){
    $('.img-zoom').hover(function() {
        $(this).addClass('transition');
 
    }, function() {
        $(this).removeClass('transition');
    });
  });
</script>
        <div class="">
		<h3>Images List</h3>
	<c:if test="${status==true}">
	<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
	</c:if>
     	
        <c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if><!--
					<select id="dropdown">
	 <option value="0">Select Action</option>				
     <option value="1">option 1</option>
     <option value="2">option 2</option>
</select>--><input type="button"  class="btn-success btn" value="Clear Device Pictures" onclick="clearDevicePictures();"/>
					<c:if test="${not empty emailImagesList}">
                   
                      
                      <div><span id="error"  style="color: red;left: 100px; position: absolute;"></span></div>
		   <div class="col-md-6 rightbox " style="">
                       <h4>Export Data</h4>   
        <form:form  action="downloadCSV"  method="post" modelAttribute="imageEmailFormVO" onsubmit="return validateDate();">
		
		<div class="pull-left">
		From:<input type="text" name="startDate" id="startDate" class="date-picker" style="" readonly="readonly"  placeholder="DD-MM-YYYY"/>
		</div>
		<div class="pull-left">
		To:<input type="text" name="endDate" id="endDate" class="date-picker" readonly="readonly" placeholder="DD-MM-YYYY" />
		</div>
		<div class="pull-left">
		<input type="submit"  class="btn-success btn" value="Go" />
		</div>
		
			
           </form:form>
                          
                          </div>
                   <form:form id="deleteFiles" action="deleteSelected" method="post" onsubmit="return confirm_update();">
                           <div class="buttondiv pull-right"> <input type="submit"  class="btn-success btn" value="Delete Selected"/>
                            <input type="button"  class="btn-success btn" value="Clear Exported Records" onclick="clearList();"/>   
                            <input type="hidden" name="page" value="userImage" />  
                            </div>
                           
                        <table id="example" class="table table-bordered table-striped dashboardtable" cellspacing="0" >
                         <thead class="thead">
                          <tr>
                           <th class="head"><input type="checkbox" id="selecctall"/>Select</th>
                          <th class="head" ><span>S.No.</span></th>
                          	<th class="head"  ><span>Images List</span></th>
                          	<th class="head"><span>Image Preview</span></th>
                            <th class="head"  ><span>Email Id</span></th>
                            <th class="head"  ><span>Sent Mail Time Span</span></th>
                            <th class="head"  ><span>Resend Image</span></th>
                           
                            
                            <th></th>
                          </tr>
                          </thead> 
                          	<tbody>
                       	<c:forEach items="${emailImagesList}" var="al">
                       	<c:if test="${al.downloadStatus == '0'}" >
                      
							<tr>
							<td style="width:7%"><input type="checkbox" class="checkbox1" id="checkbox" name="checkbox1" value="${al.id}"/></td>
							
							<td style="width:3%">${al.serialNumber}</td>
							
							<td ><c:set var="dateParts" value="${fn:split(al.mailImageName, ',')}" />
							<c:forEach items="${dateParts}" var="link" >IAMUSE${link}&nbsp;<br/></c:forEach></td>
							
<td><c:set var="dateParts" value="${fn:split(al.mailImageName, ',')}" />
							<c:forEach items="${dateParts}" var="link" ><a href="<%=request.getContextPath()%>/emailImage/${al.id}/${link}/"><img  src="<%=request.getContextPath()%>/emailImage/${al.id}/${link}/" height="50" width="75" class="fancybox"></a>&nbsp;&nbsp;</c:forEach></td>
						
  	<td>${al.emailId}&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/images/edit.png" onclick="showpopup1('${al.id},${al.emailId}');"/></td>
								<td>${al.mailSentTime}</td>
								<td><a href="#"  onclick="showpopup('${al.id},${al.emailId},${al.mailImageName}');">Resend Image</a></td>
								<td> <img onclick="javascript:exportToForm('${al.id}')" src="<%=request.getContextPath()%>/resources/images/delete.png" /></td>
                                
							</tr>
							</c:if>
					<c:if test="${al.downloadStatus == '1'}" >
                      
							<tr>
							<td style="background-color:#EBA521;width:7%"><input type="checkbox" class="checkbox1" id="checkbox" name="checkbox1" value="${al.id}"/></td>
							
							<td style="background-color:#EBA521;width:3%">${al.serialNumber}</td>
							
							<td style="background-color:#EBA521"><c:set var="dateParts" value="${fn:split(al.mailImageName, ',')}" />
							<c:forEach items="${dateParts}" var="link" >IAMUSE${link}&nbsp;<br/></c:forEach></td>
							
<td style="background-color:#EBA521"><c:set var="dateParts" value="${fn:split(al.mailImageName, ',')}" />
							<c:forEach items="${dateParts}" var="link" ><a href="<%=request.getContextPath()%>/emailImage/${al.id}/${link}/"><img  src="<%=request.getContextPath()%>/emailImage/${al.id}/${link}/" height="50" width="75" class="fancybox"></a>&nbsp;&nbsp;</c:forEach></td>
						
  	<td style="background-color:#EBA521"> ${al.emailId}&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/images/edit.png" onclick="showpopup1('${al.id},${al.emailId}');"/></td>
								<td style="background-color:#EBA521">${al.mailSentTime}</td>
								<td style="background-color:#EBA521"><a href="#"  onclick="showpopup('${al.id},${al.emailId},${al.mailImageName}');">Resend Image</a></td>
								<td style="background-color:#EBA521"> <img onclick="javascript:exportToForm('${al.id}')" src="<%=request.getContextPath()%>/resources/images/delete.png" /></td>
                                
							</tr>
					
							</c:if>
						</c:forEach>
							</tbody>
                        </table>
                        </form:form>
                        </c:if>
				<c:if test="${empty emailImagesList}">
				<div style="text-align: center; margin-top: 20px;">No Record Found</div>
				</c:if>
				
				<div class="clearfix"></div>
      </div>
				   <!-- 
  PopUp Div Starting
   -->
      <div id="toPopup"> 
    	
        <div class="close" onclick="closePopup();"></div>
       	
		<div id="popup_content"> <!--Popup content start-->
		
		<div><span id="error"  style="color: red;left: 100px; position: absolute;"></span></div>
		<form:form  action="updateEmailId"  method="post" modelAttribute="imageEmailFormVO">
		<h4 style="border-bottom:1px solid #e0e0e0;color:#428bca !important;width:70%;margin:10px auto; text-align:center;padding-bottom:10px;">Edit Email ID</h4>
		<table align="center">
		<tr><td>&nbsp;</td><td><input type="text" name="poptext1" id="poptext1" style="display: none"  /></td></tr>
		<tr><td>Email Id:</td><td><input type="text" name="poptext2" id="poptext2"  style="width:300px;"></td></tr>
		<tr><td>&nbsp;</td></tr>
		</table>
			<center> <input type="submit" class="btn btn-lg btn-theme btn-block" value="Update" /></center>
           </form:form>
           </div> <!--Popup content end-->
    
    </div> <!--toPopup end-->
    
     <!-- 
  PopUp for resend image Div Starting
   -->
      <div id="mailPopup"> 
    	
        <div class="close" onclick="closePopup1();"></div>
       	
		<div id="mailpopup_content"> <!--Popup content start-->
		
		<div><span id="error"  style="color: red;left: 100px; position: absolute;"></span></div>
	<form:form  action="resendEmail"  method="post" modelAttribute="imageEmailFormVO">
		<h4 style="border-bottom:1px solid #e0e0e0;color:#428bca !important;width:70%;margin:10px auto; text-align:center;padding-bottom:10px;">Picture Send to </h4>
		<br/><div id="currentmailId" style="text-align:center;color:#EBA521;font-size:22px;"></div>	
		<table>
		<tr><td>&nbsp;</td><td><input type="text" name="mailpoptext1" id="mailpoptext1" style="display: none"  /></td></tr>
		<tr><td>&nbsp;</td><td><input type="hidden" name="mailpoptext2" id="mailpoptext2"  style="width:300px;"></td></tr>
		<tr><td>&nbsp;</td><td><input type="hidden" name="mailpoptext3" id="mailpoptext3"  style="width:300px;"></td></tr>
		<tr><td>&nbsp;</td></tr>
		</table>
		
			<center> <input type="submit" class="btn btn-lg btn-theme btn-block" value="Send Mail" /></center>
           </form:form>
           </div> <!--Popup content end-->
    
    </div> <!--toPopup for resend image end-->
    
		
          
	<div class="loader"></div>
   	<div id="backgroundPopup"></div>
   