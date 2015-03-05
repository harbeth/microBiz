<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	invoiceFn.init();
	});
</script>

<!-- /.row -->
<div class="row">
	<div class="panel-heading">
		<a link="invoiceNew" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">New Invoice</a>
	</div>
	<div class="col-lg-12">
		<h2>All Invoices</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Invoice #</th>
						<th>Customer</th>
						<th>Address</th>
						<th>Preferred Install At</th>
						<th>Sales</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${invoices}">
						<tr>
							<td><a link="invoiceEditDetail" invoiceKey="${f:h(i.key)}">${f:h(i.invoiceNumber)}</a></td>
							<td>${f:h(i.customerRef.model.name)}</td>
							<td>${f:h(i.address)}</td>
							<td>${f:h(i.preferIntlDateStr)}</td>
							<td>${f:h(i.sales)}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->
