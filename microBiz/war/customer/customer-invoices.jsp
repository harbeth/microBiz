<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-12">
    <div class="well">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>Invoice #</th>
						<th>Address</th>
						<th>Create Date</th>
						<th>Sales</th>
						<th>Status</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${invoices}">
						<tr>
							<td>${f:h(i.invoiceNumber)}</td>
							<td>${f:h(i.address)}</td>
							<td>${f:h(i.createdAtStr)}</td>
							<td>${f:h(i.sales)}</td>
							<td>${f:h(i.statusLable)}</td>
							<td><a href="/invoice/editInvoice?invoiceKey=${f:h(i.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
			
