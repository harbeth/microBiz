package com.microBiz.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.microBiz.service.MiRoleAccessRightService;
import com.microBiz.service.MiUserService;

//this filter will first check if user has logged in, then if user can access the requested page, 
public class MiFilter implements Filter {

    private UserService gaeUserService;
    private MiUserService miUserService;
    private MiRoleAccessRightService miRoleService;

    public void init(FilterConfig fConfig) throws ServletException {
        gaeUserService = UserServiceFactory.getUserService();
        miUserService = new MiUserService();
        miRoleService = new MiRoleAccessRightService();
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String myrole = null;
        String uriStr = req.getRequestURI();

       /*
        if (gaeUserService.isUserLoggedIn()) {//

            if (session.getAttribute("myrole") == null) {
                String email =  gaeUserService.getCurrentUser().getEmail().toLowerCase();                                  
                MiUser user = miUserService.getUserByEmail(email);
                if (user == null) {
                    // if user not exist in user table, no access
                    req.getRequestDispatcher("/noAccess.jsp").forward(req, res);
                    return;
                }
                //System.out.println("get user" + user.getName());
                myrole = user.getMiRole();
                session.setAttribute("myrole", myrole);
                session.setAttribute("email", email);

            } else {
                
                    myrole = (String)session.getAttribute("myrole");
               
            }
            MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
            syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
            String accesssibleModules = (String)syncCache.get(myrole); // read from cache
            if (accesssibleModules == null) {
                accesssibleModules =  miRoleService.getAccessibleModuleByRole(myrole);

              syncCache.put(myrole, accesssibleModules); // populate cache
            }


            //System.out.println(role + "  " + accesssibleModules);
            //System.out.println("uri is *****" + uriStr);
            
            if(uriStr.length()>1 && uriStr.indexOf("_ah")==-1){// not from http:// localhost:8888/, not from localhost:8888/_ad/admin
                String requestedModule = "";
                if(uriStr.lastIndexOf("/")>1){//from http;//localhost:8888/invoice/invoicedetails
                    requestedModule = uriStr.substring(1, uriStr.indexOf("/", 1));
                }else{//from http;//localhost:8888/invoice
                    requestedModule = uriStr.substring(1);
                }
                
                if (accesssibleModules.indexOf(requestedModule) == -1) {
                    req.getRequestDispatcher("/noAccess.jsp").forward(req, res);
                    return;
                }
                
            }
         
            chain.doFilter(req, res);
        } else {// user hasn't loggin via google
            if (!req.getRequestURI().contains("login")) {
                req.getRequestDispatcher("/login.jsp").forward(req, res);

                return;
            } else {// let actual login page pass
                chain.doFilter(req, res);
            }
        }
        */
        if (session.getAttribute("myrole")==null){
            session.setAttribute("myrole", "admin");
            session.setAttribute("email", "admin@gmail.com");
        }
        chain.doFilter(req, res);

    }

    public void destroy() {
        // we can close resources here
    }

}
