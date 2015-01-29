<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
	<p>
		Name: ${f:h(customer.name)} <br> Type:
		${f:h(customer.type)} <br> Phone: ${f:h(customer.phone)} <br>
		Address: ${f:h(customer.address)}
	</p>
</div>