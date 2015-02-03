<%@include file="../includes/taglib.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<c:forEach items="${quoteVersions}" var="qv">
			<input type="radio" name="quoteVersionGroup" value="${f:h(qv.key)}" 
				<c:if test="${f:h(qv.key) eq f:h(selectedKey)}">checked</c:if> 
			>${f:h(qv.name)}--${f:h(qv.createdAt)}&nbsp;
		</c:forEach>
	</div>
</div>
<div id ="quoteVersionChangeDIV">
	<jsp:include page="./quote-version.jsp" flush="true"></jsp:include>
</div>
