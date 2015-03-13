<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<h4> Sales: ${f:h(salesName)}  ${f:nbsp('                    ')} Date: ${f:h(createdDate)} </h4>
<div class="table-responsive">
	
		<table class="table">
			<thead>
				<tr>
					<th>Inv#</th>
					<th>Address</th>
					<th>Signed Date</th>
					<th>Closed Date</th>
					<th>Amount</th>
					<th>Note</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${salesCommissionRecords}">
					<tr>
						<td>${f:h(s.invoiceNumber)}</td>
						<td>${f:h(s.address)}</td>
						<td>${f:h(s.signedDate)}</td>
						<td>${f:h(s.closedDate)}</td>
						<td>${f:h(s.amount)}</td>
						<td>${f:h(s.note)}</td>
					
					</tr>
				</c:forEach>
			</tbody>
		</table>
</div>
		
