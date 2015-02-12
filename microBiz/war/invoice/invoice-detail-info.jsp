<%@include file="../includes/taglib.jsp"%>



<div class="panel-body">
	<div class="row">
		<div class="col-xs-6 col-md-4">

			<strong>${f:h(invoice.customerRef.model.name)} </strong> 
			${f:nbsp('   ')}

			<c:if test="${f:h(invoice.hasContact)}">
			<font class="text-muted"> Contact:</font> ${f:h(invoice.contactRef.model.name)} <br>
			</c:if>
			<font class="text-muted">Invoice Number:</font> <strong>${f:h(invoice.invoiceNumber)} </strong><br>
			${f:h(invoice.address)} <br>
			<font class="text-muted">PO Number:</font>${f:h(invoice.poNumber)} <br>
			<font class="text-muted">Status:</font>${f:h(invoice.status)} 
		</div>
		<div class="col-xs-6 col-md-4">
			<font class="text-muted">Sales:</font> ${f:h(invoice.salesRef.model.name)} ${f:nbsp('  ')} <font class="text-muted">signed on</font> ${f:nbsp('  ')} ${f:h(invoice.signDateStr)}<br>
			<font class="text-muted">Preferred Installed Date:</font> ${f:h(invoice.preferIntlDateStr)} <br>
			<font class="text-muted"> Estimated Working Hours:</font>  ${f:h(invoice.estimatedWorkingHours)}<br>
			<font class="text-muted">Deposit:</font> ${f:h(invoice.deposit)} by ${f:h(invoice.depositPymtMethod)} <br>
			<font class="text-muted">Total Amount:</font> ${f:h(invoice.orderRef.model.total)}  ${f:nbsp('  ')} by  ${f:h(invoice.balancePymtMethod)}<br>
			<font class="text-muted">Note:</font> ${f:h(quote.note)}
		</div>
	</div>

</div>