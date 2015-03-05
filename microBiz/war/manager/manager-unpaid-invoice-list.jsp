<%@include file="../includes/taglib.jsp"%>
<!-- 
<jsp:include page="./manager-payment-edit.jsp" flush="true" />
 -->

<div class="panel panel-primary">
	<div class="panel-heading">
	   Not Paid Off Invoices
	</div>
	<div id="managerPaymentDetailDIV" hasContent="n">
	</div>
	<div class="panel-footer">
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Invoice #</th>
						<th>Customer</th>
						<th>Address</th>
						<th>Sales</th>
						<th>Total</th>
						<th>Received</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${invoices}">
						<tr>
							<td>${f:h(i.invoiceNumber)}</td>
							<td>${f:h(i.customerRef.model.name)}</td>
							<td>${f:h(i.address)}</td>
							<td>${f:h(i.sales)}</td>
							<td>${f:h(i.invoiceReportRef.model.total)}</td>
							<td>${f:h(i.invoiceReportRef.model.pymtReceived)}</td>
							<td><a link="managerInvoicePaymentNew" invoiceKey="${f:h(i.key)}">Receive Payment</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>