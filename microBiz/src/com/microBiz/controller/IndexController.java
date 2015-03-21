package com.microBiz.controller;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;

public class IndexController extends BaseController {



    public IndexController() {
        super();

    }

    @Override
    public Navigation run() throws Exception {
        HttpSession session = request.getSession();
        String myRole = (String)session.getAttribute("myrole");
        if(myRole.equals("installer")){
            return forward("/jobReport/jobsToReport");
        }else if (myRole.equals("sales")){
            return forward("/invoice/invoice");
        }else{
            return forward("/manager/dashboard");
        }
 
       
      
       
    }
}
