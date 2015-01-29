<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
	<p>
		Number: ${f:h(invoice.invoiceNumber)} <br> Address:
		${f:h(invoice.address)} <br> Sales: ${f:h(invoice.salesRef.model.name)} <br>

	</p>
</div>