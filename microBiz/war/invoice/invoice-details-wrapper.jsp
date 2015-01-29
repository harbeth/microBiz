<%@include file="../includes/taglib.jsp"%>

<c:import url = "../layout/layout.jsp">
	<c:param name = "title" value ="Invoice"/>
	<c:param name = "module" value ="invoice"/>
	<c:param name = "body" value ="invoice-details.jsp"/>
</c:import>

<script type ="text/javascript">
    $(document).ready(function() {
    	invoiceDetailFn.init();
	});
</script>