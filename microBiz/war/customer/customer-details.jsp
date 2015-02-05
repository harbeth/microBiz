<%@include file="../includes/taglib.jsp"%>
<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div id="customerInfoDIV">
				<jsp:include page="customer-detail-info.jsp" flush="true"></jsp:include>
			</div>
			<div class="panel-heading">
				<c:if test = "${f:h(customer.commercial)}" >
					<a link="customerContactEdit" customerKey="${f:h(customer.key)}" contactKey="-1"  class="btn btn-info btn-sm" role="button">New Contact</a>
				</c:if>
				<a href="/invoice/invoiceCreate?customerkey=${f:h(customer.key)}" class="btn btn-info btn-sm" role="button">New Invoice</a>  
				<a href="/quote/quoteCreate?customerKey=${f:h(customer.key)}" class="btn btn-info btn-sm" role="button">New Quotation</a>
				<a link="customerEditClose"  class="btn btn-info btn-sm" role="button">Close</a>
			</div>
		</div>
	</div>
</div>
<!-- /.row -->

<ul class="nav nav-tabs">
	<li class="active">
		<a link="customerDetailQuote" data-toggle="tab" aria-expanded="false">Quote</a>
	</li>
	<c:if test = "${f:h(customer.commercial)}" >
		<li class="">
			<a link="customerDetailContact" data-toggle="tab" aria-expanded="false">Contact</a>
		</li>
	</c:if>
	<li class="">
		<a link="customerDetailInvoice" data-toggle="tab" aria-expanded="false">Invoice</a>
	</li>
	<li class="">
    	<a link="customerDetailInfo" data-toggle="tab" aria-expanded="false">Edit</a>
    </li>
</ul>

<!-- Page content : customer my not have contact -->
<div id="customerDetailContactDIV" type="tab" customerKey="${f:h(customer.key)}" class="row" hasContent="n">
</div>

<div id="customerDetailInvoiceDIV" type="tab" customerKey="${f:h(customer.key)}" class="row" hasContent="n">
</div>

<div id="customerDetailQuoteDIV" type="tab" customerKey="${f:h(customer.key)}" class="row" hasContent="y">
	<jsp:include page="customer-quotes.jsp" flush="true"></jsp:include>
</div>

<div id="customerDetailInfoDIV" type="tab" customerKey="${f:h(customer.key)}" class="row" hasContent="n">
</div>
