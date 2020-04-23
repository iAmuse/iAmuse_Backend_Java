
$(document).ready(function(){
  
	var loginType=localStorage.getItem("loginType");
	
	if(loginType===undefined)
	{
		return;
	}
	if(loginType==="Google")
	{
		
	
		 	const urlParams = new URLSearchParams(window.location.search);
		    const code = urlParams.get('code');
		    //const redirect_uri = "http://localhost:8080/iamuse/driveupload" // replace with your redirect_uri;
		    const redirect_uri = "https://admin.iamuse.com/iamuse/driveupload"
		    const client_secret = "JsyrJtqLEhZ3VUttntZntD_9"; // replace with your client secret
		    const scope = "https://www.googleapis.com/auth/photoslibrary.readonly.appcreateddata https://www.googleapis.com/auth/photoslibrary.appendonly";
		    var access_token= "";
		    var client_id = "949527443986-vlbpc7s8bvt7vlgfnlnrkpcs39q729mh.apps.googleusercontent.com"// replace it with your client id;

		    $.ajax({
		        type: 'POST',
		        url: "https://www.googleapis.com/oauth2/v4/token",
		        data: {code:code
		            ,redirect_uri:redirect_uri,
		            client_secret:client_secret,
		        client_id:client_id,
		        scope:scope,
		        grant_type:"authorization_code"},
		        success: function(resultData) {
		           localStorage.setItem("accessToken",resultData.access_token);
		           localStorage.setItem("refreshToken",resultData.refresh_token);
		       //    console.log("upload page in call"+resultData.access_token);
		           localStorage.setItem("expires_in",resultData.expires_in);
		           localStorage.setItem("loginType","");
		          
		           var data = JSON.stringify({
		        	  "userId":document.getElementById('userId').value,
		        	   "access_token": resultData.access_token,
		        	   "refresh_token": resultData.refresh_token,
		        	   "flag":0
		        	 });
		           
		           deleteAccessToken(userId);
		          
		        	 var xhr = new XMLHttpRequest();
		        	 xhr.withCredentials = true;

		        	 xhr.addEventListener("readystatechange", function () {
		        	   if (this.readyState === 4) {
		        		  
		        		 window.location="driveupload";
		        		 
		        	   }
		        	 });

		        	 //xhr.open("POST", "http://localhost:8080/iamuse/accesstoken");
		        	 xhr.open("POST", "https://admin.iamuse.com/iamuse/accesstoken");
		        	 xhr.setRequestHeader("accept", "application/json");
		        	 xhr.setRequestHeader("authorization", "DrEgBqmYbTXJqi2/a/H9O9YLYcRNjNTNn89BKpui1Y8");
		        	 xhr.setRequestHeader("content-type", "application/json");
     	        	 xhr.send(data);
		           
		          // window.history.pushState({}, document.title, "/upload/" + "upload.jsp");		          
		        }
		  });
	}
	if(loginType==="Outlook")
	{
		
		const OurlParams = new URLSearchParams(window.location.search);
	    const Ocode = OurlParams.get('code');
	    //alert(Ocode);
	    //const Oredirect_uri = "http://localhost:8080/iamuse/driveupload";
	    const Oredirect_uri = "https://admin.iamuse.com/iamuse/driveupload";
	    const Oclient_secret = "WlbS+-T5C/V-l9GBoomjxhLPEAXvB45X";
//	    const Oscope = "Files.ReadWrite Files.ReadWrite.All offline_access";
	    const Oscope = "Files.Read.All, Files.ReadWrite.All, Sites.Read.All, Sites.ReadWrite.All offline_access";
	    var access_token= "";
	    var Oclient_id = "8116fbdf-22b0-4798-98ed-1e270e813f49";
	    //var Odata = "client_id=8116fbdf-22b0-4798-98ed-1e270e813f49&scope=Files.ReadWrite%20Files.ReadWrite.All%20offline_access&code="+Ocode+"&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fiamuse%2Fdriveupload&grant_type=authorization_code&client_secret=yxR_%3AAspPCUsSzm95%3DlB2uZN*7n67T4x";
	    var Odata = "client_id=8116fbdf-22b0-4798-98ed-1e270e813f49&scope=Files.ReadWrite%20Files.ReadWrite.All%20offline_access&code="+Ocode+"&redirect_uri=https://admin.iamuse.com/iamuse/driveupload&grant_type=authorization_code&client_secret=yxR_%3AAspPCUsSzm95%3DlB2uZN*7n67T4x";

	//    var xhr = new XMLHttpRequest();	
//    xhr.withCredentials = false;
  //  xhr.addEventListener("readystatechange", function () {
	//      if (this.readyState === 4) {
    	 
	    
	//    	  localStorage.setItem("loginType","");
	//        console.log(this.responseText);	      }
	//    });

	//    xhr.open("POST", "https://login.microsoftonline.com/common/oauth2/v2.0/token");
	//    xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
	//    xhr.setRequestHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	//    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	//    xhr.setRequestHeader("cache-control", "no-cache");
	//    xhr.setRequestHeader("postman-token", "d6b6a225-9710-6a29-caec-8d0a3d91523b");
//
	//    xhr.send(data);
	    
	    
	    
//	    var data = JSON.stringify({
   //   	  "userId":document.getElementById('userId').value,
  //    	   "access_token": resultData.access_token,
   //   	   "refresh_token": resultData.refresh_token,
  //    	   "flag":1
  //    	 });
	 	    $.ajax({		    
		        type: 'POST',
	            async:false,
	            crossDomain:true,
		        url: "https://cors-anywhere.herokuapp.com/https://login.microsoftonline.com/common/oauth2/v2.0/token",
		        xhr: function () {
	                var myXhr = $.ajaxSettings.xhr();
	                return myXhr;
	            },
	            "headers": {  
	                "content-type": "application/x-www-form-urlencoded"  
	            }, 
	            "data": {  
	                "grant_type": "authorization_code",  
	                "client_id ": "8116fbdf-22b0-4798-98ed-1e270e813f49", //Provide your app id    
	                "code": Ocode, //Provide your client secret genereated from your app
	                "scope": Oscope ,
	                "redirect_uri":Oredirect_uri,
	                "client_secret":Oclient_secret
	            }, 
	            //data:Odata,
//		        data: {code:Ocode,
//		        	   redirect_uri:Oredirect_uri,
//		        	   client_secret:Oclient_secret,
//		        	   client_id:Oclient_id,
//		        	   scope:Oscope,
//		        	   grant_type:"authorization_code"},
		        success: function(resultData) {
		        	console.log("54545464657"+resultData.access_token);
		        	localStorage.setItem("loginType","");
		        	
		           var data = JSON.stringify({
			        	  "userId":document.getElementById('userId').value,
			        	   "access_token": resultData.access_token,
			        	   "refresh_token": resultData.refresh_token,
			        	   "flag":1
			        	 });
		           
		           	//deleteAccessToken(userId);
			        	 var xhr = new XMLHttpRequest();
			        	 xhr.withCredentials = true;

			        	 xhr.addEventListener("readystatechange", function () {
			        	   if (this.readyState === 4) {
			        		 
			        	     window.location="driveupload";
			        	   }
			        	 });
			        	//xhr.open("POST","http://localhost:8080/iamuse/accesstoken");
			        	xhr.open("POST","https://admin.iamuse.com/iamuse/accesstoken");
			    //    	 xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
			    //    	 xhr.setRequestHeader("Access-Control-Allow-Headers", "Content-Type");
			   //     	 xhr.setRequestHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
			        //	 https://cors.io/http://localhost:8080/iamuse
			        	// xhr.setRequestHeader("Origin","http://localhost:8080/iamuse");
			        	 xhr.setRequestHeader("accept", "application/json");
			        	 xhr.setRequestHeader("authorization", "DrEgBqmYbTXJqi2/a/H9O9YLYcRNjNTNn89BKpui1Y8");
			        	 xhr.setRequestHeader("content-type", "application/json");
	     	        	 xhr.send(data);

		        },
		        error:function(xhr,status)
		        {
		        	console.log(status);	
		        	localStorage.setItem("loginType","");
		        }
		       
		  });
	}	
    function stripQueryStringAndHashFromPath(url) {
        return url.split("?")[0].split("#")[0];
    }   
    var Upload = function (file) {
        this.file = file;
    };
    
    Upload.prototype.getType = function() {
        localStorage.setItem("type",this.file.type);
        return this.file.type;
    };
    Upload.prototype.getSize = function() {
        localStorage.setItem("size",this.file.size);
        return this.file.size;
    };
    Upload.prototype.getName = function() {
        return this.file.name;
    };
    
    function pushToGooglePhotos()
    {
    	$.ajax({
            type: "POST",
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", "Bearer" + " " + localStorage.getItem("accessToken"));
                
            },
            url: "https://photoslibrary.googleapis.com/v1/mediaItems:batchCreate",
            xhr: function () {
                var myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) {
                    //myXhr.upload.addEventListener('progress', that.progressHandling, false);
                }
                return myXhr;
            },
            success: function (data) {
                console.log(data);
            },
            error: function (error) {
                console.log(error);
            },
            async: true,
            data: "{\r\n\t  \"newMediaItems\": [\r\n    {\r\n      \"description\": \"image\",\r\n      \"simpleMediaItem\": {\r\n        \"uploadToken\": \"" +localStorage.getItem("uploadToken")+"\"\r\n      }\r\n    }\r\n  ]\r\n}",
            cache: false,
            contentType: false,
            processData: false,
            timeout: 60000
        });
    	
    }   
    Upload.prototype.doUpload = function () {
        var that = this;
        
        var data = this.file;

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
          if (this.readyState === 4) {
        	  localStorage.setItem("uploadToken",this.responseText);
            console.log(this.responseText);
            pushToGooglePhotos();
          }
        });

        xhr.open("POST", "https://photoslibrary.googleapis.com/v1/uploads");
        xhr.setRequestHeader("authorization", "Bearer" + " " + localStorage.getItem("accessToken"));
        xhr.setRequestHeader("content-type", "application/octet-stream");
        xhr.setRequestHeader("x-goog-upload-file-name", "ravi.jpg");
        xhr.setRequestHeader("x-goog-upload-protocol", "raw");
        xhr.send(data);
    };
    
    Upload.prototype.progressHandling = function (event) {
        var percent = 0;
        var position = event.loaded || event.position;
        var total = event.total;
        var progress_bar_id = "#progress-wrp";
        if (event.lengthComputable) {
            percent = Math.ceil(position / total * 100);
        }
        // update progressbars classes so it fits your code
        $(progress_bar_id + " .progress-bar").css("width", +percent + "%");
        $(progress_bar_id + " .status").text(percent + "%");
    };

    $("#upload").on("click", function (e) {
        var file = $("#files")[0].files[0];
        var upload = new Upload(file);
    
        // maby check size or type here with upload.getSize() and upload.getType()
    
        // execute upload
        upload.doUpload();
    });   
});
