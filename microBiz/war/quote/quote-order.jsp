<%@include file="../includes/taglib.jsp"%>



		<h4>${f:h(quoteOrder.name)}  ${f:nbsp('  ')}  ${f:h(quoteOrder.createAtStr)} 
	    </h4>
		<form name="quoteDetailOrderForm" typerole="form" method="post" action="/quote/quoteOrderAction">
			<input type="hidden" name="quoteOrderKey" value="${f:h(quoteOrder.key)}">
			<div id="quoteOrderDIV" class="row">
				<jsp:include page="../common/order-item.jsp" flush="true"></jsp:include>
			</div>
			<div class="form-group">
				<input type="radio" name="saveOption" value="save" checked>Save to Current Version
				${f:nbsp('  ')} <input type="radio" name="saveOption" value="saveAs">Save as New Version
				${f:nbsp('  ')} <input type="radio" name="saveOption" value="convertToInvoice">Convert
				to Invoice
			</div>
			<div class="form-group">
				<button type="submit" name="saveAction" value="save" class="btn btn-default">Submit</button>
			</div>
		</form>


