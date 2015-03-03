<%@include file="../includes/taglib.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		invoiceDetailFn.init();
	});
</script>
<!-- Page Heading -->
<div class="col-lg-12">
	<div class="panel panel-primary">
		 <div class="panel-heading">
           Invoice Details
         </div>
		<div class="panel-body">
		   <p>
		   Inv#: ${f:h(invoice.invoiceNumber)} ${f:nbsp('     ')} 
			<strong> ${f:h(invoice.statusLable)} </strong>  ${f:nbsp('     ')}  
			<span class="fa fa-home">  </span> ${f:h(invoice.address)} ${f:nbsp('     ')}  
			<span class="fa fa-user">  </span>${f:h(invoice.customerRef.model.name)}
			<c:if test="${f:h(invoice.hasContact)}">
			<font class="text-muted"> Contact:</font> ${f:h(invoice.contactRef.model.name)}
			</c:if>
			<span class="pull-right">
			<a href="/pub/invoiceToPdf?invoiceKey=${f:h(invoice.key)}" target="_blank" class="btn btn-default" role="button">Download</a>
			<a href="/invoice/emailInvoice?invoiceKey=${f:h(invoice.key)}" target="_blank" class="btn btn-default" role="button">Email to Customer</a>
			</span>
			</p>
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