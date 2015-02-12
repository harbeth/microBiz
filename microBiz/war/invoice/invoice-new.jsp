<%@include file="../includes/taglib.jsp"%>

<!-- for new invoice -->
<div class="col-lg-12">
	<div class="well"> 
<form name ="invoiceDetailInfoNewForm" typerole="form" method="post" action="/invoice/invoiceCreateAction">
	
	<jsp:include page="./invoice-detail-edit.jsp" flush="true"></jsp:include>
	<!-- order item DIV -->
	<jsp:include page="../common/order-item.jsp" flush="true"></jsp:include>
	
	<div class="form-group">
		<button type="submit" class="btn btn-default">Submit</button>
		<button btnAction="invoiceEditClose" class="btn btn-default">Close</button>
	</div>
	
</form>
</div>
</div>