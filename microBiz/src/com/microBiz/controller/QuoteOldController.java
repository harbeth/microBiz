package com.microBiz.controller;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.meta.ContactMeta;
import com.microBiz.meta.CustomerMeta;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.model.Quote;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;





public class QuoteOldController extends BaseController{

    private QuoteService s;
    private ProductService ps;
    private CustomerService cxs;
    public QuoteOldController(){
        super();
        s = new QuoteService();
        ps = new ProductService();
        cxs = new CustomerService();


    }
    @Override
    public Navigation run() throws Exception {

    
        Quote q = null;
        Customer c = null;  
        if(asKey("key") != null){// from edit quote link
            q = s.get(asKey("key"));  
            c = q.getCustomerRef().getModel();
        }else{
            c = cxs.get(asKey("customerKey")); 
            q = new Quote();
        }       
       
        BeanUtil.copy(q, request);
        requestScope("customer", c);
        if (c.getContactListRef()!= null && c.getContactListRef().getModelList()!= null && c.getContactListRef().getModelList().size()>0){
            requestScope("contacts", c.getContactListRef().getModelList());
        }
        
        requestScope("prds", ps.getSellingPrds());
        requestScope("txRates", txRates);
        return forward("quote.jsp");

       
    }
    
    
}
