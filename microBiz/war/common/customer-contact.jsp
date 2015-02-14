<%@include file="../includes/taglib.jsp"%>

<c:if test = "${f:h(customerContactShown)}" >
<div class="col-lg-12">
	<div class="form-group input-group">
		<span class="input-group-addon">Contact</span> 
		<select id="customerContactSelect" name="contactKey" class="form-control">
			<option value="-1">Select</option>
			<c:forEach items="${contacts}" var="c">
				<option value="${f:h(c.key)}"
					<c:if test = "${f:h(c.selected)}" >
						selected
					</c:if> >${f:h(c.name)}</option>
			</c:forEach>
		</select>
	</div>
	<div id="customerContactInfoDIV">
		<jsp:include page="./customer-contact-info.jsp" />
	</div>
</div>
</c:if>