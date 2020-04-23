function openpopup(id){ 
	
	
	//style="display: block; background: rgb(215, 219, 182);"class="modal-backdrop fade in"
	
      var divobj = document.getElementById(id); 
      divobj.setAttribute("class", "modal hide fade in");
      divobj.setAttribute("aria-hidden", "false");
      divobj.style.display = "block";
      divobj.style.background = "rgb(215, 219, 182)";
      document.getElementById("bg").setAttribute("class","modal-backdrop fade in");

} 
function closepopup(id){ 

   var divobj = document.getElementById(id); 
   	document.getElementById("popmsg").innerHTML = '';
	
	document.getElementById(id+'form').reset();
		
      divobj.setAttribute("class", "modal hide fade");
      divobj.setAttribute("aria-hidden", "true");
      divobj.style.display = "none";
      divobj.style.background = "rgb(215, 219, 182)";
      document.getElementById("bg").setAttribute("class","");
} 
