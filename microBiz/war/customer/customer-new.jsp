<%@include file="../includes/taglib.jsp"%>

<!-- for new invoice -->
<div class="panel-heading">
	<a link="customerEditClose"  data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Close</a>
</div>

<c:import url = "./customer-edit-common.jsp">
	<c:param name = "type" value ="new"/>
	<c:param name = "action" value ="${f:url('/customer/customerCreateAction')}"/>
</c:import>
