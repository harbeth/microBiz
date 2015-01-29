<%@include file="../includes/taglib.jsp"%>
<%-- call from new quote from customer --%>
<c:import url = "../layout/layout.jsp">
	<c:param name = "title" value ="Quotation"/>
	<c:param name = "module" value ="quote"/>
	<c:param name = "body" value ="quote-new.jsp"/>
</c:import>

<script type ="text/javascript">
    $(document).ready(function() {
    	quoteNewFn.init();
	});
</script>