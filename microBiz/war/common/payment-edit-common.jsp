<%@include file="../includes/taglib.jsp"%>

<%-- 
dashboard: receive payment & invoice detail: receive payment
put it here for portable
--%>

<script>
	$(function() {
		microBizFn.initPriceInput();
	  	$( "#enterDateStr" ).datepicker();
	});
</script>

<input type="hidden" ${f:hidden("invoiceKey")} />
<c:if test="${key != null}">
	<input type="hidden" ${f:hidden("key")} />
</c:if>

<div class="form-group input-group">
	<span class="input-group-addon">Payment Method *</span>
	<select name="method" mandatory="y" field="Payment Method" class="form-control">
		<option value="-1">Select ...</option>
		<c:forEach items="${paymentTypes}" var="pt">
			<option value="${f:h(pt.value)}"
				<c:if test = "${f:h(method) == f:h(pt.value)}" >
					selected
				</c:if>>${f:h(pt.label)}</option>
		</c:forEach>
	</select>
</div>

<div class="form-group input-group">
	<span class="input-group-addon">Amount *</span>
	<input type="text" mandatory="y" field="Amount" valueType="price" name="amount" value="${f:h(amountStr)}" class="form-control" />
</div>

<div class="form-group input-group">
	<span class="input-group-addon">Notes</span>
	<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
</div>

<c:if test="${key != null}">
	<div class="form-group">
		<label>Canceled</label>
		<label class="checkbox-inline">
			<input type="checkbox" ${f:checkbox("canceled")}>yes
		</label>
	</div>
</c:if>