<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<form id="invoiceDetailOrderForm" typerole="form" method="post" action="/invoice/invoiceOrderAction">
			<div id="invoiceOrderDIV" class="row">
				<input type="hidden" name="invoiceKey" value="${f:h(invoice.key)}" />
				<jsp:include page="../common/order-item.jsp" flush="true" />
			</div>
			<div class="form-group">
				<button type="submit" name="saveAction" value="save" class="btn btn-default">Submit</button>
			</div>
		</form>
	</div>
</div>


