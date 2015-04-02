<%@include file="../includes/taglib.jsp"%>
<c:choose>
<c:when test="${msg != null}">
	<p>${f:h(msg)}</p>
</c:when>
<c:otherwise>

<div class="table-responsive">
	<table class="table  table-striped">
		<thead>
			<tr>
				<th>Invoice #</th>
				<th>Customer</th>
				<th>Address</th>
				<th>Preferred Install At</th>
				<th>Sales</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="i" items="${invoices}">
			<tr>
				<td><a link="invoiceEditDetail" invoiceKey="${f:h(i.key)}">${f:h(i.invoiceNumber)}</a></td>
				<td>${f:h(i.customerRef.model.name)}</td>
				<td>${f:h(i.address)}</td>
				<td>${f:h(i.preferIntlDateStr)}</td>
				<td>${f:h(i.sales)}</td>
			</tr>
		</c:forEach>

		</tbody>
	</table>
</div>
<jsp:include page="../common/paginating.jsp" flush="true"></jsp:include>
</c:otherwise>
</c:choose>
