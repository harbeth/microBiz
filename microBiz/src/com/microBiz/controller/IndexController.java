package com.microBiz.controller;

import org.slim3.controller.Navigation;

public class IndexController extends BaseController {



    public IndexController() {
        super();

    }

    @Override
    public Navigation run() throws Exception {
       
 
       return forward("index.jsp");
      
       
    }
}
