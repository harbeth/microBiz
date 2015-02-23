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
				<li class="active">
					<a href="#invoiceForSales" data-toggle="tab">Invoice Details</a>
				</li>
				<li>
					<a href="#invoiceForMgmt" data-toggle="tab">Management</a>
				</li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade in active" id="invoiceForSales">
					<jsp:include page="./invoice-detail-tab.jsp" flush="true"></jsp:include>
				</div>
				<div class="tab-pane fade" id="invoiceForMgmt">
					<jsp:include page="./invoice-manage.jsp" flush="true"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>