<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	quoteFn.init();
	});
</script>

<!-- /.row -->
<div class="row">
	<div class="panel-heading">
		<a link="quoteNew" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">New Quotation</a>
	</div>
	<div class="col-lg-12">
		<h2>All Quotations</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						
						<th>Address</th>
						<th>Customer</th>

						<th>Created At</th>
						<th>Sales Email</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="q" items="${quotes}">
						<tr>
							<td><a link="quoteEditDetail" quoteKey="${f:h(q.key)}">${f:h(q.address)}</a></td>
							<td>${f:h(q.customerRef.model.name)}</td>
					

							<td>${f:h(q.createDateStr)}</td>
							<td>${f:h(q.createdEmail)}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->
