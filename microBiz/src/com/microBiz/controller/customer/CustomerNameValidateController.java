package com.microBiz.controller.customer;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;

public class CustomerNameValidateController extends Controller {

    protected CustomerService customerService;
    
    public CustomerNameValidateController(){
        super();
        customerService = new CustomerService();
    }
    
    @Override
    public Navigation run() throws Exception {
    	
        // list at the top, details panel hidden first
        String customerName = asString("name");
        List<Customer> returnList = customerService.getByCustomerName(customerName);
        if ( returnList == null || returnList.size() == 0 ) {
            response.getWriter().write("success");
        }else{
            response.getWriter().write("The customer name " + customerName + " already exists, please input another one.");
        }
        return null;
    }
}
