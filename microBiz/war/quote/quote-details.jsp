<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div id="quoteInfoDIV">
				<jsp:include page="quote-detail-info.jsp" flush="true"></jsp:include>
			</div>
			<div class="panel-heading">
				<a link="quoteEditClose"  data-toggle="tab" aria-expanded="false" class="btn btn-info btn-sm" role="button">Close</a>
			</div>
		</div>
	</div>
</div>

<ul class="nav nav-tabs">
	<li class="active">
		<a link="quoteDetailVersion" data-toggle="tab" aria-expanded="false">Version</a>
	</li>
    <li class="">
    	<a link="quoteDetailInfo" data-toggle="tab" aria-expanded="false">Edit</a>
    </li>
</ul>

<!-- Page content : by default job div shown -->
<div id="quoteDetailVersionDIV" type="tab" quoteKey="${f:h(quote.key)}" class="row">
	<jsp:include page="./quote-versions.jsp" flush="true"></jsp:include>
</div>

<div id="quoteDetailInfoDIV" type="tab" quoteKey="${f:h(quote.key)}" class="row" hasContent="n">
</div>

