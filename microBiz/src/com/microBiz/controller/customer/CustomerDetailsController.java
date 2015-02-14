package com.microBiz.controller.customer;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.model.Quote;
import com.microBiz.service.CustomerService;

// on invoice edit link click, load job tab first
public class CustomerDetailsController extends BaseController {

    private CustomerService customerService;
    
    public CustomerDetailsController(){
        super();
        customerService = new CustomerService();
    }
    
    @Override
    public Navigation run() throws Exception {

        // list at the top, details panel hidden first
        Customer customer = customerService.get(asKey("customerKey")); 
        
        requestScope("customer", customer);
        requestScope("contacts", customer.getContactListRef().getModelList());
        
        // get data for invoice tab
        List<Quote> quotes = customer.getQuoteListRef().getModelList();
        requestScope("quotes", quotes);
        
        return forward("customer-details.jsp");
    }
}
