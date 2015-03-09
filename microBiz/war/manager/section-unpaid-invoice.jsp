<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		$ ${f:h(unPaidAmt)} due
	</div>
	<div class="panel-footer">
		<span class="pull-right">
			<a link="managerTabUnpaidInvoice" nowSelected="y">${f:h(unPaidOffInvoiceCount)} UnPaidoffInvoices </a>
		</span>
		<div class="clearfix"></div>
	</div>
</div>