
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<h3>Job Report</h3>
</div>

<jsp:include page="../jobReport/job-info-common.jsp" flush="true"></jsp:include>


<div class="col-lg-12">
<div class="well">
	<form name="jobReportForm" typerole="form" method="post"
		action="${f:url('/manager/managerJobReportEditAction')}">
		

		<input type="hidden" ${f:hidden("jobReportKey")} />

<jsp:include page="../jobReport/job-report-common.jsp" flush="true"></jsp:include>
