<body onload="datefunc()">
<div class="right-pannel">
					<h1 class="heading pull-left">Reports <!-- <button id="btnCreatePieChart">Show </button> --> </h1>
					<select class="sort-select" id="reportViewBy" >
						<option value="">Sort By</option>
						<option value="yearly" >Yearly</option>
						<option value="monthly" selected="selected">Monthly</option>
					</select>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<h1 class="pull-left" style="margin:0px;font-size:22px;color:#05a42e;font-weight:bold;">Year, 2016</h1>
						<span class="pull-right chnage-month">
							<img src="<%=request.getContextPath()%>/resources/images/images/calender.png" />
								<input type="hidden" id="datepicker" value="">
							<p>Change Year</p>
						</span>
						<div class="clearfix"></div>
						<div class="col-row">
							<div class="pie-chart">
								<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
							</div>
						</div>
					</div>
				</div>
</body>
				<div class="clearfix"></div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript">

$(function () {
    $("#reportViewBy").change(function () {
        var selectedText = $(this).find("option:selected").text();
        var selectedValue = $(this).val();
        alert("Selected Text: " + selectedText + " Value: " + selectedValue);
        window.location ='getReports?Value='+selectedValue;
    });
});
</script>
<script>
window.onload=ajaxCallMethod();
function ajaxCallMethod(){
	alert('hi abhishek ');
	 $.ajax({
	        type: "GET",
	        url: "barChart?Value=monthly",
	        async:false,
	        success: OnSuccessResponse_,
	        error: OnErrorCallResponse_
	 })
	  function OnSuccessResponse_(response) {
				    	alert("response"+JSON.stringify(response));
/* 				        var aData = response.d; */
				        var arr = [];
				 
				      /*   $.map(aData, function (item, index) {
				            var i = [item.name, item.y];
				            var obj = {};
				            obj.name = item.name;
				            obj.y = item.y;
				            arr.push(obj);
				            alert(" hello abhishek arr"+arr);
				        }); */
				        var responseLength=response.length;
				        for ( var i = 0; i < response.length; i++) {
				        	var obj = {};
				            obj.name = response[i].name;
				            obj.y =  response[i].y;
				            arr.push(obj);

						}
				     
				        alert(" hello abhishek "+JSON.stringify(arr));
				        var myJsonString = JSON.stringify(arr);
				        var jsonArray = JSON.parse(myJsonString);
				        alert("jsonArray \t"+jsonArray);
				        drawPieChart(jsonArray);
				 
				    }
				    function OnErrorCallResponse_(response) {
				        alert("Whoops something went wrong!");
				    }
				    function drawPieChart(seriesData){
						alert("seriesData \t"+seriesData);
					// Build the chart

						 
					   /*  $('#container').highcharts({
					        chart: {
					            plotBackgroundColor: null,
					            plotBorderWidth: null,
					            plotShadow: false,
					            type: 'pie'
					        },
					        title: {
					            text: 'Population percentage city wise'
					        },
					        tooltip: {
					            pointFormat: '{series.name}: <b>{point.population}%</b>'
					        },
					        plotOptions: {
					            pie: {
					                allowPointSelect: true,
					                cursor: 'pointer',
					                dataLabels: {
					                    enabled: true,
					                    format: '<b>{point.name}</b>: {point.population} %',
					                    style: {
					                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
					                    }
					                }
					            }
					        },
					        series: [{
					            name: "Brands",
					            colorByPoint: true,
					            data: seriesData
					        }]
					    }); */
					    
						// Build the chart
						Highcharts.chart('container', {
							chart: {
								plotBackgroundColor: null,
								plotBorderWidth: null,
								plotShadow: false,
								type: 'pie'
							},
							title: {
								text: 'Total Subscription'
							},
							tooltip: {
								pointFormat: '{series.name}: <b>{point.y}%</b>'
							},
							plotOptions: {
								pie: {
									allowPointSelect: true,
									cursor: 'pointer',
									dataLabels: {
										enabled: false
									},
									showInLegend: true
								}
							},
							series: [{
								name: 'Brands',
								colorByPoint: true,
								data: seriesData
							}]
						});
					}
}
</script>

				<script>
	$(document).ready(function(){
		$(window).resize(function() {
			var header_height=$('.header').height();
			//alert(header_height);
			$('.right-pannel').height($(window).height() - (header_height+50));
		});
		$(window).trigger('resize');
		

			$(document).ready(function () {
				$("#btnCreatePieChart").on('click', function (e) {
				    var pData = [];
				    pData[0] = $("#ddlyear").val();
				 
				    var jsonData = JSON.stringify({ pData: pData });
				 
				    $.ajax({
				        type: "GET",
				        url: "barChart",
				        data: jsonData,
				        contentType: "application/json; charset=utf-8",
				        dataType: "json",
				        success: OnSuccess_,
				        error: OnErrorCall_
				    });
				 
				    function OnSuccess_(response) {
				    	alert("response"+JSON.stringify(response));
/* 				        var aData = response.d; */
				        var arr = [];
				 
				      /*   $.map(aData, function (item, index) {
				            var i = [item.name, item.y];
				            var obj = {};
				            obj.name = item.name;
				            obj.y = item.y;
				            arr.push(obj);
				            alert(" hello abhishek arr"+arr);
				        }); */
				        var responseLength=response.length;
				        for ( var i = 0; i < response.length; i++) {
				        	var obj = {};
				            obj.name = response[i].name;
				            obj.y =  response[i].y;
				            arr.push(obj);

						}
				     
				        alert(" hello abhishek "+JSON.stringify(arr));
				        var myJsonString = JSON.stringify(arr);
				        var jsonArray = JSON.parse(myJsonString);
				        alert("jsonArray \t"+jsonArray);
				        drawPieChart1(jsonArray);
				 
				    }
				    function OnErrorCall_(response) {
				        alert("Whoops something went wrong!");
				    }
				    e.preventDefault();
				});
				
				function drawPieChart1(seriesData){
					alert("seriesData \t"+seriesData);
				// Build the chart

					 
				   /*  $('#container').highcharts({
				        chart: {
				            plotBackgroundColor: null,
				            plotBorderWidth: null,
				            plotShadow: false,
				            type: 'pie'
				        },
				        title: {
				            text: 'Population percentage city wise'
				        },
				        tooltip: {
				            pointFormat: '{series.name}: <b>{point.population}%</b>'
				        },
				        plotOptions: {
				            pie: {
				                allowPointSelect: true,
				                cursor: 'pointer',
				                dataLabels: {
				                    enabled: true,
				                    format: '<b>{point.name}</b>: {point.population} %',
				                    style: {
				                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
				                    }
				                }
				            }
				        },
				        series: [{
				            name: "Brands",
				            colorByPoint: true,
				            data: seriesData
				        }]
				    }); */
				    
					// Build the chart
					Highcharts.chart('container', {
						chart: {
							plotBackgroundColor: null,
							plotBorderWidth: null,
							plotShadow: false,
							type: 'pie'
						},
						title: {
							text: 'Total Subscription'
						},
						tooltip: {
							pointFormat: '{series.name}: <b>{point.y}%</b>'
						},
						plotOptions: {
							pie: {
								allowPointSelect: true,
								cursor: 'pointer',
								dataLabels: {
									enabled: false
								},
								showInLegend: true
							}
						},
						series: [{
							name: 'Brands',
							colorByPoint: true,
							data: seriesData
						}]
					});
				}
			});
	});	
	
</script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<style>
#ui-datepicker-div { font-size: 12px; } 
#datepicker{float:left; height:18px;}
h1{font-size: 15px;}
</style>
<script>
jQuery.noConflict();
jQuery(document).ready(function(){
	alert("gf");
	jQuery("#datepicker").datepicker({
		
	   //showOn: both - datepicker will appear clicking the input box as well as the calendar icon
	   //showOn: button - datepicker will appear only on clicking the calendar icon
	   showOn: 'button',
	   //you can use your local path also eg. buttonImage: 'images/x_office_calendar.png'
	   buttonImage: 'http://theonlytutorials.com/demo/x_office_calendar.png',
	   buttonImageOnly: true,
	   changeMonth: true,
	   changeYear: true,
	   showAnim: 'slideDown',
	   duration: 'fast',
	   dateFormat: 'dd-mm-yy',
	   onSelect: function(dateText, inst) {   
		   console.log('dateText by abhishek \t'+dateText);
	   alert('hi abhishek ');
		 $.ajax({
		        type: "GET",
		        url: "barChart?Value=monthly",
		        data: { dateText: dateText} ,
		        async:false,
		        success: OnSuccessResponse_,
		        error: OnErrorCallResponse_
		 })
		  function OnSuccessResponse_(response) {
					    	alert("response"+JSON.stringify(response));
	/* 				        var aData = response.d; */
					        var arr = [];
					 
					      /*   $.map(aData, function (item, index) {
					            var i = [item.name, item.y];
					            var obj = {};
					            obj.name = item.name;
					            obj.y = item.y;
					            arr.push(obj);
					            alert(" hello abhishek arr"+arr);
					        }); */
					        var responseLength=response.length;
					        for ( var i = 0; i < response.length; i++) {
					        	var obj = {};
					            obj.name = response[i].name;
					            obj.y =  response[i].y;
					            arr.push(obj);

							}
					     
					        alert(" hello abhishek "+JSON.stringify(arr));
					        var myJsonString = JSON.stringify(arr);
					        var jsonArray = JSON.parse(myJsonString);
					        alert("jsonArray \t"+jsonArray);
					        drawPieChart(jsonArray);
					 
					    }
					    function OnErrorCallResponse_(response) {
					        alert("Whoops something went wrong!");
					    }
					    function drawPieChart(seriesData){
							alert("seriesData \t"+seriesData);
											 
						   
						    
							// Build the chart
							Highcharts.chart('container', {
								chart: {
									plotBackgroundColor: null,
									plotBorderWidth: null,
									plotShadow: false,
									type: 'pie'
								},
								title: {
									text: 'Total Subscription'
								},
								tooltip: {
									pointFormat: '{series.name}: <b>{point.y}%</b>'
								},
								plotOptions: {
									pie: {
										allowPointSelect: true,
										cursor: 'pointer',
										dataLabels: {
											enabled: false
										},
										showInLegend: true
									}
								},
								series: [{
									name: 'Brands',
									colorByPoint: true,
									data: seriesData
								}]
							});
						}
	   
	   }
	});
});
</script>