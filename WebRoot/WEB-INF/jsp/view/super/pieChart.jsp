<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<body>
<div class="right-pannel col-lg-10">
					<h1 class="heading pull-left">Reports <!-- <button id="btnCreatePieChart">Show </button> --> </h1>
					<select class="sort-select" id="reportViewBy" style="width:150px;padding:6px;float:right">
						<option value="">Sort By</option>
						<option value="yearly" >Yearly</option>
						<option value="monthly" selected="selected">Monthly</option>
					</select>
					<div class="clearfix"></div>
					<div class="inner-content" style="padding:35px;">
						<%-- <h1 class="pull-left" style="margin:0px;font-size:22px;color:#05a42e;font-weight:bold;">Year, ${year}</h1> --%>
						
						<span class="pull-right chnage-month">
							<%-- <img src="<%=request.getContextPath()%>/resources/images/images/calender.png" /> --%>
				<%-- <form:select path="year" items="${yearList}" id="selectYear" selected="selected" class="sort-select"/> --%>
				<select id="selectYear" name="year" style="width:150px;padding:6px;">
							<c:forEach items="${yearList}" var="item" varStatus="loop">
							<c:if test="${year eq item}">
							<option value="${item}" selected="selected">${item}</option>
							</c:if>
							<c:if test="${year ne item}">
							<option value="${item}" >${item}</option>
							</c:if>
							</c:forEach>
							</select>
				<select name="month" id="selectedMonth" class="sort-select" style="width:150px;padding:6px;margin-top:6px;">
					<option value="1">Jan</option>
					<option value="2">Feb</option>
					<option value="3">March</option>
					<option value="4">April</option>
					<option value="5">May</option>
					<option value="6">June</option>
					<option value="7">July</option>
					<option value="8">Aug</option>
					<option value="9">Sep</option>
					<option value="10">Oct</option>
					<option value="11">Nov</option>
					<option value="12">Dec</option>
			</select>
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
    $("#selectedMonth").change(function () {
        var selectedText = $(this).find("option:selected").text();
        var selectedMonth = $(this).val();
        console.log("Selected Text: " + selectedText + " selectedMonth: " + selectedMonth);
        
        var yearValue=document.getElementById("selectYear").value;
        console.log(" selected year "+yearValue);
        
	  
		 $.ajax({
		        type: "GET",
		        url: "barChart?Value=monthly",
		        data: { dateText: yearValue, selectedMonth: selectedMonth} ,
		        async:false,
		        success: OnSuccessResponse_,
		        error: OnErrorCallResponse_
		 })
		  function OnSuccessResponse_(response) {
					    //	alert("response"+JSON.stringify(response));
	/* 				        var aData = response.d; */
					        var arr = [];
					 
					        var responseLength=response.length;
					        for ( var i = 0; i < response.length; i++) {
					        	var obj = {};
					            obj.name = response[i].name;
					            obj.y =  response[i].y;
					            arr.push(obj);

							}
					     
					       // alert(" hello abhishek "+JSON.stringify(arr));
					        var myJsonString = JSON.stringify(arr);
					        var jsonArray = JSON.parse(myJsonString);
					      //  alert("jsonArray \t"+jsonArray);
					        drawPieChart(jsonArray);
					 
					    }
					    function OnErrorCallResponse_(response) {
					        alert("Whoops something went wrong!");
					    }
					    function drawPieChart(seriesData){
						//	alert("seriesData \t"+seriesData);
											 
						   
						    
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
<script type="text/javascript">

$(function () {
    $("#selectYear").change(function () {
        var selectedText = $(this).find("option:selected").text();
        var selectedValue = $(this).val();
        console.log("Selected Text: " + selectedText + " Value: " + selectedValue);
       $("#selectedMonth").show();
    });
});
</script>
<script type="text/javascript">

$(function () {
    $("#reportViewBy").change(function () {
        var selectedText = $(this).find("option:selected").text();
        var selectedValue = $(this).val();
       // alert("Selected Text: " + selectedText + " Value: " + selectedValue);
        window.location ='getReports?Value='+selectedValue;
    });
});
</script>
<script>
window.onload=$("#selectedMonth").hide();
window.onload=ajaxCallMethod();
function ajaxCallMethod(){
	
	//alert('hi abhishek ');
	 $.ajax({
	        type: "GET",
	        url: "barChart?Value=monthly",
	        async:false,
	        success: OnSuccessResponse_,
	        error: OnErrorCallResponse_
	 })
	  function OnSuccessResponse_(response) {
				    	//alert("response"+JSON.stringify(response));
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
				     
				   //     alert(" hello abhishek "+JSON.stringify(arr));
				        var myJsonString = JSON.stringify(arr);
				        var jsonArray = JSON.parse(myJsonString);
				     //   alert("jsonArray \t"+jsonArray);
				        drawPieChart(jsonArray);
				 
				    }
				    function OnErrorCallResponse_(response) {
				        alert("Whoops something went wrong!");
				    }
				    function drawPieChart(seriesData){
						//alert("seriesData \t"+seriesData);
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
	});	
	
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>	
<script >
$(document).ready(function() {
$("#e").addClass("active_menu");
});
</script>