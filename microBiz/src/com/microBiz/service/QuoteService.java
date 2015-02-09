package com.microBiz.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.MicroBizUtil;
import com.microBiz.meta.QuoteMeta;
import com.microBiz.meta.QuoteOrderMeta;
import com.microBiz.model.Customer;
import com.microBiz.model.Order;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteOrder;

public class QuoteService {
    
    private QuoteMeta p = new QuoteMeta();
    private QuoteOrderMeta quoteOrderMeta = new QuoteOrderMeta();
 
    
    public List<Quote> getAll() {
        return Datastore.query(p).asList();
    }
    
    public List<Quote> getCustomerQuote(Key customerKey) {
        return Datastore.query(p).asList();
    }
    
    public Quote get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public Key save(Quote f) {
        Transaction tx = Datastore.beginTransaction();
        Key key = Datastore.put(tx, f);
        tx.commit();
        return key;
    }
    
    public Key saveQuoteOrder(QuoteOrder qo) {
        Transaction tx = Datastore.beginTransaction();
        Key key = Datastore.put(tx, qo);
        tx.commit();
        return key;
    }



    public QuoteOrder getQuoteOrder(Key key) {
        return Datastore.get(quoteOrderMeta, key);
    }
}
