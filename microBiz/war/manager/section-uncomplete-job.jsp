<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-red">
	<div class="panel-heading">
	${f:h(openJobCount)} Uncompleted Jobs
	</div>
	<div class="panel-footer">
		<span class="pull-right">
			<a link="managerTabUnCompleteJob" nowSelected="n">View Details</a>
		</span>
		<div class="clearfix"></div>
	</div>
</div>