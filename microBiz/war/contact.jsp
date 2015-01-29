<%@include file="includes/header.jsp"%>

<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			Contact <small> for:  ${f:h(customer.name)} </small>
		</h1>

	</div>
</div>
<!-- /.row -->

<!-- Page conent -->
<div class="row">

	<form role="form" method="post" action="${f:url('contactAction')}">
		<div class="col-lg-6">
		<input type="hidden" name="customerKey" value="${f:h(customer.key)}" />
			<c:if test="${key!=null}">
				<input type="hidden" ${f:hidden("key")} />
				
			

			</c:if>

			<div class="form-group input-group">
				<span class="input-group-addon">Type</span> <input
					type="text" ${f:text("type")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Name</span> <input
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
				<span class="input-group-addon">Notes</span>
				<textarea class="form-control" rows="2"></textarea>


			</div>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</form>

</div>
</div>
<!-- /.row -->



<%@include file="includes/footer.jsp"%>
