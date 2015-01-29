<%@include file="../includes/taglib.jsp"%>

<!-- for quote new, details and order item in the same page -->
<form name ="quoteDetailInfoNewForm" typerole="form" method="post" action="/quote/quoteCreateAction">
	
	<jsp:include page="./quote-detail-edit.jsp" flush="true"></jsp:include>
	<!-- order item DIV -->
	<jsp:include page="./order-item.jsp" flush="true"></jsp:include>
	
	<div class="form-group">
		<button type="submit" class="btn btn-default">Submit</button>
		<button btnAction="quoteEditClose" class="btn btn-default">Close</button>
	</div>
</form>