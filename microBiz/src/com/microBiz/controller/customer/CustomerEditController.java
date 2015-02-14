package com.microBiz.controller.customer;

import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;

public class CustomerEditController extends CustomerCreateController{
    
    public Customer getCustomer() {
        // list at the top, details panel hidden first
        Customer customer = new CustomerService().get(asKey("customerKey"));
        // customer type validation, only for edit, type cannot be empty???
        customer.setOldCustomerType(customer.getType());
        return customer;
    }
    
    public String getReturnJsp() {
        return "customer-edit.jsp";
    }
}
