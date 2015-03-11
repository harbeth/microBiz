<%@include file="../includes/taglib.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		dashboardFn.init();
	});
</script>
<div class="panel panel-yellow">
	<div class="panel-heading">
	   Unapproved Job Reports
	</div>
	<div class="panel-body">
	<div id="dashboardWorkingAreaDIV"></div>
	</div>

	<div class="panel-footer">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>Invoice #</th>
						<th>Customer</th>
						<th>Address</th>
						<th>Installer</th>
						<th>Tr.Hr</th>
						<th>Wk.Hr</th>
						<th>Material</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="jr" items="${jobReports}">
						<tr>
							<td>${f:h(jr.jobRef.model.invoiceRef.model.invoiceNumber)}</td>
							<td>${f:h(jr.jobRef.model.invoiceRef.model.customerRef.model.name)}</td>
							<td>${f:h(jr.jobRef.model.invoiceRef.model.address)}</td>
							<td>${f:h(jr.jobRef.model.installer)}</td>
							<td>${f:h(jr.travelHours)}</td>
							<td>${f:h(jr.workingHours)}</td>
							<td>${f:h(jr.materialReportStr)}</td>
							<td><a link="managerJobReportEdit"
								jobReportKey="${f:h(jr.key)}" data-toggle="tab"
								aria-expanded="false" class="btn btn-default btn-sm"
								role="button">Verify</a> </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- /.panel -->
	</div>
</div>