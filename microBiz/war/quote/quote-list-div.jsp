<%@include file="../includes/taglib.jsp"%>
<c:choose>
<c:when test="${msg != null}">
	<p>${f:h(msg)}</p>
</c:when>
<c:otherwise>

<div class="table-responsive">
	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>Address</th>
				<th>Customer</th>

				<th>Created At</th>
				<th>Creator</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="q" items="${quotes}">
			<tr>
				<td><a link="quoteEditDetail" quoteKey="${f:h(q.key)}">${f:h(q.address)}</a></td>
				<td>${f:h(q.customerRef.model.name)}</td>
				<td>${f:h(q.createDateStr)}</td>
				<td>${f:h(q.creatorName)}</td>
				<td>${f:h(q.statusLable)}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<jsp:include page="../common/paginating.jsp" flush="true"></jsp:include>
</c:otherwise>
</c:choose>
