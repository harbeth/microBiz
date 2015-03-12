<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	invoiceNewFn.init();
	});
</script>

<!-- for new invoice -->
<form name ="invoiceDetailInfoNewForm" typerole="form" method="post" action="/invoice/invoiceCreateAction">
	<div class="col-lg-12">
		<div class="panel panel-primary">
		<div class="panel-heading">
			Create New Invoice
		</div>
		
		<div class="panel-body">
			<jsp:include page="./invoice-detail-edit.jsp" flush="true"></jsp:include>
			<!-- order item DIV -->
			<jsp:include page="../common/order-item.jsp" flush="true"></jsp:include>
			<div class="form-group">
				<button btnAction="invoiceCreateSubmit" class="btn btn-default">Submit</button>
				<button btnAction="invoiceEditClose" class="btn btn-default">Close</button>
			</div>
		</div>
		</div>
	</div>
</form>