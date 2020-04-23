<%@include file="/WEB-INF/jsp/include/taglibs.jsp"%>	
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>iAmuse-Admin</title>
  	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>    
	
    <style>
    	/*css for paypal recept*/
.paypal-container{
 width:700px;
 height:600px;
 margin:0px auto;
}
.paypal-header{padding:20px 0px;}
.paypal-logo{
 width:150px;
 float:left;
}
.header-id{
 width:500px;
 float:right;
 text-align:right; 
 margin-top:-5px;
}
.header-id p{
 margin: 0px 0px -1px;
 font-size: 14px;
 color: #333333;
}
.header-id p span{color:#0000ee !important}
.paypal-body{
 padding:40px 0px 20px;
}
.greeting-text{
 font-size:14px;
 color:#333;
 font-weight:bold;
}
.greeting-h{
 font-size:15px;
 margin:0px;
 color:#c88039;
 font-weight:bold;
}
.paypal-body-text{
    font-size: 13px;
    font-weight: 400;
}
.senders-details p{margin:0px !important;font-size:14px;}
.custome-table thead tr {
 border:1px solid rgba(150, 150, 150, 0.45);
    border-left: 0;
    border-right: 0;
}
.senders-details{
 padding-bottom:35px;
}
.custome-table tr th,.custome-table tr td{padding:5px 0px;vertical-align: text-bottom;}
.highlighter{
 color:#0000ee;
 margin:0px;
}
.paypal-url{color:#0000ee;}
.paypal-url:hover{color:#0000ee;text-decoration:none}
    </style>
    <script type="text/javascript">
    function printDiv(divName) {
        var printContents = document.getElementById(divName).innerHTML;
        var originalContents = document.body.innerHTML;

        document.body.innerHTML = printContents;

        window.print();

        document.body.innerHTML = originalContents;
   }
    </script>
  </head>
	<body id="printableArea">
	
	 				<c:if test="${not empty successMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: green;">${successMessage}</h4></center></div>
					</c:if>
					
					<c:if test="${not empty errorMessage}">
					<div id="effect"  class="ui-widget-content ui-corner-all"><center><h4 style="color: red;">${errorMessage}</h4></center></div>
					</c:if>
	
		<div class="paypal-container">
			<div class="paypal-header">
				<div class="paypal-logo">
					<img src="<%=request.getContextPath()%>/resources/images/images/paypal1.png">
				</div>
				<div class="header-id">
					<p>${transactionReceiptVO.transactionDate}</p>
					<p>Transaction ID: <span>${transactionReceiptVO.transactionId}</span></p>
				</div>
			</div>
			<div class="paypal-body">
				<p class="greeting-text">Hello,</p>
				<h1 class="greeting-h">You sent a payment of ${transactionReceiptVO.totalAmount} USD to iAmuse (${transactionReceiptVO.sellerEmail})</h1>
				<p class="paypal-body-text" style="margin-top:5px;">Thanks for using Pay Pal. To see all transaction details, Log in to your Pay Pal account.</p>
				<p class="paypal-body-text" style="margin-top:5px;border-bottom: 1px solid rgba(150, 150, 150, 0.45);padding-bottom: 8px;">It may a few moments for this transaction to appear in your account</p>
				<div class="senders-details">
					<p class="greeting-text">Seller,</p>
					<p class="paypal-body-text" style="margin-top:5px;">${transactionReceiptVO.sellerName }</p>
					<p class="paypal-body-text" style="margin-top:5px;">${transactionReceiptVO.sellerEmail }</p>
				</div>
				<table class="tabel custome-table" width="100%">
				
					<thead>
						<tr>
							<th>Description</th>
							<th width="130px" style="text-align:right">Unit Price</th>
							<th width="130px" style="text-align:right">Amount</th>
						</tr>
					</thead>
					<tbody>
						<tr style="border-bottom:1px solid rgba(150, 150, 150, 0.45);">
							<td >
								<p class="highlighter">${transactionReceiptVO.supscriptionPlan }</p>
								<p class="highlighter">Subscription plan</p>
								<c:if test="${transactionReceiptVO.totalAmount.equals('$150.00') || transactionReceiptVO.totalAmount.equals('$25.00')}">
								<p>validity: ${transactionReceiptVO.planValidity }</p>
								</c:if>
								<c:if test="${transactionReceiptVO.totalAmount.equals('$1200.00')}">
								<p>validity: 365 Days</p>
								</c:if>
							</td>
							<td align="right">
							<c:if test="${transactionReceiptVO.totalAmount.equals('$150.00') || transactionReceiptVO.totalAmount.equals('$25.00')}">
							${transactionReceiptVO.unitPrice}.00</c:if>
							<c:if test="${transactionReceiptVO.totalAmount.equals('$1200.00')}">
							$100.00</c:if>
							</td>
							<td align="right">${transactionReceiptVO.totalAmount}</td>
						</tr>
						<tr>
							<td rowspan="2" style="vertical-align: bottom;">
								<img src="<%=request.getContextPath()%>/resources/images/images/iamuse-logo.png">
							</td>
							<td align="right" style="font-weight:bold">Sub Total</td>
							<td align="right" >
							<c:if test="${transactionReceiptVO.totalAmount.equals('$150.00') || transactionReceiptVO.totalAmount.equals('$25.00')}">
							${transactionReceiptVO.unitPrice}.00</c:if>
							<c:if test="${transactionReceiptVO.totalAmount.equals('$1200.00')}">
							${transactionReceiptVO.totalAmount}</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" style="font-weight:bold">Amount</td>
							<td align="right">${transactionReceiptVO.totalAmount}</td>
						</tr>
						<tr style="border-bottom:1px solid rgba(150, 150, 150, 0.45);">
							<td colspan="2" align="right">
								<p>Payment sent to ${transactionReceiptVO.sellerEmail }</p>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3">
								<p style="margin:0px">Issues with  this transaction ?</p>
								<p style="margin:0px">you have 45 days from the date of the transaction to open a dispute in the Resolution Center</p>
								<p style="margin-top:15px;">Question? Go to the help center at:<a href="www.paypal.com/help" class="paypal-url">www.paypal.com/help</a></p>
								<p style="font-size:13px;">please do not reply to his mail. this mailbox is not monitored and you will not receive a response. for assistance, log in to your PayPal account and click. Help in the top right corner of any paypal page.</p>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<input type="button" onclick="printDiv('printableArea')" value="print" style="width:auto;"/>
			<button onclick="location.href = 'closeSubs';" id="myButton" class="float-left submit-button" style="width:auto;margin-bottom:50px;">Close</button>
		</div>
		
	</body>
</html>