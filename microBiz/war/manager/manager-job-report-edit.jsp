
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>


<jsp:include page="../jobReport/job-info-common.jsp" flush="true"></jsp:include>

<form name="jobReportForm" typerole="form" method="post"
	action="${f:url('/manager/managerJobReportEditAction')}">


	<input type="hidden" ${f:hidden("jobReportKey")} />

	<jsp:include page="../jobReport/job-report-common.jsp" flush="true"></jsp:include>
</form>

