package com.microBiz.controller.customer;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;

public class CustomerCreateController extends BaseController{
    
    public CustomerCreateController(){
        super();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // list at the top, details panel hidden first
        Customer customer = new Customer();
        BeanUtil.copy(customer, request);
        
        requestScope("cxTypes", cxTypes);
        requestScope("ratings", cxRatings);
        return forward("customer-new.jsp");
    }
}
