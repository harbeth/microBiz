<%@include file="../includes/taglib.jsp"%>
<!-- for shortcut, refresh whole page , not use now -->

<c:import url = "../layout/layout.jsp">
	<c:param name = "title" value ="Invoice"/>
	<c:param name = "module" value ="invoice"/>
	<c:param name = "body" value ="invoice-new.jsp"/>
</c:import>

<script type ="text/javascript">
    $(document).ready(function() {
    	invoiceNewFn.init();
	});
</script>