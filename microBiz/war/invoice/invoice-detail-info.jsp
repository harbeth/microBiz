<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-12">

	<div class="col-lg-6">
		<p>
		<font class="text-muted">PO Number:</font>${f:h(invoice.poNumber)} <br>
		<font class="text-muted">Sales:</font> ${f:h(invoice.salesRef.model.name)} ${f:nbsp('  ')} <font class="text-muted">signed on</font> ${f:nbsp('  ')} ${f:h(invoice.signDateStr)}<br>
		<font class="text-muted">Sales Commission:</font>$300  paid on Jan 15, 2015 <br>
		<font class="text-muted">Note:</font> ${f:h(quote.note)}
		</p>
	</div>
	<div class="col-lg-6">
		<p>
		<font class="text-muted">Preferred Installed Date:</font> ${f:h(invoice.preferIntlDateStr)} <br>
		<font class="text-muted"> Estimated Working Hours:</font>  ${f:h(invoice.estimatedWorkingHours)}<br>
		<font class="text-muted">Deposit:</font> ${f:h(invoice.deposit)} by ${f:h(invoice.depositPymtMethodLable)} <br>
		<font class="text-muted">Total Amount:</font> ${f:h(invoice.orderRef.model.total)}  ${f:nbsp('  ')} by  ${f:h(invoice.balancePymtMethodLable)}<br>
		</p>
	</div>

</div>
