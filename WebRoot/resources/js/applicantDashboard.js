	  
 

function validateAndAddDeal()
{
	var name = dwr.util.getValue("name");
	var noOfTokens = dwr.util.getValue("noOfTokens");
	var description = dwr.util.getValue("description");
	var placeId = dwr.util.getValue("placeId");
	var subtitle = dwr.util.getValue("subtitle");

	var errMessage = "";
	
	if(name == null || name.trim() == '')
		{
			errMessage += "Name is required.<br>";
		}
	if(noOfTokens == null || noOfTokens.trim() == '')
		{
			errMessage += "No of tokens is required.<br>";
		}
	else
		{
		if(isNaN(noOfTokens))
			{
			errMessage += "No of tokens should be numeric.<br>";
			}
		}

	
	if(errMessage != "")
		{		
			document.getElementById("popmsg").innerHTML = '<div class="error-msg1">'+errMessage+'</div>';
			$(".error-msg1").fadeOut(5000);
		}
	else
		{
		
			dwrService.addDeal(name, subtitle, noOfTokens,description, placeId, function(data){
				

					if(data)
						{
						document.getElementById("msgs").innerHTML = '<div class="success-msg1"><span> Deal added successfully.</span></div>';
						$(".successpop-msg1").fadeOut(5000);
						closepopup('myModal');
						}
					else
						{
						document.getElementById("msgs").innerHTML = '<div class="errorfull-msg1"><span> Deal can not be added.</span></div>';
						$(".errorfull-msg1").fadeOut(5000);
						closepopup('adddeal');
						}
			});
		}
}

function setPlaceIdAndOpenPopup(id, placeId)
{
	document.getElementById('placeId').value=placeId;
	openpopup(id);
}

function refreshList()
{
	//window.location.href = "./refreshplacelist";
	
	  var form = document.createElement("form");
    form.setAttribute("action", "refreshplacelist");


    document.body.appendChild(form);
    form.submit();
}

function deleteApplication(trackingId)
{
	window.location.href = "./deleteApplication?trackingId="+trackingId;
}

function openEditdealpopup(id, dealId)
{
			dwrService.getDealDetailById(dealId, function(data){
			document.getElementById("name").value = data.name;
			document.getElementById("noOfTokens").value = data.noOfToken;
			document.getElementById("description").value = data.description;
			document.getElementById("dealId").value = data.dealId;
			document.getElementById("subtitle").value = data.subTitle;
			openpopup(id);
			});
		
			
}

function validateAndUpdatedeal()
{
	var name = dwr.util.getValue("name");
	var noOfTokens = dwr.util.getValue("noOfTokens");
	var description = dwr.util.getValue("description");
	var dealId = dwr.util.getValue("dealId");
	var subtitle = dwr.util.getValue("subtitle");

	
	var errMessage = "";
	
	if(name == null || name.trim() == '')
		{
			errMessage += "Name is required.<br>";
		}
	if(noOfTokens == null || noOfTokens.trim() == '')
		{
			errMessage += "No of tokens is required.<br>";
		}
	else
		{
		if(isNaN(noOfTokens))
			{
			errMessage += "No of tokens should be numeric.<br>";
			}
		}

	
	if(errMessage != "")
		{		
			document.getElementById("popmsg").innerHTML = '<div class="error-msg1">'+errMessage+'</div>';
			$(".error-msg1").fadeOut(5000);
		}
	else
		{
		
			dwrService.updateDeal(dealId, subtitle, name, description , noOfTokens, function(data){
				

					if(data)
						{
						document.getElementById("msgs").innerHTML = '<div class="success-msg1"><span> Deal updated successfuly.</span></div>';
						$(".success-msg1").fadeOut(5000);
						closepopup('myModal');
						document.getElementById('dealId_'+dealId).innerHTML='<td>'+name+'</td>'+
								'<td>'+noOfTokens+'</td>'+
								'<td>'+description+'</td>'+
								'<td> <a onclick=openEditdealpopup("myModal",'+dealId+')> <img alt="Add Deal"  style="width: 20px; height: 20px;" src="resources/images/edit_deal.png"> </a>  </td>';
						}
					else
						{
						document.getElementById("msgs").innerHTML = '<div class="errorfull-msg1"><span> Deal can not be updated.</span></div>';
						$(".errorfull-msg1").fadeOut(5000);
						closepopup('myModal');
						}
			});
		}
}

function showCutomResolutionDiv()
{
	var resolutionId = dwr.util.getValue("resolutionId");
	if(resolutionId == '0')
		{
		 $("#customresolution").removeClass("ratio");
		 $("#resolutionImage").addClass("ratio");
		 $("#submitButton").attr('onClick', "addResolution();");
			document.getElementById("filepopmsg").innerHTML = '';
		}
	else
		{
		$("#customresolution").addClass("ratio");
		$("#resolutionImage").removeClass("ratio");
		$("#submitButton").attr('onClick', "updateImageForResolution();");
		document.getElementById("filepopmsg").innerHTML = '';
		}
}

function addResolution()
{
	var width = dwr.util.getValue("width");
	var height = dwr.util.getValue("height");
	var errmessage = '';
		if(height.trim() == '')
		{
		errmessage='Height is required.';
		}
	else
		{
		if(isNaN(height))
			{
			errmessage='Height must be numeric.';
			}
		}
		
	if(width.trim() == '')
		{
		errmessage='Width is required.';
		}
	else
		{
		if(isNaN(width))
			{
			errmessage='Width must be numeric.';
			}
		}

	if(errmessage != "")
		{		
			document.getElementById("filepopmsg").innerHTML = '<div class="error-msg1">'+errmessage+'</div>';
			$(".error-msg1").fadeOut(5000);
		}
	else
		{
		dwrService.addResolution(width, height, function(data){
				
					if(data.message == 'Resolution added successfully')
						{
						document.getElementById("filepopmsg").innerHTML = '<div class="successpop-msg1">Resolution added successfully.</div>';
						$(".successpop-msg1").fadeOut(5000);
						dwr.util.removeAllOptions("resolutionId");
 							  var selectTest = document.getElementById('resolutionId');
  							 selectTest.options[selectTest.options.length] = new Option(
   									  'Select Resolution', '');
   							dwr.util.addOptions("resolutionId", data.resolutionVOs);
   							selectTest.options[selectTest.options.length] = new Option(
   									  'Custom Resolution', '0');
   							document.getElementById('height').value='';
   							document.getElementById('width').value='';
   						
   						}
					if(data.message == 'Custom resolution already exists')
						{
						document.getElementById("filepopmsg").innerHTML = '<div class="error-msg1">Custom resolution already exists.</div>';
						$(".error-msg1").fadeOut(5000);
   						}
					if(data.message == 'Resolution can not be added.')
						{
						document.getElementById("filepopmsg").innerHTML = '<div class="error-msg1">Resolution can not be added.</div>';
					$(".error-msg1").fadeOut(5000);
   						}
			});
		}
}

function updateImageForResolution()
{
	var resolutionId = dwr.util.getValue("resolutionId");
	var resolutionImageName = document.getElementById('resolutionImageId').value;
	var resolutionImage = dwr.util.getValue("resolutionImageId");
	var errmessage = '';
	if(resolutionImageName =='')
		{
		errmessage='Resolution Image is required.';
		}
	else
		{
		if (!isImage(resolutionImageName))
    	errmessage ='File in not an image.';
		}
	if(resolutionId =='')
		{
		errmessage='Resolution Id is required.';
		}
	if(errmessage != "")
		{		
			document.getElementById("filepopmsg").innerHTML = '<div class="error-msg1">'+errmessage+'</div>';
			$(".error-msg1").fadeOut(5000);
		}
	else
		{
		dwrService.updateSplashScreen(resolutionId, resolutionImage, resolutionImageName, function(data){
				
			if(data)
				{
				document.getElementById("filepopmsg").innerHTML = '<div class="successpop-msg1">Image updated successfully.</div>';
				$(".successpop-msg1").fadeOut(5000);
				}
			else
				{
				document.getElementById("filepopmsg").innerHTML = '<div class="error-msg1">Image can not update.</div>';
				$(".error-msg1").fadeOut(5000);
				}
				});
		
		}
}
function isImage(filename) {
    var ext = getExtension(filename);
    switch (ext.toLowerCase()) {
    case 'jpg':
    case 'gif':
    case 'bmp':
    case 'png':
        return true;
    }
    return false;
}
function getExtension(filename) {
    var parts = filename.split('.');
    return parts[parts.length - 1];
}
function resetfilepopup()
{ 
   	document.getElementById("filepopmsg").innerHTML = '';
	
	document.getElementById('filepopmsgform').reset();
}

function beforeReset()
{
	document.getElementById("popmsg").innerHTML = '';
}
