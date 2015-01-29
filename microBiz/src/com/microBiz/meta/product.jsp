<%@include file="../includes/taglib.jsp"%>
<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Product </h1>

	</div>
</div>
<!-- /.row -->

<!-- Page conent -->
<div class="row">

	<form role="form" method="post" action="${f:url('/prd/productAction')}">
		<div class="col-lg-6">
			<c:if test="${key!=null}">
				<input type="hidden" ${f:hidden("key")} />

			</c:if>

			<div class="form-group input-group">
				<span class="input-group-addon">Product Type</span> <select
					name="type" class="form-control">
					<c:forEach items="${prdTypes}" var="pt">
						<option value="${f:h(pt)}"
							<c:if test = "${f:h(type) == f:h(pt)}" >
								selected
							</c:if>>${f:h(pt)}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Name/Model</span> <input type="text"
					${f:text("model")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Description</span> <input
					type="text" ${f:text("desc")} class="form-control" />
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Supplier</span> <select
					name="supplier" class="form-control">
					<c:forEach items="${suppliers}" var="s">
						<option value="${f:h(s)}"
							<c:if test = "${f:h(supplier) == f:h(s)}" >
								selected
							</c:if>>${f:h(s)}</option>
					</c:forEach>
				</select>
			</div>



			<div class="form-group">
				<label>Active</label> <label class="checkbox-inline"> <input
					type="checkbox" ${f:checkbox("active")}> yes
				</label>
			</div>


		</div>
		<div class="col-lg-6">
		
		
			<div class="form-group input-group">
				<span class="input-group-addon">Selling Rate</span> <input
					type="text" ${f:text("sellingRate")} class="form-control" />
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Selling Unit</span> <select
					name="sellingUnit" class="form-control">
					<c:forEach items="${units}" var="u1">
						<option value="${f:h(u1)}"
							<c:if test = "${f:h(sellingUnit) == f:h(u1)}" >
								selected
							</c:if>>${f:h(u1)}</option>
					</c:forEach>
				</select>


			</div>


			<div class="form-group input-group">
				<span class="input-group-addon">Consume Report Unit</span> <select
					name="consumeReportUnit" class="form-control">
					<c:forEach items="${units}" var="u2">
						<option value="${f:h(u2)}"
							<c:if test = "${f:h(consumeReportUnit) == f:h(u2)}" >
								selected
							</c:if>>${f:h(u2)}</option>
					</c:forEach>
				</select>


			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Purchase Cost</span> <input
					type="text" ${f:text("purchaseRate")} class="form-control" />
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Purchase Unit</span> <select
					name="purchaseUnit" class="form-control">
					<c:forEach items="${units}" var="u3">
						<option value="${f:h(u3)}"
							<c:if test = "${f:h(purchaseUnit) == f:h(u3)}" >
								selected
							</c:if>>${f:h(u3)}</option>
					</c:forEach>
				</select>


			</div>





		</div>

		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
			<button type="reset" class="btn btn-default" >Reset</button>

		</div>
	</form>

</div>
</div>

<div class="row">
	<div class="panel-heading">
		<a href="/prd/product" data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">New Product</a>
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
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${prds}">
						<tr>
							<td>${f:h(p.model)}</td>
							<td>${f:h(p.type)}</td>
							<td>${f:h(p.sellingRate)}per ${f:h(p.sellingUnit)}</td>
							<td>${f:h(p.purchaseRate)}per ${f:h(p.purchaseUnit)}</td>
							<td><a href="/prd/product?key=${f:h(p.key)}">Edit</a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->



