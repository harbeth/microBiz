<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	quoteNewFn.init();
	});
</script>

<div class="col-lg-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
			Create New Quotation
		</div>
		
		<div class="panel-body">
		<!-- for quote new, details and order item in the same page -->
			<form name ="quoteDetailInfoNewForm" typerole="form" method="post" action="/quote/quoteCreateAction">
				
				<jsp:include page="./quote-detail-edit.jsp" flush="true"></jsp:include>
				<!-- order item DIV -->
				<jsp:include page="../common/order-item.jsp" flush="true"></jsp:include>
				
				<div class="form-group">
					<button type="submit" class="btn btn-default">Submit</button>
					<button btnAction="quoteEditClose" class="btn btn-default">Close</button>
				</div>
			</form>
		</div>
	</div>
</div>