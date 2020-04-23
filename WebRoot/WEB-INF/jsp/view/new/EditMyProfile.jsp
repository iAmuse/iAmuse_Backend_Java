		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
		<style>
      	/*.right-pannel{height:auto !important;}*/
      	/*.left-pannel{
  min-height:750px !important;}*/
      </style>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/editProfileValidation.js">  </script>
	<div class="col-lg-2 col-md-3" style="width:20%;"></div>
		<div class="col-lg-10 col-xs-12 col-md-9 right-pannel right-height">
		<input type="hidden" id="userTypeVal" value="${boothAdminLogin2.userType}">
					<h1 class="heading pull-left" style="padding-top:15px;">Edit Profile</h1>
					<div class="clearfix"></div>
					<div class="inner-content profile-content" >
					<form:form action="updateProfileDetails" modelAttribute="SignInVO" onsubmit="return formValidation()" enctype="multipart/form-data" autocomplete="off">
						<c:if test="${not empty successMessage}">
					<div id="effect"  class=""><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class=""><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
						<div class="col-row">
						<c:if test="${not empty boothAdminLogin2.image}">
							<div class="profile-img">
							<img src="<%=request.getContextPath()%>/imageDisplay?id=${boothAdminLogin2.userId}">
							<%-- <img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png"> --%>
							<input type="file" name="file" size="50" style="margin-top:30px;"  />
							</div>
							</c:if>
							
							<c:if test="${empty boothAdminLogin2.image}">
							<div class="profile-img">
							<img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png">
							<input type="file" name="file" size="50" style="margin-top:30px;"/>
							</div>
							</c:if>
							<div class="profile-detail">
								<div class="form_row detarow">
									<div class="form_label labrow">First Name</div> 
									<div class="form_element inputrow"><input type="text" placeholder="Mike" name="username" id="username" value="${boothAdminLogin2.username}"></div><span style="color:red" id="usernameSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row detarow">
									<div class="form_label labrow">Last Name</div> 
									<div class="form_element inputrow"><input type="text" placeholder="Mike" name="lastname" id="username" value="${boothAdminLogin2.lastname}"></div><span style="color:red" id="usernameSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row detarow">
									<div class="form_label labrow">Email Id</div>
									<div class="form_element inputrow"><input type="text" placeholder="mikenew@gmail.com"  name="emailId" value="${boothAdminLogin2.emailId}" readonly="readonly"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row detarow">
									<div class="form_label labrow">Phone Number</div>
									<div class="form_element inputrow"><input type="text" placeholder="+109993669" id="contactNumber" name="contactNumber" value="${boothAdminLogin2.contactNumber}" maxlength="15" onkeypress="return onlyNos(event,this);"></div><span style="color:red" id="contactNumberSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row detarow">
									<div class="form_label labrow">Booth Security PIN</div>
									<div class="form_element inputrow"><input type="text" placeholder="4-Digit PIN" id="PinNumber" name="pin" value="${boothAdminLogin2.pin}" maxlength="4" onkeypress="return onlyNos(event,this);"></div><span style="color:red" id="PinNumberSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row detarow">
								<div class="form_label labrow">User Type</div>
								<select id="userType" name="userType" class="form_element inputrow">
					<option value="Unknown">Unknown</option>
					<option value="Do-It-Yourselfer">Do-It-Yourselfer</option>
					<option value="Event Professional">Event Professional</option>
					<option value="Tradeshow">Tradeshow</option>
					<option value="Tourism">Tourism</option>
					<option value="Venue">Venue </option>
					</select>
								<div class="clearfix"></div>
								</div>
								<div class="form_row detarow">
									<div class="form_label labrow1">&nbsp;</div>
									<div class="form_element"><input type="submit" class="btn btn-green inputrow1"  value="Save">   
									
									</div>
									<div class="clearfix"></div>
							</div>							
							</div>
							<div class="clearfix"></div>
						</div>
						</form:form>
					</div>
				</div>
				<div class="clearfix"></div>
				<script language="Javascript" type="text/javascript">
        function onlyNos(e, t) {
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
               if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
            }
            catch (err) {
                alert(err.Description);
            }
        }
        
        var objSelect = document.getElementById("userType");
        var selectedVal= document.getElementById("userTypeVal").value;
        setSelectedValue(objSelect,selectedVal);

    function setSelectedValue(selectObj, valueToSet) {
        for (var i = 0; i < selectObj.options.length; i++) {
            if (selectObj.options[i].text== valueToSet) {
                selectObj.options[i].selected = true;
                return;
            }
        }
    }
 
    </script>