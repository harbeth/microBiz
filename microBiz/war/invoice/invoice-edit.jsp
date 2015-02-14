<%@include file="../includes/taglib.jsp"%>

<!-- for edit invoice 
btnAction="invoiceEditSubmit"
-->
<form name ="invoiceDetailInfoEditForm" typerole="form" type="post" action="/invoice/invoiceEditAction">
	<jsp:include page="./invoice-detail-edit.jsp" flush="true"></jsp:include>
	<div class="form-group">
		<button type="submit" class="btn btn-default">Submit</button>
	</div>
</form>