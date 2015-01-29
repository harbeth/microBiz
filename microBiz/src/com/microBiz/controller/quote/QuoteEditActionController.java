package com.microBiz.controller.quote;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.QuoteMeta;
import com.microBiz.model.Quote;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;
import com.microBiz.service.QuoteService;

//after save for edit, keep the same tab and update info DIV

public class QuoteEditActionController extends BaseController {

    private QuoteService quoteService;
    private CustomerService customerService;
    private ContactService contactService;
    private QuoteMeta metaQuote;
    
    public QuoteEditActionController() {
        super();
        quoteService = new QuoteService();
        customerService = new CustomerService();
        contactService = new ContactService();
        metaQuote = new QuoteMeta();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        Quote quote = quoteService.get(asKey(metaQuote.key));
        BeanUtil.copy(request, quote);
        
        // set customer reference if has selection
        String customerKey = quote.getCustomerKey();
        if ( !"-1".equals(customerKey) ) { 
            quote.getCustomerRef().setModel(customerService
                          .get(Datastore.stringToKey(customerKey)));
        }
        // set contact reference if has selection
        String contactKey = quote.getContactKey();
        if ( !"-1".equals(contactKey) ) { 
            quote.getContactRef().setModel(contactService
                           .get(Datastore.stringToKey(quote.getContactKey())));
        }
        
        quoteService.save(quote);
        quote = quoteService.get(quote.getKey());
        requestScope("quote", quote);
        
        return forward("quote-detail-info.jsp");
    }
    
}
