
<!-- /.row -->
<%@include file="../includes/taglib.jsp"%>

Inv#: ${f:h(job.invoiceRef.model.invoiceNumber)} ${f:nbsp('     ')} 
Installer: ${f:h(job.installer)} ${f:nbsp('     ')} 
Helper: ${f:h(job.helperNamesStr)} ${f:nbsp('     ')}  
Address: ${f:h(job.invoiceRef.model.address)} ${f:nbsp('     ')}
Start Date: ${f:h(job.startingDateStr)} ${f:nbsp('     ')}

