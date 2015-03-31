<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	customerFn.init();
	});
</script>

<div class="row">
<div class="col-lg-12">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="row">
			<div class="col-sm-6">
				Customers
			</div>
			<div class="col-sm-6">
				<a link="customerNew" data-toggle="tab" aria-expanded="false" class="btn btn-default" role="button">New Customer</a>
			</div>

		</div>
		
	</div>
		<div class="panel-body">
		<div class="col-lg-12">
			<div class="row">
				<form name="customerSearchForm" role="form" method="post" action="${f:url('/customer/customerSearch')}">
					<div class="row">
			            <div class="col-sm-6">
			            	<div class="form-group">
			                  <input class="form-control" name="searchCustomerByName" placeholder="Customer name starts with">
			            	</div>
			            </div>
			 			<div class="col-sm-6">
							<button type="submit" class="btn btn-default btn-sm">Submit</button>
							<a link="clearCustomerSearch" data-toggle="tab" aria-expanded="false" class="btn btn-default btn-sm" role="button">Clear</a>
						</div>
			        
					</div>
				</form>
			</div>
			<div id="customerListDIV">
				<jsp:include page="./customer-list-div.jsp" flush="true"></jsp:include>
			</div>
		</div>
	</div>
	</div>
</div>
</div>
