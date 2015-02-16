
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<h3>Job Report</h3>
</div>

<jsp:include page="./job-info-common.jsp" flush="true"></jsp:include>


<div class="col-lg-12">
<div class="well">
	<form name="jobReportForm" typerole="form" method="post"
		action="${f:url('/jobReport/jobReportEditAction')}">
		<div class="col-lg-6">


		
				<input type="hidden" ${f:hidden("key")} />
				<c:if test="${fromManager}">
					<input type="hidden" name="fromManager" value="yes" />
				</c:if>
		

<jsp:include page="./job-report-common.jsp" flush="true"></jsp:include>
