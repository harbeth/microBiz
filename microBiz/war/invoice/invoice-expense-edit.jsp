<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
     
<form id="invoiceDetailExpenseDetailForm" role="form" method="post" action="${f:url('/invoice/invoiceExpenseEditAction')}">
	<div class="col-lg-6">
	    <input type="hidden" ${f:hidden("invoiceKey")}/>
		<c:if test="${key != null}">
			<input type="hidden" ${f:hidden("key")} />
		</c:if>
		
		<div class="form-group input-group">
			<span class="input-group-addon">Expense</span> 
			<input type="text" valueType="price" ${f:text("expense")} class="form-control" />
		</div>

		<div class="form-group input-group">
			<span class="input-group-addon">Report Date</span> 
			<input type="text" ${f:text("reportDateStr")} class="form-control" id="reportDateStr"/>
		</div>
	
		<div class="form-group input-group">
			<span class="input-group-addon">Notes</span>
			<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
		</div>
		
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</div>
	
	<div class="panel-heading">
		<a link="invoiceEditCloseExpense" invoiceKey="${f:h(invoiceKey)}" class="btn btn-info btn-sm" role="button">Close</a>
	</div>
</form>