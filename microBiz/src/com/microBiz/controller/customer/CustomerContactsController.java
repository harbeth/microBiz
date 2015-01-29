package com.microBiz.controller.customer;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;

// on invoice edit link click, load job tab first
public class CustomerContactsController extends BaseController{

    private CustomerService customerService;
    
    public CustomerContactsController(){
        super();
        customerService = new CustomerService();
    }
    
    @Override
    public Navigation run() throws Exception {

        // list at the top, details panel hidden first
        Customer customer = customerService.get(asKey("customerKey")); 
        
        requestScope("customer", customer);
        requestScope("contacts", customer.getContactListRef().getModelList());
        
        return forward("customer-contacts.jsp");
    }
}
