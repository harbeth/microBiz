<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	jobReportFn.init();
	});
</script>



<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<h2>Jobs to Report</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Customer Name</th>
						<th>Address</th>
						<th>Job Start Date</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="j" items="${jobs}">
						<tr>
						
							<td>${f:h(j.invoiceRef.model.customerRef.model.name)}</td>
							<td>${f:h(j.invoiceRef.model.address)} </td>
							<td>${f:h(j.startDateStr)} </td>
							<td>
							<a link="reportJob" jobKey="${f:h(j.key)}" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Add Inventory</a>
							 ${f:nbsp('  ')}
							<a link="jobReportDetails" jobKey="${f:h(j.key)}" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Details</a>
							</td>
							

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->



