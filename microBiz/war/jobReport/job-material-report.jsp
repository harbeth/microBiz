
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>

<div class="row">
<h3> Report Material Costs</h3>
</div>

<%@include file="job-info-common.jsp"%>


<div class="row">

	<form  name="jobReportForm" typerole="form" method="post" action="${f:url('/jobReport/jobMaterialReportAction')}">
		<div class="col-lg-6">

				<input type="hidden" name="jobKey" value="${f:h(job.key)}" />


			<div class="form-group input-group">
				<span class="input-group-addon">Product </span> <select id="productSelect"
					name="productKey" class="form-control">
					<option value="-1">Select ...</option>
					<c:forEach items="${prds}" var="p">
						<option value="${f:h(p.key)}">${f:h(p.model)}</option>
					</c:forEach>
				</select>

				</div>
								
								
			<div id="productRatioDIV">
			</div>
			
			<div class="form-group input-group">
				<span class="input-group-addon">Amount Used:</span> <input type="text"
					${f:text("qty")} class="form-control" />
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Notes</span> <input
					type="text" ${f:text("note")} class="form-control" />
			</div>
			
					<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
		</div>
		</form>
		</div>
