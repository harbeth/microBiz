<%@include file="includes/header.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	invoiceFn.init();
	});
</script>

<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Invoice</h1>
	</div>
</div>
<!-- /.row -->

<!-- Page conent -->
<div class="row">

	<form role="form" method="post" action="${f:url('invoiceAction')}">
		<div class="col-lg-6">
			<c:if test="${key != null}">
				<input type="hidden" ${f:hidden("key")} />
			</c:if>
			<div class="form-group input-group">
				<span class="input-group-addon">Invoice Number</span>
				<input type="text" ${f:text("invoiceNumber")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Address</span>
				<input type="text" ${f:text("address")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Sales</span> 
				<input type="text" ${f:text("sales")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Price</span> 
				<input type="text" ${f:text("price")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Tax rate</span> 
				<input type="text" ${f:text("taxRate")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Deposit</span> 
				<input type="text" ${f:text("deposit")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Deposit Payment Method</span> 
				<input type="text" ${f:text("depositPymtMethod")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Balance Payment Method</span> 
				<input type="text" ${f:text("balancePymtMethod")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">PO Number</span> 
				<input type="text" ${f:text("poNumber")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Estimated working hours</span> 
				<input type="text" ${f:text("estimatedWorkingHours")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Note</span> 
				<input type="text" ${f:text("note")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Status</span> 
				<input type="text" ${f:text("status")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">User</span> 
				<input type="text" ${f:text("user")} class="form-control" />
			</div>
			
			<div class="form-group input-group">
				<span class="input-group-addon">Customer</span> 
				<select id="invoiceCustomerSelect" name="customerKey" class="form-control">
					<option value="-1">Select</option>
					<c:forEach items="${customers}" var="c">
						<option value="${f:h(c.key)}"
							<c:if test = "${f:h(c.selected)}" >
								selected
							</c:if> >${f:h(c.name)}</option>
					</c:forEach>
				</select>
			</div>
			
			<div id="invoiceCustomerContactDIV">
				<c:if test = "${f:h(contactShown)}" >
					<jsp:include page="invoiceCustomerContact.jsp" />
				</c:if>
			</div>
			
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</form>

</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<h2>All Invoices</h2>
		<div class="table-responsive">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Invoice #</th>
						<th>Address</th>
						<th>Sales</th>
						<th>User</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${invoices}">
						<tr>
							<td>${f:h(i.invoiceNumber)}</td>
							<td>${f:h(i.address)}</td>
							<td>${f:h(i.sales)}</td>
							<td>${f:h(i.user)}</td>
							<td><a href="/invoiceDetails?key=${f:h(i.key)}">Details</a><a href="/invoice?key=${f:h(i.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- /.row -->

<%@include file="includes/footer.jsp"%>
