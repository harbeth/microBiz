<%@include file="../includes/taglib.jsp"%>
<div class="row">
	<p></p>
</div>

<div id="invoiceSummaryDIV" class="row">
	<jsp:include page="./invoice-manage-summary.jsp" flush="true" />
</div>

<p><strong>${f:nbsp('        ')}<span id="detailPaneTitleSpan">Job List</span></strong></p>

<div class="row">
		<div id="invoiceDetailJobDIV" type="tab" invoiceKey="${f:h(invoice.key)}" hasContent="y" class="col-lg-12" >
			<jsp:include page="./invoice-jobs.jsp" flush="true" />
		</div>
		<div id="invoiceDetailPaymentDIV" type="tab" invoiceKey="${f:h(invoice.key)}" hasContent="n" class="col-lg-12" ></div>
		<div id="invoiceDetailExpenseDIV" type="tab" invoiceKey="${f:h(invoice.key)}" hasContent="n" class="col-lg-12" ></div>
</div>
