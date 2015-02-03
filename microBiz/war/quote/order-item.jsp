<%@include file="../includes/taglib.jsp"%>

<%-- 
put script here for independent 
how to deal with Key enter event
--%>

<script type ="text/javascript">
    var orderItemModuleData = {
    	// keep increasing, only for rowIndex value, not for loop since deleting
    	rowCount: 0	
    	, getRowCount: function() {
    		return this.rowCount;
    	}
    	, setRowCount: function(rowCountParam) {
    		this.rowCount = rowCountParam;
    	}
    	, increaseRowCountByOne: function() {
    		return this.rowCount ++;
    	}
    }
    
    var orderItemFn = {
    	init: function() {
    		// set calType
    		// register blur event for calculation on  row rate / qty, rowTotal
    		$("input[calType=rate][rowIndex != -1], input[calType=qty][rowIndex != -1]").on("blur",function(){
    			// register existing first
    			orderItemFn.onCalTextBlur();
			});
    		
    		// register existing remove button event
    		this.registerRemoveBtnEvent();
    		
    		// taxRate, use live for new created one
    		$("select[calType=taxRate][rowIndex != -1]").on("change",function(){
    			// only affect total
    			var taxRate = $(this).val();
				var taxRateFloat = parseFloat(taxRate);
				// get subTotal from input text
				var subTotal = $("span[calType=subTotal]").text();
				// remove $
				subTotal = subTotal.substring(1);
				var subTotalFloat = parseFloat(subTotal);
				
				var totalFloat = subTotalFloat;
				if ( subTotalFloat > 0 && taxRateFloat > 0 ) {
					totalFloat = subTotalFloat * ( 1 + taxRateFloat);
				}
				var discount = $("input[calType=discount]").val();
				if ( totalFloat > 0 && discount != "" ) {
					var discountFloat = parseFloat(discount);
					totalFloat -= discountFloat;
				}
				// round to two decimals
				totalFloat = orderItemFn.roundToTwoDecimal(totalFloat);
				$("span[calType=total]").text("$"+totalFloat);
				// set total to hidden field
				$("input[name=total]:hidden").val(totalFloat);
    		});
    		
    		// discount
    		$("input[calType=discount]").blur(function() {
    			// only affect total
    			var taxRate = $("select[calType=taxRate]").val();
				var taxRateFloat = parseFloat(taxRate);
				// get subTotal from input text
				var subTotal = $("span[calType=subTotal]").text();
				// remove $
				subTotal = subTotal.substring(1);
				var subTotalFloat = parseFloat(subTotal);
				
				var totalFloat = subTotalFloat;
				if ( subTotalFloat > 0 && taxRateFloat > 0 ) {
					totalFloat = subTotalFloat * ( 1 + taxRateFloat);
				}
				var discount = $(this).val();
				if ( totalFloat > 0 && discount != "" ) {
					var discountFloat = parseFloat(discount);
					totalFloat -= discountFloat;
				}
				// round to two decimals
				totalFloat = orderItemFn.roundToTwoDecimal(totalFloat);
				$("span[calType=total]").text("$"+totalFloat);
				$("input[name=total]:hidden").val(totalFloat);
    		});
    		
    		// product select change, create new tr, use live for new created one
    		this.registerSelectChangeEvent();
    		// update rowCount after the page is loaded, existing order items
    		var rowCount = $("table[name=orderItemTbl] tbody tr[rowIndex != -1]").length;
    		orderItemModuleData.setRowCount(rowCount);
    	}
    	, registerSelectChangeEvent: function() {
    		$("select[name=items][rowIndex=-1]").on("change",function(){
    			var rowCount = orderItemModuleData.increaseRowCountByOne();
    			// clone last one before change
    			var trCtrl = $("table[name=orderItemTbl] tbody tr:last");
    			var clonedTr = trCtrl.clone();
    			// after clone change source
    			// update tr rowIndex, 
    			trCtrl.attr("rowIndex", rowCount);
    			// update rowTotal rowIndex
    			trCtrl.find("input[calType=rowTotal]").attr("rowIndex", rowCount);
    			// show remove button
    			var removeBtnCtrl = trCtrl.find("button[btnAction=remove]")
    			removeBtnCtrl.attr("rowIndex", rowCount).show();
    			orderItemFn.registerRemoveBtnEventWithCtrl(removeBtnCtrl);
    			trCtrl.find("input[name=descs]").removeAttr("disabled");
    			// update td rowIndex, 
    			trCtrl.find("input[calType=rate], input[calType=qty]").attr("rowIndex", rowCount)
    			// enabled
    			.removeAttr("disabled")
    			// register input event
    			.on("blur",function(){
    				// new create tr
	    			// cannot calculate for the created tr only
	    			// for total, need to get all inputs
	    			orderItemFn.onCalTextBlur();
				});
				// append to table    			
    			$("table[name=orderItemTbl] tbody").append(clonedTr);
    			// after change the source rowIndex, register event for cloned one
    			orderItemFn.registerSelectChangeEvent();
    		});
    	}
    	// called by init
    	, registerRemoveBtnEvent: function() {
    		var ctrl = $("button[btnAction=remove][rowIndex != -1]");
    		// at least one remove button shown, if for new
    		if ( ctrl.length > 0 ) {
    			this.registerRemoveBtnEventWithCtrl(ctrl);
    		}
    	}
    	// call by init and remove button hidden for rowIndex = -1
    	, registerRemoveBtnEventWithCtrl: function(ctrl) {
    		ctrl.on("click", function() {
    			// remove the current tr
    			var rowIndex = $(this).attr("rowIndex");
    			// remove tr
    			$("table[name=orderItemTbl] tbody tr[rowIndex="+rowIndex+"]").remove();
    			// update subTotal and total
    			orderItemFn.onCalTextBlur();
    		});
    	}
    	// no ctrl called by init and remove button event
    	, onCalTextBlur: function() {
    		var ctrl = $("table[name=orderItemTbl] tbody tr[rowIndex != -1]");
			//alert(ctrl.length);
			this.onCalTextBlurWithCtrl(ctrl);
    	}
    	// pass ctrl, jquery object for new created tr
	    , onCalTextBlurWithCtrl: function(ctrl) {
			// assume value is correct
			// update rowTotal
			var rowCount = orderItemModuleData.getRowCount();
			//alert(" row count: " + rowCount);
			var subTotalFloat = 0;
			ctrl.each(function() {
				var rowIndex = $(this).attr("rowIndex");
				var rowRate = $("input[calType=rate][rowIndex="+rowIndex+"]").val();
				var rowQty = $("input[calType=qty][rowIndex="+rowIndex+"]").val();
				//alert("rowIndex:" + rowIndex + ", rowRate: " + rowRate + ", rowQty: " + rowQty);
				if ( rowRate != "" && rowQty != "" ) {
					var rowRateFloat = parseFloat(rowRate);
					var rowQtyInt = parseInt(rowQty);
					var rowTotalFloat = rowRateFloat * rowQtyInt;
					rowTotalFloat = orderItemFn.roundToTwoDecimal(rowTotalFloat);
					subTotalFloat += rowTotalFloat;
					// set rowTotal
					$("input[calType=rowTotal][rowIndex="+rowIndex+"]").val("$"+rowTotalFloat);
				}
			});
			//alert("subTotalFloat: " + subTotalFloat);
			var taxRate = $("select[calType=taxRate]").val();
			var taxRateFloat = parseFloat(taxRate);
			var totalFloat = subTotalFloat;
			if ( subTotalFloat > 0 && taxRateFloat > 0 ) {
				totalFloat = subTotalFloat * ( 1 + taxRateFloat);
			}
			var discount = $("input[calType=discount]").val();
			if ( totalFloat > 0 && discount != "" ) {
				var discountFloat = parseFloat(discount);
				totalFloat -= discountFloat;
			}
			// round to two decimals
			subTotalFloat = orderItemFn.roundToTwoDecimal(subTotalFloat);
			totalFloat = orderItemFn.roundToTwoDecimal(totalFloat);
			$("span[calType=subTotal]").text("$"+subTotalFloat);
			$("span[calType=total]").text("$"+totalFloat);
			// set total to hidden field
			$("input[name=total]:hidden").val(totalFloat);
		}
    	, roundToTwoDecimal: function(valueFloat) {
    		return Math.ceil(valueFloat * 100)/100;
    	}
    	// === public calls =============
    	, validate: function() {
    		var isOK = true;
    		// must have at least one order item
    		var rowCount = $("table[name=orderItemTbl] tbody tr[rowIndex != -1]").length;
    		// product has selection
    		var producCount = $("table[name=orderItemTbl] tbody tr[rowIndex!=-1] select[name=items][rowIndex!=-1] option:selected").length;
    		if ( rowCount == 0 ) {
    			isOK = false;
    			alert("At least have one item.")
    		}else if ( productCount == 0 ) {
    			isOK = false;
    			alert("Product cannnot be empty.");
    		}
    		if ( isOK ) {
    			// cannot have enabled empty rate / qty input
    			$("table[name=orderItemTbl] tbody tr[rowIndex != -1]")
    			    .find("input[calType=rate]:enabled, input[calType=qty]:enabled").each(function(){
    			    	if ( isOK ) {
	   			    		var val = $(this).val();
	       			    	if ( val == "" ) {
	       			    		isOK = false;
	       			    		alert("Rate, Qty cannot be empty.")
	       			    	}
    			    	}
    			    });
    		}
    	}
    	// cannot remove
    	, removeEmptyTr: function() {
    		// before submit the form, delete empty tr
    		$("table[name=orderItemTbl] tbody tr[rowIndex=-1]").remove();
    	}
    }
    
    $(document).ready(function() {
    	orderItemFn.init();
	});
</script>


	<table name="orderItemTbl" >
		<thead>
			<tr>
				<td style="width: 15%" class="text-center">
					<label>Product</label>
				</td>
				<td style="width: 35%" class="text-center">
					<label>Description</label>
				</td>
				<td style="width: 15%" class="text-center">
					<label>Rate</label>
				</td>
				<td style="width: 15%" class="text-center">
					<label>Qty</label>
				</td>
				<td style="width: 15%" class="text-center">
					<label>Total</label>
				</td>
				<td style="width: 5%" class="text-center">
				</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orderItems}" var="oi" varStatus="status">
				<tr rowIndex="${status.index}">
				<td>
					<select name="items" rowIndex="${status.index}">
						<option value="-1">Select</option>
						<c:forEach items="${products}" var="p">
							<option value="${f:h(p.key)}"
								<c:if test = "${f:h(oi.productRef.model.key) == f:h(p.key)}" >
									selected
								</c:if>
							>${f:h(p.model)}--${f:h(p.sellingRate)}
							</option>
						</c:forEach>
					</select>
				</td>
				<td><input class="form-control input-sm" name="descs" value="${f:h(oi.desc)}" /></td>
				<td><input rowIndex="${status.index}" calType="rate" class="form-control input-sm text-right" name="rates" value="${f:h(oi.rate)}" /></td>
				<td><input rowIndex="${status.index}" calType="qty" class="form-control input-sm text-right" name="qtys" value="${f:h(oi.qty)}" /></td>
				<td><input rowIndex="${status.index}" calType="rowTotal" disabled class="form-control input-sm text-right" name="total" value="${f:h(oi.total)}" /></td>
				<td class="text-center">
					<button rowIndex="${status.index}" btnAction="remove" class="btn-remove btn btn-sm btn-danger">
						<li class="fa fa-times fa-fw"></li>
					</button>
				</td>
			</tr>
			</c:forEach>
			<tr rowIndex="-1">
				<td>
					<select name="items" rowIndex="-1">
						<option value="-1">Select</option>
						<c:forEach items="${products}" var="p">
							<option value="${f:h(p.key)}">${f:h(p.model)}--${f:h(p.sellingRate)}</option>
						</c:forEach>
					</select>
				</td>
				<td><input disabled class="form-control input-sm" name="descs" value="" /></td>
				<td><input rowIndex="-1" calType="rate" disabled class="form-control input-sm text-right" name="rates" value="" /></td>
				<td><input rowIndex="-1" calType="qty" disabled class="form-control input-sm text-right" name="qtys" value="" /></td>
				<td><input rowIndex="-1" calType="rowTotal" disabled class="form-control input-sm text-right" name="total" value="" /></td>
				<td class="text-center">
					<button style="display: none;" rowIndex="-1" btnAction="remove" class="btn-remove btn btn-sm btn-danger">
						<li class="fa fa-times fa-fw"></li>
					</button>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td>
					Tax Rate:
					<select name="taxRate" calType="taxRate">
						<c:forEach items="${txRates}" var="tr">
							<option value="${f:h(tr)}" <c:if test="${f:h(tr) eq orders.taxRate}">selected="selected"</c:if> >${f:h(tr)}</option>
						</c:forEach>
					</select>
				</td>
				<td class="text-center">Sub Total:<span calType="subTotal">$${f:h(subTotal)}</span></td>
				<td colspan="2">Discount:<input calType="discount" name="discount" value="${f:h(orders.discount)}"/></td>
				<td colspan="2" class="text-center">Total:<input type="hidden" name="total" value="${f:h(total)}"/><span calType="total">$${f:h(total)}</span></td>
			</tr>
		</tfoot>
	</table>


