<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
	<form name="managerJobDetailForm" role="form" method="post" action="${f:url('/manager/managerJobEditAction')}">
		<div class="col-lg-6">
			<input type="hidden" ${f:hidden("key")} />
		
			<div class="form-group input-group">
				<span class="input-group-addon">Invoice Number *</span>
				<input type="text" disabled ${f:text("invoiceRef.model.invoiceNumber")} class="form-control" />
			</div>
			
			<div class="form-group input-group">
				<span class="input-group-addon">Status</span>
				<select name="status" class="form-control">
					<c:forEach items="${jobStatus}" var="j">
						<option value="${f:h(j.value)}"
							<c:if test = "${f:h(status) == f:h(j.value)}" >
								selected
							</c:if>>${f:h(j.label)}</option>
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