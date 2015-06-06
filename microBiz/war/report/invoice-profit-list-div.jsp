<%@include file="../includes/taglib.jsp"%>
<P> ${f:h(signDateFrom)} - ${f:h(signDateTo)}:&nbsp;&nbsp;&nbsp;&nbsp; Revenue: ${f:h(revenue)}  &nbsp;&nbsp;&nbsp;&nbsp;   Over All Margin:  ${f:h(margin)}%</P>
<p>
Material Cost: ${f:h(materialCost)} &nbsp;&nbsp;&nbsp;&nbsp;
Labor Cost: ${f:h(laborCost)} &nbsp;&nbsp;&nbsp;&nbsp; Other Cost: ${f:h(otherCost)}
</p>
<div class="table-responsive">
	<table class="table  table-striped">
		<thead>
			<tr>
				<th>Invoice #</th>
				<th>Address</th>
				<th>Sign Date</th>
				<th>Sales</th>
				<th>Installers</th>
				<th>Profit Margin</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="i" items="${invoices}">
			<tr>
				<td><a href="/invoice/editInvoice?invoiceKey=${f:h(i.key)}">${f:h(i.invoiceNumber)}</a></td>
				<td>${f:h(i.address)}</td>
				<td>${f:h(i.signDateStr)}</td>
				<td>${f:h(i.sales)}</td>
				<td>${f:h(i.installSummaryStr)}</td>
				<td>${f:h(i.invoiceReportRef.model.profitMargin)}%</td>
			</tr>
		</c:forEach>

		</tbody>
	</table>
</div>