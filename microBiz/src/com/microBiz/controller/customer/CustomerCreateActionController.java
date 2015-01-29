package com.microBiz.controller.customer;

import com.microBiz.model.Customer;

public class CustomerCreateActionController extends CustomerSaveActionController {

    public Customer getCustomer() {
        return new Customer();
    }
    
    public String getReturnJsp() {
        return "customer-details.jsp";
    }
}
