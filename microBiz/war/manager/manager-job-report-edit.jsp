
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>


<jsp:include page="../jobReport/job-info-common.jsp" flush="true"></jsp:include>

<form name="jobReportForm" typerole="form" method="post"
	action="${f:url('/manager/managerJobReportEditAction')}">


	<input type="hidden" ${f:hidden("jobReportKey")} />
	<div class="form-group">
		<input type="radio" name="action" value="approve" checked>Approve
		${f:nbsp('  ')} <input type="radio" name="action" value="void">Void
		${f:nbsp('  ')} <input type="radio" name="action" value="approveWithChange">Change And Approve
		to Invoice
	</div>

	<jsp:include page="../jobReport/job-report-common.jsp" flush="true"></jsp:include>
</form>

