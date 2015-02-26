<%@include file="../includes/taglib.jsp"%>

<!-- Page conent -->

<div class="row">
	<div class="panel-heading">
		Add inventory for product: ${f:h(product.model)} <br>
		Current Quantity is: ${f:h(product.currentQty)}, ${f:h(product.unit)}
	</div>
</div>
<div class="row">

	<form  name="inventoryAddForm" typerole="form" method="post" action="${f:url('/prd/inventoryAddAction')}">
		<div class="col-lg-6">
			
				<input type="hidden" name="productKey" value="${f:h(product.key)}" >

			<div class="form-group input-group">
				<span class="input-group-addon">Add Quantity:</span> <input type="text"
					${f:text("changeQty")} class="form-control" />
			</div>
 		<div class="form-group input-group">
			<span class="input-group-addon">Notes</span>
			<textarea name="notes" class="form-control" rows="2">${f:h(notes)}</textarea>
		</div>


		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</form>
</div>






