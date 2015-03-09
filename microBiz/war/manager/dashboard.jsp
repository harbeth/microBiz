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
	<div id="unpaidInvoiceSectionDIV" class="col-lg-3 col-md-6">
		<jsp:include page="./section-unpaid-invoice.jsp" flush="true" />
	</div>
	<div id="ongoingInvoiceSectionDIV" class="col-lg-3 col-md-6">
		<jsp:include page="./section-ongoing-invoice.jsp" flush="true" />
	</div>
	<div class="col-lg-3 col-md-6">
		<jsp:include page="./section-unapprove-job.jsp" flush="true" />
	</div>
	<div id="uncompleteJobSectionDIV" class="col-lg-3 col-md-6">
		<jsp:include page="./section-uncomplete-job.jsp" flush="true" />
	</div>
	<div class="row">
		<div id="managerTabUnpaidInvoiceDIV" invoiceKey="" type="tab" hasContent="y" class="col-lg-12" >
			<jsp:include page="./manager-unpaid-invoice-list.jsp" flush="true" />
		</div>
		<div id="managerTabOngoingInvoiceDIV" invoiceKey="" type="tab"  hasContent="n" class="col-lg-12" ></div>
		<div id="managerTabUnApprovedJobReportDIV" type="tab" hasContent="n" class="col-lg-12" ></div>
		<div id="managerTabUnCompleteJobDIV" jobKey="" type="tab" hasContent="n" class="col-lg-12" ></div>
	</div>
</div>