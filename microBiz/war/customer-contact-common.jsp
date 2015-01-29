<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<div class="form-group input-group">
	<span class="input-group-addon">Customer</span> 
	<select id="customerSelect" name="customerKey" class="form-control">
		<option value="-1">Select</option>
		<c:forEach items="${customers}" var="c">
			<option value="${f:h(c.key)}"
				<c:if test = "${f:h(c.selected)}" >
					selected
				</c:if> >${f:h(c.name)}</option>
		</c:forEach>
	</select>
</div>

<div id="customerContactDIV">
	<c:if test = "${f:h(contactShown)}" >
		<jsp:include page="customer-contact.jsp" />
	</c:if>
</div>