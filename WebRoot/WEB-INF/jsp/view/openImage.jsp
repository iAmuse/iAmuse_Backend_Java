 
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%   
	 String id = (String)request.getAttribute("id"); 
     System.out.println("jcdsahdsjkhdhhghcvdsauy vcdsajhgdsac"+id);
    request.setAttribute("id",id); 
     
%>

<html lang="en">
<head>
	<meta charset="utf-8" />
	<title></title>
       
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.3.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>

</head>


<form:form action="saveRGBValue?id=${id}" modelAttribute="imageFormVO" method="post">

<div class="img-container">
<canvas width="600;" height="300" id="canvas_picker"></canvas>
<div class="col-md-3">
	<div>Preview:</div>
	<div id="preview"></div>
</div>
<div class="col-md-6">  
    <div class="form-row"><input type="radio" name="rgb" value="min"  onclick="ClearFields();">&nbsp;Select RGB Value&nbsp;&nbsp;&nbsp;<!--<input type="radio" name="rgb" value="max" onclick="ClearFields();">&nbsp;Brightest color   
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
	<input type="submit" value="save" class="btn-success btn"/>
	<a style="margin-left:15px;" href="<%=request.getContextPath()%>/imagedownload/<%=id %>/">Download Image</a>
	<div class="clearfix"></div>
</div>

</div>

</form:form>







<script type="text/javascript">
	var canvas = document.getElementById('canvas_picker').getContext('2d');

	// create an image object and get it’s source
	var img = new Image();
	img.src = '<%=request.getContextPath()%>/imagedownload/<%=id %>/';


	// copy the image to the canvas
	$(img).load(function(){
	   canvas.width=500;
       canvas.height=350;
       canvas.drawImage(img,0,0,img.width,img.height,0,0,500,350);
	 
	  //canvas.drawImage(img,0,0);
	});

	// http://www.javascripter.net/faq/rgbtohex.htm
	function rgbToHex(R,G,B) {return toHex(R)+toHex(G)+toHex(B)}
	function toHex(n) {
	  n = parseInt(n,10);
	  if (isNaN(n)) return "00";
	  n = Math.max(0,Math.min(n,255));
	  return "0123456789ABCDEF".charAt((n-n%16)/16)  + "0123456789ABCDEF".charAt(n%16);
	}


	$('#canvas_picker').click(function(event){
	  // getting user coordinates
	  var x = event.pageX - this.offsetLeft;
	  var y = event.pageY - this.offsetTop;
	  // getting image data and RGB values
	  var img_data = canvas.getImageData(x, y, 1, 1).data;
     // var pixelColor="rgba(111,123,14,255)";
     var pixelColor = "rgba("+img_data[0]+", "+img_data[1]+", "+img_data[2]+", "+img_data[3]+")";
     //alert(pixelColor);
       // $('#preview').css('backgroundColor', pixelColor);
	    $('#preview').css('backgroundColor',pixelColor);
	  var R = img_data[0];
	  var G = img_data[1];
	  var B = img_data[2];  var rgb = R + ',' + G + ',' + B;
	  // convert RGB to HEX
	  var hex = rgbToHex(R,G,B);
	  // making the color the value of the input
	   $('#rValue ').val(R);

        $('#gValue').val(G);

        $('#bValue').val(B);
		
	  $('#rgb input').val(rgb);
	  $('#hex input').val('#' + hex);
	   $('#rgbaVal').val(img_data[0]+','+img_data[1]+','+img_data[2]+','+img_data[3]);
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
</html>
