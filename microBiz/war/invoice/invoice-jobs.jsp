<%@include file="../includes/taglib.jsp"%>
<div class="col-lg-12">
	<div class="panel panel-info">
	
	<h4>${f:nbsp('   ')}  All Jobs</h4>	
	<div class="panel-body">
		<ul>
		<c:forEach var="j" items="${jobs}">
			<li>
				Installer:  ${f:h(j.installerRef.model.name)} ${f:nbsp('     ')} 
				<span class="fa fa-calendar"> ${f:h(j.startingDateStr)} </span>
				${f:nbsp('     ')} 
				<strong>${f:h(j.statusLable)}</strong>
				<ul>
				<c:forEach var="jr" items="${j.jobReportListRef.modelList}">
					<li>
						<strong>${f:h(jr.statusLable)}</strong> ${f:nbsp('   ')}
						by  ${f:nbsp(' ')} ${f:h(jr.createdEmail)}  ${f:nbsp('   ')} 
						on  ${f:nbsp(' ')} ${f:h(jr.reportDateStr)} ${f:nbsp('   ')} Wk Hrs:
						${f:h(jr.workingHours)} ${f:nbsp('   ')} Trv Hrs:
						${f:h(jr.travelHours)} ${f:nbsp('   ')} Notes: ${f:h(jr.note)} <br>
						${f:nbsp('     ')} Material Used:${f:nbsp(' ')}   ${f:h(jr.materialReportStr)}<br>
					</li>
				</c:forEach>
				</ul>
			</li>
		</c:forEach>
		</ul>
		</div>
	</div>
</div>