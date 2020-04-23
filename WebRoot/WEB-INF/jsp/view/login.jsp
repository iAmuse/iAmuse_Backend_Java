<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>
<script>

function validatelogin()
{

	var emailid = document.getElementById("username").value;
	var password = document.getElementById("password").value;

	if(emailid.trim()=='')
		{
		alert('Please Enter Your User Name');
		return false;
		}
	if(password.trim()=='')
		{
		alert('Please Enter Your Password');
		return false;
		}
	
	return true;
}
</script>
<% String contextPath = request.getContextPath();
  

String cap="12345";  



%>
<div class="imageholder">
	
    <div class="container">  
        
					
				
                     <c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
				
       
       	<form:form  action="loginaction"  method="post" modelAttribute="loginVO" cssClass="form-signin" onsubmit="return validatelogin();">        
       		
       		<h2 class="form-signin-heading">Please sign in</h2>
                      
            <form:input  path="username" id="username" maxlength="50" cssClass="form-control"  placeholder="Username" />
            
            
            <form:password   path="password" id="password" maxlength="20" cssClass="form-control"  placeholder="Password" />
            
		    <label class="checkbox">
        	 <input type="checkbox" value="remember-me"> Remember me
       		 </label>    
		     
            <input type="submit" class="btn btn-lg btn-theme btn-block"  value="Submit" />
           
         </form:form>
        
       
        
           
     

</div>   				
       
       
       
        
           
     </div><!-- -container ends -->
 