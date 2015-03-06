<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-12">
	<div class="panel panel-info">
		<div class="panel-body">
			<form id="invoiceDetailJobDetailForm" role="form" method="post" action="${f:url('/invoice/invoiceJobEditAction')}">
				<jsp:include page="../common/job-edit-common.jsp" flush="true">
					<jsp:param name="closeBtnShown" value="y" />
				</jsp:include>
			</form>
		</div>
	</div>
</div>