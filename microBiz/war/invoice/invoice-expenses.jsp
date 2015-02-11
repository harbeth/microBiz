<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">Expense List</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Expense</th>
						<th>Report Date</th>
						<th>Note</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${expenses}">
						<tr>
							<td>${f:h(e.expense)}</td>
							<td>${f:h(e.reportDateStr)}</td>
							<td>${f:h(e.note)}</td>
							<td><a link="invoiceExpenseEdit" invoiceKey="${f:h(invoice.key)}" expenseKey="${f:h(e.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
			