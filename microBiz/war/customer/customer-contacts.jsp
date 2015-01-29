<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">Contact List</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Type</th>
						<th>Phone</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${contacts}">
						<tr>
							<td>${f:h(c.name)}</td>
							<td>${f:h(c.type)}</td>
							<td>${f:h(c.phone)}</td>
							<td>${f:h(c.email)}</td>
							<td><a link="customerContactEdit" customerKey="${f:h(customer.key)}" contactKey="${f:h(c.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
			