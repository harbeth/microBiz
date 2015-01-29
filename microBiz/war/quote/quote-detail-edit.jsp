<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<div class="col-lg-6">
		<c:if test="${key != null}">
			<input type="hidden" ${f:hidden("key")} />
		</c:if>
		<div class="form-group input-group">
			<span class="input-group-addon">Address</span>
			<input type="text" ${f:text("address")} class="form-control" />
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon">Status</span>
			<input type="text" ${f:text("status")} class="form-control" />
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon">Note</span> 
			<input type="text" ${f:text("note")} class="form-control" />
		</div>
		
		<jsp:include page="../customer-contact-common.jsp" />
	</div>
</div>
