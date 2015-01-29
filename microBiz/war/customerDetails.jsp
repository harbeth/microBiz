<%@include file="includes/header.jsp"%>

	<div class="col-lg-12">
		<h1 class="page-header">
			Customer Details <small><a href="/contact?customerKey=${f:h(customer.key)}"
				class="btn btn-info btn-sm" role="button">New Contact</a> </small>
				 <small>
				 <a href="/quote?customerKey=${f:h(customer.key)}"
				class="btn btn-info btn-sm" role="button">New Quote</a> </small>
		</h1>

	</div>


	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Customer Information</div>
			<div class="panel-body">
				<div class="col-lg-6">
					<p>
						Type : ${f:h(customer.type)} <br> 
						Name :${f:h(customer.name)} <br> 
						Address:${f:h(customer.address)} <br>
						Email: ${f:h(customer.email)}
					</p>
				</div>
				<!-- /.col-lg-4 -->
				<div class="col-lg-6">
					<p>
						Phone : ${f:h(customer.phone)} <br> 
						Alt. Phone :${f:h(customer.altPhone)} <br>
						 Active:${f:h(customer.active)}	<br> 
						 Rating: ${f:h(customer.rating)}<br> 
						 Notes:	${f:h(customer.notes)}<br>
					</p>
				</div>
			</div>

		</div>
	</div>



		<div class="panel-body">

			<div class="panel-group" id="accordion">
				<div class="panel panel-default">


					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo">Contact List</a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse">
						<div class="panel-body">

							<div class="table-responsive">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th>Type</th>
											<th>Phone</th>
											<th>Email</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="c" items="${contacts}">
											<tr>
												<td>${f:h(c.name)}</td>
												<td>${f:h(c.type)}</td>
												<td>${f:h(c.phone)}</td>
												<td>${f:h(p.email)}</td>
												<td><a href="/contact?key=${f:h(c.key)}">Edit</a></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree">Quotation List</a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse">
						<div class="panel-body">
						
						<ul>
                               <c:forEach var="q" items="${quotes}">
                               	<li>${f:h(q.address)}</li>
                               	
                              
                               	</c:forEach>
                              
                            </ul>
							
							
							</div>
					</div>
				</div>
			</div>
		</div>
		<!-- .panel-body -->



<%@include file="includes/footer.jsp"%>
