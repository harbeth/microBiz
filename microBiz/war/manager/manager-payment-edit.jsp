<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
	<form name="managerPaymentDetailForm" role="form" method="post" action="${f:url('/manager/managerPaymentEditAction')}">
	    <jsp:include page="../common/payment-edit-common.jsp" flush="true"></jsp:include>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button> 
			${f:nbsp('     ')}
		</div>
	</form>
</div>