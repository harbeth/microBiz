<%@include file="../includes/taglib.jsp"%>
<script type ="text/javascript">
    $(document).ready(function() {
    	inventoryFn.init();
	});
</script>


<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<h2>All Products</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Model</th>
						<th>Quantity</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${prds}">
						<tr>

							<td>${f:h(p.model)}</td>
							<td>${f:h(p.currentQyt)} ${f:h(p.unitLable)}</td>
							<td>list details  add Quantity</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->



