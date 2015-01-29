<%@include file="../includes/taglib.jsp"%>

<!-- for edit invoice -->
<c:import url = "./invoice-edit-common.jsp">
	<c:param name = "type" value ="edit"/>
	<c:param name = "action" value ="${f:url('/invoice/invoiceEditAction')}"/>
</c:import>