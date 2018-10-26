		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
		<style>
      	.right-pannel{height:auto !important;}
      </style>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script/editProfileValidation.js">  </script>
		<div class="right-pannel">
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
						<c:if test="${not empty boothAdminLogin.image}">
							<div class="profile-img">
							<img src="<%=request.getContextPath()%>/imageDisplay?id=${boothAdminLogin.userId}">
							<%-- <img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png"> --%>
							<input type="file" name="file" size="50" style="margin-top:30px;"  />
							</div>
							</c:if>
							
							<c:if test="${empty boothAdminLogin.image}">
							<div class="profile-img">
							<img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png">
							<input type="file" name="file" size="50" style="margin-top:30px;"/>
							</div>
							</c:if>
							<div class="profile-detail" style="padding-top:0px;float:right">
								<div class="form_row">
									<div class="form_label">Name</div>
									<div class="form_element"><input type="text" placeholder="Mike" name="username" id="username" value="${boothAdminLogin.username}"></div><span style="color:red" id="passwordSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Email Id</div>
									<div class="form_element"><input type="text" placeholder="mikenew@gmail.com"  name="emailId" value="${boothAdminLogin.emailId}" readonly="readonly"></div>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">Phone Number</div>
									<div class="form_element"><input type="text" placeholder="+109993669" id="contactNumber" name="contactNumber" value="${boothAdminLogin.contactNumber}" maxlength="15" onkeypress="return onlyNos(event,this);"></div><span style="color:red" id="contactNumberSpan"></span>
									<div class="clearfix"></div>
								</div>
								<div class="form_row">
									<div class="form_label">&nbsp;</div>
									<div class="form_element"><input type="submit" class="btn btn-green" style="width:auto;" value="Save">
									
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
 
    </script>