<%@include file="../includes/taglib.jsp"%>
<div class="col-lg-12">
<div class="well">
	
	
		<form name ="miLogForm" typerole="form" type="post" action="/common/logEventAction">
			<div class="row">
				<c:if test="${invoiceKey != null}">
					<input type="hidden" ${f:hidden("invoiceKey")} />
				</c:if>
				<c:if test="${quoteKey != null}">
					<input type="hidden" ${f:hidden("quoteKey")} />
				</c:if>
		 		<div class="col-sm-10">
					<div class="form-group">
						<input class="form-control" name="note" placeholder="Enter your logs" ></input>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group">
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
				</div>
			</div>
		</form>
	
	
		<div class="table-responsive">
			<table class="table table-striped">
				<tbody>
					<c:forEach var="l" items="${miLogs}">
						<tr><td>
						<span class="text-muted small"><em>${f:h(l.enterDateStr)} by ${f:h(l.creatorName)} : </em></span>         
						 ${f:h(l.note)}
						</td></tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
