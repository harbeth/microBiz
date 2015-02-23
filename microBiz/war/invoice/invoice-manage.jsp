<%@include file="../includes/taglib.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h3><span id="detailPaneTitleSpan">Job List</span></h3>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-4 col-md-8">
		<div class="panel panel-green">
			<div class="panel-heading">
				<li>Closed 2/12/2015</li>
				<li>4 Jobs Completed</li>
				<li>6 Jobs On going</li>
			</div>
			<div class="panel-footer">
				<span class="pull-left">
					<a link="invoiceJobEdit" invoiceKey="${f:h(invoice.key)}" jobKey="-1">Assign Job</a>
				</span>
				<span class="pull-right">
					<a link="invoiceDetailJob" nowSelected="y">View Jobs</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-4 col-md-8">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				<li>30 Days Term</li>
				<li>Amount: $4789</li>
				<li>Received: $0</li>
			</div>
			<div class="panel-footer">
				<span class="pull-left">
					<a link="invoicePaymentEdit" invoiceKey="${f:h(invoice.key)}" paymentKey="-1" role="button">Receive Payment</a>
				</span>
				<span class="pull-right">
					<a link="invoiceDetailPayment" nowSelected="n">View Payments</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-4 col-md-8">
		<div class="panel panel-red">
			<div class="panel-heading">
				<li>Profit Margin: 10%</li>
				<li>Labor Cost: $4789</li>
				<li>Material Cost: $9087</li>
			</div>
			<div class="panel-footer">
				<span class="pull-left">
					<a link="invoiceExpenseEdit" invoiceKey="${f:h(invoice.key)}" expenseKey="-1" role="button">Add Expense</a>
				</span>
				<span class="pull-right">
					<a link="invoiceDetailExpense" nowSelected="n">View Expenses</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="row">
		<div id="invoiceDetailJobDIV" type="tab" invoiceKey="${f:h(invoice.key)}" hasContent="y" class="col-lg-12" >
			<jsp:include page="./invoice-jobs.jsp" flush="true" />
		</div>
		<div id="invoiceDetailPaymentDIV" type="tab" invoiceKey="${f:h(invoice.key)}" hasContent="n" class="col-lg-12" ></div>
		<div id="invoiceDetailExpenseDIV" type="tab" invoiceKey="${f:h(invoice.key)}" hasContent="n" class="col-lg-12" ></div>
	</div>
</div>