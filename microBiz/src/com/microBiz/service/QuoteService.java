package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.QuoteMeta;
import com.microBiz.model.Customer;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteItem;
import com.microBiz.model.QuoteVersion;



public class QuoteService {
    private QuoteMeta p = new QuoteMeta();
 
    
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
    
    public void saveQuoteItem(QuoteItem f) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, f);
        tx.commit();
    }
    
    public void saveQuoteVersion(QuoteVersion f) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, f);
        tx.commit();
    }
    
    public void save(Key parentKey, Customer f) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(parentKey, Quote.class);
        f.setKey(childKey);
        Datastore.put(tx, f);
        tx.commit();
    }

}
