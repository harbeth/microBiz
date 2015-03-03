<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
	<form name="managerInvoiceDetailForm" role="form" method="post" action="${f:url('/manager/managerInvoiceEditAction')}">
		<div class="col-lg-6">
			<input type="hidden" ${f:hidden("key")} />
		
			<div class="form-group input-group">
				<span class="input-group-addon">Invoice Number *</span>
				<input type="text" disabled ${f:text("invoiceNumber")} class="form-control" />
			</div>
			
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
			
			<div class="form-group input-group">
				<span class="input-group-addon">Notes</span>
				<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
			</div>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button> 
			${f:nbsp('     ')}
		</div>
	</form>
</div>