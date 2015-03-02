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
					<a link="managerTabUnpaidInvoices" nowSelected="y">View Details</a>
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
					<a link="managerTabOngoingInvoices" nowSelected="n">View Details</a>
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
					<a link="managerTabUnApprovedJobReports" nowSelected="y">View Details</a>
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
					<a link="managerTabUnCompleteJobs" nowSelected="n">View Details</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>

	<div class="row">
		<div id="managerTabUnpaidInvoiceDIV" type="tab" hasContent="y" class="col-lg-12" >
			<jsp:include page="./manager-invoice-list.jsp" flush="true" />
		</div>
		<div id="managerTabOngoingInvoiceDIV" type="tab"  hasContent="n" class="col-lg-12" ></div>
		<div id="managerTabUnApprovedJobReportsDIV" type="tab" hasContent="n" class="col-lg-12" ></div>
		<div id="managerTabUnCompleteJobsDIV" type="tab" hasContent="n" class="col-lg-12" ></div>
	</div>
</div>