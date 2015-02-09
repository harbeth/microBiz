
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>



<div class="row">
	<div class="col-lg-12">
	
		<p>
			Invoice Number: ${f:h(job.invoiceRef.model.invoiceNumber)} <br>
			Address: ${f:h(job.invoiceRef.model.address)} <br> Job Start at:
			${f:h(job.startingDateStr)}
		<p>
	</div>
</div>

