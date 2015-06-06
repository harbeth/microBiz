<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		$ ${f:h(unPaidAmt)} Due
	</div>
	<div class="panel-footer">
		<span class="pull-right">
			<a link="managerTabUnpaidInvoice" nowSelected="y">${f:h(unPaidOffInvoiceCount)} Unpaid Invoices </a>
		</span>
		<div class="clearfix"></div>
	</div>
</div>