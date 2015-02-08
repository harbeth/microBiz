<%@include file="../includes/taglib.jsp"%>

<c:import url = "../layout/layout.jsp">
	<c:param name = "title" value ="Customer"/>
	<c:param name = "module" value ="customer"/>
	<c:param name = "body" value ="customer-list.jsp"/>
</c:import>

<%--

<div class="panel-heading">
				<c:if test = "${f:h(customer.commercial)}" >
					<a link="customerContactEdit" customerKey="${f:h(customer.key)}" contactKey="-1"  class="btn btn-info btn-sm" role="button">New Contact</a>
				</c:if>
				<a href="/newInvoice?customerkey=${f:h(customer.key)}" class="btn btn-info btn-sm" role="button">New Invoice</a>  
				<a href="/newQuote?customerKey=${f:h(customer.key)}" class="btn btn-info btn-sm" role="button">New Quotation</a>
				<a link="customerEditClose"  class="btn btn-info btn-sm" role="button">Close</a>
			</div>

<div class="panel-heading">
				<c:if test = "${f:h(customer.commercial)}" >
					<a link="customerContactEdit" customerKey="${f:h(customer.key)}" contactKey="-1"  class="btn btn-info btn-sm" role="button">New Contact</a>
				</c:if>
				<a href="/invoice/invoiceCreate?customerkey=${f:h(customer.key)}" class="btn btn-info btn-sm" role="button">New Invoice</a>  
				<a href="/quote/quoteCreate?customerKey=${f:h(customer.key)}" class="btn btn-info btn-sm" role="button">New Quotation</a>
				<a link="customerEditClose"  class="btn btn-info btn-sm" role="button">Close</a>
			</div>

--%>