<%@include file="../includes/taglib.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h3><span id="detailPaneTitleSpan">Job List</span></h3>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-4 col-md-8">
		<div class="panel panel-info">
			<div class="panel-heading">
				<li>${f:h(invoice.statusLable)} ${f:h('  ')} ${f:h(invoice.statusChangeDateStr)}</li>
				<li>${f:h(invoice.invoiceReportRef.model.completeJobCount)} Jobs Completed</li>
				<li>${f:h(invoice.invoiceReportRef.model.onGoingJobCount)} Jobs On going</li>
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
		<div class="panel panel-success">
			<div class="panel-heading">
				<li>${f:h(invoice.customerRef.model.pymtTermLable)} Days Term</li>
				<li>Amount: ${f:h(invoice.invoiceReportRef.model.total)}</li>
				<li>Received:${f:h(invoice.invoiceReportRef.model.pymtReceived)}</li>
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
		<div class="panel panel-danger">
			<div class="panel-heading">
				<li>Profit Margin: ${f:h(invoice.invoiceReportRef.model.profitMargin)}%</li>
				<li>Labor Cost: ${f:h(invoice.invoiceReportRef.model.labourCost)}</li>
				<li>Material Cost: ${f:h(invoice.invoiceReportRef.model.materialCost)}</li>
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