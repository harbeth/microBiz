<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	productFn.init();
	});
</script>


<div class="row">
	<div class="panel-heading">
		<a link="productNew" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">New Product</a>
	</div>
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<h2>All Products</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Model</th>
						<th>Type</th>
						<th>Selling Price</th>
						<th>Purchase Cost</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${prds}">
						<tr>
							<td><a link="productEdit" productKey="${f:h(p.key)}">${f:h(p.model)}</a></td>
							<td>${f:h(p.type)}</td>
							<td>${f:h(p.sellingRate)}per ${f:h(p.sellingUnit)}</td>
							<td>${f:h(p.purchaseRate)}per ${f:h(p.purchaseUnit)}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->



