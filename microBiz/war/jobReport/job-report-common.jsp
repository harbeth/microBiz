<%@include file="../includes/taglib.jsp"%>
		


			<c:forEach items="${prds}" var="p" varStatus="loop">
				<div class="form-group input-group">
					<span class="input-group-addon">${f:h(p.model)}
						${f:nbsp('  ')} ${f:h(p.consumeReportUnit)}</span> <input
						type="text" name="qty" value="${f:h(qty[loop.index])}" class="form-control" />
				by
                 <select name="prdRatioKey" >
				<c:forEach items="${p.prdRatioList}" var="r">
				 <option value="${f:h(r.key)}"
 
				 <c:if test = "${f:h(r.key) == f:h(prdRatioKey[loop.index])}" >
						selected
					</c:if>>
				 ${f:h(r.desc)} </option>
				</c:forEach>
				</select>
				
				</div>
			</c:forEach>
</div>
<div class="col-lg-6">
			<div class="form-group input-group">
				<span class="input-group-addon">Travel Hours:</span> <input
					type="text" ${f:text("travelHours")} class="form-control" />
			</div>

			<div class="form-group input-group">
				<span class="input-group-addon">Working Hours:</span> <input
					type="text" ${f:text("workingHours")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Notes</span> <input type="text"
					${f:text("note")} class="form-control" />
			</div>
</div>
			<div class="form-group">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		
	</form>
</div>	
</div>