package com.microBiz.controller.quote;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.CustomerContactLoadController;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.model.Quote;
import com.microBiz.service.QuoteService;

public class QuoteEditController extends CustomerContactLoadController {

    private QuoteService quoteService;
    
    public QuoteEditController(){
        super();
        quoteService = new QuoteService();
    }
    
    @Override
    public Navigation run() throws Exception {
    	
        // key should not be empty
        Quote quote = quoteService.get(asKey("quoteKey"));  
        BeanUtil.copy(quote, request);
        
        Customer customer = quote.getCustomerRef().getModel();
        Contact contact = null;
        if ( quote.getContactRef().getModel() != null ) {
            contact = quote.getContactRef().getModel();
        }
        
        // show contact DIV flag, move to super class
        setCustomerContactData(customer, contact);
        return forward("quote-edit.jsp");
    }
}
