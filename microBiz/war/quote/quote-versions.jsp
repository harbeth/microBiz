<%@include file="../includes/taglib.jsp"%>


<div class="panel panel-default">
<div class="panel-body">

<div class="list-group">
   <c:forEach items="${quoteVersions}" var="qv">
                                <a href="#" class="list-group-item">
                                    <i class="fa fa-comment fa-fw"></i> ${f:h(qv.name)}  ${f:h(qv.createAtStr)} 
                                     Total Amount: ${f:h(qv.orderRef.model.total)} 
                                    <span class="pull-right text-muted small"><em>Edit</em>
                                    </span>
                                </a>

		</c:forEach>

</div>
<div>
</div>

<div id ="quoteVersionChangeDIV">
	<jsp:include page="./quote-version.jsp" flush="true"></jsp:include>
</div>


