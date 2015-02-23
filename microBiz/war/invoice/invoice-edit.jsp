<%@include file="../includes/taglib.jsp"%>

<form name ="invoiceDetailInfoEditForm" typerole="form" type="post" action="/invoice/invoiceEditAction">
	<jsp:include page="./invoice-detail-edit.jsp" flush="true"></jsp:include>
	<div class="form-group">
		<button type="submit" class="btn btn-default">Submit</button>
		<a link="invoiceDetailEditClose" class="btn btn-default" role="button">Close</a>
	</div>
</form>