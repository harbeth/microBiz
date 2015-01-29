<%@include file="../includes/taglib.jsp"%>

<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">User </h1>

	</div>
</div>
<!-- /.row -->

<!-- Page conent -->
<div class="row">

	<form role="form" method="post" action="${f:url('/mgmt/miUserAction')}">
		<div class="col-lg-6">
			<c:if test="${key!=null}">
				<input type="hidden" ${f:hidden("key")} />

			</c:if>


			<div class="form-group input-group">
				<span class="input-group-addon">Name</span> <input type="text"
					${f:text("name")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Email</span> <input
					type="text" ${f:text("email")} class="form-control" />
			</div>


			<div class="form-group">
				<label>Active</label> <label class="checkbox-inline"> <input
					type="checkbox" ${f:checkbox("active")}> yes
				</label>
			</div>


		</div>
		<div class="col-lg-6">
			<div class="form-group input-group">
				<span class="input-group-addon">Phone</span> <input
					type="text" ${f:text("phone")} class="form-control" />
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Role</span> <select
					name="role" class="form-control">
					<c:forEach items="${roles}" var="r">
						<option value="${f:h(r)}"
							<c:if test = "${f:h(role) == f:h(r)}" >
								selected
							</c:if>>${f:h(r)}</option>
					</c:forEach>
				</select>


			</div>




	

		</div>

		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</form>

</div>
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<h2>All Users</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Email</th>
						<th>Role</th>
						<th>Phone</th>
						<th>Active</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="u" items="${users}">
						<tr>
							<td>${f:h(u.name)}</td>
							<td>${f:h(u.email)}</td>
							<td>${f:h(u.role)}</td>
							<td>${f:h(u.phone)}</td>
							<td>${f:h(u.active)}</td>
							<td><a href="/mgmt/miUser?key=${f:h(u.key)}">Edit</a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->



