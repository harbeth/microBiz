<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<div class="col-lg-12">


		<pre>Edit the Quote Version:
			${f:h(quoteVersion.name)} Created At ${f:h(quoteVersion.createAtStr)} 
        </pre>
			<form name="quoteDetailVersionForm" typerole="form" method="post"
				action="/quote/quoteVersionAction">

				<input type="hidden" name="quoteVersionKey"	value="${f:h(quoteVersion.key)}">
				<div id="quoteVersionDIV" class="row">
					<jsp:include page="./order-item.jsp" flush="true"></jsp:include>
				</div>
				<div class="form-group">
					<input type="radio" name="saveOption" value="save" checked>Save
					<input type="radio" name="saveOption" value="saveAs">Save
					as <input type="radio" name="saveOption" value="convertToInvoice">Convert
					to Invoice
				</div>
				<div class="form-group">
					<button type="submit" name="saveAction" value="save"
						class="btn btn-default">Submit</button>
				</div>
			</form>
		</div>
	</div>


