<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<script type ="text/javascript">
    $(document).ready(function() {
    	salesCommissionFn.init();
	});
</script>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">Calculate Sales Commission</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form>
					<div class="form-group">
					
						<select id="selectSales" name="sales" class="form-control">
							<option value=""> Select Sales ... </option>
							<c:forEach items="${salesList}" var="i">
								<option value="${f:h(i.name)}">${f:h(i.name)}</option>
							</c:forEach>
						</select>
					</div>
		
				</form>
				
				<div id="invoicesForSalesCommissionDIV"></div>
			</div>
		</div>
	</div>
</div>