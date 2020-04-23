
 <script src="<%=request.getContextPath()%>/resources/js/jquery-1.9.0.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.validate.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.blueberry.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
 <link rel="<%=request.getContextPath()%>/stylesheet" href="resources/css/jquery-ui-1.10.4.custom.css" type="text/css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css"/>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.i18n.properties-1.0.9.js" type="text/javascript"></script>
 <style type="text/css">
label.error {
color:red;
display: list-item;
}

input.error {
border:1px solid red;
}

div.error {
	opacity: 0.85;
    width: 100%;
    z-index: 9990;
	background: #E53434;
	position:absolute;
	color: #fff;
	width: 150px;
	font-size: 11px;
	border: 2px solid #ddd;
	box-shadow: 0 0 6px #000;
	-moz-box-shadow: 0 0 6px #000;
	-webkit-box-shadow: 0 0 6px #000;
	padding: 4px 10px 4px 10px;
	border-radius: 6px;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
    }
    
    .tablediv
    {
    width: 100%;
    }
   .tablediv table, td, th
{
border:1px solid black;
text-align: center;
padding-left: 5px;
padding-right: 5px;
vertical-align: middle;
}
.tablediv th
{
background-color:#66B9B9;color:black;font-weight: bold;font-size: 20px; border:1px solid black;
}
.tablediv td
{
color:black;font-weight: bold;font-size: 17px; border:1px solid black;width: 100px;height:50px; 
}

.hiddenClass
{
display: none;
}
.showClass
{
display: block;
}
</style>
<script  type="text/javascript">

function showServiceData()
{
//alert("edwfhhiufdw");
var value=$('#PropertyType :selected').val();
	if(value!=0)
	{
	$.each($('.showClass'), function() {
		$("#"+this.id).removeClass("showClass");
    });

	$("#"+value).addClass("showClass");
	}
}

</script>
<body>
<div class="wrap" style="min-height:100%;">
 <div class="logo">
	<a><img src="<%=request.getContextPath()%>/resources/images/iamuse-logo.png" title="logo"/></a>
</div>


<div class="content">

		
		<label style="margin-top: 3.5%;margin-left: 30%;float: left;font-weight: bold;">Service :</label><select id="PropertyType" name="" onchange="showServiceData();" style="margin-top: 3%;margin-left: 2%;">
							<option value="0">Select Service</option>
							<option value="1">Image Upload</option>
							<option value="2">Upload Crashlogs</option>
							<option value="3">Save Device Token</option>
							<option value="4">Image Upload With Email ID</option>
							<option value="5">First Time RGBConfiguration</option>
			</select>
			
			<div class="clear"> </div>
			


<div class="tablediv">

<!--Registration Admin Booth Starts  -->
<div id="1" class="hiddenClass">
<div style="margin-top:50px;height: 5%;">
<div style="float: left;">
<label style="font-weight:bold; ">Request</label>
</div>
<div style="float: right;">
<label style="font-weight:bold; ">service-url = registrationAdminBooth</label>
</div>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>

<tr><td>User Id</td><td>1</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>Image String</td><td>BASE 64 String</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/>
 <br/>
 "userId":"1",<br/>
 "images":"/9j/4AAQSkZJRgABAgAAAQABAAD/7QA2UGhvdG9zaG9wIDMuMAA4QklNBAQAAAAAABkc<br/>
 AmcAFE0zY1g5dENBTUJ3S3htbGtFMGtXAP/iC/hJQ0NfUFJPRklMRQABAQAAC+gAAAAAAgAAAG1udHJSR"<br/>
  <br/>
}  <br/><br/>



</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response</label>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>

<tr><td>imageId</td><td>21</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>responseCode</td><td>0000</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>responseDescription</td><td>Success</td><td>Yes</td><td>-</td><td>-</td></tr>


<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/>
"imageId": 21,<br/>
"responseCode": "0000",<br/>
"responseDescription": "Success"<br/>
}<br/><br/>


</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response Code Associated With This Service</label>
</div>
<table style="border:1px solid black;">

<th colspan="2" style="width:25%;">Response Code</th><th colspan="3" style="width: 25%;">Description</th>

<tr><td colspan="2" >0000</td><td colspan="3">Success</td></tr>
<tr><td colspan="2" >0010</td><td colspan="3">User id is required</td></tr>




</table>


</div>

<!--Registration Admin Booth Ends  -->

<!--Upload Crashlogs Starts  -->
<div id="2" class="hiddenClass">
<div style="margin-top:50px;height: 5%;">
<div style="float: left;">
<label style="font-weight:bold; ">Request</label>
</div>
<div style="float: right;">
<label style="font-weight:bold; ">service-url = crashlogsupload</label>
</div>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>
<tr><td>User Id</td><td>1</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>Crash Log Files</td><td>BASE 64 String</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/><br/>
  "userId":"1",<br/>
  "files":[{"file":"Q29udHJvbGxlcjoNCg0KDQpAUmVxdWVzdE1hcHBpbmcodmFsdWUgPSAidj<br/>
  EvaWFtdXNlL2NyYXNobG9nc3VwbG9hZCIsIG1ldGhvZCA9IFJlcXVlc3RNZXRob2QuUE9TVCwgaG"},<br/>
  {"file":"cHVibGljIFN0cmluZyB2YWxpZGF0ZUltYWdlVXBsb2FkV2l0aEVtYWlsKFVwbG9hZEltY<br/>
  WdlV2l0aEVtYWlsUmVxdWVzdFZPIHVwbG9hZEltYWdlV2l0aEVtYWlsUmVxdWVzdFZPKQ0KCXsNCg"},<br/>
  {"file":"Ly9jb250cm9sbGVyDQpAUmVxdWVzdE1hcHBpbmcodmFsdWU9ImFkZGNsaWVudGFjdGlvbiI<br/>
  pDQoJcHVibGljIFN0cmluZyBhZGRjbGllbnRhY3Rpb24oQFJlcXVlc3RQYXJhbSgiZmlsZSIpIE11bHRpc"}]<br/><br/>
}<br/><br/>



</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response</label>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>

<tr><td>responseCode</td><td>0000</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>responseDescription</td><td>Success</td><td>Yes</td><td>-</td><td>-</td></tr>


<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/>
"responseCode": "0000",<br/>
"responseDescription": "Success"<br/>
}<br/><br/>


</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response Code Associated With This Service</label>
</div>
<table style="border:1px solid black;">

<th colspan="2" style="width:25%;">Response Code</th><th colspan="3" style="width: 25%;">Description</th>

<tr><td colspan="2" >0000</td><td colspan="3">Success</td></tr>
<tr><td colspan="2" >0010</td><td colspan="3">User id is required</td></tr>
<tr><td colspan="2" >0010</td><td colspan="3">Email id is required</td></tr>
<tr><td colspan="2" >0011</td><td colspan="3">String Image Required</td></tr>
<tr><td colspan="2" >0012</td><td colspan="3">Can't Upload More Than Three Images</td></tr>



</table>


</div>

<!--Upload Crashlogs Ends  -->

<!--Save Device Token Starts  -->

<div id="3" class="hiddenClass">
<div style="margin-top:50px;height: 5%;">
<div style="float: left;">
<label style="font-weight:bold; ">Request</label>
</div>
<div style="float: right;">
<label style="font-weight:bold; ">service-url = saveDeviceToken</label>
</div>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>
<tr><td>User Id</td><td>1</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>Device Token</td><td>f16741a989e68cc9d626c840d692fb42ae0326abc371a0df1d38956c412aa733</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/>
 <br/>
 "userId":"1",<br/>
  "deviceToken":"f16741a989e68cc9d626c840d692fb42ae0326abc371a0df1d38956c412aa733"<br/>
  <br/>
}  <br/><br/>



</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response</label>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>

<tr><td>responseCode</td><td>0000</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>responseDescription</td><td>Success</td><td>Yes</td><td>-</td><td>-</td></tr>


<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/>
"responseCode": "0000",<br/>
"responseDescription": "Success"<br/>
}<br/><br/>


</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response Code Associated With This Service</label>
</div>
<table style="border:1px solid black;">

<th colspan="2" style="width:25%;">Response Code</th><th colspan="3" style="width: 25%;">Description</th>

<tr><td colspan="2" >0000</td><td colspan="3">Success</td></tr>
<tr><td colspan="2" >0010</td><td colspan="3">User id is required</td></tr>
<tr><td colspan="2" >0010</td><td colspan="3">Device Token is required</td></tr>
<tr><td colspan="2" >0011</td><td colspan="3">String Image Required</td></tr>
<tr><td colspan="2" >0012</td><td colspan="3">Can't Upload More Than Three Images</td></tr>



</table>


</div>

<!--Save Device Token Ends  -->

<!--Image Upload With Email ID Starts  -->
<div id="4" class="hiddenClass">
<div style="margin-top:50px;height: 5%;">
<div style="float: left;">
<label style="font-weight:bold; ">Request</label>
</div>
<div style="float: right;">
<label style="font-weight:bold; ">service-url = imageuploadWithEmailId</label>
</div>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>
<tr><td>User Id</td><td>1</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>Images</td><td>BASE 64 String</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>Email Id</td><td>sumitsharma3741@gmail.com</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/><br/>
  "userId":"1",<br/>
  "images":[{"image":"iVBORw0KGgoAAAANSUhEUgAAAf8AAADBCAYAAADB9rnXAAA<br/>
  AAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAD<br/>
  14SURBVHhe7Z2/aytNl+f9Xzh0eMMbOtsb3s2uEmFQYifGgQJ7ExnWkRJ7Au0aRYJBr<br/>
  MHsrpl3vXhfMSDYHeMXT2AzIMyDebiaJ7ACDVyUyMEIjKOz51R1q6tbVa1q/fDTsr6C<br/>
  h8e33V11zuecqm/9amuD8AEBEAABEAABEFgrAhtr5S2cBQEQAAEQAAEQIIg/kgAEQAA"},<br/><br/>
  {"image":"iVBORw0KGgoAAAANSUhEUgAAAf8AAADBCAYAAADB9rnXAAAAAXNSR0IArs4<br/>
  c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAD14SURBVHhe7Z2/<br/>
  aytNl+f9Xzh0eMMbOtsb3s2uEmFQYifGgQJ7ExnWkRJ7Au0aRYJBrMHsrpl3vXhfMSDYH<br/>
  eMXT2AzIMyDebiaJ7ACDVyUyMEIjKOz51R1q6tbVa1q/fDTsr6Ch8e33V11zuecqm/9amuD8AE"}],<br/>
  "emailId":"sumitsharma3741@gmail.com"<br/><br/>
}<br/><br/>


</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response</label>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>

<tr><td>responseCode</td><td>0000</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>responseDescription</td><td>Success</td><td>Yes</td><td>-</td><td>-</td></tr>


<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/>
"responseCode": "0000",<br/>
"responseDescription": "Success"<br/>
}<br/><br/>


</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response Code Associated With This Service</label>
</div>
<table style="border:1px solid black;">

<th colspan="2" style="width:25%;">Response Code</th><th colspan="3" style="width: 25%;">Description</th>

<tr><td colspan="2" >1</td><td colspan="3">Success</td></tr>
<tr><td colspan="2" >0010</td><td colspan="3">User id is required</td></tr>
<tr><td colspan="2" >0010</td><td colspan="3">Email id is required</td></tr>
<tr><td colspan="2" >0011</td><td colspan="3">String Image Required</td></tr>
<tr><td colspan="2" >0012</td><td colspan="3">Can't Upload More Than Three Images</td></tr>



</table>


</div>

<!--Image Upload With Email ID Ends  -->



<!--First Time RGBConfiguration Starts  -->

<div id="5" class="hiddenClass">
<div style="margin-top:50px;height: 5%;">
<div style="float: left;">
<label style="font-weight:bold; ">Request</label>
</div>
<div style="float: right;">
<label style="font-weight:bold; ">service-url = firstTimeRGBConfiguration</label>
</div>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>
<tr><td>User Id</td><td>1</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/>
 <br/>
 "userId":"1"<br/>
  <br/>
}  <br/><br/>



</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response</label>
</div>
<table style="border:1px solid black;">

<th style="width:15%;">Parameter</th><th style="width: 50%;">Allowed value/s (example)</th><th style="width: 5%;">Mandatory</th><th style="width: 5%;">Max characters length</th><th style="width: 25%;" >Description</th>

<tr><td>rgbValue</td><td>0,255,0</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>responseCode</td><td>0000</td><td>Yes</td><td>-</td><td>-</td></tr>

<tr><td>responseDescription</td><td>Success</td><td>Yes</td><td>-</td><td>-</td></tr>


<tr>
<td colspan="100%;" style="text-align: left;">
JSON:<br/><br/>
{<br/>
"rgbValue": "0,255,0",<br/>
"responseCode": "0000",<br/>
"responseDescription": "Success"<br/>
}<br/><br/>


</td>
</tr>
</table>

<div style="margin-top:50px;height: 5%;">
<label style="font-weight:bold; ">Response Code Associated With This Service</label>
</div>
<table style="border:1px solid black;">

<th colspan="2" style="width:25%;">Response Code</th><th colspan="3" style="width: 25%;">Description</th>

<tr><td colspan="2" >0000</td><td colspan="3">Success</td></tr>
<tr><td colspan="2" >0010</td><td colspan="3">User id is required</td></tr>




</table>


</div>
<!--First Time RGBConfiguration Ends  -->






</div>
<div style="height: 8%;"></div>
</div>

</div>
</body>