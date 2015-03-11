<%@include file="../includes/taglib.jsp"%>
<script>
	$(function() {
		$("input[valueType=price]").numeric();
	});
</script>
<div class="col-lg-6">
	<c:forEach items="${prds}" var="p" varStatus="loop">
		<div class="form-group input-group">
			<span class="input-group-addon">${f:h(p.model)} ${f:nbsp('  ')}
				${f:h(p.consumeReportUnitLable)} * </span> <input type="text" name="qty"
				value="${f:h(qty[loop.index])}" mandatory="y" field="material used" valueType="price" class="form-control" />
			<c:if test="${f:h(p.showRatio==false)}">
				<input type="hidden" name="prdRatioKey" value="-1" />
			</c:if>
			<c:if test="${f:h(p.showRatio==true)}">
				by *
                 <select name="prdRatioKey" mandatory="y" field="product ratio">
					<option value="-1">Select</option>
					<c:forEach items="${p.prdRatioList}" var="r">
						<option value="${f:h(r.key)}"
							<c:if test = "${f:h(r.key) == f:h(prdRatioKey[loop.index])}" >
						selected
					</c:if>>
							${f:h(r.desc)}</option>
					</c:forEach>
				</select>
			</c:if>
		</div>
	</c:forEach>
</div>
<div class="col-lg-6">
	<div class="form-group input-group">
		<span class="input-group-addon">Travel Hours:*</span> <input
			type="text" ${f:text("travelHours")} mandatory="y" field="travel hours" valueType="price" class="form-control" />
	</div>

	<div class="form-group input-group">
		<span class="input-group-addon">Working Hours:*</span> <input
			type="text" ${f:text("workingHours")} mandatory="y" field="working hours" valueType="price" class="form-control" />
	</div>
	<div class="form-group input-group">
		<span class="input-group-addon">Notes</span>
		<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
	</div>
</div>
<div class="form-group">
	<button type="submit" class="btn btn-default">Submit</button>
</div>

