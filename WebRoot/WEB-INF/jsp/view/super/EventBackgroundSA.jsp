	<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
			 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/eventBackground.js">  </script>
	
  <script>
 $(document).ready(function() {
	  $('#effect').delay(9000).fadeOut(400);
	  $("#b").removeClass("active_menu");
       $("#d").addClass("active_menu");
   });
 </script>
 <script>
 $(document).ready(function() {
	    function disableBack() { 
	    	window.history.forward() 
	    	}
	    window.onload = disableBack();
	    window.onpageshow = function(evt) { 
	    	if (evt.persisted) disableBack() ;
	    	}
	});
		/* var selectedCheckBox = new Array();
		function handleClick(id){
			selectedCheckBox.push(id);
			$('#selectedPreImage').val(selectedCheckBox);
		}	 */		 
		function handleClickWaterMarkImage(id){
			$('#selectedPreWaterMarkImage').val(id);
			$('#waterMarkImageUploadFile').val("");
		}
		function handleClickCameraTVScreenSaver(id){
			$('#selectedPreCameraTVScreenSaver').val(id);
			$('#cameraTVScreenSaverUploadFile').val("");
		}	 
		function handleClickLookAtTouchScreen(id){
			$('#selectedPreLookAtTouchScreen').val(id);
			$('#lookAtTouchScreenUploadFile').val("");
		} 
		function handleClickThankYouScreen(id){
			$('#selectedPreThankYouScreen').val(id);
			$('#thankyoufilesUploadFile').val("");				 
		}	 
				/*  function updateThankYouList(){
					 $('#selectedPreThankYouScreen').val('0');
					 $('.ThankYou').prop('checked', false);
				 } */
				 
				 /* function updateWaterMarkImageList(){
					 $('#selectedPreWaterMarkImage').val('0');
					 $('.WaterMarkImgaeClass').prop('checked', false);
				 } */
				 
				 /* function updatelookAtTouchScreenList(){
					 $('#selectedPreLookAtTouchScreen').val('0');
					 $('.LookAtTouch').prop('checked', false);
				 } */
				 
				 /* function updateCameraTvScreenSaverList(){
					 $('#selectedPreCameraTVScreenSaver').val('0');
					 $('.CameraTVScreenSaverClass').prop('checked', false);
				 } */
				 
	</script>  
	<script type="text/javascript">
		/* $(document).ready(function(){
			var subId= document.getElementById("subid").value;
			if(subId==1){
				$(".demohide").attr('disabled', 'disabled');
				$('.btn-green').attr('disabled', 'disabled');
			}
		});	 */
		
		function updateList() {
	  		var input = document.getElementById('images2');
	  		var output = document.getElementById('fileList');
	  		output.innerHTML = '<ul>';
	  		for (var i = 0; i < input.files.length; ++i) {
	    		output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
	  		}
	  		output.innerHTML += '</ul>';
		}
		
		function updateList1() {
	  		var input = document.getElementById('images1');
	  		var output = document.getElementById('fileList1');
	  		output.innerHTML = '<ul>';
	  		for (var i = 0; i < input.files.length; ++i) {
	    		output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
	  		}
	  		output.innerHTML += '</ul>';
		}
	</script>
	<script type="text/javascript">

		function ButtonClicked(){
			var subId= document.getElementById("subid").value;
			if(subId==1){
				alert("You need to upgrade your subscription to upload custom backgrounds.");
				return false;
			}else{
				var existingBackground=$("#selectedPreImage").val(); 	    
			    var uploadBackground = $('#images2').val();
			    if(existingBackground==0 && uploadBackground==""){
			    	alert("select atleast one background"); 
			      	return false;
			    }
		   		document.getElementById("formsubmitbutton").style.display = "none"; // to undisplay
		   		document.getElementById("buttonreplacement").style.display = ""; // to display
			}
			
		}
		var FirstLoading = true;
		function RestoreSubmitButton(){
	   	if( FirstLoading ) {
	      FirstLoading = false;
	      return;
	   	}
	   document.getElementById("formsubmitbutton").style.display = ""; // to display
	   document.getElementById("buttonreplacement").style.display = "none"; // to undisplay
	}
	// To disable restoring submit button, disable or delete next line.
	document.onfocus = RestoreSubmitButton;
	</script>


	 <script type="text/javascript"> 
					
	function open3() {
	document.getElementById('light').style.display='block';
	document.getElementById('fade').style.display='block';
	document.getElementById('selectedPreImage').value='0';
	$('.checkbox1').removeAttr('checked');

	    	  }
	    	  
	function close1(){   
		   document.getElementById('light').style.display='none';
		   document.getElementById('fade').style.display='none';
		   var output = document.getElementById("result2");
		   $( "#result2" ).empty();
		   var index = 1;
		    var checkedValue = [];
		    var delValue = [];
		    var i=0;
		             $.each($("input[name='imgidss']"), function(){   
		            	 
		            	 if(this.checked){
		              checkedValue.push($(this).val());
		              delValue[i]=index;
		            if($('#selectedPreImage').val()==0)
		            	{
			              $('#selectedPreImage').val(index);
		            	}
		            else
		            	{
		              $('#selectedPreImage').val($('#selectedPreImage').val()+","+index);
		            	}
		            i++;
		            	 }
		              index++;
		             });
		            // alert("My favourite sports are: " + calert("My fa;
		   for(var i = 0; i< checkedValue.length; i++){
		    var div = document.createElement("div"); 
		        div.className = "grid-img event-bg";
		        div.id="abc"+delValue[i];
		        div.innerHTML = "<img class='img-thumbnail' src='" + checkedValue[i] + "'" +
		                  "title=''/><div style='float: right;cursor: pointer;right:10px;'><a href='javascript:void(0);' id='"+delValue[i]+"' onclick ='rem(this.id);' class='remove_pict' >X</a></div>";
		      
		         output.insertBefore(div,null);   
		        
		   }
		   
		 }
		 
		 function rem(re){
			 var ids=  $('#selectedPreImage').val();
			 $('#selectedPreImage').val("");
			 var splitIds=ids.split(",");
			 if(splitIds.length==1)
			 {
			 $('#selectedPreImage').val("0");
			 }
			 for(var i=0;i<splitIds.length;i++)
				 {
				  if(splitIds[i]!=re)
					{
					  $('#selectedPreImage').val($('#selectedPreImage').val()+","+splitIds[i]);
				    }
				 }
			 
			 var str=$('#selectedPreImage').val();
			
			 while(str.charAt(0) === ',')
				 {
				    str = str.substr(1);
				 }
			 
			 $('#selectedPreImage').val(str);
		      $("#abc"+re).remove();
		    }
	</script>

	    <script type="text/javascript"> 
					
	    function openThankYouScreen() {
	    
	    document.getElementById('lightThankYouScreen').style.display='block';
	    document.getElementById('fade').style.display='block';
	    }
	    	  
	    function closeThankYouScreen(){	  
	    document.getElementById('lightThankYouScreen').style.display='none';
	    document.getElementById('fade').style.display='none';
	    document.getElementById("tyPic").style.display='none'; 
	    var output = document.getElementById("reslty");
	    $( "#reslty" ).empty();
		   var checkedValue = [];
		            $.each($("input[name='imgidsty']:checked"), function(){            
		             checkedValue.push($(this).val());
		            });
		          /*   alert("My favourite sports are: " + checkedValue); */
		            if(checkedValue==''){
		            	var div = document.createElement("div");        
		                 div.className = "grid-img event-bg-box";
	                    div.innerHTML = "<h2>End of Session</h2> <div class='event-img-bg'></div>";
	                    div.innerHTML += "<div class='event-img-text'><p>This is the Thank You screen displayed at the conclusion of a successful Booth session.</p> <div class='clearfix'></div></div>";          
		                 
		        output.insertBefore(div,null);   
		       div.children[i].addEventListener("click", function(event){
		           div.parentNode.removeChild(div);
		        });
		            }else{
		  for(var i = 0; i< checkedValue.length; i++){
		   //alert(checkedValue[i]);
		   var div = document.createElement("div");        
		                 div.className = "grid-img event-bg-box";
	                     div.innerHTML = "<h2>End of Session</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + checkedValue[i] + "'/> </div>";
	                     div.innerHTML += "<div class='event-img-text'><p>This is the Thank You screen displayed at the conclusion of a successful Booth session.</p> <div class='clearfix'></div></div>";          
		                 
		        output.insertBefore(div,null);   
		       div.children[i].addEventListener("click", function(event){
		           div.parentNode.removeChild(div);
		        });
		       
		  }
	    }
	    }


		function openwaterMarkImage() {
		document.getElementById('lightwaterMarkImage').style.display='block';
		document.getElementById('fade').style.display='block';
		}
		    	  
		function closewaterMarkImage(){	  		
		document.getElementById('lightwaterMarkImage').style.display='none';
		document.getElementById('fade').style.display='none';
		
		 document.getElementById("wmPic").style.display='none'; 
		    var output = document.getElementById("resl");
		    $( "#resl" ).empty();
			   var checkedValue = [];
			            $.each($("input[name='imgidswm']:checked"), function(){            
			             checkedValue.push($(this).val());
			            });
			           // alert("My favourite sports are: " + checkedValue.join(", "));
			           if(checkedValue==""){
			        	   var div = document.createElement("div");        
	                    	div.className = "grid-img event-bg-box";
	                       div.innerHTML = "<h2>Background Overlay</h2> <div class='event-img-bg'></div>";
	                       div.innerHTML += "<div class='event-img-text'><p>Upload transparent PNG to overlay on top of every Background for this Events (IE logo, picture frame, etc.) Position graphics relative to entire Background (IE logo in bottom right corner on 1920x1080 image.)</p> <div class='clearfix'></div></div>";
		        output.insertBefore(div,null);   
		       div.children[i].addEventListener("click", function(event){
		           div.parentNode.removeChild(div);
		        });
			           }else{
			  for(var i = 0; i< checkedValue.length; i++){
			   //alert(checkedValue[i]);
			   var div = document.createElement("div");        
		                     	div.className = "grid-img event-bg-box";
		                        div.innerHTML = "<h2>Background Overlay</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + checkedValue[i] + "'/> </div>";
		                        div.innerHTML += "<div class='event-img-text'><p>Upload transparent PNG to overlay on top of every Background for this Events (IE logo, picture frame, etc.) Position graphics relative to entire Background (IE logo in bottom right corner on 1920x1080 image.)</p> <div class='clearfix'></div></div>";
			        output.insertBefore(div,null);   
			       div.children[i].addEventListener("click", function(event){
			           div.parentNode.removeChild(div);
			        });
			  	}
			}
		}

		function opencameraTVScreenSaver() {
		document.getElementById('lightcameraTVScreenSaver').style.display='block';
		document.getElementById('fade').style.display='block';
		}
			    	  
		function closecameraTVScreenSaver(){	  
		document.getElementById('lightcameraTVScreenSaver').style.display='none';
		document.getElementById('fade').style.display='none';
		
		document.getElementById("ctvsPic").style.display='none'; 
	    var output = document.getElementById("reslctvs");
	    $( "#reslctvs" ).empty();
		   var checkedValue = [];
		            $.each($("input[name='imgidsts']:checked"), function(){            
		             checkedValue.push($(this).val());
		            });
		           // alert("My favourite sports are: " + checkedValue.join(", "));
		           if(checkedValue==""){
		        	   var div = document.createElement("div");        
		        	   div.className = "grid-img event-bg-box";
		               div.innerHTML = "<h2>Photos Complete</h2> <div class='event-img-bg'></div>";
		               div.innerHTML += "<div class='event-img-text'><p>This image is displayed after Photos are taken, prompting Guests to look at the Touchscreen and choose their Photos/enter email.</p> <div class='clearfix'></div></div>";
		        	        output.insertBefore(div,null);   
		        	       div.children[i].addEventListener("click", function(event){
		        	           div.parentNode.removeChild(div);
		        	        });
		           }else{
		  for(var i = 0; i< checkedValue.length; i++){
		   //alert(checkedValue[i]);
		   var div = document.createElement("div");        
		   div.className = "grid-img event-bg-box";
	       div.innerHTML = "<h2>Photos Complete</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + checkedValue[i] + "'/> </div>";
	       div.innerHTML += "<div class='event-img-text'><p>This image is displayed after Photos are taken, prompting Guests to look at the Touchscreen and choose their Photos/enter email.</p> <div class='clearfix'></div></div>";
		        output.insertBefore(div,null);   
		       div.children[i].addEventListener("click", function(event){
		           div.parentNode.removeChild(div);
		        });
		       
		  			}
		     }
		}
		
		function openlookAtTouchScreen() {
		document.getElementById('lightlookAtTouchScreen').style.display='block';
		document.getElementById('fade').style.display='block';
		}
				    	  
		function closelookAtTouchScreen(){	  
		document.getElementById('lightlookAtTouchScreen').style.display='none';
		document.getElementById('fade').style.display='none';
		
		document.getElementById("atslPic").style.display='none'; 
	    var output = document.getElementById("sver");
	    $( "#sver" ).empty();
		   var checkedValue = [];
		            $.each($("input[name='imgidslts']:checked"), function(){            
		             checkedValue.push($(this).val());
		            });
		           // alert("My favourite sports are: " + checkedValue.join(", "));
		           if(checkedValue==""){
		        	   var div = document.createElement("div");        
		        	   div.className = "grid-img event-bg-box";
		               div.innerHTML = "<h2>Screensaver</h2> <div class='event-img-bg'></div>";
		               div.innerHTML += "<div class='event-img-text'><p>This image is displayed on the main TV (Camera device) while the Booth is not in an active session.</p> <div class='clearfix'></div></div>";
		        	        output.insertBefore(div,null);   
		        	       div.children[i].addEventListener("click", function(event){
		        	           div.parentNode.removeChild(div);
		        	        });
		           }else{
		  for(var i = 0; i< checkedValue.length; i++){
		   //alert(checkedValue[i]);
		   var div = document.createElement("div");        
		   div.className = "grid-img event-bg-box";
	       div.innerHTML = "<h2>Screensaver</h2> <div class='event-img-bg'><img class='event-img-bg' src='" + checkedValue[i]  + "'/> </div>";
	       div.innerHTML += "<div class='event-img-text'><p>This image is displayed on the main TV (Camera device) while the Booth is not in an active session.</p> <div class='clearfix'></div></div>";
		        output.insertBefore(div,null);   
		       div.children[i].addEventListener("click", function(event){
		           div.parentNode.removeChild(div);
		        });
		       
		  }
		           }
		
		}
			
		</script>	
	<style>
		.right-pannel{height:auto !important}
	</style>
	<div class="col-lg-2 col-md-3" style="width:20%;"></div>
		<div class="right-pannel col-lg-10 col-xs-12">
					<h1 class="heading pull-left">Create Events </h1>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<div class="col-row">
							<form action="event-background.html">
								<h1 class="heading" style="margin-bottom:10px;color:#2363c5">Event Details</h1>
								<table class="table table-bordered" style="width:90%">
									<tr>
										<td style="font-weight:600">Event Date</td>
										<td>${event.eventStart}  &nbsp; ${event.eventTimezone}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Event</td>
										<td>${event.eventName}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Name Of Host</td>
										<td>${event.sponsorName}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Event Host Email</td>
										<td>${event.eventHostMailerId}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Facebook</td>
										<td>${event.facebook}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Twitter</td>
										<td>${event.twitter}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Email Body</td>
										<td>${event.emailBody}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Event Type</td>
										<td>${event.eventType}</td>
									</tr>
									<tr>
										<td style="font-weight:600">Enable Subscription</td>
										<td>${event.isSubscribed}</td>
									</tr>
									
								</table>
								</form>
								<div id="light" class="white_content">
  
  
  <div id="fade" class="black_overlay"></div>
								<form:form action="uploadBackGroundImageSA" modelAttribute="AdminPictureVO" onsubmit="return validateUpdateForm();" enctype="multipart/form-data" method="post">
								<input type="hidden" name="eventStart" value="${event.eventStart }"/>
								<input type="hidden" name="eventName" value="${event.eventName}"/>
								<input type="hidden" name="sponsorName" value="${event.sponsorName}"/>
								<input type="hidden" name="eventType" value="${event.eventType}"/>
								<input type="hidden" name="eventHostMailerId" value="${event.eventHostMailerId}"/>
								<input type="hidden" name="facebook" value="${event.facebook}"/>
								<input type="hidden" name="twitter" value="${event.twitter}"/>
								<input type="hidden" name="emailBody" value="${event.emailBody}"/>
								<input type="hidden" name="selectedPreImage" id="selectedPreImage" value="0"/>
								<input type="hidden" name="selectedPreWaterMarkImage" id="selectedPreWaterMarkImage" value="0"/>
								<input type="hidden" name="selectedPreCameraTVScreenSaver" id="selectedPreCameraTVScreenSaver" value="0"/>
								<input type="hidden" name="selectedPreLookAtTouchScreen" id="selectedPreLookAtTouchScreen" value="0"/>
								<input type="hidden" name="selectedPreThankYouScreen" id="selectedPreThankYouScreen" value="0"/>
								
								<div class="form_row" style="margin-top:35px;" >									
								
								<input type="hidden" value="${eid}" name="eId"/>
								<input type="hidden" value="${boothAdminLogin2.subId}" id="subid"/>
								<div class="demohide">
								
								<!-- <div class="upload-bg">
									<div class="form_label" style="width:27%">Upload Custom Background(s)</div>
									<div class="form_element">
										<input type="file" name="files" id="images2" multiple accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateList()" class="demohide" style="margin-bottom:10px;margin-left:-12px;padding-bottom:5px;border-bottom:1px solid #e2e2e2;" />
										<p class="subtext" style="margin-top: -12px;">*press ctrl to select multiple Images</p>
										
									</div>
									<div class="form_label" style="margin:-15px 30px;"><div style="width:275px;margin:20px 0px;"><div data-step="4" data-intro="Submit the images after you have selected them"><input type="submit" value="Upload Now" style="width:auto" class="btn btn-green" data-toggle="tooltip" title="Submit the images after you have selected them" onclick="return ButtonClicked()"></div></div></div>
									<div class="clearfix"></div>
									<a href="javascript:void(0)" class="btn btn-green " style="margin:-40px 40px 0px;width:38%;font-size: 14px;float: right;" onclick="open3();">Choose Background(s) From Library</a>
								</div> -->
								<div class="clearfix"></div>
								
								<h1 class="heading" style="margin:20px 0px 0px;color:#2363c5; border-color: black;border-top: 3px solid #05a42e;padding-top: 26px;">Customized Event Experience</h1>
								<p class="subtext" style="margin-bottom:50px;">Modify screens shown to your guests during their booth session</p>
								</div>
								<div class="grid-row">
									<%-- <div class="grid-img event-bg-box">
										<h2>Background Overlay</h2>
										<div class="event-img-bg">
											<img src="<%=request.getContextPath()%>/resources/images/banner-bg.png" class="img-thumbnail">
										</div>
										<div class="event-img-text">
											<p>hfgdsahfgsif bghsuidn said hs</p>
											<div class="clearfix"></div>
										</div>
										<input type="checkbox">
									</div> --%>
									<div class="grid-img event-bg-box" id='wmPic'>
										        <h2>Background Overlay</h2>
										        <div class="event-img-bg">
										         <img src="<%=request.getContextPath()%>/resources/img/1.png" class="img-thumbnail img-thumbnail1">
										        </div>
										        <div class="event-img-text">
										         <p>Upload transparent PNG to overlay on top of every Background for this Events (IE logo, picture frame, etc.)<br/>
										         Position graphics relative to entire Background (IE logo in bottom right corner on 1920x1080 image.)</p>
										         <div class="clearfix"></div>
										        </div>
										        <!-- <input type="checkbox"> -->
									</div> 
									<div id="resl"></div>     
										
									<div class="grid-img event-bg-box" id="ctvsPic">
										<h2>Screensaver</h2>
										<div class="event-img-bg">
											<img src="<%=request.getContextPath()%>/resources/img/2.png" class="img-thumbnail img-thumbnail1">
										</div>
										<div class="event-img-text">
											<p>This image is displayed on the main TV (Camera device) while the Booth is not in an active session.</p>
											<div class="clearfix"></div>
										</div>
										<!-- <input type="checkbox"> -->
									</div>
									<div id="sver"></div>
									
									<div class="grid-img event-bg-box" id="atslPic">
									<h2>Photos Complete</h2>
										<div class="event-img-bg">
											<img src="<%=request.getContextPath()%>/resources/img/3.png" class="img-thumbnail img-thumbnail1">
										</div>
										<div class="event-img-text">
											<p>This image is displayed after Photos are taken, prompting Guests to look at the Touchscreen and choose their Photos/enter email.</p>
											<div class="clearfix"></div>
										</div>
										<!-- <input type="checkbox"> -->
									</div>
									<div id="reslctvs"></div>
										
									<div class="grid-img event-bg-box" id='tyPic'>
									<h2>End of Session</h2>
										<div class="event-img-bg">
											<img src="<%=request.getContextPath()%>/resources/img/4.png" class="img-thumbnail img-thumbnail1">
										</div>
										<div class="event-img-text">
											<p>This is the Thank You screen displayed at the conclusion of a successful Booth session.</p>
											<div class="clearfix"></div>
										</div>
										<!-- <input type="checkbox"> -->
									</div>
									<div id="reslty"></div>
								
								<div class="clearfix"></div>
									<!-- <div id="fileList"></div> -->
									
									<div class="form-row upload-screens">

										<div>
											<input type="file" name="thankyoufiles" id="thankyoufilesUploadFile" class="demohide"  accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateThankYouList()"  />
											<div class="clearfix"></div>
											<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 11px" onclick="openThankYouScreen();" >Choose End of Session From Library</a> -->
										</div>
										
										<div>
											<input type="file" name="lookAtTouchScreen" id="lookAtTouchScreenUploadFile" class="demohide" accept=".jpg,.png,.gif,.bmp" onchange="javascript:updatelookAtTouchScreenList()" />
											<div class="clearfix"></div>
								        	<!-- <a href="javascript:void(0)" class="btn btn-green"  style="font-size: 12px"  onclick="openlookAtTouchScreen();">Choose Screensaver From Library</a> -->
								        	<div class="clearfix"></div>
										</div>
										
										 <div>
								         	<input type="file" name="cameraTVScreenSaver" id="cameraTVScreenSaverUploadFile" class="demohide" accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateCameraTvScreenSaverList()" />
								        	<div class="clearfix"></div>
								        	<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 11px"   onclick="opencameraTVScreenSaver();" >Choose Photos Complete From Library</a> -->
								         </div>
										
									<div>
										<input type="file" name="waterMarkImage" id="waterMarkImageUploadFile" class="demohide" accept=".jpg,.png,.gif,.bmp" onchange="javascript:updateWaterMarkImageList()"  />
										<div class="clearfix"></div>
										
								        	<!-- <a href="javascript:void(0)" class="btn btn-green" style="font-size: 10px"   onclick="openwaterMarkImage();"  >Choose Background Overlay From Library</a> -->
									</div>
										<div class="clearfix"></div>
									</div>
									<div class="clearfix"></div>		
								        
									<div id="formsubmitbutton">
									</div>
									
									<div class="clearfix"></div>
									</div>
									<div class="clearfix"></div>
									</div>
									<div data-step="4" data-intro="Submit the images after you have selected them"><input type="submit" value="Save & Continue" style="width:auto" class="btn btn-green pull-right" data-toggle="tooltip" title="Submit the images after you have selected them" onclick="return ButtonClicked()"><input type="reset" style="width:125px;margin-right: 10px;padding:10px 0px 10px 0px;" class="btn btn-green pull-right" value="Reset" onClick="window.location.reload()"></div>
									<div id="buttonreplacement" class="pull-right" style="margin: 5px -220px;display:none;">
										<img src="https://www.willmaster.com/images/preload.gif" alt="loading...">
									</div>
									</form:form>
									<div class="clearfix"></div>
									</div>
									
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
