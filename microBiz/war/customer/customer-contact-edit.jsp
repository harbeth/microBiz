<%@include file="../includes/taglib.jsp"%>
<div class="col-lg-12">
	<div class="well">
		<form id="customerDetailContactDetailForm" role="form" method="post" action="${f:url('/customer/customerContactEditAction')}">
			<div class="col-lg-6">
			    <input type="hidden" ${f:hidden("customerKey")}/>
				<c:if test="${key != null}">
					<input type="hidden" ${f:hidden("key")} />
				</c:if>
				
				<div class="form-group input-group">
					<span class="input-group-addon">Name *</span>
					<input type="text" mandatory="y" field="Name" ${f:text("name")} class="form-control" />
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">Type *</span> 
					<input type="text" mandatory="y" field="Type" valueType="price" ${f:text("type")} class="form-control" />
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">Phone</span> 
					<input type="text" ${f:text("phone")} class="form-control" />
				</div>
				</div>
				<div class="col-lg-6">
				<div class="form-group input-group">
					<span class="input-group-addon">Email</span> 
					<input type="text" ${f:text("email")} class="form-control" />
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">Note</span> 
					<input type="text" ${f:text("notes")} class="form-control" />
				</div>
			    </div>
		
				<button type="submit" class="btn btn-info">Submit</button>
				<a link="customerContactEditClose" customerKey="${f:h(customerKey)}" class="btn btn-info btn-sm" role="button">Close</a>
		
		</form>
	</div>
</div>