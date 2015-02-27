<%@include file="../includes/taglib.jsp"%>

<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Role Access Right</h1>
	</div>
</div>
<!-- /.row -->

<!-- Page conent -->
<div class="row">

	<form role="form" method="post" action="${f:url('/mgmt/miRoleAccessRightAction')}">
		<div class="col-lg-6">
			<div class="form-group input-group">
				<span class="input-group-addon">Role</span>
				<select name="miRole" class="form-control">
					<c:forEach items="${roles}" var="r">
						<option value="${f:h(r)}"
							<c:if test = "${f:h(miRole) == f:h(r)}" >
								selected
							</c:if>>${f:h(r)}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Accessible Module</span>
				<select name="accessibleModule" class="form-control">
					<c:forEach items="${modules}" var="m">
						<option value="${f:h(m)}"
							<c:if test = "${f:h(accessibleModule) == f:h(m)}" >
								selected
							</c:if>>${f:h(m)}</option>
					</c:forEach>
				</select>
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
		<h2>Role Access Right</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Role</th>
						<th>Access Right</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rr" items="${roleRights}">
						<tr>
							<td>${f:h(rr.miRole)}</td>
							<td>${f:h(rr.accessibleModule)}</td>
							<td><a href="/mgmt/miRoleAccessRight?key=${f:h(rr.key)}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->

