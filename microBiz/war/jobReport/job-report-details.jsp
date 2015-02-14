
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>

<div class="row">
<h3> All Reports For the Job</h3>
</div>

<%@include file="job-info-common.jsp"%>

<div class="row">
<div class="panel panel-default">
	<div class="panel-body">
		<div class="list-group">
		   <c:forEach items="${jobMaterialReports}" var="jmr">
		        <a link="jobMaterialReportVoid" jobMaterialReportKey="${f:h(jmr.key)}" class="list-group-item">
		            <i class="fa fa-comment fa-fw"></i> ${f:h(jmr.reportDateStr)} ${f:nbsp('   ')}
		             ${f:h(jmr.qty)} ${f:nbsp('   ')} ${f:h(jmr.productRef.model.consumeReportUnit)}
		             <c:if test="${jmr.prdRatioRef!=null}">
		             	${f:nbsp('   ')} ${f:h(jmr.prdRatioRef.model.desc)}
		             </c:if>
		             
		            <span class="pull-right text-muted small"><em>Void</em></span>
		        </a>
			</c:forEach>
		</div>
	</div>
</div>
</div>

<div class="row">
<div class="panel panel-default">
	<div class="panel-body">
		<div class="list-group">
		   <c:forEach items="${jobLaborReports}" var="jlr">
		        <a link="jobLaborReportVoid" jobLaborReportKey="${f:h(jlr.key)}" class="list-group-item">
		            <i class="fa fa-comment fa-fw"></i> ${f:h(jlr.reportDateStr)} ${f:nbsp('   ')}
		             Travel Hours: ${f:h(jlr.travelHours)} ${f:nbsp('   ')}
		             Working Hours ${f:h(jlr.workingHours)}

		            <span class="pull-right text-muted small"><em>Void</em></span>
		        </a>
			</c:forEach>
		</div>
	</div>
</div>
</div>
