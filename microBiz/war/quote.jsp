<%@include file="includes/header.jsp"%>

<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			Quotation <small> for: ${f:h(customer.name)} </small>
		</h1>

	</div>
</div>
<!-- /.row -->

<!-- Page conent -->

<form id="defaultForm" role="form" method="post"
	action="${f:url('quoteAction')}">

	<input type="hidden" name="customerKey" value="${f:h(customer.key)}" />
	<c:if test="${key!=null}">
		<input type="hidden" ${f:hidden("key")} />



	</c:if>
	<div class="row">
		<div class="col-lg-6">

			<div class="form-group input-group">
				<span class="input-group-addon">Address</span> <input type="text"
					${f:text("address")} class="form-control" />
			</div>

			<c:if test="${contacts!=null}">
				<div class="form-group input-group">
					<span class="input-group-addon">Contact</span> <select name="type"
						class="form-control">
						<c:forEach items="${contacts}" var="c">
							<option value="${f:h(c.key)}"
								<c:if test = "${f:h(quoteRef.model.key) == f:h(c.key)}" >
								selected
							</c:if>>${c.name}</option>
						</c:forEach>
					</select>
				</div>
			</c:if>





		</div>
		<div class="col-lg-6">


			<div class="form-group input-group">
				<span class="input-group-addon">Notes</span>
				<textarea class="form-control" rows="2"></textarea>


			</div>
		</div>
	</div>
	<%@include file="includes/addItem.jsp"%>

</form>






<%@include file="includes/footer.jsp"%>
