<%@include file="../includes/taglib.jsp"%>

<div class="panel panel-default">
	<div class="panel-body">
		<div class="list-group">
		   <c:forEach items="${quoteOrders}" var="qo">
		        <a link="quoteOrder" quoteOrderKey="${f:h(qo.key)}" class="list-group-item">
		            <i class="fa fa-comment fa-fw"></i> ${f:h(qo.name)}  ${f:h(qo.createAtStr)} 
		             Total Amount: ${f:h(qo.orderRef.model.total)} 
		            <span class="pull-right text-muted small"><em>Edit</em></span>
		        </a>
			</c:forEach>
		</div>
	</div>
</div>
<div id ="quoteOrderChangeDIV">
</div>

