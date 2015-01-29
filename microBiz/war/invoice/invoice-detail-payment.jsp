<%@include file="../includes/taglib.jsp"%>

	<div class="col-lg-12">
		<h1 class="page-header">
			<small>
			<a href="/payment?invoiceKey=${f:h(invoice.key)}"
				class="btn btn-info btn-sm" role="button">New Payment</a>
			</small>
		</h1>
	</div>


	<div class="panel-body">
		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseTwo">Payment List</a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse">
				
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-hover table-striped">
								<thead>
									<tr>
										<th>Amount</th>
										<th>Method</th>
										<th>Note</th>
										<th>User</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="p" items="${payments}">
										<tr>
											<td>${f:h(p.amount)}</td>
											<td>${f:h(p.method)}</td>
											<td>${f:h(p.note)}</td>
											<td>${f:h(p.user)}</td>
											<td><a href="/payment?key=${f:h(p.key)}">Edit</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					
					
				</div>
			</div>
		</div>
	</div>
	<!-- .panel-body -->
