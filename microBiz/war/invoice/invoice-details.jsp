<%@include file="../includes/taglib.jsp"%>
<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-primary">
		
		<div class="panel-heading">
		Invoice Details
		</div>
		<div class="panel-dody">
			<div id="invoiceInfoDIV">
				<jsp:include page="invoice-detail-info.jsp" flush="true"></jsp:include>
			</div>
		</div>
			<div class="panel-footer">
				<a link="invoicePaymentEdit" invoiceKey="${f:h(invoice.key)}" paymentKey="-1"  class="btn btn-default btn-sm" role="button">New Payment</a> 
				<a link="invoiceJobEdit" invoiceKey="${f:h(invoice.key)}" jobKey="-1" data-toggle="tab" aria-expanded="false" class="btn btn-default btn-sm" role="button">Assign Job</a>
				<a link="invoiceExpenseEdit" invoiceKey="${f:h(invoice.key)}" expenseKey="-1"  class="btn btn-default btn-sm" role="button">New Expense</a>
				<a link="invoiceEditClose"  class="btn btn-default btn-sm" role="button">Close</a>
			</div>
		</div>
	</div>
</div>
<!-- /.row -->

<ul class="nav nav-tabs">
	<li class="active">
		<a link="invoiceDetailOrder" data-toggle="tab" aria-expanded="false">Items</a>
	</li>
	<li>
		<a link="invoiceDetailJob" data-toggle="tab" aria-expanded="false">Jobs</a>
	</li>
	<li class="">
		<a link="invoiceDetailPayment" data-toggle="tab" aria-expanded="false">Payment</a>
	</li>
	<li class="">
		<a link="invoiceDetailExpense" data-toggle="tab" aria-expanded="false">Expense</a>
	</li>
    <li class="">
    	<a link="invoiceDetailInfo" data-toggle="tab" aria-expanded="false">Edit</a>
    </li>
</ul>

<!-- Page content : by default job div shown -->
<div id="invoiceDetailOrderDIV" type="tab" invoiceKey="${f:h(invoice.key)}" class="row">
	<jsp:include page="./invoice-order.jsp" flush="true" />
</div>
<div id="invoiceDetailJobDIV" type="tab" invoiceKey="${f:h(invoice.key)}" class="row" hasContent="n"></div>
<div id="invoiceDetailInfoDIV" type="tab" invoiceKey="${f:h(invoice.key)}" class="row" hasContent="n"></div>
<div id="invoiceDetailPaymentDIV" type="tab" invoiceKey="${f:h(invoice.key)}" class="row" hasContent="n"></div>
<div id="invoiceDetailExpenseDIV" type="tab" invoiceKey="${f:h(invoice.key)}" class="row" hasContent="n"></div>

<%--
<jsp:include page="invoice-jobs.jsp" flush="true"></jsp:include>
--%>