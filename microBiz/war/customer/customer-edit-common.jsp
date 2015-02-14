<%@include file="../includes/taglib.jsp"%>
<form name="customerDetailInfoForm" type="${param.type}" typerole="form" method="post" action="${param.action}">
	<div class="col-lg-12">
		<div class="well">
			<div class="col-lg-6">
				<c:if test="${key!=null}">
					<input type="hidden" ${f:hidden("key")} />
				</c:if>
	
				<div class="form-group input-group">
					<input type="hidden" editMode="${param.type}"
						${f:hidden("oldCustomerType")} /> <span class="input-group-addon">Customer
						Type</span> <select id="customerTypeSelect" name="type"
						class="form-control">
						<option value="">Select ... </option>
						<c:forEach items="${cxTypes}" var="pt">
							<option value="${f:h(pt)}"
								<c:if test = "${f:h(type) == f:h(pt)}" >
							selected
						</c:if>>${f:h(pt)}</option>
						</c:forEach>
					</select>
				</div>
	
				<div class="form-group input-group">
					<span class="input-group-addon">Customer Name</span> <input
						type="text" ${f:text("name")} class="form-control" />
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">Phone</span> <input type="text"
						${f:text("phone")} class="form-control" />
				</div>
	
				<div class="form-group input-group">
					<span class="input-group-addon">Alt.Phone</span> <input type="text"
						${f:text("altPhone")} class="form-control" />
				</div>
	
				<div class="form-group">
					<label>Active</label> <label class="checkbox-inline"> <input
						type="checkbox" ${f:checkbox("active")}> yes
					</label>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="form-group input-group">
					<span class="input-group-addon">Address</span> <input type="text"
						${f:text("address")} class="form-control" />
				</div>
	
				<div class="form-group input-group">
					<span class="input-group-addon">Email</span> <input type="text"
						${f:text("email")} class="form-control" />
				</div>
	
				<div class="form-group input-group">
					<span class="input-group-addon">Rating</span> <select
						name="purchaseUnit" class="form-control">
						<c:forEach items="${cxRatings}" var="r">
							<option value="${f:h(r)}"
								<c:if test = "${f:h(rating) == f:h(r)}" >
							selected
						</c:if>>${f:h(r)}</option>
						</c:forEach>
					</select>
				</div>
	
				<div class="form-group input-group">
					<span class="input-group-addon">Notes</span>
					<textarea class="form-control" name="notes" rows="2">${notes}</textarea>
				</div>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</div>
	</div>
</form>