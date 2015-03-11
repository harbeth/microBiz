<%@include file="../includes/taglib.jsp"%>

<script type ="text/javascript">

	var prdRatioData = {
		// keep increasing, only for rowIndex value, not for loop since deleting
		rowCount : 0,
		getRowCount : function() {
			return this.rowCount;
		},
		setRowCount : function(rowCountParam) {
			this.rowCount = rowCountParam;
		},
		increaseRowCountByOne : function() {
			return this.rowCount++;
		}
	}

	var prdRatioFn = {
		init : function() {
			// register existing remove button event
			this.registerRemoveBtnEvent();
			this.registerAddNewRowEvent();
			var rowCount = $("table[name=prdRatioTbl] tbody tr[rowIndex != -1]").length;
			prdRatioData.setRowCount(rowCount);

		}
		, registerAddNewRowEvent : function() {
			// back to invoice list page 
			$("a[link=addRow]").click(function() {
				var rowCount = prdRatioData.increaseRowCountByOne();
				// clone last one before change, :last
				var trCtrl = $("table[name=prdRatioTbl] tbody tr[rowIndex=-1]");
				var clonedTr = trCtrl.clone();
				// after clone change source
				// update tr rowIndex, 
				clonedTr.attr("rowIndex", rowCount).show();
				clonedTr.find("input[prdInput=true]").attr("rowIndex", rowCount);
				// register remove event
				var removeBtnCtrl = clonedTr.find("a[link=removeRow]");
				removeBtnCtrl.attr("rowIndex", rowCount);
				prdRatioFn.registerRemoveBtnEventWithCtrl(removeBtnCtrl);
				// append to table, remove value
				$("table[name=prdRatioTbl] tbody").append(clonedTr);
			});
		}
		// called by init
		, registerRemoveBtnEvent : function() {
			this.registerRemoveBtnEventWithCtrl($("a[link=removeRow][rowIndex!=-1]"));
		}
		, registerRemoveBtnEventWithCtrl : function(ctrl) {
			ctrl.click(function() {
				var rowIndex = $(this).attr("rowIndex");
				// remove tr
				$("table[name=prdRatioTbl] tbody tr[rowIndex="+ rowIndex + "]").remove();
			});
		}

	}

	$(document).ready(function() {
		prdRatioFn.init();
	});
</script>
<div class="row">
	<h3 class="page-header">Product Edit</h3>
</div>
<!-- /.row -->

<!-- Page conent -->
<div class="row">
	<div class="col-lg-12">
		<form  name="productEditForm" typerole="form" method="post" action="${f:url('/prd/productEditAction')}">
			<div class="col-lg-6">
				<c:if test="${key!=null}">
					<input type="hidden" ${f:hidden("key")} />
				</c:if>
	
				<div class="form-group input-group">
					<span class="input-group-addon">Product Type</span>
					<select name="type" mandatory="y" field="Product Type" class="form-control">
						<option value="-1">Select ... </option>
						<c:forEach items="${prdTypes}" var="pt">
							<option value="${f:h(pt.value)}"
								<c:if test = "${f:h(type) == f:h(pt.value)}" >
									selected
								</c:if>>${f:h(pt.label)}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group input-group">
					<span class="input-group-addon">Name/Model</span>
					<input type="text" mandatory="y" field="Name/Model" ${f:text("model")} class="form-control" />
				</div>
				
				<div class="form-group input-group">
					<span class="input-group-addon">Description</span>
					<input type="text" ${f:text("desc")} class="form-control" />
				</div>
	
				<div class="form-group input-group">
					<span class="input-group-addon">Rate</span> 
					<input type="text" mandatory="y" field="Price" valueType="price" ${f:text("rate")} class="form-control" />
				</div>
				
				<div class="form-group input-group">
					<span class="input-group-addon">Unit</span>
					<select name="unit" mandatory="y" field="Unit" class="form-control">
						<option value="-1">Select ... </option>
						<c:forEach items="${units}" var="u1">
							<option value="${f:h(u1.value)}"
								<c:if test = "${f:h(unit) == f:h(u1.value)}" >
									selected
								</c:if>>${f:h(u1.label)}
							</option>
						</c:forEach>
					</select>
				</div>
	
				<div class="form-group">
					<label>Active</label>
						<label class="checkbox-inline">
						<input type="checkbox" ${f:checkbox("active")}> yes
					</label>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="form-group input-group">
					<span class="input-group-addon">Consume Report Unit</span>
					<select name="consumeReportUnit" class="form-control">
						<option value="-1">Select ... </option>
						<c:forEach items="${units}" var="u2">
							<option value="${f:h(u2.value)}"
								<c:if test = "${f:h(consumeReportUnit) == f:h(u2.value)}" >
									selected
								</c:if>>${f:h(u2.label)}
							</option>
						</c:forEach>
					</select>
				</div>
		
				<div class="form-group input-group">
					<span class="input-group-addon">Supplier</span>
						<select name="supplier" class="form-control">
							<option value="-1">Select ... </option>
							<c:forEach items="${suppliers}" var="s">
								<option value="${f:h(s.key)}"
									<c:if test = "${f:h(supplierRef.model.key) == f:h(s.key)}">
										selected
									</c:if>>${f:h(s.name)}</option>
							</c:forEach>
					</select>
				</div>
				<div id="productRatioDIV">
					<h4>Product Ratio
					<a link="addRow" data-toggle="tab" aria-expanded="false" class="btn btn-default btn-sm" role="button">Add</a></h4>
					<table name="prdRatioTbl" class="table">
						<thead>
							<tr>
								<td style="width: 40%" class="text-center">
									<label>Description</label>
								</td>
								<td style="width: 40%" class="text-center">
									<label>Ratio</label>
								</td>
								<td style="width: 10%" class="text-center">
								</td>
							</tr>
						</thead>
						<tbody>
							<tr rowIndex="-1" style="display: none;">
								<td><input class="form-control input-sm" rowIndex="-1" prdInput="true" name="ratioDescs" value="" /></td>
								<td><input class="form-control input-sm" rowIndex="-1" prdInput="true" name="ratios" value="" /></td>
								<td class="text-center">
									<a link="removeRow" rowIndex="-1" data-toggle="tab" aria-expanded="false" class="btn btn-default btn-sm" role="button">Remove Row</a>
								</td>
							</tr>
							<c:forEach items="${prdRatios}" var="pr" varStatus="status">
								<tr rowIndex="${status.index}">
									<td><input class="form-control input-sm" rowIndex="${status.index}" prdInput="true" name="ratioDescs" value="${f:h(pr.desc)}" /></td>
									<td><input class="form-control input-sm" rowIndex="${status.index}" prdInput="true" name="ratios" value="${f:h(pr.ratio)}" /></td>
									<td class="text-center">
										<a link="removeRow" rowIndex="${status.index}" data-toggle="tab" aria-expanded="false" class="btn btn-default btn-sm" role="button">Remove Row</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
	
			<div class="form-group">
				<button type="submit" class="btn btn-default">Submit</button>
				<a link="productEditClose"  class="btn btn-default btn-sm" role="button">Close</a>
			</div>
		</form>
	</div>
</div>






