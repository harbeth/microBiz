<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-green">
	<div class="panel-heading">
		${f:h(openInvoiceCount)} ongoing invoices
	</div>
	<div class="panel-footer">
		<span class="pull-right">
			<a link="managerTabOngoingInvoice" nowSelected="n">View Details</a>
		</span>
		<div class="clearfix"></div>
	</div>
</div>