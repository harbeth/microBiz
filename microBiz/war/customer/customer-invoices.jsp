<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">Invoices List</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Invoice #</th>
						<th>Address</th>


						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${invoices}">
						<tr>
							<td>${f:h(i.invoiceNumber)}</td>
							<td>${f:h(i.address)}</td>


							<td><a href="/editInvoice?invoiceKey=${f:h(i.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
			