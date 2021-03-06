<%@include file="../includes/taglib.jsp"%>
<!-- 
<jsp:include page="./manager-payment-edit.jsp" flush="true" />
 -->

<div class="panel panel-red">
	<div class="panel-heading">
	   Uncomplete Jobs 
	</div>
	<div class="panel-body">
	<div id="managerJobDetailDIV" hasContent="n">
	</div>
	<div class="panel-footer">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>Invoice #</th>
						<th>Address</th>
						<th>Installer</th>
						<th>Start Date</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="j" items="${jobs}">
						<tr>
							<td>${f:h(j.invoiceRef.model.invoiceNumber)}</td>
							<td>${f:h(j.invoiceRef.model.address)}</td>
							<td>${f:h(j.installer)}</td>
							<td>${f:h(j.startingDateStr)}</td>
							<td><a link="managerJobEdit" jobKey="${f:h(j.key)}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>