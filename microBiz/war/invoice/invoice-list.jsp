<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	invoiceFn.init();
	});
</script>

<!-- /.row -->
<div class="row">
<div class="col-lg-12">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="row">
			<div class="col-sm-6">
				Invoices
			</div>
			<div class="col-sm-6">
				<a link="invoiceNew" data-toggle="tab" aria-expanded="false" class="btn btn-default" role="button">New Invoice</a>
			</div>

		</div>
		
	</div>
	<div class="panel-body">
		<div class="col-lg-12">
			<div class="row">
				<form name="invoiceSearchForm" role="form" method="post" action="${f:url('/invoice/invoiceSearch')}">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
			                  <input class="form-control" name="searchInvByInvNo" placeholder="Invoice# starts with">
			            	</div>
			            </div>
			            <div class="col-sm-4">
			            	<div class="form-group">
			                  <input class="form-control" name="searchInvByAddr" placeholder="Address starts with">
			            	</div>
			            </div>
			            <div class="col-sm-2">
			            	<select name="searchStatus" class="form-control">
			            		<option value="0" selected>status ... </option>
								<c:forEach items="${invoiceStatus}" var="s">
									<option value="${f:h(s.value)}">${f:h(s.label)}</option>
								</c:forEach>
							</select>
			            </div>
			 			<div class="col-sm-2">
							<button type="submit" class="btn btn-default btn-sm">Submit</button>
							<a link="clearInvoiceSearch" data-toggle="tab" aria-expanded="false" class="btn btn-default btn-sm" role="button">Clear</a>
						</div>
			        
					</div>
				</form>
			</div>
			<div id="invoiceListDIV">
				<jsp:include page="./invoice-list-div.jsp" flush="true"></jsp:include>
			</div>
		</div>
	</div>
	</div>
</div>
</div>
<!-- /.row -->
