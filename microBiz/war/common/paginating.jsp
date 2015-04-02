<%@include file="../includes/taglib.jsp"%>
<div class="row">
	<div class="col-sm-6">
		<div class="dataTables_info" id="dataTables-example_info" role="alert" aria-live="polite" aria-relevant="all">
		Showing ${f:h(start)} to ${f:h(end)} of ${f:h(counts)} entries
		</div>
	</div>
	<c:if test="${pages != null}">
		<div class="col-sm-6">
			<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
				<ul class="pagination">
					<c:if test="${pageNo > 1}">
						<li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous">
						<a link="paginating" pageNo="${f:h(pageNo-1)}">Previous</a></li>
					</c:if>
					<c:forEach var="c" begin="1" end="${f:h(pages)}">
					
						<li class="paginate_button
						<c:if test="${c == pageNo}">
							active
						</c:if>
						" aria-controls="dataTables-example" tabindex="0">
							<a link="paginating" pageNo="${f:h(c)}">${f:h(c)}</a>
						</li>
					
					</c:forEach>
					<c:if test="${pageNo < pages}">
						<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next">
						<a link="paginating" pageNo="${f:h(pageNo+1)}">Next</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</c:if>
</div>