<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<div class="col-lg-12">
<div class="panel panel-danger">
<div class="panel-body">  

<form id="invoiceDetailExpenseDetailForm" role="form" method="post" action="${f:url('/invoice/invoiceExpenseEditAction')}">

	    <input type="hidden" ${f:hidden("invoiceKey")}/>
		<c:if test="${key != null}">
			<input type="hidden" ${f:hidden("key")} />
		</c:if>
		
		<div class="form-group input-group">
			<span class="input-group-addon">Expense Amount *</span> 
			<input type="text" valueType="price" mandatory="y" field="Expense Amount" name="expense" value="${f:h(expenseStr)}" class="form-control" />
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
					${f:nbsp('         ')}
					<label>For Sales Commission</label>
					<label class="checkbox-inline">
						<input type="checkbox" ${f:checkbox("forSalesCommission")}>yes
					</label>
		</div>
		
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>${f:nbsp('    ')}
			<a link="invoiceEditCloseExpense" invoiceKey="${f:h(invoiceKey)}" class="btn btn-default" role="button">Close</a>
		</div>

</form>
</div>
</div>
</div>