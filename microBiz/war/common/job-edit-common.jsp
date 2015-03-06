<%@include file="../includes/taglib.jsp"%>

<script>
	$(function() {
		$("#startingDateStr").datepicker();
	});
</script>

<h4>${f:nbsp('     ')} Assign Job </h4> 
<div class="col-lg-6">
	<input type="hidden" ${f:hidden("invoiceKey")} />
	<c:if test="${key != null}">
		<input type="hidden" ${f:hidden("key")} />
	</c:if>

	<div class="form-group input-group">
		<span class="input-group-addon">Installer</span> 
		<select name="installer" class="form-control">
			<option value="">Select ...</option>
			<c:forEach items="${installers}" var="i">
				<option value="${f:h(i.name)}"
					<c:if test = "${f:h(installer) == f:h(i.name)}" >
						selected
					</c:if>>${f:h(i.name)}</option>
			</c:forEach>
		</select>
	</div>

	<div class="form-group input-group">
		<span class="input-group-addon">Starting Date</span> 
		<input type="text" ${f:text("startingDateStr")} class="form-control" id="startingDateStr" />
	</div>
	<div class="form-group input-group">
		<span class="input-group-addon">Arrival Time</span> 
		<input type="text" ${f:text("arrivalTime")} class="form-control" />		
	</div>
	<div class="form-group input-group">
		<span class="input-group-addon">Status</span> 
		<select name="status" class="form-control">
			<c:forEach items="${jobStatus}" var="js">
				<option value="${f:h(js.value)}"
					<c:if test = "${f:h(status) == f:h(js.value)}" >
						selected
					</c:if>>${f:h(js.label)}
				</option>
			</c:forEach>
		</select>
	</div>
</div>
<div class="col-lg-6">
	<div class="form-group">
		<label>Check the Products For the Job:</label>
		<c:forEach items="${prds}" var="p">
			<div class="checkbox">
				<label>
					<input type="checkbox" name="prds" value="${f:h(p.key)}"> ${f:h(p.model)}
				</label>
			</div>
		</c:forEach>
	</div>
	<div class="form-group input-group">
		<span class="input-group-addon">Notes</span>
		<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
	</div>
	<div class="form-group">
		<button type="submit" class="btn btn-default">Submit</button>
		<c:if test="${param.closeBtnShow == 'y'}">
		<a link="invoiceEditCloseJob" invoiceKey="${f:h(invoiceKey)}" class="btn btn-default" role="button">Close</a>
		</c:if>
	</div>
</div>

