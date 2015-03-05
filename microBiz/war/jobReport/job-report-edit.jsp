
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>


<jsp:include page="./job-info-common.jsp" flush="true"></jsp:include>
<form name="jobReportForm" typerole="form" method="post"
	action="${f:url('/jobReport/jobReportEditAction')}">


	<input type="hidden" ${f:hidden("key")} />

	<jsp:include page="./job-report-common.jsp" flush="true"></jsp:include>
</form>

