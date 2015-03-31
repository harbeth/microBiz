package com.microBiz.controller.customer;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.model.Quote;
import com.microBiz.service.CustomerService;

public class CustomerSearchController extends CustomerSearchBaseController{

 
    public CustomerSearchController(){
        super();
  
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customerList = null;
        String searchNameStr = asString("searchCustomerByName");
        if(searchNameStr==null){
            customerList = customerService.getAll();
        }else{
            customerList = customerService.searchStartWith(searchNameStr);
        }
        return customerList;
    }
    @Override
    public String getReturnedJsp() {
        return "customer-list-div.jsp";
    }
    
    
}
