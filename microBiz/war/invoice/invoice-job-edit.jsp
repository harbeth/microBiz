<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

    <script>
     $(function() {
       $( "#startingDateStr" ).datepicker();
     });
     </script>
 <div class="col-lg-12">
	<div class="well">    
<form id="invoiceDetailJobDetailForm" role="form" method="post" action="${f:url('/invoice/invoiceJobEditAction')}">

	    <input type="hidden" ${f:hidden("invoiceKey")}/>
		<c:if test="${key != null}">
			<input type="hidden" ${f:hidden("key")} />
		</c:if>
		
		<div class="form-group input-group">
			<span class="input-group-addon">Installer</span> <select
				name="installer" class="form-control">
				<c:forEach items="${installers}" var="i">
					<option value="${f:h(i.key)}"
						<c:if test = "${f:h(installerRef.model.key) == f:h(i.key)}" >
							selected
						</c:if>>${f:h(i.name)}</option>
				</c:forEach>
			</select>
		</div>
			
		<div class="form-group input-group">
			<span class="input-group-addon">Starting Date</span> 
			<input type="text" ${f:text("startingDateStr")} class="form-control" id="startingDateStr"/>
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon">Arrival Time</span> 
			<input type="text" ${f:text("arrivalTime")} class="form-control" />
		</div>

		<div class="form-group input-group">
			<span class="input-group-addon">Notes</span>
			<textarea name="note" class="form-control" rows="2">${f:h(note)}</textarea>
		</div>
		
		<div class="form-group">
			<button type="submit" class="btn btn-default">Submit</button>
			<a link="invoiceEditCloseJob" invoiceKey="${f:h(invoiceKey)}" class="btn btn-default" role="button">Close</a>
		</div>

</form>