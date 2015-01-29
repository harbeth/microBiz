<%@include file="../includes/taglib.jsp"%>

<form name ="quoteDetailInfoEditForm" typerole="form" method="post" action="/quote/quoteEditAction">
	<jsp:include page="./quote-detail-edit.jsp" flush="true"></jsp:include>
	<div class="form-group">
		<button type="submit" name="quoteDetailSubmit" value="" class="btn btn-default">Submit</button>
	</div>
</form>