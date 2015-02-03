package com.microBiz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.MicroBizUtil;
import com.microBiz.meta.QuoteMeta;
import com.microBiz.model.Customer;
import com.microBiz.model.Invoice;
import com.microBiz.model.Item;
import com.microBiz.model.Orders;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
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

    //here for insert new quote, return quote key
    public Key createQuote(Quote quote, QuoteVersion qv, Orders orders,
            List<Item> qiList) {

        Transaction tx = Datastore.beginTransaction();
        Key parentKey = Datastore.put(tx, quote);
        System.out.println("Quote key is " + Datastore.keyToString(parentKey));
        Key quoteVersionKey = Datastore.allocateId(parentKey, QuoteVersion.class);
        qv.setKey(quoteVersionKey);
        qv.getQuoteRef().setModel(quote);
        
        

        Key ordersKey = Datastore.allocateId(quoteVersionKey, Orders.class);
        orders.setKey(ordersKey);
        qv.getOrdersRef().setModel(orders);
        orders.getQuoteVersionRef().setModel(qv);
        Datastore.put(tx, qv);
        Datastore.put(tx,orders);
        setItemParent(ordersKey,orders, qiList);
        Datastore.put(tx,qiList);
        tx.commit();
        return parentKey;

        
    }

    // return quote key
    public Key saveAsNewVersion(QuoteVersion qv, Orders orders, List<Item> qiList) {
        Transaction tx = Datastore.beginTransaction();
        Quote quote = qv.getQuoteRef().getModel();
        int quoteVersionCount = quote.getCount();
        quoteVersionCount ++ ;
        quote.setCount(quoteVersionCount);
        Datastore.put(tx, quote);
        Key quoteVersionKey = Datastore.allocateId(quote.getKey(), QuoteVersion.class);
        qv.setName(MicroBizUtil.getQuoteVersionName(quoteVersionCount));
        qv.setCreateAt(new Date());
        qv.setKey(quoteVersionKey);

        Key ordersKey = Datastore.allocateId(quoteVersionKey, Orders.class);
        orders.setKey(ordersKey);
        qv.getOrdersRef().setModel(orders);
        orders.getQuoteVersionRef().setModel(qv);
        Datastore.put(tx, qv);
        Datastore.put(tx, orders);
        setItemParent(ordersKey,orders, qiList);
        Datastore.put(tx, qiList);
        tx.commit();
        return quote.getKey();
    }
    

    public void saveAsCurrentVersion(QuoteVersion qv, Orders orders, List<Item> qiList) {
        Transaction tx = Datastore.beginTransaction();
        Quote quote = qv.getQuoteRef().getModel();
        QuoteVersion newQv = new QuoteVersion();
        newQv.setName(qv.getName());
        newQv.getQuoteRef().setModel(quote);
        newQv.setCreateAt(new Date());
        
        Key quoteVersionKey = Datastore.allocateId(quote.getKey(), QuoteVersion.class);
       
        
       newQv.setKey(quoteVersionKey);
        Datastore.put(tx, newQv);
        //delete old version
        Datastore.deleteAll(tx, qv.getKey());

        Key ordersKey = Datastore.allocateId(quoteVersionKey, Orders.class);
        orders.setKey(ordersKey);
       newQv.getOrdersRef().setModel(orders);
       orders.getQuoteVersionRef().setModel(newQv);
        setItemParent(ordersKey,orders, qiList);
       
        
    }
    
 
    
    private void setItemParent(Key ordersKey,Orders orders, List<Item> items ){
       
        Iterator<Item> itemsI= items.iterator();

        while(itemsI.hasNext()) {
            Item oneItem = (Item)itemsI.next();
            Key itemKey = Datastore.allocateId(ordersKey, Item.class);
            oneItem.setKey(itemKey);
            oneItem.getOrdersRef().setModel(orders);

        }
 
    }




}
