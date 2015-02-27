<%@ page
	import="com.google.appengine.api.memcache.MemcacheServiceFactory,
com.google.appengine.api.memcache.MemcacheService,
com.google.appengine.api.memcache.ErrorHandlers,
java.util.logging.Level"%>


<%
	/*
    String myrole = (String) request.getSession().getAttribute("myrole");

	MemcacheService syncCache = MemcacheServiceFactory
			.getMemcacheService();
	syncCache.setErrorHandler(ErrorHandlers
			.getConsistentLogAndContinue(Level.INFO));
	String accesssibleModules = (String) syncCache.get(myrole); // read from cache
	*/
	String accesssibleModules = "manager, invoice, jobReport,quote, customer, mgmt, prd ";
%>

<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<a class="navbar-brand" href="index.html">Foam Expert System</a>
	</div>
	<!-- /.navbar-header -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">

				<%
					if (accesssibleModules.contains("manager")) {
				%>
				<li><a href="/manager/dashboard"><i class="fa fa-dashboard fa-fw"></i>
						Dashboard</a></li>
			   	<%
					}
				%>
				<%
					if (accesssibleModules.contains("customer")) {
				%>
				<li><a href="/customer/customer"><i
						class="fa fa-table fa-fw"></i> Customer</a></li>
				<%
					}
				%>
				<%
					if (accesssibleModules.contains("quote")) {
				%>


				<li><a href="/quote/quote"><i class="fa fa-edit fa-fw"></i>
						Quotation</a></li>

				<%
					}
				%>
				<%
					if (accesssibleModules.contains("invoice")) {
				%>
				<li><a href="/invoice/invoice"><i class="fa fa-table fa-fw"></i>Invoice</a></li>

				<%
					}
				%>
				<%
					if (accesssibleModules.contains("jobReport")) {
				%>
				<li><a href="/jobReport/jobsToReport"><i
						class="fa fa-table fa-fw"></i>job Report</a></li>
				<%
					}
				%>
				<%
					if (accesssibleModules.contains("prd")) {
				%>
				<li><a href="#"><i class="fa fa-wrench fa-fw"></i>Product<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="/prd/product">Product</a></li>
						<li><a href="/prd/inventory">Inventory</a></li>
						<li><a href="/prd/supplier">Supplier</a></li>
					</ul> <!-- /.nav-second-level --></li>
				<%
					}
				%>
				<%
					if (accesssibleModules.contains("mgmt")) {
				%>
				<li><a href="#"><i class="fa fa-wrench fa-fw"></i>Management<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="/mgmt/miUser">User</a></li>
						<li><a href="/mgmt/miRoleAccessRight">Role Access Right</a></li>
					</ul> <!-- /.nav-second-level --></li>
				<%
					}
				%>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>