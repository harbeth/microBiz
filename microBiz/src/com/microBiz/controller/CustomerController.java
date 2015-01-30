package com.microBiz.controller;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;

public class CustomerController extends BaseController{

    private CustomerService customerService;
    
    public CustomerController(){
        super();
        customerService = new CustomerService();
    }
    @Override
    public Navigation run() throws Exception {
        
        // only get data for invoice list, not details
        List<Customer> customerList = customerService.getAll();
        requestScope("customers", customerList);

        requestScope("cxTypes", cxTypes);
        requestScope("ratings", cxRatings);
        requestScope("units", units);
        
        return forward("customer/customer.jsp");

        /*
        Customer p = null;
        if(asKey(metaP.key) != null){// from edit link
            p = s.get(asKey(metaP.key));         
        }else{
            p = new Customer();
        }
        BeanUtil.copy(p, request);


        requestScope("cxTypes", cxTypes);
        requestScope("ratings", cxRatings);
        requestScope("units", units);
        requestScope("customers", s.getAll());
        return forward("customer.jsp");
        */
       
    }
    
    
}
