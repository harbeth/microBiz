<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	inventoryFn.init();
	});
</script>



<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<h2>Inventories</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Model</th>
						<th>Description</th>
						<th>Quantity</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${prds}">
						<tr>
						
							<td>${f:h(p.model)}</td>
							<td>${f:h(p.desc)} </td>
							<td>${f:h(p.currentQty)} ${f:nbsp('  ')}  ${f:h(p.purchaseUnit)}</td>
							<td>
							<a link="inventoryAdd" productKey="${f:h(p.key)}" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Add Inventory</a>
							 ${f:nbsp('  ')}
							<a link="inventoryDetails" productKey="${f:h(p.key)}" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Details</a>
							</td>
							

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->



