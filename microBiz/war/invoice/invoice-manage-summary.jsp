<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-4 col-md-8">
	<div class="panel panel-info">
		<div class="panel-heading">
			<li>${f:h(invoice.statusLable)} ${f:h('  ')} On ${f:h('  ')} ${f:h(invoice.statusChangeDateStr)} </li>
			<li>${f:h(invoice.invoiceReportRef.model.completeJobCount)} Jobs Completed</li>
			<li>${f:h(invoice.invoiceReportRef.model.onGoingJobCount)} Jobs Open</li>
		</div>
		<div class="panel-footer">
			<c:if test = "${f:h(invoice.showAssignJobLink)}">
				<span class="pull-left">
					<a link="invoiceJobEdit" invoiceKey="${f:h(invoice.key)}" jobKey="-1">Assign Job</a>
				</span>
			</c:if>
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
			<li>Amount: ${f:h(invoice.invoiceReportRef.model.total)}</li>
			<li>Received:${f:h(invoice.invoiceReportRef.model.pymtReceived)}</li>
			<li>Profit Margin: ${f:h(invoice.invoiceReportRef.model.profitMargin)}%</li>
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
			<li>Labor Cost: ${f:h(invoice.invoiceReportRef.model.labourCost)}</li>
			<li>Material Cost: ${f:h(invoice.invoiceReportRef.model.materialCost)}</li>
			<li>Other Expense: ${f:h(invoice.invoiceReportRef.model.otherExpense)}</li>
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