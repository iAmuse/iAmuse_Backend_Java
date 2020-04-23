		<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
  
				    <div class="right-pannel">
					<h1 class="heading pull-left">Reports </h1>
					<select class="sort-select" id="reportViewBy" >
						<option value="">Sort By</option>
						<option value="yearly" selected="selected">Yearly</option>
						<option value="monthly">Monthly</option>
					</select>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<h1 class="pull-left" style="margin:0px;font-size:22px;color:#05a42e;font-weight:bold;">Year, 2016</h1>
						<span class="pull-right chnage-month">
							<img src="<%=request.getContextPath()%>/resources/images/images/calender.png">
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

				<div class="clearfix"></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
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
	 $.ajax({
	        type: "GET",
	        url: "barChart",
	        async:false,
	        success: OnSuccessResponse_,
	        error: OnErrorCallResponse_
	 })
	  function OnSuccessResponse_(response) {
				    	/* alert("response"+JSON.stringify(response)); */

				        var arr = [];
				        var responseLength=response.length;
				        for ( var i = 0; i < response.length; i++) {
				        	var obj = {};
				            obj.name = response[i].name;
				            obj.y =  response[i].y;
				            arr.push(obj);

						}
				     
				      /*   alert(" hello abhishek "+JSON.stringify(arr)); */
				        var myJsonString = JSON.stringify(arr);
				        var jsonArray = JSON.parse(myJsonString);
				       /*  alert("jsonArray \t"+jsonArray); */
				        drawPieChart(jsonArray);
				 
				    }
				    function OnErrorCallResponse_(response) {
				        alert("Whoops something went wrong!");
				    }
				    function drawPieChart(seriesData){
						/* alert("seriesData \t"+seriesData); */
						// Build the chart
						Highcharts.chart('container', {
					        chart: {
					            type: 'column'
					        },
					        title: {
					            text: 'Total Subscription'
					        },
					        xAxis: {
					            type: 'category'
					        },
					        yAxis: {
					            title: {
					                text: 'Total Subscription'
					            }

					        },
					        legend: {
					            enabled: false
					        },
					        plotOptions: {
					            series: {
					                borderWidth: 0,
					                dataLabels: {
					                    enabled: true,
					                    format: '{point.y}'
					                }
					            }
					        },

					        tooltip: {
					            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
					        },

					        series: [{
					            name: 'Brands',
					            colorByPoint: true,
					            data: seriesData
					        }],
					        drilldown: {
					            series: [{
					                name: 'Microsoft Internet Explorer',
					                id: 'Microsoft Internet Explorer',
					                data: [
					                    [
					                        'v11.0',
					                        24.13
					                    ],
					                    [
					                        'v8.0',
					                        17.2
					                    ],
					                    [
					                        'v9.0',
					                        8.11
					                    ],
					                    [
					                        'v10.0',
					                        5.33
					                    ],
					                    [
					                        'v6.0',
					                        1.06
					                    ],
					                    [
					                        'v7.0',
					                        0.5
					                    ]
					                ]
					            }, {
					                name: 'Chrome',
					                id: 'Chrome',
					                data: [
					                    [
					                        'v40.0',
					                        5
					                    ],
					                    [
					                        'v41.0',
					                        4.32
					                    ],
					                    [
					                        'v42.0',
					                        3.68
					                    ],
					                    [
					                        'v39.0',
					                        2.96
					                    ],
					                    [
					                        'v36.0',
					                        2.53
					                    ],
					                    [
					                        'v43.0',
					                        1.45
					                    ],
					                    [
					                        'v31.0',
					                        1.24
					                    ],
					                    [
					                        'v35.0',
					                        0.85
					                    ],
					                    [
					                        'v38.0',
					                        0.6
					                    ],
					                    [
					                        'v32.0',
					                        0.55
					                    ],
					                    [
					                        'v37.0',
					                        0.38
					                    ],
					                    [
					                        'v33.0',
					                        0.19
					                    ],
					                    [
					                        'v34.0',
					                        0.14
					                    ],
					                    [
					                        'v30.0',
					                        0.14
					                    ]
					                ]
					            }, {
					                name: 'Firefox',
					                id: 'Firefox',
					                data: [
					                    [
					                        'v35',
					                        2.76
					                    ],
					                    [
					                        'v36',
					                        2.32
					                    ],
					                    [
					                        'v37',
					                        2.31
					                    ],
					                    [
					                        'v34',
					                        1.27
					                    ],
					                    [
					                        'v38',
					                        1.02
					                    ],
					                    [
					                        'v31',
					                        0.33
					                    ],
					                    [
					                        'v33',
					                        0.22
					                    ],
					                    [
					                        'v32',
					                        0.15
					                    ]
					                ]
					            }, {
					                name: 'Safari',
					                id: 'Safari',
					                data: [
					                    [
					                        'v8.0',
					                        2.56
					                    ],
					                    [
					                        'v7.1',
					                        0.77
					                    ],
					                    [
					                        'v5.1',
					                        0.42
					                    ],
					                    [
					                        'v5.0',
					                        0.3
					                    ],
					                    [
					                        'v6.1',
					                        0.29
					                    ],
					                    [
					                        'v7.0',
					                        0.26
					                    ],
					                    [
					                        'v6.2',
					                        0.17
					                    ]
					                ]
					            }, {
					                name: 'Opera',
					                id: 'Opera',
					                data: [
					                    [
					                        'v12.x',
					                        0.34
					                    ],
					                    [
					                        'v28',
					                        0.24
					                    ],
					                    [
					                        'v27',
					                        0.17
					                    ],
					                    [
					                        'v29',
					                        0.16
					                    ]
					                ]
					            }]
					        }
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
		
		
		
		$(function () {
    // Create the chart
    /* Highcharts.chart('container', {}); */
});
	});	
	
</script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-1.9.1.js"></script>
<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<style>
.ui-datepicker-calendar {
   display: block;
}
#ui-datepicker-div { font-size: 12px; } 
#datepicker{float:left; height:18px;}
h1{font-size: 15px;}
</style>
<script>
jQuery.noConflict();
jQuery(document).ready(function(){
	
	jQuery("#datepicker").datepicker({
		
	   //showOn: both - datepicker will appear clicking the input box as well as the calendar icon
	   //showOn: button - datepicker will appear only on clicking the calendar icon
	   showOn: 'button',
	   //you can use your local path also eg. buttonImage: 'images/x_office_calendar.png'
	   buttonImage: 'https://theonlytutorials.com/demo/x_office_calendar.png',
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
		        url: "barChart",
		        data: { dateText: dateText} ,
		        async:false,
		        success: OnSuccessResponse_,
		        error: OnErrorCallResponse_
		 })
		  function OnSuccessResponse_(response) {
					    	/* alert("response"+JSON.stringify(response)); */
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
					     
					      /*   alert(" hello abhishek "+JSON.stringify(arr)); */
					        var myJsonString = JSON.stringify(arr);
					        var jsonArray = JSON.parse(myJsonString);
					       /*  alert("jsonArray \t"+jsonArray); */
					        drawPieChart(jsonArray);
					 
					    }
					    function OnErrorCallResponse_(response) {
					        alert("Whoops something went wrong!");
					    }
					    function drawPieChart(seriesData){
							/* alert("seriesData \t"+seriesData); */
											 
						   
						    
							// Build the chart
							Highcharts.chart('container', {
						        chart: {
						            type: 'column'
						        },
						        title: {
						            text: 'Total Subscription'
						        },
						        xAxis: {
						            type: 'category'
						        },
						        yAxis: {
						            title: {
						                text: 'Total Subscription'
						            }

						        },
						        legend: {
						            enabled: false
						        },
						        plotOptions: {
						            series: {
						                borderWidth: 0,
						                dataLabels: {
						                    enabled: true,
						                    format: '{point.y}'
						                }
						            }
						        },

						        tooltip: {
						            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
						            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
						        },

						        series: [{
						            name: 'Brands',
						            colorByPoint: true,
						            data: seriesData
						        }],
						        drilldown: {
						            series: [{
						                name: 'Microsoft Internet Explorer',
						                id: 'Microsoft Internet Explorer',
						                data: [
						                    [
						                        'v11.0',
						                        24.13
						                    ],
						                    [
						                        'v8.0',
						                        17.2
						                    ],
						                    [
						                        'v9.0',
						                        8.11
						                    ],
						                    [
						                        'v10.0',
						                        5.33
						                    ],
						                    [
						                        'v6.0',
						                        1.06
						                    ],
						                    [
						                        'v7.0',
						                        0.5
						                    ]
						                ]
						            }, {
						                name: 'Chrome',
						                id: 'Chrome',
						                data: [
						                    [
						                        'v40.0',
						                        5
						                    ],
						                    [
						                        'v41.0',
						                        4.32
						                    ],
						                    [
						                        'v42.0',
						                        3.68
						                    ],
						                    [
						                        'v39.0',
						                        2.96
						                    ],
						                    [
						                        'v36.0',
						                        2.53
						                    ],
						                    [
						                        'v43.0',
						                        1.45
						                    ],
						                    [
						                        'v31.0',
						                        1.24
						                    ],
						                    [
						                        'v35.0',
						                        0.85
						                    ],
						                    [
						                        'v38.0',
						                        0.6
						                    ],
						                    [
						                        'v32.0',
						                        0.55
						                    ],
						                    [
						                        'v37.0',
						                        0.38
						                    ],
						                    [
						                        'v33.0',
						                        0.19
						                    ],
						                    [
						                        'v34.0',
						                        0.14
						                    ],
						                    [
						                        'v30.0',
						                        0.14
						                    ]
						                ]
						            }, {
						                name: 'Firefox',
						                id: 'Firefox',
						                data: [
						                    [
						                        'v35',
						                        2.76
						                    ],
						                    [
						                        'v36',
						                        2.32
						                    ],
						                    [
						                        'v37',
						                        2.31
						                    ],
						                    [
						                        'v34',
						                        1.27
						                    ],
						                    [
						                        'v38',
						                        1.02
						                    ],
						                    [
						                        'v31',
						                        0.33
						                    ],
						                    [
						                        'v33',
						                        0.22
						                    ],
						                    [
						                        'v32',
						                        0.15
						                    ]
						                ]
						            }, {
						                name: 'Safari',
						                id: 'Safari',
						                data: [
						                    [
						                        'v8.0',
						                        2.56
						                    ],
						                    [
						                        'v7.1',
						                        0.77
						                    ],
						                    [
						                        'v5.1',
						                        0.42
						                    ],
						                    [
						                        'v5.0',
						                        0.3
						                    ],
						                    [
						                        'v6.1',
						                        0.29
						                    ],
						                    [
						                        'v7.0',
						                        0.26
						                    ],
						                    [
						                        'v6.2',
						                        0.17
						                    ]
						                ]
						            }, {
						                name: 'Opera',
						                id: 'Opera',
						                data: [
						                    [
						                        'v12.x',
						                        0.34
						                    ],
						                    [
						                        'v28',
						                        0.24
						                    ],
						                    [
						                        'v27',
						                        0.17
						                    ],
						                    [
						                        'v29',
						                        0.16
						                    ]
						                ]
						            }]
						        }
						    });
						}
	   
	   }
	});
});
</script>

