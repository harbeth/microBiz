<%@include file="../includes/taglib.jsp"%>
<!-- 
<jsp:include page="./manager-payment-edit.jsp" flush="true" />
 -->

<div class="panel panel-green">
	<div class="panel-heading">
	   Open Invoices
	</div>
	<div class="panel-body">
	<div id="managerInvoiceDetailDIV" hasContent="n">
	</div>
	<div class="panel-footer">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>Invoice #</th>
						<th>Address</th>
						<th>Prefer Install Date</th>
						<th>Open Jobs</th>
						<th>Complete Jobs</th>
						<th>Sales</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${invoices}">
						<tr>
							<td><a href="/invoice/editInvoice?invoiceKey=${f:h(i.key)}">${f:h(i.invoiceNumber)}</a></td>
							<td>${f:h(i.address)}</td>
							<td>${f:h(i.preferIntlDateStr)}</td>
							<td>${f:h(i.invoiceReportRef.model.onGoingJobCount)}</td>
							<td>${f:h(i.invoiceReportRef.model.completeJobCount)}</td>
							
							<td>${f:h(i.sales)}</td>
							<td>
								<a link="managerInvoiceEdit" invoiceKey="${f:h(i.key)}">Edit</td>
							</td>
							<td>
							<c:if test = "${f:h(i.showAssignJobLink)}">
								<a link="managerInvoiceJobAssign" invoiceKey="${f:h(i.key)}">Assign Job</a>
							</c:if>
							
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>