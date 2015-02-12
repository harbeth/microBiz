<%@include file="../includes/taglib.jsp"%>

<!-- for edit invoice -->
<div class="col-lg-12">
	<div class="well">
<form name ="invoiceDetailInfoEditForm" typerole="form" method="post" action="/invoice/invoiceEditAction">
	<jsp:include page="./invoice-detail-edit.jsp" flush="true"></jsp:include>
	<div class="form-group">
		<button type="submit" class="btn btn-default">Submit</button>
	</div>
</form>
</div>
</div>