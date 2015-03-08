<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-6">
	<c:if test="${key != null}">
		<input type="hidden" ${f:hidden("key")} />
	</c:if>
	<c:if test="${key == null}">
		<label>Auto Generate</label> 
		<label class="checkbox-inline">
			<input type="checkbox" ${f:checkbox("autoInvoiceNumber")}>
		</label>
	</c:if>
	<div class="form-group input-group">
		<span class="input-group-addon">Invoice Number *</span>
		<c:choose>
			<c:when test="${key == null}">
				<input type="text" mandatory="y" field="Invoice Number" ${f:text("invoiceNumber")} class="form-control" />
				<input type="hidden" name="invoiceNumberHidden" value="" />
			</c:when>
			<c:otherwise>
				<input type="text" disabled="disabled" ${f:text("invoiceNumber")} class="form-control" />
			</c:otherwise>
		</c:choose>
	</div>
	<div class="form-group input-group">
		<span class="input-group-addon">Address *</span>
		<input type="text" mandatory="y" field="Address" ${f:text("address")} class="form-control" />
	</div>
	<div class="form-group input-group">
		<span class="input-group-addon">Sign Date *</span>
		<input type="text" mandatory="y" field="Sign Date" ${f:text("signDateStr")} id = "signDateStr" class="form-control" />
	</div>
	
	<div class="form-group input-group">
		<span class="input-group-addon">Sales</span>
		<select name="sales" class="form-control">
		<option value="">Select ... </option>
			<c:forEach items="${salesNames}" var="s">
				<option value="${f:h(s)}"
					<c:if test = "${f:h(sales) == f:h(s)}" >
						selected
					</c:if>>${f:h(s)}</option>
			</c:forEach>
		</select>
	</div>
	
	<div class="form-group input-group">
		<span class="input-group-addon">PO Number</span>
		<input type="text" ${f:text("poNumber")} class="form-control" />
	</div>
	
	<c:if test="${key != null}">
		<div class="form-group input-group">
			<span class="input-group-addon">Status</span>
			<select name="status" class="form-control">
				<c:forEach items="${invoiceStatus}" var="s">
					<option value="${f:h(s.value)}"
						<c:if test = "${f:h(status) == f:h(s.value)}" >
							selected
						</c:if>>${f:h(s.label)}</option>
				</c:forEach>
			</select>
		</div>	
	</c:if>	

	<jsp:include page="../common/customer-contact-common.jsp" />

</div>
<div class="col-lg-6">
	<div class="form-group input-group">
		<span class="input-group-addon">Estimated working hours</span>
		<input type="text" ${f:text("estimatedWorkingHours")} class="form-control" />
	</div>

	<div class="form-group input-group">
		<span class="input-group-addon">Preferred Installation Date</span>
		<input type="text" ${f:text("preferIntlDateStr")} id = "preferIntlDateStr" class="form-control" />
	</div>

	<div class="form-group input-group">
		<span class="input-group-addon">Preferred Arriva Time</span>
		<input type="text" ${f:text("preferArrivalTime")} class="form-control" />
	</div>

	<div class="form-group input-group">
		<span class="input-group-addon">Deposit</span>
		<input type="text" ${f:text("deposit")} class="form-control" />
	</div>
	
	<div class="form-group input-group">
			<span class="input-group-addon">Deposit Payment Method</span>
			<select name="depositPymtMethod" class="form-control">
			<option value="">Select ... </option>
				<c:forEach items="${paymentTypes}" var="pt">
					<option value="${f:h(pt.value)}"
						<c:if test = "${f:h(depositPymtMethod) == f:h(pt.value)}" >
							selected
						</c:if>>${f:h(pt.label)}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon">Balance Payment Method</span> <select
				name="balancePymtMethod" class="form-control">
				<option value="">Select ... </option>
				<c:forEach items="${paymentTypes}" var="pt">
					<option value="${f:h(pt.value)}"
						<c:if test = "${f:h(balancePymtMethod) == f:h(pt.value)}" >
							selected
						</c:if>>${f:h(pt.label)}</option>
				</c:forEach>
			</select>
		</div>	
	<div class="form-group input-group">
		<span class="input-group-addon">Notes</span>
		<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
	</div>
	
</div>
