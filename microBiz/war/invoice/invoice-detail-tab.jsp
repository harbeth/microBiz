<%@include file="../includes/taglib.jsp"%>
<div id="invoiceInfoDIV">
	<jsp:include page="invoice-detail-info.jsp" flush="true" />
</div>
<div>
	<a link="invoiceEdit" invoiceKey="${f:h(invoice.key)}">Edit</a>&nbsp;<a link="invoiceEditClose">Close</a>
</div>
<div id="invoiceOrderDIV">
	<jsp:include page="./invoice-order.jsp" flush="true" />
</div>
<div id="invoiceEditDetailDIV">
</div>