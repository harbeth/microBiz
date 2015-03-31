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
						<th>Name</th>
						<th>Type</th>
						<th>Phone</th>
						<th>Address</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${customers}">
						<tr>
							<td><a link="customerEditDetail" customerKey="${f:h(c.key)}">${f:h(c.name)}</a></td>
							<td>${f:h(c.typeLable)}</td>
							<td>${f:h(c.phone)}</td>
							<td>${f:h(c.address)}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
</div>
<jsp:include page="../common/paginating.jsp" flush="true"></jsp:include>
</c:otherwise>
</c:choose>
