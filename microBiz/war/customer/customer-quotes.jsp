<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">Quote List</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Address</th>
						<th>Version Count</th>
						<th>Note</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="q" items="${quotes}">
						<tr>
							<td>${f:h(q.address)}</td>
							<td>${f:h(q.count)}</td>
							<td>${f:h(q.note)}</td>
							<td><a href="/editQuote?quoteKey=${f:h(q.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
			