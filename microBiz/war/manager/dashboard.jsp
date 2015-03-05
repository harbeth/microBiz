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
				$ ${f:h(unPaidAmt)} due
			</div>
			<div class="panel-footer">
				<span class="pull-right">
					<a link="managerTabUnpaidInvoice" nowSelected="y">${f:h(unPaidOffInvoiceCount)} UnPaidoffInvoices </a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				${f:h(openInvoiceCount)} ongoing invoices
			</div>
			<div class="panel-footer">
				<span class="pull-right">
					<a link="managerTabOngoingInvoice" nowSelected="n">View Details</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				${f:h(newJobReportCount)} Job Reports to Approve
			</div>
			<div class="panel-footer">
				<span class="pull-right">
					<a link="managerTabUnApprovedJobReport" nowSelected="y">View Details</a>
				</span>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
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
	</div>

	<div class="row">
		<div id="managerTabUnpaidInvoiceDIV" type="tab" hasContent="y" class="col-lg-12" >
			<jsp:include page="./manager-unpaid-invoice-list.jsp" flush="true" />
		</div>
		<div id="managerTabOngoingInvoiceDIV" type="tab"  hasContent="n" class="col-lg-12" ></div>
		<div id="managerTabUnApprovedJobReportDIV" type="tab" hasContent="n" class="col-lg-12" ></div>
		<div id="managerTabUnCompleteJobDIV" type="tab" hasContent="n" class="col-lg-12" ></div>
	</div>
</div>