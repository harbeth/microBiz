<%@include file="../includes/taglib.jsp"%>



<div class="row">
	<div class="col-lg-12">
		<h2>Inventory Details For Product:${f:h(product.model)}  Current Quantity :  ${f:h(product.currentQty)}${f:nbsp('  ')} ${f:h(product.purchaseUnit)}  </h2> 
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Date</th>
						<th>Change Reason</th>
						<th>Notes</th>
						<th>Oringinal Quantity</th>
						<th>Change Quantity</th>
						<th>New Quantity</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="ic" items="${icList}">
						<tr>
						
							<td>${f:h(ic.changeDate)}</td>
							<td>${f:h(ic.reasons)} </td>
							<td>${f:h(ic.notes)} </td>
							<td>${f:h(ic.originalQty)} </td>
							<td>${f:h(ic.changeQty)} </td>
							<td>${f:h(ic.newQty)} </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->



