<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-green">
	<div class="panel-heading">
		 $ ${f:h(openInvoiceAmt)} Open
	</div>
	<div class="panel-footer">
		<span class="pull-right">
			<a link="managerTabOngoingInvoice" nowSelected="n">${f:h(openInvoiceCount)} Open Invoices</a>
		</span>
		<div class="clearfix"></div>
	</div>
</div>