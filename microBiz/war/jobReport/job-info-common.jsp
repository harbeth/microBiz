
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>

Inv#: ${f:h(job.invoiceRef.model.invoiceNumber)}
${f:nbsp('     ')}
<p class="fa fa-home">${f:h(job.invoiceRef.model.address)}</p>${f:nbsp('     ')}
<p class="fa fa-calendar">${f:h(job.startingDateStr)}</p>${f:nbsp('     ')}

