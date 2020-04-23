 
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/pop-up-style.css" />

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
} // isNumberKey
</script>
</head>
<div class="img-container">
<div class="col-md-3">
	<div>Preview:</div>
	<div id="preview"></div>
</div>
<div class="col-md-8"> 
<form:form action="updateDefaultRGB" modelAttribute="imageFormVO"> 
     <c:forEach items="${imageDetails}" var="dl">
     
    <div id="hex" class="form-row">
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
    <input type="submit" class="btn-success btn pull-left" style="margin-left:10px; margin-top:20px; text-decoration:none;" value="Update RGB">
	<a onclick="resetDefault()" class="btn-success btn "  style="margin-left:10px; margin-top:20px; text-decoration:none;">Reset Default</a>	
   
      </form:form>
</div>
</div>

    
    <!--toPopup end-->
</html>
