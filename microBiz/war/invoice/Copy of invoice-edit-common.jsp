<%@include file="../includes/taglib.jsp"%>

<form name ="invoiceDetailInfoForm" type="${param.type}" typerole="form" method="post" action="${param.action}" >
	<div class="col-lg-6">
		<c:if test="${key != null}">
			<input type="hidden" ${f:hidden("key")} />
		</c:if>
		<div class="form-group input-group">
			<span class="input-group-addon">Invoice Number*</span>
			<input type="text" mandatory="y" field="Invoice Number" ${f:text("invoiceNumber")} class="form-control" />
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
			<input type="text" valueType="price" field="Price" ${f:text("price")} class="form-control" />
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
		
		<jsp:include page="../customer-contact-common.jsp" />
		
	</div>
	<div class="form-group">
		<button type="submit" class="btn btn-default">Submit</button>
	</div>
</form>