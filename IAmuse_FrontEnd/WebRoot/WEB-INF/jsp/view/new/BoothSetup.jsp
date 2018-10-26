<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%-- 
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script> --%>
 <script src="<%=request.getContextPath()%>/resources/js/applicantDashboard.js" type="text/javascript"></script>
  <script type="text/javascript">

setInterval(mycode, 15000);

function mycode() {
      $.ajax({
            url : 'autoRefresh',
            success : function(data) {
            	  if(data==1)
            	   {
            	   var r = confirm("Image recieved press ok to refresh");
            	   if (r == true) {
            	   		window.location="<%=request.getContextPath()%>/afterRefresh"
            	     }
            	   }  
            	}
        	});
        }
</script>

<script type="text/javascript">
      $(document).ready(function() {
            $("#q").addClass("active_menu");
        });
      function resetDefault() {
    	     
    	     var r = confirm("Are you Sure Want to Reset Default?");
    	    if (r == true) {
    	     
    	        window.location="<%=request.getContextPath()%>/resetSystemDefaultRGBValue"
    	        
    	    } 
    	 }
</script>

<style>
/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

#abc {
width:100%;
height:100%;
opacity:.95;
top:0;
left:0;
display:none;
position:fixed;
background-color:#313131;
overflow:auto;
z-index: 1;
}

div#popupContact {
    background: #fff;
    position: absolute;
    left: 50%;
    top: 17%;
    margin-left: -172px;
    font-family: 'Raleway',sans-serif;
    width: 29%;
    height: 30%;
}

</style>
<style>
	.right-pannel{height:auto !important}
</style>


<script type="text/javascript">
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
      function div_show() {
    	  document.getElementById('abc').style.display = "block";
    	  }
      
      function check_empty() {
    	  if (document.getElementById('name').value == "" || document.getElementById('email').value == "" || document.getElementById('msg').value == "") {
    	  alert("Fill All Fields !");
    	  } else {
    	  document.getElementById('form').submit();
    	  alert("Form Submitted Successfully...");
    	  }
    	  }
    	  //Function To Display Popup
    	  function div_show() {
    	  document.getElementById('abc').style.display = "block";
    	  }
    	  //Function to Hide Popup
    	  function div_hide(){
    	  document.getElementById('abc').style.display = "none";
    	  }
</script>
<style>
/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

#abc {
width:100%;
height:100%;
opacity:.95;
top:0;
left:0;
display:none;
position:fixed;
background-color:#313131;
overflow:auto;
z-index: 1;
}

div#popupContact {
    background: #fff;
    position: absolute;
    left: 50%;
    top: 17%;
    margin-left: -172px;
    font-family: 'Raleway',sans-serif;
    width: 29%;
    height: 30%;
}

</style>
<style>
	.right-pannel{height:auto !important}
	
	
.loader { position:absolute; left:50%; top:50%;background:rgba(0,0,0,0.6);}
 .loadercls, .hidecls { 
 
opacity:0.7;
filter: alpha(opacity=20);
background-color:#000; 
width:100%; 
height:100%; 
z-index:10;
top:0; 
left:0; 
position:fixed; 
}
</style>
<div id="abc">
<div id="popupContact">
<!-- Contact Us Form -->
<input id="emailId" name="name" placeholder="Name" value="${boothAdminLogin.emailId}" type="text" style="margin:39px 20px 30px 10px;width: 91%;">
<button onclick="sentTestEmail();" class="btn btn-green" style="float: right;margin-right: 10%;">send</button>
<button onclick="div_hide();" class="btn btn-green" style="float: right;margin-right:2%;">Cancel</button>
</div>
<!-- Popup Div Ends Here -->
</div>	
<div class="right-pannel">
	<!-- <a href="boothSetUp"><button class="btn btn-green pull-right" style="margin-left:10px;">Refresh</button></a> -->
		<h1 class="heading pull-left" style="margin-bottom:0px;">Set Transparent Color</h1>		
		<div class="clearfix"></div>
		<p class="subtext pull-left" style="text-align:left;width:80%">Take a Test Picture from Camera device of your Green Background and click on image to set the transparent color. Choose brightest green color (your background should be evenly lighted with little variation/shadows.)</p>
		<a href="getImagePushAdmin"><button class="btn btn-green pull-right" style="margin-left:10px;" onclick="target();">Take Calibration Picture</button></a>
		<div class="clearfix"></div>
		<p class="subtext pull-right" style="margin-bottom:20px;text-align:left;width: 34%;font-size: 10px;">Please be patient, can take several minutes. Ensure iAmuse app notifications are set to On.</p>
		<div class="clearfix"></div>
		<!-- <div class="pull-right" data-step="3" data-intro="You can test mail">
					<a href="#"><button class="btn btn-green pull-right" style="margin-right:10px;" id="popup" onclick="div_show()" data-toggle="tooltip" title="You can test mail">Test Email</button></a>
					</div>
					<div class="pull-right" data-step="4" data-intro="You can clear your device picture">
					<a href="#"><button class="btn btn-green pull-right" style="margin-right:10px;" onclick="clearDevicePictures()"  data-toggle="tooltip" title="You can clear your device picture">Clear Device Pictures</button></a>
					</div>	 -->
							
<form:form action="saveRGBValueBoothSetup?id=${id}" modelAttribute="imageFormVO" method="post">




					<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>	
					  <div class="image-list" style="margin-top:0px;">
					  <div class="img-container" style="margin-top:0px;">
<canvas width="600" height="300" id="canvas_picker" ></canvas>
<div class="col-md-5 rgb-textbox remove_border_text">
	<div>Preview:</div>
	<div id="preview" style="width:110px;"></div>

    <div class="form-row" style="margin:20px 0px 10px 0px;"><input type="radio" name="rgb" value="min"  onclick="ClearFields();">&nbsp;Select Transparent Value&nbsp;&nbsp;&nbsp;<!--<input type="radio" name="rgb" value="max" onclick="ClearFields();">&nbsp;Brightest color   
		--><div class="clearfix"></div>
	</div>      
	 <c:forEach items="${imageDetails}" var="dl">
	<div id="hex" class="form-row">
		<span class="label-text">HEX:</span>
		<input type="text" name="hexValue" id="hexValue" class="input-sm" value="${dl.hexValue}" readonly="readonly"/>
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
		<input type="text" name="rValue" id="rValue" class="input-sm" value="${dl.r}" maxlength="3" readonly="readonly" onkeyup="funRgb();" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
		<div class="clearfix"></div>
	</div>
	<div class="form-row">
		<span class="label-text">G:</span>
		<input type="text" name="gValue" id="gValue" class="input-sm" value="${dl.g}" maxlength="3" readonly="readonly" onkeyup="funRgb();" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
		<div class="clearfix"></div>
	</div>
	<div class="form-row">
		<span class="label-text">B:</span>
		<input type="text" name="bValue" id="bValue" class="input-sm" value="${dl.b}" maxlength="3" readonly="readonly" onkeyup="funRgb();" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
		<div class="clearfix"></div>
	</div>
	<input type="hidden" id="imag" value="${uploadImage.imageId}">
	<script>
	var pixelcolor1="rgba("+"${dl.rgbaValue}"+")";
    $('#preview').css('backgroundColor',pixelcolor1);
	</script>
	</c:forEach>
	<div class="clearfix"></div>
	<a onclick="resetDefault()" class="btn-success btn "  style="margin-left:10px;text-decoration:none;width:145px;color:#fff;padding:10px;">Reset Default</a>
	<input type="submit" value="Save" class="btn-success btn" style="width:145px;margin-left:43px;float: right;" onclick="enablebtn();">
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
	<!-- <button style="float: right;margin-top: 10px;" class="btn btn-green next-btn"><a href="cropEdges" style="text-decoration: none;color:#fff;">Next</a></button> -->
<div class="clearfix"></div>

</div>

<input type="hidden" value="${uploadImage.imageUrl}" id="imgUrl"/>
<script>
function enablebtn(){
	$(".next-btn").attr("disabled", true);
	}
	$(document).ready(function(){
			$('.next-btn').attr('disabled', 'disabled');
	})

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
	var urlImg=document.getElementById("imgUrl").value;
	// create an image object and get it’s source
	var img = new Image();
	var img1='${uploadImage.imageUrl}/${boothAdminLogin.userId}/${uploadImage.imageName}';
	var img2='<%=request.getContextPath()%>/resources/img/testPicture.png';
	if(urlImg.trim()==""){
		img.src = img2;
	}else{
	img.src = img1;
	}
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
//function ClearFields() {

    $(document).ready(function() {
    	var myRadios = document.getElementsByName('rgb');
        var setCheck;
        var x = 0;
        for(x = 0; x < myRadios.length; x++){

            myRadios[x].onclick = function(){
                if(setCheck != this){
                	  document.getElementById("hexValue").value = "";
                	     document.getElementById("rgbValue").value = "";
                		 document.getElementById("rgbaVal").value = "";
                		 var r=document.getElementById("rValue").value = "";
                		 var g=document.getElementById("gValue").value = "";
                		 var b=document.getElementById("bValue").value = "";
                		 var pixelcolor1="rgba(255,255,255,255)";
                	    $('#preview').css('backgroundColor',pixelcolor1);
                	    
                	    $("#rgbValue,#rgbaVal,#rValue,#gValue,#bValue").attr("readonly", false);
                	    
                     setCheck = this;
                }else{
                	window.location.reload();
                    this.checked = false;
                    setCheck = null;
            }
            };

        } 
    });
	
   
//}
function funRgb(){
		var r=document.getElementById("rValue").value;
	 	var g=document.getElementById("gValue").value;
	 	var b=document.getElementById("bValue").value;

	if(r.trim() !="" && g.trim() !="" && b.trim() !=""){
		var rgb = b | (g << 8) | (r << 16);
		var st=(0x1000000 + rgb).toString(16).slice(1);
		document.getElementById("hexValue").value ='#' + st.toUpperCase();
		
		$('#rValue').attr('value', r);
		$('#gValue').attr('value', g);
		$('#bValue').attr('value', b);
		$('#rgbValue').attr('value', r+","+g+","+b);
		 
		var pixelColor = "rgba("+r+", "+g+", "+b+", "+255+")";
		var pixelColorq1 = r+","+g+","+b+","+255;
	        
		$('#rgbaVal').attr('value', pixelColorq1);
	        $('#preview').css('backgroundColor',pixelColor);
		}
	}
</script>
<div class="clearfix"></div>