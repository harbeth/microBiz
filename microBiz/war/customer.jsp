<%@include file="includes/header.jsp"%>


	<div class="col-lg-12">
		<h1 class="page-header">
			Customer 
		</h1>

	</div>



	<form role="form" method="post" action="${f:url('customerAction')}">
		<div class="col-lg-6">
			<c:if test="${key!=null}">
				<input type="hidden" ${f:hidden("key")} />
			</c:if>

			<div class="form-group input-group">
				<span class="input-group-addon">Customer Type</span> <select
					name="type" class="form-control">
					<c:forEach items="${cxTypes}" var="pt">
						<option value="${f:h(pt)}"
							<c:if test = "${f:h(type) == f:h(pt)}" >
								selected
							</c:if>>${f:h(pt)}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Customer Name</span> <input
					type="text" ${f:text("name")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Phone</span> <input type="text"
					${f:text("phone")} class="form-control" />
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Alt.Phone</span> <input type="text"
					${f:text("altPhone")} class="form-control" />
			</div>

			<div class="form-group">
				<label>Active</label> <label class="checkbox-inline"> <input
					type="checkbox" ${f:checkbox("active")}> yes
				</label>
			</div>


		</div>
		<div class="col-lg-6">
			<div class="form-group input-group">
				<span class="input-group-addon">Address</span> <input type="text"
					${f:text("address")} class="form-control" />
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Email</span> <input type="text"
					${f:text("email")} class="form-control" />
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Rating</span> <select
					name="purchaseUnit" class="form-control">
					<c:forEach items="${ratings}" var="r">
						<option value="${f:h(r)}"
							<c:if test = "${f:h(rating) == f:h(r)}" >
								selected
							</c:if>>${f:h(r)}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Notes</span>
				<textarea class="form-control" rows="2"></textarea>
			</div>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</form>

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
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${customers}">
						<tr>
							<td>${f:h(c.name)}</td>
							<td>${f:h(c.type)}</td>
							<td>${f:h(c.phone)}</td>
							<td>${f:h(c.address)}</td>
							<td><a href="/customerDetails?key=${f:h(c.key)}">Details</a> <a href="/customer?key=${f:h(c.key)}">Edit</a>  </td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


<%@include file="includes/footer.jsp"%>
