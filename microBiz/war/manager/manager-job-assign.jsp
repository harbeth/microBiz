<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
<h4>Assign Job <small> Inv#: ${f:h(invoice.invoiceNumber)} ${f:nbsp('     ')} Address: ${f:h(invoice.address)}</small></h4>
	<form name="managerJobDetailForm" role="form" method="post" action="${f:url('/manager/managerInvoiceJobAssignAction')}">
		<jsp:include page="../common/job-edit-common.jsp" flush="true">
			<jsp:param name="closeBtnShown" value="n" />
		</jsp:include>
	</form>
</div>
