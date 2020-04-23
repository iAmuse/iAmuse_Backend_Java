$(document).ready(function(){
     
     // client id of the project
	 var OclientId = "8116fbdf-22b0-4798-98ed-1e270e813f49";
     var clientId = "949527443986-vlbpc7s8bvt7vlgfnlnrkpcs39q729mh.apps.googleusercontent.com";

     //var Oredirect_uri = "http://localhost:8080/iamuse/driveupload";
     //var redirect_uri = "http://localhost:8080/iamuse/driveupload";
     var Oredirect_uri = "https://admin.iamuse.com/iamuse/driveupload";
     var redirect_uri = "https://admin.iamuse.com/iamuse/driveupload";
	
     // scope of the project
//     var Oscope = "Files.Read.All, Files.ReadWrite.All, Sites.Read.All, Sites.ReadWrite.All offline_access";
     var Oscope = "Files.Read.All, Files.ReadWrite.All, Sites.Read.All, Sites.ReadWrite.All offline_access";
     var scope = "https://www.googleapis.com/auth/photoslibrary.readonly.appcreateddata https://www.googleapis.com/auth/photoslibrary.appendonly";
     var Ourl = "";
     var url = "";
     $(".redirectToGooglePhotos").click(function(){
    	 
        signIn(clientId,redirect_uri,scope,url);
     });
     function signIn(clientId,redirect_uri,scope,url){
    	 
        url = "https://accounts.google.com/o/oauth2/v2/auth?redirect_uri="+redirect_uri
        +"&prompt=consent&response_type=code&client_id="+clientId+"&scope="+scope
        +"&access_type=offline";

        localStorage.setItem("loginType","Google");
        window.location = url;
     }
     $(".redirectToOneDrive").click(function(){ 
         OsignIn(OclientId,Oredirect_uri,Oscope,Ourl);
      });
      function OsignIn(clientId,redirect_uri,scope,url){
    	  
         url = "https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id="+clientId
         +"&response_type=code&redirect_uri="+redirect_uri+"&scope="+scope+"&state=270595";
         localStorage.setItem("loginType","Outlook");
         window.location = url;
         
      }
});