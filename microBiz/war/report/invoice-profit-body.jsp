<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	reportFn.init();
	});
    $(function() {
		$("#signDateFrom").datepicker();
	});
    $(function() {
		$("#signDateTo").datepicker();
	});
    
</script>



<!-- /.row -->
<div class="row">
<div class="col-lg-12">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="row">
			<div class="col-sm-6">
				Invoice Profit Report
			</div>
		</div>
		
	</div>
	<div class="panel-body">
		<div class="col-lg-12">
			<div class="row">
				<form name="invoiceProfitDateRangeForm" role="form" method="post" action="${f:url('/report/invoiceProfit')}">
					<div class="row">
						
						<div class="col-sm-4">
							<div class="form-group">
			                  <input class="form-control" name="signDateFrom"   id="signDateFrom" placeholder="Sign Date From">
			            	</div>
			            </div>
			            <div class="col-sm-4">
			            	<div class="form-group">
			                  <input class="form-control" name="signDateTo"  id="signDateTo" placeholder="Sign Date To">
			            	</div>
			            </div>

			 			<div class="col-sm-4">
							<button type="submit" class="btn btn-default btn-sm">Submit</button>
							<a link="clearInvoiceSearch" data-toggle="tab" aria-expanded="false" class="btn btn-default btn-sm" role="button">For Last 365 Days</a>
						</div>
			        
					</div>
				</form>
			</div>
			
			<div id="invoiceProfitListDIV">
				<jsp:include page="./invoice-profit-list-div.jsp" flush="true"></jsp:include>
			</div>
		</div>
	</div>
	</div>
</div>
</div>
<!-- /.row -->
