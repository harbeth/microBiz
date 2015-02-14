<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
<div class="row">
  <div class="col-xs-6 col-md-4">
  
		<Strong> ${f:h(customer.name)} </Strong>  ${f:nbsp('  ')}  ${f:h(customer.type)}  <br>
		 ${f:h(customer.address)}<br> 
		  ${f:h(customer.phone)} ${f:nbsp('  ')}
		 ${f:h(customer.altPhone)} 
		  <br>
		 ${f:h(customer.email)} <br>
  </div>
  <div class="col-xs-6 col-md-4">
  <font class="text-muted">Notes:</font> ${f:h(customer.notes)} <br>
  <font class="text-muted">Rating:</font>${f:h(customer.rating)}<br>
  <font class="text-muted">Active:</font> ${f:h(customer.active)}
  
  
  
  
  </div>
 
</div>

