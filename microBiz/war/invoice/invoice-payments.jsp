<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-12">
	<div class="well">
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
							<td>${f:h(p.methodLable)}</td>
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
			