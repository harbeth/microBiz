package com.microBiz.controller.customer;


public class NewCustomerController extends CustomerCreateController {
    
    public String getReturnJsp() {
        return "customer-new-wrapper.jsp";
    }
}
