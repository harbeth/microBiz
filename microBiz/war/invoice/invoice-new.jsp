<%@include file="../includes/taglib.jsp"%>

<!-- for new invoice -->
<div class="panel-heading">
	<a link="invoiceEditClose"  data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Close</a>
</div>

<c:import url = "./invoice-edit-common.jsp">
	<c:param name = "type" value ="new"/>
	<c:param name = "action" value ="${f:url('/invoice/invoiceCreateAction')}"/>
</c:import>
