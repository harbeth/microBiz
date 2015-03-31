package com.microBiz.controller.customer;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;

public class CustomerController extends CustomerSearchBaseController{

 
    public CustomerController(){
        super();
  
    }

    @Override
    public List<Customer> getCustomers() {
        return customerService.getAll();
    }
    @Override
    public String getReturnedJsp() {
        return "customer.jsp";
    }
    
    
}
