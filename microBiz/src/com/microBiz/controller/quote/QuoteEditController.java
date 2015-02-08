package com.microBiz.controller.quote;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.common.CustomerContactLoadController;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.model.Quote;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;


public class QuoteEditController extends CustomerContactLoadController {
    
    protected QuoteService quoteService;
    protected ProductService productService;
    
    public QuoteEditController(){
        super();
        quoteService = new QuoteService();
        productService = new ProductService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // edit quote from customer details
        Quote quote = quoteService.get(asKey("quoteKey"));
        BeanUtil.copy(quote, request);
        // customer not empty
        Customer customer = quote.getCustomerRef().getModel();
        // contact could be empty
        Contact contact = quote.getContactRef().getModel();
        setCustomerContactData(customer, contact);
        requestScope("quoteStatus", quoteStatus);
        // to edit mode
        return forward(getReturnJsp());
    }
 
    // for edit tab in quote details page
    public String getReturnJsp() {
        return "quote-edit.jsp";
    }
}
