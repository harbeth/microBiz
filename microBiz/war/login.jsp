<%@ page import= "com.google.appengine.api.users.UserService, com.google.appengine.api.users.UserServiceFactory" %>

<% 

UserService userService = UserServiceFactory.getUserService();
String loginUrl = userService.createLoginURL("/");

%>
<p>Please <A href="<%=loginUrl %>" >Login</A>  to access this application .</p>

