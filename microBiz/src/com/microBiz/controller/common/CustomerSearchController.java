package com.microBiz.controller.common;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;


public class CustomerSearchController extends BaseController {

    // for customer drop down change
    private CustomerService customerService;
    
    public CustomerSearchController(){
        super();
        customerService = new CustomerService();
    }
    
    @Override
    public Navigation run() throws Exception {
        String customerSearchStr = asString("customerSearch");
        System.out.println(" customerSearchStr : " + customerSearchStr);
        List<Customer> customerList = customerService.searchActiveCustomerStartWith(customerSearchStr);
        StringBuilder b = new StringBuilder("{\"query\": \"Unit\", \"suggestions\": [");
        // first one for new
        b.append("{\"value\": \"-- New Customer --\", \"data\": \"-1\"}");
        for (Customer customer : customerList) {
            b.append(", {\"value\": \""+customer.getName()+"\", \"data\": \"" + Datastore.keyToString(customer.getKey())+"\"}");
        }
        b.append("]}");
        response.getWriter().print(b.toString());
        System.out.println(" return str : " + b.toString());
        response.flushBuffer();
        return null;
    }
}

