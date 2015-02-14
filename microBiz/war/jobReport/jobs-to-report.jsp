
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	jobReportFn.init();
	});
</script>


<div class="row">
	<div class="col-lg-12">
		<h3>Jobs To Report</h3>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Invoice Number</th>
						<th>Customer</th>
						<th>Address</th>
						<th>Job Start Date</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="j" items="${jobs}">
						<tr>
						
							<td>${f:h(j.invoiceRef.model.invoiceNumber)}</td>
							<td>${f:h(j.invoiceRef.model.customerRef.model.name)} </td>
							<td>${f:h(j.invoiceRef.model.address)}</td>
							<td>${f:h(j.startingDateStr)}</td>
							<td>
							<a link="jobMaterialReport" jobKey="${f:h(j.key)}" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Job Material Report</a>
							 ${f:nbsp('  ')}
							 <a link="jobLaborReport" jobKey="${f:h(j.key)}" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Job Labour Report</a>
							 ${f:nbsp('  ')}
							<a link="jobReportDetails" jobKey="${f:h(j.key)}" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Reports of This Job</a>
							</td>
							

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>