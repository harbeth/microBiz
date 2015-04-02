<%@include file="../includes/taglib.jsp"%>
<div id="invoiceInfoDIV" class="row">
	<jsp:include page="invoice-detail-info.jsp" flush="true" />
</div>

<ul class="nav nav-pills">
	<li class="active">
		<a link="invoiceOrder" nowSelected="y" data-toggle="tab" aria-expanded="false">Items</a>
	</li>
    <li class="">
    	<a link="invoiceEdit" invoiceKey="${f:h(invoice.key)}" nowSelected="n" data-toggle="tab" aria-expanded="false">Edit</a>
    </li>
    <li class="">
    	<a link="invoiceLogEvent" invoiceKey="${f:h(invoice.key)}" nowSelected="n" data-toggle="tab" aria-expanded="false">Logs</a>
    </li>
</ul>

<div id="invoiceOrderDetailDIV" type="tab" invoiceKey="${f:h(invoice.key)}" class="row">
	<jsp:include page="./invoice-order.jsp" flush="true"></jsp:include>
</div>

<div id="invoiceEditDetailDIV" type="tab" invoiceKey="${f:h(invoice.key)}" class="row" hasContent="n"></div>
<div id="invoiceLogEventDIV" type="tab" invoiceKey="${f:h(invoice.key)}" class="row" hasContent="n"></div>

