<%@include file="../includes/taglib.jsp"%>

<!-- for new invoice -->
<form name ="invoiceDetailInfoNewForm" typerole="form" method="post" action="/invoice/invoiceCreateAction">
	
	<jsp:include page="./invoice-detail-edit.jsp" flush="true"></jsp:include>
	<!-- order item DIV -->
	<jsp:include page="../order-item.jsp" flush="true"></jsp:include>
	
	<div class="form-group">
		<button type="submit" class="btn btn-default">Submit</button>
		<button btnAction="invoiceEditClose" class="btn btn-default">Close</button>
	</div>
	
</form>
