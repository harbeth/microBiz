<%@include file="../includes/taglib.jsp"%>

<script>
	$(function() {
		$("#startingDateStr").datepicker();
	});
</script>

<div class="col-lg-6">
	<input type="hidden" ${f:hidden("invoiceKey")} />
	<c:if test="${key != null}">
		<input type="hidden" ${f:hidden("key")} />
	</c:if>

	<div class="form-group input-group">
		
		<c:choose>
			<c:when test = "${f:h(installerPrdsChangable)}">
			<span class="input-group-addon">Installer</span> 
			<select name="installer" class="form-control">
				<option value="">Select ...</option>
				<c:forEach items="${installers}" var="i">
					<option value="${f:h(i)}"
						<c:if test = "${f:h(installer) == f:h(i)}" >
							selected
						</c:if>>${f:h(i)}</option>
				</c:forEach>
			</select>
			</c:when>
			<c:otherwise>
			<lable>Installer :${f:h(installer)}</lable>
			</c:otherwise>
		</c:choose>
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
		<select name="status" class="form-control"
			<c:if test = "${f:h(notCompleteCancelable)}">
				disabled
			</c:if>
		
		>
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
	<c:choose>
		<c:when test = "${f:h(installerPrdsChangable)}">
			<label>Check the Products For the Job:</label>
			
			<c:forEach items="${checkedPrds}" var="pp">
				<div class="checkbox">
					<label>
						<input type="checkbox" name="prds" value="${f:h(pp.key)}" checked> ${f:h(pp.model)}
					</label>
				</div>
			</c:forEach>
			<c:forEach items="${prds}" var="p">
				<div class="checkbox">
					<label>
						<input type="checkbox" name="prds" value="${f:h(p.key)}"> ${f:h(p.model)}
					</label>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<lable>Products For the Job:<br></lable>
				<c:forEach items="${checkedPrds}" var="pp">
					<lable> ${f:h(pp.model)}<br> </lable>
				</c:forEach>
				
			
		</c:otherwise>
	</c:choose>
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

