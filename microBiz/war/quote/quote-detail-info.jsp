<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
	<p>
		Address: ${f:h(quote.address)} &nbsp; Customer:
		${f:h(quote.customerRef.model.name)} &nbsp; 
		<c:if test = "${f:h(quote.hasContact)}" >
			Contact: ${f:h(quote.contactRef.model.name)} &nbsp;
		</c:if> 
		&nbsp;Status:${f:h(quote.status)} 
		&nbsp;Note: ${f:h(quote.note)}
	</p>
</div>
