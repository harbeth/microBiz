
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		jobReportFn.init();
	});
</script>

<div id="jobReportEditDIV"></div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Jobs To Report</div>
			<div class="panel-body">
				<ul>


					<c:forEach var="j" items="${jobs}">
						<li>
							<p class="fa fa-th"> ${f:h(j.invoiceRef.model.invoiceNumber)}</p>
							${f:nbsp('     ')} 
							<p class="fa fa-home">${f:h(j.invoiceRef.model.address)} </p>${f:nbsp('     ')}
							<p class="fa fa-calendar"> ${f:h(j.startingDateStr)} </p>${f:nbsp('     ')} 
							<a link="jobReport"
							jobKey="${f:h(j.key)}" data-toggle="tab" aria-expanded="false"
							class="btn btn-default btn-sm" role="button">Job Report</a>
							<ul>
								<c:forEach var="jr" items="${j.jobReportListRef.modelList}">
									<li><a link="jobReportEdit" jobReportKey="${f:h(jr.key)}"
										data-toggle="tab" aria-expanded="false"
										class="btn btn-default btn-sm" role="button">Edit</a>
										${f:h(jr.reportDateStr)} ${f:nbsp('   ')} Working Hrs:
										${f:h(jr.workingHours)} ${f:nbsp('   ')} Travel Hrs:
										${f:h(jr.travelHours)}<br> ${f:h(jr.materialReportStr)}<br>
										Notes: ${f:h(jr.note)}</li>
								</c:forEach>
							</ul>


						</li>
					</c:forEach>

				</ul>
			</div>
		</div>
	</div>
</div>
