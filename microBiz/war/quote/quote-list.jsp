<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	quoteFn.init();
	});
</script>

<!-- /.row -->

<div class="row">
<div class="col-lg-12">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="row">
			<div class="col-sm-6">
				Quotations
			</div>
			<div class="col-sm-6">
				<a link="quoteNew" data-toggle="tab" aria-expanded="false" class="btn btn-default" role="button">New Quotation</a>
			</div>

		</div>
		
	</div>
		<div class="panel-body">
		<div class="col-lg-12">
			<div class="row">
				<form name="quoteSearchForm" role="form" method="post" action="${f:url('/quote/quoteSearch')}">
					<div class="row">
			            <div class="col-sm-6">
			            	<div class="form-group">
			                  <input class="form-control" name="searchQuoteByAddr" placeholder="Address starts with">
			            	</div>
			            </div>
			            <div class="col-sm-3">
			            	<select name="searchStatus" class="form-control">
			            		<option value="0" selected>status ... </option>
								<c:forEach items="${quoteStatus}" var="s">
									<option value="${f:h(s.value)}">${f:h(s.label)}</option>
								</c:forEach>
							</select>
			            </div>
			 			<div class="col-sm-3">
							<button type="submit" class="btn btn-default btn-sm">Submit</button>
							<a link="clearQuoteSearch" data-toggle="tab" aria-expanded="false" class="btn btn-default btn-sm" role="button">Clear</a>
						</div>
						
			        
					</div>
				</form>
			</div>
			<div id="quoteListDIV">
				<jsp:include page="./quote-list-div.jsp" flush="true"></jsp:include>
			</div>
		</div>
	</div>
	</div>
</div>
</div>
