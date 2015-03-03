<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<div class="table-responsive">
<form id="salesCommissionSelectForm" role="form" method="post" action="${f:url('/manager/salesCommissionAction')}">
	<table class="table table-hover table-striped">
		<thead>
			<tr>
				
				<th>Inv#</th>
				<th>Total</th>
				<th>Address</th>
				<th>Close Date</th>
				<th>Sales Commission</th>
				<th>Note</th>

			</tr>
		</thead>
		
		<tbody>
			
			<c:forEach var="i" items="${invoices}">
				<tr>
					<td>${f:h(i.invoiceNumber)}</td>
					<td>${f:h(i.orderRef.model.total)}</td>
					<td>${f:h(i.address)}</td>
					<td>${f:h(i.statusChangeDateStr)}</td>
					<td><input type="text" name="amt" value="0" /></td>
					<td><input type="text" name="note" value=""/></td>
					<input type="hidden" name="invoiceKey" value="${f:h(i.key)}"/>
				</tr>
			</c:forEach>
			<div class="form-group">
				<button type="submit" class="btn btn-default">Submit</button>
				
			</div>
			
			
		</tbody>
		
	</table>
	</form>
</div>
		
