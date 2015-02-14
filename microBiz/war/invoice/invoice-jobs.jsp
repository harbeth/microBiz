<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-12">
	<div class="well">
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Installer</th>
						<th>Starting Date</th>
						<th>Arrival Time</th>
						<th>Note</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="j" items="${jobs}">
						<tr>
							<td>${f:h(j.installerRef.model.name)}</td>
							<td>${f:h(j.startingDateStr)}</td>
							<td>${f:h(j.arrivalTime)}</td>
							<td>${f:h(j.note)}</td>
							<td><a link="invoiceJobEdit" invoiceKey="${f:h(invoice.key)}" jobKey="${f:h(j.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
			