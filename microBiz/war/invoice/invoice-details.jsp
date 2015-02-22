<%@include file="../includes/taglib.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		invoiceDetailFn.init();
	});
</script>
<!-- Page Heading -->
<div class="col-lg-12">
	<div class="panel panel-default">
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#invoiceDetailForSales"
					data-toggle="tab">Invoice Details</a></li>
				<li><a href="#invoiceDetailForMgmt" data-toggle="tab">Management</a>
				</li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane fade in active" id="invoiceDetailForSales">
					<jsp:include page="invoice-detail-info.jsp" flush="true"></jsp:include>

					<jsp:include page="./invoice-order.jsp" flush="true" />

				</div>
				<div class="tab-pane fade" id="invoiceDetailForMgmt">
					<div class="row">
						<div class="col-lg-12">
							<h3></h3>
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<div class="row">

						<div class="col-lg-4 col-md-8">
							<div class="panel panel-green">
								<div class="panel-heading">
									<li>Closed 2/12/2015</li>
									<li>4 Jobs Completed</li>
									<li>6 Jobs On going</li>
								</div>
							
									<div class="panel-footer">
										<span class="pull-left"><a link="invoiceJobEdit"
											invoiceKey="${f:h(invoice.key)}" jobKey="-1">Assign Job</a></span>

										<span class="pull-right"><a link="">View Details</a></span>
										<div class="clearfix"></div>
									</div>
							
							</div>
						</div>
						<div class="col-lg-4 col-md-8">
							<div class="panel panel-yellow">
								<div class="panel-heading">
									<li>30 Days Term</li>
									<li>Amount: $4789</li>
									<li>Received: $0</li>
								</div>
							
									<div class="panel-footer">
										<span class="pull-left"><a link="invoicePaymentEdit"
											invoiceKey="${f:h(invoice.key)}" paymentKey="-1"
											role="button">Receive Payment</a></span> <span class="pull-right"><a
											link="">View Details</a></span>
										<div class="clearfix"></div>
									</div>
							
							</div>
						</div>
						<div class="col-lg-4 col-md-8">
							<div class="panel panel-red">
								<div class="panel-heading">
									<li>Profit Margin: 10%</li>
									<li>Labor Cost: $4789</li>
									<li>Material Cost: $9087</li>
								</div>
						
									<div class="panel-footer">
										<span class="pull-left"><a link="invoiceExpenseEdit"
											invoiceKey="${f:h(invoice.key)}" expenseKey="-1"
											role="button">Add Expense</a></span> <span class="pull-right"><a
												link="">View Details
								</a></span>
								<div class="clearfix"></div>
							</div>
						
						</div>
					</div>
<div class="row">
                <div class="col-lg-12">
             
                Toronto police are searching for a three-year-old boy after he wandered out of an apartment building in the frigid cold in the city's north end during the early-morning hours. 
The boy's family awoke Thursday around 7:30 a.m. to find he was missing from their apartment on Neptune Drive in the Bathurst Street and Highway 401 area. They last saw him when they put him to bed around 9:30 p.m. Wednesday. 
Toronto police say security camera footage from the building shows the boy leaving on his own at around 4:20 a.m.
Elijah is described as:
- Average height and build.
- Having braided hair to the back of his neck.
- Was wearing a shirt and pull-up diaper, and his boots were missing from the apartment. 
Police have set up a command post and are searching the area surrounding the apartment building.
Officers from the division where the boy was living, 31 Division, and all officers from the adjacent 32 and 33 divisions who are not assigned to other calls are currently helping in the search. At least one officer from each other division in the city has also been sent to help. 
There are multiple horseback and canine units involved as well, as there are a number of large parks in the immediate vicinity of the building. York Regional Police has lent To
           
</div>
</div>
				</div>
			</div>
		</div>
	</div>
</div>




