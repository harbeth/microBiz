package com.microBiz.controller.quote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;
import com.microBiz.controller.BaseController;
import com.microBiz.controller.common.OrderLoadActionController;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Order;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteOrder;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;

// command controller for new/edit save
public class QuoteCreateActionController extends OrderLoadActionController {

    private QuoteService quoteService;
    private CustomerService customerService;
    private ContactService contactService;


    
    public QuoteCreateActionController(){
        super();
        quoteService = new QuoteService();
        customerService = new CustomerService();
        contactService = new ContactService();


    }
    
    @Override
    public Navigation run() throws Exception {
        
        Quote quote = new Quote();
        BeanUtil.copy(request, quote); 
        // for new, not from UI
        quote.setCount(1);
        
        if(asString("active")==null){
           // i.setActive("");
        }
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
        
        QuoteOrder quoteOrder = new QuoteOrder();
        quoteOrder.setName(MicroBizUtil. getQuoteVersionName(quote.getCount()));
        quoteOrder.setCreateAt(new Date());
      
        Order order = getOrderData();

        Key orderkey = orderService.saveNewOrder(order);
        
        Key quoteKey = quoteService.save(quote);
        
        quoteOrder.getOrderRef().setKey(orderkey);
        quoteOrder.getQuoteRef().setModel(quote);
        
        quoteService.saveQuoteOrder(quoteOrder);
        
        
      
        return redirect("/quote/quoteDetails?quoteKey=" + Datastore.keyToString(quoteKey));
    }
}
