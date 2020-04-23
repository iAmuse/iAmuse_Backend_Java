$(document).ready(function(){
$(".sub-menu").on('click',function(){
 $(".has-sub1").addClass("collapse-in");
 $('[data-toggle=collapse]').prop('disabled',true);
    
});

/*$("#boothId").on('click',function(){
  $(".has-sub1").removeClass("collapse-in");
});*/
 $("#boothId").on('click',function(){
    	      $(".has-sub-new").toggleClass('closed');
			  if($('.has-sub1').hasClass("collapse-in"))
			  {
			    $(".has-sub1").removeClass("collapse-in");
			    $('[data-toggle=collapse]').prop('disabled',false);
			  }
    	  });});

function test(){
	var url=window.location;
	$.ajax({
          url:"getSubscription",
          success:function(result){
             document.getElementById("content-area").innerHTML=result;
          }
	});
   
}
function home(){
   document.getElementById("content-area").innerHTML='<object type="text/html" data="Home.html" ></object>';
}