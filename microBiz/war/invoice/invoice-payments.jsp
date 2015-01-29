<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">Payment List</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Method</th>
						<th>Amount</th>
						<th>Date</th>
						<th>Note</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${payments}">
						<tr>
							<td>${f:h(p.method)}</td>
							<td>${f:h(p.amount)}</td>
							<td>${f:h(p.enterDateStr)}</td>
							<td>${f:h(p.note)}</td>
							<td><a link="invoicePaymentEdit" invoiceKey="${f:h(invoice.key)}" paymentKey="${f:h(p.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
			