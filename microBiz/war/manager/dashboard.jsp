<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	dashboardFn.init();
	});
</script>

<div class="row">
	<div class="col-lg-12">
		<h3><span id="detailPaneTitleSpan">Dashboard</span></h3>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
<div class="col-lg-3 col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				$456789 due
			</div>
			<div class="panel-footer">
				<span class="pull-right">
					<a link="unpaidInvoices" nowSelected="n">View Details</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				10 ongoing invoices
			</div>
			<div class="panel-footer">
				<span class="pull-right">
					<a link="ongoingInvoices" nowSelected="n">View Details</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				20 Job Reports to Approve
			</div>
			<div class="panel-footer">
				<span class="pull-right">
					<a link="unApprovedJobReports" nowSelected="y">View Details</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-red">
			<div class="panel-heading">
			10 Uncompleted Jobs
			</div>
			<div class="panel-footer">
				<span class="pull-right">
					<a link="unCompleteJobs" nowSelected="n">View Details</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>

		<div id="dashboardWorkingAreaDIV"></div>
			

	<div class="row">
		<div id="unpaidInvoiceDIV" type="tab" hasContent="n" class="col-lg-12" >
			
		</div>
		<div id="ongoingInvoiceDIV" type="tab"  hasContent="n" class="col-lg-12" ></div>
		<div id="unApprovedJobReportsDIV" type="tab" hasContent="y" class="col-lg-12" ></div>
		<div id="unCompleteJobsDIV" type="tab" hasContent="n" class="col-lg-12" ></div>
	</div>
</div>