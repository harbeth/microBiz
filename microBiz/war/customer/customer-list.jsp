<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	customerFn.init();
	});
</script>

<!-- /.row -->
<div class="row">
	<div class="panel-heading">
		<a link="customerNew" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">New Customer</a>
	</div>
	<div class="col-lg-12">
		<h2>All Customers</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Type</th>
						<th>Phone</th>
						<th>Address</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${customers}">
						<tr>
							<td><a link="customerEditDetail" customerKey="${f:h(c.key)}">${f:h(c.name)}</a></td>
							<td>${f:h(c.type)}</td>
							<td>${f:h(c.phone)}</td>
							<td>${f:h(c.address)}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->
