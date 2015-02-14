<%@include file="../includes/taglib.jsp"%>

<c:if test = "${f:h(contactInfoShown)}">
	<div class="panel panel-default">
		<div class="panel-heading">Contact Information</div>
		<div class="panel-body">
			<div class="col-lg-6">
				<p>
					Number : ${f:h(contact.name)} <br> 
					Phone : ${f:h(contact.phone)} <br> 
					Email: ${f:h(contact.email)} <br>
				</p>
			</div>
		</div>
	</div>
</c:if>