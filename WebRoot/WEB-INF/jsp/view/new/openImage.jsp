<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script src="<%=request.getContextPath()%>/resources/js/applicantDashboard.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">

 $(document).ready(function() {
	 $('#effect').delay(9000).fadeOut(400);
    $('#example').dataTable( {
         "scrollY":        "470px",
        "scrollCollapse": true,
        "paging":         false
    } );
} ); 

  
        </script>	

<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>

<script src="<%=request.getContextPath()%>/resources/js/jqueryv1.7.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrapV2.3.2.js"></script>
  
<%   
	 String ils = (String)request.getAttribute("imageListSize"); 
	  String ilus = (String)request.getAttribute("imageListUpdatedSize");
%>

<%   
	 String id = (String)request.getAttribute("id"); 
     System.out.println("jcdsahdsjkhdhhghcvdsauy vcdsajhgdsac"+id);
    request.setAttribute("id",id); 
     
%>

<script type="text/javascript">
      $(document).ready(function() {
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
<script type="text/javascript">
function finishTask() {
    
    var r = confirm("Your event setup is complete. This event is now available to download on your devices.");
   if (r == true) {
     
      return true;
   } 
}
</script>
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
<div class="col-lg-2 col-md-3" style="width:20%;"></div>
<div class="right-pannel col-xs-12 col-lg-10">

<form:form action="saveRGBValue?id=${id}" modelAttribute="imageFormVO" method="post">

<div class="img-container">
<h1 class="heading pull-left">Update Transparent Color</h1><br/><br/>
<canvas width="600" height="300" id="canvas_picker" ></canvas>
<div class="col-md-5 rgb-textbox">
	<div>Preview:</div>
	<div id="preview" style="width:110px;"></div>

    <div class="form-row" style="margin:20px 0px 10px 0px;"><input type="radio" name="rgb" value="min"  onclick="ClearFields();">&nbsp;Select RGB Value&nbsp;&nbsp;&nbsp;<!--<input type="radio" name="rgb" value="max" onclick="ClearFields();">&nbsp;Brightest color   
		--><div class="clearfix"></div>
	</div>      
	 <c:forEach items="${imageDetails}" var="dl">
	<div id="hex" class="form-row">
		<span class="label-text">HEX:</span>
		<input type="text" name="hexValue" id="hexValue" class="input-sm" value="${dl.hexValue}" readonly="readonly"  style="border:none;font-weight:600"/>
		<div class="clearfix"></div>
	</div>
	<div id="rgb"> 
		<input type="hidden"  name="rgbValue" id="rgbValue" class="input-sm" value="${dl.rgbValue}" readonly="readonly"/> 
	</div>
	<div>
		<input type="hidden" id="rgbaVal" name="rgbaValue" class="input-sm" value="${dl.rgbaValue}" />
	</div>
	<div class="form-row">
		<span class="label-text">R:</span>
		<input type="text" name="rValue" id="rValue" class="input-sm" value="${dl.r}" readonly="readonly"/>
		<div class="clearfix"></div>
	</div>
	<div class="form-row">
		<span class="label-text">G:</span>
		<input type="text" name="gValue" id="gValue" class="input-sm" value="${dl.g}" readonly="readonly"/>
		<div class="clearfix"></div>
	</div>
	<div class="form-row">
		<span class="label-text">B:</span>
		<input type="text" name="bValue" id="bValue" class="input-sm" value="${dl.b}" readonly="readonly"/>
		<div class="clearfix"></div>
	</div>
	<script>
	var pixelcolor1="rgba("+"${dl.rgbaValue}"+")";
    $('#preview').css('backgroundColor',pixelcolor1);
	</script>
	</c:forEach>
	<div class="clearfix"></div>
	<input type="submit" value="Save" class="btn-success btn" style="width:145px;margin-left:43px">
	<!-- <button class="btn-success btn" style="width:145px;margin-left:10px" onclick="finish()">Finish</button> -->
	<%-- <a style="margin-left:15px;" href="<%=request.getContextPath()%>/imagedownload/<%=id %>/">Download Image</a> --%>
	<div class="clearfix"></div>
</div>
<!-- <div class="" style="width:600px;float:left;margin-top:22px;"> -->

	<div class="clearfix"></div>
<!-- </div> -->

<div class="clearfix"></div>
</div>

</form:form>
<!-- <a href="finishConfiguration" onclick="finishTask();"><button class="btn btn-green pull-right" style="margin-left:10px;background-color: #ff5722 !important;" >Finish</button></a> -->
<div class="image-list">

						<c:if test="${not empty imagesList}">
                   
                        
                          <form:form id="deleteFiles" action="deleteSelected" method="post" onsubmit="return confirm_update();">
                            <!-- <input type="submit"  class="btn-success btn" value="Delete Selected" style="width:auto;float:right;margin-bottom:15px;"/>   -->
                            <input type="hidden" name="page" value="dashboardImage" />       
                       
                         <table id="example" class="table table-bordered table-striped" cellspacing="0">
                         <thead class="thead">
                          <tr>
                          <!--  <th class="head"><input type="checkbox" id="selecctall"/>Select All</th> -->
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
							 	<%-- <td style="width:90px;position:relative;"><input type="checkbox" class="checkbox1" id="checkbox" name="checkbox1" value="${al.imageId}"/></td> --%>
								<td>${al.serialNumber}</td>
								<td ><a href="openimagepage?id=${al.imageId}">${al.imagename}</a></td>
								<td>${al.uploadTime}</td>
								<td>${al.updateTime}</td>
								<td><b>MinRGB:&nbsp;&nbsp;</b>${al.rgbValue}<%-- <br/><br/><b>MaxRGB:&nbsp;&nbsp;</b>${al.rgbValueMax} --%></td>
								<td><img onclick="javascript:exportToForm('${al.imageId}')" src="<%=request.getContextPath()%>/resources/images/delete.png" />
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


<script>
function rgbToHex(R,G,B) {return toHex(R)+toHex(G)+toHex(B)}
function toHex(n) {
  n = parseInt(n,10);
  if (isNaN(n)) return "00";
  n = Math.max(0,Math.min(n,255));
  return "0123456789ABCDEF".charAt((n-n%16)/16)  + "0123456789ABCDEF".charAt(n%16);
}
</script>


<script type="text/javascript">
var canvas;
var ctx;
	
	// create an image object and get it’s source
	var img = new Image();
	img.src = '${uploadImage.imageUrl}/${boothAdminLogin2.userId}/${uploadImage.imageName}';
	//img.crossOrigin="anonymous";
	// copy the image to the canvas
	$(img).load(function(){
		ctx.drawImage(img, 0, 0, 600, 300);
	 
	  //canvas.drawImage(img,0,0);
	});
	 canvas = document.getElementById('canvas_picker');
	    ctx = canvas.getContext('2d');
	// http://www.javascripter.net/faq/rgbtohex.htm
	  $('#canvas_picker').click(function(e) { // mouse move handler
        var canvasOffset = $(canvas).offset();
        var canvasX = Math.floor(e.pageX - canvasOffset.left);
        var canvasY = Math.floor(e.pageY - canvasOffset.top);

        var img_data = ctx.getImageData(canvasX, canvasY, 1, 1);
        var pixel = img_data.data;

        var pixelColor = "rgba("+pixel[0]+", "+pixel[1]+", "+pixel[2]+", "+pixel[3]+")";
     //   alert(" mouse over  pixelColor "+pixelColor);
       //$('#rValue').val(pixel[0]);
       // $('#gValue').val(pixel[1]);
        //$('#bValue').val(pixel[2]);
        
       // $('#rgbValue').val(pixel[0]+','+pixel[1]+','+pixel[2]);
       // $('#rgbaValue').val(pixel[0]+','+pixel[1]+','+pixel[2]+','+pixel[3]);
     //   var dColor = pixel[2] + 256 * pixel[1] + 65536 * pixel[0];
     //   $('#hexVal').val( '#' + dColor.toString(16) );
      //  $('#preview').css('backgroundColor', pixelColor);
        
        
        $('#preview').css('backgroundColor',pixelColor);
   	 // var img_data = canvas.getImageData(x, y, 1, 1).data;
   	  var R = pixel[0];
   	  var G = pixel[1];
   	  var B = pixel[2];  
   	  var rgb = R + ',' + G + ',' + B;
   	  // convert RGB to HEX
   	  var hex = rgbToHex(R,G,B);
     // making the color the value of the input
      	$('#rValue ').val(R);
    	$('#gValue').val(G);
    	$('#bValue').val(B);
     
     $('#rgb input').val(rgb);
     $('#hex input').val('#' + hex);
     $('#rgbaVal').val(pixel[0]+','+pixel[1]+','+pixel[2]+','+pixel[3]);
    });




</script>
<script>
function ClearFields() {

     document.getElementById("hexValue").value = "";
     document.getElementById("rgbValue").value = "";
	 document.getElementById("rgbaVal").value = "";
	 document.getElementById("rValue").value = "";
     document.getElementById("gValue").value = "";
	 document.getElementById("bValue").value = "";
	 var pixelcolor1="rgba(255,255,255,255)";
    $('#preview').css('backgroundColor',pixelcolor1);
}
</script>
<div class="clearfix"></div>
</div>
