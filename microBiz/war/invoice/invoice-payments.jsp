<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-12">
	<div class="panel panel-success">
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						
						<th>Payment Amount</th>
						<th>Date</th>
						<th>Note</th>
						<th>Canceled</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${payments}">
						<tr>
							<td>${f:h(p.amount)} by ${f:h(p.methodLable)}</td>
							<td>${f:h(p.enterDateStr)}</td>
							<td>${f:h(p.note)}</td>
							<td>${f:h(p.canceled)}</td>
							<td><a link="invoicePaymentEdit" invoiceKey="${f:h(invoice.key)}" paymentKey="${f:h(p.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</div>
</div>
			