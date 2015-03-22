<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-12">
	<div class="well">
		<form id="invoiceDetailOrderForm" typerole="form" method="post" action="/invoice/invoiceOrderAction">
			<div class="row">
				<input type="hidden" name="invoiceKey" value="${f:h(invoice.key)}" />
				<jsp:include page="../common/order-item.jsp" flush="true" />
			</div>
			<div class="form-group">
				<button type="submit" name="saveAction" value="save" class="btn btn-default">Submit</button>
			</div>
		</form>
	</div>
</div>


