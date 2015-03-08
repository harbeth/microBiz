<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
	<h4>Receive Payment: <small>Inv#: ${f:h(invoice.invoiceNumber)} ${f:nbsp('     ')} Address: ${f:h(invoice.address)}</small></h4>
	<form name="managerPaymentDetailForm" role="form" method="post" action="${f:url('/manager/managerPaymentEditAction')}">
	    <jsp:include page="../common/payment-edit-common.jsp" flush="true"></jsp:include>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button> 
			${f:nbsp('     ')}
		</div>
	</form>
</div>