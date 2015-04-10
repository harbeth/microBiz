<%@include file="../includes/taglib.jsp"%>

<h4>Create/Edit Quote</h4>

<div class="col-lg-6">
	<c:if test="${key != null}">
		<input type="hidden" ${f:hidden("key")} />
	</c:if>
	<jsp:include page="../common/customer-contact-common.jsp" />
	

</div>
<div class="col-lg-6">
	<div class="form-group input-group">
		<span class="input-group-addon">Address *</span>
		<input type="text" ${f:text("address")} class="form-control" mandatory="y" field="Address" />
	</div>
	

                            

	<div class="form-group input-group">
		<span class="input-group-addon">Notes</span>
		<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
	</div>
	<div class="form-group input-group">
		<span class="input-group-addon">Status</span>
		<select name="status" class="form-control">
			<c:forEach items="${quoteStatus}" var="qs">
				<option value="${f:h(qs.value)}"
					<c:if test = "${f:h(status) == f:h(qs.value)}" >
							selected
						</c:if>
					>${f:h(qs.label)}</option>
			</c:forEach>
		</select>
	</div>
</div>