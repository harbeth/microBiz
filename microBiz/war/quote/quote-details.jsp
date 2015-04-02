<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	quoteDetailFn.init();
	});
</script>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-primary">
		<div class="panel-heading">
			Quote Details
		</div>
		<div class="panel-dody">
			<div id="quoteInfoDIV">
				<jsp:include page="quote-detail-info.jsp" flush="true"></jsp:include>
			</div>
			<div class="panel-footer">
				<a link="quoteEditClose"  data-toggle="tab" aria-expanded="false" class="btn btn-default btn-sm" role="button">Back to Quotation List</a>
				<a link="downloadQuote"  class="btn btn-default btn-sm" role="button">Download</a>
				<a link="emailToCustomer"  class="btn btn-default btn-sm" role="button">Email to Customer</a>
			</div>
		</div>
	</div>
</div>
 
<ul class="nav nav-pills">
	<li class="active">
		<a link="quoteDetailVersion" data-toggle="tab" aria-expanded="false">Versions</a>
	</li>
    <li class="">
    	<a link="quoteDetailInfo" data-toggle="tab" aria-expanded="false">Edit</a>
    </li>
    <li class="">
    	<a link="quoteDetailLogEvent"  data-toggle="tab" aria-expanded="false">Logs</a>
    </li>
</ul>
<!-- Page content : by default job div shown -->
<div id="quoteDetailVersionDIV" type="tab" quoteKey="${f:h(quote.key)}" class="row">
	<jsp:include page="./quote-order-list.jsp" flush="true"></jsp:include>
</div>

<div id="quoteDetailInfoDIV" type="tab" quoteKey="${f:h(quote.key)}" class="row" hasContent="n">
</div>

<div id="quoteDetailLogEventDIV" type="tab" quoteKey="${f:h(quote.key)}" class="row" hasContent="n"></div>

