<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<script>
	$(function() {
	  $( "#enterDateStr" ).datepicker();
	});
</script>
 <div class="col-lg-12">
	<div class="well">    
		<form id="invoiceDetailPaymentDetailForm" role="form" method="post" action="${f:url('/invoice/invoicePaymentEditAction')}">
		    <input type="hidden" ${f:hidden("invoiceKey")}/>
			<c:if test="${key != null}">
				<input type="hidden" ${f:hidden("key")} />
			</c:if>
			
			<div class="form-group input-group">
				<span class="input-group-addon">Payment Method</span>
				<select name="method" class="form-control">
					<option value="">Select ... </option>
					<c:forEach items="${paymentTypes}" var="pt">
						<option value="${f:h(pt.value)}"
							<c:if test = "${f:h(method) == f:h(pt.value)}" >
								selected
							</c:if>>${f:h(pt.label)}</option>
					</c:forEach>
				</select>
			</div>		
	
			<div class="form-group input-group">
				<span class="input-group-addon">Amount</span> 
				<input type="text" valueType="price" ${f:text("amount")} class="form-control" />
			</div>
	
			<div class="form-group input-group">
				<span class="input-group-addon">Notes</span>
				<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
			</div>
			
					<div class="form-group">
					<label>Canceled</label>
					<label class="checkbox-inline">
						<input type="checkbox" ${f:checkbox("canceled")}>yes
					</label>
		</div>
		
			<div class="form-group">
				<button type="submit" class="btn btn-default">Submit</button> 
				${f:nbsp('     ')}
				<a link="invoiceEditClosePayment" invoiceKey="${f:h(invoiceKey)}" class="btn btn-default " role="button">Close</a>
			</div>
	
		</form>
	</div>
</div>
