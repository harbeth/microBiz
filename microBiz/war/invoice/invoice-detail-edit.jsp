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
			<c:forEach items="${sales}" var="s">
				<option value="${f:h(s.key)}"
					<c:if test = "${f:h(salesRef.model.key) == f:h(s.key)}" >
						selected
					</c:if>>${f:h(s.name)}</option>
			</c:forEach>
		</select>
	</div>
	
	<div class="form-group input-group">
		<span class="input-group-addon">PO Number</span>
		<input type="text" ${f:text("poNumber")} class="form-control" />
	</div>

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
			<c:forEach items="${paymentTypes}" var="pt">
				<option value="${f:h(pt)}"
					<c:if test = "${f:h(depositPymtMethod) == f:h(pt)}" >
						selected
					</c:if>>${f:h(pt)}</option>
			</c:forEach>
		</select>
	</div>
	
	<div class="form-group input-group">
		<span class="input-group-addon">Balance Payment Method</span>
		<select name="balancePymtMethod" class="form-control">
			<c:forEach items="${paymentTypes}" var="pt">
				<option value="${f:h(pt)}"
					<c:if test = "${f:h(balancePymtMethod) == f:h(pt)}" >
						selected
					</c:if>>${f:h(pt)}</option>
			</c:forEach>
		</select>
	</div>
		
	<div class="form-group input-group">
		<span class="input-group-addon">Notes</span>
		<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
	</div>
	
</div>