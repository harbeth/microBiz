
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<h3>Job Report</h3>
</div>

<jsp:include page="job-info-common.jsp" flush="true"></jsp:include>



<div class="col-lg-12">
<div class="well">
	<form name="jobReportForm" typerole="form" method="post"
		action="${f:url('/jobReport/installerJobReportNewAction')}">
<input type="hidden" name="jobKey" value="${f:h(job.key)}" />
		
<jsp:include page="job-report-common.jsp" flush="true"></jsp:include>

