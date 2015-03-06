<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
	<form name="managerJobDetailForm" role="form" method="post" action="${f:url('/manager/managerInvoiceJobAssignAction')}">
		<jsp:include page="../common/job-edit-common.jsp" flush="true">
			<jsp:param name="closeBtnShown" value="n" />
		</jsp:include>
	</form>
</div>
