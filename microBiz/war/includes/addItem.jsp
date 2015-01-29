
<div class="row">

	<div class="form-group">

		<div class="col-lg-3">
			Item:<select name="item[]" class="form-control">
				<c:forEach items="${prds}" var="p">
					<option value="${f:h(p.key)}">${f:h(p.model)}--${f:h(p.sellingRate)}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-lg-3">
			Description: <input class="form-control" type="text" name="desc[]"
				placeholder="Textbox #1" />
		</div>

		<div class="col-lg-2">
			Rate: <input class="form-control" type="text" name="rate[]"
				placeholder="Textbox #1" />
		</div>

		<div class="col-lg-2">
			Quantity: <input class="form-control" type="text" name="quantity[]"
				placeholder="Textbox #1" />
		</div>
		<div class="col-lg-1">
			<br>
			<button type="button" class="btn btn-default btn-sm addButton"
				data-template="textbox">Add</button>
		</div>
	</div>
	<div class="form-group hide" id="textboxTemplate">

		<div class="col-lg-3">
			<select name="item[]" class="form-control">
				<c:forEach items="${prds}" var="p">
					<option value="${f:h(p.key)}">${f:h(p.model)}--${f:h(p.sellingRate)}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-lg-3">

			<input class="form-control" type="text" name="desc[]"
				placeholder="Textbox #1" />
		</div>

		<div class="col-lg-2">

			<input class="form-control" type="text" name="rate[]"
				placeholder="Textbox #1" />
		</div>

		<div class="col-lg-2">

			<input class="form-control" type="text" name="quantity[]"
				placeholder="Textbox #1" />
		</div>
		<div class="col-lg-1">

			<button type="button" class="btn btn-default btn-sm removeButton">Remove</button>
		</div>
	</div>

</div>

<div class="row">

	<p>
		<br>
	</p>

</div>
<div class="row">

	<div class="col-lg-3">
		Sub Total: <input type="text" name="subtotal" class="form-control"
			disabled />

	</div>
	<div class="col-lg-3">
		Tax Rate:<select name="txRate" class="form-control">
			<c:forEach items="${txRates}" var="tr">
				<option value="${f:h(tr)}">${f:h(tr)}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-lg-3">
		Total:<input type="text" name="total" class="form-control" disabled />
	</div>
	<div class="col-lg-3">
		<br>
		<button type="submit" class="btn btn-default">Submit</button>

	</div>

</div>
<script type="text/javascript">
	$(document).ready(function() {
						$('.addButton').on('click',
										function() {
											var index = $(this).data('index');
											if (!index) {
												index = 1;
												$(this).data('index', 1);
											}
											index++;
											$(this).data('index', index);

											var template = $(this).attr(
													'data-template'), $templateEle = $('#'
													+ template + 'Template'), $row = $templateEle
													.clone().removeAttr('id')
													.insertBefore($templateEle)
													.removeClass('hide'), $el = $row
													.find('input').eq(0).attr(
															'name',
															template + '[]');
											$('#defaultForm').bootstrapValidator('addField', $el);

											$row.on('click','.removeButton',
															function(e) {
																$('#defaultForm')
																		.bootstrapValidator(
																				'removeField',
																				$el);
																$row.remove();
															});
										});

					});
</script>