<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-12">
	<div class="panel panel-info">
		
		<div class="panel-body">
			<ul>
			<c:forEach var="j" items="${jobs}">
				<li>
					Installer:  ${f:h(j.installer)} ${f:nbsp('     ')}
					Helper:  ${f:h(j.helperNamesStr)} ${f:nbsp('     ')}  
					<span class="fa fa-calendar"> ${f:h(j.startingDateStr)} </span>
					${f:nbsp('     ')} 
					<strong>${f:h(j.statusLable)}</strong>
					${f:nbsp('     ')} 
					<c:if test="${j.editable}">
					 <a link="invoiceJobEdit" invoiceKey="${f:h(invoice.key)}" jobKey="${f:h(j.key)}">Edit</a>
					 </c:if>
					<ul>
					<c:forEach var="jr" items="${j.jobReportListRef.modelList}">
						<li>
							<strong>${f:h(jr.statusLable)}</strong> ${f:nbsp('   ')}
							reported by  ${f:nbsp(' ')} ${f:h(jr.creatorName)}  ${f:nbsp('   ')} 
							worked on  ${f:nbsp(' ')} ${f:h(jr.workingDateStr)} ${f:nbsp('   ')} Wk Hrs:
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