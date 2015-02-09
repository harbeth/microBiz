
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>
<div class="row">
<h3> Report Labor Hours</h3>
</div>

<%@include file="job-info-common.jsp"%>

<div class="row">

	<form name="jobReportForm" typerole="form" method="post"
		action="${f:url('/jobReport/jobLaborReportAction')}">
		<div class="col-lg-6">

			<input type="hidden" name="jobKey" value="${f:h(job.key)}" />


		

		<div class="form-group input-group">
			<span class="input-group-addon">Travel Hours:</span> <input
				type="text" ${f:text("travelHours")} class="form-control" />
		</div>

		<div class="form-group input-group">
			<span class="input-group-addon">Working Hours:</span> <input
				type="text" ${f:text("workingHours")} class="form-control" />
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon">Notes</span> <input type="text"
				${f:text("note")} class="form-control" />
		</div>

		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</div>
</div>

</div>
