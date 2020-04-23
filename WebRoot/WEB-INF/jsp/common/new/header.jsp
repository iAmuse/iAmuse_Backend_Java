<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> -->
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> 
                                                                                                      
<script type='text/javascript'>
(function (d, t) {
  var bh = d.createElement(t), s = d.getElementsByTagName(t)[0];
  bh.type = 'text/javascript';
  bh.src = 'https://www.bugherd.com/sidebarv2.js?apikey=j6gzrnzzsubeg3xkgye1zw';
  s.parentNode.insertBefore(bh, s);
  })(document, 'script');
</script>
<script>

function myFunction() {
    $('.logout-section').css('display','none');
}

 

</script>
<!-- <script async src="https://www.googletagmanager.com/gtag/js?id=G-NK1LDT7CR4"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

 

  gtag('config', 'G-NK1LDT7CR4');
</script>
 --> 

<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>

 


        <div class="header row-class desktop-header">
        			<div class=" col-lg-2 col-xs-4 col-sm-3 col-md-3 headercls" style="text-align:center;background: #fff;">
                    </div>
                    <div class=" col-lg-2 col-xs-4 col-sm-3 col-md-3 logo-div" style="text-align:center;background: #fff;width:20%;padding-bottom: 1%;">
                        <img src="<%=request.getContextPath()%>/resources/images/images/iamuse-email-logo.png" class="logo-class">
                    </div>
                    
                    <div style="background-color:#05a42e;text-align:right;width:80%;" class="col-lg-10 col-sm-9 col-xs-4 col-md-9 boothProfile-pic">
                    <div style="display:flex;float: right;">
                    <span style="padding-right:8px;color:#fff;margin-top:16px;"class="booth-name">
                            ${boothAdminLogin.username}
                        </span>
                            <c:if test="${not empty boothAdminLogin.image}">
                             <div class="dropdown" style="float:right;">
                       <a href="#" class="dropdown-toggle" type="button" data-toggle="dropdown">
                       <img  src="<%=request.getContextPath()%>/imageDisplay?id=${boothAdminLogin.userId}" style="width:53px;height:53px;border-radius:100%;padding:6px;margin-right:15px;">
                    <ul class="dropdown-menu dropdown-menu-right profile-dropdown">
                      <li><a href="getProfileDetails">View Profile</a></li>
                       <li><a href="<%=request.getContextPath()%>/driveupload" >Storage Account</a></li>
                          <li><a href="changeOldPassword" >Change Password</a></li>
                          <li><a href="#" onclick="signOut();">Logout</a></li>
                            </ul></div>
                              </c:if>
                            
                            <c:if test="${empty boothAdminLogin.image}">
                            <div class="dropdown" style="float:right;">
                           <a href="#" class="dropdown-toggle" type="button" data-toggle="dropdown">
                      <img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png" class="usericon" style="width:53px;height:53px;border-radius:100%;padding:6px;margin-right:15px;">
                    <ul class="dropdown-menu dropdown-menu-right profile-dropdown">
                      <li><a href="getProfileDetails">View Profile</a></li>
                          <li><a href="<%=request.getContextPath()%>/driveupload" >Storage Account</a></li>
                          <li><a href="changeOldPassword" >Change Password</a></li>
                          <li><a href="#" onclick="signOut();">Logout</a></li> 
                            </ul></div>
                              </c:if>
                            </div></div>
                           
                        <!--<div class="clearfix"></div>
                    
                    <div class="clearfix"></div>-->
                
            </div>
            
         <div class="header row-class mobile-header">
        			<div class=" col-lg-2 col-xs-4 col-sm-3 col-md-3 headercls" style="text-align:center;background: #fff;">
                    </div>
                    <div class=" col-lg-2 col-xs-4 col-sm-3 col-md-3" style="text-align:center;background: #fff;">
                        <img src="<%=request.getContextPath()%>/resources/images/images/iamuse-email-logo.png" class="logo-class">
                    </div>
                    
                    <div style="background-color:#05a42e;text-align:right;" class="col-lg-10 col-sm-9 col-xs-4 col-md-9 boothProfile-pic">
                    <div style="display:flex;float: right;">
                    <span style="padding-right:8px;color:#fff;margin-top:16px;"class="booth-name">
                            ${boothAdminLogin.username}
                        </span>
                            <c:if test="${not empty boothAdminLogin.image}">
                             <div class="dropdown" style="float:right;">
                        <a href="#" class="dropdown-toggle" type="button" data-toggle="dropdown">
                       <img  src="<%=request.getContextPath()%>/imageDisplay?id=${boothAdminLogin.userId}" style="width:53px;height:53px;border-radius:100%;padding:6px;margin-right:15px;">
                    <ul class="dropdown-menu dropdown-menu-right profile-dropdown">
                      <li><a href="getProfileDetails">View Profile</a></li>
                      <li><a href="changeOldPassword" >Storage Account</a></li>
                          <li><a href="changeOldPassword" >Change Password</a></li>
                          <li><a href="#" onclick="signOut();">Logout</a></li>
                            </ul></div>
                              </c:if>
                            
                            <c:if test="${empty boothAdminLogin.image}">
                            <div class="dropdown" style="float:right;">
                           <a href="#" class="dropdown-toggle" type="button" data-toggle="dropdown">
                      <img src="<%=request.getContextPath()%>/resources/images/images/admin_change.png" class="usericon" style="width:53px;height:53px;border-radius:100%;padding:6px;margin-right:15px;">
                    <ul class="dropdown-menu dropdown-menu-right profile-dropdown">
                      <li><a href="getProfileDetails">View Profile</a></li>
                      <li><a href="<%=request.getContextPath()%>/driveupload" >Storage Account</a></li>
                          <li><a href="changeOldPassword" >Change Password</a></li>
                          <li><a href="#" onclick="signOut();">Logout</a></li> 
                            </ul></div>
                              </c:if>
                            </div></div>
                           
                        <!--<div class="clearfix"></div>
                    
                    <div class="clearfix"></div>-->
                
            </div>


<script>
function signOut(){
//window.location.href='https://admin.iamuse.com';
 window.location.href='SignOut';
   }
/*$(document).ready(function(){

 

        $(window).resize(function() {
            var header_height=$('.header').height();
            //alert(header_height);
            $('.right-pannel').height($(window).height() - (header_height+50));
            $('.logout-section').height($(window).height() - (header_height));
        });
        $(window).trigger('resize');
        $('.drop-down').click(function(){
            $('.logout-section').toggle();            
        });
        $('.logout-box ul li a').click(function(){
            $('.logout-section').hide();
        });
})*/
</script>