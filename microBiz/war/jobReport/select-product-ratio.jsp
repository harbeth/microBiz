
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>


${f:h(product.consumeReportUnit)}
<c:if test="${showPrdRatio}">
By 
<c:forEach items="${product.prdRatioList}" var="r">
	 ${f:nbsp('  ')} <input type="radio" name="prdRatioKey", value="${f:h(r.key)}"/>${f:h(r.desc)} 
</c:forEach>
</c:if>