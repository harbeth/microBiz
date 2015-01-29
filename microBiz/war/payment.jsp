<%@include file="includes/header.jsp"%>

<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			Assign Job <small> for:  ${f:h(invoice.invoiceNumber)} </small>
		</h1>

	</div>
</div>
<!-- /.row -->

<!-- Page content 

<div class="form-group input-group">
				<span class="input-group-addon">Amount</span>
				<input type="text" $ { f:text("amount")} class="form-control" />
			</div>
-->
<div class="row">

	<form role="form" method="post" action="${f:url('jobAction')}">
		<div class="col-lg-6">
		    <input type="hidden" name="invoiceKey" value="${f:h(invoice.key)}" />
			<c:if test="${key != null}">
				<input type="hidden" ${f:hidden("key")} />
			</c:if>
			
			<div class="form-group input-group">
				<span class="input-group-addon">Method</span>
				<input type="text" ${f:text("method")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Notes</span> 
				<input type="text" ${f:text("note")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">User</span> 
				<input type="text" ${f:text("user")} class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</form>
</div>

<%@include file="includes/footer.jsp"%>
