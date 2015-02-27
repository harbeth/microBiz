<%@include file="../includes/taglib.jsp"%>

<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Supplier</h1>
	</div>
</div>
<!-- /.row -->

<!-- Page conent -->
<div class="row">

	<form role="form" method="post" action="${f:url('/prd/supplierAction')}">
		<div class="col-lg-6">
			<c:if test="${key!=null}">
				<input type="hidden" ${f:hidden("key")} />

			</c:if>

			<div class="form-group input-group">
				<span class="input-group-addon">Name</span>
				<input type="text" ${f:text("name")} class="form-control" />
			</div>
	
			<div class="form-group">
				<label>Active</label>
				<label class="checkbox-inline">
					<input type="checkbox" ${f:checkbox("active")}>yes
				</label>
			</div>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</form>
</div>

<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<h2>All Suppliers</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Name</th>

						<th>Active</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="s" items="${suppliers}">
						<tr>
							<td>${f:h(s.name)}</td>

							<td>${f:h(s.active)}</td>
							<td><a href="/prd/supplier?key=${f:h(s.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->



