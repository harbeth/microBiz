package com.microBiz.controller.customer;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;

public class CustomerEditController extends BaseController{

    private CustomerService customerService;
    
    public CustomerEditController(){
        super();
        customerService = new CustomerService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // list at the top, details panel hidden first
        Customer customer = customerService.get(asKey("customerKey"));
        // customer type validation, only for edit, type cannot be empty???
        customer.setOldCustomerType(customer.getType());
        BeanUtil.copy(customer, request);
        
        requestScope("cxTypes", cxTypes);
        requestScope("cxRatings", cxRatings);
        
        return forward("customer-edit.jsp");
    }
}
