<%@include file="../includes/taglib.jsp"%>

<div class="panel-body">
	<div class="row">
		<div class="col-xs-6 col-md-4">

			<strong>${f:h(quote.customerRef.model.name)} </strong> <br>


			<c:if test="${f:h(quote.hasContact)}">
			<font class="text-muted">Contact:</font> ${f:h(quote.contactRef.model.name)} &nbsp;<br>
			</c:if>
			${f:h(quote.address)} &nbsp;
		</div>
		<div class="col-xs-6 col-md-4">
			<font class="text-muted">Status:</font>${f:h(quote.statusLable)} <br>
			<font class="text-muted">Note:</font>
			${f:h(quote.note)}
		</div>
	</div>

</div>
