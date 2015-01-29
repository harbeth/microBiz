package com.microBiz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.meta.QuoteMeta;
import com.microBiz.model.Customer;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteItem;
import com.microBiz.model.QuoteVersion;
import com.microBiz.service.CustomerService;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;



public class QuoteActionController extends BaseController{

    private QuoteService s;
    private CustomerService cxs;
    private ProductService ps;
    private QuoteMeta metaP;
    public QuoteActionController(){
        super();
        s = new QuoteService();
        cxs = new CustomerService();
        ps = new ProductService();
        metaP = new QuoteMeta();

    }
    @Override
    public Navigation run() throws Exception {

        Quote q = null;
        Customer c = cxs.get(asKey("customerKey"));
        if(asKey(metaP.key)!= null){ // update
            q = s.get(asKey(metaP.key));
            BeanUtil.copy(request,q); 

        }else{ // insert new
            q = new Quote();
            BeanUtil.copy(request,q); 
            q.getCustomerRef().setModel(c);
            String[] items = paramValues("item[]");
            String[] rates = paramValues("rate[]");
            String[] descs = paramValues("desc[]");
            String[] quantities = paramValues("quantity[]");
            QuoteVersion  qv = new QuoteVersion();
            qv.setCreateDate(new Date());
            //qv.setQuoteVerson("v1");
            qv.getQuoteRef().setModel(q);
            q.getQuoteVersionsRef().getModelList().add(qv);
            List<QuoteItem> ois = populateOrderItems(items, rates, descs, quantities);
            Iterator i = ois.iterator();
            while (i.hasNext()){
                QuoteItem or = (QuoteItem)i.next();
                or.getQuoteVersionRef().setModel(qv);
                qv.getQuoteItemsRef().getModelList().add(or);
                s.saveQuoteItem(or);
                
            }
            s.saveQuoteVersion(qv);
            s.save(q);
            
            
 
        }

        requestScope("customer", c);
        requestScope("contacts", c.getContactListRef().getModelList());
        requestScope("quotes", c.getQuoteListRef().getModelList());
        return forward("customerDetails.jsp");


       
    }
    
    public List<QuoteItem> populateOrderItems(String[] items, String[] rates,String[] descs, String[] quantities) {
        List<QuoteItem> r = new ArrayList();
        for (int i = 0; i< items.length-1;i++){
            QuoteItem result = new QuoteItem();
            result.setDesc(descs[i]);
            result.setQty(Double.parseDouble(quantities[i]));
            result.setRate(Double.parseDouble(rates[i]));
            result.getProductRef().setModel(ps.get(Datastore.stringToKey(items[i])));
            r.add(result);
            
            
        }
        
        return r;
    }
    
    
}
