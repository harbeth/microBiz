<%@include file="../includes/taglib.jsp"%>

<!-- for new invoice -->
<div class="col-lg-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
			Create New Customer
			<span class="pull-right">
				<a link="customerEditClose"  data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Close</a>
			</span>
		</div>	
		<div class="panel-body">
			<c:import url = "./customer-edit-common.jsp">
				<c:param name = "type" value ="new"/>
				<c:param name = "action" value ="${f:url('/customer/customerCreateAction')}"/>
			</c:import>
		</div>
	</div>
</div>

