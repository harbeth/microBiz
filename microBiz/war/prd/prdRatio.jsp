<%@include file="includes/header.jsp"%>

<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<small>Ratio for Product Consume Purchase </small>
		</h1>

	</div>
</div>
<!-- /.row -->

<!-- Page conent -->
<div class="row">
	<div class="col-lg-6">
		<form role="form" method="post" action="${f:url('prdRatioAction')}">
			<c:if test="${key!=null}">
				<input type="hidden" ${f:hidden("key")} />

			</c:if>

			<div class="form-group">
				<input type="text" ${f:text("desc")} class="form-control"
					placeholder="Description" />
			</div>

			<div class="form-group">
				<input type="text" ${f:text("ratio")} class="form-control"
					placeholder="Ratio" />
			</div>

			<div class="form-group">
				<label>Active</label>
				<label class="checkbox-inline"> <input
					type="checkbox" ${f:checkbox("active")} >
					yes 
					</label>
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>

		</form>

	</div>
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-6">
		<h2>All Ratios</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Description</th>
						<th>Ratio</th>
						<th>Active</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${prdRs}">
						<tr>
							<td>${f:h(p.desc)}</td>
							<td>${f:h(p.ratio)}</td>
							<td>${f:h(p.active)}</td>
							<td><a href="/prdRatio?key=${f:h(p.key)}">Edit</a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->
<%@include file="includes/footer.jsp"%>
