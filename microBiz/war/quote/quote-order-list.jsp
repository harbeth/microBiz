<%@include file="../includes/taglib.jsp"%>

<div class="col-lg-12">
	<div class="well">
		<div class="form-group">
          <form>
			<select class="form-control" id="quoteOrder" name="quoteOrderKey">
				<option value="">Select Versions To View or Change
					Quotation Items</option>
				<c:forEach items="${quoteOrders}" var="qo">

					<option value="${f:h(qo.key)}">${f:h(qo.name)} ${f:nbsp('   ')}
						${f:h(qo.createAtStr)} ${f:nbsp('    ')} Total Amount:
						${f:h(qo.orderRef.model.total)}</option>
						
				</c:forEach>
			</select>
			</form>
		</div>

		<div id="quoteOrderChangeDIV"></div>
	</div>
</div>