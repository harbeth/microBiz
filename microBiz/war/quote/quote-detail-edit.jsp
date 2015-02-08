<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<div class="panel panel-default">
	<div class="panel-heading">
	<h4>Create/Edit Quote</h4>
	</div>
		<div class="panel-body">
			<div class="col-lg-6">
					<c:if test="${key != null}">
						<input type="hidden" ${f:hidden("key")} />
					</c:if>
					<jsp:include page="../common/customer-contact-common.jsp" />
			</div>
			<div class="col-lg-6">
				<div class="form-group input-group">
					<span class="input-group-addon">Address</span>
					<input type="text" ${f:text("address")} class="form-control" />
				</div>
		
				<div class="form-group input-group">
					<span class="input-group-addon">Notes</span>
					<textarea name="notes" class="form-control" rows="2">${f:h(note)}</textarea>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">Status</span>
					<select name="status" class="form-control">
						<c:forEach items="${quoteStatus}" var="qs">
							<option value="${f:h(qs)}"
								<c:if test = "${f:h(status) == f:h(qs)}" >
										selected
									</c:if>
								>${f:h(qs)}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
	</div>
</div>

