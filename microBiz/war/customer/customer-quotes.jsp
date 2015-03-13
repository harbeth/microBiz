<%@include file="../includes/taglib.jsp"%>

	<div class="col-lg-12">
                    <div class="well">
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Address</th>
						<th>Created Date</th>
						<th>Creator</th>
						<th>Status</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="q" items="${quotes}">
						<tr>
							<td>${f:h(q.address)}</td>
							<td>${f:h(q.createDateStr)}</td>
							<td>${f:h(q.creatorName)}</td>
							<td>${f:h(q.statusLable)}</td>
							<td><a href="/quote/editQuote?quoteKey=${f:h(q.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
			