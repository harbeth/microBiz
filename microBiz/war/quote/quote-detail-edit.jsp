<%@include file="../includes/taglib.jsp"%>

<h4>Create/Edit Quote</h4>

<div class="col-lg-6">
	<c:if test="${key != null}">
		<input type="hidden" ${f:hidden("key")} />
	</c:if>
	<jsp:include page="../common/customer-contact-common.jsp" />
	
		<button class="btn btn-default" data-toggle="modal" data-target="#myModal">
                                Launch Demo Modal
    </button>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
		<div class="modal-dialog">
		    <div class="modal-content">
		        <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
		        </div>
		        <div class="modal-body">
		            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		        </div>
		        <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		            <button type="button" class="btn btn-primary">Save changes</button>
		        </div>
		    </div>
	    <!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
	</div>
	
	
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