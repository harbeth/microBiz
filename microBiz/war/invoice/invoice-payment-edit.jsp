<%@include file="../includes/taglib.jsp"%>

 <div class="col-lg-12">
	<div class="panel panel-success">
	<div class="panel-body">
		<form id="invoiceDetailPaymentDetailForm" role="form" method="post" action="${f:url('/invoice/invoicePaymentEditAction')}">
		    <jsp:include page="../common/payment-edit-common.jsp" flush="true"></jsp:include>
			<div class="form-group">
				<button type="submit" class="btn btn-default">Submit</button> 
				${f:nbsp('     ')}
				<a link="invoiceEditClosePayment" invoiceKey="${f:h(invoiceKey)}" class="btn btn-default " role="button">Close</a>
			</div>
		</form>
	</div>
	</div>
</div>
