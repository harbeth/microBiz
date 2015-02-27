package com.microBiz.controller.customer;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;

public class CustomerCreateController extends BaseController {
    
    public CustomerCreateController(){
        super();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // list at the top, details panel hidden first
        BeanUtil.copy(getCustomer(), request);
        requestScope("cxTypes", cxTypes);
        requestScope("cxRatings", cxRatings);
        requestScope("pymtTerms", pymtTerms);
        return forward(getReturnJsp());
    }
    
    public Customer getCustomer() {
        Customer c = new Customer();
        return c;
    }
    
    public String getReturnJsp() {
        return "customer-new.jsp";
    }
}
