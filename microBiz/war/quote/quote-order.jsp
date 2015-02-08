<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<pre>Edit the Quote Version:
			${f:h(quoteOrder.name)} Created At ${f:h(quoteOrder.createAtStr)} 
	    </pre>
		<form name="quoteDetailOrderForm" typerole="form" method="post" action="/quote/quoteOrderAction">
			<input type="hidden" name="quoteOrderKey" value="${f:h(quoteOrder.key)}">
			<div id="quoteOrderDIV" class="row">
				<jsp:include page="../common/order-item.jsp" flush="true"></jsp:include>
			</div>
			<div class="form-group">
				<input type="radio" name="saveOption" value="save" checked>Save
				<input type="radio" name="saveOption" value="saveAs">Save
				as <input type="radio" name="saveOption" value="convertToInvoice">Convert
				to Invoice
			</div>
			<div class="form-group">
				<button type="submit" name="saveAction" value="save" class="btn btn-default">Submit</button>
			</div>
		</form>
	</div>
</div>


