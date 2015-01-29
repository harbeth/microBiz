<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<div class="panel panel-default">
	<div class="panel-heading">Contact Information</div>
	<div class="panel-body">
		<div class="col-lg-6">
			<p>
				Number : ${f:h(contact.name)} <br> 
				Phone : ${f:h(contact.phone)} <br> 
				Email: ${f:h(contact.email)} <br>
			</p>
		</div>
	</div>
</div>